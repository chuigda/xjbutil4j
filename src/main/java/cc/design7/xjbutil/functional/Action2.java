package cc.design7.xjbutil.functional;

import cc.design7.xjbutil.container.Pair;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface Action2<T1, T2> extends BiConsumer<T1, T2> {
    default void apply(T1 t1, T2 t2) {
        accept(t1, t2);
    }

    default void consume(Pair<T1, T2> pair) {
        accept(pair.first(), pair.second());
    }

    default void apply(Pair<T1, T2> pair) {
        accept(pair.first(), pair.second());
    }
}
