package humanResources;

import java.util.Collection;
import java.util.NoSuchElementException;

public class CircularLinkedList<T> implements Iterable<T>{
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
        Node<T> node = new Node<T>(value);
        //todo while-ом по нодам

        Node<T> current = head;
        do{
            if(node.getValue().equals(current))
                return false;
            if(!(value instanceof BusinessTravel))
                return false;
            current = current.getNext();
        } while ((current != head));

        if(head == null) {
            head = node;
            tail = node;
            tail.setNext(head);
        }
        else {
            node.setNext(head);
            tail.setNext(node);
            tail = node;
        }
        size++;
        return true;
    }

    public boolean addAll(Collection<? extends BusinessTravel> c){

        int counter = 0;
        //todo foreach(по с) b add()
        for (BusinessTravel item: c) {
            if(add((T) item))
                counter++;
        }
        return counter > 0;
    }

    public boolean retainAll(Collection<?> c) {
        int counter = 0;
        for (Object o : c) {
            if(!this.contains(o)) {
                this.remove(o);
                counter++;
            }
        }
        return counter == 0;
    }

    public boolean remove(Object value){
        Node<T> current = head;
        Node<T> previous = null;

        if(isEmpty())
            return false;

        do{
            if(current.getValue().equals(value)){
                if(previous != null){
                    previous.setNext(current.getNext());
                    if(current == tail)
                        tail = previous;
                }
                else{
                    head = current.getNext();
                    tail.setNext(current.getNext());
                }
                size--;
                return true;
            }
            previous = current;
            current = current.getNext();
        }while(current != head);

        return false;
    }

    public boolean removeAll(Collection<?> c) {
        int counter = 0;

        if (isEmpty())
            return false;
        //todo вместо массива используй foreach(по c)
        for (Object item : c) {
            if(remove(item))
                counter++;
        }

        return counter > 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void clear(){
        //todo пока ждому ноду проходимся и делаем ссылки null
        Node<T> current = head;
        do {
            current = null;
            current = current.getNext();
        } while (current != head);
        size =  0;
    }

    public boolean contains(Object value){
        Node<T> current = head;

        do {
            if(current.getValue().equals(value))
                return true;
            current = current.getNext();
        } while (current != head);

        return false;
    }

    public boolean containsAll(Collection<?> c){
        Node<T> current = head;
        int counter = 0;
        //todo поменяй на foreach
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

    public T[] toArray(){
        //todo пофиксь под E
        T[] businessTravels = (T[]) new BusinessTravel[size];
        Node node = head;
        int counter = 0;
        do {
            businessTravels[counter] = (T) node.getValue();
            node = node.getNext();
            counter++;
        }while(node != head);
        return businessTravels;
    }

    public T get(int index){
        if(index > size - 1 || index < 0)
            return null;
        int counter = 0;
        Node<T> current = head;
        do {
            if(counter == index)
                return (T) current;
            current = current.getNext();
            counter++;
        } while (current != head);

        return null;
    }

    //todo итератор
    private class Iterator<T> implements java.util.Iterator<T> {
        int current = 0;

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public T next() {
            if(!hasNext())
                throw new NoSuchElementException();
            return (T) get(current++);
        }
    }

    public Iterator<T> iterator(){
        return new Iterator<T>();
    }
}