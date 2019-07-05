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

void mergeSort(struct Coords* copy, struct Coords* coord, int start, int end){
    if(end - start < 2){
        return;
    }

    if(end-start == 2){
        if(coord[start].x > coord[start+1].x){
            int temp = coord[start].x;
            int temp2 = coord[start].y;
            coord[start].x = coord[start + 1].x;
            coord[start].y = coord[start + 1].y;
            coord[start+1].x = temp;
            coord[start+1].y = temp;
        }
        return;
    }

    int mid = (end+start)/2;
    mergeSort(copy, coord, start, mid);
    mergeSort(copy, coord, mid+1, end);

    int i = start, j = mid, index = start;
    // Sort two arrays into one array with same values going by
    // the y value to determine which goes first.
    while(index < end){
        if(copy[i].x == copy[j].x){
            if(copy[i].y < copy[j].y){
                coord[index].x = copy[i].x;
                coord[index].y = copy[i].y;
                i++;
            }
            else{
                coord[index].x = copy[j].x;
                coord[index].y = copy[j].y;
                j++;
            }
        }
        else if(j > end || (i < mid && copy[i].x < copy[j].x)){
            coord[index].x = copy[i].x;
            coord[index].y = copy[i].y;
            i++;
        }
        else{
            coord[index].x = copy[j].x;
            coord[index].y = copy[j].y;
            j++;
        }
        index++;
    }
}

void sort(struct Coords* coord, int start, int end){
    struct Coords* copy = coord;
    mergeSort(copy, coord, start, end);
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
    sort(coord, 0, numberOfElements);
    printf("\n\n");
    for(i = 0; i < numberOfElements; i++){
        printf("%d   %d\n", coord[i].x, coord[i].y);
    }
    // Sort by x coordinates using mergeSort. If x coordinates are the same convert by y coordinates...
    // Search the sorted coordinates continously until -999 and -999...
    return 0;
}