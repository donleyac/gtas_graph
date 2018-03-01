package gov.gtas.graph.repositories;

import org.springframework.stereotype.Repository;

import java.util.Collection;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import gov.gtas.graph.domain.FlightGraph;
import gov.gtas.graph.domain.PassengerGraph;


@Repository
public interface FlightGraphRepository extends GraphRepository<FlightGraph>{

	Collection<FlightGraph> findByFlightNumber(String flightNumber);
	FlightGraph findByMariaId(Long id);
	Collection<FlightGraph> findByFlightDate(String date);
	
	@Query("START n=node(*) MATCH (n:Flight)<-[r]->(m) RETURN n,r,m")
	Collection<FlightGraph> getFullFlightGraph();
	
}
