package Projeto;


import edu.princeton.cs.algs4.In;

public class Historico extends Date {
    private int historico_id;
  public Date date;
  private String user_name;
  private int pol_id;

    public Historico(int historico_id, int day, int mounth, int year, int hour, int min, String user_name, int pol_id) {
        super(day, mounth, year, hour, min);
        this.historico_id = historico_id;
        this.user_name = user_name;
        this.pol_id = pol_id;
    }

    public int getHistorico_id() {
        return historico_id;
    }
    public void setHistorico_id(int historico_id) {
        this.historico_id = historico_id;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public int getPol_id() {
        return pol_id;
    }
    public void setPol_id(int pol_id) {
        this.pol_id = pol_id;
    }
    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    /**
     * Adiciona um historico a um determinado utilizador
     * @param historico Informação relativa a um User
     */

    public static void addHistorico(Historico historico){
        BD.historico_st.put(historico.getUser_name(), historico);
    }

    public static int getNextId() {
        int last_id = 0;
        for (String id : BD.historico_st.keys()) {
            Historico historico = BD.historico_st.get(id);
            if (historico.historico_id > last_id)
                last_id = historico.historico_id;
        }
        return last_id + 1;
    }

    @Override
    public String toString() {
        return "Historico{" +
                " historico_id=" + getHistorico_id() +
                "data = " + day +
                "/ " + mounth +
                "/ " + year +
                ", hour=" + hour +
                ": " + min +
                ", user_name='" + getUser_name() + '\'' +
                ", pol_id=" + getPol_id() +
                '}';
    }

    /**
     * Imprime os Ponto de Interesse Visitados por um User num determinado periodo
     * @param inicio Data inicial
     * @param fim   Data final
     * @param user_name user a pesquisar
     */

    public static void PrintPolVisitado (Date inicio, Date fim, String user_name){

      // ciclo while que tire a data atual e compare com a data passado
        // dentro desse ciclo dar print a tudo o que fique no Contains do user_name

            Historico h = BD.historico_st.get(user_name);

        if (h == null) {
            System.out.println("Erro: utilizador nao existe");
        }
        else if (
        h.getYear() < fim.year && h.getYear() > inicio.year
                && h.getMonth() < fim.mounth && h.getMonth() > inicio.mounth
                 && h.getDay() < fim.day && h.getDay() > inicio.day
                && h.getHour() < fim.hour && h.getHour() > inicio.hour
        && h.getMin() < fim.min && h.getMin() > inicio.min){
            System.out.println("teste dentro do loop");
            System.out.println(Pol.pesquisar_Poi(h.pol_id));
        }
            }

    public static void main(String[] args) {
        Date inicio = new Date(13,12,2022,11,23);
        Date fim = new Date(16,12,2024,12,23);

        PrintPolVisitado(inicio, fim, "A");
    }
    }

