package gov.gtas.graph.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.gtas.graph.domain.FlightGraph;
import gov.gtas.graph.domain.PassengerGraph;
import gov.gtas.graph.repositories.FlightGraphRepository;

@Service
public class FlightGraphService {

	
	@Autowired
	FlightGraphRepository flightGraphRepository;
	
	@Transactional(readOnly = true)
	public Map<String, Object>  findByFlightNumber(String number) {
		Collection<FlightGraph> result = flightGraphRepository.findByFlightNumber(number);
		return toD3Format(result);
	}
	private Map<String, Object> toD3Format(Collection<FlightGraph> flights) {
		/**
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();
		int i = 0;
		Iterator<FlightGraph> result = flights.iterator();
		while (result.hasNext()) {
			FlightGraph flightGraph = result.next();
		
			nodes.add(map("title", flightGraph.getFlightNumber(), "label", "flight"));
			int target = i;
			i++;
			for (PassengerGraph pass : flightGraph.getPassengers()) {
				Map<String, Object> actor = map("title", pass.getFirstName(), "label", "passenger");
				int source = nodes.indexOf(actor);
				if (source == -1) {
					nodes.add(actor);
					source = i++;
				}
				rels.add(map("source", source, "target", target));
			}
		}
		return map("nodes", nodes, "links", rels);**/
		return null;
	}
	
	private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put(key1, value1);
		result.put(key2, value2);
		return result;
	}	
}
