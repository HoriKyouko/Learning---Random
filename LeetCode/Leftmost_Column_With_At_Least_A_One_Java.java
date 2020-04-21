/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface BinaryMatrix {
 *     public int get(int x, int y) {}
 *     public List<Integer> dimensions {}
 * };
 */

class Solution {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> size = binaryMatrix.dimensions();
        int[][] memo = new int[size.get(0)][size.get(1)];
        
        int x = 0;
        int y = size.get(1)-1;
        int output = -1;
        
        while(x < size.get(0) && y >= 0){
            int val = binaryMatrix.get(x,y);
            if(val == 1)
                output = y--;
            else
                x++;
        }
        return output;
    }
}