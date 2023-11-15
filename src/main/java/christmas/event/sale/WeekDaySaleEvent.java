package christmas.event.sale;

import christmas.database.Menu;
import christmas.database.MenuType;
import christmas.domain.Bill;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekDaySaleEvent implements SaleEventCommon {
    @Override
    public void applyEvent(Bill bill, SaleEventInfo saleEventInfo) {
        LocalDate visitDate = bill.getVisitDate();
        DayOfWeek dayOfWeek = visitDate.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY) {
            return;
        }
        int totalCount = Menu.countOrdersByMenuType(bill, MenuType.DESSERT);
        int additionalDiscountPrice = totalCount * saleEventInfo.getStandardOfDiscountPrice1();
        bill.addSaleDetail(saleEventInfo, additionalDiscountPrice);
    }
}
