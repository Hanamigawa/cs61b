public interface Deque<T> {
    void addFirst(T item);
    void addLast(T item);
    boolean isEmpty();
    public int size();
    void printDeque();
    public T removeFirst();
    public T removeLast();
    public T get(int index);
}
