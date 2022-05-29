package Projeto;




import java.util.List;

public class Nodes extends Point {



  private int id_node;

  private String tipo_de_node;

  private int tag_id;

  private int PoI_id;



  public Nodes(float x, float y, int id_node, String tipo_de_node, int tag_id, int poI_id) {
    super(x, y);
    this.id_node = id_node;
    this.tipo_de_node = tipo_de_node;
    this.tag_id = tag_id;
    PoI_id = poI_id;
  }

  @Override
  public String toString() {
    return "Nodes{" +
            "id_node=" + this.getId_node() +
            ", (x,"+this.getX()+
            "y)"+this.getY()+
            ", tipo_de_node='" + this.getTipo_de_node() +
            ", poI_id=" + this.getPoI_id() +
            ", tag_id=" + this.getTag_id() +'\'' +
            '}';
  }


  /**
   * Adicionar um Node para a base de dados
   * @param new_node
   */
  public static void add_node(Nodes new_node) {

    if (BD.nodesST.contains(new_node.getId_node())){
      System.out.println("Ja existe");
      return;
    }
    BD.nodesST.put(new_node.getId_node(), new_node);
  }

  /**
   * Remove um Node por completo de todas as bases de dados
   * @param id id do Node
   */

  public static void remover_node(int  id) {
    if (pesquisar_node(id)!=0){
      Nodes n= BD.nodesST.get(id);
      BD.nodesST.delete(n.getId_node());
      System.out.println("Node removido");
    }
  }

  /**
   * pesquisar por um determinado Node
   * @param id id do node
   * @return retorna o id do Node
   */
  public static int pesquisar_node(Integer id) {
    if (BD.nodesST.contains(id)) {
      Nodes n = BD.nodesST.get(id);
      //System.out.println("\n");
      System.out.println(n.toString());
      return id;
    } else
      System.out.println("Erro: Tag nao encontrada");
    return 0;
  }


  public static int getNextId() {
    int last_id = 0;
    for (Integer id : BD.nodesST.keys()) {
      Nodes node = BD.nodesST.get(id);
      if (node.id_node > last_id)
        last_id = node.id_node;
    }
    return last_id + 1;
  }

  /**
   * Listar Nodes associados a um User
   * @param u
   */
  public void listar_node_user(User u) {
  }


  public int getId_node() {
    return id_node;
  }

  public void setId_node(int id_node) {
    this.id_node = id_node;
  }

  public String getTipo_de_node() {
    return tipo_de_node;
  }

  public void setTipo_de_node(String tipo_de_node) {
    this.tipo_de_node = tipo_de_node;
  }

  public int getTag_id() {
    return tag_id;
  }

  public void setTag_id(int tag_id) {
    this.tag_id = tag_id;
  }

  public int getPoI_id() {
    return PoI_id;
  }

  public void setPoI_id(int poI_id) {
    PoI_id = poI_id;
  }
}

