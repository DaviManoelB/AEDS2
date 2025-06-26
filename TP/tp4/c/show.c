#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_LINE 1024
#define MAX_STRING 200
#define MAX_SHOWS 1500

typedef struct {
    char show_id[MAX_STRING];
    char title[MAX_STRING];
} Show;


typedef struct AVLNode {
    char title[MAX_STRING];
    struct AVLNode *esq, *dir;
    int altura;
} AVLNode;


int max(int a, int b) { return (a > b) ? a : b; }

int altura(AVLNode *N) {
    if (N == NULL) return 0;
    return N->altura;
}

AVLNode* novoNo(const char *title) {
    AVLNode* node = (AVLNode*)malloc(sizeof(AVLNode));
    strcpy(node->title, title);
    node->esq = node->dir = NULL;
    node->altura = 1;
    return node;
}

AVLNode *rotDir(AVLNode *y) {
    AVLNode *x = y->esq;
    AVLNode *T2 = x->dir;
    x->dir = y;
    y->esq = T2;
    y->altura = max(altura(y->esq), altura(y->dir)) + 1;
    x->altura = max(altura(x->esq), altura(x->dir)) + 1;
    return x;
}

AVLNode *rotEsq(AVLNode *x) {
    AVLNode *y = x->dir;
    AVLNode *T2 = y->esq;
    y->esq = x;
    x->dir = T2;
    x->altura = max(altura(x->esq), altura(x->dir)) + 1;
    y->altura = max(altura(y->esq), altura(y->dir)) + 1;
    return y;
}

int getBalance(AVLNode *N) {
    if (N == NULL) return 0;
    return altura(N->esq) - altura(N->dir);
}

AVLNode* inserirAVL(AVLNode* node, const char *title) {
    if (node == NULL) return novoNo(title);
    int cmp = strcmp(title, node->title);
    if (cmp < 0)
        node->esq = inserirAVL(node->esq, title);
    else if (cmp > 0)
        node->dir = inserirAVL(node->dir, title);
    else
        return node; 

    node->altura = 1 + max(altura(node->esq), altura(node->dir));
    int balance = getBalance(node);

    
    if (balance > 1 && strcmp(title, node->esq->title) < 0)
        return rotDir(node);
    if (balance < -1 && strcmp(title, node->dir->title) > 0)
        return rotEsq(node);
    if (balance > 1 && strcmp(title, node->esq->title) > 0) {
        node->esq = rotEsq(node->esq);
        return rotDir(node);
    }
    if (balance < -1 && strcmp(title, node->dir->title) < 0) {
        node->dir = rotDir(node->dir);
        return rotEsq(node);
    }
    return node;
}


void buscarComCaminho(AVLNode *node, const char *title) {
    printf("raiz ");
    AVLNode *atual = node;
    while (atual != NULL) {
        int cmp = strcmp(title, atual->title);
        if (cmp == 0) {
            printf("SIM\n");
            return;
        } else if (cmp < 0) {
            printf("esq ");
            atual = atual->esq;
        } else {
            printf("dir ");
            atual = atual->dir;
        }
    }
    printf("NAO\n");
}


void clean_field(char *str) {
    
    int len = strlen(str);
    if (len > 0 && str[0] == '"') {
        memmove(str, str+1, len);
        len--;
    }
    if (len > 0 && str[len-1] == '"') {
        str[len-1] = '\0';
    }
    
    while (len > 0 && str[0] == ' ') {
        memmove(str, str+1, len--);
    }
    while (len > 0 && str[len-1] == ' ') {
        str[len-1] = '\0';
        len--;
    }
}


int lerTodosOsShows(Show todos[]) {
    FILE *fp = fopen("/tmp/disneyplus.csv", "r");
    if (!fp) fp = fopen("/tmp/disneyplus.csv", "r");
    if (!fp) {
        fprintf(stderr, "Arquivo disneyplus.csv não encontrado!\n");
        return 0;
    }

    char line[MAX_LINE];
    int count = 0;

    fgets(line, MAX_LINE, fp); // pula cabeçalho

    while (fgets(line, MAX_LINE, fp) && count < MAX_SHOWS) {
        line[strcspn(line, "\r\n")] = 0;
        
        int col = 0, i = 0, start = 0, in_quotes = 0;
        char field[12][MAX_STRING] = {{0}};
        for (i = 0, start = 0, col = 0; line[i] && col < 12; i++) {
            if (line[i] == '"') in_quotes = !in_quotes;
            if ((line[i] == ',' && !in_quotes) || line[i+1] == '\0') {
                int end = (line[i+1] == '\0') ? i+1 : i;
                int len = end - start;
                if (len >= MAX_STRING) len = MAX_STRING-1;
                strncpy(field[col], line+start, len);
                field[col][len] = '\0';
                clean_field(field[col]);
                col++;
                start = i+1;
            }
        }
        if (col >= 3) { 
            strcpy(todos[count].show_id, field[0]);
            strcpy(todos[count].title, field[2]);
            count++;
        }
    }
    fclose(fp);
    return count;
}

int main() {
    Show todos[MAX_SHOWS];
    int total = lerTodosOsShows(todos);
    if (total == 0) {
        printf("Erro ao ler os dados\n");
        return 1;
    }

    AVLNode *raiz = NULL;
    char linha[MAX_STRING];

    
    while (fgets(linha, sizeof(linha), stdin)) {
        linha[strcspn(linha, "\n")] = '\0';
        if (strcmp(linha, "FIM") == 0) break;
        for (int i = 0; i < total; i++) {
            if (strcmp(todos[i].show_id, linha) == 0) {
                raiz = inserirAVL(raiz, todos[i].title);
                break;
            }
        }
    }

    
    while (fgets(linha, sizeof(linha), stdin)) {
        linha[strcspn(linha, "\n")] = '\0';
        if (strcmp(linha, "FIM") == 0) break;
        buscarComCaminho(raiz, linha);
    }
    return 0;
}
