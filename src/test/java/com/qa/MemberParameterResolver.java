package com.qa;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.time.LocalDate;

public class MemberParameterResolver implements ParameterResolver {
    //    Determine if this resolver supports resolution of an argument for the Parameter in the supplied ParameterContext for the supplied ExtensionContext
    //    supportsParameter() – returns true if the parameter’s type is supported (BankAccount in this example)
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == Member.class;
    }

    //    Resolve an argument for the Parameter in the supplied ParameterContext for the supplied ExtensionContext
    //    resolveParameter() – serves up an object of the correct type (a new BankAccount instance in this example),
    //    which will then be injected into your test method
    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return new Member<>("Aiman Ahmed",90879, LocalDate.of(2025,10,15));
    }
}

