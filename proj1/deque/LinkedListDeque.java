package deque;

public class LinkedListDeque<T> {
    private class ItemNode{
        private T elem;
        private ItemNode next;
        private ItemNode prev;
    }

    private int size;
    private final ItemNode sentinel;

    public LinkedListDeque(){
        size = 0;
        sentinel = new ItemNode();
        sentinel.elem = null;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addFirst(T item){
        ItemNode p = new ItemNode();
        p.elem = item;
        p.next = sentinel.next;
        p.prev = sentinel;
        sentinel.next = p;
        p.next.prev = p;
        size += 1;
    }


    public void addLast(T item){
        ItemNode p = new ItemNode();
        p.elem = item;
        p.prev = sentinel.prev;
        p.next = sentinel;
        p.prev.next = p;
        sentinel.prev = p;
        size += 1;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        ItemNode p = sentinel.next;
        while (p != sentinel){
            System.out.print(p.elem + " ");
            p = p.next;
        }
        System.out.println();
    }

    public T removeFirst(){
        if (sentinel.next == sentinel){
            return null;
        }
        T ret = sentinel.next.elem;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return ret;
    }

    public T removeLast(){
        if (sentinel.prev == sentinel){
            return null;
        }
        T ret = sentinel.prev.elem;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return ret;
    }

    public T get(int index){
        ItemNode p = sentinel.next;
        while (index != 0 && p != sentinel){
            p = p.next;
            index -= 1;
        }
        if (index == 0){
            return p.elem;
        }
        return null;
    }

    public T getRecursive(int index){
        if (index != 0 || index >= size){
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(ItemNode p, int idx){
        if (p == sentinel){
            return null;
        }
        if (idx == 0){
            return p.elem;
        }
        return getRecursiveHelper(p.next, idx - 1);
    }
}
