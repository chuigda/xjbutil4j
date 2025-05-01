package cc.design7.xjbutil.functional;

import cc.design7.xjbutil.container.Tuple4;

@FunctionalInterface
public interface Action4<T1, T2, T3, T4> {
    void accept(T1 t1, T2 t2, T3 t3, T4 t4);

    default void apply(T1 t1, T2 t2, T3 t3, T4 t4) {
        accept(t1, t2, t3, t4);
    }

    default void accept(Tuple4<T1, T2, T3, T4> tuple) {
        accept(
                tuple.value1(),
                tuple.value2(),
                tuple.value3(),
                tuple.value4()
        );
    }

    default void apply(Tuple4<T1, T2, T3, T4> tuple) {
        accept(tuple);
    }
}
