package humanResources;

import java.time.LocalDate;
import java.util.Set;

public interface BusinessTraveller extends Set<BusinessTravel>{
    void addTravel(BusinessTravel travel) throws IllegalDatesException;

    boolean isTravelNow();
    int getTravelDaysFromTimeLapse(LocalDate beginTravelMark, LocalDate endTravelMark);
}
