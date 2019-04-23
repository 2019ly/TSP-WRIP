## TSP-WRIP

### Overview
This program is for the submitted paper "A Heuristic Algorithm Based on Tour Rebuilding Operator for the Travelling Salesman Problem" to IJCAI2019. We welcome your reviews and comments.

TSP (Traveling Salesman Problem), a classic NPcomplete problem in combinatorial optimization, is of great significance in multiple fields.This project aims to implement a heuristic TSP solver.We propose a heuristic algorithm based on tour rebuilding operator for TSP. Instead of only adjusting the tour to some extent as in the k-optimal operator and the inver-over operator, this operator rebuilds the tour (T) according to the point sequence of T point by point. Thus, the rebuilt T significantly differs from the original T and the probability of being trapped into the local optima is greatly reduced accordingly. Yet what is different from tour construction operators is that our method guarantees the cost of the rebuilt T to be no larger than the original T before the rebuilding. It consequently enables the rebuilding operator to be used as the improve operator and thus increase the quality of the solutions continuously.

We apply our method to solve all the 40 instances with no more than 150 points in TSPLIB1 which is a prestigious library of TSP instances. We refresh the best known optimum of 22 instances and find the best known optimum of the other 18 instances.

### Project structure
<pre><code>.
├── instances/: TSP instances (TSPLib format)
├── setting.properties: the configuration file(a configuration file used to specify the instance directory, the instance name, and the known optimal solution cost)
└── src/main/java/: source code
    ├── TSP
    │   ├── algorithm/: main algorithm of the TSP solver
    │   │   ├── Algorithm.java
    │   │   ├── ConstantSizeRouteArchive.java
    │   │   ├── ConstructionAlgorithm.java
    │   │   ├── DynamicSizeRouteArchive.java
    │   │   ├── MultiThreadAlgorithm.java
    │   │   ├── Record.java
    │   │   ├── Route.java
    │   │   └── RouteArchive.java
    │   ├── graph/: graph of TSP
    │   │   ├── Graph.java
    │   │   ├── TSPParser.java
    │   │   └── TspXmlParser.java
    │   ├── order/: controls the order of insertion points
    │   │   ├── ConstantOrder.java
    │   │   ├── OrderGenerator.java
    │   │   └── RandomOrder.java
    │   ├── parallel/: implements the multithreaded strategy
    │   │   └── AlgorithmTask.java
    │   ├── util/: utility classes
    │   │   ├── PropertiesUtil.java
    │   │   └── RandomUtil.java
    │   └── viewer/: visualize the solutions
    │       └── Viewer.java
    └── Main.java (launcher of this software)
</code></pre>
