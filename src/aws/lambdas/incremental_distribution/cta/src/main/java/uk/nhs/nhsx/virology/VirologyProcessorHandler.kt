package uk.nhs.nhsx.virology

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import uk.nhs.nhsx.core.Environment
import uk.nhs.nhsx.core.Handler
import uk.nhs.nhsx.core.SystemClock.CLOCK
import uk.nhs.nhsx.core.aws.s3.AwsS3Client
import uk.nhs.nhsx.core.aws.s3.BucketName
import uk.nhs.nhsx.core.events.CtaTokensGenerated
import uk.nhs.nhsx.core.events.Events
import uk.nhs.nhsx.core.events.PrintingJsonEvents
import uk.nhs.nhsx.core.handler.DirectHandler
import uk.nhs.nhsx.virology.VirologyConfig.Companion.fromEnvironment
import uk.nhs.nhsx.virology.order.TokensGenerator
import uk.nhs.nhsx.virology.persistence.VirologyPersistenceService
import uk.nhs.nhsx.virology.policy.VirologyPolicyConfig
import uk.nhs.nhsx.virology.tokengen.CtaProcessorRequest
import uk.nhs.nhsx.virology.tokengen.VirologyProcessorExports
import uk.nhs.nhsx.virology.tokengen.VirologyProcessorService
import uk.nhs.nhsx.virology.tokengen.VirologyProcessorStore

class VirologyProcessorHandler @JvmOverloads constructor(
    events: Events = PrintingJsonEvents(CLOCK),
    private val virologyProcessorService: VirologyProcessorService = virologyProcessorService(
        Environment.fromSystem(),
        events
    )
) : DirectHandler<CtaProcessorRequest, Map<String, String>>(events, CtaProcessorRequest::class) {

    override fun handler() =
        Handler<CtaProcessorRequest, Map<String, String>> { event, _ ->
            events(CtaTokensGenerated(event))
            val result = virologyProcessorService.generateAndStoreTokens(event)
            events(CtaTokensGenerationComplete(result))
            result.toResponse()
        }

    companion object {
        private const val MAX_RETRY_COUNT = 3
        private fun virologyProcessorService(environment: Environment, events: Events): VirologyProcessorService {
            return VirologyProcessorService(
                VirologyService(
                    VirologyPersistenceService(
                        AmazonDynamoDBClientBuilder.defaultClient(),
                        fromEnvironment(environment),
                        events
                    ),
                    TokensGenerator,
                    CLOCK,
                    VirologyPolicyConfig(),
                    events
                ),
                VirologyProcessorStore(
                    AwsS3Client(events),
                    BucketName.of(System.getenv("virology_tokens_bucket_name"))
                ),
                CLOCK,
                VirologyProcessorExports(),
                MAX_RETRY_COUNT,
                events
            )
        }
    }
}
