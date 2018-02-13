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

import gov.gtas.graph.domain.AgencyGraph;
import gov.gtas.graph.domain.PassengerGraph;
import gov.gtas.graph.repositories.AgencyGraphRepository;


@Service
public class AgencyGraphService {
	
	@Autowired
	AgencyGraphRepository agencyRepository;

	@Transactional(readOnly = true)
	public Map<String, Object>  agencyGraph(int limit) {

		Collection<AgencyGraph> agencies = agencyRepository.findPassengersByAgency(limit);
	
		return toD3Format(agencies);
	}
	
	private Map<String, Object> toD3Format(Collection<AgencyGraph> agencies) {
		
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();
		int i = 0;
		Iterator<AgencyGraph> result = agencies.iterator();
		while (result.hasNext()) {
			AgencyGraph pGraph = result.next();
			nodes.add(map("data", pGraph, "type", "agency"));
			int target = i;
			i++;
			
	
			for (PassengerGraph p : pGraph.getPassengers()) {
				Map<String, Object> actor = map("id", p, "type", "passenger");
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