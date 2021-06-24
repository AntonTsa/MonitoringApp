package ua.study.bl.service.handlers;

public interface Handler {
    void setNext(Handler handler);
    void handle(Request request);
}
