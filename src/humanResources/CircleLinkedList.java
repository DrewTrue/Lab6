package humanResources;

import java.util.Collection;
import java.util.Iterator;

public class CircleLinkedList<T> implements Iterable<T>{
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public Node<T> getHead(){
        return head;
    }

    public Node<T> getTail(){
        return tail;
    }

    public boolean addNodeSet(T value){
        Node<T> node = new Node<T>(value);
        //todo while-ом по нодам
        for(int i = 0; i < businessTravels.length; i++){
            if(node.getValue().equals(businessTravels[i]) && !(value instanceof BusinessTravel))
                return false;
        }
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

    public boolean addAllSets(Collection<? extends BusinessTravel> c){

        int counter = 0;
        //todo foreach(по с) b add()


        return counter > 0;
    }

    public boolean retainAll(Collection<?> c) {
        int counter = 0;
        for (Object o : c) {
            if(!this.contains(o)) {
                this.removeNode(o);
                counter++;
            }
        }
        return counter == 0;
    }

    public boolean removeNode(Object value){
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
        Node<T> current = head;
        Node<T> previous = null;
        int counter = 0;
        BusinessTravel[] businessTravels = (BusinessTravel[]) c.toArray();

        if (isEmpty())
            return false;
        //todo вместо массива используй foreach(по c)
        for (int i = 0; i < businessTravels.length; i++) {
            do {
                if (current.getValue().equals(businessTravels[i])) {
                    if (previous != null) {
                        previous.setNext(current.getNext());
                        if (current == tail)
                            tail = previous;
                    } else {
                        head = current.getNext();
                        tail.setNext(current.getNext());
                    }
                    size--;
                    counter++;
                    break;
                }
                previous = current;
                current = current.getNext();
            } while (current != head);
        }

        return counter > 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void clearList(){
        //todo пока ждому ноду проходимся и делаем ссылки null
        head = null;
        tail = null;
        size =  0;
    }

    public boolean contains(Object value){
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
        //todo поменяй на foreach
        for (Object o : c) {
            current.getValue().equals(o)
        }
        for(int i = 0; i < businessTravels.length; i++) {
            while (current != null) {
                if (current.getValue().equals(businessTravels[i])) {
                    counter++;
                    break;
                }
                current = current.getNext();
            }
        }

        return counter == businessTravels.length;
    }

    public T[] toArray(){
        //todo пофиксь под T
        BusinessTravel[] businessTravels = new BusinessTravel[size];
        Node node = head;
        int counter = 0;
        do {
            businessTravels[counter] = (BusinessTravel) node.getValue();
            node = node.getNext();
            counter++;
        }while(node != head);
        return businessTravels;
    }
    //todo итератор
    private class Iterator<T> implements java.util.Iterator<T> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public T next() {
            return null; //NoSuchElementException
        }
    }

    public Iterator<T> iterator(){
        return new Iterator<T>();
    }

}