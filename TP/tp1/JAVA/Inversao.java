/* Crie um método iterativo que recebe uma string como parâmetro e retorna a string invertida. Na saída padrão, para cada linha de entrada, escreva uma linha de saída com a string invertida. Por exemplo, se a entrada for abcde, a saída deve ser edcba.
*/

import java.util.*;

public class Inversao{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String frase = new String();
    int tam;
    
    
    while(sc.hasNext()){
      frase = sc.nextLine();
      tam = frase.length();
      char[] aux = new char[tam];
      
      if ((frase.length() == 3) && (frase.charAt(0) == 'F') && (frase.charAt(1) == 'I') && (frase.charAt(2) == 'M')){ //acaba programa se receber a palavra FIM
        sc.close();
        return;
      }
      for (int i = 0, j = tam-1; i < tam; i++, j--){
        aux[i] = frase.charAt(j);
      }
      for(int i = 0; i < tam; i++){
        System.out.print(aux[i]);
      }
      System.out.printf("\n");
      //System.out.println(new StringBuilder(frase).reverse());
    }
    sc.close();
  }
}

