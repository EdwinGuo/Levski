class Solution {
    private HashMap<Integer, HashSet<Integer>> edgeNei;



    // this part is the most interesting piece
    public int findMaxHeight(int currentNode,int par){
        int ans=1;
        Iterator it = edgeNei.get(currentPos).iterator();
        while(it.hasNext()){
            int child = (int) it.next();
            if (par==child) continue;
            
            int tmp = travseGraph(child, currentNode);
            ===============================
            if (tmp+1 > ans) ans = tmp+1;
            ===============================
        }
        
        return ans;
    }
    
    
    public void travseGraph(int currentPos, , HashMap<Integer, Integer> edgeHeight, HashSet<Integer> marker){
        if (marker.contains(currentPos)) return;
        
        marker.add(currentPos);
        
        edgeHeight.put(currentPos, edgeHeight.getOrDefault(currentPos, 0) + 1);
        System.out.println("currentPos: " + currentPos);
        
        System.out.println("edgeNei.get(currentPos): ");
       
        Iterator it = edgeNei.get(currentPos).iterator();
        while(it.hasNext()){
            travseGraph((int) it.next(), edgeNei, edgeHeight, marker);
        }
        
    }
    
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // Use DFS + DP to search of the MHT. Will convert the edges into a hashMap
        // and the key will be the edge's num, and value will be the its neibougr.
        // will also use another hashmap to keep track of the height.
        // eventually return the min of the value of above hashmap.
        
        if (n == 0) return new ArrayList<Integer>();
        HashMap<Integer, HashSet<Integer>> edgeNei = new HashMap<>();
        
        // key as the edge and value as the count
        HashMap<Integer, Integer> edgeHeight = new HashMap<>();
        
        // Populate the edgeNei;
        for (int i = 0; i < n - 1; i++){
                int key1 = edges[i][0];
                int key2 = edges[i][1];
                edgeNei.getOrDefault(key1, new HashSet<Integer>()).add(key2);
            
            
                if (edgeNei.containsKey(key)){
                    System.out.println("xxxxxxxx-------: ") ;
                    
                    edgeNei.get(key).add(val);
                } else {
                    System.out.println("hhhhhhhhh-------: " );
                    
                    HashSet<Integer> hs = new HashSet<>();
                    hs.add(val);
                    edgeNei.put(key, hs);
                }
        }
        
        System.out.println("edgeNei-------: " + edgeNei.size());
        
        
        for (int i = 0; i < n; i++){
            HashSet<Integer> marker = new HashSet<>(); 
            travseGraph(i, edgeNei, edgeHeight, marker);
        }
        
        List<Integer> result = new ArrayList<Integer>();
        int max = 0;
        Iterator it = edgeHeight.entrySet().iterator();
        
        while (it.hasNext()){
            Map.Entry pair = (Map.Entry) it.next();
            int v = (int) pair.getValue();
            if (v > max) max = v;
        }
        
        while (it.hasNext()){
            Map.Entry pair = (Map.Entry) it.next();
            int k = (int) pair.getKey();
            if (k == max) result.add(new Integer(k));
        }
        
        return result;
    }
}
