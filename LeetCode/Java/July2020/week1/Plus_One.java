public class Plus_One {
    public int[] plusOne(int[] digits) {
        int length = digits.length;
        int i = 1;
        if(digits[length-i] == 9 && length == 1){
            return new int[] {1,0};
        }
        else if(digits[length-i] == 9 && length != 1){
            while(digits[length-i] == 9) {
				digits[length-i] = 0;
				i++;
				if(length-i < 0)
					break;
			}
            if(length < i){
                int[] temp = new int[length+1];
                temp[0] = 1;
                return temp;
            }
            else{
                digits[length-i]++;
                return digits;
            }
        }
        digits[length-1]++;
        return digits;
    }
}