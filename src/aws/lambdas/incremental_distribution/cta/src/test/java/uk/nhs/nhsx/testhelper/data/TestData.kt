package uk.nhs.nhsx.testhelper.data

import com.amazonaws.services.logs.model.ResultField
import uk.nhs.nhsx.diagnosiskeyssubmission.model.StoredTemporaryExposureKey
import uk.nhs.nhsx.diagnosiskeyssubmission.model.StoredTemporaryExposureKeyPayload
import uk.nhs.nhsx.domain.TestEndDate
import uk.nhs.nhsx.domain.TestKit
import uk.nhs.nhsx.domain.TestKit.LAB_RESULT
import uk.nhs.nhsx.domain.TestKit.RAPID_RESULT
import uk.nhs.nhsx.domain.TestResult.*
import uk.nhs.nhsx.domain.TestResultPollingToken
import uk.nhs.nhsx.virology.persistence.TestState
import uk.nhs.nhsx.virology.persistence.TestState.AvailableTestResult

object TestData {

    const val STORED_KEYS_PAYLOAD = """{"temporaryExposureKeys":[{"key":"W2zb3BeMWt6Xr2u0ABG32Q==","rollingStartNumber":12345,"rollingPeriod":144,"transmissionRisk":7},{"key":"kzQt9Lf3xjtAlMtm7jkSqw==","rollingStartNumber":12499,"rollingPeriod":144,"transmissionRisk":7}]}"""
    const val STORED_KEYS_PAYLOAD_SUBMISSION = """{"temporaryExposureKeys":[{"key":"W2zb3BeMWt6Xr2u0ABG32Q==","rollingStartNumber":2666736,"rollingPeriod":144,"transmissionRisk":7},{"key":"kzQt9Lf3xjtAlMtm7jkSqw==","rollingStartNumber":2664864,"rollingPeriod":144,"transmissionRisk":7}]}"""
    const val STORED_KEYS_PAYLOAD_DAYS_SINCE_ONSET = """{"temporaryExposureKeys":[{"key":"W2zb3BeMWt6Xr2u0ABG32Q==","rollingStartNumber":12345,"rollingPeriod":144,"transmissionRisk":7,"daysSinceOnsetOfSymptoms":1},{"key":"kzQt9Lf3xjtAlMtm7jkSqw==","rollingStartNumber":12499,"rollingPeriod":144,"transmissionRisk":7,"daysSinceOnsetOfSymptoms":4}]}"""
    const val STORED_KEYS_PAYLOAD_DAYS_SINCE_ONSET_SUBMISSION = """{"temporaryExposureKeys":[{"key":"W2zb3BeMWt6Xr2u0ABG32Q==","rollingStartNumber":2666736,"rollingPeriod":144,"transmissionRisk":7,"daysSinceOnsetOfSymptoms":1},{"key":"kzQt9Lf3xjtAlMtm7jkSqw==","rollingStartNumber":2664864,"rollingPeriod":144,"transmissionRisk":7,"daysSinceOnsetOfSymptoms":4}]}"""
    const val STORED_KEYS_PAYLOAD_WITH_RISK_LEVEL = """{"temporaryExposureKeys":[{"key":"W2zb3BeMWt6Xr2u0ABG32Q==","rollingStartNumber":2666736,"rollingPeriod":144,"transmissionRisk":5},{"key":"kzQt9Lf3xjtAlMtm7jkSqw==","rollingStartNumber":2664864,"rollingPeriod":144,"transmissionRisk":4}]}"""
    const val STORED_KEYS_PAYLOAD_ONE_KEY = """{"temporaryExposureKeys":[{"key":"W2zb3BeMWt6Xr2u0ABG32Q==","rollingStartNumber":2666736,"rollingPeriod":144,"transmissionRisk":7}]}"""
    const val STORED_FEDERATED_KEYS_PAYLOAD_NI = """{"temporaryExposureKeys":[{"key":"W2zb3BeMWt6Xr2u0ABG32Q==","rollingStartNumber":2666736,"rollingPeriod":144,"transmissionRisk":3,"daysSinceOnsetOfSymptoms":0},{"key":"B3xb3BeMWt6Xr2u0ABG45F==","rollingStartNumber":2666874,"rollingPeriod":144,"transmissionRisk":6,"daysSinceOnsetOfSymptoms":0}]}"""
    const val STORED_FEDERATED_KEYS_PAYLOAD_IE = """{"temporaryExposureKeys":[{"key":"kzQt9Lf3xjtAlMtm7jkSqw==","rollingStartNumber":2666868,"rollingPeriod":144,"transmissionRisk":4,"daysSinceOnsetOfSymptoms":0}]}"""

    val STORED_KEYS_PAYLOAD_DESERIALIZED = StoredTemporaryExposureKeyPayload(
        listOf(
            StoredTemporaryExposureKey("W2zb3BeMWt6Xr2u0ABG32Q==", 12345, 144, 7),
            StoredTemporaryExposureKey("kzQt9Lf3xjtAlMtm7jkSqw==", 12499, 144, 7)
        )
    )

    val STORED_KEYS_PAYLOAD_DESERIALIZED_DAYS_SINCE_ONSET = StoredTemporaryExposureKeyPayload(
        listOf(
            StoredTemporaryExposureKey("W2zb3BeMWt6Xr2u0ABG32Q==", 12345, 144, 7, 1),
            StoredTemporaryExposureKey("kzQt9Lf3xjtAlMtm7jkSqw==", 12499, 144, 7, 4)
        )
    )

    const val RISKY_VENUES_UPLOAD_PAYLOAD = """# venue_id, start_time, end_time, message_type, optional_parameter
                                                "CD2", "2019-07-04T13:33:03Z", "2019-07-04T15:56:00Z", "M1", ""
                                                "CD3", "2019-07-06T19:33:03Z", "2019-07-06T21:01:07Z", "M1", ""
                                                "CD4", "2019-07-08T20:05:52Z", "2019-07-08T22:35:56Z", "M1", """""

    const val STORED_RISKY_VENUES_UPLOAD_PAYLOAD = """{"venues":[{"id":"CD2","riskyWindow":{"from":"2019-07-04T13:33:03Z","until":"2019-07-04T15:56:00Z"},"messageType":"M1"},{"id":"CD3","riskyWindow":{"from":"2019-07-06T19:33:03Z","until":"2019-07-06T21:01:07Z"},"messageType":"M1"},{"id":"CD4","riskyWindow":{"from":"2019-07-08T20:05:52Z","until":"2019-07-08T22:35:56Z"},"messageType":"M1"}]}"""
    const val STORED_ANALYTICS_PAYLOAD_IOS_NEW_METRICS = """{"startDate":"2020-07-27T23:00:00Z","endDate":"2020-07-28T22:59:00Z","postalDistrict":"AB10","deviceModel":"iPhone11,2","operatingSystemVersion":"iPhone OS 13.5.1 (17F80)","latestApplicationVersion":"3.0","localAuthority":null,"cumulativeDownloadBytes":140000000,"cumulativeUploadBytes":140000000,"cumulativeCellularDownloadBytes":80000000,"cumulativeCellularUploadBytes":70000000,"cumulativeWifiDownloadBytes":60000000,"cumulativeWifiUploadBytes":50000000,"checkedIn":1,"canceledCheckIn":1,"receivedVoidTestResult":1,"isIsolatingBackgroundTick":1,"hasHadRiskyContactBackgroundTick":1,"receivedPositiveTestResult":1,"receivedNegativeTestResult":1,"hasSelfDiagnosedPositiveBackgroundTick":1,"completedQuestionnaireAndStartedIsolation":1,"encounterDetectionPausedBackgroundTick":1,"completedQuestionnaireButDidNotStartIsolation":1,"totalBackgroundTasks":1,"runningNormallyBackgroundTick":1,"completedOnboarding":1,"includesMultipleApplicationVersions":false,"receivedVoidTestResultEnteredManually":1,"receivedPositiveTestResultEnteredManually":1,"receivedNegativeTestResultEnteredManually":0,"receivedVoidTestResultViaPolling":0,"receivedPositiveTestResultViaPolling":0,"receivedNegativeTestResultViaPolling":1,"hasSelfDiagnosedBackgroundTick":4,"hasTestedPositiveBackgroundTick":5,"isIsolatingForSelfDiagnosedBackgroundTick":6,"isIsolatingForTestedPositiveBackgroundTick":3,"isIsolatingForHadRiskyContactBackgroundTick":13,"receivedRiskyContactNotification":1,"startedIsolation":1,"receivedPositiveTestResultWhenIsolatingDueToRiskyContact":1,"receivedActiveIpcToken":1,"haveActiveIpcTokenBackgroundTick":1,"selectedIsolationPaymentsButton":1,"launchedIsolationPaymentsApplication":1,"receivedPositiveLFDTestResultViaPolling":1,"receivedNegativeLFDTestResultViaPolling":1,"receivedVoidLFDTestResultViaPolling":1,"receivedPositiveLFDTestResultEnteredManually":1,"receivedNegativeLFDTestResultEnteredManually":1,"receivedVoidLFDTestResultEnteredManually":1,"hasTestedLFDPositiveBackgroundTick":1,"isIsolatingForTestedLFDPositiveBackgroundTick":1,"totalExposureWindowsNotConsideredRisky":1,"totalExposureWindowsConsideredRisky":1,"acknowledgedStartOfIsolationDueToRiskyContact":1,"hasRiskyContactNotificationsEnabledBackgroundTick":1,"totalRiskyContactReminderNotifications":1,"receivedUnconfirmedPositiveTestResult":1,"isIsolatingForUnconfirmedTestBackgroundTick":1,"launchedTestOrdering":1,"didHaveSymptomsBeforeReceivedTestResult":1,"didRememberOnsetSymptomsDateBeforeReceivedTestResult":1,"didAskForSymptomsOnPositiveTestEntry":1,"declaredNegativeResultFromDCT":1,"receivedPositiveSelfRapidTestResultViaPolling":1,"receivedNegativeSelfRapidTestResultViaPolling":1,"receivedVoidSelfRapidTestResultViaPolling":1,"receivedPositiveSelfRapidTestResultEnteredManually":1,"receivedNegativeSelfRapidTestResultEnteredManually":1,"receivedVoidSelfRapidTestResultEnteredManually":1,"isIsolatingForTestedSelfRapidPositiveBackgroundTick":1,"hasTestedSelfRapidPositiveBackgroundTick":1,"receivedRiskyVenueM1Warning":1,"receivedRiskyVenueM2Warning":1,"hasReceivedRiskyVenueM2WarningBackgroundTick":1,"totalAlarmManagerBackgroundTasks":1,"missingPacketsLast7Days":1,"consentedToShareVenueHistory":1,"askedToShareVenueHistory":1,"askedToShareExposureKeysInTheInitialFlow":1,"consentedToShareExposureKeysInTheInitialFlow":1,"totalShareExposureKeysReminderNotifications":1,"consentedToShareExposureKeysInReminderScreen":1,"successfullySharedExposureKeys":1,"didSendLocalInfoNotification":1,"didAccessLocalInfoScreenViaNotification":1,"didAccessLocalInfoScreenViaBanner":1,"isDisplayingLocalInfoBackgroundTick":1,"didAccessRiskyVenueM2Notification":1,"selectedTakeTestM2Journey":1,"selectedTakeTestLaterM2Journey":1,"selectedHasSymptomsM2Journey":1,"selectedHasNoSymptomsM2Journey":1,"selectedLFDTestOrderingM2Journey":1,"selectedHasLFDTestM2Journey":1}"""
    const val STORED_ANALYTICS_PAYLOAD_IOS = """{"startDate":"2020-07-27T23:00:00Z","endDate":"2020-07-28T22:59:00Z","postalDistrict":"AB10","deviceModel":"iPhone11,2","operatingSystemVersion":"iPhone OS 13.5.1 (17F80)","latestApplicationVersion":"3.0","localAuthority":null,"cumulativeDownloadBytes":140000000,"cumulativeUploadBytes":140000000,"cumulativeCellularDownloadBytes":80000000,"cumulativeCellularUploadBytes":70000000,"cumulativeWifiDownloadBytes":60000000,"cumulativeWifiUploadBytes":50000000,"checkedIn":1,"canceledCheckIn":1,"receivedVoidTestResult":1,"isIsolatingBackgroundTick":1,"hasHadRiskyContactBackgroundTick":1,"receivedPositiveTestResult":1,"receivedNegativeTestResult":1,"hasSelfDiagnosedPositiveBackgroundTick":1,"completedQuestionnaireAndStartedIsolation":1,"encounterDetectionPausedBackgroundTick":1,"completedQuestionnaireButDidNotStartIsolation":1,"totalBackgroundTasks":1,"runningNormallyBackgroundTick":1,"completedOnboarding":1,"includesMultipleApplicationVersions":false,"receivedVoidTestResultEnteredManually":null,"receivedPositiveTestResultEnteredManually":null,"receivedNegativeTestResultEnteredManually":null,"receivedVoidTestResultViaPolling":null,"receivedPositiveTestResultViaPolling":null,"receivedNegativeTestResultViaPolling":null,"hasSelfDiagnosedBackgroundTick":null,"hasTestedPositiveBackgroundTick":null,"isIsolatingForSelfDiagnosedBackgroundTick":null,"isIsolatingForTestedPositiveBackgroundTick":null,"isIsolatingForHadRiskyContactBackgroundTick":null,"receivedRiskyContactNotification":null,"startedIsolation":null,"receivedPositiveTestResultWhenIsolatingDueToRiskyContact":null,"receivedActiveIpcToken":null,"haveActiveIpcTokenBackgroundTick":null,"selectedIsolationPaymentsButton":null,"launchedIsolationPaymentsApplication":null,"receivedPositiveLFDTestResultViaPolling":null,"receivedNegativeLFDTestResultViaPolling":null,"receivedVoidLFDTestResultViaPolling":null,"receivedPositiveLFDTestResultEnteredManually":null,"receivedNegativeLFDTestResultEnteredManually":null,"receivedVoidLFDTestResultEnteredManually":null,"hasTestedLFDPositiveBackgroundTick":null,"isIsolatingForTestedLFDPositiveBackgroundTick":null,"totalExposureWindowsNotConsideredRisky":null,"totalExposureWindowsConsideredRisky":null,"acknowledgedStartOfIsolationDueToRiskyContact":null,"hasRiskyContactNotificationsEnabledBackgroundTick":null,"totalRiskyContactReminderNotifications":null,"receivedUnconfirmedPositiveTestResult":null,"isIsolatingForUnconfirmedTestBackgroundTick":null,"launchedTestOrdering":null,"didHaveSymptomsBeforeReceivedTestResult":null,"didRememberOnsetSymptomsDateBeforeReceivedTestResult":null,"didAskForSymptomsOnPositiveTestEntry":null,"declaredNegativeResultFromDCT":null,"receivedPositiveSelfRapidTestResultViaPolling":null,"receivedNegativeSelfRapidTestResultViaPolling":null,"receivedVoidSelfRapidTestResultViaPolling":null,"receivedPositiveSelfRapidTestResultEnteredManually":null,"receivedNegativeSelfRapidTestResultEnteredManually":null,"receivedVoidSelfRapidTestResultEnteredManually":null,"isIsolatingForTestedSelfRapidPositiveBackgroundTick":null,"hasTestedSelfRapidPositiveBackgroundTick":null,"receivedRiskyVenueM1Warning":null,"receivedRiskyVenueM2Warning":null,"hasReceivedRiskyVenueM2WarningBackgroundTick":null,"totalAlarmManagerBackgroundTasks":null,"missingPacketsLast7Days":null,"consentedToShareVenueHistory":null,"askedToShareVenueHistory":null,"askedToShareExposureKeysInTheInitialFlow":null,"consentedToShareExposureKeysInTheInitialFlow":null,"totalShareExposureKeysReminderNotifications":null,"consentedToShareExposureKeysInReminderScreen":null,"successfullySharedExposureKeys":null,"didSendLocalInfoNotification":null,"didAccessLocalInfoScreenViaNotification":null,"didAccessLocalInfoScreenViaBanner":null,"isDisplayingLocalInfoBackgroundTick":null}"""
    const val STORED_ANALYTICS_PAYLOAD_IOS_WITH_LOCAL_AUTHORITY = """{"startDate":"2020-07-27T23:00:00Z","endDate":"2020-07-28T22:59:00Z","postalDistrict":"SY5_TF6","deviceModel":"iPhone11,2","operatingSystemVersion":"iPhone OS 13.5.1 (17F80)","latestApplicationVersion":"3.0","localAuthority":"E06000051","cumulativeDownloadBytes":140000000,"cumulativeUploadBytes":140000000,"cumulativeCellularDownloadBytes":80000000,"cumulativeCellularUploadBytes":70000000,"cumulativeWifiDownloadBytes":60000000,"cumulativeWifiUploadBytes":50000000,"checkedIn":1,"canceledCheckIn":1,"receivedVoidTestResult":1,"isIsolatingBackgroundTick":1,"hasHadRiskyContactBackgroundTick":1,"receivedPositiveTestResult":1,"receivedNegativeTestResult":1,"hasSelfDiagnosedPositiveBackgroundTick":1,"completedQuestionnaireAndStartedIsolation":1,"encounterDetectionPausedBackgroundTick":1,"completedQuestionnaireButDidNotStartIsolation":1,"totalBackgroundTasks":1,"runningNormallyBackgroundTick":1,"completedOnboarding":1,"includesMultipleApplicationVersions":false,"receivedVoidTestResultEnteredManually":null,"receivedPositiveTestResultEnteredManually":null,"receivedNegativeTestResultEnteredManually":null,"receivedVoidTestResultViaPolling":null,"receivedPositiveTestResultViaPolling":null,"receivedNegativeTestResultViaPolling":null,"hasSelfDiagnosedBackgroundTick":null,"hasTestedPositiveBackgroundTick":null,"isIsolatingForSelfDiagnosedBackgroundTick":null,"isIsolatingForTestedPositiveBackgroundTick":null,"isIsolatingForHadRiskyContactBackgroundTick":null,"receivedRiskyContactNotification":null,"startedIsolation":null,"receivedPositiveTestResultWhenIsolatingDueToRiskyContact":null,"receivedActiveIpcToken":null,"haveActiveIpcTokenBackgroundTick":null,"selectedIsolationPaymentsButton":null,"launchedIsolationPaymentsApplication":null,"receivedPositiveLFDTestResultViaPolling":null,"receivedNegativeLFDTestResultViaPolling":null,"receivedVoidLFDTestResultViaPolling":null,"receivedPositiveLFDTestResultEnteredManually":null,"receivedNegativeLFDTestResultEnteredManually":null,"receivedVoidLFDTestResultEnteredManually":null,"hasTestedLFDPositiveBackgroundTick":null,"isIsolatingForTestedLFDPositiveBackgroundTick":null,"totalExposureWindowsNotConsideredRisky":null,"totalExposureWindowsConsideredRisky":null,"acknowledgedStartOfIsolationDueToRiskyContact":null,"hasRiskyContactNotificationsEnabledBackgroundTick":null,"totalRiskyContactReminderNotifications":null,"receivedUnconfirmedPositiveTestResult":null,"isIsolatingForUnconfirmedTestBackgroundTick":null,"launchedTestOrdering":null,"didHaveSymptomsBeforeReceivedTestResult":null,"didRememberOnsetSymptomsDateBeforeReceivedTestResult":null,"didAskForSymptomsOnPositiveTestEntry":null,"declaredNegativeResultFromDCT":null,"receivedPositiveSelfRapidTestResultViaPolling":null,"receivedNegativeSelfRapidTestResultViaPolling":null,"receivedVoidSelfRapidTestResultViaPolling":null,"receivedPositiveSelfRapidTestResultEnteredManually":null,"receivedNegativeSelfRapidTestResultEnteredManually":null,"receivedVoidSelfRapidTestResultEnteredManually":null,"isIsolatingForTestedSelfRapidPositiveBackgroundTick":null,"hasTestedSelfRapidPositiveBackgroundTick":null,"receivedRiskyVenueM1Warning":null,"receivedRiskyVenueM2Warning":null,"hasReceivedRiskyVenueM2WarningBackgroundTick":null,"totalAlarmManagerBackgroundTasks":null,"missingPacketsLast7Days":null,"consentedToShareVenueHistory":null,"askedToShareVenueHistory":null,"askedToShareExposureKeysInTheInitialFlow":null,"consentedToShareExposureKeysInTheInitialFlow":null,"totalShareExposureKeysReminderNotifications":null,"consentedToShareExposureKeysInReminderScreen":null,"successfullySharedExposureKeys":null,"didSendLocalInfoNotification":null,"didAccessLocalInfoScreenViaNotification":null,"didAccessLocalInfoScreenViaBanner":null,"isDisplayingLocalInfoBackgroundTick":null}"""
    const val STORED_ANALYTICS_MERGED_POSTCODE_PAYLOAD_IOS = """{"startDate":"2020-07-27T23:00:00Z","endDate":"2020-07-28T22:59:00Z","postalDistrict":"AB13_AB14","deviceModel":"iPhone11,2","operatingSystemVersion":"iPhone OS 13.5.1 (17F80)","latestApplicationVersion":"3.0","localAuthority":null,"cumulativeDownloadBytes":140000000,"cumulativeUploadBytes":140000000,"cumulativeCellularDownloadBytes":80000000,"cumulativeCellularUploadBytes":70000000,"cumulativeWifiDownloadBytes":60000000,"cumulativeWifiUploadBytes":50000000,"checkedIn":1,"canceledCheckIn":1,"receivedVoidTestResult":1,"isIsolatingBackgroundTick":1,"hasHadRiskyContactBackgroundTick":1,"receivedPositiveTestResult":1,"receivedNegativeTestResult":1,"hasSelfDiagnosedPositiveBackgroundTick":1,"completedQuestionnaireAndStartedIsolation":1,"encounterDetectionPausedBackgroundTick":1,"completedQuestionnaireButDidNotStartIsolation":1,"totalBackgroundTasks":1,"runningNormallyBackgroundTick":1,"completedOnboarding":1,"includesMultipleApplicationVersions":false,"receivedVoidTestResultEnteredManually":null,"receivedPositiveTestResultEnteredManually":null,"receivedNegativeTestResultEnteredManually":null,"receivedVoidTestResultViaPolling":null,"receivedPositiveTestResultViaPolling":null,"receivedNegativeTestResultViaPolling":null,"hasSelfDiagnosedBackgroundTick":null,"hasTestedPositiveBackgroundTick":null,"isIsolatingForSelfDiagnosedBackgroundTick":null,"isIsolatingForTestedPositiveBackgroundTick":null,"isIsolatingForHadRiskyContactBackgroundTick":null,"receivedRiskyContactNotification":null,"startedIsolation":null,"receivedPositiveTestResultWhenIsolatingDueToRiskyContact":null,"receivedActiveIpcToken":null,"haveActiveIpcTokenBackgroundTick":null,"selectedIsolationPaymentsButton":null,"launchedIsolationPaymentsApplication":null,"receivedPositiveLFDTestResultViaPolling":null,"receivedNegativeLFDTestResultViaPolling":null,"receivedVoidLFDTestResultViaPolling":null,"receivedPositiveLFDTestResultEnteredManually":null,"receivedNegativeLFDTestResultEnteredManually":null,"receivedVoidLFDTestResultEnteredManually":null,"hasTestedLFDPositiveBackgroundTick":null,"isIsolatingForTestedLFDPositiveBackgroundTick":null,"totalExposureWindowsNotConsideredRisky":null,"totalExposureWindowsConsideredRisky":null,"acknowledgedStartOfIsolationDueToRiskyContact":null,"hasRiskyContactNotificationsEnabledBackgroundTick":null,"totalRiskyContactReminderNotifications":null,"receivedUnconfirmedPositiveTestResult":null,"isIsolatingForUnconfirmedTestBackgroundTick":null,"launchedTestOrdering":null,"didHaveSymptomsBeforeReceivedTestResult":null,"didRememberOnsetSymptomsDateBeforeReceivedTestResult":null,"didAskForSymptomsOnPositiveTestEntry":null,"declaredNegativeResultFromDCT":null,"receivedPositiveSelfRapidTestResultViaPolling":null,"receivedNegativeSelfRapidTestResultViaPolling":null,"receivedVoidSelfRapidTestResultViaPolling":null,"receivedPositiveSelfRapidTestResultEnteredManually":null,"receivedNegativeSelfRapidTestResultEnteredManually":null,"receivedVoidSelfRapidTestResultEnteredManually":null,"isIsolatingForTestedSelfRapidPositiveBackgroundTick":null,"hasTestedSelfRapidPositiveBackgroundTick":null,"receivedRiskyVenueM1Warning":null,"receivedRiskyVenueM2Warning":null,"hasReceivedRiskyVenueM2WarningBackgroundTick":null,"totalAlarmManagerBackgroundTasks":null,"missingPacketsLast7Days":null,"consentedToShareVenueHistory":null,"askedToShareVenueHistory":null,"askedToShareExposureKeysInTheInitialFlow":null,"consentedToShareExposureKeysInTheInitialFlow":null,"totalShareExposureKeysReminderNotifications":null,"consentedToShareExposureKeysInReminderScreen":null,"successfullySharedExposureKeys":null,"didSendLocalInfoNotification":null,"didAccessLocalInfoScreenViaNotification":null,"didAccessLocalInfoScreenViaBanner":null,"isDisplayingLocalInfoBackgroundTick":null}"""
    const val STORED_ANALYTICS_INVALID_PAIR = """{"startDate":"2020-07-27T23:00:00Z","endDate":"2020-07-28T22:59:00Z","postalDistrict":"YO60_YO62","deviceModel":"iPhone11,2","operatingSystemVersion":"iPhone OS 13.5.1 (17F80)","latestApplicationVersion":"3.0","localAuthority":null,"cumulativeDownloadBytes":140000000,"cumulativeUploadBytes":140000000,"cumulativeCellularDownloadBytes":80000000,"cumulativeCellularUploadBytes":70000000,"cumulativeWifiDownloadBytes":60000000,"cumulativeWifiUploadBytes":50000000,"checkedIn":1,"canceledCheckIn":1,"receivedVoidTestResult":1,"isIsolatingBackgroundTick":1,"hasHadRiskyContactBackgroundTick":1,"receivedPositiveTestResult":1,"receivedNegativeTestResult":1,"hasSelfDiagnosedPositiveBackgroundTick":1,"completedQuestionnaireAndStartedIsolation":1,"encounterDetectionPausedBackgroundTick":1,"completedQuestionnaireButDidNotStartIsolation":1,"totalBackgroundTasks":1,"runningNormallyBackgroundTick":1,"completedOnboarding":1,"includesMultipleApplicationVersions":false,"receivedVoidTestResultEnteredManually":null,"receivedPositiveTestResultEnteredManually":null,"receivedNegativeTestResultEnteredManually":null,"receivedVoidTestResultViaPolling":null,"receivedPositiveTestResultViaPolling":null,"receivedNegativeTestResultViaPolling":null,"hasSelfDiagnosedBackgroundTick":null,"hasTestedPositiveBackgroundTick":null,"isIsolatingForSelfDiagnosedBackgroundTick":null,"isIsolatingForTestedPositiveBackgroundTick":null,"isIsolatingForHadRiskyContactBackgroundTick":null,"receivedRiskyContactNotification":null,"startedIsolation":null,"receivedPositiveTestResultWhenIsolatingDueToRiskyContact":null,"receivedActiveIpcToken":null,"haveActiveIpcTokenBackgroundTick":null,"selectedIsolationPaymentsButton":null,"launchedIsolationPaymentsApplication":null,"receivedPositiveLFDTestResultViaPolling":null,"receivedNegativeLFDTestResultViaPolling":null,"receivedVoidLFDTestResultViaPolling":null,"receivedPositiveLFDTestResultEnteredManually":null,"receivedNegativeLFDTestResultEnteredManually":null,"receivedVoidLFDTestResultEnteredManually":null,"hasTestedLFDPositiveBackgroundTick":null,"isIsolatingForTestedLFDPositiveBackgroundTick":null,"totalExposureWindowsNotConsideredRisky":null,"totalExposureWindowsConsideredRisky":null,"acknowledgedStartOfIsolationDueToRiskyContact":null,"hasRiskyContactNotificationsEnabledBackgroundTick":null,"totalRiskyContactReminderNotifications":null,"receivedUnconfirmedPositiveTestResult":null,"isIsolatingForUnconfirmedTestBackgroundTick":null,"launchedTestOrdering":null,"didHaveSymptomsBeforeReceivedTestResult":null,"didRememberOnsetSymptomsDateBeforeReceivedTestResult":null,"didAskForSymptomsOnPositiveTestEntry":null,"declaredNegativeResultFromDCT":null,"receivedPositiveSelfRapidTestResultViaPolling":null,"receivedNegativeSelfRapidTestResultViaPolling":null,"receivedVoidSelfRapidTestResultViaPolling":null,"receivedPositiveSelfRapidTestResultEnteredManually":null,"receivedNegativeSelfRapidTestResultEnteredManually":null,"receivedVoidSelfRapidTestResultEnteredManually":null,"isIsolatingForTestedSelfRapidPositiveBackgroundTick":null,"hasTestedSelfRapidPositiveBackgroundTick":null,"receivedRiskyVenueM1Warning":null,"receivedRiskyVenueM2Warning":null,"hasReceivedRiskyVenueM2WarningBackgroundTick":null,"totalAlarmManagerBackgroundTasks":null,"missingPacketsLast7Days":null,"consentedToShareVenueHistory":null,"askedToShareVenueHistory":null,"askedToShareExposureKeysInTheInitialFlow":null,"consentedToShareExposureKeysInTheInitialFlow":null,"totalShareExposureKeysReminderNotifications":null,"consentedToShareExposureKeysInReminderScreen":null,"successfullySharedExposureKeys":null,"didSendLocalInfoNotification":null,"didAccessLocalInfoScreenViaNotification":null,"didAccessLocalInfoScreenViaBanner":null,"isDisplayingLocalInfoBackgroundTick":null}"""
    const val STORED_ANALYTICS_EMPTY_PAIR = """{"startDate":"2020-07-27T23:00:00Z","endDate":"2020-07-28T22:59:00Z","postalDistrict":"NOT SET","deviceModel":"iPhone11,2","operatingSystemVersion":"iPhone OS 13.5.1 (17F80)","latestApplicationVersion":"3.0","localAuthority":null,"cumulativeDownloadBytes":140000000,"cumulativeUploadBytes":140000000,"cumulativeCellularDownloadBytes":80000000,"cumulativeCellularUploadBytes":70000000,"cumulativeWifiDownloadBytes":60000000,"cumulativeWifiUploadBytes":50000000,"checkedIn":1,"canceledCheckIn":1,"receivedVoidTestResult":1,"isIsolatingBackgroundTick":1,"hasHadRiskyContactBackgroundTick":1,"receivedPositiveTestResult":1,"receivedNegativeTestResult":1,"hasSelfDiagnosedPositiveBackgroundTick":1,"completedQuestionnaireAndStartedIsolation":1,"encounterDetectionPausedBackgroundTick":1,"completedQuestionnaireButDidNotStartIsolation":1,"totalBackgroundTasks":1,"runningNormallyBackgroundTick":1,"completedOnboarding":1,"includesMultipleApplicationVersions":false,"receivedVoidTestResultEnteredManually":null,"receivedPositiveTestResultEnteredManually":null,"receivedNegativeTestResultEnteredManually":null,"receivedVoidTestResultViaPolling":null,"receivedPositiveTestResultViaPolling":null,"receivedNegativeTestResultViaPolling":null,"hasSelfDiagnosedBackgroundTick":null,"hasTestedPositiveBackgroundTick":null,"isIsolatingForSelfDiagnosedBackgroundTick":null,"isIsolatingForTestedPositiveBackgroundTick":null,"isIsolatingForHadRiskyContactBackgroundTick":null,"receivedRiskyContactNotification":null,"startedIsolation":null,"receivedPositiveTestResultWhenIsolatingDueToRiskyContact":null,"receivedActiveIpcToken":null,"haveActiveIpcTokenBackgroundTick":null,"selectedIsolationPaymentsButton":null,"launchedIsolationPaymentsApplication":null,"receivedPositiveLFDTestResultViaPolling":null,"receivedNegativeLFDTestResultViaPolling":null,"receivedVoidLFDTestResultViaPolling":null,"receivedPositiveLFDTestResultEnteredManually":null,"receivedNegativeLFDTestResultEnteredManually":null,"receivedVoidLFDTestResultEnteredManually":null,"hasTestedLFDPositiveBackgroundTick":null,"isIsolatingForTestedLFDPositiveBackgroundTick":null,"totalExposureWindowsNotConsideredRisky":null,"totalExposureWindowsConsideredRisky":null,"acknowledgedStartOfIsolationDueToRiskyContact":null,"hasRiskyContactNotificationsEnabledBackgroundTick":null,"totalRiskyContactReminderNotifications":null,"receivedUnconfirmedPositiveTestResult":null,"isIsolatingForUnconfirmedTestBackgroundTick":null,"launchedTestOrdering":null,"didHaveSymptomsBeforeReceivedTestResult":null,"didRememberOnsetSymptomsDateBeforeReceivedTestResult":null,"didAskForSymptomsOnPositiveTestEntry":null,"declaredNegativeResultFromDCT":null,"receivedPositiveSelfRapidTestResultViaPolling":null,"receivedNegativeSelfRapidTestResultViaPolling":null,"receivedVoidSelfRapidTestResultViaPolling":null,"receivedPositiveSelfRapidTestResultEnteredManually":null,"receivedNegativeSelfRapidTestResultEnteredManually":null,"receivedVoidSelfRapidTestResultEnteredManually":null,"isIsolatingForTestedSelfRapidPositiveBackgroundTick":null,"hasTestedSelfRapidPositiveBackgroundTick":null,"receivedRiskyVenueM1Warning":null,"receivedRiskyVenueM2Warning":null,"hasReceivedRiskyVenueM2WarningBackgroundTick":null,"totalAlarmManagerBackgroundTasks":null,"missingPacketsLast7Days":null,"consentedToShareVenueHistory":null,"askedToShareVenueHistory":null,"askedToShareExposureKeysInTheInitialFlow":null,"consentedToShareExposureKeysInTheInitialFlow":null,"totalShareExposureKeysReminderNotifications":null,"consentedToShareExposureKeysInReminderScreen":null,"successfullySharedExposureKeys":null,"didSendLocalInfoNotification":null,"didAccessLocalInfoScreenViaNotification":null,"didAccessLocalInfoScreenViaBanner":null,"isDisplayingLocalInfoBackgroundTick":null}"""
    const val STORED_ANALYTICS_UNKNOWN_POSTCODE_PAYLOAD_IOS = """{"startDate":"2020-07-27T23:00:00Z","endDate":"2020-07-28T22:59:00Z","postalDistrict":"UNKNOWN","deviceModel":"iPhone11,2","operatingSystemVersion":"iPhone OS 13.5.1 (17F80)","latestApplicationVersion":"3.0","localAuthority":"UNKNOWN","cumulativeDownloadBytes":140000000,"cumulativeUploadBytes":140000000,"cumulativeCellularDownloadBytes":80000000,"cumulativeCellularUploadBytes":70000000,"cumulativeWifiDownloadBytes":60000000,"cumulativeWifiUploadBytes":50000000,"checkedIn":1,"canceledCheckIn":1,"receivedVoidTestResult":1,"isIsolatingBackgroundTick":1,"hasHadRiskyContactBackgroundTick":1,"receivedPositiveTestResult":1,"receivedNegativeTestResult":1,"hasSelfDiagnosedPositiveBackgroundTick":1,"completedQuestionnaireAndStartedIsolation":1,"encounterDetectionPausedBackgroundTick":1,"completedQuestionnaireButDidNotStartIsolation":1,"totalBackgroundTasks":1,"runningNormallyBackgroundTick":1,"completedOnboarding":1,"includesMultipleApplicationVersions":false,"receivedVoidTestResultEnteredManually":null,"receivedPositiveTestResultEnteredManually":null,"receivedNegativeTestResultEnteredManually":null,"receivedVoidTestResultViaPolling":null,"receivedPositiveTestResultViaPolling":null,"receivedNegativeTestResultViaPolling":null,"hasSelfDiagnosedBackgroundTick":null,"hasTestedPositiveBackgroundTick":null,"isIsolatingForSelfDiagnosedBackgroundTick":null,"isIsolatingForTestedPositiveBackgroundTick":null,"isIsolatingForHadRiskyContactBackgroundTick":null,"receivedRiskyContactNotification":null,"startedIsolation":null,"receivedPositiveTestResultWhenIsolatingDueToRiskyContact":null,"receivedActiveIpcToken":null,"haveActiveIpcTokenBackgroundTick":null,"selectedIsolationPaymentsButton":null,"launchedIsolationPaymentsApplication":null,"receivedPositiveLFDTestResultViaPolling":null,"receivedNegativeLFDTestResultViaPolling":null,"receivedVoidLFDTestResultViaPolling":null,"receivedPositiveLFDTestResultEnteredManually":null,"receivedNegativeLFDTestResultEnteredManually":null,"receivedVoidLFDTestResultEnteredManually":null,"hasTestedLFDPositiveBackgroundTick":null,"isIsolatingForTestedLFDPositiveBackgroundTick":null,"totalExposureWindowsNotConsideredRisky":null,"totalExposureWindowsConsideredRisky":null,"acknowledgedStartOfIsolationDueToRiskyContact":null,"hasRiskyContactNotificationsEnabledBackgroundTick":null,"totalRiskyContactReminderNotifications":null,"receivedUnconfirmedPositiveTestResult":null,"isIsolatingForUnconfirmedTestBackgroundTick":null,"launchedTestOrdering":null,"didHaveSymptomsBeforeReceivedTestResult":null,"didRememberOnsetSymptomsDateBeforeReceivedTestResult":null,"didAskForSymptomsOnPositiveTestEntry":null,"declaredNegativeResultFromDCT":null,"receivedPositiveSelfRapidTestResultViaPolling":null,"receivedNegativeSelfRapidTestResultViaPolling":null,"receivedVoidSelfRapidTestResultViaPolling":null,"receivedPositiveSelfRapidTestResultEnteredManually":null,"receivedNegativeSelfRapidTestResultEnteredManually":null,"receivedVoidSelfRapidTestResultEnteredManually":null,"isIsolatingForTestedSelfRapidPositiveBackgroundTick":null,"hasTestedSelfRapidPositiveBackgroundTick":null,"receivedRiskyVenueM1Warning":null,"receivedRiskyVenueM2Warning":null,"hasReceivedRiskyVenueM2WarningBackgroundTick":null,"totalAlarmManagerBackgroundTasks":null,"missingPacketsLast7Days":null,"consentedToShareVenueHistory":null,"askedToShareVenueHistory":null,"askedToShareExposureKeysInTheInitialFlow":null,"consentedToShareExposureKeysInTheInitialFlow":null,"totalShareExposureKeysReminderNotifications":null,"consentedToShareExposureKeysInReminderScreen":null,"successfullySharedExposureKeys":null,"didSendLocalInfoNotification":null,"didAccessLocalInfoScreenViaNotification":null,"didAccessLocalInfoScreenViaBanner":null,"isDisplayingLocalInfoBackgroundTick":null}"""
    const val STORED_ANALYTICS_PAYLOAD_ANDROID = """{"startDate":"2020-07-27T23:00:00Z","endDate":"2020-07-28T22:59:00Z","postalDistrict":"AB10","deviceModel":"HUAWEI LDN-L21","operatingSystemVersion":"29","latestApplicationVersion":"3.0","localAuthority":null,"cumulativeDownloadBytes":null,"cumulativeUploadBytes":null,"cumulativeCellularDownloadBytes":null,"cumulativeCellularUploadBytes":null,"cumulativeWifiDownloadBytes":null,"cumulativeWifiUploadBytes":null,"checkedIn":1,"canceledCheckIn":1,"receivedVoidTestResult":1,"isIsolatingBackgroundTick":1,"hasHadRiskyContactBackgroundTick":1,"receivedPositiveTestResult":1,"receivedNegativeTestResult":1,"hasSelfDiagnosedPositiveBackgroundTick":1,"completedQuestionnaireAndStartedIsolation":1,"encounterDetectionPausedBackgroundTick":1,"completedQuestionnaireButDidNotStartIsolation":1,"totalBackgroundTasks":1,"runningNormallyBackgroundTick":1,"completedOnboarding":1,"includesMultipleApplicationVersions":false,"receivedVoidTestResultEnteredManually":null,"receivedPositiveTestResultEnteredManually":null,"receivedNegativeTestResultEnteredManually":null,"receivedVoidTestResultViaPolling":null,"receivedPositiveTestResultViaPolling":null,"receivedNegativeTestResultViaPolling":null,"hasSelfDiagnosedBackgroundTick":null,"hasTestedPositiveBackgroundTick":null,"isIsolatingForSelfDiagnosedBackgroundTick":null,"isIsolatingForTestedPositiveBackgroundTick":null,"isIsolatingForHadRiskyContactBackgroundTick":null,"receivedRiskyContactNotification":null,"startedIsolation":null,"receivedPositiveTestResultWhenIsolatingDueToRiskyContact":null,"receivedActiveIpcToken":null,"haveActiveIpcTokenBackgroundTick":null,"selectedIsolationPaymentsButton":null,"launchedIsolationPaymentsApplication":null,"receivedPositiveLFDTestResultViaPolling":null,"receivedNegativeLFDTestResultViaPolling":null,"receivedVoidLFDTestResultViaPolling":null,"receivedPositiveLFDTestResultEnteredManually":null,"receivedNegativeLFDTestResultEnteredManually":null,"receivedVoidLFDTestResultEnteredManually":null,"hasTestedLFDPositiveBackgroundTick":null,"isIsolatingForTestedLFDPositiveBackgroundTick":null,"totalExposureWindowsNotConsideredRisky":null,"totalExposureWindowsConsideredRisky":null,"acknowledgedStartOfIsolationDueToRiskyContact":null,"hasRiskyContactNotificationsEnabledBackgroundTick":null,"totalRiskyContactReminderNotifications":null,"receivedUnconfirmedPositiveTestResult":null,"isIsolatingForUnconfirmedTestBackgroundTick":null,"launchedTestOrdering":null,"didHaveSymptomsBeforeReceivedTestResult":null,"didRememberOnsetSymptomsDateBeforeReceivedTestResult":null,"didAskForSymptomsOnPositiveTestEntry":null,"declaredNegativeResultFromDCT":null,"receivedPositiveSelfRapidTestResultViaPolling":null,"receivedNegativeSelfRapidTestResultViaPolling":null,"receivedVoidSelfRapidTestResultViaPolling":null,"receivedPositiveSelfRapidTestResultEnteredManually":null,"receivedNegativeSelfRapidTestResultEnteredManually":null,"receivedVoidSelfRapidTestResultEnteredManually":null,"isIsolatingForTestedSelfRapidPositiveBackgroundTick":null,"hasTestedSelfRapidPositiveBackgroundTick":null,"receivedRiskyVenueM1Warning":null,"receivedRiskyVenueM2Warning":null,"hasReceivedRiskyVenueM2WarningBackgroundTick":null,"totalAlarmManagerBackgroundTasks":null,"missingPacketsLast7Days":null,"consentedToShareVenueHistory":null,"askedToShareVenueHistory":null,"askedToShareExposureKeysInTheInitialFlow":null,"consentedToShareExposureKeysInTheInitialFlow":null,"totalShareExposureKeysReminderNotifications":null,"consentedToShareExposureKeysInReminderScreen":null,"successfullySharedExposureKeys":null,"didSendLocalInfoNotification":null,"didAccessLocalInfoScreenViaNotification":null,"didAccessLocalInfoScreenViaBanner":null,"isDisplayingLocalInfoBackgroundTick":null}"""
    const val STORED_ANALYTICS_PAYLOAD_ANDROID_WITH_LOCAL_AUTHORITY = """{"startDate":"2020-07-27T23:00:00Z","endDate":"2020-07-28T22:59:00Z","postalDistrict":"SY5_TF6","deviceModel":"HUAWEI LDN-L21","operatingSystemVersion":"29","latestApplicationVersion":"3.0","localAuthority":"E06000051","cumulativeDownloadBytes":null,"cumulativeUploadBytes":null,"cumulativeCellularDownloadBytes":null,"cumulativeCellularUploadBytes":null,"cumulativeWifiDownloadBytes":null,"cumulativeWifiUploadBytes":null,"checkedIn":1,"canceledCheckIn":1,"receivedVoidTestResult":1,"isIsolatingBackgroundTick":1,"hasHadRiskyContactBackgroundTick":1,"receivedPositiveTestResult":1,"receivedNegativeTestResult":1,"hasSelfDiagnosedPositiveBackgroundTick":1,"completedQuestionnaireAndStartedIsolation":1,"encounterDetectionPausedBackgroundTick":1,"completedQuestionnaireButDidNotStartIsolation":1,"totalBackgroundTasks":1,"runningNormallyBackgroundTick":1,"completedOnboarding":1,"includesMultipleApplicationVersions":false,"receivedVoidTestResultEnteredManually":null,"receivedPositiveTestResultEnteredManually":null,"receivedNegativeTestResultEnteredManually":null,"receivedVoidTestResultViaPolling":null,"receivedPositiveTestResultViaPolling":null,"receivedNegativeTestResultViaPolling":null,"hasSelfDiagnosedBackgroundTick":null,"hasTestedPositiveBackgroundTick":null,"isIsolatingForSelfDiagnosedBackgroundTick":null,"isIsolatingForTestedPositiveBackgroundTick":null,"isIsolatingForHadRiskyContactBackgroundTick":null,"receivedRiskyContactNotification":null,"startedIsolation":null,"receivedPositiveTestResultWhenIsolatingDueToRiskyContact":null,"receivedActiveIpcToken":null,"haveActiveIpcTokenBackgroundTick":null,"selectedIsolationPaymentsButton":null,"launchedIsolationPaymentsApplication":null,"receivedPositiveLFDTestResultViaPolling":null,"receivedNegativeLFDTestResultViaPolling":null,"receivedVoidLFDTestResultViaPolling":null,"receivedPositiveLFDTestResultEnteredManually":null,"receivedNegativeLFDTestResultEnteredManually":null,"receivedVoidLFDTestResultEnteredManually":null,"hasTestedLFDPositiveBackgroundTick":null,"isIsolatingForTestedLFDPositiveBackgroundTick":null,"totalExposureWindowsNotConsideredRisky":null,"totalExposureWindowsConsideredRisky":null,"acknowledgedStartOfIsolationDueToRiskyContact":null,"hasRiskyContactNotificationsEnabledBackgroundTick":null,"totalRiskyContactReminderNotifications":null,"receivedUnconfirmedPositiveTestResult":null,"isIsolatingForUnconfirmedTestBackgroundTick":null,"launchedTestOrdering":null,"didHaveSymptomsBeforeReceivedTestResult":null,"didRememberOnsetSymptomsDateBeforeReceivedTestResult":null,"didAskForSymptomsOnPositiveTestEntry":null,"declaredNegativeResultFromDCT":null,"receivedPositiveSelfRapidTestResultViaPolling":null,"receivedNegativeSelfRapidTestResultViaPolling":null,"receivedVoidSelfRapidTestResultViaPolling":null,"receivedPositiveSelfRapidTestResultEnteredManually":null,"receivedNegativeSelfRapidTestResultEnteredManually":null,"receivedVoidSelfRapidTestResultEnteredManually":null,"isIsolatingForTestedSelfRapidPositiveBackgroundTick":null,"hasTestedSelfRapidPositiveBackgroundTick":null,"receivedRiskyVenueM1Warning":null,"receivedRiskyVenueM2Warning":null,"hasReceivedRiskyVenueM2WarningBackgroundTick":null,"totalAlarmManagerBackgroundTasks":null,"missingPacketsLast7Days":null,"consentedToShareVenueHistory":null,"askedToShareVenueHistory":null,"askedToShareExposureKeysInTheInitialFlow":null,"consentedToShareExposureKeysInTheInitialFlow":null,"totalShareExposureKeysReminderNotifications":null,"consentedToShareExposureKeysInReminderScreen":null,"successfullySharedExposureKeys":null,"didSendLocalInfoNotification":null,"didAccessLocalInfoScreenViaNotification":null,"didAccessLocalInfoScreenViaBanner":null,"isDisplayingLocalInfoBackgroundTick":null}"""
    const val STORED_ANALYTICS_MERGED_POSTCODE_PAYLOAD_ANDROID = """{"startDate":"2020-07-27T23:00:00Z","endDate":"2020-07-28T22:59:00Z","postalDistrict":"AB13_AB14","deviceModel":"HUAWEI LDN-L21","operatingSystemVersion":"29","latestApplicationVersion":"3.0","localAuthority":null,"cumulativeDownloadBytes":null,"cumulativeUploadBytes":null,"cumulativeCellularDownloadBytes":null,"cumulativeCellularUploadBytes":null,"cumulativeWifiDownloadBytes":null,"cumulativeWifiUploadBytes":null,"checkedIn":1,"canceledCheckIn":1,"receivedVoidTestResult":1,"isIsolatingBackgroundTick":1,"hasHadRiskyContactBackgroundTick":1,"receivedPositiveTestResult":1,"receivedNegativeTestResult":1,"hasSelfDiagnosedPositiveBackgroundTick":1,"completedQuestionnaireAndStartedIsolation":1,"encounterDetectionPausedBackgroundTick":1,"completedQuestionnaireButDidNotStartIsolation":1,"totalBackgroundTasks":1,"runningNormallyBackgroundTick":1,"completedOnboarding":1,"includesMultipleApplicationVersions":false,"receivedVoidTestResultEnteredManually":null,"receivedPositiveTestResultEnteredManually":null,"receivedNegativeTestResultEnteredManually":null,"receivedVoidTestResultViaPolling":null,"receivedPositiveTestResultViaPolling":null,"receivedNegativeTestResultViaPolling":null,"hasSelfDiagnosedBackgroundTick":null,"hasTestedPositiveBackgroundTick":null,"isIsolatingForSelfDiagnosedBackgroundTick":null,"isIsolatingForTestedPositiveBackgroundTick":null,"isIsolatingForHadRiskyContactBackgroundTick":null,"receivedRiskyContactNotification":null,"startedIsolation":null,"receivedPositiveTestResultWhenIsolatingDueToRiskyContact":null,"receivedActiveIpcToken":null,"haveActiveIpcTokenBackgroundTick":null,"selectedIsolationPaymentsButton":null,"launchedIsolationPaymentsApplication":null,"receivedPositiveLFDTestResultViaPolling":null,"receivedNegativeLFDTestResultViaPolling":null,"receivedVoidLFDTestResultViaPolling":null,"receivedPositiveLFDTestResultEnteredManually":null,"receivedNegativeLFDTestResultEnteredManually":null,"receivedVoidLFDTestResultEnteredManually":null,"hasTestedLFDPositiveBackgroundTick":null,"isIsolatingForTestedLFDPositiveBackgroundTick":null,"totalExposureWindowsNotConsideredRisky":null,"totalExposureWindowsConsideredRisky":null,"acknowledgedStartOfIsolationDueToRiskyContact":null,"hasRiskyContactNotificationsEnabledBackgroundTick":null,"totalRiskyContactReminderNotifications":null,"receivedUnconfirmedPositiveTestResult":null,"isIsolatingForUnconfirmedTestBackgroundTick":null,"launchedTestOrdering":null,"didHaveSymptomsBeforeReceivedTestResult":null,"didRememberOnsetSymptomsDateBeforeReceivedTestResult":null,"didAskForSymptomsOnPositiveTestEntry":null,"declaredNegativeResultFromDCT":null,"receivedPositiveSelfRapidTestResultViaPolling":null,"receivedNegativeSelfRapidTestResultViaPolling":null,"receivedVoidSelfRapidTestResultViaPolling":null,"receivedPositiveSelfRapidTestResultEnteredManually":null,"receivedNegativeSelfRapidTestResultEnteredManually":null,"receivedVoidSelfRapidTestResultEnteredManually":null,"isIsolatingForTestedSelfRapidPositiveBackgroundTick":null,"hasTestedSelfRapidPositiveBackgroundTick":null,"receivedRiskyVenueM1Warning":null,"receivedRiskyVenueM2Warning":null,"hasReceivedRiskyVenueM2WarningBackgroundTick":null,"totalAlarmManagerBackgroundTasks":null,"missingPacketsLast7Days":null,"consentedToShareVenueHistory":null,"askedToShareVenueHistory":null,"askedToShareExposureKeysInTheInitialFlow":null,"consentedToShareExposureKeysInTheInitialFlow":null,"totalShareExposureKeysReminderNotifications":null,"consentedToShareExposureKeysInReminderScreen":null,"successfullySharedExposureKeys":null,"didSendLocalInfoNotification":null,"didAccessLocalInfoScreenViaNotification":null,"didAccessLocalInfoScreenViaBanner":null,"isDisplayingLocalInfoBackgroundTick":null}"""
    const val STORED_ANALYTICS_UNKNOWN_POSTCODE_PAYLOAD_ANDROID = """{"startDate":"2020-07-27T23:00:00Z","endDate":"2020-07-28T22:59:00Z","postalDistrict":"UNKNOWN","deviceModel":"HUAWEI LDN-L21","operatingSystemVersion":"29","latestApplicationVersion":"3.0","localAuthority":"UNKNOWN","cumulativeDownloadBytes":null,"cumulativeUploadBytes":null,"cumulativeCellularDownloadBytes":null,"cumulativeCellularUploadBytes":null,"cumulativeWifiDownloadBytes":null,"cumulativeWifiUploadBytes":null,"checkedIn":1,"canceledCheckIn":1,"receivedVoidTestResult":1,"isIsolatingBackgroundTick":1,"hasHadRiskyContactBackgroundTick":1,"receivedPositiveTestResult":1,"receivedNegativeTestResult":1,"hasSelfDiagnosedPositiveBackgroundTick":1,"completedQuestionnaireAndStartedIsolation":1,"encounterDetectionPausedBackgroundTick":1,"completedQuestionnaireButDidNotStartIsolation":1,"totalBackgroundTasks":1,"runningNormallyBackgroundTick":1,"completedOnboarding":1,"includesMultipleApplicationVersions":false,"receivedVoidTestResultEnteredManually":null,"receivedPositiveTestResultEnteredManually":null,"receivedNegativeTestResultEnteredManually":null,"receivedVoidTestResultViaPolling":null,"receivedPositiveTestResultViaPolling":null,"receivedNegativeTestResultViaPolling":null,"hasSelfDiagnosedBackgroundTick":null,"hasTestedPositiveBackgroundTick":null,"isIsolatingForSelfDiagnosedBackgroundTick":null,"isIsolatingForTestedPositiveBackgroundTick":null,"isIsolatingForHadRiskyContactBackgroundTick":null,"receivedRiskyContactNotification":null,"startedIsolation":null,"receivedPositiveTestResultWhenIsolatingDueToRiskyContact":null,"receivedActiveIpcToken":null,"haveActiveIpcTokenBackgroundTick":null,"selectedIsolationPaymentsButton":null,"launchedIsolationPaymentsApplication":null,"receivedPositiveLFDTestResultViaPolling":null,"receivedNegativeLFDTestResultViaPolling":null,"receivedVoidLFDTestResultViaPolling":null,"receivedPositiveLFDTestResultEnteredManually":null,"receivedNegativeLFDTestResultEnteredManually":null,"receivedVoidLFDTestResultEnteredManually":null,"hasTestedLFDPositiveBackgroundTick":null,"isIsolatingForTestedLFDPositiveBackgroundTick":null,"totalExposureWindowsNotConsideredRisky":null,"totalExposureWindowsConsideredRisky":null,"acknowledgedStartOfIsolationDueToRiskyContact":null,"hasRiskyContactNotificationsEnabledBackgroundTick":null,"totalRiskyContactReminderNotifications":null,"receivedUnconfirmedPositiveTestResult":null,"isIsolatingForUnconfirmedTestBackgroundTick":null,"launchedTestOrdering":null,"didHaveSymptomsBeforeReceivedTestResult":null,"didRememberOnsetSymptomsDateBeforeReceivedTestResult":null,"didAskForSymptomsOnPositiveTestEntry":null,"declaredNegativeResultFromDCT":null,"receivedPositiveSelfRapidTestResultViaPolling":null,"receivedNegativeSelfRapidTestResultViaPolling":null,"receivedVoidSelfRapidTestResultViaPolling":null,"receivedPositiveSelfRapidTestResultEnteredManually":null,"receivedNegativeSelfRapidTestResultEnteredManually":null,"receivedVoidSelfRapidTestResultEnteredManually":null,"isIsolatingForTestedSelfRapidPositiveBackgroundTick":null,"hasTestedSelfRapidPositiveBackgroundTick":null,"receivedRiskyVenueM1Warning":null,"receivedRiskyVenueM2Warning":null,"hasReceivedRiskyVenueM2WarningBackgroundTick":null,"totalAlarmManagerBackgroundTasks":null,"missingPacketsLast7Days":null,"consentedToShareVenueHistory":null,"askedToShareVenueHistory":null,"askedToShareExposureKeysInTheInitialFlow":null,"consentedToShareExposureKeysInTheInitialFlow":null,"totalShareExposureKeysReminderNotifications":null,"consentedToShareExposureKeysInReminderScreen":null,"successfullySharedExposureKeys":null,"didSendLocalInfoNotification":null,"didAccessLocalInfoScreenViaNotification":null,"didAccessLocalInfoScreenViaBanner":null,"isDisplayingLocalInfoBackgroundTick":null}"""
    const val EXPOSURE_NOTIFICATION_CIRCUIT_BREAKER_PAYLOAD = """ {"matchedKeyCount" : 2,
 "daysSinceLastExposure": 3,
 "maximumRiskScore" : 150
 }"""

    val positiveLabResult = AvailableTestResult(
        TestResultPollingToken.of("abc"),
        TestEndDate.of(2020, 4, 23),
        Positive,
        LAB_RESULT
    )
    val pendingTestResult = TestState.PendingTestResult(
        TestResultPollingToken.of("abc"),
        LAB_RESULT
    )
    val positiveRapidResult = AvailableTestResult(
        TestResultPollingToken.of("abc"),
        TestEndDate.of(2020, 4, 23),
        Positive,
        RAPID_RESULT
    )

    fun positiveResultFor(token: TestResultPollingToken, testKit: TestKit = LAB_RESULT): AvailableTestResult =
        AvailableTestResult(
            token,
            TestEndDate.of(2020, 4, 23),
            Positive,
            testKit
        )

    fun negativeResultFor(testKit: TestKit): AvailableTestResult =
        AvailableTestResult(
            TestResultPollingToken.of("abc"),
            TestEndDate.of(2020, 4, 23),
            Negative,
            testKit
        )

    fun voidResultFor(testKit: TestKit): AvailableTestResult =
        AvailableTestResult(
            TestResultPollingToken.of("abc"),
            TestEndDate.of(2020, 4, 23),
            Void,
            testKit
        )

    const val tokenGenPayloadV1 = """{"testEndDate":"2020-09-07T00:00:00Z","testResult":"POSITIVE"}"""
    const val tokenGenPayloadV2 = """{"testEndDate":"2020-09-07T00:00:00Z","testResult":"POSITIVE","testKit":"RAPID_RESULT"}"""
    const val tokenGenSelfReportedPayloadV2 = """{"testEndDate":"2020-09-07T00:00:00Z","testResult":"POSITIVE","testKit":"RAPID_SELF_REPORTED"}"""

    const val testLabResultV1 = """{"ctaToken": "cc8f0b6z","testEndDate": "2020-04-23T00:00:00Z","testResult": "NEGATIVE"}"""
    const val rapidLabResultV2 = """{"ctaToken": "cc8f0b6z","testEndDate": "2020-04-23T00:00:00Z","testResult": "POSITIVE","testKit": "RAPID_RESULT"}"""
    const val rapidSelfReportedResultV2 = """{"ctaToken": "cc8f0b6z","testEndDate": "2020-04-23T00:00:00Z","testResult": "POSITIVE","testKit": "RAPID_SELF_REPORTED"}"""

    const val tokenStatusPayloadV2 = """{"ctaToken":"cc8f0b6z"}"""

    val expectedCtaTokenGenStats = listOf(
        listOf(
            ResultField()
                .withField("start_date")
                .withValue("2021-05-04 01:00:00.000"),
            ResultField()
                .withField("test_type")
                .withValue("RAPID_SELF_REPORTED"),
            ResultField()
                .withField("source")
                .withValue("Eng"),
            ResultField()
                .withField("result_type")
                .withValue("NEGATIVE"),
            ResultField()
                .withField("total")
                .withValue("100")
        ),
        listOf(
            ResultField()
                .withField("start_date")
                .withValue("2021-05-04 01:00:00.000"),
            ResultField()
                .withField("test_type")
                .withValue("RAPID_SELF_REPORTED"),
            ResultField()
                .withField("source")
                .withValue("Wls"),
            ResultField()
                .withField("result_type")
                .withValue("POSITIVE"),
            ResultField()
                .withField("total")
                .withValue("450")
        )
    )
    val expectedCircuitBreakerLogs = listOf(
        listOf(
            ResultField()
                .withField("start_of_hour")
                .withValue("2021-01-20 16:00:00.000"),
            ResultField()
                .withField("exposure_notification_cb_count")
                .withValue("324"),
            ResultField()
                .withField("iOS_exposure_notification_cb_count")
                .withValue("143"),
            ResultField()
                .withField("android_exposure_notification_cb_count")
                .withValue("181"),
            ResultField()
                .withField("unique_request_ids")
                .withValue("0"),
            ResultField()
                .withField("app_version")
                .withValue("0.1.2")
        ),
        listOf(
            ResultField()
                .withField("start_of_hour")
                .withValue("2021-01-20 15:00:00.000"),
            ResultField()
                .withField("exposure_notification_cb_count")
                .withValue("324"),
            ResultField()
                .withField("iOS_exposure_notification_cb_count")
                .withValue("143"),
            ResultField()
                .withField("android_exposure_notification_cb_count")
                .withValue("181"),
            ResultField()
                .withField("unique_request_ids")
                .withValue("0"),
            ResultField()
                .withField("app_version")
                .withValue("3.4.5")
        )
    )

    val expectedInterOpDownloadStats = listOf(
        listOf(
            ResultField()
                .withField("start_of_hour")
                .withValue("2021-01-20 16:00:00.000"),
            ResultField()
                .withField("origin")
                .withValue("GB-SCO"),
            ResultField()
                .withField("test_type")
                .withValue("0"),
            ResultField()
                .withField("number_of_keys_downloaded")
                .withValue("100"),
            ResultField()
                .withField("number_of_keys_imported")
                .withValue("100")
        ),
        listOf(
            ResultField()
                .withField("start_of_hour")
                .withValue("2021-01-20 17:00:00.000"),
            ResultField()
                .withField("origin")
                .withValue("JE"),
            ResultField()
                .withField("test_type")
                .withValue("1"),
            ResultField()
                .withField("number_of_keys_downloaded")
                .withValue("101"),
            ResultField()
                .withField("number_of_keys_imported")
                .withValue("99"),
        )
    )

    val expectedInterOpUploadStats = listOf(
        listOf(
            ResultField()
                .withField("start_of_hour")
                .withValue("2021-01-20 16:00:00.000"),
            ResultField()
                .withField("test_type")
                .withValue("0"),
            ResultField()
                .withField("number_of_keys_uploaded")
                .withValue("100")
        ),
        listOf(
            ResultField()
                .withField("start_of_hour")
                .withValue("2021-01-20 17:00:00.000"),
            ResultField()
                .withField("test_type")
                .withValue("1"),
            ResultField()
                .withField("number_of_keys_uploaded")
                .withValue("101")
        )
    )
    val expectedCtaExchangeStats = listOf(
        listOf(
            ResultField()
                .withField("start_date")
                .withValue("2021-05-04 01:00:00.000"),
            ResultField()
                .withField("test_type")
                .withValue("RAPID_SELF_REPORTED"),
            ResultField()
                .withField("platform")
                .withValue("iOS"),
            ResultField()
                .withField("token_age_range")
                .withValue("GREATER_THAN_48_HOURS"),
            ResultField()
                .withField("source")
                .withValue("England"),
            ResultField()
                .withField("total")
                .withValue("300"),
            ResultField()
                .withField("app_version")
                .withValue("4.10")
        ),
        listOf(
            ResultField()
                .withField("start_date")
                .withValue("2021-05-04 01:00:00.000"),
            ResultField()
                .withField("test_type")
                .withValue("RAPID_SELF_REPORTED"),
            ResultField()
                .withField("platform")
                .withValue("Android"),
            ResultField()
                .withField("token_age_range")
                .withValue("GREATER_THAN_48_HOURS"),
            ResultField()
                .withField("source")
                .withValue("England"),
            ResultField()
                .withField("total")
                .withValue("200"),
            ResultField()
                .withField("app_version")
                .withValue("4.10")
        )
    )

    val expectedCtaTokenStatusStats = listOf(
        listOf(
            ResultField()
                .withField("start_date")
                .withValue("2021-05-04 01:00:00.000"),
            ResultField()
                .withField("test_type")
                .withValue("RAPID_SELF_REPORTED"),
            ResultField()
                .withField("source")
                .withValue("Eng"),
            ResultField()
                .withField("total")
                .withValue("200")
        ),
        listOf(
            ResultField()
                .withField("start_date")
                .withValue("2021-05-04 01:00:00.000"),
            ResultField()
                .withField("test_type")
                .withValue("LAB_RESULT"),
            ResultField()
                .withField("source")
                .withValue("Wls"),
            ResultField()
                .withField("total")
                .withValue("100")
        )
    )

}
