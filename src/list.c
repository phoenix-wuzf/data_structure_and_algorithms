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
} list_t;

typedef void (*display_node_cb)(void *);
typedef void (*display_list_cb)(list_t *);

void init_list(list_t *list);
void add_head(list_t *list, void * data);
void add_tail(list_t *list, void * data);
void delete_node(list_t *list, void * data);
node_t * get_node(list_t *list, void * data);

void init_list(list_t *list)
{
    list->head = NULL;
    list->tail = NULL;
    list->current = NULL;
}

void add_head(list_t *list, void * data)
{
    node_t *node;
    
    node = (node_t *)malloc(sizeof(node_t));
    if (node == NULL) {
        printf("malloc is fail\n");
        return ;
    }
    
    memeset(node, 0, sizeof(node_t));
    node->data = data;
    
    if (list->head = NULL) {
        list->tail = node;
        node->next = NULL;
    } else {
        node->next = list->head;
    }
    list->head = node;
    return ;
}

void add_tail(list_t *list, void * data)
{
    node = (node_t *)malloc(sizeof(node_t));
    if (node == NULL) {
        printf("malloc is fail\n");
        return ;
    }
    
    memeset(node, 0, sizeof(node_t));
    node->data = data;
    node->next = NULL;
    
    if (list->tail = NULL) {
        list->head = node;
    } else {
        list->tail->next = node;
    }
    list->tail = node;
    
    return ;
}

int cmp_func(void *node_data, void *data )
{
    int ret;
    
    if (node_data->a == data->a) {
        ret = 1;
    } else {
        ret = 0;
    }
    
    return ret;
}

node_t *get_node(list_t *list, cmp_func * cmp_func; void *data)
{
    node_t *node_tmp;
    node_tmp = list->head;
    
    while (node_tmp != NULL) {
        if (cmp_func(node_tmp->data, data)) {
            
            return node_tmp;
        }
        node_tmp = node_tmp->next;
    }
    
    return NULL;
}

void delete_node (list_t *list, node_t *node)
{
    node_t *tmp;
    if (node == list->head) {
        if (node->next == NULL) {
            free(node->data);
            free(node);
        } else {
            list->head = node->next;
            free(node);
        }
    } else {
        tmp = list->head;
        while (tmp != NULL && tmp->next != node) {
            tmp->next = node->next;
        }
    }
    free(node);
    
}

void delete_list (list_t *list)
{
    node_t *node_tmp;
    node_t *node_tmp2;
    
    node_tmp = list->head;
    
    if (node_tmp == NULL) {
        return ;
    } else {
        while (node_tmp != NULL) {
            node_tmp2 = node_tmp->next;
            free(node_tmp->data);
            free(node_tmp);
            node_tmp = node_tmp2;
        }
    }
    
    return ;
}

void display_node(void *data)
{
    
    
}

//比较和打印都应该通过函数指针的方式在外部定义，不应该在函数内部指定，
void display_list(list_t *list, dispaly_node_cb display_node)
{
    nodt_t *tmp;
    tmp = list->head;
    
    while (tmp != NULL) {
        printf("data: %p\n", tmp->data);
        display_node(tmp->data);
        tmp = tmp->next;
    }
}





