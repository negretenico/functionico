package com.common.functionico.validation;

import java.util.List;

public record Valid<T,E extends Exception>(T value, E exception) implements Validated<T,E>{
    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public List<E> errors() {
        return List.of();
    }
}
