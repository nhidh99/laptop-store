package org.example.service.template;

public interface ProcessService<Request> {
    void validate(Request request);

    void process(Request request);

    default void execute(Request request) {
        validate(request);
        process(request);
    }
}
