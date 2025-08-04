package deque;

import java.util.Iterator;

public class ArrayDeque<Item> implements Iterable<Item>{
    int size; //number of items
    int length; // the maximum size of the array now
    double ratio; // use ratio

    //two pointers to show the first and last position
    int firstPos;
    int lastPos;

    Item[] items;

    public ArrayDeque(){
        this.size = 0;
        items = (Item[])new Object[8];
        this.length = 8;
        this.firstPos = 0;
        this.lastPos = 1;
    }

    private boolean full(){
        if(firstPos > lastPos)
            return (firstPos - lastPos == 0);
        return (firstPos == 0 && lastPos == length - 1);
    }

    private double calRatio(){
        return size / (double)(length - 2);
    }

    //full or lowUtilRate is the priority of resizing
    private void resize(int column){
        Item[] newArr = (Item[]) new Object[column];
        if(firstPos > lastPos){
            System.arraycopy(items, 0, newArr, 0, lastPos);
            System.arraycopy(items, firstPos + 1, newArr, column + firstPos - length ,length - firstPos - 1);
        }
        else
            System.arraycopy(items, 0, newArr, 0, length);
        length = column;
    }

    public void addFirst(Item i){
        if(full()) resize(length * 2);
        if(firstPos == 0){
            firstPos = length - 1;
            items[0] = i;
        }
        else items[firstPos--] = i;
        size++;
    }

    public void addLast(Item i){
        if(full()) resize(length * 2);
        if(lastPos == length - 1){
            lastPos = 0;
            items[length -1] = i;
        }
        else items[lastPos ++] = i;
        size ++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        for(Item i : this){
            System.out.print(i.toString() + ' ');
        }
        System.out.println();
    }

    public Item removeFirst(){
        Item r;
        if(firstPos == length-1){
            r = items[0];
            items[0] = null;
            firstPos = 0;
        }
        else {
            r = items[++firstPos];
            items[firstPos] = null;
        }
        if(calRatio() < 0.25)
            resize(length / 4 + 1);
        return r;
    }

    public Item removeLast(){
        Item r;
        if(lastPos == 0){
            lastPos = length - 1;
            r = items[lastPos];
            items[lastPos] = null;
        }
        else{
            r = items[--lastPos];
            items[lastPos] = null;
        }
        if(calRatio() < 0.25)
            resize(length / 4 + 1);
        return r;
    }

    public Item get(int index){
        if(index > length - 1) return null;
        return items[(firstPos + index) % length];
    }

   public Iterator<Item> iterator(){
        return new ArrayDequeIterator<>();
    }

   private class ArrayDequeIterator<T> implements Iterator<T>{
        int pos;
        public ArrayDequeIterator(){
            int i = (firstPos == length) ? (pos = 0) : (pos = firstPos + 1);
        }
        public boolean hasNext(){
            if(pos == length - 1){
                return (items[0] != null);
            }
            return (items[pos++] != null);
        }
        public T next(){
            if(pos == length - 1) {
                pos = 0;
                return (T) items[0];
            }
            return (T) items[pos++];
        }

    }

   public boolean equals(Object o){
       if(!(o instanceof ArrayDeque)) return false;
       if(this.size() != ((ArrayDeque<Item>) o).size()){
            return false;
       }
       int index = firstPos;
       if(index == length - 1)
           index = 0;
       while(index != lastPos){
           if(!this.items[index].equals(((ArrayDeque<Item>) o).items[index])) return false;
           if(index == length - 1){
               index = 0;
           }
           else index ++;
       }
       return true;
   }



























}
