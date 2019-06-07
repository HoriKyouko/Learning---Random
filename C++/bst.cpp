#include <iostream>

struct Node{
    int value;
    Node *left, *right;
    
    Node(int val){
        this->value = val;
    }
};

void inorderTraversal(Node *node){
    if(node != NULL){
        inorderTraversal(node->left);
        std::cout << "The value is: " << node->value << std::endl;
        inorderTraversal(node->right);
    }
}

// Numbers for tree will be: 62, 5, 64, 27, 99, 85, 67, 75, 19, 76

int main(){
    Node *root = new Node(62);
    if(root->left == NULL){
        std::cout << "LEFT NODE NULL" << std::endl;
    }
    
    if(root->right == NULL){
        std::cout << "RIGHT NODE NULL" << std::endl;
    }
    root->left = new Node(5);
    root->right = new Node(64);
    std::cout << root->value << std::endl;
    std::cout << "LEFT NODE" << std::endl;
    std::cout << root->left->value << std::endl;
    std::cout << "RIGHT NODE" << std::endl;
    std::cout << root->right->value << std::endl;
    
    return 0;
}
