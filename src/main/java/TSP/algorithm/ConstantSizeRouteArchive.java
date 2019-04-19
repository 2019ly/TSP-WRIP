package TSP.algorithm;

import TSP.graph.Graph;

import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class ConstantSizeRouteArchive implements RouteArchive {
    Graph graph;
    Route[][] map;
    Route optima;
    Set<Route> routes;
    public LinkedList<int[]> keys;

    public Set<Route> getRoutes() {
        if (routes != null) {
            return routes;
        }
        routes = new HashSet<>((int) (keys.size() / 0.75));
        for (int[] key : keys) {
            routes.add(get(key[0], key[1])[0]);
        }
        return routes;
    }

    private ConstantSizeRouteArchive(Graph graph) {
        this.graph = graph;
        this.map = new Route[graph.getVertexCount()][];
        this.keys = new LinkedList<>();
    }

    public ConstantSizeRouteArchive(Graph graph, int v0, int v1, int v2) {
        this(graph);
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
            map[min] = new Route[graph.getVertexCount()];
        }
        if (map[min][max] == null) {
            this.keys.add(new int[]{min, max});
            map[min][max] = route;
        } else if (route.getLength() <= map[min][max].getLength()) {
            map[min][max] = route;
        }
    }

    public void put(Route r) {
        synchronized (this) {
            for (int[] edge : r.getEdges()) {
                if (get(edge[0], edge[1]) == null || r.getLength() < get(edge[0], edge[1])[0].getLength()) {
                    put(edge[0], edge[1], r);
                }

            }
        }

    }

    public Route[] get(int v1, int v2) {
        synchronized (this) {
            int max = max(v1, v2);
            int min = min(v1, v2);
            if (map[min] == null || map[min][max] == null) {
                return null;
            }
            return new Route[]{map[min][max]};
        }
    }

    public ConstantSizeRouteArchive insert(int i) {
        ConstantSizeRouteArchive result = new ConstantSizeRouteArchive(graph);
        for (Route route : getRoutes()) {
            for (Route r : route.insert(i)) {
                result.put(r);
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
        for (Route r : getRoutes()) {
            if (optima == null || r.getLength() < optima.getLength()) {
                optima = r;
            }
        }
        return optima;
    }
}
