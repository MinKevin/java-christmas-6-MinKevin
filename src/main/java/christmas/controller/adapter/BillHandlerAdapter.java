package christmas.controller.adapter;

import christmas.controller.BillController;

import java.util.Map;

public class BillHandlerAdapter implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return handler instanceof BillController;
    }

    @Override
    public void handle(Map<String, Object> model, Object handler) {
        BillController controller = (BillController) handler;

       controller.process(model);
    }
}
