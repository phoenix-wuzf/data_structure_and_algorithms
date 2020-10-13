#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct node_s {
    void *data;
    struct node_s *next;
} node_t;

typedef struct stack_s {
    node_t *head;
    node_t *tail;
    node_t *current;
} stack_t;

void init_stack(stack_t *stack);
void pop(stack_t *stack, void *data);
void push(stack_t *stack);

void pop(stack_t *stack)
{
    node_t * node ;
    node = (node_t *)malloc(sizeof(node_t));
    if (node == NULL) {
        printf("malloc is fail \n");
        return ;
    }
    
    if (stack->tail = NULL) {
        stack->head = node;
        node->next = NULL;
    } else {
        stack->tail->next = node;
    }
    stack->tail = node;
}

void *push (stack_t *stack)
{
    node_t *tmp;
    void *data;
    
    tmp = stack->head;
    
    if (tmp == NULL) {
        return NULL;
    } else if (tmp == stack->tail) {
        stack->tail = NULL;
        stack->head = NULL;
        data = tmp->data;
        free(tmp);
    } else {
        data = tmp->data;
        stack->head = tmp->next;
        free(tmp);
    }
    
    return data;
}


int pop (stack_t *header, int *data)
{
    stack_t *tmp;
    if (header->next == NULL) {
        printf("this stack is null or no content\n");
        return -1;
    }

    tmp = header->next;
    *data = tmp->data;
    header->next = tmp->next;
    free(tmp);
    return 1;
}

int push (stack_t *header, int data)
{
    stack_t *node;

    node = (stack_t *)malloc(sizeof(stack_t));
    if (node == NULL) {
        printf("node malloc is fail\n");
        return -1;
    }
    memset(node, 0, sizeof(stack_t));
    
    node->data = data;
    node->next = header->next;
    header->next = node;
    return 1;
}

int main ()
{
    stack_t stack_header;
    int i;
    int ret;

    memset(&stack_header, 0, sizeof(stack_t));
    
    for (i = 0; i < 10; i++) {
        push(&stack_header, i);
    }

    for (i; i > -1; i--) {
        pop(&stack_header, &ret);
        printf("pop result : %d\n", ret);
    }

    return 0;
}
