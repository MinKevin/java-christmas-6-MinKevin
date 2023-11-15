package christmas.controller;

import christmas.controller.adapter.BillHandlerAdapter;
import christmas.controller.adapter.HandlerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrontController {
    private static final FrontController singleton = new FrontController();
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<HandlerAdapter> handlerAdapters = new ArrayList<>();

    private FrontController() {
        initHandlerMappingMap();

        initHandlerAdapters();
    }

    public static FrontController getInstance() {
        return singleton;
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/save-visit-date", SaveVisitController.getInstance());
        handlerMappingMap.put("/save-orders", SaveOrdersController.getInstance());
        handlerMappingMap.put("/read-bill", ReadBillController.getInstance());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new BillHandlerAdapter());
    }

    public void match(String url, Map<String, Object> model) {
        Object handler = getHandler(url);
        HandlerAdapter adapter = getHandlerAdapter(handler);

        adapter.handle(model, handler);
    }

    private Object getHandler(String url) {
        return handlerMappingMap.get(url);
    }

    private HandlerAdapter getHandlerAdapter(Object handler) {
        for (HandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler))
                return adapter;
        }

        throw new IllegalArgumentException("Can not find compatible HandlerAdapter");
    }
}
