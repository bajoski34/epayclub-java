package com.epayclub.sdk.crypto;

import com.epayclub.sdk.errors.EpayClubClientException;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests for the Encryption utility class.
 */
class EncryptionTest {

    // Test RSA public key in XML format (2048-bit key for testing)
    private static final String TEST_RSA_XML = 
        "<RSAKeyValue>" +
        "<Modulus>w2BPJPG9DfvW9vNQRVN1M9g5LRJvPxR/5B7w8bDR8X1z8QxPm3oJCTdtLjv7H2d7" +
        "K1R2ZPxQ3zL5h9M7FN7VzXR0xj8K5Dv2FQH4qL8G9M1N6Y7P3B5S4C2V8X0W6T1" +
        "J5K9L3E7H0D4F2A6S8G1R5Y9I7U3O5P0W4E2T6R8Y1Q3M5N7B9V2C4X6Z0A8S1" +
        "D3F5G7H9J2K4L6P0O8I7U5Y3T1R9E7W5Q3N1M8B6V4C2X0Z9A7S5D3F1G9H7J2" +
        "K4L6O8P0I9U7Y5T3R1E0W8Q6N4M2B0V8C6X4Z2A9S7D5F3G1H0J8K6L4O2P1I9" +
        "U7Y5T3R1E0W=</Modulus>" +
        "<Exponent>AQAB</Exponent>" +
        "</RSAKeyValue>";

    // A valid base64-encoded encryption key containing RSA XML after the "!" delimiter
    private static final String TEST_ENCRYPTION_KEY;

    static {
        // Format: "prefix!<RSAKeyValue>...</RSAKeyValue>"
        String keyString = "merchant123!" + TEST_RSA_XML;
        TEST_ENCRYPTION_KEY = Base64.getEncoder().encodeToString(keyString.getBytes());
    }

    @Test
    void encryptPayload_shouldThrowWhenKeyIsNull() {
        assertThatThrownBy(() -> Encryption.encryptPayload(null, "test"))
                .isInstanceOf(EpayClubClientException.class)
                .hasMessageContaining("encryption key is required");
    }

    @Test
    void encryptPayload_shouldThrowWhenKeyIsBlank() {
        assertThatThrownBy(() -> Encryption.encryptPayload("   ", "test"))
                .isInstanceOf(EpayClubClientException.class)
                .hasMessageContaining("encryption key is required");
    }

    @Test
    void encryptPayload_shouldThrowWhenKeyHasNoDelimiter() {
        String invalidKey = Base64.getEncoder().encodeToString("nodelmiter".getBytes());
        assertThatThrownBy(() -> Encryption.encryptPayload(invalidKey, "test"))
                .isInstanceOf(EpayClubClientException.class)
                .hasMessageContaining("expected '!' delimiter");
    }

    @Test
    void xmlRsaPublicKeyToPem_shouldConvertXmlToPem() {
        // Use a simple, valid RSA XML key
        String simpleXml = "<RSAKeyValue>" +
                "<Modulus>0vx7agoebGcQSuuPiLJXZptN9nndrQmbXEps2aiAFbWhM78LhWx4cbbfAAtVT86zwu1RK7aPFFxuhDR1L6tSoc/BJECPebWKRXjBZCiFV4n3oknjhMstn64tZ/2W+5JsGY4Hc5n9yBXArwl93lqt7/RN5w6Cf0h4QyQ5v+65YGjQR0/FDW2QvzqY368QQMicAtaSqzs8KJZgnYb9c7d0zgdAZHzu6qMQvRL5hajrn1n91CbOpbISD08qNLyrdkt+bFTWhAI4vMQFh6WeZu0fM4lFd2NcRwr3XPksINHaQ+G/xBniIqbw0Ls1jF44+csFCur+kEgU8awapJzKnqDKgw==</Modulus>" +
                "<Exponent>AQAB</Exponent>" +
                "</RSAKeyValue>";

        String pem = Encryption.xmlRsaPublicKeyToPem(simpleXml);

        assertThat(pem)
                .startsWith("-----BEGIN PUBLIC KEY-----")
                .endsWith("-----END PUBLIC KEY-----")
                .contains("\n");
    }

    @Test
    void xmlRsaPublicKeyToPem_shouldThrowWhenMissingModulus() {
        String invalidXml = "<RSAKeyValue><Exponent>AQAB</Exponent></RSAKeyValue>";

        assertThatThrownBy(() -> Encryption.xmlRsaPublicKeyToPem(invalidXml))
                .isInstanceOf(EpayClubClientException.class)
                .hasMessageContaining("missing Modulus or Exponent");
    }

    @Test
    void xmlRsaPublicKeyToPem_shouldThrowWhenMissingExponent() {
        String invalidXml = "<RSAKeyValue><Modulus>dGVzdA==</Modulus></RSAKeyValue>";

        assertThatThrownBy(() -> Encryption.xmlRsaPublicKeyToPem(invalidXml))
                .isInstanceOf(EpayClubClientException.class)
                .hasMessageContaining("missing Modulus or Exponent");
    }

    @Test
    void rsaEncryptToBase64_shouldEncryptWithPemKey() {
        // First convert XML to PEM
        String simpleXml = "<RSAKeyValue>" +
                "<Modulus>0vx7agoebGcQSuuPiLJXZptN9nndrQmbXEps2aiAFbWhM78LhWx4cbbfAAtVT86zwu1RK7aPFFxuhDR1L6tSoc/BJECPebWKRXjBZCiFV4n3oknjhMstn64tZ/2W+5JsGY4Hc5n9yBXArwl93lqt7/RN5w6Cf0h4QyQ5v+65YGjQR0/FDW2QvzqY368QQMicAtaSqzs8KJZgnYb9c7d0zgdAZHzu6qMQvRL5hajrn1n91CbOpbISD08qNLyrdkt+bFTWhAI4vMQFh6WeZu0fM4lFd2NcRwr3XPksINHaQ+G/xBniIqbw0Ls1jF44+csFCur+kEgU8awapJzKnqDKgw==</Modulus>" +
                "<Exponent>AQAB</Exponent>" +
                "</RSAKeyValue>";
        String pem = Encryption.xmlRsaPublicKeyToPem(simpleXml);

        String encrypted = Encryption.rsaEncryptToBase64(pem, "test data");

        // Should be valid base64
        assertThat(encrypted).isNotBlank();
        assertThatCode(() -> Base64.getDecoder().decode(encrypted))
                .doesNotThrowAnyException();
    }

    @Test
    void encryptPayload_shouldEncryptWithValidKey() {
        // Use a valid RSA key for this test
        String validXml = "<RSAKeyValue>" +
                "<Modulus>0vx7agoebGcQSuuPiLJXZptN9nndrQmbXEps2aiAFbWhM78LhWx4cbbfAAtVT86zwu1RK7aPFFxuhDR1L6tSoc/BJECPebWKRXjBZCiFV4n3oknjhMstn64tZ/2W+5JsGY4Hc5n9yBXArwl93lqt7/RN5w6Cf0h4QyQ5v+65YGjQR0/FDW2QvzqY368QQMicAtaSqzs8KJZgnYb9c7d0zgdAZHzu6qMQvRL5hajrn1n91CbOpbISD08qNLyrdkt+bFTWhAI4vMQFh6WeZu0fM4lFd2NcRwr3XPksINHaQ+G/xBniIqbw0Ls1jF44+csFCur+kEgU8awapJzKnqDKgw==</Modulus>" +
                "<Exponent>AQAB</Exponent>" +
                "</RSAKeyValue>";
        String validKey = Base64.getEncoder().encodeToString(("merchant!" + validXml).getBytes());

        String encrypted = Encryption.encryptPayload(validKey, "{\"test\":\"data\"}");

        assertThat(encrypted).isNotBlank();
        assertThatCode(() -> Base64.getDecoder().decode(encrypted))
                .doesNotThrowAnyException();
    }
}
