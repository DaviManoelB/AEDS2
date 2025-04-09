/* O Imperador Júlio César foi um dos principais nomes do Império Romano. Entre suas contribuições, temos um algoritmo de criptografia chamado ``Ciframento de César''. Segundo os historiadores, César utilizava esse algoritmo para criptografar as mensagens que enviava aos seus generais durante as batalhas. A ideia básica é um simples deslocamento de caracteres. Assim, por exemplo, se a chave utilizada para criptografar as mensagens for 3, todas as ocorrências do caractere 'a' são substituídas pelo caractere 'd', as do 'b' por 'e', e assim sucessivamente. Crie um método \textbf{iterativo} que recebe uma string como parâmetro e retorna outra contendo a entrada de forma cifrada. Neste exercício, suponha a chave de ciframento três. Na saída padrão, para cada linha de entrada, escreva uma linha com a mensagem criptografada.*/

import java.util.*;

public class CezarRecursivo{
  public static void cifra(String frase, int tam, int cont){
    if (cont == tam){  //condicao de parada
      System.out.printf("\n"); //pula linha apos acabar a palavra
      return;
    }
    
    char letra, aux;
    letra = frase.charAt(cont);
    if ((letra > 31 && letra < 123)){ //verifica se é letra ou char especial
			  aux = (char) (letra + 3); //pega o caracter da vez soma 3 no valor e transforma de volta em char
			} else{
			  aux = (char) letra;
			}
			
    
    System.out.print(aux);
    cifra(frase, tam, cont+1); //chama novamente o metodo para alterar a prox letra
  }
  
  
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String frase = new String();
    int tam, cont;
    
    while(sc.hasNext()){
      cont = 0; //zera contador no inicio de cada palavra
      frase = sc.nextLine();
      
      if ((frase.length() == 3) && (frase.charAt(0) == 'F') && (frase.charAt(1) == 'I') && (frase.charAt(2) == 'M')){ //acaba programa se receber a palavra FIM
        sc.close();
        return;
      }
      tam = frase.length();
      cifra(frase, tam, cont); //chama metodo recursivo
    }
    sc.close();
  }
}
