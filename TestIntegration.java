import Tooq.Tech.MD.BinaryProtocol.Client.*;
import com.tooq.md.sbe.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class TestIntegration {
    private static final Logger logger = LoggerFactory.getLogger(TestIntegration.class);

    public static void main(String[] args) {
        IMdClientSession client = new MdClientSession();

        client.registerCallback(new IMdClientSessionEvents() {

            @Override
            public void onConnecting(IMdClientSession iMdClientSession) {
                logger.info("Connecting");
            }

            @Override
            public void onConnected(IMdClientSession iMdClientSession) {
                logger.info("Connected");
            }

            @Override
            public void onDisconnecting(IMdClientSession iMdClientSession) {
                logger.info("Disconnecting");
            }

            @Override
            public void onDisconnected(IMdClientSession iMdClientSession) {
                logger.info("Disconnected");
            }

            @Override
            public void onError(IMdClientSession iMdClientSession, Exception e) {
                logger.info("Error: {}", e.getMessage());
            }

            @Override
            public void onEmptyBookEventDataReceived(IMdClientSession iMdClientSession, EmptyBookEventData emptyBookEventData) {
                logger.info("Empty Book Event Data Received: {}", emptyBookEventData.toString());
            }

            @Override
            public void onTopOfBookEventDataReceived(IMdClientSession iMdClientSession, TopOfBookEventData topOfBookEventData) {
                logger.info("Top Of Book Event Data Received: {}", topOfBookEventData.toString());
            }

            @Override
            public void onFullMarketByOrderSnapshotEventDataReceived(IMdClientSession iMdClientSession, TopOfBookEventData topOfBookEventData) {
                logger.info("Full Market By Order Snapshot Event Data Received: {}", topOfBookEventData.toString());
            }

            @Override
            public void onIncrementalUpdateEventDataReceived(IMdClientSession iMdClientSession, IncrementalUpdateEventData incrementalUpdateEventData) {
                logger.info("Incremental Update Event Data Received: {}", incrementalUpdateEventData.toString());
            }

            @Override
            public void onInstrumentSnapshotEventDataReceived(IMdClientSession iMdClientSession, InstrumentSnapshotEventData instrumentSnapshotEventData) {
                logger.info("Instrument Snapshot Event Data Received: {}", instrumentSnapshotEventData.toString());
            }

            @Override
            public void onAuctionEventDataReceived(IMdClientSession iMdClientSession, AuctionEventData auctionEventData) {
                logger.info("Auction Event Data Received: {}", auctionEventData.toString());
            }

            @Override
            public void onSecurityStatusEventDataReceived(IMdClientSession iMdClientSession, SecurityStatusEventData securityStatusEventData) {
                logger.info("Security Status Event Data Received: {}", securityStatusEventData.toString());
            }

            @Override
            public void onSecurityGroupPhaseEventDataReceived(IMdClientSession iMdClientSession, SecurityGroupPhaseEventData securityGroupPhaseEventData) {
                logger.info("Security Group Phase Event Data Received: {}", securityGroupPhaseEventData.toString());
            }

            @Override
            public void onSecurityDefinitionEventDataReceived(IMdClientSession iMdClientSession, SecurityDefinitionEventData securityDefinitionEventData) {

            }

            @Override
            public void onNewsEventDataReceived(IMdClientSession iMdClientSession, NewsEventData newsEventData) {

            }

            @Override
            public void onChannelResetEventDataReceived(IMdClientSession iMdClientSession, ChannelResetEventData channelResetEventData) {

            }

            @Override
            public void onOpeningPriceEventDataReceived(IMdClientSession iMdClientSession, OpeningPriceEventData openingPriceEventData) {

            }

            @Override
            public void onTheoreticalOpeningPriceEventDataReceived(IMdClientSession iMdClientSession, TheoreticalOpeningPriceEventData theoreticalOpeningPriceEventData) {

            }

            @Override
            public void onClosingPriceEventDataReceived(IMdClientSession iMdClientSession, ClosingPriceEventData closingPriceEventData) {

            }

            @Override
            public void onAuctionImbalanceEventDataReceived(IMdClientSession iMdClientSession, AuctionImbalanceEventData auctionImbalanceEventData) {

            }

            @Override
            public void onPriceBandEventDataReceived(IMdClientSession iMdClientSession, PriceBandEventData priceBandEventData) {

            }

            @Override
            public void onQuantityBandEventDataReceived(IMdClientSession iMdClientSession, QuantityBandEventData quantityBandEventData) {

            }

            @Override
            public void onHighPriceEventDataReceived(IMdClientSession iMdClientSession, HighPriceEventData highPriceEventData) {

            }

            @Override
            public void onLowPriceEventDataReceived(IMdClientSession iMdClientSession, LowPriceEventData lowPriceEventData) {

            }

            @Override
            public void onLastTradePriceEventDataReceived(IMdClientSession iMdClientSession, LastTradePriceEventData lastTradePriceEventData) {

            }

            @Override
            public void onTradeEventDataReceived(IMdClientSession iMdClientSession, TradeEventData tradeEventData) {

            }

            @Override
            public void onForwardTradeEventDataReceived(IMdClientSession iMdClientSession, ForwardTradeEventData forwardTradeEventData) {

            }

            @Override
            public void onExecutionSummaryEventDataReceived(IMdClientSession iMdClientSession, ExecutionSummaryEventData executionSummaryEventData) {

            }

            @Override
            public void onExecutionStatisticsEventDataReceived(IMdClientSession iMdClientSession, ExecutionStatisticsEventData executionStatisticsEventData) {

            }

            @Override
            public void onTradeBustEventDataReceived(IMdClientSession iMdClientSession, TradeBustEventData tradeBustEventData) {

            }

            @Override
            public void onTradeWithAggressorEventDataReceived(IMdClientSession iMdClientSession, TradeWithAggressorEventData tradeWithAggressorEventData) {

            }

            @Override
            public void onSettlementPriceEventDataReceived(IMdClientSession iMdClientSession, SettlementPriceEventData settlementPriceEventData) {

            }

            @Override
            public void onOpenInterestEventDataReceived(IMdClientSession iMdClientSession, OpenInterestEventData openInterestEventData) {

            }
        });

        try {
            client.initialize(new SessionConfiguration("127.0.0.1", 20010, false, 8192, 8192, 30));
            client.connect();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                logger.info("Shutdown initiated. Disconnecting client.");
                client.disconnect();
            }));

            UUID id = client.subscribe("teste", SubscriptionType.Top_Of_Book.setFlags(SubscriptionType.Real_Time));

            logger.info("subscription with id: {}", id);

            while (true) {
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        } finally {
            client.disconnect();
        }
    }
}
