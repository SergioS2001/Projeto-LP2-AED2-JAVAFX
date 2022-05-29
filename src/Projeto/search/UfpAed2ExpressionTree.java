package Projeto.search;

import java.util.ArrayList;

public class UfpAed2ExpressionTree {

    public static void main(String[] args) {
        UfpAed2BinaryTree<String, Integer> st = new UfpAed2BinaryTree<String, Integer>();
        String expression = "4 + 3 + 5 * 2 / 4"; // the test arithmetic expression
        ArrayList lst = parseAritExpression(expression);
        int sz = lst.size();
        UfpAed2BinaryTree.Node node = null, node_left = null, node_right = null;

        for (int operator_type = 0; operator_type < 2; operator_type++) {


            for (int i = 0; i < sz; i++) {
                // test multiplicative first and additive second arithmetic operators
                if (lst.get(i).getClass().getName().compareTo(String.class.getName()) == 0
                        && isOperator(((String) lst.get(i)).charAt(0), operator_type)) {
                    node = st.createNode((String) lst.get(i), 0);
                    if (lst.get(i - 1).getClass().getName().compareTo(String.class.getName()) == 0) {
                        node_left = st.createNode((String) lst.get(i - 1), 0);
                    } else {
                        node_left = (UfpAed2BinaryTree.Node) lst.get(i - 1); // node already created
                    }
                    if (lst.get(i + 1).getClass().getName().compareTo(String.class.getName()) == 0) {
                        node_right = st.createNode((String) lst.get(i + 1), 0);
                    } else {
                        node_right = (UfpAed2BinaryTree.Node) lst.get(i + 1); // node already created
                    }               // housekeeping tasks
                    node.setLeft(node_left);
                    node.setRight(node_right);
                    lst.set(i, node);
                    lst.remove(i + 1);
                    lst.remove(i - 1);
                    i = i - 2;
                    sz = sz - 2;
                }
            }
        }

        st.setRoot(node); // set the expression three root node
        
        //TO-DO: traverse the binary tree to calculate the expression value
        
        System.out.println("Pre order list:");
        st.preOrder(st.getRoot());
    }

    /**
     * Parsing arithmetic expression composed by numbers and aritmethic
     * operators +,-,/,
     *
     *
     * @param exp
     * @return
     */
    public static ArrayList parseAritExpression(String exp) {
        ArrayList expression = new ArrayList();
        String str = "";
        for (int i = 0; i < exp.length(); i++) {
            if (exp.charAt(i) >= '0' && exp.charAt(i) <= '9' || exp.charAt(i) == '.') {
                str = str + exp.charAt(i);
            } else if (isOperator(exp.charAt(i), -1)) {
                // one operator detected
                if (str.length() > 0) {
                    expression.add(str);
                }
                expression.add(exp.substring(i, i + 1));
                str = "";
            }
        }
        if (str.length() > 0) {
            expression.add(str); // last number
        }
        return expression;
    }

    /**
     * Operators are ordered by precedence (all this operators group left to
     * right) Test if exp is operator of type: -1:all, 0:multiplicative,
     * 1:additive
     *
     * @param exp
     * @param type
     * @return true/false
     */
    public static boolean isOperator(char exp, int type) {
        if ((type == -1 || type == 0) && (exp == '*' || exp == '/')) {
            return true;
        } else if ((type == -1 || type == 1) && (exp == '+' || exp == '-')) {
            return true;
        } else {
            return false;
        }
    }
}