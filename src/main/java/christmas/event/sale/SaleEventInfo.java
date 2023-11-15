package christmas.event.sale;

import java.time.LocalDate;

public enum SaleEventInfo {
    CHRISTMAS_D_DAY_SALE(
            "크리스마스 디데이 할인",
            new ChristmasDDaySaleEvent(),
            LocalDate.of(LocalDate.now().getYear(), 12, 1),
            LocalDate.of(LocalDate.now().getYear(), 12, 31),
            1_000,
            100
    ),
    WEEK_DAY_SALE(
            "평일 할인",
            new WeekDaySaleEvent(),
            LocalDate.of(LocalDate.now().getYear(), 12, 1),
            LocalDate.of(LocalDate.now().getYear(), 12, 31),
            LocalDate.now().getYear()
    ),
    WEEKEND_SALE(
            "주말 할인",
            new WeekendSaleEvent(),
            LocalDate.of(LocalDate.now().getYear(), 12, 1),
            LocalDate.of(LocalDate.now().getYear(), 12, 31),
            LocalDate.now().getYear()
            ),
    STAR_SALE(
            "특별 할인",
            new StarSaleEvent(),
            LocalDate.of(LocalDate.now().getYear(), 12, 1),
            LocalDate.of(LocalDate.now().getYear(), 12, 31),
            1000
            );

    private final String eventName;
    private final Object eventFactory;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int standardOfDiscountPrice1;
    private final int standardOfDiscountPrice2;

    SaleEventInfo(String eventName, Object eventFactory, LocalDate startDate, LocalDate endDate, int standardOfDiscountPrice1) {
        this.eventName = eventName;
        this.eventFactory = eventFactory;
        this.startDate = startDate;
        this.endDate = endDate;
        this.standardOfDiscountPrice1 = standardOfDiscountPrice1;
        this.standardOfDiscountPrice2 = 0;
    }

    SaleEventInfo(String eventName, Object eventFactory, LocalDate startDate, LocalDate endDate, int standardOfDiscountPrice1, int standardOfDiscountPrice2) {
        this.eventName = eventName;
        this.eventFactory = eventFactory;
        this.startDate = startDate;
        this.endDate = endDate;
        this.standardOfDiscountPrice1 = standardOfDiscountPrice1;
        this.standardOfDiscountPrice2 = standardOfDiscountPrice2;
    }

    public String getEventName() {
        return eventName;
    }

    public Object getEventFactory() {
        return eventFactory;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getStandardOfDiscountPrice1() {
        return standardOfDiscountPrice1;
    }

    public int getStandardOfDiscountPrice2() {
        return standardOfDiscountPrice2;
    }
}
