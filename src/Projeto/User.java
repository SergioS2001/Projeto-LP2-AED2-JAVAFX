package Projeto;


import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.RedBlackBST;

import java.util.Objects;

public class User extends Person {



  private int user_id;
  private String user_nome;
  private int user_idade;
  private String user_adress;


  public User(int user_id, String user_nome, int user_idade, String user_adress) {
    this.user_id = user_id;
    this.user_nome = user_nome;
    this.user_idade = user_idade;
    this.user_adress = user_adress;
  }

  @Override
  public String toString() {
    return "User{" +
            "id= " + this.getUser_id()+
            ", nome= '" + this.getUser_nome() + '\'' +
            ", idade= " + this.getUser_idade()+
            ", adress= '" + this.getUser_adress() + '\'' +
            '}';
  }
  /* @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    User user = (User) o;
    return user_id == user.user_id && user_idade == user.user_idade && Objects.equals(user_ST, user.user_ST) && Objects.equals(user_nome, user.user_nome);
  }*/

  /**
   * Adicionar um User a base de Dados
   * @param new_user
   */

  public static void add_User(User new_user) {
    if (BD.user_ST.contains(new_user.getUser_nome())){
      System.out.println("Ja existe");
      return;
    }
    BD.user_ST.put(new_user.getUser_nome(), new_user);

  }

  /**
   * Remover um user da base de dados
   * @param user
   */
  public static void remover_user(User user) {
    if (BD.user_ST.contains(user.getUser_nome())){
      BD.user_ST.delete(user.getUser_nome());
      System.out.println("User removido");
      return;
    }
    System.out.println("User nao encontrado");
  }

  /**
   * Pesquisar um user
   * @param nome
   */
  public static void pesquisar_user(String nome) {
    if (BD.user_ST.contains(nome)) {
      User u = BD.user_ST.get(nome);
      System.out.println(u.toString());
    } else
      System.out.println("Erro: Utilizador nao encontrado");
  }
  public static int getNextId() {
    int last_id = 0;
    for (String nome : BD.user_ST.keys()) {
      User user = BD.user_ST.get(nome);
      if (user.user_id > last_id)
        last_id = user.user_id;

    }
    return last_id + 1;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), BD.user_ST, user_nome, user_id, user_idade);
  }

  /*public RedBlackBST<String, User> getUser_ST() {
    return BD.user_ST;
  }
  public void setUser_ST(RedBlackBST<String, User> user_ST) {
    User.user_ST = user_ST;
  }*/


  public String getUser_nome() {
    return user_nome;
  }
  public void setUser_nome(String user_nome) {
    this.user_nome = user_nome;
  }
  public int getUser_id() {
    return user_id;
  }
  public void setUser_id(int user_id) {
    this.user_id = user_id;
  }
  public int getUser_idade() {
    return user_idade;
  }
  public void setUser_idade(int user_idade) {
    this.user_idade = user_idade;
  }

  public String getUser_adress() {
    return user_adress;
  }

  public void setUser_adress(String user_adress) {
    this.user_adress = user_adress;
  }


}