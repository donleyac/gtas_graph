package gov.gtas.graph.repositories;

import java.util.Collection;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import gov.gtas.graph.domain.FlightGraph;
import gov.gtas.graph.domain.PassengerGraph;

@Repository
@RepositoryRestResource(collectionResourceRel = "passengers", path = "passengers")
public interface PassengerGraphRepository extends GraphRepository<PassengerGraph>{

	PassengerGraph findByFirstName(@Param("firstName") String firstName);
	PassengerGraph findByLastName(@Param("larstName") String larstName);
	
	Collection<PassengerGraph> findByFlightId(@Param("flightId")Long flightId);
	
	@Query("MATCH (f:Flight)<-[r:FLEW_ON]-(a:Passenger) RETURN f,r,a LIMIT {limit}")
	Collection<FlightGraph> getFlightGraph(@Param("limit") int limit);
	//MATCH p=()-->() RETURN p LIMIT 25
	//@Query("MATCH (f:Flight )<-[:FLEW_ON*..2]-(p:Passenger)	RETURN f,p")
	//@Query("MATCH p=()-->() RETURN p LIMIT 100")
	//@Query("MATCH (n:Flight) WITH n MATCH p=(n)-[*0..1]-(m) RETURN p,n")
	//Collection<FlightGraph> getFlightGraph(@Param("limit") int limit);
	//@Query("MATCH (n:Flight) WITH n MATCH p=(n)-[*0..1]-(m) RETURN n")
	//Collection<PassengerGraph> getPassengerGraph(@Param("limit") int limit);
	@Query("MATCH p=()-[r:FLEW_ON]->() RETURN p")
	Collection<FlightGraph> getFlightPaxGraph();
	
	//@Query("MATCH (f:Flight {id={0}})<-[:FLEW_ON]-(pass) RETURN pass")
	//Collection<PassengerGraph> getPassengersByFlight(@Param("id") FlightGraph flight);
	
}
