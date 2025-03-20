/* Faça um programa que leia um número inteiro n indicando o número de valores reais que devem ser lidos e salvos sequencialmente em um arquivo texto. Após a leitura dos valores, devemos fechar o arquivo. Em seguida, reabri-lo e fazer a leitura de trás para frente usando os métodos getFilePointer e seek da classe RandomAccessFile e mostre todos os valores lidos na tela. Nessa questão, você não pode usar, arrays, strings ou qualquer estrutura de dados. A entrada padrão é composta por um número inteiro n e mais n números reais. A saída padrão corresponde a n números reais mostrados um por linha de saída.
*/

#include <stdio.h>
#include <stdlib.h>

int main(){
  int n; 
  double pos, num;
  FILE *arquivo;
  
  arquivo = fopen("texto.txt", "wb"); //cria o arquivo em modo escrita
  scanf("%d", &n); //recebe o num de numeros que serao digitados
  
  for (int i = 0; i < n; i++){
    scanf("%lf",&num); 
    fwrite(&num, sizeof(double), 1, arquivo); //recebe cada numero e escreve no arquivo
  }
  fclose(arquivo);
  
  arquivo = fopen("texto.txt", "rb");
  fseek(arquivo, -sizeof(double), SEEK_END); //avança para o final do arquivo, menos um tamanho de Double 
  
  while(ftell(arquivo) > sizeof(double)){
    fread(&num, sizeof(double), 1, arquivo);
    printf("%g\n", num); //%g imprime sem os 0 a direita

    if (ftell(arquivo) >= sizeof(double)) { //se possuir um num Double, retorna true
        fseek(arquivo, -2 * sizeof(double), SEEK_CUR);  //FAZ O FSEEK IR PARA O NUM ANTERIOR
    }
  }
  fclose(arquivo);
  
  arquivo =fopen("texto.txt","wb"); //ABRE NOVAMENTE ZERANDO O ARQUIVO PARA UM NOVO TESTE
  fclose(arquivo);
} 
