/* Imprimir números em sequência é uma tarefa relativamente simples. Mas e quando se trata de uma sequência espelho? Essa é uma sequência que possui um número inicial e um número final, e todos os números entre eles, inclusive, são dispostos em ordem crescente, sem espaços, e então essa sequência é refletida de forma invertida, como um reflexo no espelho. Por exemplo, se a sequência for de 7 a 12, o resultado seria 789101112211101987.

Escreva um programa que, dados dois números inteiros, imprima a respectiva sequência espelho. */

import java.util.*;

public class espelho{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int inicio, fim, aux, aux2;
        

        while(sc.hasNext()){
            String str = new String();
            inicio = sc.nextInt();
            fim = sc.nextInt();
            aux = (fim - inicio) + 1;
            aux2 = aux * 2;
            int[] resp = new int[aux*2];
            resp[0] = inicio;
            
            
            for (int i = 0,j = inicio; j <= fim; i++, j++){
                str = str + j;
            }
            
            for (int i = aux, j = fim; j >= inicio; i++, j--){
                int temp = j;
                while(temp >= 10){
                    str =str +  (temp % 10);
                    temp /= 10;
                }
                str = str + temp;                                
            }
            //str = str + inicio + '\0';
            System.out.println(str);
        }
        sc.close();
    }
}