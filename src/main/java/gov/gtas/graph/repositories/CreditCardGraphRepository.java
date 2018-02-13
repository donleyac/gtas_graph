package gov.gtas.graph.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;

import gov.gtas.graph.domain.CreditCardGraph;

public interface CreditCardGraphRepository extends GraphRepository<CreditCardGraph>{
	public CreditCardGraph findByMariaId(Long id);
}

