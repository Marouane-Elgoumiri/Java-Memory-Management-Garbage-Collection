package org.memorymanagement.optimization;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class WeakReferenceDemo {
    private WeakHashMap<String, byte[]> cache = new WeakHashMap<>();

    public void demonstrateWeakReference() {
        // Create a weak reference
        String key = new String("key"); // Strong reference
        byte[] value = new byte[1024 * 1024]; // 1MB

        WeakReference<byte[]> weakRef = new WeakReference<>(value);
        cache.put(key, value);

        // Remove strong reference
        value = null;

        // Try to access weak reference
        byte[] retrieved = weakRef.get();
        if (retrieved != null) {
            System.out.println("Object still exists");
        } else {
            System.out.println("Object has been garbage collected");
        }
    }
}
