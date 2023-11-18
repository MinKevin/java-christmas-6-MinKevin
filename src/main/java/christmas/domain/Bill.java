package christmas.domain;

import christmas.database.Menu;
import christmas.event.badge.BadgeInfo;
import christmas.event.gift.GiftEventCommon;
import christmas.event.sale.SaleEventCommon;
import christmas.event.gift.GiftEventInfo;
import christmas.event.sale.SaleEventInfo;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;

import static christmas.view.InformMessage.*;

public class Bill {
    private static Bill singleton = new Bill();

    private final DecimalFormat decimalFormat = new DecimalFormat("###,###");

    private LocalDate visitDate;
    private Map<Menu, Integer> orders;
    private final Map<SaleEventInfo, Integer> saleDetails = new HashMap<>();
    private final List<GiftEventInfo> giftDetails = new ArrayList<>();
    private BadgeInfo badge;

    private Bill() {
    }

    public static Bill getInstance() {
        return singleton;
    }


    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public Map<Menu, Integer> getOrders() {
        return orders;
    }

    public void setOrders(Map<Menu, Integer> orders) {
        this.orders = orders;
    }


    public void addSaleDetail(SaleEventInfo saleEventInfo, int amount) {
        saleDetails.put(saleEventInfo, amount);
    }

    public void addGiftDetail(GiftEventInfo giftEventInfo) {
        giftDetails.add(giftEventInfo);
    }

    public void fillOutBill() {
        if (calculateOriginalPrice() >= 10000) {
            fillSaleDetails();

            fillGiftDetails();
        }

        fillBadge();
    }

    public int calculateOriginalPrice() {
        int originalPrice = 0;
        for (Map.Entry<Menu, Integer> order : orders.entrySet()) {
            originalPrice += order.getKey().getPrice() * order.getValue();
        }
        return originalPrice;
    }

    private void fillSaleDetails() {
        for (SaleEventInfo saleEventInfo : SaleEventInfo.values()) {
            if (visitDate.isAfter(saleEventInfo.getStartDate()) && visitDate.isBefore(saleEventInfo.getEndDate())
                    || visitDate.isEqual(saleEventInfo.getStartDate())
                    || visitDate.isEqual(saleEventInfo.getEndDate())) {
                ((SaleEventCommon) saleEventInfo.getEventFactory()).applyEvent(singleton, saleEventInfo);
            }
        }
    }

    private void fillGiftDetails() {
        for (GiftEventInfo giftEventInfo : GiftEventInfo.values()) {
            if (visitDate.isAfter(giftEventInfo.getStartDate()) && visitDate.isBefore(giftEventInfo.getEndDate())
                    || visitDate.isEqual(giftEventInfo.getStartDate())
                    || visitDate.isEqual(giftEventInfo.getEndDate())) {
                ((GiftEventCommon) giftEventInfo.getEventFactory()).applyEvent(singleton, giftEventInfo);
            }
        }
    }

    private void fillBadge() {
        int benefitPrice = getSaleDetailsTotalPrice() + getGiftDetailsTotalPrice();
        for (BadgeInfo badgeInfo : BadgeInfo.values()) {
            if (-benefitPrice >= badgeInfo.getCriteriaBenefitPrice()) {
                this.badge = badgeInfo;
            }
        }
    }

    private int getSaleDetailsTotalPrice() {
        int totalBenefit = 0;
        for (int value : saleDetails.values()) {
            totalBenefit -= value;
        }
        return totalBenefit;
    }

    private int getGiftDetailsTotalPrice() {
        int totalPrice = 0;
        for (GiftEventInfo giftEventInfo : giftDetails) {
            totalPrice -= giftEventInfo.getGiftPrice();
        }
        return totalPrice;
    }

    public String toString() {
        StringBuilder message = new StringBuilder();

        ordersToString(message);

        if (!checkGiftDetailsIsEmpty(message))
            giftDetailsToString(message);

        if (!checkSaleDetailsIsEmpty(message))
            saleDetailsToString(message);

        finalPriceToString(message);

        badgeToString(message);

        return message.toString();
    }

    private void ordersToString(StringBuilder message) {
        message.append(INFORM_ORDER_LIST.getMessage());
        for (Map.Entry<Menu, Integer> order : orders.entrySet()) {
            message.append(
                    String.format(
                            INFORM_ORDER_LIST_DETAIL.getMessage(),
                            order.getKey().getName(),
                            order.getValue()
                    )
            );
        }
        message.append("\n");

        ordersDetailsToString(message);
    }

    private void ordersDetailsToString(StringBuilder message) {
        message.append(INFORM_ORIGINAL_PRICE.getMessage())
                .append(
                        String.format(
                                INFORM_ORIGINAL_PRICE_DETAIL.getMessage(),
                                decimalFormat.format(calculateOriginalPrice())
                        )
                )
                .append("\n");
    }

    private boolean checkGiftDetailsIsEmpty(StringBuilder message) {
        message.append(INFORM_GIFT.getMessage());
        if (giftDetails.isEmpty()) {
            message.append(INFORM_NOTHING.getMessage());
            message.append("\n");
            return true;
        }
        return false;
    }

    private void giftDetailsToString(StringBuilder message) {
        for (GiftEventInfo giftEventInfo : giftDetails) {
            message.append(
                    String.format(
                            INFORM_GIFT_DETAIL.getMessage(),
                            giftEventInfo.getGiftName(),
                            giftEventInfo.getGiftQuantity()
                    )
            );
        }
        message.append("\n");
    }

    private boolean checkSaleDetailsIsEmpty(StringBuilder message) {
        message.append(INFORM_BENEFIT_LIST.getMessage());
        if (saleDetails.isEmpty() && giftDetails.isEmpty()) {
            message.append(INFORM_NOTHING.getMessage());
            message.append("\n");
            return true;
        }
        return false;
    }

    private void saleDetailsToString(StringBuilder message) {
        for (Map.Entry<SaleEventInfo, Integer> saleDetail : saleDetails.entrySet()) {
            message.append(
                    String.format(
                            INFORM_BENEFIT_LIST_DETAIL.getMessage(),
                            saleDetail.getKey().getEventName(),
                            decimalFormat.format(saleDetail.getValue())
                    )
            );
        }
        giftDetailsTotalPriceToString(message);

        totalBenefitPriceToString(message);
    }

    private void giftDetailsTotalPriceToString(StringBuilder message) {
        if (!giftDetails.isEmpty()) {
            message.append(
                    String.format(
                            INFORM_BENEFIT_LIST_GIFT_EVENT.getMessage(),
                            decimalFormat.format(getGiftDetailsTotalPrice())
                    )
            );
        }
        message.append("\n");
    }

    private void totalBenefitPriceToString(StringBuilder message) {
        message.append(INFORM_TOTAL_BENEFIT_PRICE.getMessage());
        message.append(
                String.format(
                        INFORM_TOTAL_BENEFIT_PRICE_DETAIL.getMessage(),
                        decimalFormat.format(getSaleDetailsTotalPrice() + getGiftDetailsTotalPrice())
                )
        );
        message.append("\n");
    }

    private void finalPriceToString(StringBuilder message) {
        message.append(INFORM_PRICE_AFTER_DISCOUNT.getMessage());
        message.append(
                String.format(
                        INFORM_PRICE_AFTER_DISCOUNT_DETAIL.getMessage(),
                        decimalFormat.format(calculateOriginalPrice() + getSaleDetailsTotalPrice())
                )
        );
        message.append("\n");
    }

    private void badgeToString(StringBuilder message) {
        message.append(
                String.format(
                        INFORM_EVENT_BADGE.getMessage(),
                        visitDate.getMonthValue()
                )
        );

        message.append(
                String.format(
                        INFORM_EVENT_BADGE_DETAIL.getMessage(),
                        badge.getName()
                )
        );
    }
}
