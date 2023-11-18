package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.Application;
import christmas.domain.Bill;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.Month;
import java.util.NoSuchElementException;

class InputViewTest extends NsTest {
    final InputView inputView = InputView.getInstance();


    @AfterEach
    void after() throws IOException {
        Console.close();
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "32", "33"})
    void 입력된_날짜가_유효하지_않을_때(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Assertions.assertThatThrownBy(() -> inputView.readDate()).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "15", "31"})
    void 입력된_날짜가_유효할_때(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        inputView.pleaseEnterVisitDate();
        Bill bill = Bill.getInstance();
        Assertions.assertThat(bill.getVisitDate()).isEqualTo(LocalDate.of(2023, 12, Integer.parseInt(input)));
    }

    @ParameterizedTest
    @CsvSource(value = {"abcd-3", "타파스-a, 해산물파스타-3", "가나다라-a"}, delimiter = ':')
    void 비정상적인_메뉴와_수량을_입력(String input) {
        System.out.println(input);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        try {
            inputView.pleaseEnterOrders();
        } catch (NoSuchElementException ignore) {
        }
        Assertions.assertThat(output())
                .contains("[ERROR]");
    }

    @ParameterizedTest
    @CsvSource(value = {"타파스-1, 타파스-1", "해산물파스타-1, 해산물파스타-2, 해산물파스타-3, 파타스-1"}, delimiter = ':')
    void 중복된_메뉴와_수량을_입력(String input) {
        System.out.println(input);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        try {
            inputView.pleaseEnterOrders();
        } catch (NoSuchElementException ignore) {
        }
        Assertions.assertThat(output())
                .contains("[ERROR]");
    }

    @ParameterizedTest
    @CsvSource(value = {"바비큐립-3:1", "타파스-10, 바비큐립-10:2", "레드와인-1, 티본스테이크-3, 초코케이크-2:3"}, delimiter = ':')
    void 정상적인_메뉴와_수량을_입력(String input, int result) {
        System.out.println(input);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        inputView.pleaseEnterOrders();

        Bill bill = Bill.getInstance();

        Assertions.assertThat(bill.getOrders().size()).isEqualTo(result);
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}