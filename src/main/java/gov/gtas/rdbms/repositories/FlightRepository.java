package gov.gtas.rdbms.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gov.gtas.rdbms.domain.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long>{

    @Query("SELECT f FROM Flight f WHERE f.flightDate between :startDate AND :endDate")
    public List<Flight> getFlightsByDates(@Param("startDate") Date startDate, 
                                          @Param("endDate") Date endDate);
}
