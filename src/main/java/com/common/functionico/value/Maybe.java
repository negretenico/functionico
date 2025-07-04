package com.common.functionico.value;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
public interface Maybe<T> {
    <U> Maybe<U> map(Function<T,U> mapper);
    T orElse(T value);
    T orElseGet(Supplier<T> supplier);
    boolean isPresent();
    void ifPresent(Consumer<T> peek);
    static <T> Maybe<T> of(T value){
        return Objects.isNull(value)?new None<>() :new Some<>(value);
    }
    static <T> Maybe<T> none(){
        return new None<>();
    }
}
