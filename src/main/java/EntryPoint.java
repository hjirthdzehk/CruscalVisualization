import edu.uci.ics.jung.graph.Graph;

import javax.swing.*;

public class EntryPoint {
    public static void main(String[] args) {
        int verticesCount = 400;
        int edgesCount = 1000;

        GraphFactory graphFactory = new GraphFactory();
        Graph<Vertex, Edge> graph = graphFactory.create(verticesCount, edgesCount);

        KruskalGraphVisualizer graphVisualizer = new KruskalGraphVisualizer(graph);

        JFrame jf = new JFrame("Graph View");

        jf.getContentPane().add(graphVisualizer);

        jf.setSize(700, 500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);
    }
}
