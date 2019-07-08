#include <stdio.h>
#include <stdlib.h>

struct Node{
    int height;
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
    temp->height = 1;
    temp->left = temp->right = NULL;
    return temp;
}

int computeHeight(struct Node* node){
    if(node == NULL){
        return 0;
    }
    return node->height;
}

int getBalance(struct Node* node){
    if(node == NULL){
        return 0;
    }
    return computeHeight(node->left) - computeHeight(node->right);
}

int maxI(int num1, int num2){
    return (num1 > num2) ? num1 : num2;
}

struct Node* leftRotate(struct Node* node){
    struct Node* x = node->right;
    struct Node* temp = x->left;

    x->left = node;
    node->right = temp;

    node->height = maxI(computeHeight(node->left), computeHeight(node->right)) + 1;
    x->height = maxI(computeHeight(x->left), computeHeight(x->right)) + 1;
    
    return x;
}

struct Node* rightRotate(struct Node* node){
    struct Node* x = node->left;
    struct Node* temp = x->right;

    x->right = node;
    node->left = temp;
    
    node->height = maxI(computeHeight(node->left), computeHeight(node->right)) + 1;
    x->height = maxI(computeHeight(x->left), computeHeight(x->right)) + 1;
    
    return x;
}

struct Node* minValueNode(struct Node* node){
    struct Node* current = node;
    while(current->left != NULL){
        current = current->left;
    }
    return current;
}

struct Node* add(struct Node* node, int value){
    if(node == NULL)
        return createBinaryTreeNode(value);
    else if(value < node->value)
        node->left = add(node->left, value);
    else if(value > node->value)
        node->right = add(node->right, value);
    else
        return node;
    
    node->height = 1 + maxI(computeHeight(node->left), computeHeight(node->right));
    int balance = getBalance(node);

    // Left Left Case
    if(balance > 1 && value < node->left->value){
        return rightRotate(node);
    } 
    // Right Right Case
    if(balance < -1 && value > node->right->value){
        return leftRotate(node);
    }
    // Left Right Case
    if(balance > 1 && value > node->left->value){
        node->left = leftRotate(node->left);
        return rightRotate(node);
    }
    // Right Left Case
    if(balance < -1 && value < node->right->value){
        node->right = rightRotate(node->right);
        return leftRotate(node);
    }

    return node;
}

struct Node* removeNode(struct Node* node, int value){
    if(node == NULL){
        return node;
    }
    else if( value < node->value){
        node->left = removeNode(node->left, value);
    }
    else if(value > node->value){
        node->right = removeNode(node->right, value);
    }
    else{
        if((node->left == NULL) || (node->right == NULL)){
            struct Node* temp = node->left ? node->left : node->right;
            // No child case
            if(temp == NULL){
                temp = node;
                node = NULL;
            }
            // One child case
            else{
                *node = *temp;
            }
            free(temp);
        }
        else{
            struct Node* temp = minValueNode(node->right);
            node->value = temp->value;
            node->right = removeNode(node->right, temp->value);
        }
    }
    if(node == NULL){
        return node;
    }
    node->height = 1 + maxI(computeHeight(node->left), computeHeight(node->right));
    int balance = getBalance(node);

    // Left Left Case
    if(balance > 1 && getBalance(node->left) >= 0){
        return rightRotate(node);
    } 
    // Right Right Case
    if(balance < -1 && getBalance(node->right) <= 0){
        return leftRotate(node);
    }
    // Left Right Case
    if(balance > 1 && getBalance(node->left) < 0){
        node->left = leftRotate(node->left);
        return rightRotate(node);
    }
    // Right Left Case
    if(balance < -1 && getBalance(node->right) > 0){
        node->right = rightRotate(node->right);
        return leftRotate(node);
    }

    return node;
}

void inorderTraversal(struct Node* root){
    if(root != NULL){
        inorderTraversal(root->left);
        printf("%d ", root->value);
        inorderTraversal(root->right);
    }
}

void preorderTraversal(struct Node* root){
    if(root != NULL){
        printf("%d ", root->value);
        preorderTraversal(root->left);
        preorderTraversal(root->right);
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
    bTree->root = add(bTree->root, 30);
    bTree->root = add(bTree->root, 20);
    bTree->root = add(bTree->root, 40);
    bTree->root = add(bTree->root, 70);
    bTree->root = add(bTree->root, 60);
    bTree->root = add(bTree->root, 80);

    struct BinaryTree* bTree2 = createBinaryTree();
    bTree2->root = add(bTree2->root, 10);
    bTree2->root = add(bTree2->root, 20);
    bTree2->root = add(bTree2->root, 30);
    bTree2->root = add(bTree2->root, 40);
    bTree2->root = add(bTree2->root, 50);
    bTree2->root = add(bTree2->root, 25);

    struct BinaryTree* bTree3 = createBinaryTree();
    bTree3->root = add(bTree3->root, 9);
    bTree3->root = add(bTree3->root, 5);
    bTree3->root = add(bTree3->root, 10);
    bTree3->root = add(bTree3->root, 0);
    bTree3->root = add(bTree3->root, 6);
    bTree3->root = add(bTree3->root, 11);
    bTree3->root = add(bTree3->root, -1);
    bTree3->root = add(bTree3->root, 1);
    bTree3->root = add(bTree3->root, 2);

    inorderTraversal(bTree->root);
    printf("\n");
    preorderTraversal(bTree2->root);
    printf("\n");
    preorderTraversal(bTree3->root);
    printf("\n");

    bTree3->root = removeNode(bTree3->root, 10);

    preorderTraversal(bTree3->root);
    printf("\n");
    return 0;
}