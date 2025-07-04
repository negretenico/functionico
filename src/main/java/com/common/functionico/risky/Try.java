package com.common.functionico.risky;

import java.util.function.Consumer;
import java.util.function.Function;

public sealed interface Try<T> permits Success, Failure {
    <U> Try<U> map(Function<T, U> fn);
    <U> Try<U> flatMap(Function<T, Try<U>> fn);
    Try<T> onSuccess(Consumer<T> action);
    Try<T> onFailure(Consumer<Throwable> action);
    boolean isSuccess();
    boolean isFailure();
    T getOrElse(T fallback);
    T getOrThrow();

    static <T> Try<T> of(ThrowingSupplier<T> supplier) {
        try {
            return new Success<>(supplier.get());
        } catch (Throwable t) {
            return new Failure<>(t);
        }
    }

    @FunctionalInterface
    interface ThrowingSupplier<T> {
        T get() throws Throwable;
    }
}
