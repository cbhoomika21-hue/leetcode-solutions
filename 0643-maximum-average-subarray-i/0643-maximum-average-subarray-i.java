class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int n=nums.length;
        int cur_sum=0;

        for(int i=0;i<k;i++){
            cur_sum=cur_sum + nums[i];
        }        
        int max_sum= cur_sum;
        for(int i=1;i<=n-k;i++){
            cur_sum= cur_sum - nums[i-1] + nums[i+k-1];
            if(cur_sum>max_sum){
                max_sum=cur_sum;
            }
        }
        return (double) max_sum/k;
    }
}