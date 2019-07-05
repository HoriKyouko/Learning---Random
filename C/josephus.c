#include <stdio.h>
#include <stdlib.h>

struct node 
{
    int num;
    struct node *next;
    struct node *prev;
};

void create(struct node **); 
int survivor(struct node **, int); 

int main()
{
    struct node *head = NULL;
    int survive, skip = 3;

    create(&head); 
    printf("Enter the number of persons to be skipped: "); 
    //scanf("%d", &skip);
    survive = survivor(&head, skip); 
    printf("The person to survive is : %d\n", survive); 
    free(head); 

    return 0;
}

int survivor(struct node **head, int k)
{
    struct node *p, *q;
    int i;

    q = p = *head;
    while (p->next != p)
    {
        for (i = 0; i < k - 1; i++) 
        {
            q = p;
            p = p->next;
        }
        q->next = p->next;   
        free(p); 
        p = q->next; 
    }
    *head = p;

    return (p->num);
}

void create (struct node **head)
{
    struct node *temp, *rear;
    int n = 5, k;
    printf("Enter a number of prisoners : "); 
    //scanf("%d", &n);
    for(int i=1;i<=n;i++){
        temp = (struct node *)malloc(sizeof(struct node));
        temp->num = i;
        temp->next = NULL;
        if (*head == NULL)
        {
            *head = temp;
        }
        else
        {
            rear->next = temp;
        }
        rear = temp;
    }
    rear->next = *head;
}