package tech.icey.xjbutil.container;

public abstract sealed class Either<L, R> {
    public static final class Left<L, R> extends Either<L, R> {
        public final L value;

        public Left(L value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value.toString();
        }
    }

    public static final class Right<L, R> extends Either<L, R> {
        public final R value;

        public Right(R value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value.toString();
        }
    }

    public static <L, R> Either<L, R> left(L value) {
        return new Left<>(value);
    }

    public static <L, R> Either<L, R> right(R value) {
        return new Right<>(value);
    }

    public L left() {
        return ((Left<L, R>)this).value;
    }

    public R right() {
        return ((Right<L, R>) this).value;
    }

    public boolean isLeft() {
        return this instanceof Left;
    }

    public boolean isRight() {
        return this instanceof Right;
    }
}
