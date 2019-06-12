#include <stdio.h>
#include <stdlib.h>

#define length 20
#define cutoff 10
/**
 * Quick Sort Algorithm as written in Algorithms in a nutshell 2nd Ed.
 * 
 * Runtime:
 * 
 * Best: O(n log n)
 * Average: O(n log n)
 * Worse: O(n^2)
 */

/**
 * Simplistic compare operation where:
 * if the a and b are equal we return 0
 * else if a is less than b we return -1
 * else we return 1
 */
int compare(int a, int b){
    if(a == b)
        return 0;
    else if(a < b)
        return -1;
    else
        return 1;
}
/**
 * Simplistic swap function where:
 * temp gets value at arr[a]
 * arr[a] gets value at arr[b].
 * arr[b] gets value temp
 */
void swap(int *arr, int a, int b){
    int temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
}

void insertionSort(int* arr, int left, int right){
    int i, j, key;
    for(i = left+1; i <= right; i++){
        key = arr[i];
        for(j = i; j > left && compare(key, arr[j-1]) < 0; j--)
            arr[j] = arr[j-1];
        arr[j] = key;
    }
}

/**
 * Selects our pivot using the median of three method. This allows
 * us to pick the median of the left, center, and right index to
 * determine what is the median of those three values. This should
 * give us the middle value for that subarray.
 */
int selectPivot(int *arr, int left, int right){
    int middle = (left + right) / 2;
    if(compare(arr[middle], arr[left]) < 0)
        swap(arr, left, middle);
    if(compare(arr[right], arr[left]) < 0)
        swap(arr, left, right);
    if(compare(arr[right], arr[middle]) < 0)
        swap(arr, middle, right);

    // place pivot at position right - 1
    swap(arr, middle, right-1);
    return arr[right-1];
}

void quickSort(int* arr, int left, int right){
    // If we are below 10 values just call insertion sort.
    if(left + cutoff > right){
        insertionSort(arr, left, right);
    }
    // otherwise do quicksort
    else{
        int pivot = selectPivot(arr, left, right);
        int i = left, j = right-1;
        // continue to loop until i >= j
        while(1){
            // increment our i value till it's >= to pivot
            while(arr[++i] < pivot){}
            // decrement our j value till it's <= to pivot
            while(pivot < arr[--j]){}
            // if i is still less than j swap the two values
            if(i < j)
                swap(arr, i, j);
            else
                break;
        }
        // We finally swap our pivot with right-1
        swap(arr, i, right-1);
        // call quickSort with our left subarray.
        quickSort(arr, left, i - 1);
        // call quickSort with our right subarray.
        quickSort(arr, i + 1, right);
    }
}

int main(){
    // This is completely redundant but I don't want to read from a file in order to get these random
    // numbers. I'm also lazy and don't want to build a unique random number generator.
    int temp[20] = {17, 36, 11, 2, 43, 79, 83, 69, 28, 63, 78, 31, 74, 37, 15, 81, 3, 50, 42, 86};
    int* quick = (int*) malloc(sizeof(int) * length);
    int i;
    for(i = 0; i < length; i++)
        quick[i] = temp[i];
    
    quickSort(quick, 0, length - 1);
    /**
     * Sorted should appear as:
     * 2 3 11 15 17 28 31 36 37 42 43 50 63 69 74 78 79 81 83 86
     */
    for(i = 0; i < length; i++)
        printf("%d ", quick[i]);

    free(quick);
    return 0;
}