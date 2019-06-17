#include <stdio.h>
#include <stdlib.h>

#define length 20

/**
 * Merge Sort Algorithm as written in Algorithms in a nutshell 2nd Ed.
 * 
 * Runtime:
 * 
 * Best: O(n log n)
 * Average: O(n log n)
 * Worse: O(n log n)
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

void mergeSort(int* copy, int* array, int start, int end){
    if(end - start < 2)
        return;

    if(end-start == 2){
        if(array[start] > array[start+1]){
            int temp = array[start];
            array[start] = array[start+1];
            array[start+1] = temp;
        }
        return;
    }

    int mid = (end+start)/2;
    mergeSort(copy, array, start, mid);
    mergeSort(copy, array, mid+1, end);

    int i = start, j = mid, index = start;
    while(index < end){
        if(j >= end || (i < mid && copy[i] < copy[j])){
            array[index] = copy[i];
            i++;
        }
        else{
            array[index] = copy[j];
            j++;
        }
        index++;
    }
}

void sort(int* array){
    int* copy = array;
    mergeSort(copy, array, 0, length);
}

int main(){
    // This is completely redundant but I don't want to read from a file in order to get these random
    // numbers. I'm also lazy and don't want to build a unique random number generator.
    int temp[20] = {17, 36, 11, 2, 43, 79, 83, 69, 28, 63, 78, 31, 74, 37, 15, 81, 3, 50, 42, 86};
    int* array = (int*) malloc(sizeof(int) * length);
    int i;
    for(i = 0; i < length; i++)
        array[i] = temp[i];
    
    sort(array);

    for(i = 0; i < length; i++)
        printf("%d ", array[i]);

    free(array);
    return 0;
}
