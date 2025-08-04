package deque;

import java.util.Iterator;

public class LinkedListDeque<Item> implements Iterable<Item>{
    int size;

    //definition of a node
    private class Node<Item>{
        Node<Item> last;
        Item item;
        Node<Item> next;

        private Node(Item i){
            last = null;
            next = null;
            item = i;
        }

    }

    Node<Item> Sentinel;

    //constructor without parameter
    public LinkedListDeque(){
        this.Sentinel = new Node<>(null);
        this.Sentinel.last = this.Sentinel;
        this.Sentinel.next = this.Sentinel;
        this.size = 0;
    }

    public void addFirst(Item item){
        Node<Item> node = new Node<>(item);
        node.last = this.Sentinel;
        node.next = this.Sentinel.next;
        this.Sentinel.next.last = node;
        this.Sentinel.next = node;
        this.size += 1;
    }
    public void addLast(Item item){
        Node<Item> node = new Node<>(item);
        node.next = this.Sentinel;
        node.last = this.Sentinel.last;
        this.Sentinel.last.next = node;
        this.Sentinel.last = node;
        this.size += 1;
    }

    public boolean isEmpty(){
        return (this.size == 0);
    }

    public void printDeque(){
        for(Item i : this){
            if(i == null) System.out.print("  ");
            else System.out.print(i.toString() + " ");
        }
        System.out.println();
    }

    public Item removeFirst(){
        if(this.size == 0) return null;
        Node<Item> r = this.Sentinel.next;
        this.Sentinel.next = r.next;
        r.next.last = this.Sentinel;
        this.size -= 1;
        return r.item;
    }

    public Item removeLast(){
        if(this.size == 0) return null;
        Node<Item> r = this.Sentinel.last;
        this.Sentinel.last = r.last;
        r.last.next = this.Sentinel;
        this.size -= 1;
        return r.item;
    }

    public Item get(int index){
        for(Item item : this){
            if(index == 0) return item;
            index--;
        }
        return null;
    }

    //helper function of getRecursive function
    private Item getRecursive(int index, Node<Item> node){
        if(index == 0) return node.item;
        return getRecursive(index - 1, node.next);
    }

    public Item getRecursive(int index){
        return getRecursive(index, Sentinel.next);
    }

    public int size(){
        return this.size;
    }

    @Override
    public Iterator<Item> iterator(){
        return new LinkedListDequeIterator<>();
    }

    private class LinkedListDequeIterator<T> implements Iterator<T>{
        int pos;
        public LinkedListDequeIterator(){
            pos = 0;
        }
        @Override
        public boolean hasNext() {
            return pos < size;
        }
        public T next(){
            Node<T> tmp = (Node<T>) Sentinel.next;
            for(int i = 0; i < pos+1; i++){
                tmp = tmp.next;
            }
            pos++;
            return tmp.item;
        }
    }

    public boolean equals(Object o){
        int cnt = size;
        if(!(o instanceof LinkedListDeque)) return false;
        if(this.size != ((LinkedListDeque<Item>) o).size) return false;
        Node<Item> ptr1 = this.Sentinel.next;
        Node<Item> ptr2 = (Node<Item>) ((LinkedListDeque<Item>) o).Sentinel.next;
        while(cnt != 0){
            if(!(ptr1.item.equals(ptr2.item))) return false;
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
            cnt--;
        }
        return true;
    }
















}
