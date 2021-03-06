package org.example.handler;

import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.example.constant.HeaderConstants;
import org.example.constant.LocaleConstants;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

@Component
public class GlobalHeaderHandler implements OperationCustomizer {
    @Override
    public Operation customize(Operation operation, HandlerMethod handlerMethod) {
        operation.addParametersItem(getLocaleHeader());
        return operation;
    }

    private Parameter getLocaleHeader() {
        return new Parameter()
                .in(ParameterIn.HEADER.toString())
                .name(HeaderConstants.ACCEPT_LANGUAGE)
                .schema(new StringSchema()
                        .addEnumItem(LocaleConstants.ENGLISH)
                        .addEnumItem(LocaleConstants.VIETNAM)
                        ._default(LocaleConstants.ENGLISH));
    }
}
