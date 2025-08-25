<<<<<<< HEAD
package deque;

public interface Deque<Item> {
    void addFirst(Item item);
    void addLast(Item item);
    int size();
    void printDeque();
    Item removeFirst();
    Item removeLast();
    Item get(int index);

    default boolean isEmpty() {
        return size() == 0;
    }
}
=======
package deque;

public interface Deque<Item> {
    public void addFirst(Item item);
    public void addLast(Item item);
    public int size();
    public void printDeque();
    public Item removeFirst();
    public Item removeLast();
    public Item get(int index);

    default public boolean isEmpty(){
        return size() == 0;
    }
}
>>>>>>> 69a1ff0d77d7110a42ad778f7c11a0890efc3cbc
