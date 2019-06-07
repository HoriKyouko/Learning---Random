#include <iostream>
#include <array>

bool binarySearch(std::array<int, 10> searchingList, int start, int end, int num){
    if(end >= start){
        int mid = (start + end)/2;
        if (num == searchingList[mid])
            return true;
        if(num < searchingList[mid])
            return binarySearch(searchingList, start, mid-1, num);

        return binarySearch(searchingList, mid+1, end, num);
    }
    return false;
}

int main(){
    int num;
    std::array<int, 10> searchingList = {2, 4, 5, 24, 29, 34, 40, 78, 83, 96};
    std::cout << "Enter a number to look for: " << std::endl;
    std::cin >> num;
    (binarySearch(searchingList, 0, searchingList.size(), num)) ? std::cout << "We found your number!" << std::endl
                                                                : std::cout << "We couldn't find your number!" << std::endl; 
    return 0;
}