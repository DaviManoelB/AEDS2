/*Crie um método iterativo que recebe uma string como parâmetro e retorna true se essa é um ``Palíndromo''. Na saída padrão, para cada linha de entrada, escreva uma linha de saída com SIM/NÃO indicando se a linha é um palíndromo. Destaca-se que uma linha de entrada pode ter caracteres não letras.

*/

import java.util.*;

public class palindromoJava{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int tam,aux = 0;
        String frase = new String();

        while(sc.hasNext()){ //funciona enquanto tiver palavra no arquivo
            aux = 0;
            frase = sc.nextLine();
            tam = frase.length();

	          if ((frase.length() == 3) && (frase.charAt(0) == 'F') && (frase.charAt(1) == 'I') && (frase.charAt(2) == 'M')){ //acaba programa se receber a palavra FIM
		            sc.close();
		            return;
            }

            for( int i = 0, j = tam - 1; i < j; i++, j--){ 
                if(frase.charAt(i) != frase.charAt(j)){ //compara o ultimo char com o primeiro, e vai andando ate chegar no meio
                    aux = 1; 
                    System.out.println("NAO");
                    i = j;
                }
            }
            if(aux == 0){ //se todos os char forem iguais aux continua 0 e printa SIM
                    System.out.println("SIM");
            }
        }
    sc.close();
  }
}
