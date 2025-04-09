/* Crie um método iterativo que recebe uma string como parâmetro e retorna o comprimento da substring mais longa sem caracteres repetidos. Na saída padrão, para cada linha de entrada, escreva uma linha de saída com o comprimento da substring mais longa sem repetição. Por exemplo, se a entrada for abcabcbb, a saída deve ser 3 (correspondendo à substring abc).
*/

import java.util.*;

public class Substring{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String frase;
    int cont, tam, max;
    
    
    while(sc.hasNext()){
      frase = sc.nextLine();
      max = 1; cont = 0;
      
      if ((frase.length() == 3) && (frase.charAt(0) == 'F') && (frase.charAt(1) == 'I') && (frase.charAt(2) == 'M')){ //acaba programa se receber a palavra FIM
        sc.close();
        return;
      }
      frase = frase + '.';
      tam = frase.length();
      
      char [] aux = new char[tam];
      for (int i = 0,x = 0; i < tam -1; i++, x++){
        aux[x] = frase.charAt(i);
        cont++;
        for (int j = 0; j <= i; j++){
          if(frase.charAt(i+1) == '.' || frase.charAt(i) == aux[j]){
            if(max < cont){
              max = cont;
              cont = 0;
            }
            //aux[0] = '\0';

            x = 0;
          }
        }
      }
      if(max < cont){
        max = cont;
        cont = 0;
      }
      System.out.println(max);

      }
      sc.close();
    }
  }

