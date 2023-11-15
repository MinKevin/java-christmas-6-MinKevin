package christmas.event.badge;

public enum BadgeInfo {
    NOTHING("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    String name;
    int criteriaBenefitPrice;

    BadgeInfo(String name, int criteriaBenefitPrice) {
        this.name = name;
        this.criteriaBenefitPrice = criteriaBenefitPrice;
    }

    public String getName() {
        return name;
    }

    public int getCriteriaBenefitPrice() {
        return criteriaBenefitPrice;
    }
}
