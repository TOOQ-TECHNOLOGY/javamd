import Tooq.Tech.MD.BinaryProtocol.Client.*;
import com.tooq.md.sbe.IMdClientSession;
import com.tooq.md.sbe.IMdClientSessionEvents;
import com.tooq.md.sbe.MdClientSession;
import com.tooq.md.sbe.SessionConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {

        MdClientSession session = new MdClientSession(new SessionConfiguration(
                "USER",
                "P",
                "IP",
                10278,
                false,
                8192,
                8192,
                30
        ));

        session.registerCallback(new IMdClientSessionEvents() {
            @Override
            public void onConnecting(IMdClientSession iMdClientSession) {
                System.out.println("onConnecting");
            }

            @Override
            public void onConnected(IMdClientSession iMdClientSession) {
                System.out.println("onConnected");
            }

            @Override
            public void onDisconnecting(IMdClientSession iMdClientSession) {
                System.out.println("onDisconnecting");
            }

            @Override
            public void onDisconnected(IMdClientSession iMdClientSession) {
                System.out.println("onDisconnected");
            }

            @Override
            public void onError(IMdClientSession iMdClientSession, Exception e) {
                System.out.println("onError");
            }

            @Override
            public void onEmptyBookEventDataReceived(IMdClientSession iMdClientSession,
                                                     EmptyBookEventData emptyBookEventData) {
                System.out.println("onEmptyBookEventDataReceived");
            }

            @Override
            public void onTopOfBookEventDataReceived(IMdClientSession iMdClientSession,
                                                     TopOfBookEventData topOfBookEventData) {
                System.out.println(topOfBookEventData);
            }

            @Override
            public void onFullMarketByOrderSnapshotEventDataReceived
                    (IMdClientSession iMdClientSession,
                     FullMarketByOrderSnapshotEventData fullMarketByOrderSnapshotEventData) {
                System.out.println(fullMarketByOrderSnapshotEventData);
            }

            @Override
            public void onIncrementalUpdateEventDataReceived
                    (IMdClientSession iMdClientSession,
                     IncrementalUpdateEventData incrementalUpdateEventData) {
                System.out.println(incrementalUpdateEventData);
            }

            @Override
            public void onInstrumentSnapshotEventDataReceived
                    (IMdClientSession iMdClientSession,
                     InstrumentSnapshotEventData instrumentSnapshotEventData) {
                System.out.println(instrumentSnapshotEventData);
            }

            @Override
            public void onAuctionEventDataReceived(IMdClientSession iMdClientSession,
                                                   AuctionEventData auctionEventData) {
                System.out.println(auctionEventData);
            }

            @Override
            public void onSecurityStatusEventDataReceived(IMdClientSession iMdClientSession,
                                                          SecurityStatusEventData securityStatusEventData) {
                System.out.println(securityStatusEventData);
            }

            @Override
            public void onSecurityGroupPhaseEventDataReceived(IMdClientSession iMdClientSession,
                                                              SecurityGroupPhaseEventData securityGroupPhaseEventData) {
                System.out.println(securityGroupPhaseEventData);
            }

            @Override
            public void onSecurityDefinitionEventDataReceived(IMdClientSession iMdClientSession,
                                                              SecurityDefinitionEventData securityDefinitionEventData) {
                System.out.println(securityDefinitionEventData);
            }

            @Override
            public void onNewsEventDataReceived(IMdClientSession iMdClientSession, NewsEventData newsEventData) {
                System.out.println(newsEventData);
            }

            @Override
            public void onChannelResetEventDataReceived(IMdClientSession iMdClientSession,
                                                        ChannelResetEventData channelResetEventData) {
                System.out.println(channelResetEventData);
            }

            @Override
            public void onOpeningPriceEventDataReceived(IMdClientSession iMdClientSession,
                                                        OpeningPriceEventData openingPriceEventData) {
                System.out.println(openingPriceEventData);
            }

            @Override
            public void onTheoreticalOpeningPriceEventDataReceived
                    (IMdClientSession iMdClientSession,
                     TheoreticalOpeningPriceEventData theoreticalOpeningPriceEventData) {
                System.out.println(theoreticalOpeningPriceEventData);
            }

            @Override
            public void onClosingPriceEventDataReceived(IMdClientSession iMdClientSession,
                                                        ClosingPriceEventData closingPriceEventData) {
                System.out.println(closingPriceEventData);
            }

            @Override
            public void onAuctionImbalanceEventDataReceived(IMdClientSession iMdClientSession,
                                                            AuctionImbalanceEventData auctionImbalanceEventData) {
                System.out.println(auctionImbalanceEventData);
            }

            @Override
            public void onPriceBandEventDataReceived(IMdClientSession iMdClientSession,
                                                     PriceBandEventData priceBandEventData) {
                System.out.println(priceBandEventData);
            }

            @Override
            public void onQuantityBandEventDataReceived(IMdClientSession iMdClientSession,
                                                        QuantityBandEventData quantityBandEventData) {
                System.out.println(quantityBandEventData);
            }

            @Override
            public void onHighPriceEventDataReceived(IMdClientSession iMdClientSession,
                                                     HighPriceEventData highPriceEventData) {
                System.out.println(highPriceEventData);
            }

            @Override
            public void onLowPriceEventDataReceived(IMdClientSession iMdClientSession,
                                                    LowPriceEventData lowPriceEventData) {
                System.out.println(lowPriceEventData);
            }

            @Override
            public void onLastTradePriceEventDataReceived(IMdClientSession iMdClientSession,
                                                          LastTradePriceEventData lastTradePriceEventData) {
                System.out.println(lastTradePriceEventData);
            }

            @Override
            public void onTradeEventDataReceived(IMdClientSession iMdClientSession, TradeEventData tradeEventData) {
                System.out.println(tradeEventData);
            }

            @Override
            public void onForwardTradeEventDataReceived(IMdClientSession iMdClientSession,
                                                        ForwardTradeEventData forwardTradeEventData) {
                System.out.println(forwardTradeEventData);
            }

            @Override
            public void onExecutionSummaryEventDataReceived(IMdClientSession iMdClientSession,
                                                            ExecutionSummaryEventData executionSummaryEventData) {
                System.out.println(executionSummaryEventData);
            }

            @Override
            public void onExecutionStatisticsEventDataReceived
                    (IMdClientSession iMdClientSession,
                     ExecutionStatisticsEventData executionStatisticsEventData) {
                System.out.println(executionStatisticsEventData);
            }

            @Override
            public void onTradeBustEventDataReceived(IMdClientSession iMdClientSession,
                                                     TradeBustEventData tradeBustEventData) {
                System.out.println(tradeBustEventData);
            }

            @Override
            public void onTradeWithAggressorEventDataReceived(IMdClientSession iMdClientSession,
                                                              TradeWithAggressorEventData tradeWithAggressorEventData) {
                System.out.println(tradeWithAggressorEventData);
            }

            @Override
            public void onSettlementPriceEventDataReceived(IMdClientSession iMdClientSession,
                                                           SettlementPriceEventData settlementPriceEventData) {
                System.out.println(settlementPriceEventData);
            }

            @Override
            public void onOpenInterestEventDataReceived(IMdClientSession iMdClientSession,
                                                        OpenInterestEventData openInterestEventData) {
                System.out.println(openInterestEventData);
            }

            @Override
            public void onL2MarketByPriceSnapshotEventDataReceived
                    (IMdClientSession iMdClientSession,
                     L2MarketByPriceSnapshotEventData l2MarketByPriceSnapshotEventData) {
                System.out.println(l2MarketByPriceSnapshotEventData);
            }

            @Override
            public void onL5MarketByPriceSnapshotEventDataReceived
                    (IMdClientSession iMdClientSession,
                     L5MarketByPriceSnapshotEventData l5MarketByPriceSnapshotEventData) {
                System.out.println(l5MarketByPriceSnapshotEventData);
            }
        });

        if (session.connect() < 0)
        {
            throw new RuntimeException("Failed to connect to server");
        }

        int requestTimeout = 30_000;
        LoginResponse loginResponse = session.login(requestTimeout);

        if (loginResponse.isAuthenticated == false) {
            throw new RuntimeException("Failed to login. Reason: " + loginResponse.status);
        }

        GetSecurityListResponse securityList = session.getSecurityList(requestTimeout);
        //System.out.println(securityList);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            if ("exit".equalsIgnoreCase(input)) {
                System.out.println("Closing connection...");
                session.disconnect();
                break;
            }

            SubscriptionType subscriptionType = SubscriptionType.Real_Time
                    .setFlags(SubscriptionType.Top_Of_Book);

            InstrumentId instrument = null;
            Optional<InstrumentId> instrumentId = securityList.securities.entrySet()
                .stream()
                .filter(s -> input.equalsIgnoreCase(s.getValue().symbol))
                .map(s->s.getKey()).findFirst();

            if (!instrumentId.isPresent()) {
                System.out.println("No instrument found");
                continue;
            }

            instrument = instrumentId.get();

            RequestSubscriptionResponse resp = session.requestSubscription(instrument, subscriptionType, 0,
                                                                           requestTimeout);

            if (null != resp.error) {
                throw new RuntimeException("Error subscribing: " + resp.error);
            }
        }

        scanner.close();

        System.out.println("RPC Client terminated.");
    }
}
