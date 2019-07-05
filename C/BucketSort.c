#include <stdio.h>
#include <stdlib.h>

#define length 20

/**
 * Bucket Sort Algorithm as written in Algorithms in a nutshell 2nd Ed.
 * 
 * Runtime:
 * 
 * Best: O(n)
 * Average: O(n)
 * Worse: O(n)
 */

struct node{
    int element;
    struct node *next;
};
struct Bucket{
    int size;
    struct node *head;
};

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
 * Simplistic finding largest value in
 * a given integer array and returns it.
 */
int largest(int* array){
    int i, largest = array[0];
    for(i = 1; i < length; i++)
        if(array[i] > largest)
            largest = array[i];

    return largest;
}
/**
 * Simplistic hashing function where given
 * a number it will determine it's value
 * based on how many integer values we have.
 * This is determined by the define length
 * above.
 */
int hash(int d){
    int value = d/length;
    return value;
}
/**
 * Returns the maximum amount of buckets we
 * will need which is determined by the 
 * maximum value in the array divided by
 * length of our array and add one to the 
 * end to catch our highest values.
 */
int numBuckets(int* array){
    return (largest(array)/length) + 1;
}

void sortEachBucket(struct Bucket* buckets, int* array, int numBuckets){
    int i, low, index = 0;
    for(i = 0; i < numBuckets; i++){
        if(buckets[i].size == 0) continue;

        struct node* ptr = buckets[i].head, *temp;
        if(buckets[i].size == 1){
            array[index++] = ptr->element;
            free(ptr);
            buckets[i].size = 0;
            continue;
        }
        
        low = index;
        array[index++] = ptr->element;
        temp = ptr;
        ptr = ptr->next;
        free(temp);
        
        while(ptr != NULL){
            int i = index-1;
            while(i >= low && compare(array[i], ptr->element) > 0){
                array[i+1] = array[i];
                i--;
            }
            array[i+1] = ptr->element;
            temp = ptr;
            ptr = ptr->next;
            free(temp);
            index++;
        }
        
        buckets[i].size = 0;
    }
}

void bucketSort(int* array){
    int num = numBuckets(array);
    struct Bucket* buckets = (struct Bucket*)calloc(num, sizeof(struct Bucket));
    int i;
    for(i = 0; i < length; i++){
        int k = hash(array[i]);
        struct node* entry = (struct node*)calloc(1, sizeof(struct node));
        entry->element = array[i];
        if(buckets[k].head == NULL){
            buckets[k].head = entry;
        }
        else{
            entry->next = buckets[k].head;
            buckets[k].head = entry;
        }
        buckets[k].size++;
    }

    sortEachBucket(buckets, array, num);

    free(buckets);
}

int main(){
    // This is completely redundant but I don't want to read from a file in order to get these random
    // numbers. I'm also lazy and don't want to build a unique random number generator.
    int temp[20] = {17, 36, 11, 2, 43, 79, 83, 69, 28, 63, 78, 31, 74, 37, 15, 81, 3, 50, 42, 86};
    int* array = (int*) malloc(sizeof(int) * length);
    int i;
    for(i = 0; i < length; i++)
        array[i] = temp[i];
    
    bucketSort(array);

    for(i = 0; i < length; i++)
        printf("%d ", array[i]);

    free(array);
    return 0;
}
