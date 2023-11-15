package christmas.view;

public enum InformMessage {
    INFORM_ORDER_LIST("<주문 메뉴>\n"),
    INFORM_ORDER_LIST_DETAIL("%s %s개\n"),
    INFORM_ORIGINAL_PRICE("<할인 전 총주문 금액>\n"),
    INFORM_ORIGINAL_PRICE_DETAIL("%s원\n"),
    INFORM_GIFT("<증정 메뉴>\n"),
    INFORM_GIFT_DETAIL("%s %s개\n"),
    INFORM_BENEFIT_LIST("<혜택 내역>\n"),
    INFORM_BENEFIT_LIST_GIFT_EVENT("증정 이벤트: %s원\n"),
    INFORM_BENEFIT_LIST_DETAIL("%s: -%s원\n"),
    INFORM_TOTAL_BENEFIT_PRICE("<총혜택 금액>\n"),
    INFORM_TOTAL_BENEFIT_PRICE_DETAIL("%s원\n"),
    INFORM_PRICE_AFTER_DISCOUNT("<할인 후 예상 결제 금액>\n"),
    INFORM_PRICE_AFTER_DISCOUNT_DETAIL("%s원\n"),
    INFORM_EVENT_BADGE("<%d월 이벤트 배지>\n"),
    INFORM_EVENT_BADGE_DETAIL("%s\n"),
    INFORM_NOTHING("없음\n");

    final String message;

    InformMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
