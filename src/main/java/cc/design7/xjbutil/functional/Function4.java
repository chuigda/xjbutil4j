package cc.design7.xjbutil.functional;

import cc.design7.xjbutil.container.Tuple4;

@FunctionalInterface
public interface Function4<T1, T2, T3, T4, R> {
    R apply(T1 t1, T2 t2, T3 t3, T4 t4);

    default R apply(Tuple4<T1, T2, T3, T4> tuple) {
        return apply(
            tuple.value1(),
            tuple.value2(),
            tuple.value3(),
            tuple.value4()
        );
    }
}
