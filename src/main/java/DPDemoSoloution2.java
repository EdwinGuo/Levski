import java.util.*;
public class HelloWorld{
    public static List<Integer> largestDivisibleSubset(int[] nums) {
        // Using a dp solution to solve the problem.
        // In order to save some effort of re-compute,
        // we should sort the array first in an asending order,
        // after that we iterate through the sorted array and then
        // check elements against the left.
        
        //if (nums == null) return null;
        
        //comp: n*logn
        Arrays.sort(nums);
        
        // space n
        int[] dp = new int[nums.length];
        
        dp[0] = 1;
        
        // key is the element of the input, value is a list of element
        HashMap<Integer, Set<Integer>> temp = new HashMap<>();
        HashSet<Integer> hset = new HashSet<Integer>();
        hset.add(nums[0]);
        temp.put(nums[0], hset);
        
        // comp n^2
        for (int i = 1; i < nums.length; i++){
            HashSet<Integer> hs = new HashSet<Integer>();
            hs.add(nums[i]);
            temp.put(nums[i], hs);           
            for (int j = i; j >= 0; j--) {
                if (nums[i] % nums[j] == 0) {
                    if (dp[j] + 1 > dp[i]){
                        dp[i] = dp[j] + 1;
                        temp.get(nums[i]).addAll(temp.get(nums[j]));
                    }
                    
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    
                }
            }
        }
        
        Iterator it = temp.entrySet().iterator();
        
        int highestMark = 0;
        int key = 0;
        while (it.hasNext()){
            Map.Entry mentry = (Map.Entry) it.next();
            HashSet<Integer> tt = (HashSet<Integer>) mentry.getValue();
            int siz = tt.size();
            if (siz > highestMark){
                highestMark = siz;
                key = (int) mentry.getKey();
            } 
        }
        
        
        return new ArrayList<Integer>(temp.get(key));
        
        // int result = 0;
        // int index = 0;
        // for (int i = 0; i < dp.length; i++){
        //     if (result < dp[i]) {
        //         result = dp[i];
        //         index = i;
        //     }
        // }
        
        // List<Integer> val = new ArrayList<Integer>();
        // val.add(nums[index]);
        // // now walk to the left and retrieve all the elements.
        // for (int i = index; i >=0; i--){
        //     if (result - dp[i] == 1 && nums[index] % nums[i] == 0) {
        //         val.add(nums[i]);
        //         index = i;
        //         result = dp[i];
        //     }
        // }
        
        // return val;
        
        
        
    }
    
     public static void main(String []args){
        int[] nums = new int[]{2,3,5,6,12};
        List<Integer> xx =  largestDivisibleSubset(nums);
        for (int x : xx){
         System.out.println("Hello World, " + x);
        }
     }
}
