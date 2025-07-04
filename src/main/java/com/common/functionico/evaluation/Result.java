package com.common.functionico.evaluation;

import java.util.Objects;
import java.util.function.Function;

public record Result<T>(T data, String errorMsg) {
    public static <T> Result<T> success(T data) {
        return new Result<>(data, null);
    }

    public static <T> Result<T> failure(String errorMessage) {
        return new Result<>(null, errorMessage);
    }

    public boolean isSuccess() {
        return Objects.nonNull(this.data);
    }

    public boolean isFailure() {
        return Objects.isNull(this.data);
    }
    public <U> Result<U> map(Function<T, U> mapper){
        if (isFailure()) {
            return Result.failure(errorMsg);
        }
        return Result.success(mapper.apply(data));
    }
    public T getOrThrow() {
        if (isFailure()) {
            throw new RuntimeException("Result failed: " + errorMsg);
        }
        return data;
    }
}