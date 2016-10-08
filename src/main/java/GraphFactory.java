import edu.uci.ics.jung.algorithms.generators.random.EppsteinPowerLawGenerator;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.functors.ConstantFactory;

public class GraphFactory {
    public Graph<Vertex, Edge> create(int vertexCount, int edgeCount) {
        Factory<Graph<Vertex, Edge>> graphFactory = new ConstantFactory<>(new UndirectedSparseGraph<>());
        Factory<Vertex> vertexFactory = () -> new Vertex();
        Factory<Edge> edgeFactory = () -> new Edge();

        EppsteinPowerLawGenerator<Vertex, Edge> graphGenerator = new EppsteinPowerLawGenerator<>(graphFactory, vertexFactory, edgeFactory, vertexCount, edgeCount, 100);
        return graphGenerator.create();
    }
}
