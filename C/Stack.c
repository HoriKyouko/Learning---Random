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
/**
 * Checks if our stack is empty
*/
int isEmpty(int top){
    if(top == -1)
        return 1;
    return 0;
}

/**
 * Checks if the stack is empty:
 * If it is prints out the stack is empty and returns -1
 * Else returns the top value of the stack stored in the arr.
*/
char peek(struct Stack *stack){
    if(isEmpty(stack->top))
        printf("The stack is empty");
    else
        return stack->arr[stack->top];
    return -1;
}

/**
 * Returns the top value of the stack and decrements
 * the stack. In this case it does it for char values.
 * This doesn't remove the value, but gives
 * the user the idea that we have removed it from the
 * stack. 
*/
char pop(struct Stack *stack){
    char val = stack->arr[stack->top];
    stack->top--;
    return val;
}

/**
 * Returns the top value of the stack and decrements
 * the stack. In this case it does it for int values.
 * This doesn't remove the value, but gives
 * the user the idea that we have removed it from the
 * stack. 
*/
int pop_int(struct StackInt *stack){
    int val = stack->arr[stack->top];
    stack->top--;
    return val;
}

/**
 * Adds to the stack the and increments our top.
 * In this case it adds a char value. 
*/
void push(struct Stack *stack, char val){
    stack->top++;
    stack->arr[stack->top] = val;
}

/**
 * Adds to the stack the and increments our top.
 * In this case it adds a int value. 
*/
void push_int(struct StackInt *stack, int val){
    stack->top++;
    stack->arr[stack->top] = val;
}

/**
 * Setup our char Stack with some initial capacity.
*/
struct Stack* createStack(int capacity){
    struct Stack* temp = (struct Stack*)malloc(sizeof(struct Stack));
    temp->top = -1;
    temp->capacity = capacity;
    temp->arr = (char*)calloc(0, capacity * sizeof(char));
    return temp;
}

/**
 * Setup our int Stack with some initial capacity.
*/
struct StackInt* createStackInt(int capacity){
    struct StackInt* temp = (struct StackInt*)malloc(sizeof(struct StackInt));
    temp->top = -1;
    temp->capacity = capacity;
    temp->arr = (int*)calloc(0, capacity * sizeof(int));
    return temp;
}
/**
 * Simulates PEMDAS by dealing with order of precedence
 * with a higher return value being more important.
 * If for some reason a symbol is passed in that isn't
 * recognized we return -1.
*/
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
/**
 * Changes our infix equation to a postfix equation.
 * This uses () to determine the depth of our equation
 * we are at.
 * 
 * ie. ((9+(3*2))/5)
*/
void infixToPostFix(char *inFix){
    int len = strlen(inFix)-1, i, k;
    struct Stack* stack = createStack(len);
    /**
     * We loop so long as inFix[i] has some value that isn't null or terminating.
     * 
     * There are several checks done:
     * 
     * If our character is a digit (0..9) then we add this to
     * the ++k place in our inFix array. This could invalidate
     * the equation if something like 9(3*2) as it would remove
     * the first ( but we are told this will never occur.
     * 
     * Else if our character is an opening ( we push it onto our
     * character stack.
     * 
     * Else if our character is a closing ) we pop from the stack
     * so long as the stack isn't empty and the stacks value isn't
     * a (. This will move all the math symbols into their proper
     * place. Finally we check to make sure that we removed all the
     * symbols up to the first occurance of ( and if we haven't then
     * there is an invalid ( and we back out of the function. Else
     * we simply pop the ( and continue on with the program.
     * 
     * Else we remove symbols that have a lower precedence to our
     * current symbol, if this occurs, and add them to the stack
     * in the ++k position. Finally we push our new symbol onto the stack.
    */
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
    /**
     * Once we have finished our infix equation we might have some values
     * still left over in our stack so we check that our stack isn't empty
     * and while it isn't we tac on to the array the remaining values from
     * the stack.
     * 
     * Since we are dealing with a string we need add a terminating character
     * to the end and then we can free the allocated stack memory.
    */
    while(!isEmpty(stack->top))
        inFix[++k] = pop(stack);

    inFix[++k] = '\0';
    free(stack);
}

/**
 * Simply takes a character number and subtracts the ASCII value of 0
 * which will give us the actual int that the character is.
 * 
 * ie. '9'-'0' => 57 - 48 = 9
*/
int convertCharToInt(char num){
    return num - '0';
}

/**
 * Simple evaluation of our postfix equation to find
 * the value of the equation. 
*/
void evaluatePostFix(char *postFix){
    int i, val1, val2;
    int len = strlen(postFix);
    struct StackInt* stack = createStackInt(len);
    /**
     * We loop until we have reached the end of the postFix
     * equation.
     * 
     * We do several checks all of which are essentially the same:
     * 
     * Summed up for +, -, *, /, %, and ^ we first pop the two values
     * from the stack and push the result onto the stack.
     * 
     * ie. Stack holds 7, 4, + then val1 = 7, val2 = 4 and we push
     * 4+7 = 11 onto the Stack. Now Stack only holds 11.
     * 
     * Else if the character is just a digit we convert from char to int
     * and push it onto the Stack.
    */
    for(i = 0; i < len; i++){
        if(postFix[i] == '+'){
            val1 = pop_int(stack);
            val2 = pop_int(stack);
            push_int(stack, val2+val1);
        }
        else if(postFix[i] == '-'){
            val1 = pop_int(stack);
            val2 = pop_int(stack);
            push_int(stack, val2-val1);
        }
        else if(postFix[i] == '*'){
            val1 = pop_int(stack);
            val2 = pop_int(stack);
            push_int(stack, val2*val1);
        }
        else if(postFix[i] == '/'){
            val1 = pop_int(stack);
            val2 = pop_int(stack);
            push_int(stack, val2/val1);
        }
        else if(postFix[i] == '%'){
            val1 = pop_int(stack);
            val2 = pop_int(stack);
            push_int(stack, val2%val1);
        }
        else if(postFix[i] == '^'){
            val1 = pop_int(stack);
            val2 = pop_int(stack);
            push_int(stack, pow(val2, val1));
        }
        else{
            push_int(stack, convertCharToInt(postFix[i]));
        }
    }
    
    /**
     * We pop the final value and print it out. Then free the
     * memory allocated for our Stack.
    */
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