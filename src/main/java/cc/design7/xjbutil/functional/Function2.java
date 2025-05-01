package cc.design7.xjbutil.functional;

import cc.design7.xjbutil.container.Pair;

import java.util.function.BiFunction;

@FunctionalInterface
public interface Function2<T1, T2, R> extends BiFunction<T1, T2, R> {
    default R apply(Pair<T1, T2> pair) {
        return apply(pair.first(), pair.second());
    }
}
