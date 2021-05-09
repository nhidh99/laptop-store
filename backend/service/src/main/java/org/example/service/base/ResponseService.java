package org.example.service.base;

public interface ResponseService<Request, Response> {
    default void validate(Request request) { }

    Response process(Request request);

    default Response execute(Request request) {
        validate(request);
        return process(request);
    }
}
