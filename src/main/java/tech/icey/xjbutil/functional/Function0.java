package tech.icey.xjbutil.functional;

import java.util.function.Supplier;

@FunctionalInterface
public interface Function0<R> extends Supplier<R> {
    default R apply() {
        return get();
    }
}
