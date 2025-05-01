package cc.design7.xjbutil.functional;

import java.util.function.Consumer;

@FunctionalInterface
public interface Action1<T> extends Consumer<T> {
    default void apply(T t) {
        accept(t);
    }
}
