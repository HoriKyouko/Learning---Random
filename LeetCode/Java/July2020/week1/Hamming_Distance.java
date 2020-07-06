public class Hamming_Distance {
    public int hammingDistance(int x, int y) {
        int dist = 0;
        int val = x ^ y;
        while(val != 0){
            dist++;
            val &= val - 1;
        }
        return dist;
    }
}