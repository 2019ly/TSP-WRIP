package TSP.algorithm;


import TSP.graph.Graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Route {
    private Graph graph;
    public List<Integer> vertexSequence;
    private boolean isModified;
    private int length;
//    private List<Route> routes;
    List<int[]> edges;

    public Route(Graph graph) {
        this.graph = graph;
        vertexSequence = new ArrayList<>(graph.getVertexCount());
    }

    public Route(Graph graph, Iterable<Integer> vertices) {
        this(graph);
        for (int v : vertices) {
            vertexSequence.add(v);
        }
        isModified = true;
    }

    public Route(Graph graph, int[] vertices) {
        this(graph);
        for (int v : vertices) {
            vertexSequence.add(v);
        }
        isModified = true;
    }

    public List<int[]> getEdges() {
        if (edges == null) {
            edges = new ArrayList<>(vertexSequence.size());
            for (int i = 0; i < vertexSequence.size(); i++) {
                edges.add(new int[]{vertexSequence.get(i), vertexSequence.get((i + 1) % vertexSequence.size())});
            }
        }
        return edges;
    }

    public Route insert(int vertexIndex, int position) {
        Route result = new Route(graph);
        result.length = getLength();
        result.length += graph.getCost(vertexIndex, vertexSequence.get(position));
        if (position == 0) {
            result.length += graph.getCost(vertexIndex, vertexSequence.get(vertexSequence.size() - 1));
            result.length -= graph.getCost(vertexSequence.get(position), vertexSequence.get(vertexSequence.size() - 1));
        } else {
            result.length += graph.getCost(vertexIndex, vertexSequence.get(position - 1));
            result.length -= graph.getCost(vertexSequence.get(position), vertexSequence.get(position - 1));
        }
        for (int i = 0; i < this.vertexSequence.size(); i++) {
            if (i == position) {
                result.vertexSequence.add(vertexIndex);
            }
            result.vertexSequence.add(this.vertexSequence.get(i));
        }
        result.isModified = false;
        return result;
    }

    public Collection<Route> insert(int vertexIndex) {
//        if(routes!=null){
//            return routes;
//        }
        List<Route> routes = new LinkedList<>();
        for (int i = 0; i < vertexSequence.size(); i++) {
            routes.add(insert(vertexIndex, i));
        }
        return routes;
    }
    public Route insert(int[] edge, int vertexIndex){
        int position=0;
        for(int i = 0; i < this.vertexSequence.size(); i++){
            if(vertexSequence.get(i)==edge[0]&&vertexSequence.get((i+1)%vertexSequence.size())==edge[1]){
                position=i;
                break;
            }
            if(vertexSequence.get(i)==edge[1]&&vertexSequence.get((i+1)%vertexSequence.size())==edge[0]){
                position=i;
                break;
            }
        }
        return insert(vertexIndex,position);
    }

    public int getVertexCount() {
        return vertexSequence.size();
    }

    public int getLength() {
        if (isModified) {
            this.length = 0;
            Integer vp = null;
            for (Integer v : vertexSequence) {
                if (vp != null) {
                    this.length += graph.getCost(vp, v);
                }
                vp = v;
            }
            this.length += graph.getCost(vp, vertexSequence.get(0));
        }
        isModified = false;
        return this.length;
    }

}
