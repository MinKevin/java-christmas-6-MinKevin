package christmas.view;

import christmas.controller.FrontController;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

public class OutputView {
    private static final OutputView singleton = new OutputView();
    private final FrontController frontController = FrontController.getInstance();

    private OutputView() {};

    public static OutputView getInstance() {
        return singleton;
    }

    public void printBill() {
        Map<String, Object> model = new HashMap<>();
        frontController.match("/read-bill", model);

        String message = (String) model.get("message");
        System.out.println(message);
    }

    public void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}
