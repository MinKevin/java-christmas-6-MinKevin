package christmas.event.sale;

import christmas.domain.Bill;

import java.time.LocalDate;
import java.time.Period;

public class ChristmasDDaySaleEvent implements SaleEventCommon {
    @Override
    public void applyEvent(Bill bill, SaleEventInfo saleEventInfo) {
        Period period = Period.between(LocalDate.of(LocalDate.now().getYear(), 12, 1), bill.getVisitDate());
        int additionalDiscountPrice = saleEventInfo.getStandardOfDiscountPrice1() + saleEventInfo.getStandardOfDiscountPrice2() * period.getDays();
        bill.addSaleDetail(saleEventInfo,additionalDiscountPrice);
    }
}
