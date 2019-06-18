#include <stdio.h>
#include <stdlib.h>

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

int binarySearchRecursive(int searchList[10], int start, int end, int num){
    if(end >= start){
        int mid = (start+end)/2;
        if(num == searchList[mid])
            return 1;
        if(num < searchList[mid])
            return binarySearchRecursive(searchList, start, mid-1, num);

        return binarySearchRecursive(searchList, mid+1, end, num);
    }
    return 0;
}

int binarySearchIterative(int searchList[10], int num){
    int low = 0, high = 9;
    while(low <= high){
        int mid = (low + high)/2;
        if(compare(num, searchList[mid]) < 0){
            high = mid - 1;
        }
        else if(compare(num, searchList[mid]) > 0){
            low = mid + 1;
        }
        else{
            return 1;
        } 
    }
    return 0;
}

int main(){
    int num = 5;
    int searchList[10] = {2, 4, 5, 24, 29, 34, 40, 78, 83, 96};
    /*
        Technically shouldn't be 10 I just know that there are only 10
        in the array. If we wanted to properly do it we'd do:

        int length = sizeof(searchList)/sizeof(searchList[0]);

        Or we could make a macro for it as well...
    */    
    binarySearchRecursive(searchList, 0, 10, 79) == 1 ? printf("We found your number!\n") : printf("We couldn't find your number!\n");
    binarySearchIterative(searchList, num) == 1 ? printf("We found your number!\n") : printf("We couldn't find your number!");
    return 0;
}