#include <iostream>
#include <array>

std::array<int, 10> insertionSort(std::array<int, 10> sortingList){
    for(int i = 0; i < sortingList.size(); i++){
        int key = sortingList[i];
        int j = i-1;
        while(j >= 0 && sortingList[j] > key){
            sortingList[j+1] = sortingList[j];
            j-=1;
        }
        sortingList[j+1] = key;
    }
    return sortingList;
}

int main(){
    std::array<int, 10> sortingList = {24, 4, 5, 96, 2, 83, 34, 78, 29, 40};
    sortingList = insertionSort(sortingList);
    for(int i = 0; i < sortingList.size(); i++)
        std::cout << sortingList[i] << std::endl;

    return 0;
}