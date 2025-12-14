package com.epayclub.sdk.models.orders;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class CreateOrderRequestTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void deserializeExampleJson() throws Exception {
        String json = """
            {
              "customer": {
                "email": "customer@example.com",
                "mobile": "+2348012345678",
                "firstname": "John",
                "lastname": "Doe",
                "country": "NG"
              },
              "order": {
                "amount": 1500,
                "reference": "ORDER-123456",
                "currency": "NGN",
                "description": "Order for premium subscription"
              },
              "payment": {
                "RedirectUrl": "https://merchant.example.com/payment/callback",
                "paymentlinkid": 10,
                "frequencyId": 1,
                "numberOfPayments": 1
              },
              "paymentMeta": {
                "ipAddress": "203.0.113.10"
              }
            }
            """;

        CreateOrderRequest r = mapper.readValue(json, CreateOrderRequest.class);

        assertThat(r).isNotNull();
        assertThat(r.getCustomer()).isNotNull();
        assertThat(r.getCustomer().getEmail()).isEqualTo("customer@example.com");
        assertThat(r.getCustomer().getMobile()).isEqualTo("+2348012345678");
        assertThat(r.getCustomer().getFirstname()).isEqualTo("John");
        assertThat(r.getCustomer().getLastname()).isEqualTo("Doe");
        assertThat(r.getCustomer().getCountry()).isEqualTo("NG");

        assertThat(r.getOrder()).isNotNull();
        assertThat(r.getOrder().getAmount()).isEqualTo(1500d);
        assertThat(r.getOrder().getReference()).isEqualTo("ORDER-123456");
        assertThat(r.getOrder().getCurrency()).isEqualTo("NGN");
        assertThat(r.getOrder().getDescription()).isEqualTo("Order for premium subscription");

        assertThat(r.getPayment()).isNotNull();
        assertThat(r.getPayment().getRedirectUrl()).isEqualTo("https://merchant.example.com/payment/callback");
        assertThat(r.getPayment().getPaymentlinkid()).isEqualTo(10);
        assertThat(r.getPayment().getFrequencyId()).isEqualTo(1);
        assertThat(r.getPayment().getNumberOfPayments()).isEqualTo(1);

        assertThat(r.getPaymentMeta()).isNotNull();
        assertThat(r.getPaymentMeta().get("ipAddress")).isEqualTo("203.0.113.10");
    }
}

