/* Crie um método iterativo que recebe uma string como parâmetro e retorna o número de palavras na string. Uma palavra é definida como uma sequência de caracteres separada por espaços. Na saída padrão, para cada linha de entrada, escreva uma linha de saída com o número de palavras. Por exemplo, se a entrada for Hello world, a saída deve ser 2.
*/

import java.util.*;

public class Contagem{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String frase;
    int cont;
    
    while(sc.hasNext()){
      cont = 0;
      frase = sc.next();
      
      if ((frase.length() == 3) && (frase.charAt(0) == 'F') && (frase.charAt(1) == 'I') && (frase.charAt(2) == 'M')){ //acaba programa se receber a palavra FIM
        sc.close();
        return;
      }
      
      while(!frase.contains(".") && !frase.contains("!") && !frase.contains("?")){ //termina o loop ao receber pontuacao de final de frase
        cont++;
        frase = sc.next();
        
      }
      System.out.println(cont + 1); //imprime o numero de palavras. Cont comeca com 0, por isso +1
    }
    sc.close();
  }
}
