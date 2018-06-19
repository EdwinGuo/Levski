class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        // this question is to given 2 int array, find the 
        // absolute minimum distince between array1 and array2
        // [1, 4, 7 10], => house .     heater position
        
   
        // below solution got a exceed time limit, complexity is O(m*n) where m is houses.length
        // and n is heaters.length
        
        
        //         int result = 0;
        //         for(int house : houses){
        //             int current = Integer.MAX_VALUE;
        //             for(int heater : heaters){
        //                 current = Math.min(Math.abs(house - heater), current);
        //             }
        //             result = Math.max(result, current);
        //         }


        //         return result;
        //     }
        
        // We would like to reduce to the complexity to O(m*logn) because every house need a heater
        // but not necessaryly that every heater need a house.
        
        // we need to do a binary search on the heaters, which means:
        // firstly, we need to sort on the heaters, complexity O(n*logn)
        Arrays.sort(heaters);
        
        //Now we need to iterate through all the houses and calculate the minimum distince
        int result = 0;
        for (int house: houses){
            // binary search the heaters
            int index = Arrays.binarySearch(heaters, house);
            // index will be two condition, either positive which mean there is a match,
            // or a negative number which we have to caculate the position that this house will be according to the heaters
            // for example, input = [1,3,5,7,9] and target is 2, the return value is -2, which means this target is sitting
            // between -(target + 1) - 1 and -(target + 1)

            if (index < 0){
                index = -(index + 1);
            }
            
            // now there are two possible solution, either heaters[index] == house or heaters[index] < house 
            // or index > heaters.length - 1
             
            if(index > heaters.length - 1){
                result = Math.max(result, Math.abs(heaters[heaters.length - 1] - house));
            } else if (heaters[index] > house){
                result = Math.max(result, index >= 1 ? Math.min(Math.abs(heaters[index] - house),  Math.abs(heaters[index - 1] - house)): Math.abs(heaters[0] - house));
            } else {
                continue;
            }
        }
        return result;
    } 
        
}
