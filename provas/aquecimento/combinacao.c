/*mplemente um programa denominado Combinador, que recebe duas strings e deve combiná-las, alternando as letras de cada string,
 come¸cando com a primeira letra da primeira string, seguido pela primeira letra da segunda string, em seguida pela segunda letra da primeira string,
  e assim sucessivamente. As letras restantes da cadeia mais longa devem ser adicionadas ao fim da string resultante e retornada.*/


#include <stdio.h>
#include <string.h>

int main(){
	char str1[100], str2[100], resp[200];
	int tam1, tam2;

	while(scanf("%s",str1) != EOF){

		scanf("%s",str2);
		getchar();
		tam1 = strlen(str1);
		tam2 = strlen(str2);

		for (int cont = 0, i = 0, j = 0; cont < tam1+tam2; i++,j++){
			if (i < tam1){
				resp[cont] = str1[i];
				cont++;
			}
			if (j < tam2){
				resp[cont] = str2[j];
				cont++;
			}

		}
		resp[tam1+tam2] = '\0';
		printf("%s\n",resp);
		str1[0] = '\0'; 
		str2[0] = '\0';
	}
}
