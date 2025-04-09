/*Crie um método recursivo que recebe uma string e retorna true se a mesma é composta somente por vogais. Crie outro método iterativo que recebe uma string e retorna true se a mesma é composta somente por consoantes. Crie um terceiro método iterativo que recebe uma string e retorna true se a mesma corresponde a um número inteiro. Crie um quarto método iterativo que recebe uma string e retorna true se a mesma corresponde a um número real. Na saída padrão, para cada linha de entrada, escreva outra de saída da seguinte forma X1 X2 X3 X4 onde cada X i é um booleano indicando se a é entrada é: composta somente por vogais (X1); composta somente somente por consoantes (X2); um número inteiro (X3); um número real (X4). Se X$i$ for verdadeiro, seu valor será SIM, caso contrário, NÃO.
*/

import java.util.*;

public class IsRecursivo{
  public static void Vogal(String frase, int tam, int cont){ //verifica se todas as letras sao vogais
    char letra;
    if(cont == tam){
      System.out.printf("SIM ");
      return;
    }
    letra = frase.charAt(cont);
    //condicoes de ser letra e vogal
    if ((letra == 'a') || (letra == 'A') || (letra == 'e') || (letra == 'E') || (letra == 'i') || (letra == 'I') || (letra == 'o') || (letra == 'O') || (letra == 'u') || (letra == 'U')){
      Vogal(frase,tam,cont+1); //chama recursividade
    } else{
      System.out.printf("NAO ");
      return;
    }
  }
  
  public static void Consoante(String frase, int tam, int cont){ //verifica se todas as letras sao consoantes
    char letra;
    if(cont == tam){
      System.out.printf("SIM ");
      return;
    }
    letra = frase.charAt(cont);
    int ch = letra;
    if ((letra != 'a') && (letra != 'A') && (letra != 'e') && (letra != 'E') && (letra != 'i') && (letra != 'I') && (letra != 'o') && (letra != 'O') && (letra != 'u') && (letra != 'U') && ((ch > 64 && ch < 91) || (ch > 96 && ch < 123))){ //condicoes de ser diferente de vogal e letra
        Consoante(frase,tam,cont+1); //chama recursividade
    } else{
      System.out.printf("NAO ");
      return;
    }
  }
  
  public static void Inteiro(String frase, int tam, int cont){
    int num;
    if(cont == tam){
      System.out.printf("SIM ");
      return;
    }
    num = frase.charAt(cont);
    if (num > 47 && num < 58){ //condicao ser somente numero
      Inteiro(frase,tam,cont+1); //chama recursividade
    }else{
      System.out.printf("NAO ");
      return;
    }
  }
  
  public static void Real(String frase, int tam, int cont,int ponto){
    int num;
    if (cont == tam){
      System.out.printf("SIM\n");
      return;
    }
    num = frase.charAt(cont);
    if (num > 47 && num < 58){//condicao ser somente numero
      Inteiro(frase,tam,cont+1); //chama recursividade
    }else if (num == 46 && ponto == 0){ //verifica se o numero possui 1 ponto somente
      ponto++;
      Inteiro(frase,tam,cont+1); //chama recursividade
    }else{
      System.out.printf("NAO\n");
      return;
    }
  }
  
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String frase = new String();
    int tam, cont, ponto; //ponto serve para contar quantos pontos foram usados no num real
    
    while(sc.hasNext()){
      frase = sc.nextLine();
      
      if ((frase.length() == 3) && (frase.charAt(0) == 'F') && (frase.charAt(1) == 'I') && (frase.charAt(2) == 'M')){ //acaba programa se receber a palavra FIM
        sc.close();
        return;
      }
      cont = 0;
      ponto = 0;
      tam = frase.length();
      Vogal(frase,tam,cont); //chama os metodos recursivos
      Consoante(frase,tam,cont);
      Inteiro(frase,tam,cont);
      Real(frase,tam,cont,ponto);
    }
    sc.close();
  }
}
