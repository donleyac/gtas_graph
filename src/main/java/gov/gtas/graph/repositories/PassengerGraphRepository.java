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
	PassengerGraph findByMariaId(@Param("paxId") Long paxId);

	@Query("MATCH (p:Passenger)<-[r:FLEW_ON]-(f:Flight) RETURN p,r,f LIMIT {limit}")
	Collection<PassengerGraph> getPassengers(@Param("limit") int limit);

	@Query("MATCH (f:Flight)<-[r:FLEW_ON]-(a:Passenger) RETURN f,r,a LIMIT {limit}")
	Collection<FlightGraph> getFlightGraph(@Param("limit") int limit);

	@Query("MATCH p=()-[r:FLEW_ON]->() RETURN p")
	Collection<FlightGraph> getFlightPaxGraph();

	@Query("MATCH (m:Document)<-[r:HAS_A]-(a:Passenger) where m.documentNumber={documentNumber} RETURN m,r,a")
	Collection<PassengerGraph> getPassengersByDocumentNumber(@Param("documentNumber") String number);
}
