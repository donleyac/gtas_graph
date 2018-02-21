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

import gov.gtas.graph.domain.AgencyGraph;
import gov.gtas.graph.domain.DocumentGraph;
import gov.gtas.graph.domain.EmailGraph;
import gov.gtas.graph.domain.FlightGraph;
import gov.gtas.graph.domain.PassengerGraph;
import gov.gtas.graph.domain.PhoneGraph;
import gov.gtas.graph.repositories.FlightGraphRepository;
import gov.gtas.graph.repositories.PassengerGraphRepository;
import gov.gtas.graph.vo.AgencyGraphVo;
import gov.gtas.graph.vo.DocumentGraphVo;
import gov.gtas.graph.vo.EmailGraphVo;
import gov.gtas.graph.vo.FlightGraphVo;
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
}
