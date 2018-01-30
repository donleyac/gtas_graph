package gov.gtas.graph.repositories;



import org.springframework.data.neo4j.repository.GraphRepository;

import gov.gtas.graph.domain.FlightPaxGraph;

public interface FlightPaxGraphRepository extends GraphRepository<FlightPaxGraph>{

}
