/* Crie um método recursivo que recebe um número inteiro como parâmetro e retorna a soma de seus dígitos. Na saída padrão, para cada linha de entrada, escreva uma linha de saída com o resultado da soma dos dígitos. Por exemplo, se a entrada for 12345, a saída deve ser 15.
*/

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void funcao(int  soma, int num){
  if((num / 10) == 0){ //compara se o neumro foi somado por completo
    soma += (num % 10); 
    printf("%d\n",soma);
    return;
  }
  soma += (num % 10); //soma o numero mais a direita
  funcao(soma, num/10); //chama a funcao com num/10
  
}
int main(){
  char string[20];
  int num,soma = 0;
  
  while(scanf("%s",string) != EOF){ //VERIFICAR EM STRING DEPOIS PASSAR PARA INT O VALOR, se nao da erro ao codigo ler FIM
  
    if(strcmp(string, "FIM") == 0){ //se String for FIM interrompe o programa
      return 0;
    }
    num = atoi(string); //CONVERTE STRING PARA INT
    funcao(soma,num); //chama funcao recursiva
  }
}
