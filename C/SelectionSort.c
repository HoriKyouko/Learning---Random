#include <stdio.h>
#include <stdlib.h>

void swap(int *num1, int *num2){
    int temp = *num1;
    *num1 = *num2;
    *num2 = temp;
}

void selectionSort(int sortingList[], int length){
    int i, j, min;
    for(i = 0; i < length-1; i++){
        min = i;
        for(j = i+1; j < length; j++){
            if(sortingList[j] < sortingList[min]){
                min = j;
            }
        }
        swap(&sortingList[i], &sortingList[min]);
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
    selectionSort(sortingList, 10);
    printList(sortingList, 10);
    return 0;
}