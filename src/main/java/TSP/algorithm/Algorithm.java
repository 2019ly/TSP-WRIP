package TSP.algorithm;

import TSP.graph.Graph;
import TSP.order.OrderGenerator;

import java.util.Iterator;

public class Algorithm implements ConstructionAlgorithm {
    Graph graph;
    RouteArchive archive;
    public Graph getGraph() {
        return this.graph;
    }

    public RouteArchive getArchive(){
        return this.archive;
    }

    public ConstructionAlgorithm getInstance(){
        return new Algorithm();
    }
    @Override
    public Route execute(Graph graph, OrderGenerator orderGenerator) {
        this.graph = graph;
        orderGenerator.setAlgorithm(this);
        int[] order = new int[graph.getVertexCount()];

        Iterator<Integer> it = orderGenerator.iterator();
        for (int i = 0; i < 3; i++) {
            order[i] = it.next();
        }
//        System.out.print("order: " + order[0] + ", " + order[1] + ", " + order[2]);
        archive = RouteArchive.instance(1,graph, order[0], order[1], order[2]);
        int id = 3;
        while (it.hasNext()) {
            order[id] = it.next();
//            System.out.print(", " + order[id]);
            archive = archive.insert(order[id]);
            id++;
        }
        Route optima=archive.getOptima();
        return optima;
    }

}
