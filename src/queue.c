#include <stdio.h>
#include <stdlib.h>

typedef struct node_s {
    void *data;
    struct node_s *next;
} node_t;

typedef struct list_s {
    node_t *head;
    node_t *tail;
    node_t *current;
} queue_t;

void init_queue(queue_t *queue);
void *de_queue(queue_t *queue);
void en_queue(queue_t *queue, void *node);

void init_queue(queue_t *queue)
{
    queue.head = NULL;
    queue.tail = NULL;
    queue.current = NULL;
}

void en_queue (queue_t *queue, void *data)
{
    node_t * node ;
    node = (node_t *)malloc(sizeof(node_t));
    if (node == NULL) {
        printf("malloc is fail \n");
        return ;
    }
    
    node->data = data;
    
    if (queue->head == NULL) {
        queue->tail = node;
        node->next = NULL;
    } else {
        node->next = queue->head;
    }
    queue->head = node;
}


void *de_queue(queue_t *queue)
{
    node_t * tmp;
    tmp = queue->head;
    void *data;
    
    if (queue->head == NULL) {
        data = NULL;
    } else if (queue->head == queue->tail ) {
        queue->head = NULL;
        queue->tail = NULL;
        data = tmp->data;
        free(tmp);
        
    } else {
        while (tmp->next != queue->tail) {
            tmp = tmp->next;
        }
        queue->tail = tmp;
        tmp = tmp->next;
        queue->tail->next = NULL; //链表的最后一个元素的next要置空
        data = tmp->data;
        free(tmp); //这个地方释放了，为什么data还能有效？返回的是一个地址吧应该
    }
    
    return data;
}