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
import org.springframework.beans.BeanUtils;
import gov.gtas.graph.domain.AgencyGraph;
import gov.gtas.graph.domain.FlightGraph;
import gov.gtas.graph.domain.PassengerGraph;
import gov.gtas.graph.repositories.AgencyGraphRepository;
import gov.gtas.graph.vo.AgencyGraphVo;
import gov.gtas.graph.vo.FlightGraphVo;
import gov.gtas.graph.vo.PassengerGraphVo;


@Service
public class AgencyGraphService {
	
	@Autowired
	AgencyGraphRepository agencyRepository;

	@Transactional(readOnly = true)
	public Map<String, Object>  agencyGraph(int limit) {

		Collection<AgencyGraph> agencies = agencyRepository.findPassengersByAgency(limit);
		List<AgencyGraphVo> modfiedList=mapModelObjectsToVo(agencies);
		return toD3Format(modfiedList);
	}
	
	@Transactional(readOnly = true)
	public Map<String, Object>  agencyFullGraph(int limit) {

		Collection<AgencyGraph> agencies = agencyRepository.findPassengersFlightsByAgency(limit);
		List<AgencyGraphVo> modfiedList=mapModelObjectsToVo(agencies);
		return toD3Format(modfiedList);
		//return agencies;
	}
	
	private List<AgencyGraphVo> mapModelObjectsToVo(Collection<AgencyGraph> agencies){
		List<AgencyGraphVo> mList=new ArrayList<>();
		Iterator<AgencyGraph> result = agencies.iterator();
		while (result.hasNext()) {
			AgencyGraph pGraph = result.next();
			AgencyGraphVo vo=new AgencyGraphVo();
			//BeanUtils.copyProperties(pGraph, vo);
			vo.setCity(pGraph.getCity());
			vo.setCountry(pGraph.getCountry());
			vo.setId(pGraph.getId());
			vo.setLocation(pGraph.getLocation());
			vo.setMariaId(pGraph.getMariaId());
			vo.setName(pGraph.getName());
			vo.setPhone(pGraph.getPhone());
			vo.setType(pGraph.getType());
			for (PassengerGraph p : pGraph.getPassengers()) {
				PassengerGraphVo pvo=new PassengerGraphVo();
				//BeanUtils.copyProperties(pGraph, vo);
				pvo.setDob(p.getDob());
				pvo.setFirstName(p.getFirstName());
				pvo.setGender(p.getGender());
				pvo.setId(p.getId());
				pvo.setLastName(p.getLastName());
				pvo.setMariaId(p.getMariaId());
				pvo.setMiddleName(p.getMiddleName());
				vo.getPassengers().add(pvo);
				for(FlightGraph fg:p.getFlights()){
					FlightGraphVo fvo=new FlightGraphVo();
					fvo.setDebarkation(fg.getDebarkation());
					fvo.setEmbarkation(fg.getEmbarkation());
					fvo.setFlightDate(fg.getFlightDate());
					fvo.setFlightNumber(fg.getFlightNumber());
					fvo.setId(fg.getId());
					pvo.getFlights().add(fvo);
				}
			}
			
			mList.add(vo);
		}
		return mList;
	}
//	private Map<String, Object> toD3Format(Collection<AgencyGraph> agencies) {
	private Map<String, Object> toD3Format(Collection<AgencyGraphVo> agencies) {		
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();
		int i = 0;
		Iterator<AgencyGraphVo> result = agencies.iterator();
		while (result.hasNext()) {
			AgencyGraphVo pGraph = result.next();
			nodes.add(map("data", pGraph, "type", "agency"));
			int target = i;
			i++;

			for (PassengerGraphVo p : pGraph.getPassengers()) {
				Map<String, Object> actor = map("data", p, "type", "passenger");
				int source = nodes.indexOf(actor);
				if (source == -1) {
					nodes.add(actor);
					source = i++;
					for(FlightGraphVo fvo:p.getFlights()){
						int newSource=i++;
						int newTarget=source;
						Map<String, Object> flight = map("data", fvo, "type", "flight");
						nodes.add(flight);
						newSource = i++;
						rels.add(map("source", fvo.getId(), "target", p.getId()));
					}
				rels.add(map("source", p.getId(), "target", pGraph.getId()));
		
				}
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