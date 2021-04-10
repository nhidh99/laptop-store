package org.example.service.template;

public interface ResponseService<Request, Response> {
    void validate(Request request);

    Response process(Request request);

    default Response execute(Request request) {
        validate(request);
        return process(request);
    }
}
