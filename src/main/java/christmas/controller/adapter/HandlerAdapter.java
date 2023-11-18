package christmas.controller.adapter;

import java.util.Map;

public interface HandlerAdapter {
    boolean supports(Object handler);

    void handle(Map<String, Object> model, Object handler);
}
