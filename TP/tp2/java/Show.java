import java.io.*;
import java.util.*;

class Show{
    //--------------------------------------------------------ATRIBUTOS--------------------------------------------------------
    private String show_id;
    private String type;
    private String title;
    private String director;
    private String[] cast;
    private String country;
    private String date_added;
    private int release_year;
    private String rating;
    private String duration;
    private String[] listed_in;

    //--------------------------------------------------------CONTRUTOR VAZIO--------------------------------------------------------
//region
    public Show(){ //incializa os atributos
        this.show_id = "";
        this.type = "";
        this.title = "";
        this.director = "";
        this.cast = new String[0];
        this.country = "";
        this.date_added = new String();
        this.release_year = 0;
        this.rating = "";
        this.duration = "";
        this.listed_in = new String[0];
    }
//endregion

    //--------------------------------------------------------CONTRUTOR COM PARAMETROS--------------------------------------------------------
//region
    public Show(String show_id, String type, String title, String director, String[] cast, String country, String date_added, int release_year, String rating, String duration, String[] listed_in){
        this.show_id = show_id; //inicializa os atributos com os valores determinados
        this.type = type;
        this.title = title;
        this.director = director;
        this.cast = cast;
        this.country = country;
        this.date_added = date_added;
        this.release_year = release_year;
        this.rating = rating;
        this.duration = duration;
        this.listed_in = listed_in;
    }
//endregion

    //--------------------------------------------------------GETTERS--------------------------------------------------------
//region
    public String getShowId(){return show_id;} //serve para manipluar os atributos. Como estao como private, precisa de get e set para acessar
    public String gettype(){return type;}
    public String gettitle(){return title;}
    public String getdirector(){return director;}
    public String[] getcast(){return cast;}
    public String getcountry(){return country;}
    public String getDataAdded(){return date_added;}
    public int getReleaseYear(){return release_year;}
    public String getrating(){return rating;}
    public String getduration(){return duration;}
    public String[] getListedIn(){return listed_in;}
//endregion

    //--------------------------------------------------------SETTERS--------------------------------------------------------
//region
    
    public void setshow_id(String show_id) { this.show_id = show_id; }
    public void settype(String type) { this.type = type; }
    public void settitle(String title) { this.title = title; }
    public void setdirector(String director) { this.director = director; }
    public void setcast(String[] cast) { this.cast = cast; }
    public void setcountry(String country) { this.country = country; }
    public void setdate_added(String date_added) { this.date_added = date_added; }
    public void setrelease_year(int release_year) { this.release_year = release_year; }
    public void setrating(String rating) { this.rating = rating; }
    public void setduration(String duration) { this.duration = duration; }
    public void setlisted_in(String[] listed_in) { this.listed_in = listed_in; }
    
//endregion

    //--------------------------------------------------------CLONE--------------------------------------------------------
//region
    @Override
    public Show clone(){
        Show clone = new Show();
        clone.show_id = this.show_id;
        clone.type = this.type;
        clone.title = this.title;
        clone.director = this.director;
        clone.cast = this.cast.clone();
        clone.country = this.country;
        clone.date_added = this.date_added;
        clone.release_year = this.release_year;
        clone.rating = this.rating;
        clone.duration = this.duration;
        clone.listed_in = this.listed_in.clone();
        return clone;
    }
//endregion

    //--------------------------------------------------------IMPRIMIR--------------------------------------------------------
//region
    public void imprimir(Show tabela){
        System.out.print("=> " + tabela.show_id + " ## "+ tabela.title + " ## " + tabela.type + " ## " + tabela.director + " ## [");
        for(int i = 0; i < tabela.cast.length; i++){
            if(i == tabela.cast.length - 1){
                System.out.print(tabela.cast[i]);
            }else{
                System.out.print(tabela.cast[i] + ", ");
            }
        }
        System.out.print("] ## " + tabela.country + " ## " + tabela.date_added + " ## " + tabela.release_year + " ## " + tabela.rating + " ## " + tabela.duration + " ## [");
        for(int i = 0; i < tabela.listed_in.length; i++){
            if(i == tabela.listed_in.length - 1){
                System.out.print(tabela.listed_in[i]);
            }else{
                System.out.print(tabela.listed_in[i] + ", ");
            }
        }
        System.out.print("] ##");
        System.out.println();
    }
//endregion

    //--------------------------------------------------------ORDENAR--------------------------------------------------------
//region
    public void ordenarListas() {
        Arrays.sort(cast);
        Arrays.sort(listed_in);
    }

    public static void ordenarTitulosSelecao(Show[] tabela) {
        long comparacoes = 0, movimentacoes = 0;
        long inicio = System.currentTimeMillis();
        Show tmp;
        
        for(int i = 0; i < tabela.length; i++) {
            for(int j = i + 1; j < tabela.length; j++) {
                comparacoes++;
                if(tabela[i].title.toLowerCase().compareTo(tabela[j].title.toLowerCase()) > 0) {
                    movimentacoes += 3;
                    tmp = tabela[i];
                    tabela[i] = tabela[j];
                    tabela[j] = tmp;
                }
            }
        }
        long fim = System.currentTimeMillis();
        escreverLog("865235_selecao.txt", comparacoes, movimentacoes, fim - inicio);
    }

    public static void ordenarInsercao(Show[] tabela) {
        long comparacoes = 0, movimentacoes = 0;
        long inicio = System.currentTimeMillis();

        for(int i = 1; i < tabela.length; i++) {
            movimentacoes++;
            Show tmp = tabela[i];
            int j = i - 1;
            while (j >= 0 && (++comparacoes > 0) && 
            ((tabela[j].type.toLowerCase().compareTo(tmp.type.toLowerCase()) > 0) || 
            (tabela[j].type.toLowerCase().compareTo(tmp.type.toLowerCase()) == 0 && 
            tabela[j].title.toLowerCase().compareTo(tmp.title.toLowerCase()) > 0))) {
                movimentacoes++;
                tabela[j + 1] = tabela[j];
                j--;
            }
            tabela[j + 1] = tmp;
            movimentacoes++;
        }
        long fim = System.currentTimeMillis();
        escreverLog("865235_insercao.txt", comparacoes, movimentacoes, fim - inicio);
    }

    //region HEAP
    public static void Heap(Show[] tabela) {
        long comparacoes = 0, movimentacoes = 0;
        long inicio = System.currentTimeMillis();
        int n = tabela.length;
    
        Show[] tmp = new Show[n + 1];
        for(int i = 0; i < n; i++) {
            tmp[i + 1] = tabela[i];
            movimentacoes++;
        }
    
        for(int tamHeap = 2; tamHeap <= n; tamHeap++) {
            comparacoes += construir(tmp, tamHeap);
            
        }
    
        int tamHeap = n;
        while (tamHeap > 1) {
            swap(tmp, 1, tamHeap--);
            movimentacoes += 3;
            comparacoes += reconstruir(tmp, tamHeap);
        }
    
        for(int i = 0; i < n; i++) {
            tabela[i] = tmp[i + 1];
            movimentacoes++;
        }
        long fim = System.currentTimeMillis();
        escreverLog("865235_heapsort.txt", comparacoes, movimentacoes, fim - inicio);
    }
    
    private static long construir(Show[] array, int tamHeap) {
        long comparacoes = 0;
        for(int i = tamHeap; i > 1 && comparar(array[i], array[i / 2]) > 0; i /= 2) {
            comparacoes++;
            swap(array, i, i / 2);
        }
        return comparacoes;
    }
    
    private static long reconstruir(Show[] array, int tamHeap) {
        long comparacoes = 0;
        int i = 1;
        while (i <= tamHeap / 2) {
            int filho = getMaiorFilho(array, i, tamHeap);
            comparacoes++;
            if (comparar(array[i], array[filho]) < 0) {
                swap(array, i, filho);
                i = filho;
            } else {
                i = tamHeap;
            }
        }
        return comparacoes;
    }
    
    private static int getMaiorFilho(Show[] array, int i, int tamHeap) {
        int filho;
        if (2 * i == tamHeap || comparar(array[2 * i], array[2 * i + 1]) > 0) {
            filho = 2 * i;
        } else {
            filho = 2 * i + 1;
        }
        return filho;
    }
    
    private static void swap(Show[] array, int i, int j) {
        Show temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    private static int comparar(Show a, Show b) {
        int resultado = a.getdirector().toLowerCase().compareTo(b.getdirector().toLowerCase());
        if (resultado == 0) {
            resultado = a.gettitle().toLowerCase().compareTo(b.gettitle().toLowerCase());
        }
        return resultado;
    }
    //endregion
    //region COUNTING
    public static void Counting(Show[] tabela) {
        long[] comparacoes = new long[1];
        long movimentacoes = 0;
        long inicio = System.currentTimeMillis();
        int n = tabela.length;
    
        int maior = getMaior(tabela);
    
        @SuppressWarnings("unchecked")
        List<Show>[] buckets = new ArrayList[maior + 1];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
    
        for (Show s : tabela) {
            if (s != null) {
                buckets[s.release_year].add(s);
                movimentacoes++;
            }
        }
    
        for (List<Show> bucket : buckets) {
            bucket.sort((a, b) -> {
                comparacoes[0]++;
                return a.title.compareToIgnoreCase(b.title);
            });
        }
    
        int index = 0;
        for (List<Show> bucket : buckets) {
            for (Show s : bucket) {
                tabela[index++] = s;
                movimentacoes++;
            }
        }
    
        long fim = System.currentTimeMillis();
        escreverLog("865235_countingsort.txt", comparacoes[0], movimentacoes, fim - inicio);
    }
    public static int getMaior(Show[] tabela) {
        int maior = 0;
        for (Show s : tabela) {
            if (s != null && s.release_year > maior) {
                maior = s.release_year;
            }
        }
        return maior;
    }
    //endregion
    //region MERGESORT
    public static Comparator<Show> durationLexComparator(int[] comps) {
        return (a, b) -> {
            comps[0]++;
            String durA = a.getduration() == null ? "" : a.getduration().toLowerCase();
            String durB = b.getduration() == null ? "" : b.getduration().toLowerCase();
    
            int cmp = durA.compareTo(durB);
            if (cmp != 0) return cmp;
    
            comps[0]++;
            return a.gettitle().toLowerCase().compareTo(b.gettitle().toLowerCase());
        };
    }
    
    public static void mergeSort(List<Show> lista, int[] comps, int[] movs) {
        if (lista == null || lista.size() <= 1) return;
        mergeSort(lista, 0, lista.size() - 1, durationLexComparator(comps), comps, movs);
    }
    
    private static void mergeSort(List<Show> lista, int inicio, int fim, Comparator<Show> comp, int[] comps, int[] movs) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            mergeSort(lista, inicio, meio, comp, comps, movs);
            mergeSort(lista, meio + 1, fim, comp, comps, movs);
            merge(lista, inicio, meio, fim, comp, movs);
        }
    }
    
    private static void merge(List<Show> lista, int inicio, int meio, int fim, Comparator<Show> comp, int[] movs) {
        List<Show> temp = new ArrayList<>();
        int i = inicio, j = meio + 1;
    
        while (i <= meio && j <= fim) {
            if (comp.compare(lista.get(i), lista.get(j)) <= 0) {
                temp.add(lista.get(i++));
            } else {
                temp.add(lista.get(j++));
            }
            movs[0]++;
        }
    
        while (i <= meio) {
            temp.add(lista.get(i++));
            movs[0]++;
        }
    
        while (j <= fim) {
            temp.add(lista.get(j++));
            movs[0]++;
        }
    
        for (int k = 0; k < temp.size(); k++) {
            lista.set(inicio + k, temp.get(k));
            movs[0]++;
        }
    }
    
    //endregion

    public static void selecaoParcial(Show[] tabela){
        Show tmp;
        
        for(int i = 0; i < 10; i++) {
            for(int j = i + 1; j < tabela.length; j++) {
                if(tabela[i].title.toLowerCase().compareTo(tabela[j].title.toLowerCase()) > 0) {
                    tmp = tabela[i];
                    tabela[i] = tabela[j];
                    tabela[j] = tmp;
                }
            }
        }
    }

    public static void quickParcial(Show[] tabela, int esq, int dir) {
        int i = esq, j = dir;
        Show pivo = tabela[(esq + dir) / 2].clone();
        while (i <= j) {
            while (i <= dir) {
                try {
                    int cmpDate = formatDateISO(tabela[i].date_added).compareTo(formatDateISO(pivo.date_added));
                    if (cmpDate < 0 || (cmpDate == 0 && tabela[i].title.toLowerCase().compareTo(pivo.title.toLowerCase()) < 0)) {
                        i++;
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    if (tabela[i].title.toLowerCase().compareTo(pivo.title.toLowerCase()) < 0) {
                        i++;
                    } else {
                        break;
                    }
                }
            }
            while (j >= esq) {
                try {
                    int cmpDate = formatDateISO(tabela[j].date_added).compareTo(formatDateISO(pivo.date_added));
                    if (cmpDate > 0 || (cmpDate == 0 && tabela[j].title.toLowerCase().compareTo(pivo.title.toLowerCase()) > 0)) {
                        j--;
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    if (tabela[j].title.toLowerCase().compareTo(pivo.title.toLowerCase()) > 0) {
                        j--;
                    } else {
                        break;
                    }
                }
            }
            if (i <= j) {
                Show tmp = tabela[i];
                tabela[i] = tabela[j];
                tabela[j] = tmp;
                i++;
                j--;
            }
        }
        if (esq < j) quickParcial(tabela, esq, j);
        if (i < 10 && i < dir) quickParcial(tabela, i, dir);
    }
    
//endregion

    //--------------------------------------------------------Ler-------------------------------------------------------
//region
    public static Show[] ler() throws Exception {
        List<Show> lista = new ArrayList<>();
    
        Scanner sc = null;
        try {
            sc = new Scanner(new File("/tmp/disneyplus.csv"));
        } catch (Exception e) {
            sc = new Scanner(new File("../disneyplus.csv"));
        }
    
        String cabecalho = sc.nextLine(); // pula cabeçalho
    
        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
    
            // Expressão para separar campos corretamente, mesmo com vírgulas entre aspas
            String[] campos = linha.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            for (int i = 0; i < campos.length; i++) {
                campos[i] = campos[i].replaceAll("^\"|\"$", "").trim();
            }
    
            if (campos.length < 11) continue; // ignora linhas incompletas
    
            // Substituições para campos vazios
            String show_id = campos[0].isEmpty() ? "NaN" : campos[0];
            String type = campos[1].isEmpty() ? "NaN" : campos[1];
            String title = campos[2].isEmpty() ? "NaN" : campos[2];
            String director = campos[3].isEmpty() ? "NaN" : campos[3];
            String[] cast = campos[4].isEmpty() ? new String[] {"NaN"} : campos[4].split(",\\s*");
            String country = campos[5].isEmpty() ? "NaN" : campos[5];
            String date_added = campos[6].isEmpty() ? "NaN" : campos[6];
            int release_year = (campos[7].isEmpty()) ? 0 : Integer.parseInt(campos[7]);
            String rating = campos[8].isEmpty() ? "NaN" : campos[8];
            String duration = campos[9].isEmpty() ? "NaN" : campos[9];
            String[] listed_in = campos[10].isEmpty() ? new String[] {"NaN"} : campos[10].split(",\\s*");
    
            // Cria o objeto Show
            Show s = new Show(show_id, type, title, director, cast, country, date_added, release_year, rating, duration, listed_in);
            s.ordenarListas();
    
            lista.add(s);
        }
    
        sc.close();
        return lista.toArray(new Show[0]);
    }
//endregion

    //--------------------------------------------------------MAIN--------------------------------------------------------
//region mainQ1
    /* 
    public static void main(String[] args) {

        Show[] tabela = new Show[1370]; //cria um vetor para cadastrar no sistema todos os shows
        try {
            
            tabela = ler();
        } catch (Exception e) {
            System.out.println("Erro em usar o metodo ler");
            return;
        }
       
        Scanner sc = new Scanner(System.in);
        String id = new String();
        while(sc.hasNext()){
            id = sc.nextLine(); //le o id a ser printado
           
            if(id.equals("FIM")) {
                sc.close();
                return; //se for FIM, para o loop
            }
            
            for (int i = 1; i < tabela.length; i++) {
                if((tabela[i] != null) && (id.equals(tabela[i].show_id))){ //se o id for igual ao id do vetor, imprime. A primeira condicao serve para evitar  o erro 'NullPointerException'
                    tabela[i].imprimir(tabela[i]);
                    i = tabela.length; //para o loop
                }
            }
        }
        sc.close(); 
    }
    */
//endregion

//region mainQ3 
/* 
public static void main(String[] args) {
        Show[] tabela = new Show[1370]; //cria um vetor para cadastrar no sistema todos os shows
        String[] nomes = new String[tabela.length];
        String titulo = new String();
        int cont = 0,aux = 0;


        try {
            
            tabela = ler();
        } catch (Exception e) {
            System.out.println("Erro em usar o metodo ler");
            return;
        }

        Scanner sc = new Scanner(System.in);
        String id = new String();
        String linha = sc.nextLine();

        while(!linha.equals("FIM")){
            id = linha;
            for(int i = 0; i < tabela.length; i++){
                comparacoes++;
                if(tabela[i] != null && id.equals(tabela[i].show_id)){
                    cont++;
                    nomes[cont] = tabela[i].title;
                    i = 1370;
                }
            }
            linha = sc.nextLine();
        }
        linha = sc.nextLine();
        System.out.println("NAO"); //nao tava saindo 100% sem isso nem no privado nem no publico no verde
        while(!linha.equals("FIM")){
            titulo = linha;
            for(int i = 0; i < cont; i++){
                if(titulo.equals(nomes[i])){
                    System.out.println("SIM");
                    aux = 1;
                    i = cont;
                }
            }
            if(aux == 0){
                System.out.println("NAO");
            }
            aux = 0;
            linha = sc.nextLine();
        }
        cont = 0;
        sc.close();
    }
        */
//endregion

//region mainQ5
/*
    public static void main(String[] args) {

        Show[] tabela = new Show[1370]; //cria um vetor para cadastrar no sistema todos os shows
        int aux = 0;

        try {
            tabela = ler();
        } catch (Exception e) {
            System.out.println("Erro em usar o metodo ler");
            return;
        }

        Scanner sc = new Scanner(System.in);
        String id = new String();
        Show[] tabela2 = new Show[tabela.length];

        while(sc.hasNext()){
            id = sc.nextLine(); //le o id a ser printado

            if(id.equals("FIM")) {
                sc.close();
                break; // fim do loop
            }

            for (int i = 0; i < tabela.length; i++) {
                if((tabela[i] != null) && (id.equals(tabela[i].getShowId()))){
                    tabela2[aux] = tabela[i].clone();
                    aux++;
                    i = tabela.length; 
                }
            }
        }

        // Copia apenas os elementos válidos para ordenar e imprimir
        Show[] tabelaOrdenada = Arrays.copyOf(tabela2, aux);
        ordenarTitulosSelecao(tabelaOrdenada);
        for(int i = 0; i < tabelaOrdenada.length; i++) {
            tabelaOrdenada[i].imprimir(tabelaOrdenada[i]);
        }
    }
    */
//endregion

//region mainQ7
/*
    public static void main(String[] args) {

        Show[] tabela = new Show[1370]; //cria um vetor para cadastrar no sistema todos os shows
        int aux = 0;

        try {
            tabela = ler();
        } catch (Exception e) {
            System.out.println("Erro em usar o metodo ler");
            return;
        }

        Scanner sc = new Scanner(System.in);
        String id = new String();
        Show[] tabela2 = new Show[tabela.length];

        while(sc.hasNext()){
            id = sc.nextLine(); //le o id a ser printado

            if(id.equals("FIM")) {
                sc.close();
                break;
            }

            for (int i = 0; i < tabela.length; i++) {
                if((tabela[i] != null) && (id.equals(tabela[i].getShowId()))){
                    tabela2[aux] = tabela[i].clone();
                    aux++;
                    i = tabela.length; 
                }
            }
        }

        
        Show[] tabelaOrdenada = Arrays.copyOf(tabela2, aux);
        ordenarInsercao(tabelaOrdenada);
        for (int i = 0; i < tabelaOrdenada.length; i++) {
            tabelaOrdenada[i].imprimir(tabelaOrdenada[i]);
        }
    }
    */
//endregion

//region mainQ9
/*
    public static void main(String[] args) {

        Show[] tabela = new Show[1370]; //cria um vetor para cadastrar no sistema todos os shows
        int aux = 0;

        try {
            tabela = ler();
        } catch (Exception e) {
            System.out.println("Erro em usar o metodo ler");
            return;
        }

        Scanner sc = new Scanner(System.in);
        String id = new String();
        Show[] tabela2 = new Show[tabela.length];

        while(sc.hasNext()){
            id = sc.nextLine(); //le o id a ser printado

            if(id.equals("FIM")) {
                sc.close();
                break; // fim do loop
            }

            for (int i = 0; i < tabela.length; i++) {
                if((tabela[i] != null) && (id.equals(tabela[i].getShowId()))){
                    tabela2[aux] = tabela[i].clone();
                    aux++;
                    i = tabela.length; 
                }
            }
        }

        // Copia apenas os elementos válidos para ordenar e imprimir
        Show[] tabelaOrdenada = Arrays.copyOf(tabela2, aux);
        Heap(tabelaOrdenada);
        for (int i = 0; i < tabelaOrdenada.length; i++) {
            tabelaOrdenada[i].imprimir(tabelaOrdenada[i]);
        }
    }
*/
//endregion

//region mainQ11
/*
    public static void main(String[] args) {

        Show[] tabela = new Show[1370]; //cria um vetor para cadastrar no sistema todos os shows
        int aux = 0;

        try {
            tabela = ler();
        } catch (Exception e) {
            System.out.println("Erro em usar o metodo ler");
            return;
        }

        Scanner sc = new Scanner(System.in);
        String id = new String();
        Show[] tabela2 = new Show[tabela.length];

        while(sc.hasNext()){
            id = sc.nextLine(); //le o id a ser printado

            if(id.equals("FIM")) {
                sc.close();
                break; // fim do loop
            }

            for (int i = 0; i < tabela.length; i++) {
                if((tabela[i] != null) && (id.equals(tabela[i].getShowId()))){
                    tabela2[aux] = tabela[i].clone();
                    aux++;
                    i = tabela.length; 
                }
            }
        }

        // Copia apenas os elementos válidos para ordenar e imprimir
        Show[] tabelaOrdenada = Arrays.copyOf(tabela2, aux);
        Counting(tabelaOrdenada);
        for (int i = 0; i < tabelaOrdenada.length; i++) {
            tabelaOrdenada[i].imprimir(tabelaOrdenada[i]);
        }
    }
    */ 
//endregion

//region mainQ13
    /* 
    public static void main(String[] args) {
        Show[] tabela; 
        try {
            tabela = ler();
        } catch (Exception e) {
            System.out.println("Erro em usar o metodo ler");
            return;
        }
        Scanner sc = new Scanner(System.in);
        ArrayList<Show> escolhidos = new ArrayList<>();
        while (sc.hasNext()) {
            String id = sc.nextLine();
            if (id.equals("FIM")) {
                break;
            }
            for (int i = 0; i < tabela.length; i++) {
                if (tabela[i] != null && id.equals(tabela[i].getShowId())) {
                    escolhidos.add(tabela[i].clone());
                    break; 
                }
            }
        }
        sc.close();
        // Ordenar usando o novo mergeSort
        int[] comps = new int[1];
        int[] movs = new int[1];
        long inicio = System.currentTimeMillis();
        mergeSort(escolhidos, comps, movs);
        long fim = System.currentTimeMillis();
        // Imprimir resultados
        for (Show s : escolhidos) {
            s.imprimir(s);
        }
        escreverLog("865235_mergesort.txt", comps[0], movs[0], fim - inicio);
    }
    */
//endregion

//region mainQ15
    /* 
    public static void main(String[] args) {

        Show[] tabela = new Show[1370]; //cria um vetor para cadastrar no sistema todos os shows
        int aux = 0;

        try {
            tabela = ler();
        } catch (Exception e) {
            System.out.println("Erro em usar o metodo ler");
            return;
        }

        Scanner sc = new Scanner(System.in);
        String id = new String();
        Show[] tabela2 = new Show[tabela.length];

        while(sc.hasNext()){
            id = sc.nextLine(); //le o id a ser printado

            if(id.equals("FIM")) {
                sc.close();
                break; // fim do loop
            }

            for (int i = 0; i < tabela.length; i++) {
                if((tabela[i] != null) && (id.equals(tabela[i].getShowId()))){
                    tabela2[aux] = tabela[i].clone();
                    aux++;
                    i = tabela.length; 
                }
            }
        }

        // Copia apenas os elementos válidos para ordenar e imprimir
        Show[] tabelaOrdenada = Arrays.copyOf(tabela2, aux);
        selecaoParcial(tabelaOrdenada);
        for(int i = 0; i < 10; i++) {
            tabelaOrdenada[i].imprimir(tabelaOrdenada[i]);
        }
    }
    */
//endregion

//region mainQ18
    /* 
    public static void main(String[] args) {

        Show[] tabela = new Show[1370]; //cria um vetor para cadastrar no sistema todos os shows
        int aux = 0;

        try {
            tabela = ler();
        } catch (Exception e) {
            System.out.println("Erro em usar o metodo ler");
            return;
        }

        Scanner sc = new Scanner(System.in);
        String id = new String();
        Show[] tabela2 = new Show[tabela.length];

        while(sc.hasNext()){
            id = sc.nextLine(); //le o id a ser printado

            if(id.equals("FIM")) {
                sc.close();
                break; // fim do loop
            }

            for (int i = 0; i < tabela.length; i++) {
                if((tabela[i] != null) && (id.equals(tabela[i].getShowId()))){
                    tabela2[aux] = tabela[i].clone();
                    aux++;
                    i = tabela.length; 
                }
            }
        }

        // Copia apenas os elementos válidos para ordenar e imprimir
        Show[] tabelaOrdenada = Arrays.copyOf(tabela2, aux);
        int tmp = tabelaOrdenada.length;
        quickParcial(tabelaOrdenada, 0, (tmp - 1));
        for(int i = 0; i < 10; i++) {
            tabelaOrdenada[i].imprimir(tabelaOrdenada[i]);
        }
    }
    */
//endregion



    //--------------------------------------------------------ARQUIVO LOG--------------------------------------------------------
    public static void escreverLog(String nomeArquivo, long comparacoes, long movimentacoes, long tempo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            writer.write("865235\t" + comparacoes + "\t" + movimentacoes + "\t" + tempo + "\n");
        } catch (IOException e) {
            System.err.println("Erro ao escrever no log: " + e.getMessage());
        }
    }
    
    public static String formatDateISO(String data) {
        String[] meses = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        };
    
        String[] partes = data.replace(",", "").split(" ");
        int mes = 0;
        for (int i = 0; i < meses.length; i++) {
            if (meses[i].equalsIgnoreCase(partes[0])) {
                mes = i + 1;
                break;
            }
        }
    
        String dia = partes[1];
        if (dia.length() == 1) dia = "0" + dia;
        String mesStr = (mes < 10 ? "0" + mes : Integer.toString(mes));
    
        return partes[2] + "-" + mesStr + "-" + dia;
    }
}

    
