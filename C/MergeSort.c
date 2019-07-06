#include <stdio.h>
#include <stdlib.h>

#define length 20

/**
 * Merge Sort Algorithm as written in Algorithms in a nutshell 2nd Ed.
 * 
 * EDIT: Found out later that this doesn't accept duplicates in the
 * array. Will need to find a work around for it...
 * 
 * EDIT2: Found a solution for allowing duplicates, but had to change up
 * my Omega(N) space complexity, but also allowed for a WAY simpler
 * solution that is easy to follow and understand.
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

void merge(int *array, int start, int mid, int end){
    int i, j, index = start;
    int len1 = mid - start + 1;
    int len2 = end - mid;
    int *L = (int*)calloc(len1, sizeof(int));
    int *R = (int*)calloc(len2, sizeof(int));

    for(i = 0; i < len1; i++){
        L[i] = array[start+i];
    }
    for(j = 0; j < len2; j++){
        R[j] = array[mid+j+1];
    }
    i = j = 0;

    while(i < len1 && j < len2){
        if(L[i] <= R[j]){
            array[index] = L[i];
            i++;
        }
        else{
            array[index] = R[j];
            j++;
        }
        index++;
    }

    while(i < len1){
        array[index] = L[i];
        i++;
        index++;
    }
    while(j < len2){
        array[index] = R[j];
        j++;
        index++;
    }
}

void mergeSort(int* array, int start, int end){
    if(start < end){
        int mid = (end+start)/2;
        mergeSort(array, start, mid);
        mergeSort(array, mid+1, end);
        merge(array, start, mid, end);
    }
}

int main(){
    // This is completely redundant but I don't want to read from a file in order to get these random
    // numbers. I'm also lazy and don't want to build a unique random number generator.
    int temp[] = {17, 36, 11, 2, 43, 79, 83, 69, 28, 63, 78, 31, 74, 37, 15, 81, 3, 50, 42, 86};
    //int temp[] = {2,2,5,1,4,3,7};
    int* array = (int*) malloc(sizeof(int) * length);
    int i;
    for(i = 0; i < length; i++)
        array[i] = temp[i];
    
    mergeSort(array, 0, length-1);

    for(i = 0; i < length; i++)
        printf("%d ", array[i]);

    free(array);
    return 0;
}
