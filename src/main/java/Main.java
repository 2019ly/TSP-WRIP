import TSP.algorithm.MultiThreadAlgorithm;
import TSP.algorithm.Record;
import TSP.algorithm.Route;
import TSP.graph.Graph;
import TSP.graph.TSPParser;
import TSP.graph.TspXmlParser;
import TSP.order.OrderGenerator;
import TSP.order.RandomOrder;
import TSP.util.PropertiesUtil;
import TSP.viewer.Viewer;
import org.dom4j.DocumentException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, DocumentException {
        PropertiesUtil propertiesUtil= new PropertiesUtil(new File("setting.properties"));
        String name = propertiesUtil.getName();
        int knownOptimum = propertiesUtil.getKnownOptimum();
        int time = propertiesUtil.getTime();
        int threadNum=propertiesUtil.getThreadNum();
        String dir = propertiesUtil.getInstanceDir();
        solve(dir, name, knownOptimum, time,threadNum);
    }

    public static void solve(String path, String name, int knownOptimum, int time,int threadNum) throws FileNotFoundException, DocumentException {
        TspXmlParser parser = new TspXmlParser(path +"/"+ name + ".xml");
        TSPParser tspparser = new TSPParser(path+"/" + name + ".tsp");
        System.out.println(name);
        Graph graph = parser.generateGraph();
        for (int i = 0; i < time; i++) {
            System.out.println(i + 1 + "#####################################");
            MultiThreadAlgorithm multiSeedAlgorithm = new MultiThreadAlgorithm();
            OrderGenerator orderGenerator = new RandomOrder();
            long beginTime = System.currentTimeMillis();
            Record record = multiSeedAlgorithm.excecute(graph, orderGenerator, threadNum, knownOptimum);
            System.out.println("time: " + (System.currentTimeMillis() - beginTime)/1000.0+"s");
            System.out.println("optima: " + record.getOptimaLength());
            Route optima = record.getOptima().get(0);
            try {
                Viewer.view(tspparser.coordinates, optima.vertexSequence);
            } catch (Exception e) {
                System.out.println("No coordinate");
            }
        }
    }
}
