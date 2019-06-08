#include <stdio.h>
#include <stdlib.h>

int binarySearch(int searchList[10], int start, int end, int num){
    if(end >= start){
        int mid = (start+end)/2;
        if(num == searchList[mid])
            return 1;
        if(num < searchList[mid])
            return binarySearch(searchList, start, mid-1, num);

        return binarySearch(searchList, mid+1, end, num);
    }
    return 0;
}

int main(){
    int num;
    int searchList[10] = {2, 4, 5, 24, 29, 34, 40, 78, 83, 96};
    printf("Enter a number to look for: ");
    scanf("%d", &num);
    /*
        Technically shouldn't be 10 I just know that there are only 10
        in the array. If we wanted to properly do it we'd do:

        int length = sizeof(searchList)/sizeof(searchList[0]);

        Or we could make a macro for it as well...
    */    
    binarySearch(searchList, 0, 10, num) == 1 ? printf("We found your number!") : printf("We couldn't find your number!");
    return 0;
}