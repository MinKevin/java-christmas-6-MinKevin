package christmas.event.gift;

import java.time.LocalDate;

public enum GiftEventInfo {
    GIFT(
            new GiftSaleEvent(),
            LocalDate.of(LocalDate.now().getYear(), 12, 1),
            LocalDate.of(LocalDate.now().getYear(), 12, 31),
            "샴페인",
            1,
            25_000,
            120_000
    );

    private final Object eventFactory;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String giftName;
    private final int giftQuantity;
    private final int giftPrice;
    private final int criteriaOfDiscountPrice;

    GiftEventInfo(Object eventFactory, LocalDate startDate, LocalDate endDate, String giftName, int giftQuantity, int giftPrice) {
        this.eventFactory = eventFactory;
        this.startDate = startDate;
        this.endDate = endDate;
        this.giftName = giftName;
        this.giftQuantity = giftQuantity;
        this.giftPrice = giftPrice;
        this.criteriaOfDiscountPrice = 0;
    }

    GiftEventInfo(Object eventFactory, LocalDate startDate, LocalDate endDate, String giftName, int giftQuantity, int giftPrice, int criteriaOfDiscountPrice) {
        this.eventFactory = eventFactory;
        this.startDate = startDate;
        this.endDate = endDate;
        this.giftName = giftName;
        this.giftQuantity = giftQuantity;
        this.giftPrice = giftPrice;
        this.criteriaOfDiscountPrice = criteriaOfDiscountPrice;
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

    public String getGiftName() {
        return giftName;
    }

    public int getGiftQuantity() {
        return giftQuantity;
    }

    public int getGiftPrice() {
        return giftPrice;
    }

    public int getCriteriaOfDiscountPrice() {
        return criteriaOfDiscountPrice;
    }
}
