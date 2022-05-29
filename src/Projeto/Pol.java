package Projeto;


import java.util.List;

public class Pol {

  private int id_pol;

  public String nome_pol;

  public String descricao;


  public Pol(int id_pol, String nome_pol, String descricao) {

    this.id_pol = id_pol;
    this.nome_pol = nome_pol;
    this.descricao = descricao;
  }

  @Override
  public String toString() {
    return "Pol{" +
            "id_pol=" + id_pol +
            ", nome_pol='" + nome_pol + '\'' +
            ", descricao='" + descricao + '\'' +
            '}';
  }

  /**
   * Adicionar um Ponto de Interesse a Base de Dados
   * @param new_pol
   */

  public static void add_Pol(Pol new_pol) {
    if (BD.Pol_ST.contains(new_pol.getId_pol())){
      System.out.println("Ja existe");
      return;
    }
    BD.Pol_ST.put(new_pol.getId_pol(), new_pol);
  }

  /**
   * Remover Pold a Base de Dados
   * @param pol_id
   */
  public static void remover_pol(int  pol_id) {
    if (pesquisar_Poi(pol_id)!=0) {
      Pol p = BD.Pol_ST.get(pol_id);
      BD.Pol_ST.delete(p.getId_pol());
      System.out.println("Poi removido");
    }
  }

  /**
   * Pesquisar por um determinado Poi
   * @param id id do Pol
   * @return retorna o id e a info do Pol procurado
   */
  public static int pesquisar_Poi(Integer id) {
    if (BD.Pol_ST.contains(id)) {
      Pol p = BD.Pol_ST.get(id);
      //System.out.println("\n");
      System.out.println(p.toString());
      return id;
    } else
      System.out.println("Erro: Poi nao encontrada");
    return 0;
  }

  public static int getNextId() {
    int last_id = 0;
    for (Integer id : BD.Pol_ST.keys()) {
      Pol pol =  BD.Pol_ST.get(id);
      if (pol.id_pol > last_id)
        last_id = pol.id_pol;
    }
    return last_id + 1;
  }

  public void listar_user_pol() {
  }

  public void listar_polNaoVisitado_user() {
  }

  public void listar_top5User_visitarPol() {
  }

  public void listar_top5Pol(Date d) {
  }

  public int getId_pol() {
    return id_pol;
  }

  public void setId_pol(int id_pol) {
    this.id_pol = id_pol;
  }

  public String getNome_pol() {
    return nome_pol;
  }

  public void setNome_pol(String nome_pol) {
    this.nome_pol = nome_pol;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }


}