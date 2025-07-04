package com.common.functionico.value;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public record None<T>()implements Maybe<T> {
    @Override
    public <U>Maybe<U> map(Function<T,U> mapper) {
        return Maybe.none();
    }

    @Override
    public T orElse(T value) {
        return value;
    }

    @Override
    public T orElseGet(Supplier<T> supplier) {
        return supplier.get();
    }

    @Override
    public boolean isPresent() {
        return false;
    }

    @Override
    public void ifPresent(Consumer<T> peek) {
    }
}
