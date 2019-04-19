package TSP.order;

import TSP.algorithm.ConstructionAlgorithm;

import java.util.Iterator;
import java.util.List;

public class ConstantOrder implements OrderGenerator {
    private ConstructionAlgorithm algorithm;
    int[] arr;
    public ConstantOrder(int[] arr){
        this.arr=arr;
    }
    public ConstantOrder(List<Integer> list){
        this.arr=new int[list.size()];
        for(int i=0;i<arr.length;i++){
            arr[i]=list.get(i);
        }
    }
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
        return new ConstantOrder(this.arr);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int i=0;
            @Override
            public boolean hasNext() {
                return i<arr.length;
            }

            @Override
            public Integer next() {
                int result=arr[i++];

                return result;
            }
        };
    }

}
