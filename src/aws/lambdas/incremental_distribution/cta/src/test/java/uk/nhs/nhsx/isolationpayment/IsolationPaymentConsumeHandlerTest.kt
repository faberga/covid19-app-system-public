package uk.nhs.nhsx.isolationpayment

import io.mockk.every
import io.mockk.mockk
import io.mockk.verifySequence
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import uk.nhs.nhsx.core.events.RecordingEvents
import uk.nhs.nhsx.isolationpayment.model.IsolationRequest
import uk.nhs.nhsx.isolationpayment.model.IsolationResponse
import uk.nhs.nhsx.testhelper.ContextBuilder
import uk.nhs.nhsx.domain.IpcTokenId

class IsolationPaymentConsumeHandlerTest {

    private val ipcToken = IpcTokenId.of("1".repeat(64))
    private val state = "state"

    private val service = mockk<IsolationPaymentGatewayService>()
    private val handler = IsolationPaymentConsumeHandler(service, RecordingEvents())

    @Test
    fun `returns isolation payment response with given token`() {
        every { service.consumeIsolationToken(any()) } returns IsolationResponse(ipcToken, state)

        val response = handler.handler()(IsolationRequest(ipcToken), ContextBuilder.aContext())

        assertThat(response.contractVersion).isEqualTo(1)
        assertThat(response.ipcToken).isEqualTo(ipcToken)
        assertThat(response.state).isEqualTo(state)

        verifySequence {
            service.consumeIsolationToken(ipcToken)
        }
    }
}
