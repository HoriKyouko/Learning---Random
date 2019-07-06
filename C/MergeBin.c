#include <stdio.h>
#include <stdlib.h>

#define MAXCHAR 10000

struct Coords{
    int x;
    int y;
};

FILE* openFile(char name[], char type[]){
    FILE* fp = fopen(name, type);
    if(fp == NULL)
        return NULL;
    return fp;
}

void merge(struct Coords* coord, int start, int mid, int end){
    int i, j, index = start;
    int len1 = mid - start + 1;
    int len2 = end - mid;
    struct Coords* L = (struct Coords*)malloc(sizeof(struct Coords) * len1);
    struct Coords* R = (struct Coords*)malloc(sizeof(struct Coords) * len2);

    for(i = 0; i < len1; i++){
        L[i].x = coord[start+i].x;
        L[i].y = coord[start+i].y;
    }

    for(j = 0; j < len2; j++){
        R[j].x = coord[mid+j+1].x;
        R[j].y = coord[mid+j+1].y;
    }

    i = j = 0;
    while(i < len1 && j < len2){
        if(L[i].x <= R[j].x){
            coord[index].x = L[i].x;
            coord[index].y = L[i].y;
            i++;
        }
        else{
            coord[index].x = R[j].x;
            coord[index].y = R[j].y;
            j++;
        }
        index++;
    }

    while(i < len1){
        coord[index].x = L[i].x;
        coord[index].y = L[i].y;
        i++;
        index++;
    }

    while(j < len2){
        coord[index].x = R[j].x;
        coord[index].y = R[j].y;
        j++;
        index++;
    }
}

void mergeSort(struct Coords* coord, int start, int end){
    if(start < end){
        int mid = (end + start)/2;
        mergeSort(coord, start, mid);
        mergeSort(coord, mid+1, end);
        merge(coord, start, mid, end);
    }
}

int main(){
    FILE* fp = openFile("in.txt", "r");
    if(fp == NULL){
        printf("Couldn't open the file");
        return 0;
    }
    char str[MAXCHAR];
    int numberOfElements, i = 0;
    fscanf(fp, "%d", &numberOfElements);
    // Array of Coords.
    struct Coords* coord = (struct Coords*)malloc(sizeof(struct Coords) * numberOfElements);
    while (!feof(fp)){
        int x, y;
        fscanf(fp, "%d %d", &coord[i].x, &coord[i].y);
        i++;
    }
    fclose(fp);
    for(i = 0; i < numberOfElements; i++){
        printf("%d   %d\n", coord[i].x, coord[i].y);
    }


    mergeSort(coord, 0, numberOfElements-1);
    
    
    printf("\n\n");
    for(i = 0; i < numberOfElements; i++){
        printf("%d   %d\n", coord[i].x, coord[i].y);
    }
    // Sort by x coordinates using mergeSort. If x coordinates are the same convert by y coordinates...
    // Search the sorted coordinates continously until -999 and -999...
    return 0;
}