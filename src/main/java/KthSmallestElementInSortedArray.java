class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Tuple> pq = new PriorityQueue<>((a, b) -> (a.val - b.val));
        
        //firstly populate the pq
        for (int i = 0; i < matrix.length; i++){
            pq.add(new Tuple(matrix[i][0], i, 0));
        }
        
        int result = 0;
        
        for (int i = 0; i < k; i++){
            Tuple tul = pq.poll();
            result = tul.val;
            if (tul.col < matrix[tul.row].length - 1) pq.add(new Tuple(matrix[tul.row][tul.col + 1], tul.row, tul.col + 1));
        }
        
        return result;
    }
    
    
    class Tuple {
        int val;
        int row;
        int col;
        
        public Tuple(int v, int i, int c){
            this.val = v;
            this.row = i;
            this.col = c;
        }
    }
}
