import java.util.Collection;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by Олег on 08.10.2016.
 */
public class DisjointSet<T> {
    private Dictionary<T,T> parents = new Hashtable<T, T>();
    public DisjointSet(Collection<T> elements) {
        elements.forEach(element -> {
            parents.put(element, element);
        });
    }

    public void union(T first, T second) {
        T firstParent = find(first);
        T secondParent = find(second);
        if (firstParent.equals(secondParent)){
            return;
        }
        parents.put(secondParent, firstParent);
    }

    public T find(T element){
        T current = element;
        while (!parents.get(current).equals(current)) {
            current = parents.get(current);
        }
        return current;
    }

    private class DisjointNodeWrapper<T> {
        T value;
        DisjointNodeWrapper<T> parent;

        public DisjointNodeWrapper(T value) {
            this.value = value;
            this.parent = this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            DisjointNodeWrapper<?> that = (DisjointNodeWrapper<?>) o;

            return value.equals(that.value);
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }
    }
}
