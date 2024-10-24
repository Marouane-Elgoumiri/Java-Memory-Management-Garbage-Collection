package org.memorymanagement.monitoring;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

public class GCMonitoring {
    public void monitorGC() {
        List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();

        for (GarbageCollectorMXBean gcBean : gcBeans) {
            System.out.println("GC Name: " + gcBean.getName());
            System.out.println("Collection count: " + gcBean.getCollectionCount());
            System.out.println("Collection time: " + gcBean.getCollectionTime() + "ms");
            System.out.println("Pool names:");
            for (String name : gcBean.getMemoryPoolNames()) {
                System.out.println("\t" + name);
            }
        }
    }

    public void generateGarbage() {
        for (int i = 0; i < 1000; i++) {
            byte[] bytes = new byte[1024 * 1024]; // 1MB
            bytes = null; // Make eligible for GC
        }
    }
}
