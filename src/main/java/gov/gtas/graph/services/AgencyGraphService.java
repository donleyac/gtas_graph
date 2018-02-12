package gov.gtas.graph.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.gtas.graph.domain.AgencyGraph;
import gov.gtas.graph.repositories.AgencyGraphRepository;


@Service
public class AgencyGraphService {
	
	@Autowired
	AgencyGraphRepository agencyRepository;

	@Transactional(readOnly = true)
	public Collection<AgencyGraph> findPassengersByAgency(int limit){
		
		return agencyRepository.findPassengersByAgency(limit);
	}
}
