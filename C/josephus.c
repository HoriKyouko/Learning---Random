#include <stdio.h>
#include <stdlib.h>
/**
 * 
 * Solving the Josephus Problem using a Circular Linked List.
 * Technically it's a Queue, but we all know Queues are just 
 * a form of Linked Lists. All hail the might Linked List!
 * 
 */
struct node {
    int num;
    struct node *next;
};

/**
 * Finds the survivor for the Josephus problem
 */
int survivor(struct node **head, int skip) {
    // Create our variables
    struct node *person, *killedOff;
    int i;

    killedOff = person = *head;
    /**
     * First check to make sure we don't have one node.
     * 
     * We then keep moving around so long as we haven't
     * reached the person we will be killing off.
     * 
     * person holds the previous point of who was possibly
     * on the chopping block, while killedOff holds the point
     * of who is on the chopping block.
     * 
     * Once we find who is to be killed off we need to set our
     * person->next value to be that of killedOff->next value.
     * This will ensure we don't lose our connections to the
     * rest of the list and keep our list ciruclar.
     * 
     * We then free our killedOff memory, which is symbolical
     * to freeing their souls from their bodies, in the actual
     * josephus problem.
     * 
     * We then say our killedOff variable is the next person on
     * the chopping block. This is to ensure that we can continue
     * freeing souls from bodies until we only have one soul left.
     * 
     * Finally once we have josephus safe and sound we need to return
     * where he needs to stand in order to survive. Also for memory 
     * clean-up issues we set our head to be josephus position.
     */
    while (killedOff->next != killedOff) {
        for (i = 0; i < skip - 1; i++) {
            person = killedOff;
            killedOff = killedOff->next;
        }
        person->next = killedOff->next;   
        free(killedOff); 
        killedOff = person->next; 
    }
    *head = killedOff;

    return (killedOff->num);
}
/**
 * Sets up the Josephus problem.
 */
void create (struct node **head) {
    // Create our variables
    struct node *temp, *rear;
    int n = 5, k;
    /**
     * If you want users to input numbers uncomment the printf/scanf.
     */
    //printf("Enter a number of prisoners : "); 
    //scanf("%d", &n);
    
    /**
     * We start at 1 for the convenience of not incremeting i for
     * setting our nodes value everytime. Other than that just
     * a simple for loop.
     * We alloacte space for our new node and give it a value
     * for all of it's variables.
     * Check to see if we are at the first node:
     * 
     * If so we just say head is the new node.
     * Else we are not then the rear pointers next is temp.
     * 
     * Regardless of which occurs we have rear now be our new node.
     * This is because we want it to always have an idea of where
     * the rear of our list is.
     * 
     * Finally once we have added all the nodes we make it circular
     * by having our rear pointers next variable be the beginning/head
     * of our list.
     * 
     * h = head
     * r = rear
     * 
     *     h           r
     *     1->2->3->4->5-
     *     ^            |
     *     |____________|
     */
    for(int i=1;i<=n;i++) {
        temp = (struct node *)malloc(sizeof(struct node));
        temp->num = i;
        temp->next = NULL;

        if (*head == NULL)
            *head = temp;
        else
            rear->next = temp;

        rear = temp;
    }
    rear->next = *head;
}

int main() {
    // Create our variables
    struct node *head = NULL;
    int survive, skip = 3;

    create(&head);
     /**
     * If you want users to input numbers uncomment the printf/scanf.
     */
    //printf("Enter the number of persons to be skipped: "); 
    //scanf("%d", &skip);
    
    survive = survivor(&head, skip);

    // Finally we print out where his position needs to be and free our memory. 
    printf("The person to survive is : %d\n", survive); 
    free(head); 

    return 0;
}
