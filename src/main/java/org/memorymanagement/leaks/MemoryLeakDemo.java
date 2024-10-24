package org.memorymanagement.leaks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryLeakDemo {
    // Static collection causing memory leak
    private static final List<byte[]> staticLeakList = new ArrayList<>();

    // Non-static collection for comparison
    private final List<byte[]> instanceList = new ArrayList<>();

    // Map that may cause memory leak through key retention
    private static final Map<Object, String> leakyMap = new HashMap<>();

    public void demonstrateStaticLeak() {
        // Adding large arrays to static list - these will never be GC'd
        while (true) {
            staticLeakList.add(new byte[1024 * 1024]); // 1MB each
            System.out.println("Static list size: " + staticLeakList.size());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void demonstrateInstanceLeak() {
        // These objects can be GC'd when the instance is no longer referenced
        while (true) {
            instanceList.add(new byte[1024 * 1024]);
            System.out.println("Instance list size: " + instanceList.size());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
