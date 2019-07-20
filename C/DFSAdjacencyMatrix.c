#include <stdio.h>
#include <stdlib.h>

#define SIZE 10
/**
 * 
 * DFS using Adjacency Matrix. Runtime is O(V+E)
 * 
 */
/*
GRAPH to DFS/BFS
    1
    |
    2 - 3   8
    |   |   |
    5 - 4 - 7
     \    /
      6  10
       \ |
         9
*/

struct Vertex{
    int value;
    int visited;
};

void push(int prev[], int val, int *top){
    *top += 1;
    prev[*top] = val;
}

int pop(int prev[], int *top){
    int temp = prev[*top];
    *top -= 1;
    return temp;
}

int peek(int prev[], int top){
    return prev[top];
}

int isEmpty(int top){
    return top == -1;
}

void displayVertex(struct Vertex** lstVertices, int vertexIndex){
    printf("%d\n", lstVertices[vertexIndex]->value);
}

void addVertex(int val, struct Vertex** lstVertices, int *vertexCount){
    struct Vertex* vertex = (struct Vertex*)malloc(sizeof(struct Vertex));
    vertex->value = val;
    // 0 is false, 1 is true;
    vertex->visited = 0;
    lstVertices[*vertexCount] = vertex;
    *vertexCount += 1;
}

void addEdge(int start, int end, int adjMatrix[SIZE][SIZE]){
    adjMatrix[start][end] = 1;
    adjMatrix[end][start] = 1;
}

int getAdjUnvisitedVertice(int val, int vertexCount, int adjMatrix[SIZE][SIZE], struct Vertex** lstVertices){
    int i;
    for(i = 0; i < vertexCount; i++){
        if(adjMatrix[val][i] == 1 && lstVertices[i]->visited == 0){
            return i;
        }
    }
    return -1;
}

void depthFirstSearch(struct Vertex** lstVertices, int vertexCount, int prev[], int *top, int adjMatrix[SIZE][SIZE]){
    int i = 0;
    lstVertices[i]->visited = 1;
    displayVertex(lstVertices, i);
    push(prev, i, top);
    while(!isEmpty(*top)){
        int unVisitedVertex = getAdjUnvisitedVertice(peek(prev, *top), vertexCount, adjMatrix, lstVertices);
        if(unVisitedVertex == -1){
            pop(prev, top);
        }
        else{
            lstVertices[unVisitedVertex]->visited = 1;
            displayVertex(lstVertices, unVisitedVertex);
            push(prev, unVisitedVertex, top);
        }
    }


}

int main(){
    // Previous nodes order...
    int prev[SIZE];
    // stack top pointer, vertex count
    int top = -1, vertexCount = 0, i, j;
    // array of vertices
    struct Vertex** lstVertices = calloc(SIZE, sizeof(struct Vertex*));
    // adjaceny matrix
    int adjMatrix[SIZE][SIZE];
    for(i = 0; i < SIZE; i++)
        for(j = 0; j < SIZE; j++)
            adjMatrix[i][j] = 0;

    addVertex(1, lstVertices, &vertexCount);
    addVertex(2, lstVertices, &vertexCount);
    addVertex(3, lstVertices, &vertexCount);
    addVertex(4, lstVertices, &vertexCount);
    addVertex(5, lstVertices, &vertexCount);
    addVertex(6, lstVertices, &vertexCount);
    addVertex(7, lstVertices, &vertexCount);
    addVertex(8, lstVertices, &vertexCount);
    addVertex(9, lstVertices, &vertexCount);
    addVertex(10, lstVertices, &vertexCount);

    addEdge(0, 1, adjMatrix);
    addEdge(1, 2, adjMatrix);
    addEdge(1, 4, adjMatrix);
    addEdge(2, 3, adjMatrix);
    addEdge(3, 4, adjMatrix);
    addEdge(3, 6, adjMatrix);
    addEdge(4, 5, adjMatrix);
    addEdge(5, 8, adjMatrix);
    addEdge(6, 7, adjMatrix);
    addEdge(6, 9, adjMatrix);
    addEdge(8, 9, adjMatrix);

    printf("Depth First Search:\n");
    depthFirstSearch(lstVertices, vertexCount, prev, &top, adjMatrix);

    return 0;
}