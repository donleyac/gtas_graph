package gov.gtas.graph.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gov.gtas.graph.domain.PassengerGraph;
import gov.gtas.graph.services.FlightGraphService;
import gov.gtas.graph.services.FlightPaxGraphService;
import gov.gtas.graph.services.PassengerGraphService;

@RestController("/gtas-graph")
public class FlightPassengerController {

	@Autowired
	FlightPaxGraphService service;

	@Autowired
	PassengerGraphService passengerGraphService;
	
	@Autowired
	FlightGraphService flightGraphService;
	
	@RequestMapping("/graph")
	public Map<String, Object> getFlightGraph(@RequestParam(value = "limit",required = false) Integer limit) {
		return service.graph(limit == null ? 100 : limit);
	}
	
	@RequestMapping("/searchFlight")
	public Map<String,Object> findByFlightNumber(@Param("flightNumber") String flightNumber){
		return flightGraphService.findByFlightNumber(flightNumber);
	}
	
	@RequestMapping("/search")
	public Collection<PassengerGraph> searchDocumentByCriteria(@Param("documentNumber") String number){
		number="52263000";
		Set<PassengerGraph> passengers=(Set<PassengerGraph>) passengerGraphService.findByDocumentNumber(number);
		for(PassengerGraph p:passengers){
			System.out.println("Passenger : "+p.getFirstName());
			System.out.println("Passenger docs : "+p.getDocuments().size());
		}
		return passengers;
	}
	
}
