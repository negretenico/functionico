package com.common.functionico.either;

import java.util.function.Function;

public sealed interface Either<L, R>  permits  Left, Right {
    <U> Either<L, U> map(Function<R, U> fn);
    <U> Either<L, U> flatMap(Function<R, Either<L, U>> fn);
    boolean isLeft();
    boolean isRight();
    L getLeft();
    R getRight();
    static <L, R> Either<L, R> left(L value) {
        return new Left<>(value);
    }

    static <L, R> Either<L, R> right(R value) {
        return new Right<>(value);
    }
}
