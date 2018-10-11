public class FindAllComboArray {
    public List<Integer> findLongestModeArray(int[] input){

        return new ArrayList<Integer>();
    }

    public static void findAllCombo(int[] input, List<List<Integer>> result){
        for (int in: input){
            List<List<Integer>> temp = new ArrayList<List<Integer>>();
            for(List<Integer> re: result){
                ArrayList<Integer> copy = new ArrayList<Integer>();
                copy.addAll(re);
                copy.add(in);
                temp.add(copy);
            }

            for (List<Integer> lt: temp){
                result.add(lt);
            }
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

    public static void main(String []args){
        // this practice to find all the possible combination of a given array
        int[] input = new int[]{1,2,3};
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.add(new ArrayList());
        findAllCombo(input, result);
        System.out.println("size " + result.size());

        pprint(result);
    }
}
