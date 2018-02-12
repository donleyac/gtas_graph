package gov.gtas.graph.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import gov.gtas.graph.domain.DocumentGraph;

@Repository
public interface DocumentGraphRepository extends GraphRepository<DocumentGraph>{

	public DocumentGraph findByDocumentNumber(String documentNumber);
	public DocumentGraph findByGtasDocId(Long id);
	
}
