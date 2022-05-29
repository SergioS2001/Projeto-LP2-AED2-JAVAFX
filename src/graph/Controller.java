package graph;

import Projeto.BD;
import Projeto.Nodes;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class Controller {

    protected static final int GROUP_MARGIN = 20;

    public static int edgesField;
    public static int verticesNumberField;
    private GeoGraph gG;
    public Group graphGroup;
    private double radius = 20;

    private static final String FILE_DELIMETER = ";";

    static EdgeWeightedDigraph edgeWeightedDiGraph = new EdgeWeightedDigraph(verticesNumberField);


    public void readGraphFromFile(String path) {
        In in = new In(path);
        verticesNumberField = Integer.parseInt(in.readLine());
        edgesField = Integer.parseInt(in.readLine());

        try{
            while (!in.isEmpty()) {
                String line = in.readLine();
                String[] fields = line.split(FILE_DELIMETER);

                DirectedEdge e1 = new DirectedEdge(Integer.parseInt(fields[0]),Integer.parseInt(fields[1]),Double.parseDouble(fields[2]));//Integer.parseInt(fields[2]));

                edgeWeightedDiGraph.addEdge(e1);
            }
            createGraphGroup();
        }catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
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
    public void createGraphGroup(){

        graphGroup.getChildren().clear();
        readNodesFromFile("C:\\Users\\sergi\\Desktop\\Projeto-LP2-AED2-JAVAFX\\data\\nodes.txt");
        for(int i=0; i <gG.V(); i++){
            Nodes n = BD.nodesST.get(Nodes.pesquisar_node(i));
            Circle c = new Circle(n.x,n.y, radius);
            c.setFill(Color.WHITE);

            StackPane stack = new StackPane();
            stack.setLayoutX(gG.getVertexPosX((int) n.x)- radius);
            stack.setLayoutY(gG.getVertexPosY((int)n.y) - radius);
            stack.getChildren().addAll(c, new Text(i + ""));

            graphGroup.getChildren().add(stack);

            if(gG.E() > 0){
                for(Integer adj: gG.adj(i)) {
                    Line line = new Line(gG.getVertexPosX((int) n.x),gG.getVertexPosY((int)n.y), gG.getVertexPosX(adj), gG.getVertexPosY(adj));
                    graphGroup.getChildren().add(line);
                }
            }
        }
    }

    private void createNewGraph(int nVertices){
        if(gG == null)
            gG = new GeoGraph(nVertices);
        else
            gG = new GeoGraph(gG, nVertices);
    }

   /* private void createNewDiGraph(int nVertices){
        if(gG == null)
            gG = new EdgeWeightedDigraph(verticesNumberField);
        else
            gG = new EdgeWeightedDigraph(verticesNumberField, edgesField);
    }*/






    public void handleVerticesButtonAction(ActionEvent actionEvent) {
        try {
            readGraphFromFile("C:\\Users\\sergi\\Desktop\\Projeto-LP2-AED2-JAVAFX\\data\\ewd_g1.txt");
        }catch(NumberFormatException e){
            System.out.println("Error: Vertices not inserted!");
        }
    }

    /*public void handleEdgesButtonAction(ActionEvent actionEvent) {
        try {
            if (gG != null)
                gG = new EdgeWeightedDigraph(gG);
            else
                createNewDiGraph(verticesNumberField);

            //String[] lines = edgesField.getText().split("\n");
            for (int i=0;i<edgesField;i++) {
                //String[] position = line.split(";");
                int v = edgeWeightedDiGraph.V();
                int adj = edgeWeightedDiGraph.adj();

                if (!gG.containsEdge(v, adj))
                    gG.addEdge(Integer.parseInt(position[0]), Integer.parseInt(position[1]));
            }
            createGraphGroup();
        } catch(NumberFormatException e) {
            System.out.println("Error: Edges or Vertices not inserted!");
        }
    }*/

    /*public void handleClearButtonAction(ActionEvent actionEvent) {
        graphGroup.getChildren().clear();
        edgesField.setText("");
        verticesNumberField.setText("");
        gG = null;
    }*/
}
