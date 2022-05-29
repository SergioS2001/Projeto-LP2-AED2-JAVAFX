package Projeto.search;

public class UfpAed2BinaryTree<Key extends Comparable<Key>, Value> {

    private Node root;             // root of BinaryTree

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
            this.left = null;
            this.left = null;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Key getKey() {
            return key;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public Value getVal() {
            return val;
        }

        @Override
        public String toString() {
            return "Node{" + "key=" + key + ", val=" + val + '}';
        }
    }

    // does there exist a key-value pair with given key?
    public boolean contains(Key key) {
        return get(key) != null;
    }

    // return value associated with the given key, or null if no such key exists
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp != 0) {
            Value v = get(x.left, key);
            if (v == null) {
                return get(x.right, key);
            } else {
                return v;
            }
        } else {
            return x.val;
        }
    }

    /**
     * ***************************************************************************
     * Test client
     * ***************************************************************************
     */

    public static void main(String[] args) {
        // creating a new binary tree: each node has an integer key/value pair (value is always zero)
        UfpAed2BinaryTree<Integer, Integer> st = new UfpAed2BinaryTree<Integer, Integer>();
        // nó root - nivel/level 0
        st.setRoot(st.createNode(65, 0));
        // nos nivel 1
        st.getRoot().setLeft(st.createNode(34, 0));
        st.getRoot().setRight(st.createNode(76, 0));
        // nos  nivel 2
        st.getRoot().getLeft().setLeft(st.createNode(21, 0));
        st.getRoot().getLeft().setRight(st.createNode(44, 0));
        st.getRoot().getRight().setRight(st.createNode(90, 0));
        // nos  nivel 3        
        st.getRoot().getLeft().getLeft().setLeft(st.createNode(8, 0));
        st.getRoot().getRight().getRight().setLeft(st.createNode(81, 0));

        System.out.println("Pre order list:");
        st.preOrder(st.getRoot());
    }

    public void preOrder(Node no) {
        if (no != null) {
            System.out.println(no);
            preOrder(no.getLeft());
            preOrder(no.getRight());
        }
    }

    public Node createNode(Key key, Value val) {
        return new Node(key, val);
    }

    public int subTreeSize(Node x) {
        if (x == null) {
            return 0;
        }
        return subTreeSize(x.left) + subTreeSize(x.right) + 1;
    }
}
