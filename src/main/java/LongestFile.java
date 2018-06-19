class Solution {
    class Node {
        String str;
        List<Node> nei;
        
        Node(String str, List<Node> nei){
            this.str = str;
            this.nei = nei;
        }    
    }
    
    public int lengthLongestPath(String input) {
        // using hashmap to keep track of the length of the current level.
        HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
        // Transfer this model to a DFS model 
        int result = 0;
        
        String[] inputs = input.split("\n");
        
        for (String in : inputs){
            //take care of the first element in the list
            if (!in.startsWith("\t")) {
                continue;
            }
            int level = in.lastIndexOf("\t") + 1;
            if (in.contains(".")){
                // End signal
                int temp = in.length() - level;
                for (int i = 1; i < level; i++){
                    temp += hmap.get(i);
                    result = Math.max(result, temp);
                }
            } else {
                int len = in.length() - level;
                hmap.put(level, len);
            }
        }
        
        return result + inputs[0].length() + 2;
    }
}
