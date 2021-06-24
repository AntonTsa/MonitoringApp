package ua.study.bl.service;

import ua.study.bl.service.device.Observer;
import ua.study.bl.service.handlers.*;

public class SubstanceService implements Observer {
    @Override
    public void start(Long sensorId) {
        Request request = new Request();
        Handler handler1 = new Comparator();
        Handler handler2 = new Convertor();
        Handler handler3 = new Sender();

        handler1.setNext(handler2);
        handler2.setNext(handler3);

        handler1.handle(request);
    }
}
