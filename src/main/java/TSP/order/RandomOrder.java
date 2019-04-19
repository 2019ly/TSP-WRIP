package TSP.order;

import TSP.algorithm.ConstructionAlgorithm;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class RandomOrder implements OrderGenerator {
    private ConstructionAlgorithm algorithm;
    @Override
    public void setAlgorithm(ConstructionAlgorithm algorithm) {
        this.algorithm=algorithm;
    }

    @Override
    public ConstructionAlgorithm getAlgorithm() {
        return this.algorithm;
    }

    @Override
    public OrderGenerator getInstance() {
        return new RandomOrder();
    }

    @Override
    public Iterator<Integer> iterator() {
        Random random= new Random();
        LinkedList<Integer> list= new LinkedList<>();
        for(int i=0;i<algorithm.getGraph().getVertexCount();i++){
            list.add(random.nextInt(list.size()+1),i);
        }
        return list.iterator();
    }
}
