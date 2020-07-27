public class Add_Digits {
    public int addDigits(int num) {
        if(num < 10)
            return num;
        while(num >= 10)
            num = sumNum(num, 0);
        return num;
    }
    
    private int sumNum(int num, int sum){
        while(num != 0){
            sum += num %10;
            num /=10;
        }
        return sum;
    }
}