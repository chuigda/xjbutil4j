package tech.icey.xjbutil.functional;

import tech.icey.xjbutil.container.Tuple5;

@FunctionalInterface
public interface Function5<T1, T2, T3, T4, T5, R> {
    R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5);

    default R apply(Tuple5<T1, T2, T3, T4, T5> tuple) {
        return apply(
            tuple.value1(),
            tuple.value2(),
            tuple.value3(),
            tuple.value4(),
            tuple.value5()
        );
    }
}
