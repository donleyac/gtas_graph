package gov.gtas.graph.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.gtas.graph.domain.FlightGraph;
import gov.gtas.graph.domain.PassengerGraph;
import gov.gtas.graph.repositories.FlightGraphRepository;
import gov.gtas.graph.repositories.PassengerGraphRepository;
import gov.gtas.rdbms.domain.Passenger;

@Service
public class PassengerGraphService {

	@Autowired
	PassengerGraphRepository passengerRepository;
	
	@Autowired
	FlightGraphRepository flightGraphRepository;
	
	@Transactional(readOnly = true)
	public Map<String, Object>  passengerGraph(int limit) {
		Collection<PassengerGraph> result = (Collection<PassengerGraph>) passengerRepository.findAll();
		return toD3Format(result);
	}

	private Map<String, Object> toD3Format(Collection<PassengerGraph> pass) {
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();
		int i = 0;
		Iterator<PassengerGraph> result = pass.iterator();
		while (result.hasNext()) {
			PassengerGraph graph = result.next();
			nodes.add(map("title", graph.getFirstName(), "label", "passenger"));
			int target = i;
			i++;

		}
		return map("nodes", nodes, "links", rels);
	}
	
	private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put(key1, value1);
		result.put(key2, value2);
		return result;
	}
	
	public Collection<PassengerGraph> findByDocumentNumber(String dNumber){
		
		return passengerRepository.getPassengersByDocumentNumber(dNumber);
	}
}
