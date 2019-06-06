#include <iostream>
#include <array>

void swap(int *i, int *j){
    int temp = *i;
    *i = *j;
    *j= temp;
}

std::array<int, 10> selectionSort(std::array<int, 10> sortingList){
    for(int i = 0; i < sortingList.size() - 1; i++){
        int min = i;
        for(int j = i+1; j < sortingList.size(); j++)
            if(sortingList[j] < sortingList[min])
                min = j;

        swap(&sortingList[i], &sortingList[min]);
    }
    return sortingList;
}

int main(){
    std::array<int, 10> sortingList = {24, 4, 5, 96, 2, 83, 34, 78, 29, 40};
    sortingList = selectionSort(sortingList);
    for(int i = 0; i < sortingList.size(); i++)
        std::cout << sortingList[i] << std::endl;

    return 0;
}