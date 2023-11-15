package christmas.error;

public enum ErrorMessage {
    INVALID_DAY_EXCEPTION("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_MENU_AND_QUANTITY_EXCEPTION("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_MENU_NAME_EXCEPTION("[ERROR] 메뉴 목록에 없는 메뉴 이름입니다. 올바른 메뉴 이름을 입력해주세요.");

    final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
