package tech.icey.xjbutil.container;

import java.util.Objects;

public final class Tuple5<T1, T2, T3, T4, T5> {
    private T1 value1;
    private T2 value2;
    private T3 value3;
    private T4 value4;
    private T5 value5;

    public Tuple5(T1 value1, T2 value2, T3 value3, T4 value4, T5 value5) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
        this.value5 = value5;
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

    public T4 value4() {
        return value4;
    }

    public T5 value5() {
        return value5;
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

    public void setValue4(T4 value4) {
        this.value4 = value4;
    }

    public void setValue5(T5 value5) {
        this.value5 = value5;
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
                ", " + value4 +
                ", " + value5 +
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
        Tuple5<?, ?, ?, ?, ?> tuple = (Tuple5<?, ?, ?, ?, ?>) obj;
        return Objects.equals(value1, tuple.value1) &&
               Objects.equals(value2, tuple.value2) &&
               Objects.equals(value3, tuple.value3) &&
               Objects.equals(value4, tuple.value4) &&
               Objects.equals(value5, tuple.value5);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value1, value2, value3, value4, value5);
    }
}