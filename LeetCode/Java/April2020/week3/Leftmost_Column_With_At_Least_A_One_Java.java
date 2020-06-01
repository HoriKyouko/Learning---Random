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
        
        int x = 0;
        int y = size.get(1)-1;
        int output = -1;
        
        while(x < size.get(0) && y >= 0){
            if(binaryMatrix.get(x,y) == 1)
                output = y--;
            else
                x++;
        }
        return output;
    }
}