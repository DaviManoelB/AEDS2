/*Crie um método iterativo em Java que receba como parâmetro uma string e retorne seu número de caracteres maiúsculos. Em seguida, teste o método anterior usando redirecionamento de entrada e saída.
A entrada padrão é composta por várias linhas sendo que a última contém a palavra FIM. A saída padrão contém um número inteiro para cada linha de entrada. */

import java.util.*;

public class maiusculo{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String frase = new String();
        int tam, resp, aux;

        while(sc.hasNext()){
            frase = sc.nextLine();
            tam = frase.length();
            resp = 0;

            if(frase.equals("FIM")){
                sc.close();
                return;
            }

            for (int i = 0; i < tam; i++){
                aux = frase.charAt(i);
                if((aux > 64) && (aux < 91)){
                    resp++;
                }
            }
            System.out.println(resp);
        }
    sc.close();
    }
}