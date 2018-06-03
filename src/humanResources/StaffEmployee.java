package humanResources;

import java.io.Serializable;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class StaffEmployee extends Employee implements BusinessTraveller, Serializable {
    private int bonus;
    private CircularLinkedList<BusinessTravel> businessTravelsSet;
    private int travelsQuantity;

    private static final int DEFAULT_VALUE = 0;

    public StaffEmployee(String firstName, String secondName){
        this(firstName, secondName, JobTitlesEnum.NONE, DEFAULT_VALUE, new CircularLinkedList<>());
    }

    public StaffEmployee(String firstName, String secondName, JobTitlesEnum jobTitles, int salary){
        this(firstName,secondName,jobTitles,salary, new CircularLinkedList<>());
    }

    public StaffEmployee(String firstName, String secondName, JobTitlesEnum jobTitle, int salary, CircularLinkedList<BusinessTravel> businessTravelsSet){
        super(firstName, secondName, jobTitle, salary);
        this.businessTravelsSet = businessTravelsSet;
        this.bonus = DEFAULT_VALUE;
        this.travelsQuantity = DEFAULT_VALUE;
    }

    public void setTravelsQuantity(int travelsQuantity){
        this.travelsQuantity = travelsQuantity;
    }

    @Override
    public int size() {
        return businessTravelsSet.size();
    }

    @Override
    public boolean isEmpty() {
        return businessTravelsSet.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return businessTravelsSet.contains(o);
    }

    @Override
    public Iterator<BusinessTravel> iterator() {
        return businessTravelsSet.iterator();
    }

    @Override
    public Object[] toArray() {
        return businessTravelsSet.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return (T[])businessTravelsSet.toArray();
    }

    @Override
    public boolean add(BusinessTravel travel) {
        return businessTravelsSet.add(travel);
    }

    @Override
    public boolean remove(Object o) {
        return businessTravelsSet.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return businessTravelsSet.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends BusinessTravel> c) {
        return businessTravelsSet.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return businessTravelsSet.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return businessTravelsSet.removeAll(c);
    }

    @Override
    public void clear() {
        businessTravelsSet.clear();
    }

    @Override
    public boolean isTravelNow(){
        //todo сделай в списке метод, который возвращает последний элемент и вызывай его здесь
        BusinessTravel lastBusinessTravel = businessTravelsSet.getTail().getValue();
        return LocalDate.now().isAfter(lastBusinessTravel.getBeginTravel())
                && LocalDate.now().isBefore(lastBusinessTravel.getEndTravel());
    }

    @Override
    public int getTravelDaysFromTimeLapse(LocalDate beginTravelMark, LocalDate endTravelMark){
        //todo заведи счетчик и накапливай число дней в него, а потом возврати его значение
        int travelDays = 0;
        for (BusinessTravel businessTravel : businessTravelsSet) {
            if (beginTravelMark.isAfter(businessTravel.getBeginTravel())
                    && endTravelMark.isBefore(businessTravel.getEndTravel()))
                travelDays += (int) ChronoUnit.DAYS.between(beginTravelMark, endTravelMark);

            if (beginTravelMark.isAfter(businessTravel.getBeginTravel())
                    && beginTravelMark.isBefore(businessTravel.getEndTravel())
                    && endTravelMark.isAfter(businessTravel.getEndTravel()))
                travelDays += (int) ChronoUnit.DAYS.between(beginTravelMark, businessTravel.getEndTravel());

            if (endTravelMark.isAfter(businessTravel.getBeginTravel())
                    && endTravelMark.isBefore(businessTravel.getEndTravel())
                    && beginTravelMark.isBefore(businessTravel.getBeginTravel()))
                travelDays += (int) ChronoUnit.DAYS.between(businessTravel.getBeginTravel(), endTravelMark);
        }
        return travelDays;
    }

    @Override
    public void addTravel(BusinessTravel travel) throws IllegalDatesException {
        for (BusinessTravel businessTravel : businessTravelsSet) {
            if (travel.getBeginTravel().isAfter(businessTravel.getBeginTravel())
                    && travel.getBeginTravel().isBefore(businessTravel.getEndTravel()))
                throw new IllegalDatesException();
        }
        if(businessTravelsSet.add(travel))
            travelsQuantity++;
    }

    @Override
    public int getBonus(){
        return bonus;
    }

    @Override
    public void setBonus(int bonus){
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(super.toString()).append("\n").append("Travels ").append("/n");
        BusinessTravel[] businessTravel = businessTravelsSet.toArray();
        for (BusinessTravel aBusinessTravel : businessTravel) {
            result.append(aBusinessTravel.toString()).append("\n");
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj)
                && this.bonus == ((StaffEmployee) obj).bonus
                && travelsQuantity == ((StaffEmployee) obj).travelsQuantity;
    }

    //todo super.hashCode()
    @Override
    public int hashCode() {
        return super.hashCode() ^ travelsQuantity ^ bonus ^ businessTravelsSet.hashCode();
    }
}
