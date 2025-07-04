package com.common.functionico.evaluation;

import java.util.function.Consumer;
import java.util.function.Supplier;

@FunctionalInterface
public interface Lazy<T> {
    T get();
}
