package TSP.algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Record {
    Map<Integer, List<Route>> map;
    long beginTime;
    int bound;
    boolean success;

    public Record() {
        this.map = new HashMap<>();
        beginTime = System.currentTimeMillis();
        success=false;
    }

    public void setBound(int bound) {
        this.bound = bound;
    }

    public boolean contains(Route route) {
        synchronized (this) {
            if (containsLength(route.getLength()) && contains(get(route.getLength()), route)) {
                return true;
            }
            return false;
        }
    }

    public List<Route> get(int length) {
        synchronized (this) {
            return map.get(length);
        }
    }

    public boolean containsLength(Integer length) {
        synchronized (this) {
            if (map.containsKey(length) && map.get(length) != null) {
                return true;
            }
            return false;
        }
    }

    public boolean contains(List<Route> list, Route route) {
        synchronized (this) {
            for (Route r : list) {
                if (equals(r, route)) {
                    return true;
                }
            }
            return false;
        }
    }

    public void add(Route route) {
        synchronized (this) {
            int length = route.getLength();
            if (!containsLength(length)) {
                map.put(length, new LinkedList<>());

            }
            List<Route> list = map.get(length);
            if (!contains(list, route)) {
                list.add(route);
            }
        }
    }

    public int getOptimaLength() {
        synchronized (this) {
            int ol = Integer.MAX_VALUE;
            for (int length : map.keySet()) {
                if (length < ol) {
                    ol = length;
                }
            }
            return ol;
        }
    }

    public List<Route> getOptima() {
        synchronized (this) {
            return get(getOptimaLength());
        }
    }

    public static boolean equals(Route r1, Route r2) {
        return absoluteEquals(r1.vertexSequence, r2.vertexSequence);

    }

    public static boolean absoluteEquals(List<Integer> list1, List<Integer> list2) {
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i) != list2.get(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean equals(List<Integer> list1, List<Integer> list2) {
        int j = 0;
        for (int i = 0; i < list2.size(); i++) {
            if (list1.get(0) == list2.get(i)) {
                j = i;
                break;
            }
        }
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i) != list2.get(j)) {
                return false;
            }
            j = (j + 1) % list2.size();
        }
        return true;
    }

    public boolean isSuccess(Route route) {
        if(success){
            return true;
        }
        if(route!=null&&route.getLength()<=bound){
            success=true;
            return true;
        }
        return false;
    }
}
