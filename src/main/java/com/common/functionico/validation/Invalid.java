package com.common.functionico.validation;

import java.util.List;

public record Invalid<T,E extends Exception>(E exception) implements Validated<T
        ,E>{
    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public List<E> errors() {
        return List.of(exception);
    }

    @Override
    public T value() {
        return null;
    }
}
