/* Crie um método recursiva que recebe uma string como parâmetro e retorna true se essa é um ``Palíndromo''. Na saída padrão, para cada linha de entrada, escreva uma linha de saída com SIM/NÃO indicando se a linha é um palíndromo. Destaca-se que uma linha de entrada pode ter caracteres não letras.
*/

#include <stdio.h>
#include <string.h>


int palindromo(int result, int inicio, int final, char* string){
  if((inicio >= final) || (result == 0)){
    return result; //retorna result
  }
  if(string[inicio] != string[final]){ //compara os char da String
    result = 0;
  }
  palindromo(result,inicio+1,final-1,string); //chama a funcao novamente, andando INICIO para a direita e FINAL para a esquerda
}

int main(){
  int tam, result;
  char string[500];
  while(scanf("%[^\n]",string) != EOF){ //funciona enquanto tiver palavras no arquivo. %[^\n] serve para nao ter problemas com \n
    getchar();
    
    if(strcmp(string, "FIM") == 0){ //funciona enquanto tiver palavras no arquivo
      return 0;
    }
    tam = strlen(string); //define tam como o tamanho do array
    result = palindromo(1,0,tam - 1, string); //chama funcao recursiva
    if(result == 1){ //se result foi retornado 1 a palavra eh palindromo
      printf("SIM\n");
    }else{
      printf("NAO\n");
    }
  }
}
