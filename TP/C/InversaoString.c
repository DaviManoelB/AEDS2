/* Crie um método recursivo que recebe uma string como parâmetro e retorna a string invertida. Na saída padrão, para cada linha de entrada, escreva uma linha de saída com a string invertida. Por exemplo, se a entrada for abcde, a saída deve ser edcba.
*/

#include <stdio.h>
#include <string.h>

void inverter(int inicio,int troca, char* string){
  if(inicio >= troca){ //se INICIO chega no meio da palavra, ou seja igual ou maior q TROCA, printa string e retorna
    printf("%s\n",string);
    return;
  }
  char temp = string[inicio]; //passa o char para uma variavel temporaria para fazer uma troca de char
  string[inicio] = string[troca];
  string[troca] = temp;
  inverter(inicio+1,troca-1,string);
}

int main(){
  int tam;
  char string[500];
  while(scanf("%s",string) != EOF){ //funciona enquanto tiver palavras no arquivo
  
    if(strcmp(string, "FIM") == 0){ //se String for FIM interrompe o programa
      return 0;
    }
    tam = strlen(string);
    inverter(0,tam - 1, string); //chama metodo recursivo
   }
}
