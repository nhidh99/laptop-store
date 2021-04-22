package org.example.service.base;

public interface ProcessService<Request> {
    void validate(Request request);

    void process(Request request);

    default void execute(Request request) {
        validate(request);
        process(request);
    }
}
