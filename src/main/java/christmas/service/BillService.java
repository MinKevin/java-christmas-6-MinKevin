package christmas.service;

import christmas.database.Menu;
import christmas.domain.Bill;

import java.time.LocalDate;
import java.util.Map;

public class BillService {
    private static final BillService singleton = new BillService();
    private final Bill bill = Bill.getInstance();

    private BillService() {}

    public static BillService getInstance() {
        return singleton;
    }

    public void saveVisitDate(LocalDate visitDate) {
        bill.setVisitDate(visitDate);
    }

    public void saveOrders(Map<Menu, Integer> orders) {
        bill.setOrders(orders);

        bill.fillOutBill();
    }

    public void readBill(Map<String, Object> model) {
        model.put("message", bill.toString());
    }
}
