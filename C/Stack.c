#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <math.h>

struct Stack{
    int top;
    int capacity;
    char *arr;
};

struct StackInt{
    int top;
    int capacity;
    int *arr;
};

int isEmpty(int top){
    if(top == -1)
        return 1;
    return 0;
}

char peek(struct Stack *stack){
    if(isEmpty(stack->top))
        printf("The stack is empty");
    else
        return stack->arr[stack->top];
    return -1;
}

char pop(struct Stack *stack){
    char val = stack->arr[stack->top];
    stack->top--;
    return val;
}

int pop_int(struct StackInt *stack){
    int val = stack->arr[stack->top];
    stack->top--;
    return val;
}

void push(struct Stack *stack, char val){
    stack->top++;
    stack->arr[stack->top] = val;
}

void push_int(struct StackInt *stack, int val){
    stack->top++;
    stack->arr[stack->top] = val;
}

struct Stack* createStack(int capacity){
    struct Stack* temp = (struct Stack*)malloc(sizeof(struct Stack));
    temp->top = -1;
    temp->capacity = capacity;
    temp->arr = (char*)calloc(0, capacity * sizeof(char));
    return temp;
}

struct StackInt* createStackInt(int capacity){
    struct StackInt* temp = (struct StackInt*)malloc(sizeof(struct StackInt));
    temp->top = -1;
    temp->capacity = capacity;
    temp->arr = (int*)calloc(0, capacity * sizeof(int));
    return temp;
}

int precedent(char symbol){
    switch(symbol){
        case '+':
        case '-':
            return 1;
        case '*':
        case '/':
        case '%':
            return 2;
        case '^':
            return 3;
        default:
            return -1;
    }
}

void infixToPostFix(char *inFix){
    int len = strlen(inFix)-1, i, k;
    struct Stack* stack = createStack(len);
    
    for(i = 0, k = -1; inFix[i]; ++i){
        if(isdigit(inFix[i]) != 0)
            inFix[++k] = inFix[i];
        else if(inFix[i] == '(')
            push(stack, inFix[i]);
        else if(inFix[i] == ')'){
            while(!isEmpty(stack->top) && peek(stack) != '(')
                inFix[++k] = pop(stack);
            if(!isEmpty(stack->top) && peek(stack) != '(')
                return;
            else
                pop(stack);
        }
        else{
            while(!isEmpty(stack->top) && precedent(inFix[i]) <= precedent(peek(stack)))
                inFix[++k] = pop(stack);
            push(stack, inFix[i]);
        }
    }
    while(!isEmpty(stack->top))
        inFix[++k] = pop(stack);

    inFix[++k] = '\0';
    free(stack);
}

int convertCharToInt(char num){
    return num - '0';
}

void evaluatePostFix(char *inFix){
    int i, val1, val2;
    int len = strlen(inFix);
    struct StackInt* stack = createStackInt(len);
    for(i = 0; i < len; i++){
        if(inFix[i] == '+'){
            val1 = pop_int(stack);
            val2 = pop_int(stack);
            push_int(stack, val2+val1);
        }
        else if(inFix[i] == '-'){
            val1 = pop_int(stack);
            val2 = pop_int(stack);
            push_int(stack, val2-val1);
        }
        else if(inFix[i] == '*'){
            val1 = pop_int(stack);
            val2 = pop_int(stack);
            push_int(stack, val2*val1);
        }
        else if(inFix[i] == '/'){
            val1 = pop_int(stack);
            val2 = pop_int(stack);
            push_int(stack, val2/val1);
        }
        else if(inFix[i] == '%'){
            val1 = pop_int(stack);
            val2 = pop_int(stack);
            push_int(stack, val2%val1);
        }
        else if(inFix[i] == '^'){
            val1 = pop_int(stack);
            val2 = pop_int(stack);
            push_int(stack, pow(val2, val1));
        }
        else{
            push_int(stack, convertCharToInt(inFix[i]));
        }
    }
    int finalVal = pop_int(stack);
    printf("%d", finalVal);
    free(stack);
}

// (4^3)+((4*8)-(6%5)) = 95
// (7+3)/2 = 5
// 7+3/2 = 8

int main(){
    char inFix[] = "(7+3)/2";
    infixToPostFix(inFix);
    /*
        Process of the stack on: (7+3)/2
        
        Array: 
        0. ['(', '7', '+', '3', ')', '/', '2']
        1. ['7', '7', '+', '3', ')', '/', '2']
        2. ['7', '7', '+', '3', ')', '/', '2']
        3. ['7', '3', '+', '3', ')', '/', '2']
        4. ['7', '3', '+', '3', ')', '/', '2']
        5. ['7', '3', '+', '3', ')', '/', '2']
        6. ['7', '3', '+', '3', '/', '/', '2']
        
        Final:  ['7', '3', '+', '2', '/']
        
        Stack:
         push        NA        push        NA        pop        push       pop
          0.         1.         2.         3.         4.         5.         6.
         ---        ---        ---        ---        ---        ---        ---
        |   |      |   |      |   |      |   |      |   |      |   |      |   |
         ---        ---        ---        ---        ---        ---        ---
        |   |      |   |      |   |      |   |      |   |      |   |      |   |
         ---   ->   ---   ->   ---   ->   ---   ->   ---   ->   ---   ->   ---
        |   |      |   |      | + |      | + |      |   |      |   |      |   |
         ---        ---        ---        ---        ---        ---        ---
        | ( |      | ( |      | ( |      | ( |      |   |      | / |      |   |
         ---        ---        ---        ---        ---        ---        ---
    */
    printf("%s\n", inFix);
    evaluatePostFix(inFix);
    return 0;
}