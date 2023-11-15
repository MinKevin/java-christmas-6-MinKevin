package christmas.controller;

import christmas.service.BillService;

import java.util.Map;

public class ReadBillController implements BillController {
    private static final ReadBillController singleton = new ReadBillController();

    private final BillService billService = BillService.getInstance();

    private ReadBillController() {
    }

    public static ReadBillController getInstance() {
        return singleton;
    }

    @Override
    public void process(Map<String, Object> model) {
        billService.readBill(model);
    }
}
