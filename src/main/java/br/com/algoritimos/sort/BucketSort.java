package br.com.algoritimos.sort;

import java.util.Collections;
import java.util.Vector;

public class BucketSort {
	// Function to sort arr[] of size n
    // using bucket sort
    public static void sort(float arr[], int n)
    {
        if (n <= 0)
            return;
 
        // 1) Create n empty buckets
        @SuppressWarnings("unchecked")
        Vector<Float>[] buckets = new Vector[n];
 
        for (int i = 0; i < n; i++) {
            buckets[i] = new Vector<Float>();
        }
 
        // 2) Put array elements in different buckets
        for (int i = 0; i < n; i++) {
            float idx = arr[i] * n;
            buckets[(int)idx].add(arr[i]);
        }
 
        // 3) Sort individual buckets
        for (int i = 0; i < n; i++) {
            Collections.sort(buckets[i]);
        }
 
        // 4) Concatenate all buckets into arr[]
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                arr[index++] = buckets[i].get(j);
            }
        }
    }
 
}
