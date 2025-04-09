/*Crie um método recursivo que recebe um número inteiro como parâmetro e retorna a soma de seus dígitos. Na saída padrão, para cada linha de entrada, escreva uma linha de saída com o resultado da soma dos dígitos. Por exemplo, se a entrada for 12345, a saída deve ser 15.
*/

import java.util.*;

public class SomaDigitos{

  public static void somaNum(int soma, int num){
    if(num == 0){ //se os algarismos do num acabaram printa a soma e retorna
      System.out.println(soma);
      return;
    }
    soma += num % 10; //divide o numero por 10 e soma o resto. Isso faz com que some semre o numero mais a direita somente
    somaNum(soma,num/10); //chama o metodo mas dessa vez com o numero dividido por 10
  }
  
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String frase = new String();
    int num, soma;
    
    while(sc.hasNext()){
      frase = sc.nextLine(); // JOGO PARA STRING PRIMEIRO PARA VERIFICAR SE EH TEXTO. SE NAO FOR TRANSFORMO DE VOLTA PARA INT (LINHA 20) E REALIZO A SOMA
      
      if ((frase.length() == 3) && (frase.charAt(0) == 'F') && (frase.charAt(1) == 'I') && (frase.charAt(2) == 'M')){ //acaba programa se receber a palavra FIM
        sc.close();
        return;
      }
  
      soma = 0;
      num = Integer.parseInt(frase); //transforma String em int
      somaNum(soma,num); //chama metodo recursivo
    }
    sc.close();
  }
}
