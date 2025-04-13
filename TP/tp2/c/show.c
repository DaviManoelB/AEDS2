#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Show{
    char show_id[10];
    char type[100];
    char title[100];
    char director[100];
    char cast[500];
    char country[50];
    char date_added[50];
    int release_year;
    char reating[10];
    char duration[10];
    char listed_in[100];
} Show;

void imprimir(Show s){
    printf("=> %s ## %s ## %s ## %s ## %s ## %s ## %s ## %d ## %s ## %s ## [%s]\n",
         s.show_id, s.type, s.title, s.director, s.cast, s.country, s.date_added, s.release_year, s.reating, s.duration, s.listed_in);
}

void ordenar(){}

Show ler(int i){
    int cont = 1;
    FILE *arquivo = fopen("../disneyplus.csv", "r");
    Show tabela[1370];
    char resp[12];
    char cabecalho[500];
    scanf("%s", resp);



}

int main(){
    FILE *arquivo = fopen("../disneyplus.csv", "r");

    Show tabela[1370];
    char id[10];
    for (int i = 1; i < 1370; i++){
        tabela[i] = ler(i);
    }
    
    for(int i = 1; i < 1370; i++){
        fscanf(arquivo, "%[^,],%[^,],%[^,],%[^,],%[^,],%[^,],%[^,],%d,%[^,],%[^,],%[^\n]\n",
            tabela[i].show_id,
            tabela[i].type,
            tabela[i].title,
            tabela[i].director,
            tabela[i].cast,
            tabela[i].country,
            tabela[i].date_added,
            &tabela[i].release_year,
            tabela[i].reating,
            tabela[i].duration,
            tabela[i].listed_in);
    }

    while((scanf("%s", &id) != EOF)){   

        if (strcmp(id, "FIM") == 0){ //condicao de parada
            return 0;
        }

        for(int i = 0; i < 1370; i++){
            if(strcmp(tabela[i].show_id, id) == 0){
                imprimir(tabela[i]);
                i = 1370;
            }
        }
    }
}