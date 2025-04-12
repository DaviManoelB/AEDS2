
import java.io.*;
import java.text.*;
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
    //--------------------------------------------------------CONTRUTOR COM PARAMETROS--------------------------------------------------------
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

    //--------------------------------------------------------GETTERS--------------------------------------------------------
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

    //--------------------------------------------------------SETTERS--------------------------------------------------------
    
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
    


    //--------------------------------------------------------CLONE--------------------------------------------------------
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

    //--------------------------------------------------------IMPRIMIR--------------------------------------------------------
    public void imprimir(Show tabela){
        System.out.println("=> " + tabela.show_id + " ## "+ tabela.title + " ## " + tabela.type + " ## " + tabela.director + " ##  ["+ tabela.cast +
         "] ## "+ tabela.country + " ## " + tabela.date_added + " ## " + tabela.release_year + " ## " + tabela.rating + " ## " + tabela.duration + " ## [" + tabela.listed_in + "] ##");
    }


    //--------------------------------------------------------ORDENAR--------------------------------------------------------
    public void ordenarListas() {
        Arrays.sort(cast);
        Arrays.sort(listed_in);
    }

    // CONVERTER String PARA Date
    private Date converterParaDate(String dataStr) {
        if (dataStr.equals("NaN")) return null;
        try {
            SimpleDateFormat formato = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
            return formato.parse(dataStr);
        } catch (ParseException e) {
            return null;
        }
    }


    //--------------------------------------------------------Ler-------------------------------------------------------
    public static Show[] ler(String[] args) throws Exception{
        int cont = 1; //contador para o vetor tabela
        Show[] tabela = new Show[1370]; //cria um vetor para cadastrar no sistema todos os shows
        String[] resp = new String[12]; //cria array para cada categoria do show
        String filme = new String (); //cria String para ler cada linha do arquivo csv

        //Scanner sc = new Scanner(new File("./tmp/disneyplus.csv"));
        Scanner sc = new Scanner(new File("../disneyplus.csv"));
        String cabecalho = sc.nextLine(); //pega o cabecalho


        while ((sc.hasNext())) {
            filme = sc.nextLine(); //le a linha de disneyplus.csv
            int cFilme = 0, cResp = 0; //cFilme controla o char da frase do arquivo csv e cResp controla o char na string a ser cadastrada
            for(int i = 0; i < resp.length; i++){
                resp[i] = ""; //inicializa as string resp para vazio
            }

            while(cResp < 11){
                tabela[cont] = new Show(); //cria um novo objeto do tipo Show
                char ch = filme.charAt(cFilme);
                if(ch == '"'){ //se for uma aspas ignora a virgula no meio e le ate a proxima aspas
                    cFilme++;
                    while(filme.charAt(cFilme) != '"'){
                        ch = filme.charAt(cFilme);
                        resp[cResp] += ch;
                        cFilme++;
                    }
                    cFilme+=2; cResp++;
                    
                }else if(ch == ','){ // se for uma virgula que aparece dps de outra virgula, cadastra NaN em resp
                    resp[cResp] = "NaN";
                    cFilme++; cResp++; 
                }else{
                    while(filme.charAt(cFilme) != ','){ //le enquanto nao acha uma virgula. Quando acha, já pula pro prox char. Por isso o elseIf de cima verifica se foi virgula dupla
                        ch = filme.charAt(cFilme);
                        resp[cResp] += ch;
                        cFilme++;
                    }
                    cFilme++; cResp++; 
                }           
            } 
            //-----------atribui o valor do vetor resp na tabela-------------
            tabela[cont].show_id = resp[0]; 
            tabela[cont].type = resp[1];
            tabela[cont].title = resp[2];
            tabela[cont].director = resp[3];
            tabela[cont].cast = resp[4].split( ","); //separa os atores por virgula
            tabela[cont].country = resp[5]; 
            tabela[cont].date_added = resp[6].equals("Nan")? tabela[cont].date_added = "Nan" : (resp[6]); //converte a data para o formato correto
            tabela[cont].release_year = (resp[7].equals("Nan") || resp[7].isEmpty()) ? 0 : Integer.parseInt(resp[7]); //transofrma a string em inteiro
            tabela[cont].rating = resp[8];
            tabela[cont].duration = resp[9];
            tabela[cont].listed_in = resp[10].equals("Nan")? null : resp[10].split(","); //se for NaN, atribui NaN, senao separa por virgula
            cont++; //adiciona +1 em cont         
        }
        sc.close(); //fecha o scanner
        return tabela;
    }


    public static void main(String[] args) {
        Show[] tabela = new Show[1370]; //cria um vetor para cadastrar no sistema todos os shows
        try {
            tabela = ler(args);
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
                if(id.equals(tabela[i].show_id)){ //se o id for igual ao id do vetor, imprime
                    tabela[i].imprimir(tabela[i]);
                }
            }
        }
        sc.close(); 
    }
}