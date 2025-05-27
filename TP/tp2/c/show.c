#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <time.h>

#define MAX_LINE 1024
#define MAX_STRING 200
#define MAX_LIST 20
#define MAX_SHOWS 1370

// Struct Show
typedef struct {
    char show_id[MAX_STRING];
    char type[MAX_STRING];
    char title[MAX_STRING];
    char director[MAX_STRING];
    char cast[MAX_LIST][MAX_STRING];
    int cast_size;
    char country[MAX_STRING];
    char date_added[MAX_STRING];
    int release_year;
    char rating[MAX_STRING];
    char duration[MAX_STRING];
    char listed_in[MAX_LIST][MAX_STRING];
    int listed_in_size;
} Show;

void handle_empty(char *field) {
    if (field == NULL || strlen(field) == 0)
        strcpy(field, "NaN");
}

void sort_list(char list[][MAX_STRING], int size) {
    for (int i = 0; i < size - 1; i++) {
        for (int j = i + 1; j < size; j++) {
            if (strcmp(list[i], list[j]) > 0) {
                char temp[MAX_STRING];
                strcpy(temp, list[i]);
                strcpy(list[i], list[j]);
                strcpy(list[j], temp);
            }
        }
    }
}

int split(char *field, char list[][MAX_STRING]) {
    int count = 0;
    char *token = strtok(field, ",");
    while (token != NULL && count < MAX_LIST) {
        while (*token == ' ') token++;
        strcpy(list[count++], token);
        token = strtok(NULL, ",");
    }
    sort_list(list, count);
    return count;
}

void remove_aspas(char *str) {
    char *src = str, *dst = str;
    while (*src) {
        if (*src == '"' && *(src + 1) == '"') {
            src += 2;
            *dst++ = '"';
        } else {
            *dst++ = *src++;
        }
    }
    *dst = '\0';
}

void categorias(char *line, char *fields[12]) {
    int i = 0;
    char *start = line;
    while (*start && i < 12) {
        if (*start == '"') {
            start++;
            fields[i++] = start;
            while (*start && !(*start == '"' && (*(start + 1) == ',' || *(start + 1) == '\0'))) start++;
            *start = '\0';
            start += 2;
        } else {
            fields[i++] = start;
            while (*start && *start != ',') start++;
            if (*start) {
                *start = '\0';
                start++;
            }
        }
    }
}

void parse_show(char *line, Show *s) {
    char *fields[12];
    categorias(line, fields);

    strcpy(s->show_id, fields[0]);
    strcpy(s->type, fields[1]);

    remove_aspas(fields[2]);
    strcpy(s->title, fields[2]);

    remove_aspas(fields[3]);
    strcpy(s->director, fields[3]);
    handle_empty(s->director);

    remove_aspas(fields[4]);
    if (strlen(fields[4]) == 0) {
        strcpy(s->cast[0], "NaN");
        s->cast_size = 1;
    } else {
        s->cast_size = split(fields[4], s->cast);
    }

    strcpy(s->country, fields[5]);
    handle_empty(s->country);
    strcpy(s->date_added, fields[6]);
    handle_empty(s->date_added);
    s->release_year = atoi(fields[7]);
    strcpy(s->rating, fields[8]);
    handle_empty(s->rating);
    strcpy(s->duration, fields[9]);
    handle_empty(s->duration);

    remove_aspas(fields[10]);
    if (strlen(fields[10]) == 0) {
        strcpy(s->listed_in[0], "NaN");
        s->listed_in_size = 1;
    } else {
        s->listed_in_size = split(fields[10], s->listed_in);
    }
}

int lerTodosOsShows(Show todos[]) {
    FILE *fp = fopen("../disneyplus.csv", "r");
    if (!fp) return 0;

    char line[MAX_LINE];
    int count = 0;

    fgets(line, MAX_LINE, fp); // pula cabeçalho

    while (fgets(line, MAX_LINE, fp) && count < MAX_SHOWS) {
        line[strcspn(line, "\r\n")] = 0;
        parse_show(line, &todos[count++]);
    }

    fclose(fp);
    return count;
}

void ordenarShowsPorTitulo(Show arr[], int n, long *comp, long *mov) {
    for (int i = 0; i < n - 1; i++) {
        int menor = i;
        for (int j = i + 1; j < n; j++) {
            (*comp)++;
            if (strcmp(arr[j].title, arr[menor].title) < 0) {
                menor = j;
            }
        }
        if (menor != i) {
            Show temp = arr[i];
            arr[i] = arr[menor];
            arr[menor] = temp;
            (*mov) += 3;
        }
    }
}

int buscarTituloPorBinaria(Show arr[], int n, const char *titulo, long *comp) {
    int esq = 0, dir = n - 1;
    while (esq <= dir) {
        int meio = (esq + dir) / 2;
        (*comp)++;
        int cmp = strcmp(titulo, arr[meio].title);
        if (cmp == 0) return 1;
        else if (cmp < 0) dir = meio - 1;
        else esq = meio + 1;
    }
    return 0;
}

void gerarLogDesempenho(const char *nomeArquivo, long comparacoes, long movimentacoes, long tempo) {
    FILE *fp = fopen(nomeArquivo, "w");
    if (fp != NULL) {
        fprintf(fp, "865235\t%ld\t%ld\t%ld\n", comparacoes, movimentacoes, tempo);
        fclose(fp);
    }
}

int main() {
    Show todos[MAX_SHOWS];
    Show selecionados[MAX_SHOWS];
    int selecionadosCount = 0;
    char linha[200];
    long comparacoes = 0, movimentacoes = 0;
    clock_t inicio, fim;
    long tempo;

    int total = lerTodosOsShows(todos);
    if (total == 0) {
        printf("Erro ao ler os dados\n");
        return 1;
    }

    while (fgets(linha, sizeof(linha), stdin)) {
        linha[strcspn(linha, "\n")] = '\0';
        if (strcmp(linha, "FIM") == 0) break;

        for (int i = 0; i < total; i++) {
            comparacoes++;
            if (strcmp(todos[i].show_id, linha) == 0) {
                selecionados[selecionadosCount++] = todos[i];
                break;
            }
        }
    }

    inicio = clock();
    ordenarShowsPorTitulo(selecionados, selecionadosCount, &comparacoes, &movimentacoes);
    fim = clock();
    tempo = ((long)(fim - inicio) * 1000) / CLOCKS_PER_SEC;

    printf("NAO\n");
    while (fgets(linha, sizeof(linha), stdin)) {
        linha[strcspn(linha, "\n")] = '\0';
        if (strcmp(linha, "FIM") == 0) break;

        if (buscarTituloPorBinaria(selecionados, selecionadosCount, linha, &comparacoes)) {
            printf("SIM\n");
        } else {
            printf("NAO\n");
        }
    }

    gerarLogDesempenho("865235_binaria.txt", comparacoes, movimentacoes, tempo);
    return 0;
}
