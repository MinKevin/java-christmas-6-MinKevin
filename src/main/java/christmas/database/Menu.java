package christmas.database;

import christmas.domain.Bill;

import java.util.Map;

public enum Menu {
    MUSHROOM_SOUP("양송이 수프", MenuType.APPETIZER, 6_000),
    TAPAS("타파스", MenuType.APPETIZER, 5_500),
    CAESAR_SALAD("시저샐러드", MenuType.APPETIZER, 8_000),

    T_BONE_STEAK("티본스테이크", MenuType.MAIN, 55_000),
    BARBECUE_RIBS("바비큐립", MenuType.MAIN, 54_000),
    SEAFOOD_PASTA("해산물파스타", MenuType.MAIN, 35_000),
    CHRISTMAS_PASTA("크리스마스파스타", MenuType.MAIN, 25_000),

    CHOCO_CAKE("초코케이크", MenuType.DESSERT, 15_000),
    ICE_CREAM("아이스크림", MenuType.DESSERT, 5_000),

    ZERO_COLA("제로콜라", MenuType.DRINK, 3_000),
    RED_WINE("레드와인", MenuType.DRINK, 60_000),
    CHAMPAGNE("샴페인", MenuType.DRINK, 25_000);


    final String name;
    final MenuType menuType;
    final int price;

    Menu(String name, MenuType menuType, int price) {
        this.name = name;
        this.menuType = menuType;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public int getPrice() {
        return price;
    }

    public static int countOrdersByMenuType(Bill bill, MenuType menuType) {
        Map<Menu, Integer> orders = bill.getOrders();
        int totalCount = 0;
        for (Map.Entry<Menu, Integer> order : orders.entrySet()) {
            Menu menu = order.getKey();
            if (menu.getMenuType() == menuType)
                totalCount += order.getValue();
        }
        return totalCount;
    }
}


