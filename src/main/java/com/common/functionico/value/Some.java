package com.common.functionico.value;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public record Some<T>(T value)implements Maybe<T> {

    @Override
    public <U> Maybe<U> map(Function<T, U> mapper) {
        return Maybe.of(mapper.apply(value));
    }

    @Override
    public T orElse(T other) {
        return Objects.isNull(this.value)?other: this.value;
    }

    @Override
    public T orElseGet(Supplier<T> supplier) {
        return supplier.get();
    }

    @Override
    public boolean isPresent() {
        return Objects.nonNull(value);
    }

    @Override
    public void ifPresent(Consumer<T> peek) {
        peek.accept(value);
    }
}
