package tech.icey.xjbutil.sync;

import tech.icey.xjbutil.functional.Action1;
import tech.icey.xjbutil.functional.Function0;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public final class DoubleBuffer<T> {
    private final List<T> buffer;
    private final Semaphore semaphore;
    private final AtomicInteger readIndex;

    public DoubleBuffer(Function0<T> bufferSupplier) {
        this.buffer = List.of(bufferSupplier.get(), bufferSupplier.get());
        this.semaphore = new Semaphore(1);
        this.readIndex = new AtomicInteger(0);
    }

    public void writeBuffer(Action1<T> action) {
        try {
            semaphore.acquire();
            action.accept(buffer.get(1 - readIndex.get()));

            readIndex.set(1 - readIndex.get());
        } catch (InterruptedException e) {
            // ignore
        } finally {
            semaphore.release();
        }
    }

    public T getBufferForRead() {
        return buffer.get(readIndex.get());
    }
}
