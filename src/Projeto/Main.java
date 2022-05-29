package Projeto;


import edu.princeton.cs.algs4.*;

import javax.swing.text.TableView;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;  // Import the IOException class to handle errors



public class Main {

    private static final String FILE_DELIMETER = ";";


    public static void main(String[] args) {

        //User userAdmin = new User(1,"Marcos",24,"Rua Henrique de Sousa Reis");
           /*User.add_User(new UserAdmin(0, "Marcos", 24, "Rua Henrique de Sousa Reis"));
            User.add_User(new UserAdmin(1, "Sergio", 24, "Rua Henrique de Sousa Reis"));
            User.add_User(new Basic(2, "Cesar", 24, "Rua Henrique de Sousa Reis"));*/

        menu();
    }

    /**
     * Menu principal para interajir com as diferentes categorias do projeto
     * Consiste num sistema de switch case, dependendo da escolha de input (0-6)
     */
    private static void menu() {
        int opcao;
        do {
            System.out.println("== MENU ==");
            System.out.println("1- Login(username)");
            System.out.println("2- listar Utilizadores");
            System.out.println("4- Read BD from txt");
            System.out.println("5- Write txt to BD ");
            System.out.println("6- Grafos ");
            System.out.println("0- Sair");
            System.out.println();
            Scanner in = new Scanner(System.in);
            System.out.print("Escolha opcao: ");
            opcao = Integer.parseInt(in.nextLine());
            switch (opcao) {
                case 1:
                    menu_utilizadores(in);
                    break;
                case 2:
                    printAllUsers();
                    printAllTags();
                    break;
                case 4:
                    readFiles();
                    break;
                case 5:
                    writeFiles();
                    break;
                case 6:
                    //MST1();
                    menu_grafos(in);
                    break;
                case 0:
                    menu();
                    break;
                default:
                    System.out.println("Opcao " + opcao + " invalida");
            }
        } while (opcao != 0);
    }

    /**
     * Menu de gestao de utilizadores, com funcionalidade de inserir, remover, editar, pesquisar sobre as diferentes classes
     * Divide-se em 2 partes
     * Utilizador Basic, com capacidade de visualizar informação
     * Utilizador Admnistrador, com capacidade de manipular a informação
     * @param in, input do user relativo a escolha que pretende fazer
     */

    private static void menu_utilizadores(Scanner in) {

        String username;

        System.out.println("Qual o nome do utilizador");
        username = in.nextLine();
        User user = BD.user_ST.get(username);

        if (user == null) {
            System.out.println("Erro: utilizador nao existe");
        } else {
            System.out.println(username);
            /** se o utilizador for ADMIN pode fazer : **/
            if (user instanceof UserAdmin) {
                int opcao;
                do {
                    System.out.println("== MENU ADMINISTRADOR ==");
                    System.out.println("1- Menu Gestao Utilizadores");
                    System.out.println("2- Menu Nodes");
                    System.out.println("3- Menu Tags");
                    System.out.println("4- Menu Pols");
                    System.out.println("5- Menu Historico");
                    System.out.println("0- Sair");
                    System.out.println();
                    System.out.print("Escolha opcao: ");
                    opcao = Integer.parseInt(in.nextLine());
                    switch (opcao) {
                        case 1: {
                            menu_administrador(in);
                            break;
                        }
                        case 2: {
                            menu_nodes(in);
                        }
                        case 3: {
                            menu_tags(in);
                        }
                        case 4: {
                            menu_pols(in);
                        }
                        case 5: {
                            menu_historico(in);
                        }
                        case 0:
                            menu();
                    }
                } while (opcao != 0);
                /** se for um utilizador Basic pode fazer **/
            } else {
                int opcao;
                do {
                    System.out.println("== MENU UTILIZADORES BASIC ==");
                    System.out.println("1- Listar nodes");
                    System.out.println("2- Listar ways");
                    System.out.println("3- Listar PoI");
                    System.out.println("4- Listar Historico");
                    System.out.println("0- Sair");
                    System.out.println();
                    System.out.print("Escolha opcao: ");
                    opcao = Integer.parseInt(in.nextLine());
                    switch (opcao) {
                        case 1: {
                            printAllNodes();
                            break;
                        }
                        case 2: {
                            printAllWays();
                            break;
                        }
                        case 3: {
                            printAllPoi();
                        }
                        case 4: {
                            Date inicio = new Date(13, 12, 2022, 11, 23);
                            Date fim = new Date(16, 12, 2024, 12, 23);
                            Historico.PrintPolVisitado(inicio, fim, "C");
                            // printAllPolsByUser(username);
                        }
                    }
                } while (opcao != 0);
            }
        }
    }

    /**
     * Menu de gestao de utilizadores, com funcionalidade de inserir, remover, editar, pesquisar e adicionar um historico
     * @param in, input do user relativo a escolha que pretende fazer
     */

    private static void menu_administrador(Scanner in) {
        int opcao;
        do {
            System.out.println("== Menu Gestao Utilizadores ==");
            System.out.println("1- Adicionar utilizador");
            System.out.println("2- Remover utilizador");
            System.out.println("3- Editar utilizador");
            System.out.println("4- Listar utilizadores");
            System.out.println("5- Pesquisar utilizadores");
            System.out.println("6- Listar utilizadores Basic");
            System.out.println("7- Listar utilizadores Admin");
            System.out.println("8- Adicionar Historico");
            System.out.println("0- Sair");
            System.out.println();
            System.out.print("Escolha opcao: ");
            opcao = Integer.parseInt(in.nextLine());
            Scanner user = new Scanner(System.in);
            switch (opcao) {

                case 1: {

                    // adicionar utilizadores
                    System.out.print("Qual o nome do utilizador a adicinar: ");
                    String nome = user.nextLine();

                    if (BD.user_ST.contains(nome)) {
                        System.out.println("Erro: Nome de utilizador ja existente");
                        continue;
                    }

                    String tipo;
                    do {
                        System.out.print("Qual o tipo do utilizador: ");
                        tipo = user.nextLine();
                        if (!tipo.equals("Basic") && !tipo.equals("Admin"))
                            System.out.println("Erro: tipo tem que ser: Basic, Admin");

                    } while (!tipo.equals("Basic") && !tipo.equals("Admin"));

                    int idade;
                    do {
                        System.out.print("Qual a idade do utilizador: ");
                        idade = Integer.parseInt(user.nextLine());
                        if (idade < 18)
                            System.out.println("Tem que ser maior de idade");
                    } while (idade < 18);
                    String adress;
                    System.out.println("Qual a morada do utilizador");
                    adress = user.nextLine();

                    User u = null;

                    if (tipo.equals("Basic"))
                        u = new Basic(User.getNextId(), nome, idade, adress);
                    else if (tipo.equals("Admin"))
                        u = new UserAdmin(User.getNextId(), nome, idade, adress);

                    User.add_User(u);

                    write_User_AccionTo_TXT("C:\\Users\\sergi\\Desktop\\Projeto-LP2-AED2-JAVAFX\\data\\accion2.txt", u, "Adicionar User ");
                    break;
                }
                case 2:  // remover utilizadores
                {
                    System.out.print("Qual o nome do utilizador a remover: ");
                    String nome = user.nextLine();

                    if (BD.user_ST.contains(nome)) {
                        //colocar o utilizador a remover na ST de UserLog como se tarta-se de um historico
                        User u = BD.user_ST.get(nome);
                        User.remover_user(u);
                        write_User_AccionTo_TXT("C:\\Users\\sergi\\Desktop\\Projeto-LP2-AED2-JAVAFX\\data\\accion2.txt", u, "Remover User ");

                    } else
                        System.out.println("Erro: Utilizador nao encontrado");


                    break;
                }
                case 3: {  // editar utilizadores
                    System.out.print("Qual o utilizador: ");
                    String nome = user.nextLine();

                    // procurar utilizador
                    User user1 = BD.user_ST.get(nome);
                    if (user1 == null) {
                        System.out.println("Erro: utilizador nao existe");
                        continue;
                    }
                    /** PRINT USER INFO **/
                    System.out.print("Qual o novo nome: ");
                    String nome2 = user.nextLine();

                    int idade;
                    do {
                        System.out.print("Qual a nova idade do utilizador: ");
                        idade = Integer.parseInt(user.nextLine());
                        if (idade < 18)
                            System.out.println("Tem que ser maior de idade");
                    } while (idade < 18);

                    System.out.println("Qual a nova morada: ");
                    String adress2 = user.nextLine();

                    user1.setUser_nome(nome2);
                    user1.setUser_idade(idade);
                    user1.setUser_adress(adress2);
                    break;
                }
                case 4: {  // listar utilizadores
                    printAllUsers();
                    break;
                }
                case 5: {//pesquisar utilizadores
                    System.out.print("Qual o nome do utilizador que pretende pesquisar: ");
                    String nome = user.nextLine();

                    User.pesquisar_user(nome);

                    break;
                }
                case 6:
                    printAllBasic();
                    break;
                case 7:
                    printAllAdmin();
                    break;
                case 8: {
                    // adicionar utilizadores historico
                    System.out.print("Qual o nome do utilizador a adicinar: ");
                    String nome1 = user.nextLine();

                    User user1 = BD.user_ST.get(nome1);
                    if (user1 == null) {
                        System.out.println("Erro: utilizador nao existe");
                        continue;
                    } else {
                        System.out.println("Insira Novamente: ");
                        nome1 = user.nextLine();
                    }
                    int id;
                    System.out.print("Qual o id do Ponto de Interesse: ");
                    id = Integer.parseInt(user.nextLine());

                    int min, hora, dia, mes, ano;
                    System.out.println("Data registada: \n");
                    System.out.println("Minuto: ");
                    min = Integer.parseInt(in.nextLine());
                    System.out.println("Hora: ");
                    hora = Integer.parseInt(in.nextLine());
                    System.out.println("Dia: ");
                    dia = Integer.parseInt(in.nextLine());
                    System.out.println("Mes: ");
                    mes = Integer.parseInt(in.nextLine());
                    System.out.println("Ano: ");
                    ano = Integer.parseInt(in.nextLine());

                    Historico h = null;

                    h = new Historico(Historico.getNextId(), dia, mes, ano, hora, min, nome1, id);

                    Historico.addHistorico(h);
                    break;
                }

                case 0:
                    menu();
                    break;
                default:
                    System.out.println("Opcao " + opcao + " invalida");
            }
        } while (opcao != 0);
    }

    /**
     * Menu de gestão de nodes, com capacidade de inserir, remover, editar, pesquisar, listar Nodes
     * @param in, input do user relativo a escolha que pretende fazer
     */

    private static void menu_nodes(Scanner in) {
        int opcao;
        do {
            System.out.println("== Menu Gestao Nodes ==");
            System.out.println("1- Adicionar node");
            System.out.println("2- Remover node");
            System.out.println("3- Editar node");
            System.out.println("4- Pesquisar node");
            System.out.println("5- Listar  Nodes");
            System.out.println("0- Sair");
            System.out.println();
            System.out.print("Escolha opcao: ");
            opcao = Integer.parseInt(in.nextLine());
            switch (opcao) {
                case 1: {
                    // adicionar nodes
                    float x, y;
                    System.out.print("Qual X(float) do node ");
                    x = Float.parseFloat(in.nextLine());
                    System.out.print("Qual Y(float) do node ");
                    y = Float.parseFloat(in.nextLine());

                    String tipo;
                    do {
                        System.out.print("Qual o tipo de node: ");
                        tipo = in.nextLine();
                        if (!tipo.equals("Semaforo") && !tipo.equals("PoI"))
                            System.out.println("Erro: tipo tem que ser: PoI, Semaforo");

                    } while (!tipo.equals("PoI") && !tipo.equals("Semaforo"));

                    int tag_id;
                    printAllTags();
                    System.out.println("Qual o id da tag que pretende associar a este Node");
                    tag_id = Integer.parseInt(in.nextLine());
                    int poi_id = 0;
                    if (tipo == "PoI") {
                        printAllNodes();
                        System.out.println("Qual o id do PoI que pretende associar a este Node");
                        poi_id = Integer.parseInt(in.nextLine());
                    }

                        /*if (tipo.equals("PoI")){
                            
                            System.out.print("Qual o nome do PoI\n");
                            String nome_PoI = in.readString();
                            
                            System.out.print("Qual a descriçao do PoI\n");
                            String descricao_PoI = in.readString();
                            
                            Pol p = new Pol(x,y,Nodes.getNextId(),tipo, tag_id,Pol.getNextId(),nome_PoI,descricao_PoI);
                            Nodes.add_node_PoI(p);
                            break;
                        }else{*/

                    Nodes n = null;
                    n = new Nodes(x, y, Nodes.getNextId(), tipo, tag_id, poi_id);
                    Nodes.add_node(n);
                    write_Node_AccionTo_TXT("C:\\Users\\sergi\\Desktop\\Projeto-LP2-AED2-JAVAFX\\data\\accion2.txt", n,"Adicionar Node ");


                    break;

                }
                case 2:
                    printAllNodes();
                    System.out.print("Qual o id do node que pretende remover: ");
                    int node = Integer.parseInt(in.nextLine());
                    Nodes n1 = BD.nodesST.get(Nodes.pesquisar_node(node));
                    Nodes.remover_node(node);
                    write_Node_AccionTo_TXT("C:\\Users\\sergi\\Desktop\\Projeto-LP2-AED2-JAVAFX\\data\\accion2.txt",n1, "Remover Node");

                    break;
                case 3: {  // editar node
                    printAllNodes();
                    System.out.print("Qual o id do node que pretende editar: ");
                    int id = Integer.parseInt(in.nextLine());
                    Nodes n = null;
                    // procurar node
                    if (Nodes.pesquisar_node(id) == 0) {
                        System.out.println("Node nao encontrada;");
                    } else {
                        n = BD.nodesST.get(Nodes.pesquisar_node(id));
                    }

                    System.out.print("Qual o novo X");
                    float x = Float.parseFloat(in.nextLine());
                    System.out.print("Qual o novo Y");
                    float y = Float.parseFloat(in.nextLine());
                    String tipo;
                    do {
                        System.out.print("Qual o novo tipo de node: ");
                        tipo = in.nextLine();
                        if (!tipo.equals("PoI") && !tipo.equals("Semaforo"))
                            System.out.println("Erro: tipo tem que ser: PoI, Semaforo");

                    } while (!tipo.equals("PoI") && !tipo.equals("Semaforo"));
                    int tag_id;
                    printAllTags();
                    System.out.println("Qual o novo id da tag que pretende associar a este Node");
                    tag_id = Integer.parseInt(in.nextLine());
                    int poi_id = 0;
                    if (tipo == "PoI") {
                        printAllNodes();
                        System.out.println("Qual o novo id do PoI que pretende associar a este Node");
                        poi_id = Integer.parseInt(in.nextLine());
                    }

                    n.setX(x);
                    n.setY(y);
                    n.setTipo_de_node(tipo);
                    n.setTag_id(tag_id);
                    n.setPoI_id(poi_id);
                    write_Node_AccionTo_TXT("C:\\Users\\sergi\\Desktop\\Projeto-LP2-AED2-JAVAFX\\data\\accion2.txt", n, "Editar Node ");

                    break;
                }
                case 4: {
                    printAllNodes();
                    System.out.print("Qual o id do node que pretende pesquisar: ");
                    int id = Integer.parseInt(in.nextLine());
                    Nodes.pesquisar_node(id);
                }
                case 5:
                    printAllNodes();

                case 0:
                    menu();
                    break;
                default:
                    System.out.println("Opcao " + opcao + " invalida");
            }
        } while (opcao != 0);
    }

    /*private static void menu_way(In in) {
        int opcao;
        do {
            System.out.println("== Menu Gestao Ways ==");
            System.out.println("1- Adicionar Way");
            System.out.println("2- Remover Way");
            System.out.println("3- Editar Way");
            System.out.println("4- Pesquisar Way");
            System.out.println("5- Listar  Way");
            System.out.println("0- Voltar");
            System.out.println();
            System.out.print("Escolha opcao: ");
            opcao = in.readInt();
            switch (opcao) {
                case 1: {
                    // adicionar way

                    String tipo;
                    do {
                        System.out.print("Qual o tipo de Rua: ");
                        tipo = in.readString();
                        if (!tipo.equals("Pedonal") && !tipo.equals("Paralelo") && !tipo.equals("Alcatrao"))
                            System.out.println("Erro: tipo tem que ser: Pedonal, Paralelo, Alcatrao");

                    } while (!tipo.equals("Pedonal") && !tipo.equals("Paralelo") && !tipo.equals("Alcatrao"));
                    System.out.print("Qual o peso da Rua: ");
                    int peso = in.readInt();
                    System.out.print("Qual o comprimento da Rua: ");
                    int c = in.readInt();
                    Ways w = null;
                    w = new Ways(Ways.getNextId(),tipo,peso,c);


                    Ways.add_Ways(w);
                    break;
                }
                case 2:
                    printAllNodes();
                    System.out.print("Qual o id do node que pretende remover: ");
                    int tag = in.readInt();
                    Tags.remover_tag(tag);
                    break;
                case 3: {  // editar node
                    System.out.print("Qual o id do node que pretende editar: ");
                    int id = in.readInt();
                    Nodes n = null;
                    // procurar node
                    if (Nodes.pesquisar_node(id)==0){
                        System.out.println("Node nao encontrada;");
                    }else{
                        n = BD.nodesST.get(Nodes.pesquisar_node(id));
                    }


                    System.out.print("Qual o novo X");
                    int x = in.readInt();
                    System.out.print("Qual o novo Y");
                    int y = in.readInt();
                    String tipo;
                    do {
                        System.out.print("Qual o novo tipo de node: ");
                        tipo = in.readString();
                        if (!tipo.equals("Curva") && !tipo.equals("Semaforo"))
                            System.out.println("Erro: tipo tem que ser: Curva, Semaforo");

                    } while (!tipo.equals("Basic") && !tipo.equals("Admin"));

                    n.setX(x);
                    n.setY(y);
                    n.setTipo_de_node(tipo);
                    break;
                }
                case 4 : System.out.print("Qual o id do node que pretende pesquisar: ");
                    int id = in.readInt();
                    Nodes.pesquisar_node(id);
                case 5: printAllNodes();

                case 0:
                    break;
                default:
                    System.out.println("Opcao " + opcao + " invalida");
            }
        } while (opcao != 0);
    }*/

    /**
     * Menu de gestão de Tags, com capacidade de inserir, remover, editar, pesquisar, listar
     * @param in, input do user relativo a escolha que pretende fazer
     */
    private static void menu_tags(Scanner in) {
        int opcao;
        do {
            System.out.println("== Menu Gestao de TAGS ==");
            System.out.println("1- Adicionar Tag");
            System.out.println("2- Remover Tag");
            System.out.println("3- Editar Tag");
            System.out.println("4- Pesquisar Tag");
            System.out.println("5- Listar Tags");
            System.out.println("0- Sair");
            System.out.print("Escolha opcao: ");
            opcao = Integer.parseInt(in.nextLine());
            switch (opcao) {
                case 1: {
                    // adicionar tags
                    String tag;
                    System.out.print("Qual a tag que pretende criar: ");
                    tag = in.nextLine();

                    Tags t = new Tags(Tags.getNextId(), tag);
                    Tags.add_Tags(t);
                    write_Tags_AccionTo_TXT("C:\\Users\\sergi\\Desktop\\Projeto-LP2-AED2-JAVAFX\\data\\accion2.txt", t, " Adicionar Tags ");

                    break;
                }
                case 2:
                    printAllTags();
                    System.out.print("Qual o id da tag que pretende remover: ");
                    int tag = Integer.parseInt(in.nextLine());

                    Tags t1 = BD.tags_ST.get(Tags.pesquisar_Tag(tag));
                    Tags.remover_tag(tag);

                    write_Tags_AccionTo_TXT("C:\\Users\\sergi\\Desktop\\Projeto-LP2-AED2-JAVAFX\\data\\accion2.txt", t1, "Remover Tag ");

                    break;
                case 3: {  // editar tags
                    printAllTags();
                    System.out.print("Qual o id da tag que pretende editar: ");
                    int id = Integer.parseInt(in.nextLine());
                    Tags t = null;
                    // procurar tag
                    if (Tags.pesquisar_Tag(id) == 0) {
                        System.out.println("tag nao encontrada;");
                    } else {
                        t = BD.tags_ST.get(Tags.pesquisar_Tag(id));
                    }

                    System.out.print("Qual a nova descriçao: ");
                    String tag2 = in.nextLine();

                    t.setTag_descricao(tag2);
                    write_Tags_AccionTo_TXT("C:\\Users\\sergi\\Desktop\\Projeto-LP2-AED2-JAVAFX\\data\\accion2.txt", t, "Editar Tags ");


                    break;
                }

                case 4:
                    System.out.print("Qual o id da tag que pretende pesquisar: ");
                    int id = Integer.parseInt(in.nextLine());
                    Tags.pesquisar_Tag(id);

                case 5:
                    printAllTags();

                case 0:
                    menu();
                    break;
                default:
                    System.out.println("Opcao " + opcao + " invalida");
            }
        } while (opcao != 0);
    }

    /**
     * Menu de gestão de Pontos de Interesse, com capacidade de inserir, remover, editar, pesquisar, listar
     * @param in, input do user relativo a escolha que pretende fazer
     */
    private static void menu_pols(Scanner in) {
        int opcao;
        do {
            System.out.println("== Menu Gestao de Pontos de Interessse ==");
            System.out.println("1- Adicionar Pol");
            System.out.println("2- Remover Pol");
            System.out.println("3- Editar Pol");
            System.out.println("4- Pesquisar Pol");
            System.out.println("5- Listar Pol");
            System.out.println("0- Sair");
            System.out.println();
            System.out.print("Escolha opcao: ");
            opcao = Integer.parseInt(in.nextLine());
            switch (opcao) {
                case 1: {
                    // adicionar Pol
                    String pol_nome;
                    String pol_descricao;
                    System.out.print("Qual o Pol que pretende criar: ");
                    pol_nome = in.nextLine();
                    System.out.println("Qual a descricao que pretende ter o Pol: ");
                    pol_descricao = in.nextLine();
                    Pol p  =new Pol(Pol.getNextId(),pol_nome, pol_descricao);
                    Pol.add_Pol(p);
                    write_Pol_AccionTo_TXT("C:\\Users\\sergi\\Desktop\\Projeto-LP2-AED2-JAVAFX\\data\\accion2.txt", p, "Adicionar Pol ");

                    break;
                }
                case 2: {
                    //remover
                    printAllPoi();
                    System.out.print("Qual o id do Pol que pretende remover: ");
                    int pol = Integer.parseInt(in.nextLine());
                    Pol p1 = BD.Pol_ST.get(Pol.pesquisar_Poi(pol));
                    Pol.remover_pol(pol);
                    write_Pol_AccionTo_TXT("C:\\Users\\sergi\\Desktop\\Projeto-LP2-AED2-JAVAFX\\data\\accion2.txt", p1, "Remover Pol ");

                    break;
                }
                case 3: {  // editar pol
                    System.out.print("Qual o id do Pol que pretende editar: ");
                    int id = Integer.parseInt(in.nextLine());
                    Pol p = null;
                    // procurar pol
                    if (Pol.pesquisar_Poi(id) == 0) {
                        System.out.println("Pol nao encontrado;");
                    } else {
                        p = BD.Pol_ST.get(Pol.pesquisar_Poi(id));
                    }
                    /** PRINT USER INFO **/

                    // NAO ESTA A GUARDAR A NOVA DESCRICAO
                    System.out.print("Qual a nova descriçao: ");
                    String Pol2 = in.nextLine();

                    p.setDescricao(Pol2);

                    write_Pol_AccionTo_TXT("C:\\Users\\sergi\\Desktop\\Projeto-LP2-AED2-JAVAFX\\data\\accion2.txt", p, "Editar Pol ");


                    break;
                }

                case 4:
                    System.out.print("Qual o id do Pol que pretende pesquisar: ");
                    int id = Integer.parseInt(in.nextLine());
                    Pol.pesquisar_Poi(id);

                case 5:
                    printAllPoi();

                case 0:
                    menu();
                    break;
                default:
                    System.out.println("Opcao " + opcao + " invalida");
            }
        } while (opcao != 0);
    }

    /**
     * Menu de gestão do Historico do User, com capacidade de listar informação dentro de uma restrição de tempo
     * listar Pols mais visitados, Pols visitados por user, Pols nao visitados por user entre outros
     * @param in, input do user relativo a escolha que pretende fazer
     */
    private static void menu_historico(Scanner in) {
        int opcao;
        do {
            System.out.println("== Menu Historico == ");
            System.out.println("1- Adicionar Historico");
            System.out.println("2- Listar Pol Visitado pelo User Num dado Periodo");
            System.out.println("3- Listar Pol Nao Visitado pelo User Num dado Periodo");
            System.out.println("4- Listar Todo o User que visitou certo Pol");
            System.out.println("5- Listar Pol Nao Visitado por Users Num dado Periodo");
            System.out.println("6 -Listar Top 5 User que mais Pol Visitaram num dado Periodo");
            System.out.println("7 -Listar Top 5 Pol mais Visitados num dado Periodo");
            System.out.println("8 -Listar Nodes e Ways associados a uma Tag");
            System.out.println("0- Sair");
            System.out.print("Escolha opcao: ");
            opcao = Integer.parseInt(in.nextLine());
            switch (opcao) {
                case 1: {
                    // adicionar utilizadores
                    System.out.print("Qual o nome do utilizador a adicinar: ");
                    String nome1 = in.nextLine();

                    User user = BD.user_ST.get(nome1);
                    if (user == null) {
                        System.out.println("Erro: utilizador nao existe");
                        continue;
                    } else {
                        System.out.println("Insira Novamente: ");
                        nome1 = in.nextLine();
                    }
                    int id;
                    System.out.print("Qual o id do Ponto de Interesse: ");
                    id = Integer.parseInt(in.nextLine());

                    int min, hora, dia, mes, ano;
                    System.out.println("Data registada: \n");
                    System.out.println("Minuto: ");
                    min = Integer.parseInt(in.nextLine());
                    System.out.println("Hora: ");
                    hora = Integer.parseInt(in.nextLine());
                    System.out.println("Dia: ");
                    dia = Integer.parseInt(in.nextLine());
                    System.out.println("Mes: ");
                    mes = Integer.parseInt(in.nextLine());
                    System.out.println("Ano: ");
                    ano = Integer.parseInt(in.nextLine());

                    Historico h = null;

                    h = new Historico(Historico.getNextId(), dia, mes, ano, hora, min, nome1, id);

                    Historico.addHistorico(h);

                    break;
                }
                case 2: {
                    int min, hora, dia, mes, ano;
                    int min1, hora1, dia1, mes1, ano1;
                    System.out.println("Data Inicial: \n");
                    System.out.println("Minuto: ");
                    min = Integer.parseInt(in.nextLine());
                    System.out.println("Hora: ");
                    hora = Integer.parseInt(in.nextLine());
                    System.out.println("Dia: ");
                    dia = Integer.parseInt(in.nextLine());
                    System.out.println("Mes: ");
                    mes = Integer.parseInt(in.nextLine());
                    System.out.println("Ano: ");
                    ano = Integer.parseInt(in.nextLine());


                    System.out.println("Data Final: \n");
                    System.out.println("Minuto: ");
                    min1 = Integer.parseInt(in.nextLine());
                    System.out.println("Hora: ");
                    hora1 = Integer.parseInt(in.nextLine());
                    System.out.println("Dia: ");
                    dia1 = Integer.parseInt(in.nextLine());
                    System.out.println("Mes: ");
                    mes1 = Integer.parseInt(in.nextLine());
                    System.out.println("Ano: ");
                    ano1 = Integer.parseInt(in.nextLine());

                    Date inicio = new Date(dia, mes, ano, hora, min);
                    Date fim = new Date(dia1, mes1, ano1, hora1, min1);

                    // adicionar utilizadores
                    System.out.print("Qual o nome do utilizador a pesquisar: ");
                    String nome1 = in.nextLine();

                    Historico.PrintPolVisitado(inicio, fim, nome1);


                    break;
                }
                case 3: {
                    printAllHistorico();
                }
                /*
                case 3: {  // editar pol
                    System.out.print("Qual o id do Pol que pretende editar: ");
                    int id = in.readInt();
                    Pol p = null;
                    // procurar pol
                    if (Pol.pesquisar_Poi(id)==0){
                        System.out.println("Pol nao encontrado;");
                    }else{
                        p = BD.Pol_ST.get(Pol.pesquisar_Poi(id));
                    }
                    /** PRINT USER INFO **/

                // NAO ESTA A GUARDAR A NOVA DESCRICAO
                    /*
                    System.out.print("Qual a nova descriçao: ");
                    String Pol2 = in.readString();

                    p.setDescricao(Pol2);

                    break;
                }

                case 4 : System.out.print("Qual o id do Pol que pretende pesquisar: ");
                    int id = in.readInt();
                    Pol.pesquisar_Poi(id);

                case 5 : printAllPoi();
*/
                case 0:
                    menu();
                    break;
                default:
                    System.out.println("Opcao " + opcao + " invalida");
            }


        }
        while (opcao != 0);
    }

    /**
     * Menu de gestão de Grafos, com capacidade de pesquisar grafos, sub-grafos, Calcular o menor custo, verificar a conectividade
     * @param in, input do user relativo a escolha que pretende fazer
     */
    private static void menu_grafos(Scanner in) {
        int opcao;
        do {
            System.out.println("== Menu Gestao de Grafos ==");
            System.out.println("1- Pesquisa Grafo estatico");
            System.out.println("2- Pesquisa Grafo ficheiro");
            System.out.println("3- Pesquisa Sub-Grafo  estatico");
            System.out.println("4- Pesquisa Sub-Grafo  ficheiro");
            System.out.println("5- Verificar se grafo e conexo");
            System.out.println("0- Sair");
            System.out.print("Escolha opcao: ");
            opcao = Integer.parseInt(in.nextLine());
            switch (opcao) {
                case 1: {
                    MST1();
                    break;
                }
                case 2:
                    System.out.print("Qual o ponto inicial: ");
                    int s = Integer.parseInt(in.nextLine());
                    System.out.print("Qual o ponto Final: ");
                    int t2 = Integer.parseInt(in.nextLine());
                    MST2("C:\\Users\\sergi\\Desktop\\Projeto-LP2-AED2-JAVAFX\\data\\ewd_g1.txt", s, t2);
                    break;
                case 3: {
                    System.out.print("Qual o ponto inicial: ");
                    int s1 = Integer.parseInt(in.nextLine());
                    System.out.print("Qual o ponto Final: ");
                    int t3 = Integer.parseInt(in.nextLine());
                    MST_subgrafo("C:\\Users\\sergi\\Desktop\\Projeto-LP2-AED2-JAVAFX\\data\\ewd_g1.txt", s1, t3);

                    break;
                }

                case 4:
                    System.out.print("Qual o id da tag que pretende pesquisar: ");
                    int id = Integer.parseInt(in.nextLine());
                    Tags.pesquisar_Tag(id);

                case 5:
                    //Check_conexo_static();
                    Check_conexo_FILE("C:\\Users\\sergi\\Desktop\\Projeto-LP2-AED2-JAVAFX\\data\\g_conexo.txt");
                    //    Check_conexo_FILE("C:\\\\Users\\\\sergi\\\\Desktop\\\\Projeto-LP2-AED2-JAVAFX\\\\data\\\\g2_conexo.txt");
                    //Check_conexo_FILE("C:\\Users\\sergi\\Desktop\\Projeto-LP2-AED2-JAVAFX\\data\\ewd_g1.txt");

                case 0:
                    menu();
                    break;
                default:
                    System.out.println("Opcao " + opcao + " invalida");
            }
        } while (opcao != 0);
    }


       /*
        private static void read_user_bufferedReader(String path){
            File file=null;
            FileReader fr=null;
            BufferedReader br=null;

            try {
                br = new BufferedReader(new FileReader(new File(path))); //crio o BufferedReader(new File Reader e o filename)

                // Read lines from file
                String line=""; //line vai ler uma linha inteira
                int line_count=1, bcount=0;
                //byte[] b = new byte[(int)file.length()];
                while ((line=br.readLine()) != null) {
                    System.out.println("Linha " + (line_count++) + ": " + line);
                    //byte[] baux = line.getBytes();
                    //System.arraycopy(baux, 0, b, bcount, baux.length);
                    StringTokenizer st=new StringTokenizer(line, ","); //strtok
                    //inicializo as variaveis que vou ler
                    int tokenCount=0;
                    String name=null;
                    int number=0;
                    float grade=0.0f;
                    //enquando houver para ler das que separei com o StringTokenizer
                    while (st.hasMoreTokens()) {
                        String token=st.nextToken();
                        System.out.println("FileBufferedReaderApp main(): token ["+tokenCount+"] = " + token); //numero do Token + o conteúdo
                        switch (tokenCount) {
                            case 0: //se for o 1, é o nome
                                name=token;
                                break;
                            case 1: //se for o 2, é o numero
                                number=Integer.parseInt(token.trim());
                                break;
                            case 2: //se for o 3, é a grade
                                grade=Float.parseFloat(token);
                                break;
                        }
                        tokenCount++; //itero a tokenCount, avanco o campo que li
                    }
                    System.out.println("FileBufferedReaderApp main(): read = " + name + "," + number + "," + grade);
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally { //finally fecha os wrappers pela ordem inversa que foram abertos
                try {
                    if (br != null) {
                        br.close();
                    }
                    if (fr != null) {
                        fr.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        */

    /**
     * Leitura de ficheiro txt e guardar a informação num User Novo
     * @param path Diretorio do ficheiro
     */
    private static void readUserFromFile(String path) {
        try {
            In in = new In(path);
            in.readLine();

            while (!in.isEmpty()) {
                String line = in.readLine();
                String[] fields = line.split(FILE_DELIMETER);

                User u = null;

                if (fields[1].equals("Basic")) {
                    u = new Basic(Integer.parseInt(fields[0]), fields[2], Integer.parseInt(fields[3]), fields[4]);
                } else if (fields[1].equals("Admin")) {
                    u = new UserAdmin(Integer.parseInt(fields[0]), fields[2], Integer.parseInt(fields[3]), fields[4]);
                }
                User.add_User(u);
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Leitura de ficheiro txt e guardar a informação num Node Novo
     * @param path Diretorio do ficheiro
     */
    private static void readNodesFromFile(String path) {
        try {
            In in = new In(path);
            in.readLine();
            while (!in.isEmpty()) {
                String line = in.readLine();
                String[] fields = line.split(FILE_DELIMETER);
                Nodes n = null;
                n = new Nodes(Float.parseFloat(fields[0]), Float.parseFloat(fields[1]), Integer.parseInt(fields[2]), fields[3], Integer.parseInt(fields[4]), Integer.parseInt(fields[5]));
                Nodes.add_node(n);
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Leitura de ficheiro txt e guardar a informação numa Tag Nova
     * @param path Diretorio do ficheiro
     */
    private static void readTagsFromFile(String path) {
        try {
            In in = new In(path);
            in.readLine();

            while (!in.isEmpty()) {
                String line = in.readLine();
                String[] fields = line.split(FILE_DELIMETER);

                Tags t = new Tags(Integer.parseInt(fields[0]), fields[1]);

                Tags.add_Tags(t);
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    /**
     * Leitura de ficheiro txt e guardar a informação num Ponto de Interesse Novo
     * @param path Diretorio do ficheiro
     */
    private static void readPoiFromFile(String path) {
        try {
            In in = new In(path);
            in.readLine();

            while (!in.isEmpty()) {
                String line = in.readLine();
                String[] fields = line.split(FILE_DELIMETER);

                Pol p = new Pol(Integer.parseInt(fields[0]), fields[1], fields[2]);
                Pol.add_Pol(p);
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Leitura de ficheiro txt e guardar a informação num Historico Novo
     * @param path Diretorio do ficheiro
     */
    private static void readHistoricoFromFile(String path) {
        try {
            In in = new In(path);
            in.readLine();

            while (!in.isEmpty()) {
                String line = in.readLine();
                String[] fields = line.split(FILE_DELIMETER);

                Historico h = new Historico(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]),
                        Integer.parseInt(fields[2]), Integer.parseInt(fields[3]),
                        Integer.parseInt(fields[4]), Integer.parseInt(fields[5]),
                        fields[6], Integer.parseInt(fields[7]));
                Historico.addHistorico(h);
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Leitura de ficheiro txt e guardar a informação num Graph Novo
     * @param path Diretorio do ficheiro
     */
    public static int readGraphFromFile(String path) {
        In in = new In(path);
        int nlines = Integer.parseInt(in.readLine());

        try {
            while (!in.isEmpty()) {
                String line = in.readLine();
                String[] fields = line.split(FILE_DELIMETER);

                DirectedEdge e1 = new DirectedEdge(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), Double.parseDouble(fields[2]));//Integer.parseInt(fields[2]));

                EdgeWeightedDigraph edgeWeightedDiGraph = new EdgeWeightedDigraph(nlines);
                edgeWeightedDiGraph.addEdge(e1);
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return nlines;
    }

    /**
     * Escrita a informação de um user para um ficheiro txt
     * @param path Diretorio do ficheiro
     */
    private static void writeUserToTXT(String path) {
        try {
            Out out = new Out(path);   //abre ficheiro
            //Email;Nome;Password
            out.print("user_id, user_tipo, user_nome, user_idade, user_adress\n");
            for (String u : BD.user_ST.keys()) {

                User user = BD.user_ST.get(u);

                out.print(user.getUser_id());
                out.print(";");
                if (user instanceof UserAdmin)
                    out.print("Admin;");
                else if (user instanceof Basic)
                    out.print("Basic;");
                out.print(user.getUser_nome());
                out.print(";");
                out.print(user.getUser_idade());
                out.print(";");
                out.print(user.getUser_adress());
                out.print("\n");

            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    /*private static void writeNodesToTXT(String path) {
        Out out = new Out(path);   //abre ficheiro
        //Email;Nome;Password
        out.print("id_node, x, y, tag_id, tipo de node, nome_pol, descricao");
        for (Integer u : BD.nodesST.keys()) {
            Nodes n =BD.Pol_ST.get(u);



            Pol p= null;
            p.pesquisar_Poi_Node(u);
            if (n.getTipo_de_node() == "PoI"){
                out.print(n.getId_node());
                out.print(";");
                out.print(n.getX());
                out.print(";");
                out.print(n.getY());
                out.print(";");
                out.print(n.getTag_id());
                out.print(";");
                out.print(n.getTipo_de_node());
                out.print(";");
                out.print(p.getNome_pol());
                out.print(";");
                out.print(p.getDescricao());
                out.print(";");
            }else{
                out.print(n.getId_node());
                out.print(";");
                out.print(n.getX());
                out.print(";");
                out.print(n.getY());
                out.print(";");
                out.print(n.getTag_id());
                out.print(";");
                out.print(n.getTipo_de_node());
                out.print(";");
            }

        }
    }*/

    /**
     * Escrita a informação de um historico para um ficheiro txt
     * @param path Diretorio do ficheiro
     */
    private static void writeHistoricoToTXT(String path) {
        try {
            Out out = new Out(path);   //abre ficheiro
            //Email;Nomhistorico_id, dia, mes, ano, hora, minuto, user_name, pol_ide;Password
            out.print("\n");
            for (String u : BD.historico_st.keys()) {

                Historico h = BD.historico_st.get(u);

                out.print(h.getHistorico_id());
                out.print(";");
                out.print(h.getDay());
                out.print(";");
                out.print(h.getMonth());
                out.print(";");
                out.print(h.getYear());
                out.print(";");
                out.print(h.getHour());
                out.print(";");
                out.print(h.getMin());
                out.print(";");
                out.print(h.getUser_name());
                out.print(";");
                out.print(h.getPol_id());
                out.print("\n");
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Escrita a informação de uma tag para um ficheiro txt
     * @param path Diretorio do ficheiro
     */

    private static void WriteTagToTXT(String path) {
        try {
            Out out = new Out(path);   //abre ficheiro
            //Email;Nome;Password
            out.print("tag_id, tag_descricao\n");
            for (Integer t : BD.tags_ST.keys()) {

                Tags tag = BD.tags_ST.get(t);
                out.print(tag.getTag_id());
                out.print(";");
                out.print(tag.getTag_descricao());
                out.print("\n");

            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Escrita a informação de um pol para um ficheiro txt
     * @param path Diretorio do ficheiro
     */
    private static void WritePoiToTXT(String path) {
        try {
            Out out = new Out(path);   //abre ficheiro
            //Email;Nome;Password
            out.print("poi_id, poi_tipo ,poi_caracteristica, poi_nodes\n");

            for (Integer p : BD.Pol_ST.keys()) {

                Pol pol = BD.Pol_ST.get(p);
                out.print(pol.getId_pol());
                out.print(";");
                out.print(pol.getNome_pol());
                out.print(";");
                out.print(pol.getDescricao());
                out.print("\n");

            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Imprimir todos os User existentes
     */

    private static void printAllUsers() {
        for (String name : BD.user_ST.keys()) {
            User user = BD.user_ST.get(name);
            //System.out.println(User.userST.get(name));
            System.out.println(user.toString());
        }
        System.out.println("\n\n");
    }

    /**
     * Imprimir todos os User Basic existentes
     */
    private static void printAllBasic() {

        for (String name : BD.user_ST.keys()) {
            User user = BD.user_ST.get(name);

            if (user instanceof UserAdmin)
                continue;
            else
                System.out.println(user.toString());
        }

        System.out.println("\n\n");
    }

    /**
     * Imprimir todos os User Admin existentes
     */
    private static void printAllAdmin() {

        for (String name : BD.user_ST.keys()) {
            User user = BD.user_ST.get(name);

            if (user instanceof UserAdmin) {
                System.out.println(user.toString());
            }
        }
        System.out.println("\n\n");
    }

    /**
     * Imprimir todos os Nodes existentes
     */
    private static void printAllNodes() {
        for (Integer id : BD.nodesST.keys()) {
            Nodes node = BD.nodesST.get(id);

            System.out.println(node.toString());
        }
        System.out.println("\n\n");
    }


    /**
     * Imprimir todos as Tags existentes
     */
    private static void printAllTags() {
        for (Integer id : BD.tags_ST.keys()) {
            Tags tag = BD.tags_ST.get(id);
            System.out.println(tag.toString());
        }
        System.out.println("\n\n");
    }

    /**
     * Imprimir todos as Ways existentes
     */

    private static void printAllWays() {
        for (Integer id : BD.ways_ST.keys()) {
            Ways way = BD.ways_ST.get(id);
            System.out.println(way.toString());
        }
        System.out.println("\n\n");
    }

    /**
     * Imprimir todos os Pol existentes
     */
    private static void printAllPoi() {
        for (Integer id : BD.Pol_ST.keys()) {
            Pol pol = BD.Pol_ST.get(id);
            System.out.println(pol.toString());
        }
        System.out.println("\n\n");
    }

    /**
     * Imprimir todos os Historicos existentes
     */

    private static void printAllHistorico() {
        for (String nome : BD.historico_st.keys()) {
            Historico historico = BD.historico_st.get(nome);
            System.out.println(historico.toString());
        }
        System.out.println("\n\n");
    }

    /**
     * Imprimir todos os Pols visitados pelo User
     * @param user User a pesquisar
     */


    private static void printAllPolsByUser(String user) {
        if (BD.historico_st.contains(user)) {
            Historico h = BD.historico_st.get(user);
            System.out.println(h.toString());
        } else
            System.out.println("Erro: User nao encontrado");
        System.out.println("\n\n");
    }


/*
        private static void readFiles(){
            readPoiFromFile("data/poi.txt");
            readUserFromFile("/data/user.txt");
            readTagsFromFile("/data/tags.txt");
    }
    private static void writeFiles(){
        writeUserToTXT("/data/user2.txt");
        WriteTagToTXT("/data/tag2.txt");
        WritePoiToTXT("/data/poi2.txt");
    }

 */

    /**
     * Iniciar a leitura de ficheiros
     */

    private static void readFiles() {

        readTagsFromFile("C:\\Users\\sergi\\Desktop\\Projeto-LP2-AED2-JAVAFX\\data\\tags.txt");
        readUserFromFile("C:\\Users\\sergi\\Desktop\\Projeto-LP2-AED2-JAVAFX\\data\\user.txt");
        readPoiFromFile("C:\\Users\\sergi\\Desktop\\Projeto-LP2-AED2-JAVAFX\\data\\poi.txt");
        readHistoricoFromFile("C:\\Users\\sergi\\Desktop\\Projeto-LP2-AED2-JAVAFX\\data\\historico.txt");
        readGraphFromFile("C:\\Users\\sergi\\Desktop\\Projeto-LP2-AED2-JAVAFX\\data\\ewd_g1.txt");
        readNodesFromFile("C:\\Users\\sergi\\Desktop\\Projeto-LP2-AED2-JAVAFX\\data\\nodes.txt");


    }

    /**
     * Iniciar a escrita de ficheiros
     */
    private static void writeFiles() {

        writeUserToTXT("C:\\Users\\sergi\\Documents\\Projeto-AED2-LP2-27-05\\Projeto-AED2-LP2-08-05\\Projeto-AED2-LP2-2022\\Projeto-AED2-LP2-2022\\data\\user2.txt");
        WriteTagToTXT("C:\\Users\\sergi\\Documents\\Projeto-AED2-LP2-27-05\\Projeto-AED2-LP2-08-05\\Projeto-AED2-LP2-2022\\Projeto-AED2-LP2-2022\\data\\tags2.txt");
        WritePoiToTXT("C:\\Users\\sergi\\Documents\\Projeto-AED2-LP2-27-05\\Projeto-AED2-LP2-08-05\\Projeto-AED2-LP2-2022\\Projeto-AED2-LP2-2022\\data\\poi2.txt");
        writeHistoricoToTXT("C:\\Users\\sergi\\Documents\\Projeto-AED2-LP2-27-05\\Projeto-AED2-LP2-08-05\\Projeto-AED2-LP2-2022\\Projeto-AED2-LP2-2022\\data\\historico2.txt");
        //writeNodesToTXT("C:\Users\sergi\Documents\Projeto-AED2-LP2-27-05\Projeto-AED2-LP2-08-05\Projeto-AED2-LP2-2022\Projeto-AED2-LP2-2022\data\accion.txtnodes2.txt");
         /*
        Test_write_file("C:\\Users\\sergi\\Documents\\Projeto-AED2-LP2-27-05\\Projeto-AED2-LP2-08-05\\Projeto-AED2-LP2-2022\\Projeto-AED2-LP2-2022\\data\\user2.txt");
         */
    }

    /**
     * Função que aplica o algoritmo de calculo de menor custo, versao estatica
     */

    public static void MST1() {
        int s = 0, t = 6;

        DirectedEdge e1 = new DirectedEdge(1, 2, 1);
        DirectedEdge e2 = new DirectedEdge(2, 3, 1);
        DirectedEdge e5 = new DirectedEdge(3, 4, 1);
        DirectedEdge e3 = new DirectedEdge(4, 5, 1);
        DirectedEdge e4 = new DirectedEdge(1, 4, 4);
        EdgeWeightedDigraph ewdg = new EdgeWeightedDigraph(6);
        ewdg.addEdge(e1);
        ewdg.addEdge(e2);
        ewdg.addEdge(e3);
        ewdg.addEdge(e5);
        ewdg.addEdge(e4);
        System.out.println(ewdg);
        System.out.println("Grafo: \n" + ewdg);
        System.out.println("SP from: " + s + " to " + t + ":\n");
        DijkstraSP sp = new DijkstraSP(ewdg, s);
        //AQUI CALCULA O CAMINHO MAIS CURTO
        for (DirectedEdge e : sp.pathTo(t)) {
            System.out.println(e);
            int a = e.from();
            //Nodes.pesquisar_node(a);
            Nodes n = BD.nodesST.get(Nodes.pesquisar_node(a));
            Pol.pesquisar_Poi(n.getPoI_id());
            Tags.pesquisar_Tag(n.getTag_id());
        }
        StdOut.println("Cost: " + sp.distTo(t));
    }

    /**
     * Função que aplica o algoritmo de calculo de menor custo, apartir da leitura do grafo de um ficheiro TXT
     * @param path Diretorio do ficheiro
     * @param s Valor de inicio a pesquisar
     * @param t Valor de fim a pesquiar
     */

    public static void MST2(String path, int s, int t) {
        //verificar
        In in = new In(path);
        int nlines = Integer.parseInt(in.readLine());
        DirectedEdge e1 = null;
        EdgeWeightedDigraph edgeWeightedDiGraph = new EdgeWeightedDigraph(nlines);

        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] fields = line.split(FILE_DELIMETER);

            e1 = new DirectedEdge(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), Double.parseDouble(fields[2]));//Integer.parseInt(fields[2]));

            edgeWeightedDiGraph.addEdge(e1);
        }
        System.out.println(edgeWeightedDiGraph);
        System.out.println("Grafo: \n" + edgeWeightedDiGraph);
        System.out.println("SP from: " + s + " to " + t + ":\n");
        DijkstraSP sp = new DijkstraSP(edgeWeightedDiGraph, s);
        //AQUI CALCULA O CAMINHO MAIS CURTO
        for (DirectedEdge e : sp.pathTo(t)) {
            System.out.println(e);
            int a = e.from();
            //Nodes.pesquisar_node(a);
            Nodes n = BD.nodesST.get(Nodes.pesquisar_node(a));
            Pol.pesquisar_Poi(n.getPoI_id());
            Tags.pesquisar_Tag(n.getTag_id());
        }
        StdOut.println("Cost: " + sp.distTo(t));
    }
    public static void MST_subgrafo(String path, int s, int t) {
        //verificar
        In in = new In(path);
        int nlines = Integer.parseInt(in.readLine());
        DirectedEdge e1 = null;
        EdgeWeightedDigraph edgeWeightedDiGraph = new EdgeWeightedDigraph(nlines);

        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] fields = line.split(FILE_DELIMETER);
            Nodes n1 = BD.nodesST.get(Nodes.pesquisar_node(Integer.parseInt(fields[0])));
            Nodes n2 = BD.nodesST.get(Nodes.pesquisar_node(Integer.parseInt(fields[1])));
            if (n1.getTipo_de_node().equals("Semaforo")||n2.getTipo_de_node().equals("Semaforo")){
               continue;
            }
            e1 = new DirectedEdge(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), Double.parseDouble(fields[2]));
            edgeWeightedDiGraph.addEdge(e1);
        }
        System.out.println(edgeWeightedDiGraph);
        System.out.println("Grafo: \n" + edgeWeightedDiGraph);
        System.out.println("SP from: " + s + " to " + t + ":\n");
        DijkstraSP sp = new DijkstraSP(edgeWeightedDiGraph, s);
        //AQUI CALCULA O CAMINHO MAIS CURTO
        for (DirectedEdge e : sp.pathTo(t)) {
            System.out.println(e);
            int a = e.from();
            //Nodes.pesquisar_node(a);
            Nodes n = BD.nodesST.get(Nodes.pesquisar_node(a));
            Pol.pesquisar_Poi(n.getPoI_id());
            Tags.pesquisar_Tag(n.getTag_id());
        }
        StdOut.println("Cost: " + sp.distTo(t));
    }

    /**
     * Funçao que aplica a verificação da conectividade do graph, forma estatica
     */

    public static void Check_conexo_static() {
        Digraph g = new Digraph(10);
        int[] vertex = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        g.addEdge(3, 7);
        g.addEdge(1, 4);
        g.addEdge(7, 8);
        g.addEdge(0, 5);
        g.addEdge(5, 2);
        g.addEdge(3, 8);
        g.addEdge(2, 9);
        g.addEdge(0, 6);
        g.addEdge(4, 9);
        g.addEdge(2, 6);
        g.addEdge(6, 4);

        ST<Integer, ArrayList<Integer>> closureArray1 = new ST<>();

        TransitiveClosure closure = new TransitiveClosure(g);

        for (int i : vertex) { // corre todos os vertices
            ArrayList<Integer> temp = new ArrayList<>(); //sao guardados em array temp
            for (int j : vertex) {   //corre denovo
                if (i != j) {
                    if (closure.reachable(i, j)) { // verifica se eles estao conectados
                        temp.add(j);
                    }
                }
            }
            closureArray1.put(i, temp); //ao estar conectados guarda na ST
            //    System.out.println("adicionou o valor ->" + i + " a st");
        }
        //  System.out.println("grafo conectado");

        System.out.println(closureArray1.toString());
    }

    /**
     * Funçao que aplica a verificação da conectividade do graph, leitura do ficheiro de um grafo
     * @param path Diretorio do ficheiro
     */
    public static void Check_conexo_FILE(String path) {
        In in = new In(path);
        int nlines = Integer.parseInt(in.readLine());
        Digraph g = new Digraph(nlines);

        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] fields = line.split(FILE_DELIMETER);
            g.addEdge(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]));
        }
        ST<Integer, ArrayList<Integer>> closureArray1 = new ST<>();
        TransitiveClosure closure = new TransitiveClosure(g);

        for (int i = 0; i < nlines; i++) { // corre todos os vertices
            ArrayList<Integer> temp = new ArrayList<>(); //sao guardados em array temp
            for (int j = 0; j < nlines; j++) {   //corre denovo
                if (i != j) {
                    if (closure.reachable(i, j)) { // verifica se eles estao conectados
                        temp.add(j);
                    }
                }
            }
            closureArray1.put(i, temp); //ao estar conectados guarda na ST
        }
        System.out.println("ST NO FIM:\n\n" + closureArray1);
    }

  //  public static ArrayList<accion> accionList;
    public static ArrayList<String> accionList;

    /**
     * Escrita das ações feitas por um user para um ficheiro TXT
     * @param path Diretorio do ficheiro
     * @param c    User que fez a açao
     * @param accion Ação feita pelo User
     */

    private static void write_User_AccionTo_TXT(String path, User c, String accion) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(path, true));
            if(pw != null){
                pw.append(accion).append(FILE_DELIMETER).append(c.toString()).append(FILE_DELIMETER).append("\n");
                pw.close();
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    /**
     * Escrita das ações feitas por um Node para um ficheiro TXT
     * @param path Diretorio do ficheiro
     * @param n    Node que fez a açao
     * @param accion Ação feita pelo Node
     */
    private static void write_Node_AccionTo_TXT(String path, Nodes n, String accion) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(path, true));
            if(pw != null){
                pw.append(accion).append(FILE_DELIMETER).append(n.toString()).append(FILE_DELIMETER).append("\n");
                pw.close();
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    /**
     * Escrita das ações feitas por uma Way para um ficheiro TXT
     * @param path Diretorio do ficheiro
     * @param w    Way que fez a açao
     * @param accion Ação feita pelo Way
     */

    private static void write_Ways_AccionTo_TXT(String path, Ways w, String accion) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(path, true));
            if(pw != null){
                pw.append(accion).append(FILE_DELIMETER).append(w.toString()).append(FILE_DELIMETER).append("\n");
                pw.close();
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    /**
     * Escrita das ações feitas por uma tag para um ficheiro TXT
     * @param path Diretorio do ficheiro
     * @param t    Tag que fez a açao
     * @param accion Ação feita pelo Tag
     */
    private static void write_Tags_AccionTo_TXT(String path, Tags t, String accion) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(path, true));
            if(pw != null){
                pw.append(accion).append(FILE_DELIMETER).append(t.toString()).append(FILE_DELIMETER).append("\n");
                pw.close();
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    /**
     * Escrita das ações feitas por um pol para um ficheiro TXT
     * @param path Diretorio do ficheiro
     * @param p    Pol que fez a açao
     * @param accion Ação feita pelo Pol
     */
    private static void write_Pol_AccionTo_TXT(String path, Pol p, String accion) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(path, true));
            if(pw != null){
                pw.append(accion).append(FILE_DELIMETER).append(p.toString()).append(FILE_DELIMETER).append("\n");
                pw.close();
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }
}
