
import java.io.*;
import java.text.*;
import java.util.*;

class Show{
    //ATRIBUTOS
    private String show_id;
    private String type;
    private String title;
    private String director;
    private String[] cast;
    private String country;
    private Date date_added;
    private int release_year;
    private String rating;
    private String duration;
    private String[] listed_in;

    //CONTRUTOR VAZIO
    public Show(){ //incializa os atributos
        this.show_id = "";
        this.type = "";
        this.title = "";
        this.director = "";
        this.cast = new String[0];
        this.country = "";
        this.date_added = new Date();
        this.release_year = 0;
        this.rating = "";
        this.duration = "";
        this.listed_in = new String[0];
    }
    //CONTRUTOR COM PARAMETROS
    public Show(String show_id, String type, String title, String director, String[] cast, String country, Date date_added, int release_year, String rating, String duration, String[] listed_in){
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

    //GETTERS
    public String getShowId(){return show_id;} //serve para manipluar os atributos. Como estao como private, precisa de get e set para acessar
    public String gettype(){return type;}
    public String gettitle(){return title;}
    public String getdirector(){return director;}
    public String[] getcast(){return cast;}
    public String getcountry(){return country;}
    public Date getDataAdded(){return date_added;}
    public int getReleaseYear(){return release_year;}
    public String getrating(){return rating;}
    public String getduration(){return duration;}
    public String[] getListedIn(){return listed_in;}

    //SETTERS
    
    public void setshow_id(String show_id) { this.show_id = show_id; }
    public void settype(String type) { this.type = type; }
    public void settitle(String title) { this.title = title; }
    public void setdirector(String director) { this.director = director; }
    public void setcast(String[] cast) { this.cast = cast; }
    public void setcountry(String country) { this.country = country; }
    public void setdate_added(Date date_added) { this.date_added = date_added; }
    public void setrelease_year(int release_year) { this.release_year = release_year; }
    public void setrating(String rating) { this.rating = rating; }
    public void setduration(String duration) { this.duration = duration; }
    public void setlisted_in(String[] listed_in) { this.listed_in = listed_in; }
    


    //CLONE
    @Override
    public Show clone(){
        Show clone = new Show();
        clone.show_id = this.show_id;
        clone.type = this.type;
        clone.title = this.title;
        clone.director = this.director;
        clone.cast = this.cast.clone();
        clone.country = this.country;
        clone.date_added = (Date) this.date_added.clone();
        clone.release_year = this.release_year;
        clone.rating = this.rating;
        clone.duration = this.duration;
        clone.listed_in = this.listed_in.clone();
        return clone;
    }

    //IMPRIMIR
    public void imprimir(){
        System.out.println("[=> " + show_id + "##" + type + "##" + title + "##" + director + "##" + Arrays.toString(cast) + "##" + country + "##" + date_added + "##" + release_year + "##" + rating + "##" + duration + "##" + Arrays.toString(listed_in) + "]");
    }


    //ORDENAR
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


    //Ler
    public static void ler(String[] args) throws Exception{
        
        String[] resp = new String[12];
        String filme = new String ();
        //Scanner sc = new Scanner(new File("./tmp/disneyplus.csv"));
        Scanner sc = new Scanner(new File("../disneyplus.csv"));
        String cabecalho = sc.nextLine(); //pega o cabecalho
        while ((sc.hasNext())) {
            filme = sc.nextLine();
            int cFilme = 0, cResp = 0;
            for(int i = 0; i < resp.length; i++){
                resp[i] = ""; //inicializa as string resp para vazio
            }

            while(cResp < 11){
                char ch = filme.charAt(cFilme);
                if(ch == '"'){ //se for uma aspas ignora a virgula no meio e le ate a proxima aspas
                    cFilme++;
                    while(filme.charAt(cFilme) != '"'){
                        ch = filme.charAt(cFilme);
                        resp[cResp] += ch;
                        cFilme++;
                        
                    }
                    cFilme++; cResp++;
                    
                }else if(ch == ','){ // se for uma virgula que aparece dps de outra virgula, cadastra NaN em resp
                    resp[cResp] = "NaN";
                    cResp++; cFilme++;
                }else{
                    while(filme.charAt(cFilme) != ','){ //le enquanto nao acha uma virgula. Quando acha, já pula pro prox char. Por isso o elseIf de cima verifica se foi virgula dupla
                        ch = filme.charAt(cFilme);
                        resp[cResp] += ch;
                        cFilme++;
                    }
                }   
            }          
        }
        System.out.println(resp[1]);
    }


    public static void main(String[] args) {
        /*
        Scanner sc = new Scanner(System.in);
        List<String> idsEntrada = new ArrayList<>();

        while (true) {
            String entrada = sc.nextLine();
            if (entrada.equals("FIM")) break;
            idsEntrada.add(entrada);
        }

        Map<String, String> mapaCSV = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("/tmp/disneyplus.csv"));
            String linha;
            br.readLine(); // pula cabeçalho
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",", 2);
                if (partes.length > 1) {
                    mapaCSV.put(partes[0].trim(), linha);
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            sc.close();
            return;
        }

        for (String id : idsEntrada) {
            if (mapaCSV.containsKey(id)) {
                Show s = new Show();
                s.ler(mapaCSV.get(id));
                s.imprimir();
            } else {
                System.out.println("ID " + id + " não encontrado.");
            }
        }

        sc.close();
        */
        // Teste do método ler
        try {
            ler(args);
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }  
    }
}