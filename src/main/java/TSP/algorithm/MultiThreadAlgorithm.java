package TSP.algorithm;

import TSP.Parallel.AlgorithmTask;
import TSP.graph.Graph;
import TSP.order.OrderGenerator;

import java.util.concurrent.ForkJoinPool;

public class MultiThreadAlgorithm {

    int id=0;
    public Record excecute(Graph graph, OrderGenerator orderGenerator, int threadNum, Integer bound){
        ConstructionAlgorithm algorithm = new Algorithm();
        Record record = new Record();
        record.setBound(bound);
        ForkJoinPool pool = new ForkJoinPool(threadNum);
        pool.invoke(new AlgorithmTask(this,graph,algorithm,orderGenerator,record,threadNum,System.currentTimeMillis()));
        return record;
    }

    public int generateId(){
        return id++;
    }

}
