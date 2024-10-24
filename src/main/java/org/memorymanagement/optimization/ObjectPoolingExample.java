package org.memorymanagement.optimization;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ObjectPoolingExample {
    public static class ObjectPool<T> {
        private ConcurrentLinkedQueue<T> pool;
        private ObjectFactory<T> factory;

        public ObjectPool(ObjectFactory<T> factory, int initialSize) {
            this.factory = factory;
            pool = new ConcurrentLinkedQueue<>();

            for (int i = 0; i < initialSize; i++) {
                pool.offer(factory.createObject());
            }
        }

        public T borrowObject() {
            T object = pool.poll();
            return object != null ? object : factory.createObject();
        }

        public void returnObject(T object) {
            pool.offer(object);
        }
    }

    public interface ObjectFactory<T> {
        T createObject();
    }

    // Example usage
    public static void main(String[] args) {
        ObjectPool<StringBuilder> pool = new ObjectPool<>(
                () -> new StringBuilder(),
                10
        );

        StringBuilder sb = pool.borrowObject();
        try {
            sb.append("Hello, World!");
            System.out.println(sb.toString());
        } finally {
            sb.setLength(0); // Clear the builder
            pool.returnObject(sb); // Return to pool
        }
    }
}