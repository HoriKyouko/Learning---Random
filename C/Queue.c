#include <stdio.h>
#include <stdlib.h>

struct Node{
    int data;
    struct Node* next;
};

struct Queue{
    struct Node* head;
    struct Node* tail;
};

struct Queue* createQueue(){
    // Allocates memory for our queue and sets the head and tail to null.
    struct Queue* temp = (struct Queue*)malloc(sizeof(struct Queue));
    temp->head = temp->tail = NULL;
    return temp;
}

struct Node* createNode(int value){
    // Allocates memory for our Node and sets the data to value and it's
    // next node to null.
    struct Node* temp = (struct Node*)malloc(sizeof(struct Node));
    temp->data = value;
    temp->next = NULL;
    return temp;
}

void add(struct Queue* queue, int value){
    // Creates our node with the value we want to store.
    struct Node* node = createNode(value);
    // Checks to see if our tail is null if it is then the queue
    // is empty.
    if(queue->tail == NULL){
        // Sets queue head and tail to the first node
        queue->head = queue->tail = node;
        return;
    }
    // sets our tails next to the node and then moves our tail over
    /*
        Before queue->tail->next:

        [5]  ->  [6]  ->  [89]  ->  [10]  ->  [32]  ->  null
        head                                  tail
    */
    queue->tail->next = node;
    /*
        After queue->tail->next & before queue->tail:

        [5]  ->  [6]  ->  [89]  ->  [10]  ->  [32]  ->  [value]  ->  null
        head                                  tail
    */
    queue->tail = node;
    /*
        After queue->tail:

        [5]  ->  [6]  ->  [89]  ->  [10]  ->  [32]  ->  [value]  ->  null
        head                                             tail
    */
}

void removeQueue(struct Queue* queue){
    if(queue->head == NULL){
        return;
    }
    /*
        Before temp = queue->head:

        [5]  ->  [6]  ->  [89]  ->  [10]  ->  [32]  ->  [value]  ->  null
        head                                             tail
    */
    struct Node* temp = queue->head;
    /*
        After temp = queue->head & Before queue->head = queue->head->next:

        [5]  ->  [6]  ->  [89]  ->  [10]  ->  [32]  ->  [value]  ->  null
        head                                             tail
        temp
    */
    queue->head = queue->head->next;
    /*
        After queue->head = queue->head->next:

        [5]  ->  [6]  ->  [89]  ->  [10]  ->  [32]  ->  [value]  ->  null
        temp     head                                    tail
    */

    if(queue->head == NULL){
        queue->tail == NULL;
    }

    printf("Removing item: %d\n", temp->data);
    free(temp);
    /*
        After free(temp):

        [6]  ->  [89]  ->  [10]  ->  [32]  ->  [value]  ->  null
        head                                    tail
    */
}

void walkList(struct Node* node){
    // Continues looping through until we have reached the end of our queue
    while(node != NULL){
        printf("%d ", node->data);
        node = node->next;
    }
    printf("\n");
}

int main(){
    // Creates our Queue which contains a head and tail node.
    struct Queue* queue = createQueue();
    // Adds the given value to the queue
    add(queue, 1);
    // Walks through the queue to print out the queue for debug reasons.
    walkList(queue->head);
    add(queue, 3);
    walkList(queue->head);
    add(queue, 5);
    walkList(queue->head);
    add(queue, 4);
    walkList(queue->head);
    add(queue, 2);
    walkList(queue->head);

    // Removes the first item from the queue.
    removeQueue(queue);
    removeQueue(queue);

    walkList(queue->head);
    add(queue, 1);
    walkList(queue->head);
    add(queue, 3);
    walkList(queue->head);

    return 0;
}