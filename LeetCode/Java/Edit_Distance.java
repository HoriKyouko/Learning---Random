public class Edit_Distance {
    public int minDistance(String word1, String word2) {
        if(word1.length() == 0 || word2.length() == 0) return Math.max(word1.length(), word2.length());
        int[][] output = new int[word1.length()+1][word2.length()+1];
        
        for(int i = 0; i <= word1.length(); i++)
            output[i][0] = i;
        for(int i = 0; i <= word2.length(); i++)
            output[0][i] = i;
        
        for(int i = 1; i <= word1.length(); i++){
            for(int j = 1; j <= word2.length(); j++){
                if(word1.charAt(i-1) == word2.charAt(j-1))
                    output[i][j] = output[i-1][j-1];
                else
                    output[i][j] = Math.min(output[i-1][j-1], Math.min(output[i][j-1], output[i-1][j])) + 1;
            }
        }
        /*for(int i = 0; i < output.length; i++){
            for(int j = 0; j < output[0].length; j++){
                System.out.print(output[i][j] + " ");
            }
            System.out.println();
        }*/
        
        return output[word1.length()][word2.length()];
    }
}