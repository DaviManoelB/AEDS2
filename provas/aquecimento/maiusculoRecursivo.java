/*Crie um método iterativo em Java que receba como parâmetro uma string e retorne seu número de caracteres maiúsculos. Em seguida, teste o método anterior usando redirecionamento de entrada e saída.
A entrada padrão é composta por várias linhas sendo que a última contém a palavra FIM. A saída padrão contém um número inteiro para cada linha de entrada.

Refaça a questão anterior de forma recursiva. Atente-se para os detalhes dos retornos de cada um dos métodos. */

import java.util.*;


public class maiusculoRecursivo{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String frase = new String();
        int tam, resp;

        while(sc.hasNext()){
            frase = sc.nextLine();

            if(frase.equals("FIM")){
                sc.close();
                return;
            }

            tam = frase.length();
            resp = 0;
            contagem(frase,tam,resp);

        }
        sc.close();

    }
    public static void contagem(String frase,int tam,int resp){
        if (tam == 0){
            System.out.println(resp);
            return;
        }
        int aux;
        aux = frase.charAt(tam-1);
        if((aux > 64) && (aux < 91)){
            resp++;
        }
        contagem(frase,tam-1,resp);
    }
}