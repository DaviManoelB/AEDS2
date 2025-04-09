/* Faça um programa que leia um número inteiro n indicando o número de valores reais que devem ser lidos e salvos sequencialmente em um arquivo texto. Após a leitura dos valores, devemos fechar o arquivo. Em seguida, reabri-lo e fazer a leitura de trás para frente usando os métodos getFilePointer e seek da classe RandomAccessFile e mostre todos os valores lidos na tela. Nessa questão, você não pode usar, arrays, strings ou qualquer estrutura de dados. A entrada padrão é composta por um número inteiro n e mais n números reais. A saída padrão corresponde a n números reais mostrados um por linha de saída.
*/

import java.util.*;
import java.io.*;

public class Arquivo{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    sc.useLocale(Locale.US); //serve para o codigo usar (.) em vez de (,) na divisao dos numeros
    int n;
    long tam, pos;
    
    
    try (RandomAccessFile texto = new RandomAccessFile("texto.txt", "rw")){ //CRIA UM ARQUIVO DE NOME TEXTO (RW LE E ESCREVE NO ARQUIVO)
      n = sc.nextInt();
      texto.writeInt(n);
      
      for(int i = 0; i < n; i++){
        texto.writeDouble(sc.nextDouble()); //LE O PROX NUM
      }
    }catch (IOException e) {
		e.printStackTrace();	
	  }
	  
	  try (RandomAccessFile texto = new RandomAccessFile("texto.txt", "r")){ //ABRE O ARQUIVO APENAS PARA LEITURA
	    n = texto.readInt();
	    tam = Double.BYTES;
	    pos = texto.length() - tam; //PEGA O TAMANHO TOTAL DO ARQUIVO MENOS UMA INT
	    
	    while (pos >= Integer.BYTES) {
	      texto.seek(pos); //VA PARA A POSICAO DO VALOR DE POS PARA CONSEGUIR LER OS NUMERO DE TRAS PRA FRENTE
	      double num = texto.readDouble();
	      if (num % 1 == 0) {
          System.out.println((long) num); // se for num inteiro, imprime sem virgula
        } else{
          System.out.println(num);
        }
	      pos -= tam; //DIMINUI O VALOR DE POS PARA IR AO NUMERO ANTERIOR
	    }
	  }catch (IOException e){
	    e.printStackTrace();
	  }
	  
	  try (RandomAccessFile texto = new RandomAccessFile("texto.txt", "rw")){
	    texto.setLength(0);  //LIMPAR O ARQUIVO APOS O PROCESSO
	  }catch (IOException e) {
      e.printStackTrace();
    }
	  sc.close();
  }
}
