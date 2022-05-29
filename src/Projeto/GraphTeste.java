package Projeto;
import edu.princeton.cs.algs4.*;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Vector;

class WeightedEdge{
    int source;
    int destination;
    int weight;

    public WeightedEdge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

public class GraphTeste {

    static int vertice;
    static LinkedList<Edge>[] adjacencylist;

    public GraphTeste(int vertice, LinkedList<Edge>[] adjacencylist) {
        this.vertice = vertice;
        this.adjacencylist = adjacencylist;
    }
    public int getVertice() {
        return vertice;
    }
    public void setVertice(int vertice) {
        this.vertice = vertice;
    }
    public LinkedList<Edge>[] getAdjacencylist() {
        return adjacencylist;
    }
    public void setAdjacencylist(LinkedList<Edge>[] adjacencylist) {
        this.adjacencylist = adjacencylist;
    }

    public static void testeGraph(String[] args) {
        int [] teste_graph = new int[] {1,2,3,4,5,6,7,8,9,10};

        Graph graphTeste = new Graph(5);

        graphTeste.addEdge(1,1);
        graphTeste.addEdge(1,2);
        graphTeste.addEdge(3,2);
        graphTeste.addEdge(4,3);
        graphTeste.addEdge(4,0);

        for (int i: teste_graph) System.out.println(graphTeste.toString());
    }
    public static void testeDigraph(){
        int [] teste_Digraph = new int[] {1,2,3,4,5,6,7,8,9,10};

        Digraph digraphTeste = new Digraph(10);

        digraphTeste.addEdge(1,1);
        digraphTeste.addEdge(2,9);
        digraphTeste.addEdge(3,6);
        digraphTeste.addEdge(9,3);
        digraphTeste.addEdge(5,6);
        System.out.println(digraphTeste.toString());
    }

    public static void testeWeightedGraph(){

        Edge e1 = new Edge(0,1,1);
        Edge e2 = new Edge(0,3,5);
        Edge e5 = new Edge(0,5,10);
        Edge e3 = new Edge(2,3,4);
        Edge e4 = new Edge(9,8,15);

        EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(10);
        edgeWeightedGraph.addEdge(e1);
        edgeWeightedGraph.addEdge(e2);
        edgeWeightedGraph.addEdge(e3);
        edgeWeightedGraph.addEdge(e4);
        edgeWeightedGraph.addEdge(e5);
        System.out.println(edgeWeightedGraph.toString());

    }
/*
    public static void testeEdgeWeightedDigraph(){
        DirectedEdge e1 = new DirectedEdge(0,1,1);
        DirectedEdge e2 = new DirectedEdge(0,3,5);
        DirectedEdge e5 = new DirectedEdge(0,5,10);
        DirectedEdge e3 = new DirectedEdge(2,3,4);
        DirectedEdge e4 = new DirectedEdge(9,8,15);
        DirectedEdge e6 = new DirectedEdge(3,8,15);
        DirectedEdge e7 = new DirectedEdge(4,8,15);
        DirectedEdge e8 = new DirectedEdge(4,3,15);
        DirectedEdge e9 = new DirectedEdge(6,2,15);
        EdgeWeightedDigraph ewdg = new EdgeWeightedDigraph(10);
        ewdg.addEdge(e1);
        ewdg.addEdge(e2);
        ewdg.addEdge(e3);
        ewdg.addEdge(e4);
        ewdg.addEdge(e5);
        ewdg.addEdge(e6);
        ewdg.addEdge(e7);
        ewdg.addEdge(e8);
        ewdg.addEdge(e9);
        System.out.println(ewdg.toString());
    }

    public static void main(String[] args) {
       // testeDigraph();
        //testeWeightedGraph();
        testeEdgeWeightedDigraph();
    }

 */
}
/*
class MST {
    // Number of vertices in the graph
    private static final int V = 5;

    public MST(EdgeWeightedGraph g) {
    }

    // A utility function to find the vertex with minimum key
    // value, from the set of vertices not yet included in MST
    int minKey(int key[], Boolean mstSet[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }

    // A utility function to print the constructed MST stored in
    // parent[]
    void printMST(int parent[], int graph[][])
    {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++)
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
    }

    // Function to construct and print MST for a graph represented
    // using adjacency matrix representation
    void primMST(EdgeWeightedDigraph graph)
    {
        // Array to store constructed MST
        int parent[] = new int[V];

        // Key values used to pick minimum weight edge in cut
        int key[] = new int[V];

        // To represent set of vertices included in MST
        Boolean mstSet[] = new Boolean[V];

        // Initialize all keys as INFINITE
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // Always include first 1st vertex in MST.
        key[0] = 0; // Make key 0 so that this vertex is
        // picked as first vertex
        parent[0] = -1; // First node is always root of MST

        // The MST will have V vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick thd minimum key vertex from the set of vertices
            // not yet included in MST
            int u = minKey(key, mstSet);

            // Add the picked vertex to the MST Set
            mstSet[u] = true;

            // Update key value and parent index of the adjacent
            // vertices of the picked vertex. Consider only those
            // vertices which are not yet included in MST
            for (int v = 0; v < V; v++)

                // graph[u][v] is non zero only for adjacent vertices of m
                // mstSet[v] is false for vertices not yet included in MST
                // Update the key only if graph[u][v] is smaller than key[v]
                if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
        }

        // print the constructed MST
        printMST(parent, graph);
    }

 */
/*
    public static void main(String[] args)
    {
		/* Let us create the following graph
		2 3
		(0)--(1)--(2)
		| / \ |
		6| 8/ \5 |7
		| /	 \ |
		(3)-------(4)
			9		 */
    /*
        MST t = new MST();
        /*
        int graph[][] = new int[][] { { 0, 2, 0, 6, 0 },
                { 2, 0, 3, 8, 5 },
                { 0, 3, 0, 0, 7 },
                { 6, 8, 0, 0, 9 },
                { 0, 5, 7, 9, 0 } };

         */
    /*
        DirectedEdge e1 = new DirectedEdge(0,1,1);
        DirectedEdge e2 = new DirectedEdge(0,3,5);
        DirectedEdge e5 = new DirectedEdge(0,5,10);
        DirectedEdge e3 = new DirectedEdge(2,3,4);
        DirectedEdge e4 = new DirectedEdge(9,8,15);
        DirectedEdge e6 = new DirectedEdge(3,8,15);
        DirectedEdge e7 = new DirectedEdge(4,8,15);
        DirectedEdge e8 = new DirectedEdge(4,3,15);
        DirectedEdge e9 = new DirectedEdge(6,2,15);
        EdgeWeightedDigraph ewdg = new EdgeWeightedDigraph(10);
        ewdg.addEdge(e1);
        ewdg.addEdge(e2);
        ewdg.addEdge(e3);
        ewdg.addEdge(e4);
        ewdg.addEdge(e5);
        ewdg.addEdge(e6);
        ewdg.addEdge(e7);
        ewdg.addEdge(e8);
        ewdg.addEdge(e9);

        // Print the solution
        t.primMST(ewdg);
    }



     */

class CHECKIFCONNECTED {
    static int N = 100000;

    // To keep correct and reverse direction
    @SuppressWarnings("unchecked")
    static Vector<Integer>[] gr1 = new Vector[N];
    @SuppressWarnings("unchecked")
    static Vector<Integer>[] gr2 = new Vector[N];

    static boolean[] vis1 = new boolean[N];
    static boolean[] vis2 = new boolean[N];

    static {
        for (int i = 0; i < N; i++)
        {
            gr1[i] = new Vector<>();
            gr2[i] = new Vector<>();
        }
    }

    // Function to add edges
    static void Add_edge(int u, int v)
    {
        gr1[u].add(v);
        gr2[v].add(u);
    }

    // DFS function
    static void dfs1(int x)
    {
        vis1[x] = true;
        for (int i : gr1[x])
            if (!vis1[i])
                dfs1(i);
    }

    // DFS function
    static void dfs2(int x)
    {
        vis2[x] = true;
        for (int i : gr2[x])
            if (!vis2[i])
                dfs2(i);
    }

    static boolean Is_connected(int n)
    {

        // Call for correct direction
        Arrays.fill(vis1, false);
        dfs1(1);

        // Call for reverse direction
        Arrays.fill(vis2, false);
        dfs2(1);

        for (int i = 1; i <= n; i++)
        {

            // If any vertex it not visited in any direction
            // Then graph is not connected
            if (!vis1[i] && !vis2[i])
                return false;
        }

        // If graph is connected
        return true;
    }

    // Driver Code
    public static void main(String[] args)
    {
        int n = 4;

        // Add edges
        Add_edge(1, 2);
        Add_edge(1, 3);
        Add_edge(2, 3);
        Add_edge(3, 4);

        // Function call
        if (Is_connected(n))
            System.out.println("Yes");
        else
            System.out.println("No");
    }
}

class aula_18_5_grafos {
    /**
     * calcula o MST do grafo
     * os ficheiros para ler a info dos grafos devemos por:
     * num edge
     * num vertix
     * conexao, peso da conexao
     */
    public static void ewd_ex1() {
        int s = 0, t = 6;
      //  Scanner in = new Scanner("C:\\Users\\sergi\\Desktop\\Projetos\\Projeto-AED2-LP2-08-05\\Projeto-AED2-LP2-2022\\Projeto-AED2-LP2-2022\\data\\ewd_g1.txt");
        In in = new In("C:\\Users\\sergi\\Desktop\\Projetos\\Projeto-AED2-LP2-08-05\\Projeto-AED2-LP2-2022\\Projeto-AED2-LP2-2022\\data\\ewd_g1.txt");
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(in);
        System.out.println("Grafo: \n" + g);
        System.out.println("SP from: " + s + " to " + t + ":\n");
        DijkstraSP sp = new DijkstraSP(g, s);
        /*
        //AQUI CALCULA O CAMINHO MAIS CURTO
        for (DirectedEdge e : sp.pathTo(t)) {
            //  System.out.println(e);
            StdOut.println(e);
        }
         */
        // AQUI CALCULA O CAMINHO MAIS CURTO DE TODOS PARA TODOS
        for (int v = 0; v<g.V(); v++){
            StdOut.println(v + ": (" + sp.distTo(v) + ")");
            for (DirectedEdge e : sp.pathTo(v)) {
                //  System.out.println(e);
                StdOut.println(e);
            }
            StdOut.println();
        }
        // System.out.println("Cost: " + sp.distTo(t));
        StdOut.println("Cost: " + sp.distTo(t));
    }

    public static void ewd_ex2() {
        In in = new In("C:\\Users\\sergi\\Desktop\\Projeto-LP2-AED2-JAVAFX\\data\\ewd_g1.txt");
        EdgeWeightedDigraph g1 = new EdgeWeightedDigraph(in);
        System.out.println("Grafo: \n" + g1.toString());
    }

    public static void check_conexo(){
        Digraph g = new Digraph(10);
        int[] vertex = new int[] {0,1,2,3,4,5,6,7,8,9};
        g.addEdge(3,7);
        g.addEdge(1,4);
        g.addEdge(7,8);
        g.addEdge(0,5);
        g.addEdge(5,2);
        g.addEdge(3,8);
        g.addEdge(2,9);
        g.addEdge(0,6);
        g.addEdge(4,9);
        g.addEdge(2,6);
        g.addEdge(6,4);

        ST<Integer, ArrayList<Integer>> closureArray1 = new ST<>();

        TransitiveClosure closure = new TransitiveClosure(g);

        for (int i : vertex){ // corre todos os vertices
            ArrayList<Integer>temp = new ArrayList<>(); //sao guardados em array temp
            for (int j : vertex){   //corre denovo
                if (i!=j){
                    if (closure.reachable(i,j)){ // verifica se eles estao conectados
                        temp.add(j);
                        System.out.println("verifica que eles estao conectados");
                    }
                }
            }
            closureArray1.put(i,temp); //ao estar conectados guarda na ST
            System.out.println("adicionou a st");
        }
        System.out.println("grafo conectado");
        System.out.println(closureArray1.toString());
    }


    public static void main(String[] args) {
        ewd_ex1();
      //  check_conexo();
      //  ewd_ex2();
    }
}

