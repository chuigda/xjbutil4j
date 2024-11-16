package tech.icey.xjbutil.functional;

import tech.icey.xjbutil.container.Tuple5;

public interface Action5<T1, T2, T3, T4, T5> {
    void accept(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5);

    default void apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
        accept(t1, t2, t3, t4, t5);
    }

    default void accept(Tuple5<T1, T2, T3, T4, T5> tuple) {
        accept(
                tuple.value1(),
                tuple.value2(),
                tuple.value3(),
                tuple.value4(),
                tuple.value5()
        );
    }

    default void apply(Tuple5<T1, T2, T3, T4, T5> tuple) {
        accept(tuple);
    }
}
