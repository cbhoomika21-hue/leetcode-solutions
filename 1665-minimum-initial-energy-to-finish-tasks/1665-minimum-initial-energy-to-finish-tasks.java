import java.util.Arrays;

class Solution {
    public int minimumEffort(int[][] tasks) {
        // Sort tasks based on the difference (minimum - actual) in descending order.
        // This greedy approach ensures we handle tasks with the largest 
        // energy "buffer" requirement first.
        Arrays.sort(tasks, (a, b) -> (b[1] - b[0]) - (a[1] - a[0]));
        
        int initialEnergy = 0;
        int currentEnergy = 0;
        
        for (int[] task : tasks) {
            int actual = task[0];
            int minReq = task[1];
            
            // If current energy is not enough to start the task,
            // we must increase our initial energy pool.
            if (currentEnergy < minReq) {
                initialEnergy += (minReq - currentEnergy);
                currentEnergy = minReq;
            }
            
            // Perform the task and reduce current energy
            currentEnergy -= actual;
        }
        
        return initialEnergy;
    }
}