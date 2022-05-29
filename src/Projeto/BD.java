package Projeto;

import edu.princeton.cs.algs4.RedBlackBST;

public class BD {

    /**
     * Base dados, contem todas as Symbol Tables usadas para guardar a informação das diferentes classes
     */

    static RedBlackBST<String, User> user_ST = new RedBlackBST<>();
    public static RedBlackBST<Integer, Nodes> nodesST = new RedBlackBST<>();
    static RedBlackBST<Integer, Tags> tags_ST = new RedBlackBST<>();
    static RedBlackBST<Integer, Ways> ways_ST = new RedBlackBST<>();

    static RedBlackBST<Integer, Pol> Pol_ST = new RedBlackBST<>();

    static RedBlackBST<String, Historico> historico_st = new RedBlackBST<String, Historico>();

}
