package com.epayclub.sdk.services;

import com.epayclub.sdk.http.HttpExecutor;
import com.epayclub.sdk.http.RequestOptions;
import com.epayclub.sdk.models.paymentoperations.BankCodesResponse;

/**
 * Service for payment operations API.
 * Provides methods for retrieving bank codes and other payment-related data.
 */
public class PaymentOperationsService {

    private final HttpExecutor httpExecutor;

    /**
     * Constructs a new PaymentOperationsService.
     *
     * @param httpExecutor the HTTP executor for making API requests
     */
    public PaymentOperationsService(HttpExecutor httpExecutor) {
        this.httpExecutor = httpExecutor;
    }

    /**
     * Gets the list of available bank codes.
     *
     * @return the bank codes response
     */
    public BankCodesResponse bankCodes() {
        return bankCodes(RequestOptions.defaults());
    }

    /**
     * Gets the list of available bank codes with custom request options.
     *
     * @param options request options
     * @return the bank codes response
     */
    public BankCodesResponse bankCodes(RequestOptions options) {
        return httpExecutor.get("/checkout/banks", BankCodesResponse.class, options);
    }
}
