#include <stdio.h>
#include <stdlib.h>

#define length 20

/**
 * Heap Sort Algorithm as written in Algorithms in a nutshell 2nd Ed.
 * 
 * Runtime:
 * 
 * Best: O(n log n)
 * Average: O(n log n)
 * Worse: O(n log n)
 */

/**
 * Simplistic compare operation where:
 * if the a and b are equal we return 0
 * else if a is less than b we return -1
 * else we return 1
 */
int compare(int a, int b){
    if(a == b)
        return 0;
    else if(a < b)
        return -1;
    else
        return 1;
}

/**
 * Goes through our array and ensures that we maintain both our shape and heap properties.
 * Uses a sort of "Bubbling Up" method where the largest value is bubbled to the top
 * recursively. This always makes sure we have a maxHeap. A minHeap would 
 * 
 * Shape property: A leaf node at depth k > 0 can exist only if all 2^(k-1) nodes at depth
 * k - 1 exist. Additionally, nodes at a partially filled level must be added "from left to
 * right". The root node has a depth of 0.
 * 
 * Heap Property: Each node in the tree contains a value greater than or equal to either of
 * its two children, if it has any.
 * 
 * Bubbling Up: Takes a child node and swaps it with it's parents node due to the child node
 * value being bigger than its parents value.
 * 
 * Bubble Up Example:           
 *    Swap 16 & 14          Swap 16 & 15                Final
 *           15                    15                    16
 *         /    \                /    \                /    \
 *        11      14     ->     11     16      ->     11     15
 *       /  \    /  \          /  \    /  \          /  \    /  \
 *      9   10  13  16        9   10  13  14        9   10  13  14
 */
void heapify(int* heap, int index, int size){
    // Gets our left child if it exists.
    int left = 2*index +1;
    // Gets our right child if it exists.
    int right = 2*index + 2;
    // Takes the assumption that our largest is the index
    int largest = index;
    // Checks if our left node is greater than it's parent node if it is largest number is our left node.
    if (left < size && compare(heap[left], heap[index]) > 0)
        largest = left;
        
    // Checks if our right node is greater than it's parent node if it is largest number is our right node.
    if(right < size && compare(heap[right], heap[largest]) > 0)
        largest = right;

    /**
     * If our largest value wasn't our index we need to swap our index with largest in the
     * array. This allows us to maintain "Bubbling Up" method of largest values to the top.
     * Since we swapped the value and "Bubbled Up" we may have to "Bubble Up" again if the
     * value we just swapped is now bigger than its parents value. Hence, the recursive call
     * of heapify (heap, largest, size); 
     */
    if(largest != index){
        int temp;
        temp = heap[index];
        heap[index] = heap[largest];
        heap[largest] = temp;

        heapify(heap, largest, size);
    }
}
/**
 * Driver function for building our Heap Sort algorithm.
 */
void buildHeap(int *heap, int size){
    int i;
    for(i = (size/2)-1; i>= 0; i--)
        heapify(heap, i, size);
}

int main(){
    // This is completely redundant but I don't want to read from a file in order to get these random
    // numbers. I'm also lazy and don't want to build a unique random number generator.
    int temp[20] = {17, 36, 11, 2, 43, 79, 83, 69, 28, 63, 78, 31, 74, 37, 15, 81, 3, 50, 42, 86};
    int* heap = (int*) malloc(sizeof(int) * length);
    int i;
    for(i = 0; i < length; i++)
        heap[i] = temp[i];
    
    buildHeap(heap, length);

    for(i = 0; i < length; i++)
        printf("%d ", heap[i]);

    free(heap);
    return 0;
}