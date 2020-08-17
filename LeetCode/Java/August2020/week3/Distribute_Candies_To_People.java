public class Distribute_Candies_To_People {
    public int[] distributeCandies(int candies, int num_people) {
        int[] output = new int[num_people];
        int count = 1;
        
        for(int i = 0; i < num_people; i++){
            if(candies < count){
                output[i] += candies;
                candies = 0;
                break;
            }
            else{
                output[i] += count;
                candies -= count;
                count++;
            }
        }
        
        int j = 0;
        while(count < candies){
            output[j%num_people] += count;
            candies -= count;
            count++;
            j++;
        }
        output[j%num_people] += candies;
        return output;
    }
}