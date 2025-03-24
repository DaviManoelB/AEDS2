/* o codigo recebe o numero de alunos que farão a prova e o numero min de aprovados desejados. 
O programa deve retornar o valor da nota de corte para o numero desejado de aprovados
*/

import java.util.*;

public class NotaCorte{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 1, k,cont = 0;
        while(cont < n){
            n = sc.nextInt();
            k = sc.nextInt();
            int[] notas = new int[n];
            for(int i = 0; i < n; i++){
                notas[i] = sc.nextInt();
            }
            insercao(notas,n);
            System.out.println(notas[k - 1]); //com o array ordenado, basta printar o valor da posicao k-1, que é o valor da nota de corte
            cont++;
        }
    }
    public static void insercao(int[] notas, int n){ //usa o metodo de insercao para ordenar o array
        int tmp;
        for (int i = 0; i < n; i++){
            tmp = notas[i];
            int j = i - 1;
            while ((j >=0) && (notas[j] < tmp)){
                notas[j + 1] = notas[j];
                j--;
            }
            notas[j + 1] = tmp;
        }
    }
}