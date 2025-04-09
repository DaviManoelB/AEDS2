/*Crie um método iterativo que recebe uma string e retorna true se a mesma é composta somente por vogais. Crie outro método iterativo que recebe uma string e retorna true se a mesma é composta somente por consoantes. Crie um terceiro método iterativo que recebe uma string e retorna true se a mesma corresponde a um número inteiro. Crie um quarto método iterativo que recebe uma string e retorna true se a mesma corresponde a um número real. Na saída padrão, para cada linha de entrada, escreva outra de saída da seguinte forma X1 X2 X3 X4 onde cada X i é um booleano indicando se a é entrada é: composta somente por vogais (X1); composta somente somente por consoantes (X2); um número inteiro (X3); um número real (X4). Se X$i$ for verdadeiro, seu valor será SIM, caso contrário, NÃO.
*/

import java.util.*;

public class is{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String frase = new String();
    int tam, ponto, ch, cont;
    char letra;
    
    
    while(sc.hasNextLine()){ 
      frase = sc.nextLine();
      
      if ((frase.length() == 3) && (frase.charAt(0) == 'F') && (frase.charAt(1) == 'I') && (frase.charAt(2) == 'M')){ //acaba programa se receber a palavra FIM
        sc.close();
        return;
      }

      tam = frase.length();
      cont = 0; //inicializa o contador. conador serve para verificar todas as letras antes de afirmar SIM
      for(int i = 0; i < tam; i++){
        ch = frase.charAt(i); //letra a ser comparada pela tabela ASCII
        letra = frase.charAt(i); //letra a ser comparada em char
        
        
        if ((letra != 'a' && letra != 'e' && ch != 'i' && letra != 'o' && letra != 'u' ) || !((ch <= 90) && (ch >= 65) || (ch <= 122) && (ch >= 97))){ //verifica se sao todas vogais e letras
          System.out.printf("NAO ");
          i = tam;
        } else {
          cont++;
          if(cont == tam){
            System.out.printf("SIM ");
          }
          
        }}
      cont = 0; 
      for(int i = 0; i < tam; i++){
        ch = frase.charAt(i);
        letra = frase.charAt(i);
        
        
        if ((letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u') || !((ch <= 90) && (ch >= 65) || (ch <= 122) && (ch >= 97))){ //verifica se possui alguma vogal ou nao letra
          System.out.printf("NAO ");
          i = tam;
        } else{
          cont++;
          if(cont == tam){
            System.out.printf("SIM ");
          } 
        }}
       cont = 0; 
       
      for(int i = 0; i < tam; i++){
        int num = frase.charAt(i);
        
        if (num > 47 && num < 58){ //condicao ser somente numero
          cont++;
        }else{
          System.out.printf("NAO ");
          i = tam;
        }
        if(cont == tam){
            System.out.printf("SIM ");
          }
        }
      cont = 0;
      ponto = 0;
      for(int i = 0; i < tam; i++){
        int num = frase.charAt(i);
        if (num > 47 && num < 58){ //condicao ser somente numero
          cont++;
        }else if (num == 46 && ponto == 0){ //verifica se o numero possui 1 ponto somente
          ponto++;
          cont++;
        }else{
          if(cont == tam){ 
            System.out.printf("SIM ");
          }else{
          System.out.printf("NAO\n");
          i = tam; //sai do for
          }
        }
        if(cont == tam){
          System.out.printf("SIM ");
        }
      }
    }
  sc.close();
  }
}
