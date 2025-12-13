# Changelog

All notable changes to the EpayClub Java SDK will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0] - 2024-01-15

### Added

- Initial release of the EpayClub Java SDK
- **Orders Service**
  - Create order (`create`)
  - Calculate fee (`fee`)
  - Process payment (`pay`)
  - Get order status (`status`)
  - Verify order (`verify`)
  - Get order timeline (`timeline`)
- **Payment Links Service**
  - Create payment link (`create`)
  - List payment links (`list`)
  - Get link types (`types`)
  - Edit payment link (`edit`)
  - Activate payment link (`activate`)
  - Deactivate payment link (`deactivate`)
  - Cancel recurring payments (`cancelRecurringPayments`)
  - Get payment frequencies (`frequencies`)
- **Payment Operations Service**
  - Get bank codes (`bankCodes`)
- RSA encryption support for sensitive payloads
- Automatic retry with exponential backoff for transient failures (408, 429, 5xx)
- Comprehensive error handling with `EpayClubApiException` and `EpayClubClientException`
- Type-safe request and response models
- Builder pattern for all request objects
- Configurable timeout, retries, and base URL
- Full JavaDoc documentation
