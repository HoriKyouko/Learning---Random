#include <stdio.h>
#include <stdlib.h>

struct Node{
    int value;
    struct Node* left;
    struct Node* right;
};

struct BinaryTree{
    struct Node* root;
};

struct BinaryTree* createBinaryTree(){
    struct BinaryTree* temp = (struct BinaryTree*)malloc(sizeof(struct BinaryTree));
    temp->root = NULL;
    return temp;
}

struct Node* createBinaryTreeNode(int value){
    struct Node* temp = (struct Node*)malloc(sizeof(struct Node));
    temp->value = value;
    temp->left = temp->right = NULL;
    return temp;
}
struct Node* add(struct Node* node, int value){
    if(node == NULL)
        return createBinaryTreeNode(value);
    else if(value < node->value)
        node->left = add(node->left, value);
    else if(value > node->value)
        node->right = add(node->right, value);
    return node;
}

void inorderTraversal(struct Node* root){
    if(root != NULL){
        inorderTraversal(root->left);
        printf("%d ", root->value);
        inorderTraversal(root->right);
    }
}

int contains(struct Node* node, int key){
    while(node != NULL){
        if(key < node->value)
            node = node->left;
        else if( key > node->value)
            node = node->right;
        else
            return 1;
    }
    return 0;
}

int main(){

    struct BinaryTree* bTree = createBinaryTree();
    bTree->root = add(bTree->root, 50);
    add(bTree->root, 30);
    add(bTree->root, 20);
    add(bTree->root, 40);
    add(bTree->root, 70);
    add(bTree->root, 60);
    add(bTree->root, 80);

    inorderTraversal(bTree->root);
    return 0;
}