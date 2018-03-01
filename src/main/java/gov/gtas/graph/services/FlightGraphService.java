package gov.gtas.graph.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.gtas.graph.domain.AddressGraph;
import gov.gtas.graph.domain.AgencyGraph;
import gov.gtas.graph.domain.DocumentGraph;
import gov.gtas.graph.domain.EmailGraph;
import gov.gtas.graph.domain.FlightGraph;
import gov.gtas.graph.domain.PassengerGraph;
import gov.gtas.graph.domain.PhoneGraph;
import gov.gtas.graph.repositories.FlightGraphRepository;
import gov.gtas.graph.vo.DocumentGraphVo;
import gov.gtas.graph.vo.EmailGraphVo;
import gov.gtas.graph.vo.FlightFilterVo;
import gov.gtas.graph.vo.FlightGraphVo;
import gov.gtas.graph.vo.PassengerFilterVo;
import gov.gtas.graph.vo.PassengerGraphVo;
import gov.gtas.graph.vo.PhoneGraphVo;

@Service
public class FlightGraphService {

	
	@Autowired
	FlightGraphRepository flightGraphRepository;
/**	
	@Transactional(readOnly = true)
	public Map<String, Object>  findByFlightNumber(String number) {
		Collection<FlightGraph> result = flightGraphRepository.findByFlightNumber(number);
		return toD3Format(result);
	}
	
	@Transactional(readOnly = true)
	public Map<String, Object>  findFullFlightGraph() {
		Collection<FlightGraph> result = flightGraphRepository.getFullFlightGraph();
		return toD3Format(result);
	}
	**/

	@Transactional(readOnly = true)
	public Map<String, Object> getFlteredFlightGraph(FlightFilterVo vo){
		List<FlightGraph> result = new ArrayList();
		if(vo.getMariaId() != null){
			FlightGraph fg = flightGraphRepository.findByMariaId(vo.getMariaId());
			result.add(fg);
		}
		else if(StringUtils.isNotBlank(vo.getFlightNumber())){
			Collection<FlightGraph> fgs = flightGraphRepository.findByFlightNumber(vo.getFlightNumber());
			result.addAll(fgs);			
		}
		else if(StringUtils.isNotBlank(vo.getFlightDate())){
			Collection<FlightGraph> fgs = flightGraphRepository.findByFlightDate(vo.getFlightDate());
			result.addAll(fgs);				
		}else{
			Collection<FlightGraph> fgs = flightGraphRepository.getFullFlightGraph();
			result.addAll(fgs);	
		}
		result=getFilteredFlightList(result,vo);
		List<FlightGraphVo> flights=mapModelObjectsToVo(result);
		return toD3Format(flights);
	}
	
	private Map<String, Object> toD3Format(Collection<FlightGraphVo> flights) {
	
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();
		Iterator<FlightGraphVo> result = flights.iterator();
		while (result.hasNext()) {
			FlightGraphVo fGraph = result.next();
			nodes.add(map("data", fGraph, "type", "flight"));
			for(PassengerGraphVo pGraph : fGraph.getPassengers()){
				Map<String, Object> pass = map("data", pGraph, "type", "passenger");
				int source = nodes.indexOf(pass);
				if (source == -1) {
					nodes.add(pass);
					rels.add(map("source", pGraph.getId(), "target", fGraph.getId()));
				}
				for(DocumentGraphVo d : pGraph.getDocuments()){
					Map<String, Object> doc = map("data", d, "type", "document");
					int source1 = nodes.indexOf(doc);
					if (source == -1) {
						nodes.add(doc);
						rels.add(map("source", d.getId(), "target", pGraph.getId()));
					}
					
				}
				for(PhoneGraphVo pd : pGraph.getPhones()){
					Map<String, Object> pdoc = map("data", pd, "type", "phone");
					int source1 = nodes.indexOf(pdoc);
					if (source == -1) {
						nodes.add(pdoc);
						rels.add(map("source", pd.getId(), "target", pGraph.getId()));
					}				
				}
				for(EmailGraphVo eg:pGraph.getEmails()){
					Map<String, Object> edoc = map("data", eg, "type", "email");
					int source1 = nodes.indexOf(edoc);
					if (source == -1) {
						nodes.add(edoc);
						rels.add(map("source", eg.getId(), "target", pGraph.getId()));
					}				
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
	
	private List<FlightGraphVo> mapModelObjectsToVo(Collection<FlightGraph> flights){
		List<FlightGraphVo> mList=new ArrayList<>();
		Iterator<FlightGraph> result = flights.iterator();
		while (result.hasNext()) {
			FlightGraph fg = result.next();
			FlightGraphVo fvo=new FlightGraphVo();
			fvo.setDebarkation(fg.getDebarkation());
			fvo.setEmbarkation(fg.getEmbarkation());
			fvo.setFlightDate(fg.getFlightDate());
			fvo.setFlightNumber(fg.getFlightNumber());
			fvo.setId(fg.getId());
			for(PassengerGraph p:fg.getPassengers()){
				PassengerGraphVo pvo=new PassengerGraphVo();
				//BeanUtils.copyProperties(pGraph, vo);
				pvo.setDob(p.getDob());
				pvo.setFirstName(p.getFirstName());
				pvo.setGender(p.getGender());
				pvo.setId(p.getId());
				pvo.setLastName(p.getLastName());
				pvo.setMariaId(p.getMariaId());
				pvo.setMiddleName(p.getMiddleName());
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
				fvo.getPassengers().add(pvo);
			}
			mList.add(fvo);
		}
		return mList;
	}
	
	private ArrayList<FlightGraph> getFilteredFlightList(List<FlightGraph> result,FlightFilterVo flight){
		ArrayList<FlightGraph> filterList=new ArrayList<>();
		for(FlightGraph f: result){
			if(flight.isPassenger() == false){
				f.setPassengers(new ArrayList<PassengerGraph>());
			}
			else{
				for(PassengerGraph p:f.getPassengers()){
					if(flight.isAddress() == false ){
						p.setAdresses(new ArrayList<AddressGraph>());
					}
					if(flight.isAgency() == false){
						p.setAgencies(new ArrayList<AgencyGraph>());
					}	
					if(flight.isDocument() == false){
						p.setDocuments(new ArrayList<DocumentGraph>());
					}				
				}
			}
			filterList.add(f);
		}
		return filterList;
	}
}
