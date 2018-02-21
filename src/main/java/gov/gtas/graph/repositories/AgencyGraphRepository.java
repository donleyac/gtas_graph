package gov.gtas.graph.repositories;

import java.util.Collection;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import gov.gtas.graph.domain.AgencyGraph;


public interface AgencyGraphRepository extends GraphRepository<AgencyGraph>{
	
	public AgencyGraph findByMariaId(Long id);
	

	@Query("MATCH (a:Agency)<-[r:BOOKED_AT]-(p:Passenger) RETURN a,r,p LIMIT {limit}")
	public Collection<AgencyGraph> findPassengersByAgency(@Param("limit") int limit);
	
	@Query("MATCH (m:Agency)<-[r:BOOKED_AT]-(p:Passenger)-[r1:FLEW_ON]-(f:Flight) return m,r,r1,p,f LIMIT {limit}")
	public Collection<AgencyGraph> findPassengersFlightsByAgency(@Param("limit") int limit);
}
