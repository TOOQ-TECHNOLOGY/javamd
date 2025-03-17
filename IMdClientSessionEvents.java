package com.tooq.md.sbe;

import Tooq.Tech.MD.BinaryProtocol.Client.*;

public interface IMdClientSessionEvents {

    void onConnecting(IMdClientSession session);
    void onConnected(IMdClientSession session);
    void onDisconnecting(IMdClientSession session);
    void onDisconnected(IMdClientSession session);
    void onError(IMdClientSession session, Exception e);

    void onEmptyBookEventDataReceived(IMdClientSession session, EmptyBookEventData data);
    void onTopOfBookEventDataReceived(IMdClientSession session, TopOfBookEventData data);
    void onFullMarketByOrderSnapshotEventDataReceived(IMdClientSession session, FullMarketByOrderSnapshotEventData data);

    void onIncrementalUpdateEventDataReceived(IMdClientSession session, IncrementalUpdateEventData data);

    void onInstrumentSnapshotEventDataReceived(IMdClientSession session, InstrumentSnapshotEventData data);

    void onAuctionEventDataReceived(IMdClientSession session, AuctionEventData data);

    void onSecurityStatusEventDataReceived(IMdClientSession session, SecurityStatusEventData data);

    void onSecurityGroupPhaseEventDataReceived(IMdClientSession session, SecurityGroupPhaseEventData data);

    void onSecurityDefinitionEventDataReceived(IMdClientSession session, SecurityDefinitionEventData data);

    void onNewsEventDataReceived(IMdClientSession session, NewsEventData data);

    void onChannelResetEventDataReceived(IMdClientSession session, ChannelResetEventData data);

    void onOpeningPriceEventDataReceived(IMdClientSession session, OpeningPriceEventData data);

    void onTheoreticalOpeningPriceEventDataReceived(IMdClientSession session, TheoreticalOpeningPriceEventData data);

    void onClosingPriceEventDataReceived(IMdClientSession session, ClosingPriceEventData data);

    void onAuctionImbalanceEventDataReceived(IMdClientSession session, AuctionImbalanceEventData data);

    void onPriceBandEventDataReceived(IMdClientSession session, PriceBandEventData data);

    void onQuantityBandEventDataReceived(IMdClientSession session, QuantityBandEventData data);

    void onHighPriceEventDataReceived(IMdClientSession session, HighPriceEventData data);

    void onLowPriceEventDataReceived(IMdClientSession session, LowPriceEventData data);

    void onLastTradePriceEventDataReceived(IMdClientSession session, LastTradePriceEventData data);

    void onTradeEventDataReceived(IMdClientSession session, TradeEventData data);

    void onForwardTradeEventDataReceived(IMdClientSession session, ForwardTradeEventData data);

    void onExecutionSummaryEventDataReceived(IMdClientSession session, ExecutionSummaryEventData data);

    void onExecutionStatisticsEventDataReceived(IMdClientSession session, ExecutionStatisticsEventData data);

    void onTradeBustEventDataReceived(IMdClientSession session, TradeBustEventData data);

    void onTradeWithAggressorEventDataReceived(IMdClientSession session, TradeWithAggressorEventData data);

    void onSettlementPriceEventDataReceived(IMdClientSession session, SettlementPriceEventData data);

    void onOpenInterestEventDataReceived(IMdClientSession session, OpenInterestEventData data);

    void onL2MarketByPriceSnapshotEventDataReceived(IMdClientSession session, L2MarketByPriceSnapshotEventData data);
    void onL5MarketByPriceSnapshotEventDataReceived(IMdClientSession session, L5MarketByPriceSnapshotEventData data);

}
