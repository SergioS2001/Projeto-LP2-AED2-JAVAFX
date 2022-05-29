package Projeto;


import edu.princeton.cs.algs4.*;

public class Ways extends EdgeWeightedDigraph {
  private int id_tag;

  public Ways(EdgeWeightedDigraph G, int id_tag) {
    super(G);
    this.id_tag = id_tag;
  }


  @Override
  public String toString() {
    return "Ways{" +
            ", id_tag='" + this.getId_tag() + '\'' +
            '}';
  }
  /*
  public static void add_Ways(Ways new_way) {
    if (BD.ways_ST.contains(new_way.getId_via())){
      System.out.println("Rua ja existe");
      return;
    }
    BD.ways_ST.put(new_way.getId_via(), new_way);
  }
  public static void remover_Way(int  id) {
    if (pesquisar_Way(id)!=0) {
      Ways w = BD.ways_ST.get(id);
      BD.ways_ST.delete(w.getId_via());
      System.out.println("Rua removida");
    }
  }
   */

    /**
     * Pesquisar por uma especifica Way
     * @param id
     * @return
     */
  public static int pesquisar_Way(Integer id) {
    if (BD.ways_ST.contains(id)) {
      Ways w = BD.ways_ST.get(id);
      System.out.println("\n");
      System.out.println(w.toString());
      System.out.println("\n");
      return id;
    } else
      System.out.println("Erro: Rua nao encontrada");
    return 0;
  }


  public int getId_tag() {
    return id_tag;
  }
  public void setId_tag(int id_tag) {
    this.id_tag = id_tag;
  }


  public void listar_nodes() {
  }

  public void listar_tags() {
  }

  public void listar_pols() {
  }

  public void listar_caract_via() {
  }

}
