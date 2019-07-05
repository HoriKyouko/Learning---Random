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

int main(){
    FILE* fp = fopen("in.txt", "r");
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

    // Sort by x coordinates using mergeSort. If x coordinates are the same convert by y coordinates...
    // Search the sorted coordinates continously until -999 and -999...
    return 0;
}