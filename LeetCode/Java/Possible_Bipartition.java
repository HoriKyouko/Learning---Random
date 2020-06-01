public class Possible_Bipartition {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        int[] colors = new int[N+1];
        for(int i = 1; i <= N; i++)
            colors[i] = i;
        for(int i = 0; i < dislikes.length; i++){
            int p1 = dislikes[i][0];
            int p2 = dislikes[i][1];
            if(colors[p2] == p2){
                colors[p2] = p1;
            }
            else{
                int[] pair1 = find(p1, colors);
                int[] pair2 = find(p2, colors);
                if(pair1[0] == pair2[0] && pair1[1] == pair2[1])
                    return false;
            }
        }
        return true;
    }
    
    private int[] find(int p, int[] colors){
        int color = 0;
        while(colors[p] != p){
            p = colors[p];
            color = color == 0 ? 1 : 0;
        }
        return new int[]{p, color};
    }
}