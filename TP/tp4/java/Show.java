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

    //--------------------------------------------------------ARVORE--------------------------------------------------------
static class No{
        Show elemento;
        No esq, dir;

        public No(Show elemento){
            this(elemento, null, null);
        }
        public No(Show elemento, No esq, No dir){
            this.elemento = elemento;
            this.esq = esq;
            this.dir = dir;
        }
    }

//region ARVORE BINARIA  
    static class Arvore{
        private No raiz;
        
        public Arvore(){
            this.raiz = null;
        }

        public void inserir(Show elemento){
            raiz = inserir(raiz, elemento);
        }

        private No inserir(No no, Show elemento){
            if(no == null){
                no = new No(elemento);
            }else if(elemento.gettitle().compareTo(no.elemento.gettitle()) < 0){
                no.esq = inserir(no.esq, elemento);
            }else if(elemento.gettitle().compareTo(no.elemento.gettitle()) > 0){
                no.dir = inserir(no.dir, elemento);
            }else if(elemento.gettitle().compareTo(no.elemento.gettitle()) == 0){
                return no;
            }
            return no;
        }

        public int pesquisar(String title) {
            int comparacoes = 0;
            System.out.printf("=>raiz ");
            return pesquisar(title, raiz, 0);
        }
        private int pesquisar(String title, No no, int comparacoes) {
            
            No tmp = no;
            if(tmp == null) {
                comparacoes++;
                System.out.printf("NAO\n");
            } else if(tmp.elemento.title.compareTo(title) > 0){
                comparacoes++;
                System.out.printf("esq ");
                comparacoes += pesquisar(title, no.esq,0);
            } else if(tmp.elemento.title.compareTo(title) < 0){
                comparacoes++;
                System.out.printf("dir ");
                comparacoes += pesquisar(title, no.dir, 0);
            } else if(tmp.elemento.title.compareTo(title) == 0) {
                comparacoes++;
                System.out.printf("SIM\n");
            }
            return comparacoes;
        }
    }
//endregion

//region ALVINEGRA
    static class NoAN{
        Show elemento;
        NoAN esq, dir;
        boolean cor;

        public NoAN(Show elemento){
            this(elemento, false, null, null);
        }
        public NoAN(Show elemento, boolean cor){
            this(elemento, cor, null, null);
        }
        public NoAN(Show elemento,boolean cor, NoAN esq, NoAN dir){
            this.elemento = elemento;
            this.esq = esq;
            this.dir = dir;
            this.cor = cor;
        }
    }
    static class Alvinegra{
        public NoAN raizAN;

        public Alvinegra(){
            raizAN = null;
        }

        public void inserirAN(Show elemento) throws Exception {
            // Se a arvore estiver vazia
            if (raizAN == null) {
                raizAN = new NoAN(elemento);

            // Senao, se a arvore tiver um elemento
            } else if (raizAN.esq == null && raizAN.dir == null) {
                if (elemento.title.compareTo(raizAN.elemento.title) < 0) {
                    raizAN.esq = new NoAN(elemento);
                } else {
                    raizAN.dir = new NoAN(elemento);
                }

            // Senao, se a arvore tiver dois elementos (raizAN e dir)
            } else if (raizAN.esq == null) {
                if (elemento.title.compareTo(raizAN.elemento.title) < 0) {
                    raizAN.esq = new NoAN(elemento);

                } else if (elemento.title.compareTo(raizAN.dir.elemento.title) < 0) {
                    raizAN.esq = new NoAN(raizAN.elemento);
                    raizAN.elemento = elemento;

                } else {
                    raizAN.esq = new NoAN(raizAN.elemento);
                    raizAN.elemento = raizAN.dir.elemento;
                    raizAN.dir.elemento = elemento;

                }
                raizAN.esq.cor = raizAN.dir.cor = false;

            // Senao, se a arvore tiver dois elementos (raizAN e esq)
            } else if (raizAN.dir == null) {
                if (elemento.title.compareTo(raizAN.elemento.title) > 0) {
                    raizAN.dir = new NoAN(elemento);
 
                } else if (elemento.title.compareTo(raizAN.esq.elemento.title) > 0) {
                    raizAN.dir = new NoAN(raizAN.elemento);
                    raizAN.elemento = elemento;

                } else {
                    raizAN.dir = new NoAN(raizAN.elemento);
                    raizAN.elemento = raizAN.esq.elemento;
                    raizAN.esq.elemento = elemento;
                raizAN.esq.cor = raizAN.dir.cor = false;
                }
            // Senao, a arvore tem tres ou mais elementos
            } else {
                inserirAN(elemento, null, null, null, raizAN);
            }
            raizAN.cor = false;
            
        }
        public void inserirAN(Show elemento, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) throws Exception {
            if (i == null) {
                if (elemento.title.compareTo(pai.elemento.title) < 0) {
                    i = pai.esq = new NoAN(elemento, true);
                } else {
                    i = pai.dir = new NoAN(elemento, true);
                }
                if (pai.cor == true) {
                    balancear(bisavo, avo, pai, i);
                }
            } else {
                // Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
                if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
                    i.cor = true;
                    i.esq.cor = i.dir.cor = false;
                    if (i == raizAN) {
                    i.cor = false;
                    } else if (pai.cor == true) {
                    balancear(bisavo, avo, pai, i);
                    }
                }
                if (elemento.title.compareTo(i.elemento.title) < 0) {
                    inserirAN(elemento, avo, pai, i, i.esq);
                } else if (elemento.title.compareTo(i.elemento.title) > 0) {
                    inserirAN(elemento, avo, pai, i, i.dir);
                } else {
                    throw new Exception("Erro inserirAN (elemento repetido)!");
                }
            }
        }
        private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
            // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
            if (pai.cor == true) {
                // 4 tipos de reequilibrios e acoplamento
                if (pai.elemento.title.compareTo(avo.elemento.title) > 0) { // rotacao a esquerda ou direita-esquerda
                    if (i.elemento.title.compareTo(pai.elemento.title) > 0) {
                    avo = rotacaoEsq(avo);
                    } else {
                    avo = rotacaoDirEsq(avo);
                    }
                } else { // rotacao a direita ou esquerda-direita
                    if (i.elemento.title.compareTo(pai.elemento.title) < 0) {
                    avo = rotacaoDir(avo);
                    } else {
                    avo = rotacaoEsqDir(avo);
                    }
                }
                if (bisavo == null) {
                    raizAN = avo;
                } else if (avo.elemento.title.compareTo(bisavo.elemento.title) < 0) {
                    bisavo.esq = avo;
                } else {
                    bisavo.dir = avo;
                }
                // reestabelecer as cores apos a rotacao
                avo.cor = false;
                avo.esq.cor = avo.dir.cor = true;
            } // if(pai.cor == true)
        }
        private NoAN rotacaoDir(NoAN no) {
            
            NoAN noEsq = no.esq;
            NoAN noEsqDir = noEsq.dir;

            noEsq.dir = no;
            no.esq = noEsqDir;

            return noEsq;
        }

        private NoAN rotacaoEsq(NoAN no) {
            
            NoAN noDir = no.dir;
            NoAN noDirEsq = noDir.esq;

            noDir.esq = no;
            no.dir = noDirEsq;
            return noDir;
        }

        private NoAN rotacaoDirEsq(NoAN no) {
            no.dir = rotacaoDir(no.dir);
            return rotacaoEsq(no);
        }

        private NoAN rotacaoEsqDir(NoAN no) {
            no.esq = rotacaoEsq(no.esq);
            return rotacaoDir(no);
        }

        public int pesquisar(String titulo) {
            System.out.printf("=>raiz ");
            return pesquisar(titulo, raizAN, 0);
        }
        private int pesquisar(String titulo, NoAN i, int comparacoes) {
            if (i == null) {
                comparacoes++;
                System.out.printf("NAO\n");
            } else if (titulo.compareTo(i.elemento.title) == 0) {
                comparacoes++;
                System.out.printf("SIM\n");
            } else if (titulo.compareTo(i.elemento.title) < 0) {
                comparacoes++;
                System.out.printf("esq ");
                comparacoes += pesquisar(titulo, i.esq, 0);
            } else {
                System.out.printf("dir ");
                comparacoes += pesquisar(titulo, i.dir,0);
            }
            return comparacoes;
        }
    }     

//endregion

//region ARVOREdeARVORE

    static class NoA{
        int elemento;
        NoA esq, dir;
        No tabela;
        public NoA(int elemento){
            this(elemento, null, null, null);
        }
        public NoA(int elemento, NoA esq, NoA dir, No tabela){
            this.elemento = elemento;
            this.esq = esq;
            this.dir = dir;
            this.tabela = tabela;
        }
    }

    static class ArvoreArvore{
        private NoA raizA;

        public ArvoreArvore(){
            this.raizA = null;
            inserirAA (7);
            inserirAA (3);
            inserirAA (11);
            inserirAA (1);
            inserirAA (5);
            inserirAA (9);
            inserirAA (13);
            inserirAA (0);
            inserirAA (2);
            inserirAA (4);
            inserirAA (6);
            inserirAA (8);
            inserirAA (10);
            inserirAA (12);
            inserirAA (14);
        }
        public void inserirAA(int resto){
            raizA = inserirAA(resto, raizA);
        }
        private NoA inserirAA(int resto, NoA no){
            if(no == null){
                no = new NoA(resto);
            }else if(resto < no.elemento){
                no.esq = inserirAA(resto, no.esq);
            }else if(resto > no.elemento){
                no.dir = inserirAA(resto, no.dir);
            }
            return no;
        }
        public void inserirShow(Show elemento){
            NoA no = pesquisarNoA(elemento.release_year % 15, raizA);
            no.tabela = inserirNo(no.tabela, elemento);
        }

        // Método auxiliar para inserir em uma árvore binária de No (secundária)
        private No inserirNo(No no, Show elemento){
            if(no == null){
                no = new No(elemento);
            }else if(elemento.gettitle().compareTo(no.elemento.gettitle()) < 0){
                no.esq = inserirNo(no.esq, elemento);
            }else if(elemento.gettitle().compareTo(no.elemento.gettitle()) > 0){
                no.dir = inserirNo(no.dir, elemento);
            }
            // Se for igual, não insere (evita duplicatas)
            return no;
        }

        public NoA pesquisarNoA(int resto, NoA no){
            if(no == null || no.elemento == resto){
                return no;
            }else if(resto < no.elemento){
                return pesquisarNoA(resto, no.esq);
            }else{
                return pesquisarNoA(resto, no.dir);
            }
        }

        // Implementação do método pesquisar(String titulo)
        public void pesquisar(String titulo) {
            System.out.print("raiz ");
            if (pesquisarTituloEmTodasArvores(raizA, titulo, false)) {
                System.out.println(" SIM");
            } else {
                System.out.println(" NAO");
            }
        }

        // Novo método com impressão do caminho entre as árvores principais
        private boolean pesquisarTituloEmTodasArvores(NoA noA, String titulo, boolean veioDaPrincipal) {
            if (noA == null) return false;
            // Busca na árvore secundária deste nó
            if (pesquisarTituloNaSecundaria(noA.tabela, titulo)) return true;
            // Busca nos filhos, imprimindo ESQ/DIR ao trocar de árvore principal
            if (noA.esq != null) {
                System.out.print(" ESQ ");
                if (pesquisarTituloEmTodasArvores(noA.esq, titulo, true)) return true;
            }
            if (noA.dir != null) {
                System.out.print(" DIR ");
                if (pesquisarTituloEmTodasArvores(noA.dir, titulo, true)) return true;
            }
            return false;
        }

        // Pesquisa o título na árvore binária secundária, imprimindo o caminho
        private boolean pesquisarTituloNaSecundaria(No no, String titulo) {
            while (no != null) {
                if (titulo.compareTo(no.elemento.gettitle()) == 0) {
                    return true;
                } else if (titulo.compareTo(no.elemento.gettitle()) < 0) {
                    System.out.print("esq ");
                    no = no.esq;
                } else {
                    System.out.print("dir ");
                    no = no.dir;
                }
            }
            return false;
        }
    }
//endregion

    //--------------------------------------------------------HASH--------------------------------------------------------
//region HASH COM RESERVA
    static class HashReserva{
        Show[] tabela;
        int m1, m2, m, reserva;

        public HashReserva(){
            this(21,9);
        }
        private HashReserva(int m1, int m2){
            this.m1 = m1; //tamanho da tabela
            this.m2 = m2; //tamanho da reserva
            this.m = m1 + m2;
            this.reserva = 0;
            tabela = new Show[this.m];
            for(int i = 0; i < tabela.length; i++){
                tabela[i] = null;
            }
        }
        public void inserir(Show elemento) {
            int ascii = 0;
            for (int i = 0; i < elemento.gettitle().length(); i++) {
                ascii += (int) elemento.gettitle().charAt(i);
            }
            if(tabela[ascii % m1] == null){
                tabela[ascii % m1] = elemento;
            } else {
                if(reserva < m2){
                    tabela[m1 + reserva] = elemento;
                    reserva++;
                }
            }
        }

        public void pesquisar(String titulo){
            int ascii = 0;
            for (int i = 0; i < titulo.length(); i++){
                ascii += (int) titulo.charAt(i);
            }
            if (tabela[ascii % m1] != null && tabela[ascii % m1].gettitle().equals(titulo)){
                System.out.println(" (Posicao: " + (ascii % m1) + ") SIM");
            }else{
                System.out.println(" (Posicao: " + (ascii % m1) + ") NAO");
            }
        }

    }
//endregion

//region HASH COM REHASH
    static class HashRehash{
        Show[] tabela;
        int m;
        public HashRehash(){
            this(21);
        }
        private HashRehash(int m){
            this.m = m;
            this.tabela = new Show[m];
            for(int i = 0; i < m; i++){tabela[i] = null;}
        }
        public void inserir(Show elemento){
            int ascii = 0;
            for (int i = 0; i < elemento.gettitle().length(); i++) {
                ascii += (int) elemento.gettitle().charAt(i);
            }
            if(tabela[ascii % m] == null){
                tabela[ascii % m] = elemento;
            }else{
                int pos = rehash(ascii);
                if(tabela[pos] == null){
                    tabela[pos] = elemento;
                }
            }
        }
        public int rehash(int ascii){
            return (ascii + 1) % m;
        }
        public void pesquisar(String titulo){
            int ascii = 0;
            for (int i = 0; i < titulo.length(); i++) {
                ascii += (int) titulo.charAt(i);
            } 
            if(tabela[ascii % m] != null && tabela[ascii % m].gettitle().equals(titulo)){
                System.out.println(" (Posicao: " + (ascii % m) + ") SIM");
            }else if(tabela[rehash(ascii)] != null && tabela[rehash(ascii)].gettitle().equals(titulo)){
                System.out.println(" (Posicao: " + ((ascii + 1) % m) + ") SIM");
            }else{
                System.out.println(" (Posicao: " + (ascii % m) + ") NAO");
            }
        }
    }
//endregion

//region HASH LISTA SIMPLES
    static class HashSimples{
        Lista tabela[];
        int m;
        static class Celula{
            Show elemento;
            Celula prox;
            public Celula(){
                this(null);
            }
            private Celula(Show elemento){
                this.elemento = elemento;
                this.prox = null;
            }
            private Celula(Show elemento, Celula prox){
                this.elemento = elemento;
                this.prox = prox;
            }
        }
        static class Lista{
            private Celula primeiro, ultimo;

            public Lista(){
                primeiro = new Celula();
                ultimo = primeiro;
            }
            public void inserirLista(Show elemento){
                Celula tmp = new Celula(elemento);
                ultimo.prox = tmp;
                ultimo = ultimo.prox;
                tmp = null;
            }
            public boolean pesquisarLista(String titulo){
                boolean achou = false;
                Celula tmp = primeiro.prox;
                while(tmp != null){
                    if(tmp.elemento.gettitle().equals(titulo)){
                        achou = true;
                        tmp = null;
                    }else{tmp = tmp.prox;}
                }
                return achou;
            }
        }
        public HashSimples(){
            this(21);
        }
        public HashSimples(int m){
            this.m = m;
            tabela = new Lista[m];
            for(int i = 0; i < tabela.length; i++){
                tabela[i] = null;
            }
        }
        public int h(String elemento){
            int ascii = 0;
            for (int i = 0; i < elemento.length(); i++) {
                ascii += (int) elemento.charAt(i);
            }
            return ascii % m;
        }
        public void inserir(Show elemento){
            int ascii = h(elemento.gettitle());
            if(tabela[ascii] == null){
                tabela[ascii] = new Lista();
            }else{
                tabela[ascii].inserirLista(elemento);
            }
        }
        public void pesquisar(String titulo){
            boolean achou = false;
            int ascii = h(titulo);
            if(tabela[ascii] != null){
                Celula tmp = tabela[ascii].primeiro;
                achou = tabela[ascii].pesquisarLista(titulo);
            }
            if(tabela[ascii] == null || !achou){
                System.out.println(" (Posicao: " + ascii + ") NAO");
            }else{
                System.out.println(" (Posicao: " + ascii + ") SIM");
            }
        }
    }
//endregion
    //--------------------------------------------------------MAIN--------------------------------------------------------
//region mainQ1 
/*
    public static void main(String[] args){
        Arvore arvore = new Arvore();
        Show[] tabela = new Show[1370]; //cria um vetor para cadastrar no sistema todos os shows
        int comparacoes = 0;
        long tempoInicial = System.currentTimeMillis();
        String titulo = new String();
        


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
            for (int i = 0; i < tabela.length; i++) {
                if((tabela[i] != null) && (id.equals(tabela[i].getShowId()))){ 
                    try {
                        arvore.inserir(tabela[i]);
                    } catch (Exception e) {
                        // Trate o erro conforme necessário, por exemplo:
                        System.out.println("Erro ao inserir na árvore: " + e.getMessage());
                    }
                    i = tabela.length; //para o loop
                }
            }
            linha = sc.nextLine();
        }
        linha = sc.nextLine();
        //System.out.println("NAO"); //nao tava saindo 100% sem isso nem no privado nem no publico no verde
        while(!linha.equals("FIM")){
            titulo = linha;
            comparacoes += arvore.pesquisar(titulo);
            linha = sc.nextLine();
        }
        long tempoFinal = System.currentTimeMillis();
        long tempoTotal = tempoFinal - tempoInicial;
        
        escreverLog("865235_arvoreBinaria.txt", comparacoes, tempoTotal);
        sc.close();
    }
   */  
//endregion

//region mainQ2
/**/
    public static void main(String[] args){
        //Arvore arvore = new Arvore();
        ArvoreArvore arvoreResto = new ArvoreArvore();
        Show[] tabela = new Show[1370]; //cria um vetor para cadastrar no sistema todos os shows
        String titulo = new String();
        


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
            for (int i = 0; i < tabela.length; i++) {
                if((tabela[i] != null) && (id.equals(tabela[i].getShowId()))){ 
                    try {
                        arvoreResto.inserirShow(tabela[i]);
                    } catch (Exception e) {
                        // Trate o erro conforme necessário, por exemplo:
                        System.out.println("Erro ao inserir na árvore: " + e.getMessage());
                    }
                    i = tabela.length; //para o loop
                }
            }
            linha = sc.nextLine();
        }
        linha = sc.nextLine();
        //System.out.println("NAO"); //nao tava saindo 100% sem isso nem no privado nem no publico no verde
        while(!linha.equals("FIM")){
            titulo = linha;
            arvoreResto.pesquisar(titulo);
            linha = sc.nextLine();
        }
        sc.close();
    }
    
//endregion

//region mainQ4
/*
    public static void main(String[] args){
        Alvinegra arvore = new Alvinegra();
        Show[] tabela = new Show[1370]; //cria um vetor para cadastrar no sistema todos os shows
        int comparacoes = 0;
        long tempoInicial = System.currentTimeMillis();
        String titulo = new String();
        

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
            for (int i = 0; i < tabela.length; i++) {
                if((tabela[i] != null) && (id.equals(tabela[i].getShowId()))){ 
                    try {
                        arvore.inserirAN(tabela[i]);
                    } catch (Exception e) {
                        // Trate o erro conforme necessário, por exemplo:
                        System.out.println("Erro ao inserir na árvore: " + e.getMessage());
                    }
                    i = tabela.length; //para o loop
                }
            }
            linha = sc.nextLine();
        }
        linha = sc.nextLine();
        //System.out.println("NAO"); //nao tava saindo 100% sem isso nem no privado nem no publico no verde
        while(!linha.equals("FIM")){
            titulo = linha;
            comparacoes = arvore.pesquisar(titulo);
            linha = sc.nextLine();
        }
        long tempoFinal = System.currentTimeMillis();
        long tempoTotal = tempoFinal - tempoInicial;
        
        escreverLog("865235_alvinegra.txt", comparacoes, tempoTotal);
        sc.close();
    }
*/
//endregion

//region mainQ5
/* 
    public static void main(String[] args){
        HashReserva hash = new HashReserva();
        Show[] tabela = new Show[1370]; //cria um vetor para cadastrar no sistema todos os shows
        int comparacoes = 0;
        long tempoInicial = System.currentTimeMillis();
        String titulo = new String();


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
            for (int i = 0; i < tabela.length; i++) {
                if((tabela[i] != null) && (id.equals(tabela[i].getShowId()))){ 
                    hash.inserir(tabela[i]);
                    i = tabela.length; //para o loop
                }
            }
            linha = sc.nextLine();
        }
        linha = sc.nextLine();
        //System.out.println("NAO"); //nao tava saindo 100% sem isso nem no privado nem no publico no verde
        while(!linha.equals("FIM")){
            titulo = linha;
            hash.pesquisar(titulo);
            linha = sc.nextLine();
        }
        long tempoFinal = System.currentTimeMillis();
        long tempoTotal = tempoFinal - tempoInicial;
        comparacoes = tabela.length; // Considerando que cada pesquisa conta como uma comparação
        escreverLog("865235_hashReserva.txt", comparacoes, tempoTotal);
        sc.close();
    }
    */
//endregion

//region mainQ6
/*  
    public static void main(String[] args){
        HashRehash hash = new HashRehash();
        Show[] tabela = new Show[1370]; //cria um vetor para cadastrar no sistema todos os shows
        int comparacoes = 0;
        long tempoInicial = System.currentTimeMillis();
        String titulo = new String();


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
            for (int i = 0; i < tabela.length; i++) {
                if((tabela[i] != null) && (id.equals(tabela[i].getShowId()))){ 
                    hash.inserir(tabela[i]);
                    i = tabela.length; //para o loop
                }
            }
            linha = sc.nextLine();
        }
        linha = sc.nextLine();
        //System.out.println("NAO"); //nao tava saindo 100% sem isso nem no privado nem no publico no verde
        while(!linha.equals("FIM")){
            titulo = linha;
            hash.pesquisar(titulo);
            linha = sc.nextLine();
        }
        long tempoFinal = System.currentTimeMillis();
        long tempoTotal = tempoFinal - tempoInicial;
        comparacoes = tabela.length; // Considerando que cada pesquisa conta como uma comparação
        escreverLog("865235_hashRehash.txt", comparacoes, tempoTotal);
        sc.close();
    }
   */
//endregion

//region mainQ7
/* 
    public static void main(String[] args){
        HashSimples hash = new HashSimples();
        Show[] tabela = new Show[1370]; //cria um vetor para cadastrar no sistema todos os shows
        int comparacoes = 0;
        long tempoInicial = System.currentTimeMillis();
        
        String titulo = new String();


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
            for (int i = 0; i < tabela.length; i++) {
                if((tabela[i] != null) && (id.equals(tabela[i].getShowId()))){ 
                    hash.inserir(tabela[i]);
                    i = tabela.length; //para o loop
                }
            }
            linha = sc.nextLine();
        }
        linha = sc.nextLine();
        //System.out.println("NAO"); //nao tava saindo 100% sem isso nem no privado nem no publico no verde
        while(!linha.equals("FIM")){
            titulo = linha;
            hash.pesquisar(titulo);
            linha = sc.nextLine();
        }
        long tempoFinal = System.currentTimeMillis();
        long tempoTotal = tempoFinal - tempoInicial;
        comparacoes = tabela.length; // Considerando que cada pesquisa conta como uma comparação
        escreverLog("865235_hashIndireta.txt", comparacoes, tempoTotal);
        sc.close();
    }
    */
//endregion

    //--------------------------------------------------------ARQUIVO LOG--------------------------------------------------------
    public static void escreverLog(String nomeArquivo, long comparacoes, long tempo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            writer.write("865235\t" + comparacoes + "\t" + tempo + "\n");
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
