package uk.nhs.nhsx.aae

import uk.nhs.nhsx.core.Environment
import uk.nhs.nhsx.core.Environment.EnvironmentKey

data class AAEUploadConfig(
    val aaeUrlPrefix: String,
    val aaeUrlSuffix: String,
    val p12CertificateSecretName: String,
    val p12CertificatePasswordSecretName: String,
    val subscriptionKeySecretName: String,
    val s3DisallowedPrefixList: String,
    val analyticsEventsBucket: String,
    val analyticsDataBucket: String
) {
    companion object {
        private val AAE_URL_PREFIX = EnvironmentKey.string("AAE_URL_PREFIX")
        private val AAE_URL_SUFFIX = EnvironmentKey.string("AAE_URL_SUFFIX")
        private val P12_CERT_SECRET_NAME = EnvironmentKey.string("P12_CERT_SECRET_NAME")
        private val P12_CERT_PASSWORD_SECRET_NAME = EnvironmentKey.string("P12_CERT_PASSWORD_SECRET_NAME")
        private val AAE_SUBSCRIPTION_SECRET_NAME = EnvironmentKey.string("AAE_SUBSCRIPTION_SECRET_NAME")
        private val S3_DISALLOWED_PREFIX_LIST = EnvironmentKey.string("S3_DISALLOWED_PREFIX_LIST")
        private val ANALYTICS_EVENTS_BUCKET = EnvironmentKey.string("ANALYTICS_EVENTS_BUCKET")
        private val ANALYTICS_DATA_BUCKET = EnvironmentKey.string("ANALYTICS_DATA_BUCKET")

        fun fromEnvironment(e: Environment) = AAEUploadConfig(
            e.access.required(AAE_URL_PREFIX),
            e.access.required(AAE_URL_SUFFIX),
            e.access.required(P12_CERT_SECRET_NAME),
            e.access.required(P12_CERT_PASSWORD_SECRET_NAME),
            e.access.required(AAE_SUBSCRIPTION_SECRET_NAME),
            e.access.required(S3_DISALLOWED_PREFIX_LIST),
            e.access.required(ANALYTICS_EVENTS_BUCKET),
            e.access.required(ANALYTICS_DATA_BUCKET)
        )
    }
}
