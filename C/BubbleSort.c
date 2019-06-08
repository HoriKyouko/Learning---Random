#include <stdio.h>
#include <stdlib.h>

void swap(int *num1, int *num2){
    int temp = *num1;
    *num1 = *num2;
    *num2 = temp;
}

void bubbleSort(int sortingList[], int length){
    int i, j;
    for(i = 0; i < length-1; i++){
        for(j = 0; j < length-i-1; j++){
            if(sortingList[j] > sortingList[j+1]){
                swap(&sortingList[j], &sortingList[j+1]);
            }
        }
    }
}

void printList(int sortingList[], int length){
    int i;
    for(i = 0; i < length; i++){
        printf("%d ", sortingList[i]);
    }
}

int main(){
    int sortingList[10] = {24, 4, 5, 96, 2, 83, 34, 78, 29, 40};
    bubbleSort(sortingList, 10);
    printList(sortingList, 10);
    return 0;
}