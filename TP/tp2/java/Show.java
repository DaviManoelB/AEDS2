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
        Show tmp;
        for(int i = 0; i < tabela.length; i++) {
            for(int j = i + 1; j < tabela.length; j++) {
                if(tabela[i].title.toLowerCase().compareTo(tabela[j].title.toLowerCase()) > 0) {
                    tmp = tabela[i];
                    tabela[i] = tabela[j];
                    tabela[j] = tmp;
                }
            }
        }
    }

    public static void ordenarTituloInsercao(Show[] tabela) {
        for(int i = 1; i < tabela.length; i++) {
            Show tmp = tabela[i];
            int j = i - 1;
            while((j >= 0) && (tabela[j].type.toLowerCase().compareTo(tmp.type.toLowerCase()) > 0) || (j >= 0) &&
            (tabela[j].type.toLowerCase().compareTo(tmp.type.toLowerCase()) == 0 && tabela[j].title.toLowerCase().compareTo(tmp.title.toLowerCase()) > 0)) {
                
                tabela[j + 1] = tabela[j];
                j--;
            }
            tabela[j + 1] = tmp;
        }
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
        int cont = 0,aux = 0, comparacoes = 0;
        long inicio = System.nanoTime();


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
        long fim = System.nanoTime();
        double tempo = (fim - inicio) / 1e6; // tempo em milissegundos

        // Criar arquivo de log
        try {
            PrintWriter log = new PrintWriter("865235_sequencial.txt");
            log.printf("865235\t%.3f\t%d\n", tempo, comparacoes);
            log.close();
        } catch (IOException e) {
            System.out.println("Erro ao escrever o log");
        }
    }
        */
//endregion

//region mainQ5
/*
    public static void main(String[] args) {

        Show[] tabela = new Show[1370]; //cria um vetor para cadastrar no sistema todos os shows
        long inicio = System.nanoTime();
        int comparacoes = 0, aux = 0;

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
                comparacoes++;
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
        for (int i = 0; i < tabelaOrdenada.length; i++) {
            tabelaOrdenada[i].imprimir(tabelaOrdenada[i]);
        }

        long fim = System.nanoTime();
        double tempo = (fim - inicio) / 1e6; // tempo em milissegundos

        // Criar arquivo de log
        try {
            PrintWriter log = new PrintWriter("865235_sequencial.txt");
            log.printf("865235\t%.3f\t%d\n", tempo, comparacoes);
            log.close();
        } catch (IOException e) {
            System.out.println("Erro ao escrever o log");
        }
    }
    */
//endregion

//region mainQ7
/*
    public static void main(String[] args) {

        Show[] tabela = new Show[1370]; //cria um vetor para cadastrar no sistema todos os shows
        long inicio = System.nanoTime();
        int comparacoes = 0, aux = 0;

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
                comparacoes++;
                if((tabela[i] != null) && (id.equals(tabela[i].getShowId()))){
                    tabela2[aux] = tabela[i].clone();
                    aux++;
                    i = tabela.length; 
                }
            }
        }

        // Copia apenas os elementos válidos para ordenar e imprimir
        Show[] tabelaOrdenada = Arrays.copyOf(tabela2, aux);
        ordenarTituloInsercao(tabelaOrdenada);
        for (int i = 0; i < tabelaOrdenada.length; i++) {
            tabelaOrdenada[i].imprimir(tabelaOrdenada[i]);
        }

        long fim = System.nanoTime();
        double tempo = (fim - inicio) / 1e6; // tempo em milissegundos

        // Criar arquivo de log
        try {
            PrintWriter log = new PrintWriter("865235_insercao.txt");
            log.printf("865235\t%.3f\t%d\n", tempo, comparacoes);
            log.close();
        } catch (IOException e) {
            System.out.println("Erro ao escrever o log");
        }
    }
    */
//endregion

//region mainQ9
public static void main(String[] args) {

    Show[] tabela = new Show[1370]; //cria um vetor para cadastrar no sistema todos os shows
    long inicio = System.nanoTime();
    int comparacoes = 0, aux = 0;

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
            comparacoes++;
            if((tabela[i] != null) && (id.equals(tabela[i].getShowId()))){
                tabela2[aux] = tabela[i].clone();
                aux++;
                i = tabela.length; 
            }
        }
    }

    // Copia apenas os elementos válidos para ordenar e imprimir
    Show[] tabelaOrdenada = Arrays.copyOf(tabela2, aux);
    ordenarHeap(tabelaOrdenada);
    for (int i = 0; i < tabelaOrdenada.length; i++) {
        tabelaOrdenada[i].imprimir(tabelaOrdenada[i]);
    }

    long fim = System.nanoTime();
    double tempo = (fim - inicio) / 1e6; // tempo em milissegundos

    // Criar arquivo de log
    try {
        PrintWriter log = new PrintWriter("865235_heap.txt");
        log.printf("865235\t%.3f\t%d\n", tempo, comparacoes);
        log.close();
    } catch (IOException e) {
        System.out.println("Erro ao escrever o log");
    }
}

//endregion

}