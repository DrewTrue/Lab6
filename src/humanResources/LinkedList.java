package humanResources;

import java.util.*;


//todo аналогично CycledLinkedList
public class LinkedList<T> implements Iterable<T>{
    private class Node<E> {
        private E value;
        private Node<E> next;

        public Node(E value){
            this.value = value;
        }

        public E getValue(){
            return value;
        }

        public void setValue(E value){
            this.value = value;
        }

        public Node<E> getNext(){
            return next;
        }

        public void setNext(Node<E> next){
            this.next = next;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public Node<T> getHead(){
        return head;
    }

    public Node<T> getTail(){
        return tail;
    }

    public boolean add(T value){
        Node<T> node = new Node<>(value);

        if(head == null)
            head = node;
        else
            tail.setNext(node);
        tail = node;
        size++;

        return true;
    }

    public boolean add(int index, T value){
        Node<T> nodeValue = new Node<T>(value);

        Node<T> node = head;
        Node<T> currentNode;
        int counter = 0;

        if(!isEmpty()) {
            if (index == 0) {
                currentNode = head;
                head = nodeValue;
                head.setNext(currentNode);
                size++;
                return true;
            }

            if (index == size - 1) {
                currentNode = tail;
                remove(tail.value);
                if(add(nodeValue.value)) {
                    add(currentNode.value);
                    return true;
                }
                return false;
            }
        } else {
            return add(value);
        }

        while(node != null) {
            if(counter == index){
                currentNode = node;
                node = nodeValue;
                node.setNext(currentNode);
            }

            counter++;
            node = node.getNext();
        }

        if(counter > size){
            size = counter;
            return true;
        }

        return false;
    }

    public boolean addAll(Collection<? extends T> c) {
        int counter = 0;

        for (T item : c) {
            if(add(item))
                counter++;
        }

        return counter > 0;
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        Object[] objects = toArray();
        int counter = 0;

        clear();

        for(int i = 0; i < index; i++){
            add((T) objects[i]);
        }

        for (T item : c) {
            add(item);
            counter++;
        }

        for(int i = index; i < objects.length; i++){
            add((T)objects[i]);
        }

        return counter == c.size();
    }

    public T get(int index){
        Node<T> current = head;
        int counter = 0;
        while (current != null){
            if(counter == index)
                return (T) current;
            counter++;
            current = current.getNext();
        }
        return null;
    }

    public T set(int index, T value){
        Node<T> current = head;
        int counter = 0;

        if (index == 0) {
            head.setValue(value);
            return head.getValue();
        }

        if(index == size - 1){
            tail.setValue(value);
            return tail.getValue();
        }

        while(current != null) {
            if(counter == index){
                current.setValue(value);
                return current.getValue();
            }
            counter++;
            current = current.getNext();
        }

        return null;
    }

    public boolean retainAll(Collection<?> c) {
        int counter = 0;
        for (Object item : c) {
            if(!this.contains((T) item)) {
                this.remove((T) item);
                counter++;
            }
        }
        return counter == 0;
    }

    public boolean remove(T value){
        Node<T> current = head;
        Node<T> previous = null;

        while(current != null){
            if(current.getValue().equals(value)){
                if(previous != null){
                    previous.setNext(current.getNext());
                    if(current.getNext() == null)
                        tail = previous;
                }
                else{
                    head = head.getNext();
                    if(head == null)
                        tail = null;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.getNext();
        }
        return false;
    }

    public T remove(int index){
        T removeNode;
        for(int i = 0; i < size; i++){
            if(index == i){
                removeNode = get(i);
                if(remove(removeNode))
                    return removeNode;
            }
        }

        return null;
    }

    public boolean removeAll(Collection<?> c) {
        int counter = 0;

        if (isEmpty())
            return false;
        for (Object item : c) {
            if(remove((T) item))
                counter++;
        }

        return counter > 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void clear(){
        Node<T> current = head;
        while (current != null){
            current = null;
            current = current.getNext();
        }
        size =  0;
    }

    public boolean contains(T value){
        Node<T> current = head;
        while(current != null){
            if(current.getValue().equals(value))
                return true;
            current = current.getNext();
        }
        return false;
    }

    public boolean containsAll(Collection<?> c){
        Node<T> current = head;
        int counter = 0;
        for(Object item : c) {
            do {
                if (current.getValue().equals(item)) {
                    counter++;
                    break;
                }
                current = current.getNext();
            } while (current != head);
        }

        return counter == size;
    }

    public Object[] toArray(){
        Object[] objects = new Object[size];
        Node<T> current = head;
        int counter = 0;
        while (current != null){
            objects[counter] = current;
            counter++;
            current = current.getNext();
        }
        return objects;
    }

    //todo та же фигня с итератором, что и в CycledLinkedList

    private class Iterator<E> implements java.util.Iterator<E> {
        int current = 0;

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public E next() {
            if(!hasNext())
                throw new NoSuchElementException();
            return (E) get(current++);
        }
    }

    public Iterator<T> iterator(){
        return new Iterator<>();
    }

    private class ListIterator<E> implements java.util.ListIterator<E>{
        int current;

        private final static int DEAFAULT_INDEX = 0;

        ListIterator(){
            this(DEAFAULT_INDEX);
        }

        ListIterator(int index){
            current = index;
        }

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public E next() {
            if(!hasNext())
                throw new NoSuchElementException();
            return (E) get(current++);
        }

        @Override
        public boolean hasPrevious() {
            return current >= 0;
        }

        @Override
        public E previous() {
            if(!hasPrevious())
                throw new NoSuchElementException();
            return (E) get(current--);
        }

        @Override
        public int nextIndex() {
            return current++;
        }

        @Override
        public int previousIndex() {
            return current--;
        }

        @Override
        public void remove() {
            LinkedList.this.remove(current);
        }

        @Override
        public void set(E e) {
            LinkedList.this.set(current, (T) e);
        }

        @Override
        public void add(E e) {
            LinkedList.this.add((T) e);
        }
    }

    public ListIterator<T> listIterator(){
        return new ListIterator<>();
    }

    public ListIterator<T> listIterator(int index){
        return new ListIterator<>(index);
    }
}
