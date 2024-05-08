package cn.edu.swust.qd.common.factory;

import cn.hutool.core.util.StrUtil;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 重命名 ThreadFactory
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
public class NamedThreadFactory implements ThreadFactory {

    private final AtomicInteger poolNumber = new AtomicInteger(1);

    private final ThreadGroup threadGroup;

    private final AtomicInteger threadNumber = new AtomicInteger(1);

    public final String namePrefix;

    public NamedThreadFactory(String namePrefix) {
        this.threadGroup = Thread.currentThread().getThreadGroup();

        if (StrUtil.isBlank(namePrefix)) {
            namePrefix = "pool";
        }
        this.namePrefix = namePrefix + "-" + poolNumber.getAndIncrement() + "-thread-";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(threadGroup, r,
                namePrefix + threadNumber.getAndIncrement(),
                0);
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}
