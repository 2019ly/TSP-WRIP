package TSP.viewer;

import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;

public class Viewer {
    public static void view(List<double[]> coordinates, List<Integer> vertices){

        List<double[]> vcdns=new ArrayList<>(coordinates.size());
        double[] xBounds=new double[]{coordinates.get(0)[0],coordinates.get(0)[0]};
        double[] yBounds=new double[]{coordinates.get(0)[1],coordinates.get(0)[1]};
        for(double[] cdn:coordinates){
            xBounds[0]=Math.min(cdn[0],xBounds[0]);
            xBounds[1]=Math.max(cdn[0],xBounds[1]);
            yBounds[0]=Math.min(cdn[1],yBounds[0]);
            yBounds[1]=Math.max(cdn[1],yBounds[1]);
        }
        double xSize=(xBounds[1]-xBounds[0])*1.05;
        double ySize=(yBounds[1]-yBounds[0])*1.05;
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius(0.01);
        for(double[] cdn:coordinates){
            double x=(cdn[0]-xBounds[0])/xSize+0.025;
            double y= (cdn[1]-yBounds[0])/ySize+0.025;

            vcdns.add(new double[]{x,y});
            StdDraw.point(x,y);
//            StdDraw.setPenColor(StdDraw.BLUE);
        }
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.003);
        for(int i=0;i<vertices.size();i++){
            int id1=vertices.get(i);
            int id2=vertices.get((i+1)%vcdns.size());
            StdDraw.line(vcdns.get(id1)[0],vcdns.get(id1)[1],vcdns.get(id2)[0],vcdns.get(id2)[1]);
        }

    }
}
