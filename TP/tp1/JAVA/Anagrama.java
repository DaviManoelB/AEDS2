/* Crie um método iterativo que recebe duas strings como parâmetros e retorna true se as strings são anagramas uma da outra, ou false caso contrário. Na saída padrão, para cada par de strings de entrada, escreva uma linha de saída com SIM/NÃO indicando se as strings são anagramas. Por exemplo, se as entradas forem listen e silent, a saída deve ser SIM.
*/

import java.util.*;

public class Anagrama{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String frase, anagrama, hifen;
    char[] fra, ana;
    
    while(sc.hasNext()){
      frase = sc.next();
      
       if ((frase.length() == 3) && (frase.charAt(0) == 'F') && (frase.charAt(1) == 'I') && (frase.charAt(2) == 'M')){ //acaba programa se receber a palavra FIM
        sc.close();
        return;
      }
      
      hifen = sc.next(); //pega o char hifen do meio da frase
      anagrama = sc.next();
      frase = frase.toLowerCase(); //transforma todas as letras para minusculo (ln 21-22)
      anagrama = anagrama.toLowerCase(); 
      fra = frase.toCharArray(); //tranforma as String em array de char
      ana = anagrama.toCharArray();
      
      Arrays.sort(fra); //organiza arrays
      Arrays.sort(ana);
      
      if (Arrays.equals(fra,ana)){    // compara array e define se eh anagrama       
        System.out.println("SIM");
      }else{
        System.out.println("NÃO");
      }
    }
    sc.close();
  }
}
    







