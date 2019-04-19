package TSP.algorithm;

import TSP.graph.Graph;
import TSP.order.OrderGenerator;

public interface ConstructionAlgorithm {
     Graph getGraph();
     Route execute(Graph graph, OrderGenerator orderGenerator);
     ConstructionAlgorithm getInstance();
}
