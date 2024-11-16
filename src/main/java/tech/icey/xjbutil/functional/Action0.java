package tech.icey.xjbutil.functional;

@FunctionalInterface
public interface Action0 {
    void accept();

    default void apply() {
        accept();
    }
}
