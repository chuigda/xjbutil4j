package tech.icey.xjbutil.sync;

import org.jetbrains.annotations.Nullable;
import tech.icey.xjbutil.container.Pair;
import tech.icey.xjbutil.container.Ref;

import java.util.concurrent.Semaphore;

public final class Oneshot {
    public static final class Sender<T> {
        private final Semaphore semaphore;
        private final Ref<@Nullable T> ref;

        Sender(Semaphore semaphore, Ref<@Nullable T> ref) {
            this.semaphore = semaphore;
            this.ref = ref;
        }

        public void send(T value) {
            ref.value = value;
            semaphore.release();
        }
    }

    public static final class Receiver<T> {
        private final Semaphore semaphore;
        private final Ref<@Nullable T> ref;

        Receiver(Semaphore semaphore, Ref<@Nullable T> ref) {
            this.semaphore = semaphore;
            this.ref = ref;
        }

        public T recv() {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return ref.value;
        }
    }

    public static <T> Pair<Sender<T>, Receiver<T>> create() {
        Semaphore semaphore = new Semaphore(0);
        Ref<@Nullable T> ref = new Ref<>(null);

        return new Pair<>(new Sender<>(semaphore, ref), new Receiver<>(semaphore, ref));
    }
}
