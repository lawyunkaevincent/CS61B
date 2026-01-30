package deque;

public class ArrayDeque<T> {
    private int size;
    private int length;
    private T[] items;
    private int first;
    private int last;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 8;
        length = 0;
        first = 0;
        last = 0;
    }

    public void addFirst(T item){
        if (length == size - 1){
            reSize(size *= 2);
        }
        if (!isEmpty()){
            first = (first - 1 + size) % size;
        }
        items[first] = item;
        length += 1;
    }

    public void addLast(T item){
        if (length == size - 1){
            reSize(size *= 2);
        }
        if (!isEmpty()){
            last = (last + 1) % size;
        }
        items[last] = item;
        length += 1;
    }

    public void printDeque(){
        if (isEmpty()){
            System.out.println();
            return;
        }
        int i = first;
        while (i != last){
            System.out.print(items[i] + " ");
            i = (i + 1) % size;
        }
        System.out.println(items[i]);
    }

    public T removeFirst(){
        if (isEmpty()){
            return null;
        }
        length -= 1;
        T ret = items[first];
        items[first] = null;
        first = (first + 1) % size;
        if (length*4 < size){
            reSize(size/2);
        }
        return  ret;
    }

    public T removeLast(){
        if (isEmpty()){
            return null;
        }
        length -= 1;
        T ret = items[last];
        items[last] = null;
        last = (last - 1 + size) % size;
        if (length*4 < size){
            reSize(size/2);
        }
        return ret;
    }

    public T get(int index){
        if (index < 0 || index >= length){
            return null;
        }
        int pos = (first + index) % size;
        return items[pos];
    }

    private void reSize(int s){
        T[] temp = (T[]) new Object[s];
        int i = first;
        int j = 0;
        while (j < length){
            temp[j] = items[i];
            i = (i + 1) % size;
            j += 1;
        }
        size = s;
        first = 0;
        if (isEmpty()){
            last = 0;
        } else {
            last = j - 1;
        }
        items = temp;
    }

    public int size(){
        return length;
    }

    public boolean isEmpty(){
        return size() == 0;
    }
}
