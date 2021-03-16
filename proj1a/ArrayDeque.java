public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int head;
    private int tail;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        head = 4;
        tail = 3;
        size = tail - head + 1;
    }

    private void update() {
        if (head <= 0 || tail >= items.length - 1){
            resize();
        }
        if (items.length >= 16 && size * 4 <= items.length) {
            resize();
        }
    }

    private void resize() {
        T[] newItems = (T[]) new Object[size * 2];
        int newHead = size / 2;
        System.arraycopy(items, head, newItems, newHead, size);
        this.items = newItems;
        head = newHead;
        tail = head + size - 1;
    }

    public void addFirst(T item) {
        head -= 1;
        items[head] = item;
        size += 1;
        update();
    }

    public void addLast(T item) {
        tail += 1;
        items[tail] = item;
        size += 1;
        update();
    }

    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T val = items[head];
        head += 1;
        size -= 1;
        update();
        return val;
    }
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T val = items[tail];
        tail -= 1;
        size -= 1;
        update();
        return val;
    }
    public void printDeque() {
        for (int i = head; i <= tail; i++) {
            System.out.print(items[i] + " ");
        }
    }
    public T get(int index) {
        if (size == 0 || index >= size) {
            return null;
        }
        return items[head + index];
    }
}
