package cc.design7.xjbutil.functional;

@FunctionalInterface
public interface Action0 extends Runnable {
    default void accept() { run(); }

    default void apply() {
        run();
    }
}
