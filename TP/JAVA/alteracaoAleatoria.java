/*Crie um método iterativo que recebe uma string, sorteia duas letras minúsculas aleatórias (código ASCII ≥ 'a' e ≤ 'z'), substitui todas as ocorrências da primeira letra na string pela segunda e retorna a string com as alterações efetuadas. Na saída padrão, para cada linha de entrada, execute o método desenvolvido nesta questão e mostre a string retornada como uma linha de saída.

Abaixo, observamos um exemplo de entrada supondo que para a primeira linha as letras sorteadas foram 'a' e 'q'. Para a segunda linha, foram 'e' e 'k'.

Exemplo de Entrada	Exemplo de Saída
o rato roeu a roupa do rei de roma	o rqto roeu q roupq do rei de romq
e qwe qwe qwe ewq ewq ewq	k qwk qwk qwk kwq kwq kwq
FIM	FIM

A classe Random do Java gera números (ou letras) aleatórios e o exemplo abaixo mostra uma letra minúscula na tela. Em especial, destacamos que:

seed é a semente para geração de números aleatórios;
nesta questão, por causa da correção automática, a seed será quatro;
a disciplina de Estatística e Probabilidade faz uma discussão sobre "aleatório".

Random gerador = new Random();
gerador.setSeed(4);
System.out.println((char)('a' + (Math.abs(gerador.nextInt()) % 26)));

*/


import java.util.*;
public class alteracaoAleatoria{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in); //inicializa variaveis e gerador aleatorio
    String frase = new String();
    Random gerador = new Random();
    gerador.setSeed(4);
    char mudar, altera;
    int tam,cont = 0;
    
    while(sc.hasNext()){
      cont = 0;
      frase = sc.nextLine();
      
      if ((frase.length() == 3) && (frase.charAt(0) == 'F') && (frase.charAt(1) == 'I') && (frase.charAt(2) == 'M')){ //acaba programa se receber a palavra FIM
        sc.close();
        return;
      }
      tam = frase.length();
      mudar = (char)('a' + (Math.abs(gerador.nextInt()) % 26)); //define o char MUDAR e ALTERA para algum aleatorio das 26 letras do alfabeto
      altera = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
      
        while(cont < tam){
          if(frase.charAt(cont) == mudar){ //compara MUDAR com ALTERA. Se forem iguais, troca a letra
            altera = frase.charAt(cont); 
          }
          cont++; 
        }
      System.out.println(frase); //printa a frase
      
    }
    sc.close();
  }
}
