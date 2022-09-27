package br.ufpb.dcx.tdm.utils;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.util.concurrency.Invoker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.concurrency.CancellablePromise;

public class EDTInvoker {
    private static final Logger LOG = Logger.getInstance(EDTInvoker.class);
    private static final Disposable dummyDisposable = () -> LOG.debug("DummyDisposable disposed");

    public static CancellablePromise<?> invoke(@NotNull Runnable task) {
        return invoke(dummyDisposable, task);
    }

    // deprecated API methods used for compatibility with older IDE versions
    @SuppressWarnings("UnstableApiUsage")
    public static CancellablePromise<?> invoke(@NotNull Disposable parent, @NotNull Runnable task) {
        return new Invoker.EDT(parent).runOrInvokeLater(task);
    }

    public static CancellablePromise<?> invokeLater(@NotNull Runnable task, int delay) {
        return invokeLater(dummyDisposable, task, delay);
    }

    // deprecated API methods used for compatibility with older IDE versions
    @SuppressWarnings("UnstableApiUsage")
    public static CancellablePromise<?> invokeLater(@NotNull Disposable parent, @NotNull Runnable task, int delay) {
        return new Invoker.EDT(parent).invokeLater(task, delay);
    }
}
