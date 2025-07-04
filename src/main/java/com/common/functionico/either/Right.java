package com.common.functionico.either;

import java.util.function.Function;

public record Right<L,R>(R data) implements Either<L, R> {

    @Override
    public <U> Either<L, U> map(Function< R, U> fn) {
        return new Right<>(fn.apply(data));
    }

    @Override
    public <U> Either<L, U> flatMap(Function<R, Either<L, U>> fn) {
        return fn.apply(data);
    }

    @Override
    public boolean isLeft() {
        return false;
    }

    @Override
    public boolean isRight() {
        return false;
    }

    @Override
    public L getLeft() {
        throw new IllegalStateException("Cannot get Left from Right");
    }

    @Override
    public R getRight() {
        return data;
    }
}
