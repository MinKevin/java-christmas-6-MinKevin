package christmas;

import christmas.view.InputView;
import christmas.view.OutputView;

import java.text.DecimalFormat;

public class Application {
    static final InputView inputView = InputView.getInstance();
    static final OutputView outputView = OutputView.getInstance();

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        inputView.pleaseEnterVisitDate();

        inputView.pleaseEnterOrders();

        outputView.printBill();
    }
}
