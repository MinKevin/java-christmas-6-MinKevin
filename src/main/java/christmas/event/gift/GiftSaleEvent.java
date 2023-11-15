package christmas.event.gift;

import christmas.domain.Bill;

public class GiftSaleEvent implements GiftEventCommon {
    @Override
    public void applyEvent(Bill bill, GiftEventInfo giftEventInfo) {

        if (bill.calculateOriginalPrice() >= giftEventInfo.getCriteriaOfDiscountPrice()){
            bill.addGiftDetail(giftEventInfo);
        }
    }
}
