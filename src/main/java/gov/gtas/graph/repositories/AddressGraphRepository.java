package gov.gtas.graph.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import gov.gtas.graph.domain.AddressGraph;

@Repository
public interface AddressGraphRepository extends GraphRepository<AddressGraph>{
	public AddressGraph findByMariaId(Long id);
}
