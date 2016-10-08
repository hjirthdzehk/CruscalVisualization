import java.util.UUID;

public class Vertex {
    private UUID id;

    public Vertex() {
        id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "Vertex-" + id.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        return id.equals(vertex.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
