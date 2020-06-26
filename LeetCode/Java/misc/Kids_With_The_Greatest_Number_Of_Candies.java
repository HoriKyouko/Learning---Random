public class Kids_With_The_Greatest_Number_Of_Candies {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int largest = -1;
        for(int candy : candies)
            if(candy > largest)
                largest = candy;
        List<Boolean> output = new ArrayList<>();
        
        for(int candy :candies){
            if((candy + extraCandies) >= largest)
                output.add(true);
            else
                output.add(false);
        }
        return output;
    }
}