import java.util.Random;
/**
    Implementation of American Flag sort.

    Found on a youtube 50+ sorts visualized. Can't find the link :/

    Essentially takes a normal Radix sort and improves upon it.
 */
public class AmericanFlagSort{

    private static final int SIZE_OF_ARRAY = 1000000;
    private static final int NUM_OF_BUCKETS = 256;

    public static void main(String args[]){
        int[] arr = populateArray();

        /* Uncomment if you want to view starting result.
        WARNING: Recommend lowering size of array to something reasonable
        say < 250
        
        System.out.print("Unsorted array: ");
        for(int i = 0; i < SIZE_OF_ARRAY; i++)
            System.out.print(arr[i] + " ");
        
        System.out.print("\n\n");
        */
        
        flagSort(arr);
        
        /* Uncomment if you want to view ending result.
        WARNING: Recommend lowering size of array to something reasonable
        say < 250

        System.out.print("Sorted array: ");
        for(int i = 0; i < SIZE_OF_ARRAY; i++)
            System.out.print(arr[i] + " ");
        */
        if(validSort(arr))
            System.out.println("\nArray is sorted");
        else
            System.out.println("\nArray is not sorted");
    }

    private static int[] populateArray(){
        int [] arr = new int[SIZE_OF_ARRAY];
        for(int i = 0; i < SIZE_OF_ARRAY; i++)
            arr[i] = new Random().nextInt(SIZE_OF_ARRAY);

        return arr;
    }

    private static void flagSort(int[] unsorted){
        int numOfDigits = getMaxNumberOfDigits(unsorted);
        int max = 1;
       
        for(int i = 0; i < numOfDigits - 1; i++)
            max *= 10;
        
        flagSort(unsorted, 0, SIZE_OF_ARRAY, max);
    }

    private static void flagSort(int[] unsorted, int start, int len, int divisor){
        int[] count = new int[NUM_OF_BUCKETS];
        int[] offset = new int[NUM_OF_BUCKETS];

        int digit = 0;
        
        for(int i = start; i < len; i++){
            int d = unsorted[i];
            digit = getDigit(d, divisor);
            count[digit]++;
        }

        offset[0] = start + 0;
        for(int i = 1; i < NUM_OF_BUCKETS; i++)
            offset[i] = count[i - 1] + offset[i - 1];
        
        for(int bucket = 0; bucket < NUM_OF_BUCKETS; bucket++){
            while(count[bucket] > 0){
                int origin = offset[bucket];
                int from = origin;
                int num = unsorted[from];
                unsorted[from] = -1;
                do{
                    digit = getDigit(num, divisor);
                    int to = offset[digit]++;
                    count[digit]--;
                    int temp = unsorted[to];
                    unsorted[to] = num;
                    num = temp;
                    from = to;
                }while(from != origin);
            }
        }
        if(divisor > 1){
            for(int i = 0; i < NUM_OF_BUCKETS; i++){
                int begin = (i > 0) ? offset[i - 1] : start;
                int end = offset[i];
                if(end - begin > 1)
                    flagSort(unsorted, begin, end, divisor / 10);
            }
        }
    }

    private static int getMaxNumberOfDigits(int[] unsorted){
        int max = Integer.MIN_VALUE;
        int temp = 0;
        for(int i : unsorted){
            temp = (int) Math.log10(i) + 1;
            if(temp > max)
                max = temp;
        }
        return max;
    }

    private static int getDigit(int i, int d){
        return (i / d) % 10;
    }
    
    /**
        Simply checks to make sure that our array is valid, greater than 1
        and that either value is greater than or equal to the previous value.

        Runtime is O(N) where N is size of array to be sorted.
    */ 
    private static boolean validSort(int[] arr){
        for (int i = 1; i < SIZE_OF_ARRAY; i++)
            if(arr[i] < arr[i-1])
                return false;
        return true;
    }
}
