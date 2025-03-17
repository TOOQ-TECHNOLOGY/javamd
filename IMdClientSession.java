package com.tooq.md.sbe;

import Tooq.Tech.MD.BinaryProtocol.Client.*;
import Tooq.Tech.MD.BinaryProtocol.Client.SubscriptionType;

import java.util.ArrayList;
import java.util.UUID;

public interface IMdClientSession {

    /**
     * Check if the client session is currently connected.
     *
     * @return true if the session is connected; false otherwise.
     */
    boolean isConnected();

    /**
     * Retrieves the unique identifier of the current session.
     *
     * @return the UUID representing the session's unique identifier.
     */
    UUID getSessionId();

    /**
     * Registers a callback object to handle session-related events. The provided callback
     * instance will receive notifications such as connection status changes or
     * market data updates based on the events defined in {@link IMdClientSessionEvents}.
     *
     * @param callback the instance of {@link IMdClientSessionEvents} to be registered as the callback
     * @return 0 if the callback was registered successfully; -1 if an error occurred during registration
     */
    int registerCallback(IMdClientSessionEvents callback);


    /**
     * Establishes a connection for the client session.
     * 
     * Before calling this method, the callbacks must be registered via {@link #registerCallback(IMdClientSessionEvents)}
     * to handle session events and errors that may eventually happen.
     *
     * @return 1 if the connection was successfully established; -1 if the connection failed
     */
    int connect();

    /**
     * Terminates the client session, stopping any active threads or processes associated
     * with the session and disconnecting from the server. This method ensures that the session
     * transitions to a non-running, disconnected state.
     *
     * @return 0 if the session was successfully disconnected; -1 if the disconnection process encountered an error.
     */
    int disconnect();

    /**
     * Request the security list from the connected feed handler instance. After this method is called, the
     * response will be cached internally and can be fetched using the {@link #getCachedSecurityList()} method
     *
     * @param timeoutInMs Time limit after which the Request will be considered lost and return with an error..
     * @return {@link GetSecurityListResponse}, a type encapsulating dictionary with key of type
     * {@link InstrumentId} and value of type
     * {@link SecurityDefinitionEventData}
     * @throws UnsupportedOperationException when the method is called with a disconnected session
     * @throws IllegalStateException when the method is called but no callbacks were registered for the session
     */
    GetSecurityListResponse getSecurityList(int timeoutInMs)
    throws UnsupportedOperationException,
           IllegalStateException;

    /**
     * This method will return the cache of the initial getSecurityList request
     *
     * @return {@link GetSecurityListResponse}, a type encapsulating dictionary with key of type
     * {@link InstrumentId} and value of type
     * {@link SecurityDefinitionEventData}
     * @throws UnsupportedOperationException when the call to <CODE>getCachedSecurityList</CODE> is made before having
     *                                       called {@link #getSecurityList(int timeoutInMs)}
     */
    GetSecurityListResponse getCachedSecurityList() throws UnsupportedOperationException;

    /**
     * Request a list of brokers from the connected feed handler instance
     *
     * @param exchangeId  The exchange id. Use "BVMF" for B3.
     * @param timeoutInMs Time limit after which the Request will be considered lost and return with an error..
     * @return {@link GetBrokerListResponse}
     * @throws UnsupportedOperationException when the method is called with a disconnected session
     * @throws IllegalStateException when the method is called but no callbacks were registered for the session
     */
    GetBrokerListResponse getBrokerList(String exchangeId, int timeoutInMs)
    throws UnsupportedOperationException,
           IllegalStateException;

    /**
     * Request a subscription to a security to receive updates.
     *
     * @param instrumentId     The instrument identifier object as defined in the SecurityDefinitionEventData
     * @param subscriptionType A bitwise flag that configures the subscription type. Subscriptions can be either
     *                         Realtime or Conflated. See {@}SubscriptionType
     * @param rateInMs         The rate upon which data will be sent (only valid for SubscriptionType.Conflated
     *                         subscriptions)
     * @param timeoutInMs      Time limit after which the Request will be considered lost and return with an error..
     * @return {@link RequestSubscriptionResponse}
     * @throws UnsupportedOperationException when the method is called with a disconnected session
     * @throws IllegalStateException when the method is called but no callbacks were registered for the session
     */
    RequestSubscriptionResponse requestSubscription(InstrumentId instrumentId,
                                                    SubscriptionType subscriptionType,
                                                    Integer rateInMs,
                                                    int timeoutInMs)
    throws UnsupportedOperationException,
           IllegalStateException;

    /**
     * Subscribe to market news updates from the feed handler instance.
     *
     * @param exchangeId  The exchange id. Use "BVMF" for B3.
     * @param timeoutInMs Time limit after which the Request will be considered lost and return with an error.
     * @return {@link RequestNewsSubscriptionResponse}
     * @throws UnsupportedOperationException when the method is called with a disconnected session
     * @throws IllegalStateException when the method is called but no callbacks were registered for the session
     */
    RequestNewsSubscriptionResponse requestNewsSubscription(String exchangeId, int timeoutInMs)
    throws UnsupportedOperationException,
           IllegalStateException;

    /**
     * Subscribe to auction updates from the feed handler instance. This subscription will raise events with
     * AuctionEventData objects whenever any security from the feed handler
     * enters/leaves auction state. Expect a large number of events during market open or market close.
     *
     * @param exchangeId  The exchange id. Use "BVMF" for B3.
     * @param timeoutInMs Time limit after which the Request will be considered lost and return with an error.
     * @return {@link RequestAuctionUpdatesSubscriptionResponse}
     * @throws UnsupportedOperationException when the method is called with a disconnected session
     * @throws IllegalStateException when the method is called but no callbacks were registered for the session
     */
    RequestAuctionUpdatesSubscriptionResponse requestAuctionUpdatesSubscription(String exchangeId, int timeoutInMs)
    throws UnsupportedOperationException, IllegalStateException;

    /**
     * Cancel a subscription.
     *
     * @param subscriptionId The id of the original subscription to be cancelled.
     * @param timeoutInMs    Time limit after which the Request will be considered lost and return with an error
     * @return {@link RequestCancelSubscriptionResponse}
     * @throws UnsupportedOperationException when the method is called with a disconnected session
     * @throws IllegalStateException when the method is called but no callbacks were registered for the session
     */
    RequestCancelSubscriptionResponse requestCancelSubscription(UUID subscriptionId, int timeoutInMs)
    throws UnsupportedOperationException,
           IllegalStateException;

    /**
     * Change the subscription type of existing subscription. Since the subscription type is a bitwise flag, flags
     * Be aware that invalid flag combinations can cause an error and the subscription will not be changed. Please
     * check the official docs for the possible flags.
     *
     * @param subscriptionId   The id of the subscription
     * @param subscriptionType A bitwise flag that configures the subscription type. Subscriptions can be either
     *                         Realtime or Conflated
     * @param changeType       The type of change to be applied to the subscription: AddFlag or RemoveFlag
     * @param timeoutInMs      Time limit after which the Request will be considered lost and return with an error
     * @return {@link RequestChangeSubscriptionResponse}
     * @throws UnsupportedOperationException when the method is called with a disconnected session
     * @throws IllegalStateException when the method is called but no callbacks were registered for the session
     */
    RequestChangeSubscriptionResponse requestChangeSubscription(UUID subscriptionId,
                                                                SubscriptionType subscriptionType,
                                                                ChangeType changeType,
                                                                int timeoutInMs)
    throws UnsupportedOperationException,
           IllegalStateException;

    /**
     * Initial request that will authenticate the client. Only after a successful login the client will be able to
     * request and receive data updates. The credentials should be defined in the <Code>SessionConfiguration</Code>
     * object
     *
     * @param timeoutInMs Time limit after which the Request will be considered lost and return with an error
     * @return {@link LoginResponse}
     * @throws UnsupportedOperationException when the method is called with a disconnected session
     * @throws IllegalStateException when the method is called but no callbacks were registered for the session
     */
    LoginResponse login(int timeoutInMs);

    /**
     * Gets an instant full orderbook snapshot from a given instrument.
     *
     * @param instrumentId The instrument identifier object as defined in the SecurityDefinitionEventData
     * @param timeoutInMs  Time limit after which the Request will be considered lost and return with an error
     * @return {@link GetOrderbookSnapshotResponse}
     * @throws UnsupportedOperationException when the method is called with a disconnected session
     * @throws IllegalStateException when the method is called but no callbacks were registered for the session
     */
    GetOrderbookSnapshotResponse getOrderbookSnapshot(InstrumentId instrumentId, int timeoutInMs)
    throws UnsupportedOperationException,
           IllegalStateException;

    /**
     * Sends a heartbeat to server to keep connection alive.
     *
     * @param timeoutInMs Time limit after which the Request will be considered lost and return with an error
     * @return {@link HeartbeatResponse}
     * @throws UnsupportedOperationException when the method is called with a disconnected session
     * @throws IllegalStateException when the method is called but no callbacks were registered for the session
     */
    HeartbeatResponse sendHeartbeat(int timeoutInMs)
    throws UnsupportedOperationException,
           IllegalStateException;

    /**
     * Request a subscription to multiple securities at the same time.
     *
     * This method should be used when the user is interested in subscribing to several instruments at once. Since
     * this library encapsulates market data within TCP, it is not recommended to subscribe to the entire security
     * list of a channel since this will cause queue build up both on the subscribing side (client) and the publishing
     * side (server) due to the amount of traffic.
     *
     * @param securityIds      A list of security ids to subscribe
     * @param subscriptionType A bitwise flag that configures the subscription type. Subscriptions can be either
     *                         Realtime or Conflated
     * @param rateInMs         The rate upon which data will be sent (only valid for SubscriptionType.Conflated
     *                         subscriptions)
     * @param timeoutInMs Time limit after which the Request will be considered lost and return with an error
     * @return {@link RequestMultipleSubscriptionResponse}
     * @throws UnsupportedOperationException when the method is called with a disconnected session
     * @throws IllegalStateException when the method is called but no callbacks were registered for the session
     */
    RequestMultipleSubscriptionResponse requestMultipleSubscription(ArrayList<Long> securityIds,
                                                                    SubscriptionType subscriptionType,
                                                                    Integer rateInMs, int timeoutInMs)
    throws UnsupportedOperationException,
           IllegalStateException;

    /**
     * Request the top of book data for a list of securities
     *
     * @param securityIds List of securityIds
     * @param timeoutInMs Time limit after which the Request will be considered lost and return with an error
     * @return {@link GetTopOfBookResponse}
     * @throws UnsupportedOperationException when the method is called with a disconnected session
     * @throws IllegalStateException when the method is called but no callbacks were registered for the session
     */
    GetTopOfBookResponse getTopOfBook(ArrayList<Long> securityIds, int timeoutInMs)
    throws UnsupportedOperationException,
           IllegalStateException;

    /**
     * Request the last price for a list of securities
     *
     * @param securityIds List of securityIds
     * @param timeoutInMs Time limit after which the Request will be considered lost and return with an error
     * @return {@link GetLastPriceResponse}
     * @throws UnsupportedOperationException when the method is called with a disconnected session
     * @throws IllegalStateException when the method is called but no callbacks were registered for the session
     */
    GetLastPriceResponse getLastPrice(ArrayList<Long> securityIds, int timeoutInMs)
    throws UnsupportedOperationException,
           IllegalStateException;
}
