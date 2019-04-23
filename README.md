## TSP-WRIP

### Overview
This project aims to implement a heuristic TSP solver.

### Project structure
<pre><code>.
├── instances/: TSP instances (TSPLib format)
├── setting.propertis: the configuration file(a configuration file used to specify the instance directory, the instance name, and the known optimal solution cost)
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
    │   └── viewer/: Visualize the solutions
    │       └── Viewer.java
    └── Main.java (launcher of this software)
</code></pre>
