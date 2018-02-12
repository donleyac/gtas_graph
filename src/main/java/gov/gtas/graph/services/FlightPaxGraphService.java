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
import gov.gtas.graph.domain.FlightPaxGraph;
import gov.gtas.graph.domain.PassengerGraph;
import gov.gtas.graph.repositories.FlightPaxGraphRepository;
import gov.gtas.graph.repositories.PassengerGraphRepository;

@Service
public class FlightPaxGraphService {

	@Autowired
	private FlightPaxGraphRepository flightPaxGraphRepository;
	
	@Autowired
	PassengerGraphRepository passengerRepository;
	
	@Transactional(readOnly = true)
	public Map<String, Object>  graph(int limit) {

		Collection<PassengerGraph> passengers = passengerRepository.getPassengers(limit);
	
		return toD3Format(passengers);
	}
	
	private Map<String, Object> toD3Format(Collection<PassengerGraph> passengers) {
	
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();
		int i = 0;
		Iterator<PassengerGraph> result = passengers.iterator();
		while (result.hasNext()) {
			PassengerGraph pGraph = result.next();
			nodes.add(map("title", pGraph, "label", "Passenger"));
			int target = i;
			i++;
			
	
			for (FlightGraph f : pGraph.getFlights()) {
				Map<String, Object> actor = map("title", f, "label", "flight");
				int source = nodes.indexOf(actor);
				if (source == -1) {
					nodes.add(actor);
					source = i++;
				}
				rels.add(map("source", source, "target", target));
			}
		}
		return map("nodes", nodes, "links", rels);

	}
	
	private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put(key1, value1);
		result.put(key2, value2);
		return result;
	}
}
