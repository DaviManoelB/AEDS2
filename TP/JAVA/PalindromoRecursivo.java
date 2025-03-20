/*Crie um método recursivo que recebe uma string como parâmetro e retorna true se essa é um ``Palíndromo''. Na saída padrão, para cada linha de entrada, escreva uma linha de saída com SIM/NÃO indicando se a linha é um palíndromo. Destaca-se que uma linha de entrada pode ter caracteres não letras.
*/

import java.util.*;


public class PalindromoRecursivo{
  
  public static void palindromo(String frase, int inicio, int fim){
    if(inicio >= fim){ 
        System.out.println("SIM");
        return;
    }
    if(frase.charAt(inicio) != frase.charAt(fim)){ //compara o char. se for diferente a palavra nao eh palindromo
      System.out.println("NAO");
      return;
    }
    palindromo(frase, inicio +1, fim -1); //chama novamente o metodo, andando para a prox letra
  }

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String frase;
    int tam;
    
    while(sc.hasNext()){
      frase = sc.nextLine();
      
       if ((frase.length() == 3) && (frase.charAt(0) == 'F') && (frase.charAt(1) == 'I') && (frase.charAt(2) == 'M')){ //acaba programa se receber a palavra FIM
        sc.close();
        return;
      }
      
      tam = frase.length();
      palindromo(frase, 0, tam - 1);   //chama o metodo recursivo 
    }
  }
}

