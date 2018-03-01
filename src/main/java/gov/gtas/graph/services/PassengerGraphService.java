package gov.gtas.graph.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import gov.gtas.graph.domain.AddressGraph;
import gov.gtas.graph.domain.AgencyGraph;
import gov.gtas.graph.domain.DocumentGraph;
import gov.gtas.graph.domain.EmailGraph;
import gov.gtas.graph.domain.FlightGraph;
import gov.gtas.graph.domain.PassengerGraph;
import gov.gtas.graph.domain.PhoneGraph;
import gov.gtas.graph.repositories.FlightGraphRepository;
import gov.gtas.graph.repositories.PassengerGraphRepository;
import gov.gtas.graph.vo.AddressGraphVo;
import gov.gtas.graph.vo.AgencyGraphVo;
import gov.gtas.graph.vo.DocumentGraphVo;
import gov.gtas.graph.vo.EmailGraphVo;
import gov.gtas.graph.vo.FlightGraphVo;
import gov.gtas.graph.vo.PassengerFilterVo;
import gov.gtas.graph.vo.PassengerGraphVo;
import gov.gtas.graph.vo.PhoneGraphVo;
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
		List<PassengerGraphVo> passengers=mapModelObjectsToVo(result);
		return toD3Format(passengers);
	}
	

	public Map<String, Object> getFilteredPassengerGraph(PassengerFilterVo passenger) {
		Collection<PassengerGraph> result=new ArrayList<>();
		if(passenger.getMariaId() != null ){
			PassengerGraph pg= passengerRepository.findByMariaId(passenger.getMariaId());
			result.add(pg);
		}
		else if(StringUtils.isNotBlank(passenger.getFirstName())){
			PassengerGraph pg= passengerRepository.findByFirstName(passenger.getFirstName());
			result.add(pg);
		}
		else if(StringUtils.isNotBlank(passenger.getLastName())){
			PassengerGraph pg= passengerRepository.findByLastName(passenger.getLastName());
			result.add(pg);
		}
		else{
			result = (Collection<PassengerGraph>) passengerRepository.getFilteredPassengerGraph();			
		}
		result=getFlteredPassengerList(result,passenger);
		List<PassengerGraphVo> passengers=mapModelObjectsToVo(result);
		return toD3Format(passengers);

	}
	public Map<String, Object> getNoFilteredPassengerGraph() {
		Collection<PassengerGraph> result = (Collection<PassengerGraph>) passengerRepository.getFilteredPassengerGraph();
		List<PassengerGraphVo> passengers=mapModelObjectsToVo(result);
		return toD3Format(passengers);
	}
	

	@Transactional
	public Map<String, Object> findFilteredPassengerGraph(Map<String, Object> searchMap){
		boolean flight=false;
		boolean agency=false;
		boolean address=false;
		boolean document=false;
		
		Collection<PassengerGraph> result = (Collection<PassengerGraph>) passengerRepository.getFilteredPassengerGraph();
		Iterator it = searchMap.entrySet().iterator();
	    while (it.hasNext()) {
	        @SuppressWarnings("rawtypes")
			Map.Entry pair = (Map.Entry)it.next();
	        if(pair.getKey().equals("Flight")){
	        	FlightGraphVo vo=(FlightGraphVo) pair.getValue();
	        	if(vo.getInclude().equalsIgnoreCase("true")){
	        		flight=true;
	        	}
	        }
	        if(pair.getKey().equals("Address")){
	        	AddressGraphVo vo=(AddressGraphVo) pair.getValue();
	        	if(vo.getInclude().equalsIgnoreCase("true")){
	        		address=true;
	        	}
	        }
	        if(pair.getKey().equals("Agency")){
	        	AgencyGraphVo vo=(AgencyGraphVo) pair.getValue();
	        	if(vo.getInclude().equalsIgnoreCase("true")){
	        		agency=true;
	        	}
	        }
	        if(pair.getKey().equals("Document")){
	        	DocumentGraphVo vo=(DocumentGraphVo) pair.getValue();
	        	if(vo.getInclude().equalsIgnoreCase("true")){
	        		document=true;
	        	}
	        }	        
	    }
	    for(PassengerGraph pg : result){
	    	if(!flight){
	    		pg.setFlights(new ArrayList());
	    	}
	    	if(!document){
	    		pg.setDocuments(new ArrayList());
	    	}
	    	if(!agency){
	    		pg.setAgencies(new ArrayList());
	    	}
	    	if(!address){
	    		pg.setAdresses(new ArrayList());
	    	}
	    }
	    List<PassengerGraphVo> passengers=mapModelObjectsToVo(result);
		return toD3Format(passengers);
	}
	
	private Map<String, Object> toD3Format(Collection<PassengerGraphVo> passengers) {
		
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();
		Iterator<PassengerGraphVo> result = passengers.iterator();
	
		while (result.hasNext()) {
	
			PassengerGraphVo pGraph = result.next();
			nodes.add(map("data", pGraph, "type", "passenger"));
			for (FlightGraphVo f : pGraph.getFlights()) {
				Map<String, Object> flight = map("data", f, "type", "flight");
				int source = nodes.indexOf(flight);
				if (source == -1) {
					nodes.add(flight);
					//source = source++;
					rels.add(map("source", f.getId(), "target", pGraph.getId()));
				}
			}
			for(DocumentGraphVo d : pGraph.getDocuments()){
				Map<String, Object> doc = map("data", d, "type", "document");
				int source = nodes.indexOf(doc);
				if (source == -1) {
					nodes.add(doc);
					rels.add(map("source", d.getId(), "target", pGraph.getId()));
				}
				
			}
			for(PhoneGraphVo pd : pGraph.getPhones()){
				Map<String, Object> pdoc = map("data", pd, "type", "phone");
				int source = nodes.indexOf(pdoc);
				if (source == -1) {
					nodes.add(pdoc);
					rels.add(map("source", pd.getId(), "target", pGraph.getId()));
				}				
			}
			for(EmailGraphVo eg:pGraph.getEmails()){
				Map<String, Object> edoc = map("data", eg, "type", "email");
				int source = nodes.indexOf(edoc);
				if (source == -1) {
					nodes.add(edoc);
					rels.add(map("source", eg.getId(), "target", pGraph.getId()));
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
	
	public Collection<PassengerGraph> findByDocumentNumber(String dNumber){
		
		return passengerRepository.getPassengersByDocumentNumber(dNumber);
	}
	
	@Transactional(readOnly = true)
	public Collection<PassengerGraph> findPassengers(int limit){
		
		return passengerRepository.getPassengers(limit);
	}
	
	@Transactional(readOnly = true)
	public Map<String, Object> findFullPassengerGraph(int limit){
		//TODO remove
		limit=400;
		Collection<PassengerGraph> result=passengerRepository.getFullPassengerGraph(limit);
		List<PassengerGraphVo> passengers=mapModelObjectsToVo(result);
		return toD3Format(passengers);
	}	
	private List<PassengerGraphVo> mapModelObjectsToVo(Collection<PassengerGraph> passengers){
		List<PassengerGraphVo> mList=new ArrayList<>();
		Iterator<PassengerGraph> result = passengers.iterator();
		while (result.hasNext()) {
			PassengerGraph p=result.next();
			PassengerGraphVo pvo=new PassengerGraphVo();
			//BeanUtils.copyProperties(pGraph, vo);
			pvo.setDob(p.getDob());
			pvo.setFirstName(p.getFirstName());
			pvo.setGender(p.getGender());
			pvo.setId(p.getId());
			pvo.setLastName(p.getLastName());
			pvo.setMariaId(p.getMariaId());
			pvo.setMiddleName(p.getMiddleName());
			for(FlightGraph fg:p.getFlights()){
				FlightGraphVo fvo=new FlightGraphVo();
				fvo.setDebarkation(fg.getDebarkation());
				fvo.setEmbarkation(fg.getEmbarkation());
				fvo.setFlightDate(fg.getFlightDate());
				fvo.setFlightNumber(fg.getFlightNumber());
				fvo.setId(fg.getId());
				pvo.getFlights().add(fvo);
			}
			for(DocumentGraph d : p.getDocuments()){
				DocumentGraphVo dvo = new DocumentGraphVo();
				dvo.setDocumentNumber(d.getDocumentNumber());
				dvo.setDocumentType(d.getDocumentType());
				dvo.setExpirationDate(d.getExpirationDate());
				dvo.setId(d.getId());
				pvo.getDocuments().add(dvo);
			}
			for(EmailGraph e : p.getEmails()){
				EmailGraphVo evo = new EmailGraphVo();
				evo.setAddress(e.getAddress());
				evo.setDomain(e.getDomain());
				evo.setId(e.getId());
				pvo.getEmails().add(evo);
			}
			for(PhoneGraph ph:p.getPhones()){
				PhoneGraphVo phvo=new PhoneGraphVo();
				phvo.setId(ph.getId());
				phvo.setNumber(ph.getNumber());
				pvo.getPhones().add(phvo);
			}
			
			mList.add(pvo);
		}
		return mList;
	}
	private ArrayList<PassengerGraph> getFlteredPassengerList(Collection<PassengerGraph> result,PassengerFilterVo passenger){
		ArrayList<PassengerGraph> filterList=new ArrayList<>();
		for(PassengerGraph p: result){
			if(passenger.isAddress() == false ){
				p.setAdresses(new ArrayList<AddressGraph>());
			}
			if(passenger.isAgency() == false){
				p.setAgencies(new ArrayList<AgencyGraph>());
			}
			if(passenger.isDocument() == false){
				p.setDocuments(new ArrayList<DocumentGraph>());
			}
			if(passenger.isFlight() == false){
				p.setFlights(new ArrayList<FlightGraph>());
			}
			if(StringUtils.isNotBlank(passenger.getGender()) && passenger.getGender().equalsIgnoreCase(p.getGender())) {
				filterList.add(p);
			}
			else{
				filterList.add(p);
			}
		
		}
		return filterList;
	}
}
