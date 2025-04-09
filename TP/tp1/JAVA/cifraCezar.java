/*O Imperador Júlio César foi um dos principais nomes do Império Romano. Entre suas contribuições, temos um algoritmo de criptografia chamado ``Ciframento de César''. Segundo os historiadores, César utilizava esse algoritmo para criptografar as mensagens que enviava aos seus generais durante as batalhas. A ideia básica é um simples deslocamento de caracteres. Assim, por exemplo, se a chave utilizada para criptografar as mensagens for 3, todas as ocorrências do caractere 'a' são substituídas pelo caractere 'd', as do 'b' por 'e', e assim sucessivamente. Crie um método \textbf{iterativo} que recebe uma string como parâmetro e retorna outra contendo a entrada de forma cifrada. Neste exercício, suponha a chave de ciframento três. Na saída padrão, para cada linha de entrada, escreva uma linha com a mensagem criptografada.

*/

//CARACTER ESPECIAL NAO FUNCIONA

import java.util.*;

public class cifraCezar{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String frase = new String();
		int tam, cont, tabela;
		char cifra;
		

		while(sc.hasNext()){ //loop enquanto possui palavra no arquivo
		  	cont = 0;
			frase = sc.nextLine();
      		tam = frase.length();
      		char[] aux = new char[tam];
      
			if ((frase.length() == 3) && (frase.charAt(0) == 'F') && (frase.charAt(1) == 'I') && (frase.charAt(2) == 'M')){ //acaba programa se receber a palavra FIM
				sc.close();
				return;
			}
		
			while(cont < tam){ 
			tabela = frase.charAt(cont);
			if ((tabela > 31 && tabela < 123)){ //verifica se é letra ou char especial
			  cifra = (char) (frase.charAt(cont) + 3); //pega o caracter da vez soma 3 no valor e transforma de volta em char
			} else{
			  cifra = (char) frase.charAt(cont);
			}
			
			
			aux[cont] = cifra; //o aux serve para criar uma outra "string" (array de char) com a letra trocada sem usar os metodos de String

			cont++;
			}
			for(int i = 0; i < tam; i++){ //printa letra por letra da palavra e dps do loop da o \n
			  System.out.print(aux[i]);
			}
			System.out.printf("\n");

		}
		sc.close();
	}
}
