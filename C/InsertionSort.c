#include <stdio.h>
#include <stdlib.h>

void insertionSort(int sortingList[], int length){
    int i, j, key;
    for(i = 0; i < length; i++){
        key = sortingList[i];
        j = i-1;
        while(j >= 0 && sortingList[j] > key){
            sortingList[j+1] = sortingList[j];
            j-=1;
        }
        sortingList[j+1] = key;
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
    insertionSort(sortingList, 10);
    printList(sortingList, 10);
    return 0;
}