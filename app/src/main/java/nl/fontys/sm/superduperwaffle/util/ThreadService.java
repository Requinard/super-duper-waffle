package nl.fontys.sm.superduperwaffle.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by David on 6/10/2016.
 */
public class ThreadService {
    private static ExecutorService threadPool = Executors.newFixedThreadPool(4);

    public static void queue(Runnable r) {
        threadPool.submit(r);
    }

    public static <T> void submit(FutureTask<T> futureTask) {
        threadPool.submit(futureTask);
    }
}
