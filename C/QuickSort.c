#include <stdio.h>
#include <stdlib.h>

#define length 20

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

void swap(int *arr, int a, int b){
    int temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
}
/**
 * This may need work...
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

int main(){
    // This is completely redundant but I don't want to read from a file in order to get these random
    // numbers. I'm also lazy and don't want to build a unique random number generator.
    int temp[20] = {17, 36, 11, 2, 43, 79, 83, 69, 28, 63, 78, 31, 74, 37, 15, 81, 3, 50, 42, 86};
    int* quickSort = (int*) malloc(sizeof(int) * length);
    int i;
    for(i = 0; i < length; i++)
        quickSort[i] = temp[i];
    
    

    for(i = 0; i < length; i++)
        printf("%d ", quickSort[i]);

    free(quickSort);
    return 0;
}