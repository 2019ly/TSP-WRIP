package TSP.algorithm;

import TSP.graph.Graph;
import TSP.util.RandomUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class DynamicSizeRouteArchive implements  RouteArchive{
    Graph graph;
    Route[][][] map;
    Route optima;
    private int blockSize = 1;

    public LinkedList<int[]> keys;

    private DynamicSizeRouteArchive(int blockSize, Graph graph) {
        this.graph = graph;
        this.map = new Route[graph.getVertexCount()][][];
        this.keys = new LinkedList<>();
        this.blockSize=blockSize;
    }

    public DynamicSizeRouteArchive(int blockSize, Graph graph, int v0, int v1, int v2) {
        this(blockSize,graph);
        List<Integer> vertices = new ArrayList<>(3);
        vertices.add(v0);
        vertices.add(v1);
        vertices.add(v2);
        Route route = new Route(graph, vertices);
        put(v0, v1, route);
        put(v1, v2, route);
        put(v2, v0, route);
    }

    public void put(int v1, int v2, Route route) {
        int max = max(v1, v2);
        int min = min(v1, v2);
        if (map[min] == null) {
            map[min] = new Route[graph.getVertexCount()][];
        }
        if (map[min][max] == null) {
            this.keys.add(new int[]{min, max});
            map[min][max] = new Route[blockSize];
        }
        List<Route> list = new ArrayList<>(blockSize);
        for (Route r : map[min][max]) {
            if (r != null) {
                list.add(r);
            }
        }
        if (list.size() <blockSize) {
            list.add(route);
        } else {
            int i= RandomUtil.RandomInt(list.size());
            if(route.getLength()<=list.get(i).getLength()){
                list.set(i, route);
            }
        }
        int i = 0;
        for (Route r : list) {
            map[min][max][i] = r;
            i++;
        }
    }

    private void put(Route r) {
        for (int[] edge : r.getEdges()) {
            put(edge[0], edge[1], r);
        }
    }

    public Route[] get(int v1, int v2) {
        int max = max(v1, v2);
        int min = min(v1, v2);
        if (map[min] != null) {
            return map[min][max];
        }
        return null;
    }

    public DynamicSizeRouteArchive insert(int i) {
        DynamicSizeRouteArchive result = new DynamicSizeRouteArchive(blockSize,graph);
        for (int[] key : keys) {
            Route[] routes = get(key[0], key[1]);
            for (Route route : routes) {
                if(route!=null){
                    for (Route r : route.insert(i)) {
                        result.put(r);
                    }
                }
            }
        }
        return result;
    }


    @Override
    public List<int[]> getKeys() {
        return keys;
    }

    public Route getOptima() {
        if (optima != null) {
            return optima;
        }
        for (int[] key : keys) {
            Route[] routes = get(key[0], key[1]);
            for (Route r : routes) {
                if (optima == null || r.getLength() < optima.getLength()) {
                    optima = r;
                }
            }
        }
        return optima;
    }
}
