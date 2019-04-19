package TSP.algorithm;

import TSP.graph.Graph;

import java.util.List;

public interface RouteArchive {

    static RouteArchive instance(int blockSize, Graph graph, int v0, int v1, int v2) {
        if (blockSize <= 1) {
            return new ConstantSizeRouteArchive(graph, v0, v1, v2);
        }
        return new DynamicSizeRouteArchive(blockSize, graph, v0, v1, v2);
    }

    RouteArchive insert(int v);

    //    List<Integer> getVertex();
    Route[] get(int v1, int v2);

    List<int[]> getKeys();

    Route getOptima();
}
