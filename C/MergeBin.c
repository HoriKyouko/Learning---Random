#include <stdio.h>
#include <stdlib.h>

struct Coords{
    int x;
    int y;
};

FILE* openFile(char name[], char type[]){
    FILE* fp = fopen(name, type);
    if(fp == NULL){
        printf("Couldn't open the file!");
        exit(1);
    }
    return fp;
}

void writeToFile(struct Coords* coord, int num){
    FILE* fp = fopen("IO/out.txt", "w");
    if(fp == NULL){
        printf("Couldn't open the file!");
        exit(1);
    }
    int i;
    for(i = 0; i < num; i++)
        fprintf(fp, "%d %d\n", coord[i].x, coord[i].y);

    fclose(fp);
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
        if(L[i].x == R[j].x && L[i].y > R[j].y){
            coord[index].x = R[j].x;
            coord[index].y = R[j].y;
            j++;
        }
        else if(L[i].x == R[j].x && L[i].y < R[j].y){
            coord[index].x = L[i].x;
            coord[index].y = L[i].y;           
            i++;
        }
        else if(L[i].x < R[j].x){
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
    free(L);
    free(R);
}

void mergeSort(struct Coords* coord, int start, int end){
    if(start < end){
        int mid = (end + start)/2;
        mergeSort(coord, start, mid);
        mergeSort(coord, mid+1, end);
        merge(coord, start, mid, end);
    }
}

int binarySearch(struct Coords* coord, int start, int end, int x, int y){
    if(end >= start){
        int mid = (start + end) / 2;
        if(x == coord[mid].x && y == coord[mid].y){
            return mid;
        }
        if(x < coord[mid].x){
            return binarySearch(coord, start, mid-1, x, y);
        }
        return binarySearch(coord, mid+1, end, x, y);
    }
    return -1;
}

int main(){
    int numberOfElements, i = 0, x, y;
    FILE* fp = openFile("IO/in.txt", "r");

    fscanf(fp, "%d", &numberOfElements);
    
    struct Coords* coord = (struct Coords*)malloc(sizeof(struct Coords) * numberOfElements);
    
    while (!feof(fp)){
        fscanf(fp, "%d %d", &coord[i].x, &coord[i].y);
        i++;
    }  
    fclose(fp);

    mergeSort(coord, 0, numberOfElements-1);

    writeToFile(coord, numberOfElements);

    do{
        
        printf("Search input (x y): ");
        scanf("%d %d", &x, &y);

        if(x != -999999 && y != -999999){
            int temp = binarySearch(coord, 0, numberOfElements, x, y);
            if(temp != -1)
                printf("Found at record %d\n", temp+1);
            else
                printf("Not Found\n");
        }
    }while(x != -999999 && y != -999999);
    free(coord);
    return 0;
}