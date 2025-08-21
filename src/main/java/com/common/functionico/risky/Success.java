package com.common.functionico.risky;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public record Success<T>(T data) implements Try<T> {
    @Override
    public <U> Try<U> map(Function<T, U> fn) {
        try{
            return new Success<>(fn.apply(data));
        }catch (Exception e){
            return new Failure<>(e);
        }
    }

    @Override
    public <U> Try<U> flatMap(Function<T, Try<U>> fn) {
        try {
            return fn.apply(data);
        }catch (Exception e){
            return new Failure<>(e);
        }
    }

    @Override
    public Try<T> onSuccess(Consumer<T> action) {
        action.accept(data);
        return this;
    }

    @Override
    public Try<T> onFailure(Consumer<Throwable> action) {
        return this;
    }

    @Override
    public boolean isSuccess() {
        return true;
    }

    @Override
    public boolean isFailure() {
        return false;
    }

    @Override
    public T get() {
        return data;
    }

    @Override
    public T getOrElse(Supplier<T> fallback) {
        return Objects.isNull(data)?fallback.get():data;
    }

    @Override
    public T getOrThrow() {
        return data;
    }
}
