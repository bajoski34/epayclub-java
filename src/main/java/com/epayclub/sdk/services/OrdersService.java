package com.epayclub.sdk.services;

import com.epayclub.sdk.http.HttpExecutor;
import com.epayclub.sdk.http.RequestOptions;
import com.epayclub.sdk.models.orders.*;

import java.io.Console;

/**
 * Service for order-related API operations.
 * Provides methods for creating orders, processing payments, and checking order status.
 */
public class OrdersService {

    private final HttpExecutor httpExecutor;

    /**
     * Constructs a new OrdersService.
     *
     * @param httpExecutor the HTTP executor for making API requests
     */
    public OrdersService(HttpExecutor httpExecutor) {
        this.httpExecutor = httpExecutor;
    }

    /**
     * Creates a new order.
     *
     * @param request the order creation request
     * @return the order creation response
     */
    public CreateOrderResponse create(CreateOrderRequest request) {
        return create(request, RequestOptions.defaults());
    }

    /**
     * Creates a new order with custom request options.
     *
     * @param request the order creation request
     * @param options request options
     * @return the order creation response
     */
    public CreateOrderResponse create(CreateOrderRequest request, RequestOptions options) {
        request.validate();

        options = getRequestOptions(options);

        return httpExecutor.post("/checkout/order/create", request, CreateOrderResponse.class, options);
    }

    /**
     * Calculates the fee for an order.
     *
     * @param request the fee calculation request
     * @return the fee response
     */
    public FeeResponse fee(FeeRequest request) {
        return fee(request, RequestOptions.defaults());
    }

    /**
     * Calculates the fee for an order with custom request options.
     *
     * @param request the fee calculation request
     * @param options request options
     * @return the fee response
     */
    public FeeResponse fee(FeeRequest request, RequestOptions options) {
        request.validate();

        options = getRequestOptions(options);

        return httpExecutor.post("/checkout/order/fee", request, FeeResponse.class, options);
    }

    private RequestOptions getRequestOptions(RequestOptions options) {
        if(options.getRetries() != null && options.getTimeoutSeconds() != null) {
            int currentRetry = options.getRetries();
            int currentTimeout = options.getTimeoutSeconds();
            options = RequestOptions.builder().encrypt(true).retries(currentRetry).timeout(currentTimeout).build();
        } else {
            options = RequestOptions.builder().encrypt(true).build();
        }
        return options;
    }

    /**
     * Processes payment for an order.
     *
     * @param request the payment request
     * @return the payment response
     */
    public PayOrderResponse pay(PayOrderRequest request) {
        return pay(request, RequestOptions.defaults());
    }

    /**
     * Processes payment for an order with custom request options.
     *
     * @param request the payment request
     * @param options request options
     * @return the payment response
     */
    public PayOrderResponse pay(PayOrderRequest request, RequestOptions options) {
        request.validate();

        options = getRequestOptions(options);

        return httpExecutor.post("/checkout/order/pay", request, PayOrderResponse.class, options);
    }

    /**
     * Gets the status of an order.
     *
     * @param request the status request
     * @return the order status response
     */
    public OrderStatusResponse status(OrderStatusRequest request) {
        return status(request, RequestOptions.defaults());
    }

    /**
     * Gets the status of an order with custom request options.
     *
     * @param request the status request
     * @param options request options
     * @return the order status response
     */
    public OrderStatusResponse status(OrderStatusRequest request, RequestOptions options) {
        request.validate();

        options = getRequestOptions(options);

        return httpExecutor.get("checkout/order/status",
                OrderStatusResponse.class, options);
    }

    /**
     * Verifies an order.
     *
     * @param request the verification request
     * @return the verification response
     */
    public VerifyOrderResponse verify(VerifyOrderRequest request) {
        return verify(request, RequestOptions.defaults());
    }

    /**
     * Verifies an order with custom request options.
     *
     * @param request the verification request
     * @param options request options
     * @return the verification response
     */
    public VerifyOrderResponse verify(VerifyOrderRequest request, RequestOptions options) {
        request.validate();

        options = getRequestOptions(options);

        return httpExecutor.get("/checkout/order/verify",
                VerifyOrderResponse.class, options);
    }

    /**
     * Gets the timeline for an order.
     *
     * @param request the timeline request
     * @return the timeline response
     */
    public OrderTimelineResponse timeline(OrderTimelineRequest request) {
        return timeline(request, RequestOptions.defaults());
    }

    /**
     * Gets the timeline for an order with custom request options.
     *
     * @param request the timeline request
     * @param options request options
     * @return the timeline response
     */
    public OrderTimelineResponse timeline(OrderTimelineRequest request, RequestOptions options) {
        request.validate();

        options = getRequestOptions(options);

        return httpExecutor.get("/order/event/track",
                OrderTimelineResponse.class, options);
    }
}
