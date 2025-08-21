package com.common.functionico.evaluation;

import org.springframework.util.function.ThrowingSupplier;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public record Result<State>(State state, Throwable e) {

    public static <State> Result<State> success(State initial) {
        return new Result<>(initial, null);
    }

    public static <State> Result<State> failure(Throwable e) {
        return new Result<>(null, e);
    }

    public Result<State> onSuccess(Consumer<State> success) {
        if (Objects.isNull(this.e)) {
            success.accept(this.state);
        }
        return this;
    }

    public static <State> Result<State> of(ThrowingSupplier<State> func) {
        try {
            return success(func.get());
        } catch (Exception e) {
            return failure(e);
        }
    }

    public Result<State> onFailure(Consumer<Throwable> failure) {
        if (Objects.nonNull(this.e)) {
            failure.accept(this.e);
        }
        return this;
    }

    public <NewState> Result<NewState> flatMap(Function<State, Result<NewState>> next) {
        if (Objects.nonNull(e)) {
            return new Result<>(null, e);
        }
        try {
            return next.apply(state);
        } catch (Exception e) {
            return new Result<>(null, e);
        }
    }


    public <NewState> Result<NewState> map(Function<State, NewState> convert) {
        if (Objects.nonNull(e)) {
            return new Result<>(null, e);
        }
        if (Objects.isNull(state)) {
            return new Result<>(null, new RuntimeException("Cannot map on null state"));
        }
        try {
            NewState newState = convert.apply(this.state);
            if (newState == null) {
                throw new RuntimeException("Map function returned null");
            }
            return new Result<>(newState, null);
        } catch (Exception e) {
            return new Result<>(null, e);
        }
    }

    public Throwable getError() {
        return e;
    }

    public State get() {
        return state;
    }

    public State getOrElse(State fallback) {
        return Objects.nonNull(state) ? state : fallback;
    }
}