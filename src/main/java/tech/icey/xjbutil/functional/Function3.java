package tech.icey.xjbutil.functional;

import tech.icey.xjbutil.container.Tuple3;

@FunctionalInterface
public interface Function3<T1, T2, T3, R> {
    R apply(T1 t1, T2 t2, T3 t3);

    default R apply(Tuple3<T1, T2, T3> tuple) {
        return apply(tuple.value1(), tuple.value2(), tuple.value3());
    }
}
