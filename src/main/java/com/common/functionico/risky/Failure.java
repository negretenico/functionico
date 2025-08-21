package com.common.functionico.risky;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public record Failure<T>(Throwable error)implements Try<T> {
    @Override
    public <U> Try<U> map(Function<T, U> fn) {
        return new Failure<>(error);
    }

    @Override
    public <U> Try<U> flatMap(Function<T, Try<U>> fn) {
        return new Failure<>(error);
    }

    @Override
    public Try<T> onSuccess(Consumer<T> action) {
        return this;
    }

    @Override
    public Try<T> onFailure(Consumer<Throwable> action) {
        action.accept(error);
        return this;
    }

    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public boolean isFailure() {
        return true;
    }

    @Override
    public T get() {
        return null;
    }

    @Override
    public T getOrElse(Supplier<T>fallback) {
        return fallback.get();
    }

    @Override
    public T getOrThrow() {
        throw new RuntimeException("Try failed", error);
    }
}
