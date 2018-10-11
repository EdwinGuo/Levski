package Levski;

import java.util.*;


class LongestMode {
    // for a given input [2, 4, 1, 3, 7]  (element is distinct within the array)
    // find the longest array that for any given two element in the array,
    // say input[i] and input[j], it has to satisfy the condition
    // input[i] % input[j]
    //
    // for the above example, it should return List<int>(1, 2, 4)


    public List<Integer> findLongestModeArray(int[] input){
        // The brute force way of doing this is that list all the possible combination
        // and then check the array that whether it satisfy the condition.
        // Facts that we can assume:
        // 1) All elements are distinct
        // 2) if max(input[i], input[j]) % min(input[i], input[j]) == 0,
        //    then we can safely confrim that max(input[i], input[j]) % max(input[i], input[j]) == 0; which means we need to sort the array



        return new ArrayList<Integer>();
    }

    public static void findAllCombo(int[] input, List<List<Integer>> result){
        for (int in: input){
            List<List<Integer>> temp = new ArrayList<List<Integer>>();
            for(List<Integer> re: result){
                re.add(in);
                temp.add(re);
            }
            List<Integer> lst = new ArrayList<Integer>();
            lst.add(in);
            temp.add(lst);
        }
    }

    public static void pprint(List<List<Integer>> result){
        for (List<Integer> lstOuter: result){
            StringBuilder sb = new StringBuilder();
            for (Integer inte: lstOuter){
                sb.append(Integer.toString(inte) + "-");
            }

            System.out.println(sb.toString());
        }
    }

    public static void main(String[] args) {
        // generate all the possible combos
        int[] input = new int[]{1,2,3};
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.add(new ArrayList());
        findAllCombo(input, result);
        pprint(result);

    }
}

