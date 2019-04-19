package TSP.graph;

import java.io.*;
import java.util.*;

public class TSPParser {
    Map<String, String> properties = new HashMap<String, String>();
    public List<double[]> coordinates;
    private Scanner scanner;

    public TSPParser(String path) throws FileNotFoundException {
        this(new File(path));
    }

    public TSPParser(File file) throws FileNotFoundException {
        this(new FileInputStream(file));
    }

    public TSPParser(InputStream in) {
        scanner = new Scanner(new BufferedReader(new InputStreamReader(in), 1024 * 5));
        loadData(scanner);
    }

    private void loadData(Scanner scanner) {
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
//            System.out.println(line);
            if (line.contains(":")) {
                String[] entry = line.split(":");
                properties.put(entry[0].trim(), entry[1].trim());
            } else if (line.trim().equals("DISPLAY_DATA_SECTION") || line.trim().equals("NODE_COORD_SECTION")) {
                this.coordinates = loadCoordinate(scanner);
            }
        }
    }

    public List<double[]> loadCoordinate(Scanner scanner) {
        List<double[]> coordinates = new ArrayList<double[]>();
        while(scanner.hasNext()){
            try{
            int id= scanner.nextInt()-1;
            double[] cdn= new double[2];
            cdn[0] = scanner.nextDouble();
            cdn[1] = scanner.nextDouble();
            coordinates.add(cdn);}catch (Exception e){
                return coordinates;
            }
        }
        return coordinates;
    }

    public int getDimension() {
        return Integer.parseInt(properties.get("DIMENSION"));
    }

    public double[] getCoordinate(int i){
        if(this.coordinates!=null){
            return this.coordinates.get(i);
        }
        return null;

    }

    public static void main(String[] args) throws FileNotFoundException {

    }
}
