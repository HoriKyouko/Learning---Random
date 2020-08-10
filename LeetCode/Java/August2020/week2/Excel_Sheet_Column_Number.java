public class Excel_Sheet_Column_Number {
    public int titleToNumber(String s) {
        if(s.length() == 1)
            return s.charAt(0) - 'A' + 1;
        int sum = 0, factor = 1;
        for(int i = s.length()-1; i >= 0; i--){
            int val = s.charAt(i) - 'A' + 1;
            sum += val * factor;
            factor*=26;
        }
        return sum;
    }
}