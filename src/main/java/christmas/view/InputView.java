package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.FrontController;
import christmas.database.ConstantPool;
import christmas.database.Menu;
import christmas.error.ErrorMessage;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class InputView {
    private static final InputView singleton = new InputView();
    private final FrontController frontController = FrontController.getInstance();
    private final OutputView outputView = OutputView.getInstance();

    private InputView() {
    }

    public static InputView getInstance() {
        return singleton;
    }

    public void pleaseEnterVisitDate() {
        try {
            System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");

            LocalDate visitDate = readDate();

            Map<String, Object> model = new HashMap<>();
            model.put("visitDate", visitDate);
            frontController.match("/save-visit-date", model);

        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);

            pleaseEnterVisitDate();
        }
    }

    public LocalDate readDate() throws IllegalArgumentException {
        String input = Console.readLine();

        validateInputDay(input);

        return LocalDate.of(ConstantPool.THIS_YEAR.getValue(), Month.DECEMBER, Integer.parseInt(input.trim()));
    }

    public void pleaseEnterOrders() {
        try {
            System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

            Map<Menu, Integer> orders = readOrders();

            Map<String, Object> model = new HashMap<>();
            model.put("orders", orders);

            frontController.match("/save-orders", model);

        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);

            pleaseEnterOrders();
        }
    }

    public Map<Menu, Integer> readOrders() throws IllegalArgumentException {
        String input = Console.readLine();

        Map<Menu, Integer> orders = new HashMap<>();
        validateInputMenuWithQuantity(input, orders);

        return orders;
    }

    private void validateInputMenuWithQuantity(String input, Map<Menu, Integer> menus) throws IllegalArgumentException {
        Arrays.stream(input.split(","))
                .forEach(menuWithQuantity -> {
                            List<String> menuAndQuantity = List.of(menuWithQuantity.split("-"));
                            validateResultSize(menuAndQuantity);

                            String menuName = menuAndQuantity.get(0).trim();

                    if (!checkMenuNameIsValid(menus, menuName, menuAndQuantity))
                                throw new IllegalArgumentException(ErrorMessage.INVALID_MENU_AND_QUANTITY_EXCEPTION.getMessage());
                        }
                );
    }

    private boolean checkMenuNameIsValid(Map<Menu, Integer> menus, String menuName, List<String> menuAndQuantity) throws IllegalArgumentException {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(menuName)) {
                if (menus.containsKey(menu))
                    throw new IllegalArgumentException(ErrorMessage.INVALID_MENU_AND_QUANTITY_EXCEPTION.getMessage());

                String quantity = menuAndQuantity.get(1).trim();
                validateQuantity(quantity);

                menus.put(menu, Integer.parseInt(quantity));
                return true;
            }
        }
        return false;
    }

    private void validateInputDay(String input) throws IllegalArgumentException {
        if (!Pattern.matches(ValidationRegex.NUMBER_BETWEEN_1_TO_31.regex, input.trim()))
            throw new IllegalArgumentException(ErrorMessage.INVALID_DAY_EXCEPTION.getMessage());
    }

    private void validateResultSize(List<String> result) throws IllegalArgumentException {
        if (result.size() != 2)
            throw new IllegalArgumentException(ErrorMessage.INVALID_MENU_AND_QUANTITY_EXCEPTION.getMessage());
    }

    private void validateQuantity(String quantityAsString) throws IllegalArgumentException {
        if (!Pattern.matches(ValidationRegex.ONLY_NUMBER.getRegex(), quantityAsString))
            throw new IllegalArgumentException(ErrorMessage.INVALID_MENU_AND_QUANTITY_EXCEPTION.getMessage());
    }
}
