import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import org.apache.commons.collections15.Transformer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class KruskalGraphVisualizer extends JPanel {
    private Graph<Vertex, Edge> graph;
    private Layout mVisualizer;
    private VisualizationViewer mVizViewer;

    public KruskalGraphVisualizer(Graph<Vertex, Edge> graph) {
        this.graph = graph;
        mVisualizer = new FRLayout(graph);

        mVizViewer = new VisualizationViewer(mVisualizer);
        mVizViewer.setBackground(Color.WHITE);

        mVizViewer.setGraphMouse(new DefaultModalGraphMouse());

        makeSpanningTreeRed();

        add(mVizViewer);
    }

    private void makeSpanningTreeRed(){
        final ArrayList<Edge> edges = new ArrayList<Edge>(graph.getEdges());
        Collections.sort(edges);

        int vertexCount = graph.getVertexCount();
        final ArrayList<Edge> edgesInSpanningTree = new ArrayList<Edge>(vertexCount - 1);
        ArrayList<Vertex> verticesInSpanningTree = new ArrayList<Vertex>(vertexCount);
        DisjointSet<Vertex> verticesDisjointSet = new DisjointSet<>(graph.getVertices());
        int currentSize = edgesInSpanningTree.size();
        while (edgesInSpanningTree.size() < vertexCount-1) {
            for (Edge edge : edges) {
                if (edgesInSpanningTree.contains(edge)){
                    continue;
                }
                Vertex[] incidentVertices = new Vertex[2];
                graph.getIncidentVertices(edge).toArray(incidentVertices);

                if (verticesDisjointSet.find(incidentVertices[0])
                        .equals(verticesDisjointSet.find(incidentVertices[1]))) {
                    continue;
                }
                edgesInSpanningTree.add(edge);
                verticesInSpanningTree.add(incidentVertices[0]);
                verticesInSpanningTree.add(incidentVertices[1]);
                verticesDisjointSet.union(incidentVertices[0], incidentVertices[1]);
                verticesDisjointSet.union(verticesInSpanningTree.get(0), incidentVertices[0]);
                edges.remove(edge);
                break;
            }
            if (currentSize == edgesInSpanningTree.size()) {
                break; //if graph not linked just break, we will get minimum spanning tree for 1 component
            }
            currentSize = edgesInSpanningTree.size();
        }
        Transformer<Edge, Paint> edgePaint = new Transformer<Edge, Paint>() {
            @Override
            public Paint transform(Edge edge) {
                if (edgesInSpanningTree.contains(edge)){
                    return Color.RED;
                }
                else {
                    return Color.DARK_GRAY;
                }
            }
        };

        mVizViewer.getRenderContext().setEdgeDrawPaintTransformer(edgePaint);
    }
}