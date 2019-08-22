#include <stdio.h>
#include <stdlib.h>

#define SIZE 14

/**
 * 
 * DFS using Adjacency List. Runtime is O(V+E)
 * 
 * GRAPH for BFS
 * 
 *          1           11
 *       / / | \       /  \
 *      2  3 4  5     12  14
 *     /\ /  \ /       |
 *    6  7    8       13
 *        \  /
 *          9
 *          |
 *         10
 */

struct Node{
    int vertex;
    struct  Node* next;
};

struct Queue{
    int items[SIZE];
    int front;
    int rear;
};

int isEmpty(struct Queue* queue){
    return queue->rear == -1 ? 1 : 0;
}

void enqueue(struct Queue* queue, int val){
    if(queue->rear == SIZE-1){
        printf("Queue is full!\n");
    }
    else{
        if(queue->front == -1)
            queue->front = 0;
        queue->rear++;
        queue->items[queue->rear] = val;
    }
}

int dequeue(struct Queue* queue){
    if(isEmpty(queue)){
        printf("Queue is Empty!\n");
        return -1;
    }
    else{
        int temp = queue->items[queue->front];
        queue->front++;
        if(queue->front > queue->rear){
            //printf("Resetting queue\n");
            queue->front = queue->rear = -1;
        }
        return temp;
    }
}

void printQueue(struct Queue* queue){
    int i;
    if(isEmpty(queue)){
        printf("Queue is Empty!\n");
    }
    else{
        printf("\nQueue contains: ");
        for(i = queue->front; i < queue->rear + 1; i++){
            printf("%d ", queue->items[i]);
        }
        printf("\n");
    }
}

struct Node** addEdge(int start, int end, struct Node** graph){
    struct Node* temp = (struct Node*)malloc(sizeof(struct Node));
    temp->next = NULL;
    temp->vertex = end;

    if(graph[start] == NULL){
        graph[start] = temp;
    }
    else{
        struct Node* ptr = graph[start];
        while(ptr->next != NULL){
            ptr = ptr->next;
        }
        ptr->next = temp;
    }
    return graph;
}

struct Queue* createQueue(){
    struct Queue* temp = (struct Queue*)malloc(sizeof(struct Queue));
    temp->front = temp->rear = -1;
    return temp;
}

void BFS(int start, struct Node** graph, int* visited){
    struct Queue* queue = createQueue();
    visited[start] = 1;
    enqueue(queue, start);
    while(!isEmpty(queue)){
        //printQueue(queue);
        int currentVertex = dequeue(queue);
        printf("%d ", currentVertex + 1);
        struct Node* temp = graph[currentVertex];
        while(temp){
            int adjVertex = temp->vertex;
            if(visited[adjVertex] == 0){
                visited[adjVertex] = 1;
                enqueue(queue, adjVertex);
            }
            temp = temp->next;
        }
    }
}

int main(){
    int i;
    int *visited = calloc(SIZE, sizeof(int));
    struct Node** graph = calloc(SIZE, sizeof(struct Node*));

    graph = addEdge(0, 1, graph);
    graph = addEdge(0, 2, graph);
    graph = addEdge(0, 3, graph);
    graph = addEdge(0, 4, graph);
    graph = addEdge(1, 5, graph);
    graph = addEdge(1, 6, graph);
    graph = addEdge(2, 6, graph);
    graph = addEdge(3, 7, graph);
    graph = addEdge(4, 7, graph);
    graph = addEdge(6, 8, graph);
    graph = addEdge(7, 8, graph);
    graph = addEdge(8, 9, graph);
    graph = addEdge(10, 11, graph);
    graph = addEdge(10, 13, graph);
    graph = addEdge(11, 12, graph);

    for(i = 0; i < SIZE; i++){
        if(!visited[i]){
            BFS(i, graph, visited);
        }
    }
    return 0;
}