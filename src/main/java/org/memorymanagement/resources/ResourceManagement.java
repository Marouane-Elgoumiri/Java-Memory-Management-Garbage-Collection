package org.memorymanagement.resources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResourceManagement {
    // Bad practice - resource leak
    public String readFileBadPractice(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        return reader.readLine();
        // Reader is never closed!
    }

    // Good practice - try-with-resources
    public String readFileGoodPractice(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return reader.readLine();
        }
    }

    // Demonstrate collection cleanup
    public static class Cache {
        private List<byte[]> data = new ArrayList<>();

        public void addData(byte[] item) {
            data.add(item);
        }

        public void clearCache() {
            data.clear(); // Proper cleanup
            data = null; // Allow list itself to be GC'd
        }
    }
}
