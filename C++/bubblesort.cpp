#include <iostream>
#include <array>

void swap(int *i, int *j){
    int temp = *i;
    *i = *j;
    *j= temp;
}

std::array<int,10> bubbleSort(std::array<int, 10> sortingList){
    for(int i = 0; i < sortingList.size()-1; i++)
        for(int j = 0; j < sortingList.size()-i-1; j++)
            if(sortingList[j] > sortingList[j+1])
                swap(&sortingList[j], &sortingList[j+1]);
    return sortingList;
}
// 24, 4, 5, 96, 2, 83, 34, 78, 29, 40
int main(){
    std::array<int, 10> sortingList = {24, 4, 5, 96, 2, 83, 34, 78, 29, 40};
    sortingList = bubbleSort(sortingList);
    for(int i = 0; i < sortingList.size(); i++)
        std::cout << sortingList[i] << std::endl;

    return 0;
}