package com.epayclub.sdk.services;

import com.epayclub.sdk.http.HttpExecutor;
import com.epayclub.sdk.http.RequestOptions;
import com.epayclub.sdk.models.paymentlinks.*;

/**
 * Service for payment link-related API operations.
 * Provides methods for creating, managing, and querying payment links.
 */
public class PaymentLinksService {

    private final HttpExecutor httpExecutor;

    /**
     * Constructs a new PaymentLinksService.
     *
     * @param httpExecutor the HTTP executor for making API requests
     */
    public PaymentLinksService(HttpExecutor httpExecutor) {
        this.httpExecutor = httpExecutor;
    }

    /**
     * Creates a new payment link.
     *
     * @param request the link creation request
     * @return the link creation response
     */
    public CreateLinkResponse create(CreateLinkRequest request) {
        return create(request, RequestOptions.defaults());
    }

    /**
     * Creates a new payment link with custom request options.
     *
     * @param request the link creation request
     * @param options request options
     * @return the link creation response
     */
    public CreateLinkResponse create(CreateLinkRequest request, RequestOptions options) {
        request.validate();
        return httpExecutor.post("/checkout/links/create", request, CreateLinkResponse.class, options);
    }

    /**
     * Lists payment links.
     *
     * @param request the fetch links request (maybe null for defaults)
     * @return the fetch links response
     */
    public FetchLinksResponse list(FetchLinksRequest request) {
        return list(request, RequestOptions.defaults());
    }

    /**
     * Lists payment links with custom request options.
     *
     * @param request the fetch links request (maybe null for defaults)
     * @param options request options
     * @return the fetch links response
     */
    public FetchLinksResponse list(FetchLinksRequest request, RequestOptions options) {
        String path = "/checkout/links/all";
//        if (request != null) {
//            String queryString = request.toQueryString();
//            if (!queryString.isEmpty()) {
//                path += "?" + queryString;
//            }
//        }
        return httpExecutor.get(path, FetchLinksResponse.class, options);
    }

    /**
     * Gets the available payment link types.
     *
     * @return the link types response
     */
    public LinkTypesResponse types() {
        return types(RequestOptions.defaults());
    }

    /**
     * Gets the available payment link types with custom request options.
     *
     * @param options request options
     * @return the link types response
     */
    public LinkTypesResponse types(RequestOptions options) {
        return httpExecutor.get("/checkout/links/types", LinkTypesResponse.class, options);
    }

    /**
     * Edits an existing payment link.
     *
     * @param request the edit link request
     * @return the edit link response
     */
    public EditLinkResponse edit(EditLinkRequest request) {
        return edit(request, RequestOptions.defaults());
    }

    /**
     * Edits an existing payment link with custom request options.
     *
     * @param request the edit link request
     * @param options request options
     * @return the edit link response
     */
    public EditLinkResponse edit(EditLinkRequest request, RequestOptions options) {
        request.validate();

        String path = "/checkout/links/" + request.getId() + "/edit";
        request.setId(null);
        return httpExecutor.patch(path,
                request, EditLinkResponse.class, options);
    }

    /**
     * Activates a payment link.
     *
     * @param request the activate link request
     * @return the activate link response
     */
    public ActivateLinkResponse activate(ActivateLinkRequest request) {
        return activate(request, RequestOptions.defaults());
    }

    /**
     * Activates a payment link with custom request options.
     *
     * @param request the activate link request
     * @param options request options
     * @return the activate link response
     */
    public ActivateLinkResponse activate(ActivateLinkRequest request, RequestOptions options) {
        request.validate();
        String path = "/checkout/links/" + request.getId() + "/status/activate";
        return httpExecutor.patch(path,
                null, ActivateLinkResponse.class, options);
    }

    /**
     * Deactivates a payment link.
     *
     * @param request the deactivate link request
     * @return the deactivate link response
     */
    public DeactivateLinkResponse deactivate(DeactivateLinkRequest request) {
        return deactivate(request, RequestOptions.defaults());
    }

    /**
     * Deactivates a payment link with custom request options.
     *
     * @param request the deactivate link request
     * @param options request options
     * @return the deactivate link response
     */
    public DeactivateLinkResponse deactivate(DeactivateLinkRequest request, RequestOptions options) {
        request.validate();

        return httpExecutor.patch("/checkout/links/" + request.getLinkId() + "/status/disable",
                null, DeactivateLinkResponse.class, options);
    }

    /**
     * Cancels recurring payments for a payment link.
     *
     * @param request the cancel recurring payments request
     * @return the cancel recurring payments response
     */
    public CancelRecurringPaymentsResponse cancelRecurringPayments(CancelRecurringPaymentsRequest request) {
        return cancelRecurringPayments(request, RequestOptions.defaults());
    }

    /**
     * Cancels recurring payments for a payment link with custom request options.
     *
     * @param request the cancel recurring payments request
     * @param options request options
     * @return the cancel recurring payments response
     */
    public CancelRecurringPaymentsResponse cancelRecurringPayments(CancelRecurringPaymentsRequest request,
                                                                    RequestOptions options) {
        request.validate();
        return httpExecutor.patch("/checkout/links/recurringpayment/" + request.getId() + "/cancel",
                request, CancelRecurringPaymentsResponse.class, options);
    }

    /**
     * Gets the available payment frequencies.
     *
     * @return the frequencies response
     */
    public FrequenciesResponse frequencies() {
        return frequencies(RequestOptions.defaults());
    }

    /**
     * Gets the available payment frequencies with custom request options.
     *
     * @param options request options
     * @return the frequencies response
     */
    public FrequenciesResponse frequencies(RequestOptions options) {
        return httpExecutor.get("/checkout/frequencies", FrequenciesResponse.class, options);
    }
}
