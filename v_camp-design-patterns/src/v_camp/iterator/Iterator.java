package v_camp.iterator;

public interface Iterator<T> {
	boolean hasNext();
	
	T next();
	
	void reset();
}
