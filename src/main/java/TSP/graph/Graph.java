package TSP.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Graph {
    ArrayList<Map<Integer, Integer>> graph;
    Map<String, String> properties;

    public int getVertexCount() {
        return graph.size();
    }

    public Graph() {
        properties = new HashMap<>();
        graph = new ArrayList<>();
    }

    public int getCost(int v1, int v2) {
        if (v1 == v2) {
            return 0;
        }
        Integer result = graph.get(v1).get(v2);
        if (result == null) {
            return Integer.MAX_VALUE;
        }
        return result;
    }

    public void setCost(int v1, int v2,int cost) {
        return;
    }

    public String getProperty(String name) {
        return properties.get(name);
    }

    public Collection<String> getPropertyNames() {
        return properties.keySet();
    }


}
