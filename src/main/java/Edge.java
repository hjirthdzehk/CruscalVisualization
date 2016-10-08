import java.util.Random;
import java.util.UUID;

public class Edge implements Comparable<Edge> {
    private UUID id;
    private int weight;

    public Edge() {
        id = UUID.randomUUID();
        weight = new Random().nextInt(100);
    }

    @Override
    public String toString() {
        return "Edge-"+id.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        return id.equals(edge.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public int compareTo(Edge o)
    {
        return (weight - o.getWeight());
    }

    public int getWeight() {
        return weight;
    }
}
