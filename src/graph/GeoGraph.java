package graph;

import edu.princeton.cs.algs4.Graph;

import java.util.Random;

import static graph.Controller.GROUP_MARGIN;


public class GeoGraph extends Graph {

    private int[] positionsX;
    private int[] positionsY;

    public GeoGraph(GeoGraph gG) {
        super(gG.V());
        positionsX = gG.positionsX;
        positionsY = gG.positionsY;
    }

    public GeoGraph(int nVertices) {
        super(nVertices);

        positionsX = new int[nVertices];
        positionsY = new int[nVertices];

        setRandomPositions(0);

    }

    public GeoGraph(GeoGraph gG, int newSize) {
        super(newSize);
        positionsX = new int[newSize];
        positionsY = new int[newSize];

        for(int i=0; i<gG.V() && i<newSize; i++){
            positionsX[i] = gG.positionsX[i];
            positionsY[i] = gG.positionsY[i];
        }

        setRandomPositions(gG.V());

        for(int v=0; v<gG.V(); v++){
            for(Integer adj: gG.adj(v)){
                this.addEdge(v,adj);
            }
        }
    }

    private void setVertexPosition(int vertexIDx, int x, int y){
        positionsX[vertexIDx] = x;
        positionsY[vertexIDx] = y;
    }

    public int getVertexPosX(int vertexIDx) { return positionsX[vertexIDx];}

    public int getVertexPosY(int vertexIDx){ return positionsY[vertexIDx];}

    private void setRandomPositions(int pos){
        for(int i=pos; i<this.V(); i++){
            Random r = new Random();
            positionsX[i] = (int) (GROUP_MARGIN + r.nextDouble() * (600-GROUP_MARGIN*2));
            positionsY[i] = (int) (GROUP_MARGIN + r.nextDouble() * (371-GROUP_MARGIN*2));
        }
    }

    public boolean containsEdge(int v, int a){
        for(Integer adj: this.adj(v))
            if(adj == a) return true;

        for(Integer adj: this.adj(a))
            if(adj == v) return true;

        return false;
    }
}
