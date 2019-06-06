#include <stdio.h>
#include <stdlib.h>

struct Node{
    int data;
    struct Node* next;
};

struct LinkedList{
    struct Node* root;
};

struct LinkedList* createLinkedList(){
    struct LinkedList* temp = (struct LinkedList*)malloc(sizeof(struct LinkedList));
    temp->root = NULL;
    return temp;
}

struct Node* createNode(int value){
    struct Node* temp = (struct Node*)malloc(sizeof(struct Node));
    temp->data = value;
    temp->next = NULL;
    return temp;
}

void addToList(struct LinkedList* list, int value){
    struct Node* node = createNode(value);
    struct Node* walk = list->root;

    if(list->root == NULL){
        /*
            Before list->root = node:

            null
            root
        */
        list->root = node;
        /*
            After list->root:

            [1]  ->  null
            root
        */
        return;
    }
    while(walk->next != NULL){
        /*
            Before walk = walk->next:

            [1]  ->  [3]  ->  [5]  ->  null
            root
            walk
        */
        walk = walk->next;
        /*
            After walk = walk->next:

            [1]  ->  [3]  ->  [5]  ->  null
            root
                     walk
        */
    }
    /*
        Before walk->next = node:

        [1]  ->  [3]  ->  [5]  ->  null
        root
                          walk
    */
    walk->next = node;
    /*
        After walk->next = node:

        [1]  ->  [3]  ->  [5]  ->  [4]  ->  null
        root
                          walk
    */
}

void removeFromList(struct LinkedList* list, int value){
    if(list->root == NULL){
        printf("The list is empty and can't remove anything!\n");
        return;
    }

    struct Node* tmp = list->root, *tmp2;
    if(list->root->data == value){
        /*
            Before list->root = tmp->next:

            [1]  ->  [3]  ->  [5]  ->  [4]  ->  [2]  ->  null
            root
            tmp      tmp->next
        */
        list->root = tmp->next;
        /*
            After list->root = tmp->next:

            [3]  ->  [5]  ->  [4]  ->  [2]  ->  null
            root
            tmp      tmp->next
        */
        free(tmp);
        return;
    }

    while(tmp != NULL && tmp->data != value){
        /*
            Before tmp2 = tmp:

            [1]  ->  [3]  ->  [5]  ->  [4]  ->  [2]  ->  null
            root
                              tmp
                     tmp2
        */
        tmp2 = tmp;
        /*
            After tmp2 = tmp & Before tmp = tmp->next:

            [1]  ->  [3]  ->  [5]  ->  [4]  ->  [2]  ->  null
            root
                              tmp
                              tmp2
        */
        tmp = tmp->next;
        /*
            After tmp = tmp->next:

            [1]  ->  [3]  ->  [5]  ->  [4]  ->  [2]  ->  null
            root
                                       tmp
                              tmp2
        */
    }
    
    if(tmp == NULL){
        printf("The value could not be found\n");
        return;
    }
    /*
        Before tmp2->next = tmp->next:

        [1]  ->  [3]  ->  [5]  ->  [4]  ->  [2]  ->  null
        root
        
                          tmp    tmp->next

                 tmp2     tmp2->next
    */
    tmp2->next = tmp->next;
    /*
        After tmp2->next = tmp->next & Before free(tmp):

        [1]  ->  [3]  ->  [5]  ->  [4]  ->  [2]  ->  null
        root
        
                          tmp    tmp->next

                 tmp2            tmp2->next
    */
    free(tmp);
    /*
        After free(tmp):

        [1]  ->  [3]  ->  [4]  ->  [2]  ->  null
        root
                 tmp2     tmp2->next
    */
}

void walkList(struct Node* node){
    while(node != NULL){
        printf("%d ", node->data);
        node = node->next;
    }
    printf("\n");
}
int main(){
    struct LinkedList* linkedList = createLinkedList();
    addToList(linkedList, 1);
    walkList(linkedList->root);
    addToList(linkedList, 3);
    walkList(linkedList->root);
    addToList(linkedList, 5);
    walkList(linkedList->root);
    addToList(linkedList, 4);
    walkList(linkedList->root);
    addToList(linkedList, 2);
    walkList(linkedList->root);

    removeFromList(linkedList, 9);
    walkList(linkedList->root);
    removeFromList(linkedList, 5);
    walkList(linkedList->root);
    removeFromList(linkedList, 1);
    walkList(linkedList->root);

    return 0;
}