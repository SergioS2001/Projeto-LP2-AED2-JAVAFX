package Projeto.search;

import edu.princeton.cs.algs4.StdDraw;
import java.awt.*;

public class Aed2Plot {
    private static final int INTERVALS_X = 10;
    private static final int INTERVALS_Y = 10;
    private static final double halfWidth = .47;
    private static final double halfHeight = .47;
    private static final double graphAreaCenterX = .51;
    private static final double graphAreaCenterY = .51;
    private static final double circleRadius = .003;
    private static final int fontSize = 10;
    private static final int canvasWidth = 512;
    private static final int canvasHeight = 512;
    private static double xmin = 0; //default
    private static double xmax = 10; //default
    private static double ymin = 0; //default
    private static double ymax = 10; //default

    public static void init_plot(double xmin, double xmax, double ymin, double ymax) {
        Aed2Plot.xmin = xmin;
        Aed2Plot.xmax = xmax;
        Aed2Plot.ymin = ymin;
        Aed2Plot.ymax = ymax;
        StdDraw.setCanvasSize(canvasWidth,canvasHeight);
        StdDraw.rectangle(graphAreaCenterX,graphAreaCenterY,halfWidth,halfHeight);
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, fontSize));
        double stepw = halfWidth*2/INTERVALS_X;
        double stepx = (xmax-xmin)/INTERVALS_X;
        for (int i = 0; i < INTERVALS_X; i++) {
            int x = (int)(stepx*i + xmin);
            double w = stepw*i + (graphAreaCenterX-halfWidth);
            StdDraw.text(w,(graphAreaCenterY-halfHeight)/2, Integer.toString(x));
        }
        double steph = halfHeight*2/INTERVALS_Y;
        double stepy = (ymax-ymin)/INTERVALS_Y;
        for (int i = 0; i < INTERVALS_Y; i++) {
            int y = (int)(stepy*i + ymin);
            double h = steph*i + (graphAreaCenterY-halfHeight);
            StdDraw.text((graphAreaCenterX-halfWidth)/2,h, Integer.toString(y));
        }
    }

    public static void plot(double xvals[], double yvals[], Color color) {
        //assert (xvals.length == yvals.length);
        for (int i = 0; i < xvals.length - 1; i++) {
            double x = (halfWidth*2)*(xvals[i] - xmin)/(xmax - xmin)+(graphAreaCenterX-halfWidth);
            double y = (halfHeight*2)*(yvals[i] - ymin)/(ymax - ymin)+(graphAreaCenterY-halfHeight);
            StdDraw.setPenColor(color);
            StdDraw.filledCircle(x,y,circleRadius);
        }
    }

    public static void clear(){
        StdDraw.clear();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double xx[] = {0,1,2,3,4,5,6,7,8,9,10};
        double yy[] = {0,10,25,30,23,20,25,20,35,30,40};
        double xmin=0, xmax=10, ymin=0, ymax=40;
        System.out.println("Exemplo Aed2Plot (begin)");
        init_plot(xmin,xmax,ymin,ymax);
        plot(xx,yy,Color.BLUE);
        System.out.println("Exemplo Aed2Plot (end)");
    }
}