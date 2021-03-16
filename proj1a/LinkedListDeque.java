public class LinkedListDeque<T> {
    private int size;
    private Node sentinel = new Node(null,null,null);

    private class Node {
        public T value;
        public Node left;
        public Node right;

        public Node(T value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public LinkedListDeque() {
        sentinel.left = sentinel;
        sentinel.right = sentinel;
        size =  0;
    }

    public LinkedListDeque(T val) {
        sentinel.right = new Node(val, sentinel, sentinel);
        sentinel.left = sentinel.right;
        size = 1;
    }

    public void addFirst(T item) {
        Node newNode = new Node(item, sentinel, sentinel.right);
        sentinel.right = newNode;
        newNode.right.left = newNode;
        size += 1;
    }

    public void addLast(T item) {
        Node newNode = new Node(item, sentinel.left, sentinel);
        sentinel.left.right = newNode;
        sentinel.left = newNode;
        size += 1;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return this.size;
    }

    public void printDeque() {
        Node cur = sentinel.right;
//        System.out.println("Start printing: ");
        for(int i = 0; i < size; i++){
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
    }

    public T removeFirst() {
        if (this.size == 0) return null;
        Node pop = sentinel.right;
        sentinel.right = pop.right;
        pop.right.left = sentinel;
        this.size -= 1;
        return pop.value;
    }

    public T removeLast() {
        if (this.size == 0) return null;
        Node pop = sentinel.left;
        sentinel.left = pop.left;
        pop.left.right = sentinel;
        this.size -= 1;
        return pop.value;
    }

    public T get(int index) {
        if (size == 0 || index >= size) return null;
        Node cur = sentinel.right;
        while (index > 0){
            cur = cur.right;
            index -= 1;
        }
        return cur.value;
    }

    public T getRecursive(int index) {
        if (size == 0 || index >= size) return null;
        return getRecurHelper(sentinel.right, index);
    }

    private T getRecurHelper(Node cur, int index) {
        if (index == 0) {
            return cur.value;
        }
        return getRecurHelper(cur.right, index - 1);
    }
}
