package edu.ccrm.util;

import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

/**
 * Recursive utility methods demonstrating recursion concepts
 * Shows different types of recursive algorithms
 */
public class RecursiveUtils {
    
    // Recursive method to calculate directory size
    public static long calculateDirectorySize(Path directory) throws IOException {
        if (!Files.exists(directory)) {
            return 0;
        }
        
        if (Files.isRegularFile(directory)) {
            return Files.size(directory);
        }
        
        if (Files.isDirectory(directory)) {
            try (Stream<Path> children = Files.list(directory)) {
                return children.mapToLong(child -> {
                    try {
                        return calculateDirectorySize(child); // Recursive call
                    } catch (IOException e) {
                        System.err.println("Error calculating size for: " + child);
                        return 0;
                    }
                }).sum();
            }
        }
        
        return 0;
    }
    
    // Recursive method to list files with depth information
    public static void listFilesRecursively(Path directory, int currentDepth, int maxDepth) throws IOException {
        if (currentDepth > maxDepth || !Files.exists(directory)) {
            return;
        }
        
        // Print current directory with indentation
        String indent = "  ".repeat(currentDepth);
        System.out.println(indent + (Files.isDirectory(directory) ? "[DIR] " : "[FILE] ") + 
                          directory.getFileName());
        
        if (Files.isDirectory(directory)) {
            try (Stream<Path> children = Files.list(directory)) {
                children.forEach(child -> {
                    try {
                        listFilesRecursively(child, currentDepth + 1, maxDepth); // Recursive call
                    } catch (IOException e) {
                        System.err.println("Error listing: " + child);
                    }
                });
            }
        }
    }
    
    // Recursive method to count files and directories
    public static FileCount countFilesAndDirectories(Path path) throws IOException {
        if (!Files.exists(path)) {
            return new FileCount(0, 0);
        }
        
        if (Files.isRegularFile(path)) {
            return new FileCount(1, 0);
        }
        
        if (Files.isDirectory(path)) {
            FileCount count = new FileCount(0, 1);
            
            try (Stream<Path> children = Files.list(path)) {
                for (Path child : children.toList()) {
                    FileCount childCount = countFilesAndDirectories(child); // Recursive call
                    count = count.add(childCount);
                }
            }
            
            return count;
        }
        
        return new FileCount(0, 0);
    }
    
    // Inner class for file counting result
    public static class FileCount {
        private final int files;
        private final int directories;
        
        public FileCount(int files, int directories) {
            this.files = files;
            this.directories = directories;
        }
        
        public int getFiles() { return files; }
        public int getDirectories() { return directories; }
        
        public FileCount add(FileCount other) {
            return new FileCount(this.files + other.files, this.directories + other.directories);
        }
        
        @Override
        public String toString() {
            return String.format("Files: %d, Directories: %d", files, directories);
        }
    }
    
    // Recursive factorial method (mathematical recursion example)
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }
        if (n == 0 || n == 1) {
            return 1; // Base case
        }
        return n * factorial(n - 1); // Recursive call
    }
    
    // Recursive Fibonacci sequence (with memoization for efficiency)
    public static long fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Fibonacci is not defined for negative numbers");
        }
        return fibonacciHelper(n, new long[n + 1]);
    }
    
    private static long fibonacciHelper(int n, long[] memo) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        
        if (memo[n] != 0) {
            return memo[n]; // Return memoized result
        }
        
        memo[n] = fibonacciHelper(n - 1, memo) + fibonacciHelper(n - 2, memo); // Recursive calls
        return memo[n];
    }
    
    // Recursive string reversal
    public static String reverseString(String str) {
        if (str == null || str.length() <= 1) {
            return str; // Base case
        }
        return reverseString(str.substring(1)) + str.charAt(0); // Recursive call
    }
    
    // Recursive method to find maximum in array
    public static int findMax(int[] array, int index) {
        if (index == array.length - 1) {
            return array[index]; // Base case
        }
        
        int maxOfRest = findMax(array, index + 1); // Recursive call
        return Math.max(array[index], maxOfRest);
    }
    
    // Wrapper method for findMax
    public static int findMax(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty");
        }
        return findMax(array, 0);
    }
}