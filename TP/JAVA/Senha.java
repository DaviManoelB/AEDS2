/* Crie um método iterativo que recebe uma string como parâmetro e retorna true se a string é uma senha válida, ou false caso contrário. Uma senha é considerada válida se contém pelo menos 8 caracteres, incluindo pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial (por exemplo, !, @, #, etc.). Na saída padrão, para cada linha de entrada, escreva uma linha de saída com SIM/NÃO indicando se a senha é válida. Por exemplo, se a entrada for Senha123!s, a saída deve ser SIM.
*/

import java.util.*;

public class Senha{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String senha;
    int tam, ch, pontoM, pontom, ponton, pontoe;
    
    while(sc.hasNext()){
      pontoM = pontom = ponton = pontoe = 0; //VOLTA TODOS OS PONTOS PARA 0
      senha = sc.nextLine();
      
      if ((senha.length() == 3) && (senha.charAt(0) == 'F') && (senha.charAt(1) == 'I') && (senha.charAt(2) == 'M')){ //acaba programa se receber a palavra FIM
        sc.close();
        return;
      }
      tam = senha.length();
      for (int i = 0; i < tam; i++){
        ch = senha.charAt(i);
        for(int M = 0; M < tam; M++){ //  VERIFICACAO DE LETRA MAIUSCULA
          if ((ch <= 90) && (ch >= 65)){ //ESTA VERIFICANDO O VALOR DO CHAR PELA TABELA ASCII
            pontoM++; //AUMENTA O PONTO SE POSSUI O CHAR PEDIDO
            M = tam; //SAI DO LOOP APOS VERIFICAR
          }
        }
        for(int m = 0; m < tam; m++){ //  VERIFICACAO DE LETRA MINUSCULA
          if ((ch <= 122) && (ch >= 97)){
            pontom++;
            m = tam;
          }
        }
        for(int n = 0; n < tam; n++){ //  VERIFICACAO DE NUMERO
          if ((ch <= 57) && (ch >= 48)){
            ponton++;
            n = tam;
          }
        }
        for(int e = 0; e < tam; e++){ //  VERIFICACAO DE CHAR ESPECIAL
          if (((ch <= 47) && (ch >= 33)) || ((ch <= 64) && (ch >= 58)) || ((ch <= 96) && (ch >= 91))){ //OS CHAR ESPECIAIS ESTAO ESPALHADOS PELA TABELA
            pontoe++;
            e = tam;
          }
        }
      } 
      if ((tam >= 8) && (pontoM >= 1) && (pontom >= 1) && (ponton >= 1) && (pontoe >= 1)){ //SE O TAMANHO FOR ADEQUADO E TODAS AS EXIGENCIAS FOREM CUMPRIDAS (TODOS OS PONTOS SEREM > 0)
        System.out.println("SIM");
      }else{
        System.out.println("NAO");
      }
    }
    sc.close();
  }
}
