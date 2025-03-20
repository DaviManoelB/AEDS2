/*Crie um método iterativo que recebe uma string como parâmetro e retorna true se essa é um ``Palíndromo''. Na saída padrão, para cada linha de entrada, escreva uma linha de saída com SIM/NÃO indicando se a linha é um palíndromo. Destaca-se que uma linha de entrada pode ter caracteres não letras.

*/

#include <stdio.h>
#include <string.h>


int main(){
  char frase[500];
  int aux = 0,tam;

  while (scanf("%[^\n]",frase) != EOF){ //funciona enquanto tiver palavras no arquivo. %[^\n] serve para nao ter problemas com \n
    getchar();
    tam = strlen(frase);
		
    if(strcmp(frase, "FIM") == 0) //se String for FIM interrompe o programa
        return 0;

    for(int i = 0, j = tam - 1; i < j; i++, j--){ //compara os char da String
      if(frase[i] != frase[j]){
	  aux = 1;
	  printf("NAO\n");
	  i = j;
      }

    }
    if( aux == 0){ //se a palavra for palindromo, aux =0, entao printa SIM
      printf("SIM\n");
    }
    aux = 0;
  }
}

