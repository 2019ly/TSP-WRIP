package TSP.util;

import edu.princeton.cs.algs4.In;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
    private Properties p;
    public PropertiesUtil(File file) throws IOException {
        p = new Properties();
        p.load(new FileInputStream(file));
    }

    public String getName(){
        return p.getProperty("name");
    }
    public int getKnownOptimum(){
        return Integer.parseInt(p.getProperty("knownOptimum"));
    }
    public int getTime(){
        System.out.println();
        return Integer.parseInt(p.getProperty("time"));
    }
    public int getThreadNum(){
        return Integer.parseInt(p.getProperty("threadNum"));
    }

    public String getInstanceDir(){
        return p.getProperty("dir");
    }
}
