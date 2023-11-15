package christmas.database;

import java.time.LocalDate;

public enum ConstantPool {
    THIS_YEAR(LocalDate.now().getYear());

    final int value;

    ConstantPool(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
