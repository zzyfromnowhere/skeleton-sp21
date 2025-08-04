package deque;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MaxArrayDeque<Item> implements Iterable<Item>, Deque<Item>{
    private static final int INITIAL_CAPACITY = 8;
    private static final double SHRINK_FACTOR = 0.25;
    private static final double EXPAND_FACTOR = 1.0;

    private int size;
    private int capacity;
    private int first;
    private int last;
    private Item[] items;

    private Comparator<Item> comparator;

    public MaxArrayDeque() {
        this.size = 0;
        this.capacity = INITIAL_CAPACITY;
        this.items = (Item[]) new Object[INITIAL_CAPACITY];
        this.first = capacity / 2;
        this.last = first + 1;
    }

    public MaxArrayDeque(Comparator<Item> c){
        this.size = 0;
        this.capacity = INITIAL_CAPACITY;
        this.items = (Item[]) new Object[INITIAL_CAPACITY];
        this.first = capacity / 2;
        this.last = first + 1;
        this.comparator = c;
    }

    public Item max(){
        if(isEmpty()) return null;
        Item max = items[first];
        for(Item i : this){
            if(comparator.compare(i, max) >= 0) max = i;
        }
        return max;
    }

    public Item max(Comparator<Item> c){
        if(isEmpty()) return null;
        Item max = items[first];
        for(Item i : this){
            if(c.compare(i, max) >= 0) max = i;
        }
        return max;
    }

    private boolean isFull() {
        return size == capacity;
    }

    private void resize(int newCapacity) {
        Item[] newArray = (Item[]) new Object[newCapacity];

        if (first < last) {
            System.arraycopy(items, first + 1, newArray, 1, size);
        } else {
            int firstPart = capacity - first - 1;
            System.arraycopy(items, first + 1, newArray, 1, firstPart);
            System.arraycopy(items, 0, newArray, firstPart + 1, size - firstPart);
        }

        this.items = newArray;
        this.capacity = newCapacity;
        this.first = 0;
        this.last = size + 1;
    }

    @Override
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null to deque");
        }

        if (isFull()) {
            resize(capacity * 2);
        }

        items[first] = item;
        first = (first - 1 + capacity) % capacity;
        size++;
    }


    @Override
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null to deque");
        }

        if (isFull()) {
            resize(capacity * 2);
        }

        items[last] = item;
        last = (last + 1) % capacity;
        size++;
    }
    

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (Item item : this) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    @Override
    public Item removeFirst() {
        if (isEmpty()) {
            return null;  // Changed from throwing exception to returning null
        }

        first = (first + 1) % capacity;
        Item item = items[first];
        items[first] = null;
        size--;

        if (capacity > INITIAL_CAPACITY && size < capacity * SHRINK_FACTOR) {
            resize(Math.max(INITIAL_CAPACITY, capacity / 2));
        }

        return item;
    }

    @Override
    public Item removeLast() {
        if (isEmpty()) {
            return null;  // Changed from throwing exception to returning null
        }

        last = (last - 1 + capacity) % capacity;
        Item item = items[last];
        items[last] = null;
        size--;

        if (capacity > INITIAL_CAPACITY && size < capacity * SHRINK_FACTOR) {
            resize(Math.max(INITIAL_CAPACITY, capacity / 2));
        }

        return item;
    }

    @Override
    public Item get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
        }
        return items[(first + 1 + index) % capacity];
    }

    public Iterator<Item> iterator() {
        return new MaxArrayDequeIterator();
    }

    private class MaxArrayDequeIterator<T> implements Iterator<Item> {
        private int current;
        private int remaining;

        public MaxArrayDequeIterator() {
            this.current = (first + 1) % capacity;
            this.remaining = size;
        }

        public boolean hasNext() {
            return remaining > 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = items[current];
            current = (current + 1) % capacity;
            remaining--;
            return item;
        }
    }

}
