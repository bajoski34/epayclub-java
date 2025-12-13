package com.epayclub.sdk.crypto;

import com.epayclub.sdk.errors.EpayClubClientException;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Encryption utilities for EpayClub API.
 * Implements RSA encryption for secure payload transmission.
 */
public final class Encryption {

    private static final Pattern MODULUS_PATTERN = Pattern.compile("<Modulus>([^<]+)</Modulus>");
    private static final Pattern EXPONENT_PATTERN = Pattern.compile("<Exponent>([^<]+)</Exponent>");

    private Encryption() {
        // Utility class
    }

    /**
     * Encrypts a payload using the merchant encryption key.
     * 
     * Steps (per EpayClub encryption docs):
     * 1. Base64 decode the merchantEncryptionKey
     * 2. Split the decoded string by "!"
     * 3. Take element [1] as RSA public key XML
     * 4. Convert RSA public key XML to PEM format
     * 5. RSA encrypt the request JSON string
     * 6. Return base64 encoded encrypted string
     *
     * @param merchantEncryptionKey the base64 encoded encryption key
     * @param plaintext             the plaintext data to encrypt
     * @return the base64 encoded encrypted data
     */
    public static String encryptPayload(String merchantEncryptionKey, String plaintext) {
        if (merchantEncryptionKey == null || merchantEncryptionKey.isBlank()) {
            throw new EpayClubClientException("Merchant encryption key is required for encryption");
        }

        try {
            // Step 1: Base64 decode the merchantEncryptionKey
            String decodedKey = new String(Base64.getDecoder().decode(merchantEncryptionKey));

            // Step 2: Split by "!"
            String[] parts = decodedKey.split("!");
            if (parts.length < 2) {
                throw new EpayClubClientException(
                        "Invalid encryption key format: expected '!' delimiter");
            }

            // Step 3: Take element [1] as RSA public key XML
            String rsaXml = parts[1];

            // Step 4 & 5: Convert XML to PublicKey and encrypt
            PublicKey publicKey = xmlToPublicKey(rsaXml);
            byte[] encryptedBytes = rsaEncrypt(publicKey, plaintext.getBytes());

            // Step 6: Return base64 encoded
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (EpayClubClientException e) {
            throw e;
        } catch (Exception e) {
            throw new EpayClubClientException("Encryption failed: " + e.getMessage(), e);
        }
    }

    /**
     * Converts an XML RSA public key to PEM format.
     *
     * @param xml the XML RSA public key
     * @return the PEM formatted public key
     */
    public static String xmlRsaPublicKeyToPem(String xml) {
        try {
            PublicKey publicKey = xmlToPublicKey(xml);
            byte[] encoded = publicKey.getEncoded();
            String base64 = Base64.getEncoder().encodeToString(encoded);

            StringBuilder pem = new StringBuilder();
            pem.append("-----BEGIN PUBLIC KEY-----\n");
            // Wrap at 64 characters
            for (int i = 0; i < base64.length(); i += 64) {
                pem.append(base64, i, Math.min(i + 64, base64.length()));
                pem.append("\n");
            }
            pem.append("-----END PUBLIC KEY-----");
            return pem.toString();
        } catch (Exception e) {
            throw new EpayClubClientException("Failed to convert XML to PEM: " + e.getMessage(), e);
        }
    }

    /**
     * RSA encrypts data and returns base64 encoded result.
     *
     * @param pemPublicKey the PEM formatted public key
     * @param data         the data to encrypt
     * @return the base64 encoded encrypted data
     */
    public static String rsaEncryptToBase64(String pemPublicKey, String data) {
        try {
            PublicKey publicKey = pemToPublicKey(pemPublicKey);
            byte[] encryptedBytes = rsaEncrypt(publicKey, data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new EpayClubClientException("RSA encryption failed: " + e.getMessage(), e);
        }
    }

    /**
     * Parses an XML RSA public key and creates a PublicKey object.
     */
    private static PublicKey xmlToPublicKey(String xml) throws Exception {
        // Extract Modulus and Exponent from XML
        Matcher modulusMatcher = MODULUS_PATTERN.matcher(xml);
        Matcher exponentMatcher = EXPONENT_PATTERN.matcher(xml);

        if (!modulusMatcher.find() || !exponentMatcher.find()) {
            throw new EpayClubClientException(
                    "Invalid RSA XML: missing Modulus or Exponent element");
        }

        String modulusBase64 = modulusMatcher.group(1);
        String exponentBase64 = exponentMatcher.group(1);

        // Decode from base64 and convert to BigInteger
        byte[] modulusBytes = Base64.getDecoder().decode(modulusBase64);
        byte[] exponentBytes = Base64.getDecoder().decode(exponentBase64);

        BigInteger modulus = new BigInteger(1, modulusBytes);
        BigInteger exponent = new BigInteger(1, exponentBytes);

        // Create RSA public key spec
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, exponent);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * Parses a PEM formatted public key.
     */
    private static PublicKey pemToPublicKey(String pem) throws Exception {
        String pemContent = pem
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");

        byte[] keyBytes = Base64.getDecoder().decode(pemContent);
        java.security.spec.X509EncodedKeySpec spec = new java.security.spec.X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(spec);
    }

    /**
     * Performs RSA encryption with PKCS1 padding.
     */
    private static byte[] rsaEncrypt(PublicKey publicKey, byte[] data) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }
}
