package tech.icey.xjbutil.container;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tech.icey.xjbutil.functional.Function0;

import java.util.NoSuchElementException;
import java.util.Optional;

/// A somewhat modernized option type intended to replace old, very limited {@link java.util.Optional}.
///
/// You can create an option with {@link Option#some(Object)} and {@link Option#none()}:
///
/// {@snippet lang=java :
/// Option<String> some = Option.some("Hello, world!");
/// Option<String> none = Option.none();
///
/// System.out.println(some); // prints "Hello, world!"
/// System.out.println(none); // prints "Option.None"}
///
/// And the general preferred way of using it is to use poor man's `if let`:
///
/// {@snippet lang=java :
/// if (sese instanceof Option.Some<String> someSese) {
///     doSomethingWithString(someSese.value);
/// }}
///
/// Poor man's `let else`:
///
/// {@snippet lang=java :
/// if (!(sese instanceof Option.Some<String> someSese)) {
///     throw new RuntimeException("色不异空不是说让你真给我个空值啊");
/// }
///
/// doSomethingWithString(someSese.value);}
///
/// And new genesis Java switch:
///
/// {@snippet lang=java :
/// switch (sese) {
///     case Option.Some<String> someSese -> doSomethingWithString(someSese.value);
///     case Option.None<?> _ -> throw new RuntimeException("色不异空不是说让你真给我个空值啊");
/// }}
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

    /// Converts Java 8 {@link Optional} to {@link Option}
    ///
    /// This function handles the cases where the Java 8 {@link Optional} itself is {@code null} and the value inside
    /// the {@link Optional} is {@code null}, don't worry about it.
    ///
    /// @param optional Java 8 {@link Optional}
    /// @param <T>      Type of the value
    /// @return the converted {@link Option}
    @SuppressWarnings({"OptionalUsedAsFieldOrParameterType", "OptionalAssignedToNull"})
    public static <T> Option<T> fromJavaOptional(@Nullable Optional<@Nullable T> optional) {
        // I know that we have jdk.internal.ValueBased of course but let's still be safe here, and assume that Optional
        // can be null in certain cases.
        if (optional == null) {
            return none();
        }
        return optional.map(Option::fromNullable).orElseGet(Option::none);
    }

    /// Sanitize a potentially malformed {@link Option} reference.
    ///
    /// This function should only be used for very, very bad cases where you fail to figure out why the {@link Option}
    /// is malformed. This function will convert the {@link Option} to {@link Option#none()} if the {@link Option}
    /// itself is {@code null} or the value inside the {@link Option} is {@code null}.
    ///
    /// Generally speaking, to avoid such cases, you should always use {@link #fromNullable} for objects you don't know
    /// whether they can be {@code null} or not. You should always use {@link #fromJavaOptional} for Java 8
    /// {@link Optional} objects.
    ///
    /// @see Option#fromNullable(Object)
    /// @see Option#fromJavaOptional(Optional)
    public static <T> Option<T> sanitize(@Nullable Option<T> option) {
        if (option == null) {
            return none();
        } else {
            // if the value inside option is unfortunately null, we will convert it to None
            if (option instanceof Some<T> some) {
                //noinspection ConstantValue
                if (some.value == null) {
                    return none();
                }
            }
            return option;
        }
    }

    public final T get() {
        if (this instanceof Some) {
            return ((Some<T>)this).value;
        } else {
            throw new NoSuchElementException("Option is None");
        }
    }

    public final boolean isSome() {
        return this instanceof Some;
    }

    public final boolean isNone() {
        return this instanceof None;
    }

    public final T orElse(T defaultValue) {
        if (this instanceof Some) {
            return ((Some<T>)this).value;
        } else {
            return defaultValue;
        }
    }

    public final Option<T> orElse(Option<T> defaultValue) {
        if (this instanceof Some) {
            return this;
        } else {
            return defaultValue;
        }
    }

    public final T orElseGet(Function0<T> supplier) {
        if (this instanceof Some) {
            return ((Some<T>)this).value;
        } else {
            return supplier.apply();
        }
    }

    public final Option<T> orElseGetOption(Function0<Option<T>> supplier) {
        if (this instanceof Some) {
            return this;
        } else {
            return supplier.apply();
        }
    }
}
