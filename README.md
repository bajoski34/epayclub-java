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

```java
import com.epayclub.sdk.models.orders.*;

CreateOrderRequest request = CreateOrderRequest.builder()
    .amount(1000.0)
    .currency("NGN")
    .email("customer@example.com")
    .firstName("John")
    .lastName("Doe")
    .narration("Payment for Order #12345")
    .redirectUrl("https://yoursite.com/callback")
    .build();

CreateOrderResponse response = client.orders().create(request);

System.out.println("Order ID: " + response.getData().getOrderId());
System.out.println("Checkout URL: " + response.getData().getCheckoutUrl());
```

### Process Payment

```java
PayOrderRequest payRequest = PayOrderRequest.builder()
    .orderId(response.getData().getOrderId())
    .paymentMethod("card")
    .cardNumber("4111111111111111")
    .expiryMonth("12")
    .expiryYear("25")
    .cvv("123")
    .build();

PayOrderResponse payResponse = client.orders().pay(payRequest);
```

### Verify Order

```java
VerifyOrderRequest verifyRequest = new VerifyOrderRequest(orderId);
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

For endpoints that require encrypted payloads, configure the encryption key:

```java
EpayClubClient client = EpayClubClient.builder()
    .apiKey("your-api-key")
    .merchantEncryptionKey("your-base64-encoded-encryption-key")
    .build();

// Use RequestOptions to enable encryption
PayOrderRequest request = PayOrderRequest.builder()
    .orderId("order123")
    .paymentMethod("card")
    .cardNumber("4111111111111111")
    .expiryMonth("12")
    .expiryYear("25")
    .cvv("123")
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
