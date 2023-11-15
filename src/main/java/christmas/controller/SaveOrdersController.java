package christmas.controller;

import christmas.database.Menu;
import christmas.service.BillService;

import java.util.Map;

public class SaveOrdersController implements BillController {
    private static final SaveOrdersController singleton = new SaveOrdersController();

    private final BillService billService = BillService.getInstance();

    private SaveOrdersController() {
    }

    public static SaveOrdersController getInstance() {
        return singleton;
    }

    @Override
    public void process(Map<String, Object> model) {
        Map<Menu, Integer> orders = (Map<Menu, Integer>) model.get("orders");
        billService.saveOrders(orders);
    }
}
