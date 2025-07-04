package com.common.functionico.validation;

import java.util.List;

public sealed interface Validated<T, E extends Exception> permits Valid,
        Invalid {
    boolean isValid();

    List<E> errors();

    T value();
}
