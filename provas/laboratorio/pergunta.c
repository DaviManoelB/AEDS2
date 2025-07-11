/*Muitos sites na internet adicionam uma sessão chamada “Perguntas mais Frequentes” que, como o nome já diz, contém as perguntas mais feitas pelos usuários que utilizam o site.

O portal do URI costuma receber muitas perguntas de seus usuários, então Neilor imaginou que seria uma boa ideia adicionar uma sessão de Perguntas mais Frequentes no site. Como o Neilor anda muito ocupado ultimamente, ele pediu a sua ajuda para adicionar essa sessão.

Dados os identificadores de perguntas feitas pelos usuários, diga o número de perguntas que serão adicionadas na nova sessão do site. Uma pergunta é classificada como “frequente” quando ela é feita ao menos K vezes.


Entrada
Haverá diversos casos de teste. Cada caso de teste inicia com dois inteiros N e K (1 ≤ N ≤ 1000, 1 ≤ K ≤ 100), indicando o número de perguntas realizadas, e o número de vezes que uma pergunta deve ser feita para ser considerada “frequente”, respectivamente.

Em seguida haverá N inteiros P (1 ≤ P ≤ 100), cada um indicando o número de uma determinada pergunta.

O último caso de teste é indicado quando N = K = 0, o qual não deverá ser processado.


Saída
Para cada caso de teste imprima uma linha, contendo um inteiro, indicando o número de perguntas que serão adicionadas na nova sessão do site.*/


#include <stdio.h>

int main(){
    int n, k, resp, aux;

    while(scanf("%d",&n) != EOF){
        scanf("%d",&k);

        if(n == 0 && k == 0){
            return 0;
        }

        resp = 0;
        aux = 0;
        int array[n];
        for(int i = 0; i < n; i++){
            scanf("%d",&array[i]);
        }

        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                if (array[i] == array[j]){
                    if(array[i] != array[i-1]){
                    aux++;
                    }
                }
            }
            if(aux >= k){
                resp++;
            }
            aux = 0;
        }
        printf("%d\n",resp);
    }
}