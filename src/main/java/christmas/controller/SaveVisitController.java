package christmas.controller;

import christmas.service.BillService;

import java.time.LocalDate;
import java.util.Map;

public class SaveVisitController implements BillController {
    private static final SaveVisitController singleton = new SaveVisitController();

    private final BillService billService = BillService.getInstance();

    private SaveVisitController() {
    }

    public static SaveVisitController getInstance() {
        return singleton;
    }

    @Override
    public void process(Map<String, Object> model) {
        LocalDate visitDate = (LocalDate) model.get("visitDate");

        billService.saveVisitDate(visitDate);
    }
}
