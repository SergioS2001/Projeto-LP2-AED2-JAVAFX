package Projeto;



public class UserAdmin extends Basic {
    public UserAdmin(int user_id, String user_nome, int user_idade, String user_adress) {
        super(user_id, user_nome, user_idade, user_adress);
    }


    @Override
    public String toString() {
        return "UserAdmin{" +
                "id= " + this.getUser_id()+
                ", nome= '" + this.getUser_nome() + '\'' +
                ", idade= " + this.getUser_idade()+
                ", adress= '" + this.getUser_adress() + '\'' +
                '}';
    }

    public static void main(String[] args) {

    }

 /* public void inserir_user_admin(UserAdmin new_user) {
    if (user_ST.contains(new_user.getUser_nome())){
      System.out.println("Ja existe");
      return;
    }
    user_ST.put(new_user.getName(), new_user);
  }*/

  /*public void editar_user(User user) {
    In input = new In();
    if (user_ST.contains(user.getName())){
      System.out.println("Info sobre este o user "+ user.getName());
      System.out.println("Nome: "+user.getName()+"\n"+"Idade: "+user.getUser_idade()+"\n"+"Tipo: "+user.getTipo()+"\n"+"Id: "+user.getUser_id()+"\n"+"Address: "+user.getAddress()+"\n");

      System.out.println("Qual o novo nome ");
      String novo_nome = input.readString();
      user.setUser_nome(novo_nome);

      System.out.println("Qual a nova idade ");
      int nova_idade = input.readInt();
      user.setUser_idade(nova_idade);

      System.out.println("Qual o novo id ");
      int novo_id = input.readInt();
      user.setUser_id(novo_id);

      System.out.println("Qual o tipo de user ");
      String novo_tipo = input.readString();
      user.setTipo(novo_tipo);

      System.out.println("Qual o novo address ");
      String novo_add = input.readString();
      user.setAddress(novo_add);
    }
  }
/*
  public void remover_user(User user) {
    if (User_ST.contains(user.getName())){
      User_ST.delete(user.getName());
      System.out.println("User removido");
      return;
    }
    System.out.println("User nao encontrado");
    }
    */

    /**
     * Remover um User da base de dados
     * @param name
     * @return
     */
 public static User remover_user(String name){
   User u = BD.user_ST.get(name);
   if (u == null){
     System.out.println("Nao existe");
     return null;
   }
   BD.user_ST.delete(name);
   return u;
 }




}