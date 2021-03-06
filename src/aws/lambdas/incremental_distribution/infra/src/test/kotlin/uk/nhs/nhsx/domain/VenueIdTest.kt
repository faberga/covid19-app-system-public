package uk.nhs.nhsx.domain

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test

class VenueIdTest {

    @Test
    fun `creates a valid VenueId`() {
        assertThat(VenueId.of("CD3")).isNotNull
    }

    @Test
    fun `throws validation exception if longer than 12 characters`() {
        assertThatThrownBy { VenueId.of("9".repeat(20)) }
            .hasMessageContaining("validation error: VenueId must match [CDEFHJKMPRTVWXY2345689]{1,12}")
    }

    @Test
    fun `throws validation exception if contains invalid characters`() {
        assertThatThrownBy { VenueId.of("L") }
            .hasMessageContaining("validation error: VenueId must match [CDEFHJKMPRTVWXY2345689]{1,12}")
    }
}
