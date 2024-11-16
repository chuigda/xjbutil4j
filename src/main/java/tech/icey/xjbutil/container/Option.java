package tech.icey.xjbutil.container;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tech.icey.xjbutil.functional.Function0;

public abstract sealed class Option<T> {
    public static final class Some<T> extends Option<T> {
        public final @NotNull T value;

        public Some(@NotNull T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value.toString();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Some<?> some = (Some<?>) obj;
            return value.equals(some.value);
        }
    }

    public static final class None<T> extends Option<T> {
        @Override
        public String toString() {
            return "Option.None";
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof None;
        }

        private static final None<?> INSTANCE = new None<>();
    }

    public static <T> Option<T> some(@NotNull T value) {
        return new Some<>(value);
    }

    public static <T> Option<T> none() {
        //noinspection unchecked
        return (Option<T>) None.INSTANCE;
    }

    public static <T> Option<T> fromNullable(@Nullable T value) {
        if (value == null) {
            return none();
        } else {
            return some(value);
        }
    }

    /// Converts Java 8 {@link java.util.Optional} to {@link Option}
    ///
    /// This function handles the cases where the Java 8 {@link java.util.Optional} itself is {@code null} and the value
    /// inside the {@link java.util.Optional} is {@code null}, don't worry about it.
    ///
    /// @param optional Java 8 {@link java.util.Optional}
    /// @param <T>      Type of the value
    /// @return the converted {@link Option}
    @SuppressWarnings({"OptionalUsedAsFieldOrParameterType", "OptionalAssignedToNull"})
    public static <T> Option<T> fromJavaOptional(@Nullable java.util.Optional<@Nullable T> optional) {
        // I know that we have jdk.internal.ValueBased of course but let's still be safe here, and assume that Optional
        // can be null in certain cases.
        if (optional == null) {
            return none();
        }
        return optional.map(Option::fromNullable).orElseGet(Option::none);
    }

    public T get() {
        return ((Some<T>)this).value;
    }

    public boolean isSome() {
        return this instanceof Some;
    }

    public boolean isNone() {
        return this instanceof None;
    }

    public T or(Function0<T> supplier) {
        if (this instanceof Some) {
            return ((Some<T>)this).value;
        } else {
            return supplier.apply();
        }
    }

    public T orElse(T defaultValue) {
        if (this instanceof Some) {
            return ((Some<T>)this).value;
        } else {
            return defaultValue;
        }
    }
}
