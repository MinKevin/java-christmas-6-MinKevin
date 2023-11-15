package christmas.view;

public enum ValidationRegex {
    NUMBER_BETWEEN_1_TO_31("^([1-9]|[12][0-9]|3[01])$"),
    ONLY_NUMBER("^([0-9]+)$");

    final String regex;

    ValidationRegex(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
