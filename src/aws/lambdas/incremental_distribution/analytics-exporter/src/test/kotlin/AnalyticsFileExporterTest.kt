package uk.nhs.nhsx.analyticsexporter

import com.amazonaws.services.lambda.runtime.events.SQSEvent
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.S3Object
import com.amazonaws.services.s3.model.S3ObjectInputStream
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import uk.nhs.nhsx.core.aws.s3.AwsS3
import uk.nhs.nhsx.core.events.RecordingEvents
import java.util.*

class AnalyticsFileExporterTest {
    @Test
    fun `does not export event that is not created by firehose to aae`() {
        val s3 = mockk<AwsS3>()
        val uploader = mockk<ExportDestinationUploader>()
        val config = mockk<AnalyticsFileExporterConfig>()
        every { config.s3DisallowedPrefixList } returns "disallowed-prefix"
        val events = RecordingEvents()
        val exporter = AnalyticsFileExporter(events, s3, uploader, config)
        val sqsEvent = SQSEvent().apply {
            records = listOf(
                SQSEvent.SQSMessage().apply {
                    body = """{ "bucketName": "TEST_BUCKET", "key": "Poster/TEST.csv" }"""
                }
            )
        }

        assertThat(exporter.export(sqsEvent)).isInstanceOf(S3ParquetFileNotCreatedByFirehouse::class.java)
        verify(exactly = 0) {
            uploader.uploadFile(any(), any(), any())
        }
    }

    @Test
    fun `does export event that is created by firehose to aae`() {
        val s3 = mockk<AwsS3>()
        val fakeS3Object = S3Object().apply {
            key = "fake-Key"
            objectContent = S3ObjectInputStream("dummyContent".byteInputStream(), null);
            objectMetadata = ObjectMetadata().apply { contentType = "dummyContentType" }
        }
        every { s3.getObject(any()) } returns Optional.of(fakeS3Object)
        val uploader = mockk<ExportDestinationUploader>()
        every { uploader.uploadFile(any(), any(), any()) } returns Unit
        val config = mockk<AnalyticsFileExporterConfig>()
        every { config.s3DisallowedPrefixList } returns ""
        val events = RecordingEvents()
        val exporter = AnalyticsFileExporter(events, s3, uploader, config)
        val sqsEvent = SQSEvent().apply {
            records = listOf(
                SQSEvent.SQSMessage().apply {
                    body = """{ "bucketName": "TEST_BUCKET", "key": "2021/05/18/04/te-staging-analytics-6-2021-05-12-12-30-21-c81ba8a5-d040-4398-a173-ff4569cdd24a.parquet" }"""
                }
            )
        }

        assertThat(exporter.export(sqsEvent)).isInstanceOf(DataUploadedToS3::class.java)
        verify(exactly = 1) {
            uploader.uploadFile(any(), any(), any())
        }
    }

    @Test
    fun `does export event that is created by firehose after migration to aae`() {
        val s3 = mockk<AwsS3>()
        val fakeS3Object = S3Object().apply {
            key = "fake-Key"
            objectContent = S3ObjectInputStream("dummyContent".byteInputStream(), null);
            objectMetadata = ObjectMetadata().apply { contentType = "dummyContentType" }
        }
        every { s3.getObject(any()) } returns Optional.of(fakeS3Object)
        val uploader = mockk<ExportDestinationUploader>()
        every { uploader.uploadFile(any(), any(), any()) } returns Unit
        val config = mockk<AnalyticsFileExporterConfig>()
        every { config.s3DisallowedPrefixList } returns ""
        val events = RecordingEvents()
        val exporter = AnalyticsFileExporter(events, s3, uploader, config)
        val sqsEvent = SQSEvent().apply {
            records = listOf(
                SQSEvent.SQSMessage().apply {
                    body = """{ "bucketName": "TEST_BUCKET", "key": "submitteddatehour=2020-12-26-15/te-staging-analytics-6-2021-05-12-12-30-21-c81ba8a5-d040-4398-a173-ff4569cdd24a.parquet" }"""
                }
            )
        }

        assertThat(exporter.export(sqsEvent)).isInstanceOf(DataUploadedToS3::class.java)
        verify(exactly = 1) {
            uploader.uploadFile(any(), any(), any())
        }
    }

    @Test
    fun `does not export event that is created by glue to aae`() {
        val s3 = mockk<AwsS3>()
        val fakeS3Object = S3Object().apply {
            key = "fake-Key"
            objectContent = S3ObjectInputStream("dummyContent".byteInputStream(), null);
            objectMetadata = ObjectMetadata().apply { contentType = "dummyContentType" }
        }
        every { s3.getObject(any()) } returns Optional.of(fakeS3Object)
        val uploader = mockk<ExportDestinationUploader>()
        every { uploader.uploadFile(any(), any(), any()) } returns Unit
        val config = mockk<AnalyticsFileExporterConfig>()
        every { config.s3DisallowedPrefixList } returns ""
        val events = RecordingEvents()
        val exporter = AnalyticsFileExporter(events, s3, uploader, config)
        val sqsEvent = SQSEvent().apply {
            records = listOf(
                SQSEvent.SQSMessage().apply {
                    body = """{ "bucketName": "TEST_BUCKET", "key": "submitteddatehour=2020-12-26-15/part-00000-1be9b1c9-5d88-449e-a1ae-68bc697ecb68.c000.snappy.parquet" }"""
                }
            )
        }

        assertThat(exporter.export(sqsEvent)).isInstanceOf(S3ParquetFileNotCreatedByFirehouse::class.java)
        verify(exactly = 0) {
            uploader.uploadFile(any(), any(), any())
        }
    }

    @Test
    fun `does export event that is created by firehose to aae for load-test`() {
        val s3 = mockk<AwsS3>()
        val fakeS3Object = S3Object().apply {
            key = "fake-Key"
            objectContent = S3ObjectInputStream("dummyContent".byteInputStream(), null);
            objectMetadata = ObjectMetadata().apply { contentType = "dummyContentType" }
        }
        every { s3.getObject(any()) } returns Optional.of(fakeS3Object)
        val uploader = mockk<ExportDestinationUploader>()
        every { uploader.uploadFile(any(), any(), any()) } returns Unit
        val config = mockk<AnalyticsFileExporterConfig>()
        every { config.s3DisallowedPrefixList } returns ""
        val events = RecordingEvents()
        val exporter = AnalyticsFileExporter(events, s3, uploader, config)
        val sqsEvent = SQSEvent().apply {
            records = listOf(
                SQSEvent.SQSMessage().apply {
                    body = """{ "bucketName": "TEST_BUCKET", "key": "2021/05/18/04/te-load-test-analytics-6-2021-05-12-12-30-21-c81ba8a5-d040-4398-a173-ff4569cdd24a.parquet" }"""
                }
            )
        }

        assertThat(exporter.export(sqsEvent)).isInstanceOf(DataUploadedToS3::class.java)
        verify(exactly = 1) {
            uploader.uploadFile(any(), any(), any())
        }
    }

    @Test
    fun `does not export event where key starts with format conversion failed prefix`() {
        val s3 = mockk<AwsS3>()
        val uploader = mockk<ExportDestinationUploader>()
        val config = mockk<AnalyticsFileExporterConfig>()
        val events = RecordingEvents()
        val exporter = AnalyticsFileExporter(events, s3, uploader, config)
        val sqsEvent = SQSEvent().apply {
            records = listOf(
                SQSEvent.SQSMessage().apply {
                    body = """{ "bucketName": "TEST_BUCKET", "key": "format-conversion-failed/TEST.csv" }"""
                }
            )
        }

        assertThat(exporter.export(sqsEvent)).isInstanceOf(S3ToParquetObjectConversionFailure::class.java)
        verify(exactly = 0) {
            uploader.uploadFile(any(), any(), any())
        }
    }

    @Test
    fun `does not export event that has disallowed prefix`() {
        val s3 = mockk<AwsS3>()
        val uploader = mockk<ExportDestinationUploader>()
        val config = mockk<AnalyticsFileExporterConfig>()
        every { config.s3DisallowedPrefixList } returns "disallowed-prefix"
        val events = RecordingEvents()
        val exporter = AnalyticsFileExporter(events, s3, uploader, config)
        val sqsEvent = SQSEvent().apply {
            records = listOf(
                SQSEvent.SQSMessage().apply {
                    body = """{ "bucketName": "TEST_BUCKET", "key": "disallowed-prefix/te-staging-analytics-6-2021-05-12-12-30-21-c81ba8a5-d040-4398-a173-ff4569cdd24a.parquet" }"""
                }
            )
        }

        assertThat(exporter.export(sqsEvent)).isInstanceOf(S3ObjectStartsWithDisallowedPrefix::class.java)
        verify(exactly = 0) {
            uploader.uploadFile(any(), any(), any())
        }
    }
}