package cc.design7.xjbutil.functional;

import cc.design7.xjbutil.container.Tuple3;

@FunctionalInterface
public interface Action3<T1, T2, T3> {
    void accept(T1 t1, T2 t2, T3 t3);

    default void apply(T1 t1, T2 t2, T3 t3) {
        accept(t1, t2, t3);
    }

    default void accept(Tuple3<T1, T2, T3> tuple) {
        accept(tuple.value1(), tuple.value2(), tuple.value3());
    }

    default void apply(Tuple3<T1, T2, T3> tuple) {
        accept(tuple);
    }
}
