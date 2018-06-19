class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
	
	// Complexty analysis:
	// computation: m * n (assume m is the width of the 2d array, n is the height of the 2d array)
	// space: m * n (maintain the hashset)

        // Need to iterate through all the spot within the 2d array
        // Need to keep track of what had been visited
        // assume each row is the same length
        HashSet<String> hset = new HashSet<>();
        int numOfRows = grid.length;
        int numOfCols = grid[0].length;
        int result = 0;
        
        
        for (int h = 0; h < numOfRows; h++){
            for (int v = 0; v < numOfCols; v++){
                // if visited, then skip this round!
                if (hset.contains(h + ":" + v)){
                    continue;
                } else if (grid[h][v] == '0') {
                    hset.add(h + ":" + v);
                    continue;
                } 
                
                result++;
                searchIslands(grid, h, v, hset, numOfRows, numOfCols);
            }
            
        }
        return result;
    }
    
    public void searchIslands(char[][]grid, int h, int v, HashSet<String> hset, int numOfRows, int numOfCols){
        // make sure that the pointer doesn't go out of the boundary
        if (h < 0 || v < 0 || h >=numOfRows || v >= numOfCols) return;
        
        // if the point had been already visited, then return;
        if (hset.contains(h + ":" + v)) return;
        
        hset.add(h + ":" + v);
        
        if (grid[h][v] == '1'){
            
            // search 4 directons, left, right, up and down
            //left
            searchIslands(grid, h - 1, v, hset, numOfRows, numOfCols);
            //right
            searchIslands(grid, h + 1, v, hset, numOfRows, numOfCols);
            //up
            searchIslands(grid, h, v -1 , hset, numOfRows, numOfCols);
            //down
            searchIslands(grid, h, v + 1, hset, numOfRows, numOfCols);
           
        }
    }
    
}
