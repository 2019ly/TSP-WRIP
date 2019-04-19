package TSP.Parallel;

import TSP.algorithm.Route;
import TSP.algorithm.ConstructionAlgorithm;
import TSP.algorithm.MultiThreadAlgorithm;
import TSP.algorithm.Record;
import TSP.graph.Graph;
import TSP.order.ConstantOrder;
import TSP.order.OrderGenerator;

import java.util.concurrent.RecursiveAction;

public class AlgorithmTask extends RecursiveAction {
    Graph graph;
    int num;
    ConstructionAlgorithm algorithm;
    OrderGenerator orderGenerator;
    Record record;
    MultiThreadAlgorithm handle;
    long beginTime;
    int id=0;


    public AlgorithmTask(MultiThreadAlgorithm handle, Graph graph, ConstructionAlgorithm algorithm, OrderGenerator orderGenerator, Record record, int num, long beginTime) {
        this.handle=handle;
        this.graph = graph;
        this.algorithm = algorithm;
        this.record = record;
        this.orderGenerator = orderGenerator;
        this.num = num;
        this.beginTime = beginTime;
    }

    @Override
    protected void compute() {
        if (num < 2) {
            id=handle.generateId();
            Route route = null;
            do {
                if (record.isSuccess(route)) {
                    break;
                }
                route = algorithm.execute(graph, orderGenerator);
                if (record.contains(route)) {
                    break;
                }
                record.add(route);
                orderGenerator = new ConstantOrder(route.vertexSequence);
                int length = route.getLength();
                System.out.println("thread: "+id+"   cost: " + length + "   time:" + (System.currentTimeMillis() - beginTime)/1000.0+"s");
            } while (true);
        } else {
            AlgorithmTask task1 = new AlgorithmTask(handle,graph, algorithm.getInstance(), orderGenerator.getInstance(), record, num / 2, beginTime);
            AlgorithmTask task2 = new AlgorithmTask(handle,graph, algorithm.getInstance(), orderGenerator.getInstance(), record, num - num / 2, beginTime);
            invokeAll(task1, task2);
        }
    }
}
