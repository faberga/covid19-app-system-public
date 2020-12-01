package uk.nhs.nhsx.virology

import org.assertj.core.api.Assertions.assertThatCode
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import uk.nhs.nhsx.core.exceptions.ApiResponseException
import uk.nhs.nhsx.virology.result.VirologyResultValidator.validateTestResult

class VirologyResultValidatorTest {

    private val validTestDate = "2020-04-23T00:00:00Z"

    @Test
    fun `valid positive test result and date`() {
        assertThatCode { validateTestResult("POSITIVE", validTestDate) }
            .doesNotThrowAnyException()
    }

    @Test
    fun `valid negative test result and date`() {
        assertThatCode { validateTestResult("NEGATIVE", validTestDate) }
            .doesNotThrowAnyException()
    }

    @Test
    fun `valid void test result and date`() {
        assertThatCode { validateTestResult("NEGATIVE", validTestDate) }
            .doesNotThrowAnyException()
    }

    @Test
    fun `test result with invalid date throws exception`() {
        assertThatThrownBy { validateTestResult("NEGATIVE", "2020-0F-23T09:11:32Z") }
            .isInstanceOf(ApiResponseException::class.java)
            .hasMessage("validation error: Invalid date format")
    }

    @Test
    fun `test result with invalid test result throws exception`() {
        assertThatThrownBy { validateTestResult("INCORRECT", "2020-0F-23T09:11:32Z") }
            .isInstanceOf(ApiResponseException::class.java)
            .hasMessage("validation error: Invalid test result value")
    }

    @Test
    fun `date with invalid time throws exception`() {
        assertThatThrownBy { validateTestResult("POSITIVE", "2020-04-23T11:09:53Z") }
            .isInstanceOf(ApiResponseException::class.java)
            .hasMessage("validation error: Invalid date, time must be set to 00:00:00")
    }

    @Test
    fun `invalid date throws exception`() {
        assertThatThrownBy { validateTestResult("POSITIVE", "2020-04-23T1F:09:53Z") }
            .isInstanceOf(ApiResponseException::class.java)
            .hasMessage("validation error: Invalid date format")
    }

    @Test
    fun `invalid date format with suffix throws exception`() {
        assertThatThrownBy { validateTestResult("POSITIVE", "2020-09-29T00:00:00+00:00") }
            .isInstanceOf(ApiResponseException::class.java)
            .hasMessage("validation error: Invalid date format")
    }

    @Test
    fun `random date format with suffix throws exception`() {
        assertThatThrownBy { validateTestResult("POSITIVE", "fsd34af") }
            .isInstanceOf(ApiResponseException::class.java)
            .hasMessage("validation error: Invalid date format")
    }
}