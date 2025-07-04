package com.common.functionico.either;

import java.util.function.Function;

public record Left<L,R>(L data) implements Either<L,R> {
    @Override
    public <U> Either<L, U> map(Function< R, U> fn) {
        return new Left<>(data);
    }

    @Override
    public <U> Either<L, U> flatMap(Function<R, Either<L, U>> fn) {
        return new Left<>(data);
    }

    @Override
    public boolean isLeft() {
        return true;
    }

    @Override
    public boolean isRight() {
        return false;
    }

    @Override
    public L getLeft() {
        return  data;
    }

    @Override
    public R getRight() {
        throw new IllegalStateException("Cannot get Right from Left");
    }
}
