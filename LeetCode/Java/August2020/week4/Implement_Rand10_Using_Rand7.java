public class Implement_Rand10_Using_Rand7 {
    public int rand10() {
        int i = 0;
        int j = 0;
        int val = 0;
        do{
            i = rand7();
            j = rand7();
            val = j + (i - 1) * 7;
        }while( val > 40);
        return 1 + (val - 1) % 10;
    }
}