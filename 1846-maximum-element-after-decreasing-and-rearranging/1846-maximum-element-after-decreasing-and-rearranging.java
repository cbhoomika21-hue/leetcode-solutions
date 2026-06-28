import java.util.Arrays;

public class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
            // Sort the array to process elements in increasing order
                    Arrays.sort(arr);
                            
                                    // The first element must be 1
                                            arr[0] = 1;
                                                    
                                                            // Iterate through the array and adjust elements
                                                                    for (int i = 1; i < arr.length; i++) {
                                                                                // If the current element is more than 1 greater than the previous,
                                                                                            // reduce it to previous + 1
                                                                                                        if (arr[i] > arr[i - 1] + 1) {
                                                                                                                        arr[i] = arr[i - 1] + 1;
                                                                                                                                    }
                                                                                                                                            }
                                                                                                                                                    
                                                                                                                                                            // The last element will be the maximum possible value
                                                                                                                                                                    return arr[arr.length - 1];
                                                                                                                                                                        }
                                                                                                                                                                        }