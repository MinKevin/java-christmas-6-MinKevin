package christmas.event.sale;

import christmas.event.StarMarker;
import christmas.domain.Bill;

public class StarSaleEvent implements SaleEventCommon {
    @Override
    public void applyEvent(Bill bill, SaleEventInfo saleEventInfo) {
        String visitDate = bill.getVisitDate().getMonth().toString() + "_" + bill.getVisitDate().getDayOfMonth();
        for (StarMarker starMarker : StarMarker.values()) {
            if (starMarker.toString().equals(visitDate)) {
                int additionalDiscountPrice = saleEventInfo.getStandardOfDiscountPrice1();
                bill.addSaleDetail(saleEventInfo, additionalDiscountPrice);
                return;
            }
        }
    }
}
