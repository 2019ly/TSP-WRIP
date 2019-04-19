package TSP.order;

import TSP.algorithm.ConstructionAlgorithm;

public interface OrderGenerator extends Iterable<Integer>{
    void setAlgorithm(ConstructionAlgorithm algorithm);
    ConstructionAlgorithm getAlgorithm();
    OrderGenerator getInstance();
}
