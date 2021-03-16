public class ArrayDeque<Type> {
    private Type[] items;
    private int size;
    private int head;
    private int tail;

    public ArrayDeque() {
        items = (Type[]) new Object[8];
        head = 4;
        tail = 3;
        size = tail - head + 1;
    }

    private void update() {
        if (head == 0 || tail == items.length - 1){
            resize();
        }
        if (items.length >= 16 && size * 4 <= items.length) {
            downsize();
        }
    }

    private void resize() {
        Type[] newItems = (Type[]) new Object[size * 2];
        int newHead = size / 2;
        System.arraycopy(items, head, newItems, newHead, size);
        this.items = newItems;
        head = newHead;
        tail = newHead + size - 1;
    }

    private void downsize() {
        resize();
    }

    public void addFirst(Type item) {
        head -= 1;
        items[head] = item;
        size += 1;
        update();
    }

    public void addLast(Type item) {
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
    public Type removeFirst() {
        if (size == 0) {
            return null;
        }
        Type val = items[head];
        head += 1;
        size -= 1;
        update();
        return val;
    }
    public Type removeLast() {
        if (size == 0) {
            return null;
        }
        Type val = items[tail];
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
    public Type get(int index) {
        if (size == 0 || index >= size) {
            return null;
        }
        return items[head + index];
    }
}
