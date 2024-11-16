package tech.icey.xjbutil.container;

import java.util.Objects;

public final class Tuple3<T1, T2, T3> {
    private T1 value1;
    private T2 value2;
    private T3 value3;

    public Tuple3(T1 value1, T2 value2, T3 value3) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }

    public T1 value1() {
        return value1;
    }

    public T2 value2() {
        return value2;
    }

    public T3 value3() {
        return value3;
    }

    public T1 first() {
        return value1;
    }

    public T2 second() {
        return value2;
    }

    public void setValue1(T1 value1) {
        this.value1 = value1;
    }

    public void setValue2(T2 value2) {
        this.value2 = value2;
    }

    public void setValue3(T3 value3) {
        this.value3 = value3;
    }

    public void setFirst(T1 value1) {
        this.value1 = value1;
    }

    public void setSecond(T2 value2) {
        this.value2 = value2;
    }

    @Override
    public String toString() {
        return "Tuple(" +
                 value1 +
                ", " + value2 +
                ", " + value3 +
                ')';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Tuple3<?, ?, ?> tuple = (Tuple3<?, ?, ?>) obj;
        return Objects.equals(value1, tuple.value1) &&
               Objects.equals(value2, tuple.value2) &&
               Objects.equals(value3, tuple.value3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value1, value2, value3);
    }
}
