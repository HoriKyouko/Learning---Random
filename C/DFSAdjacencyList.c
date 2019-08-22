#include <stdio.h>
#include <stdlib.h>

#define SIZE 14
/**
 * 
 * DFS using Adjacency List. Runtime is O(V+E)
 * 
 * GRAPH to DFS/BFS
 * 
 * 1                11
 * |               /  \
 * 2 - 3   8      12  13
 * |   |   |       |
 * 5 - 4 - 7      14
 *  \     /
 *   6  10
 *    \ |
 *      9  
 */

struct Node{
    int vertex;
    struct Node* next;
};

void DFS(int start, struct Node** graph, int *visited){
    struct Node* temp;
    printf("%d ", start+1);
    temp = graph[start];
    visited[start] = 1;
    while(temp != NULL){
        start = temp->vertex;
        if(!visited[start])
            DFS(start, graph, visited);
        temp = temp->next;
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

int main(){
    int *visited = calloc(SIZE, sizeof(int));
    int i;
    struct Node** graph = calloc(SIZE, sizeof(struct Node*));
    
    graph = addEdge(0, 1, graph);
    graph = addEdge(1, 2, graph);
    graph = addEdge(1, 4, graph);
    graph = addEdge(2, 3, graph);
    graph = addEdge(3, 4, graph);
    graph = addEdge(3, 6, graph);
    graph = addEdge(4, 5, graph);
    graph = addEdge(5, 8, graph);
    graph = addEdge(6, 7, graph);
    graph = addEdge(6, 9, graph);
    graph = addEdge(8, 9, graph);
    graph = addEdge(10, 11, graph);
    graph = addEdge(10, 12, graph);
    graph = addEdge(11, 13, graph);


    for(i = 0; i < SIZE; i++)
        if(!visited[i])
            DFS(i, graph, visited);

    return 0;
}