package christmas.event.gift;

import christmas.domain.Bill;

public interface GiftEventCommon {
    public void applyEvent(Bill bill, GiftEventInfo giftEventInfo);
}
