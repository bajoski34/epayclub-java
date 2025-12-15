# EpayClub Java SDK

Official Java SDK for the EpayClub Payment API. A production-grade, type-safe client library for integrating EpayClub payments into your Java applications.

[![Java Version](https://img.shields.io/badge/Java-17%2B-blue)](https://adoptium.net/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## Features

- ✅ Full coverage of EpayClub API endpoints (Orders, Payment Links, Payment Operations)
- ✅ Type-safe request/response models
- ✅ Automatic retry with exponential backoff for transient failures
- ✅ RSA encryption support for sensitive payloads
- ✅ Comprehensive error handling
- ✅ Built with Java's HttpClient (no external HTTP dependencies)
- ✅ Jackson for JSON processing

## Requirements

- Java 17 or higher
- Maven 3.6+ or Gradle 7.0+

## Installation

### Maven

Add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.epayclub</groupId>
    <artifactId>epayclub-java</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle

Add the following to your `build.gradle`:

```groovy
implementation 'com.epayclub:epayclub-java:1.0.0'
```

Or for Kotlin DSL (`build.gradle.kts`):

```kotlin
implementation("com.epayclub:epayclub-java:1.0.0")
```

## Quick Start

### Initialize the Client

```java
import com.epayclub.sdk.EpayClubClient;

EpayClubClient client = EpayClubClient.builder()
    .apiKey("your-api-key")
    .build();
```

### Create an Order

(Note: the SDK uses a nested request shape for orders — `customer`, `order`, and `payment` are nested objects.)

```java
import com.epayclub.sdk.models.orders.*;

CreateOrderRequest request = CreateOrderRequest.builder()
    // convenience helper that builds the nested customer object
    .customer("customer@example.com", "0900000000", "John", "Doe", "NG")
    // convenience helper that builds the nested order object: amount, reference, currency, description
    .order(1000.0, "ORDER-12345", "NGN", "Payment for Order #12345")
    // optional payment meta (redirect URL, etc.)
    .payment("https://yoursite.com/callback", null, null, null)
    .build();

CreateOrderResponse response = client.orders().create(request);

// Access created order reference and payment/source info from the response
System.out.println("Order reference: " + response.getData().getOrder().getReference());
System.out.println("Payment source / checkout info: " +
        (response.getData().getPayment() != null ? response.getData().getPayment().getSource() : "(none)"));
```

### Process Payment

(The SDK expects a `reference` string for the order and a nested `Card` object when paying by card. The SDK tests use the code "C" for card paymentMethod.)

```java
PayOrderRequest payRequest = PayOrderRequest.builder()
    .reference(response.getData().getOrder().getReference())
    .paymentMethod("C") // use "C" for card (SDK validation expects this code)
    .country("NG")
    .card(PayOrderRequest.Card.builder()
        .cardNumber("4111111111111111")
        .expiryMonth("12")
        .expiryYear("25")
        .cvv("123")
        .build())
    .build();

PayOrderResponse payResponse = client.orders().pay(payRequest);
```

### Verify Order

```java
// Use the order reference returned when you created the order
String orderRef = response.getData().getOrder().getReference();

VerifyOrderRequest verifyRequest = new VerifyOrderRequest(orderRef);
VerifyOrderResponse verifyResponse = client.orders().verify(verifyRequest);

if (Boolean.TRUE.equals(verifyResponse.getData().getVerified())) {
    System.out.println("Payment verified successfully!");
}
```

## API Reference

### Orders Service

```java
// Create an order
client.orders().create(CreateOrderRequest request)

// Calculate fee
client.orders().fee(FeeRequest request)

// Process payment
client.orders().pay(PayOrderRequest request)

// Get order status
client.orders().status(OrderStatusRequest request)

// Verify order
client.orders().verify(VerifyOrderRequest request)

// Get order timeline
client.orders().timeline(OrderTimelineRequest request)
```

### Payment Links Service

```java
// Create a payment link
client.paymentLinks().create(CreateLinkRequest request)

// List payment links
client.paymentLinks().list(FetchLinksRequest request)

// Get link types
client.paymentLinks().types()

// Edit a payment link
client.paymentLinks().edit(EditLinkRequest request)

// Activate a link
client.paymentLinks().activate(ActivateLinkRequest request)

// Deactivate a link
client.paymentLinks().deactivate(DeactivateLinkRequest request)

// Cancel recurring payments
client.paymentLinks().cancelRecurringPayments(CancelRecurringPaymentsRequest request)

// Get payment frequencies
client.paymentLinks().frequencies()
```

### Payment Operations Service

```java
// Get bank codes
client.paymentOperations().bankCodes()
```

## Encryption Support

For endpoints that require encrypted payloads, configure the encryption key when building the client:

```java
EpayClubClient client = EpayClubClient.builder()
    .apiKey("your-api-key")
    .merchantEncryptionKey("your-base64-encoded-encryption-key")
    .build();

// Use RequestOptions to enable encryption for a specific call. Note: OrdersService currently forces encryption for order-related calls internally,
// but you can still explicitly pass options when calling the API.
PayOrderRequest request = PayOrderRequest.builder()
    .reference("ORDER-12345")
    .paymentMethod("C")
    .country("NG")
    .card(PayOrderRequest.Card.builder()
        .cardNumber("4111111111111111")
        .expiryMonth("12")
        .expiryYear("25")
        .cvv("123")
        .build())
    .build();

PayOrderResponse response = client.orders().pay(request, RequestOptions.encrypt(true));
```

## Error Handling

The SDK provides specific exception types for different error scenarios:

```java
import com.epayclub.sdk.errors.*;

try {
    CreateOrderResponse response = client.orders().create(request);
} catch (EpayClubApiException e) {
    // API errors (4xx, 5xx responses)
    System.err.println("API Error: " + e.getMessage());
    System.err.println("HTTP Status: " + e.getHttpStatus());
    System.err.println("Status ID: " + e.getStatusId());
    System.err.println("Response Code: " + e.getResponseCode());
} catch (EpayClubClientException e) {
    // Client-side errors (validation, serialization, etc.)
    System.err.println("Client Error: " + e.getMessage());
}
```

## Configuration Options

```java
EpayClubClient client = EpayClubClient.builder()
    .apiKey("your-api-key")                    // Required
    .baseUrl("https://custom-api.example.com") // Optional, default: https://checkout-api-service.epayclub.com
    .timeout(Duration.ofSeconds(60))           // Optional, default: 30 seconds
    .retries(5)                                // Optional, default: 3
    .userAgent("my-app/1.0")                   // Optional, default: epayclub-java/1.0.0
    .merchantEncryptionKey("base64-key")       // Optional, for encrypted endpoints
    .build();
```

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
