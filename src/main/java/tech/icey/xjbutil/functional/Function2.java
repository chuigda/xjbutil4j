package tech.icey.xjbutil.functional;

import tech.icey.xjbutil.container.Pair;

@FunctionalInterface
public interface Function2<T1, T2, R> {
    R apply(T1 t1, T2 t2);

    default R apply(Pair<T1, T2> pair) {
        return apply(pair.first(), pair.second());
    }
}
