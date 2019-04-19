package TSP.graph;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;

public class TspXmlParser {
    InputStream in;
    public TspXmlParser(String path) throws FileNotFoundException{
        this(new File(path));
    }
    public TspXmlParser(File file) throws FileNotFoundException{
        this(new FileInputStream(file));
    }
    public TspXmlParser(InputStream in){
        this.in = in;

    }

    public Graph generateGraph() throws DocumentException {
        Graph graph=new Graph();
        SAXReader reader = new SAXReader();
        Document document = reader.read(in);
        Element tspInstance = document.getRootElement();
        Iterator it = tspInstance.elementIterator();
        while(it.hasNext()){
            Element e = (Element) it.next();
            if(isGraphElement(e)){
                loadGraph(graph,e);
            }else{
                loadProperty(graph,e);
            }
        }
        return graph;
    }
    public void loadGraph(Graph graph,Element e){
        Iterator it = e.elementIterator();
        while(it.hasNext()){
            loadVertex(graph,(Element)it.next());
        }
    }

    private void loadVertex(Graph graph,Element e) {
        HashMap<Integer,Integer> edges=new HashMap<> ();
        Iterator it = e.elementIterator();
        while(it.hasNext()){
            Element edge= (Element) it.next();
            double cost = Double.parseDouble(edge.attribute("cost").getValue());
            int id = Integer.parseInt(edge.getStringValue());
            edges.put(id,(int)cost);
        }
        graph.graph.add(edges);
    }

    public void loadProperty(Graph graph,Element e){
        graph.properties.put(e.getName(),e.getStringValue());
    }

    public boolean isGraphElement(Element element){
        return element.getName().trim().toLowerCase().equals("graph");
    }


}
