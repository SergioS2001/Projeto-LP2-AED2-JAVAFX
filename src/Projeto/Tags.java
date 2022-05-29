package Projeto;

public class Tags{

  private int tag_id;
  private String tag_descricao;

  public Tags(int tag_id, String tag_descricao) {
    this.tag_id = tag_id;
    this.tag_descricao = tag_descricao;
  }

  @Override
  public String toString() {
    return "Tags{" +
            "tag_id=" + tag_id +
            ", tag_descricao='" + tag_descricao + '\'' +
            '}';
  }

  /**
   * Adicionar Tags a base de dados
   * @param new_tag
   */
  public static void add_Tags(Tags new_tag) {
    if (BD.tags_ST.contains(new_tag.getTag_id())){
      System.out.println("Ja existe");
      return;
    }
    BD.tags_ST.put(new_tag.getTag_id(), new_tag);
  }

  /**
   * Remover Tags da base de dados
   * @param tag_id
   */
  public static void remover_tag(int  tag_id) {
    if (pesquisar_Tag(tag_id)!=0) {
      Tags t = BD.tags_ST.get(tag_id);
      BD.tags_ST.delete(t.getTag_id());
      System.out.println("Tag removida");
    }
  }

  /**
   * Pesquisar uma tag na base de dados
   * @param id
   * @return
   */
  public static int pesquisar_Tag(Integer id) {
    if (BD.tags_ST.contains(id)) {
      Tags t = BD.tags_ST.get(id);
      //System.out.println("\n");
      System.out.println(t.toString());
      return id;
    } else
      System.out.println("Erro: Tag nao encontrada");
    return 0;
  }
  public static int getNextId() {
    int last_id = 0;
    for (Integer id : BD.tags_ST.keys()) {
      Tags tag =  BD.tags_ST.get(id);
      if (tag.tag_id > last_id)
        last_id = tag.tag_id;
    }
    return last_id + 1;
  }

  public int getTag_id() {
    return tag_id;
  }

  public void setTag_id(int tag_id) {
    this.tag_id = tag_id;
  }

  public String getTag_descricao() {
    return tag_descricao;
  }

  public void setTag_descricao(String tag_descricao) {
    this.tag_descricao = tag_descricao;
  }
 /*
  public static void insertTag(String tag){

    BD.map.put(getNextId(), tag);

  }
  public static int getNextId() {
    int last_id = 0;

    return last_id + 1;
  }*/

  public void listar_tags_ways() {
  }

  public void listar_pol_tags() {
  }



}