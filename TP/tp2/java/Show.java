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
    public Show(){
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
        this.show_id = show_id;
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
    public String getShowId(){return show_id;}
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
    public void ler(String linha) {
        try {
            // Divide por vírgula, mas mantendo vírgulas internas em aspas
            List<String> campos = new ArrayList<>();
            boolean aspas = false;
            StringBuilder campoAtual = new StringBuilder();
            for (char c : linha.toCharArray()) {
                if (c == '\"') aspas = !aspas;
                else if (c == ',' && !aspas) {
                    campos.add(campoAtual.toString().trim());
                    campoAtual.setLength(0);
                } else {
                    campoAtual.append(c);
                }
            }
            campos.add(campoAtual.toString().trim());

            this.show_id = getOuNaN(campos, 0);
            this.type = getOuNaN(campos, 1);
            this.title = getOuNaN(campos, 2);
            this.director = getOuNaN(campos, 3);

            String castStr = getOuNaN(campos, 4);
            this.cast = castStr.equals("NaN") ? new String[0] : castStr.split(",\\s*");

            this.country = getOuNaN(campos, 5);
            this.date_added = converterParaDate(getOuNaN(campos, 6));

            String ano = getOuNaN(campos, 7);
            this.release_year = ano.equals("NaN") ? -1 : Integer.parseInt(ano);

            this.rating = getOuNaN(campos, 8);
            this.duration = getOuNaN(campos, 9);

            String listedInStr = getOuNaN(campos, 10);
            this.listed_in = listedInStr.equals("NaN") ? new String[0] : listedInStr.split(",\\s*");

            ordenarListas();
        } catch (Exception e) {
            System.out.println("Erro ao ler linha: " + linha);
            e.printStackTrace();
        }
    }

    private String getOuNaN(List<String> campos, int index) {
        if (index >= campos.size() || campos.get(index).trim().isEmpty()) return "NaN";
        return campos.get(index).replace("\"", "").trim();
    }


public static void main(String[] args) {
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
}
}