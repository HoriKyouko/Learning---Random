public class Flood_Fill {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int color = image[sr][sc];
        if(color != newColor) floodFilled(image, sr, sc, newColor, color);
        return image;
    }
    
    private void floodFilled(int [][] image, int r, int c, int newColor, int currentColor){
        if(image[r][c] == currentColor){
            image[r][c] = newColor;
            if(r >= 1) floodFilled(image, r-1, c, newColor, currentColor);
            if(r+1 < image.length) floodFilled(image, r+1, c, newColor, currentColor);
            if(c >= 1) floodFilled(image, r, c-1, newColor, currentColor);
            if(c+1 < image[0].length) floodFilled(image, r, c+1, newColor, currentColor);
        }
    }
}