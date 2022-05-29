package Projeto;



public class Basic extends User {
  /**
   * Construtor da classe Basic
   * @param user_id
   * @param user_nome
   * @param user_idade
   * @param user_adress
   */
  public Basic(int user_id, String user_nome, int user_idade, String user_adress) {
    super(user_id, user_nome, user_idade, user_adress);
  }

  @Override
  public String toString() {
    return "Basic{" +
            "id= " + this.getUser_id()+
            ", nome= '" + this.getUser_nome() + '\'' +
            ", idade= " + this.getUser_idade()+
            ", adress= '" + this.getUser_adress() + '\'' +
            '}';
  }

  /**
   * Função que lista a informação de um certo User
   */

  public void listar_user() {
    for (String name : BD.user_ST.keys()){
      User user = BD.user_ST.get(name);
      System.out.println(user.toString());
    }
    System.out.println("\n");
  }

}