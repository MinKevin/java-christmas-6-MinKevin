package christmas.event.sale;

import christmas.domain.Bill;

public interface SaleEventCommon {
    public void applyEvent(Bill bill, SaleEventInfo saleEventInfo);
}
