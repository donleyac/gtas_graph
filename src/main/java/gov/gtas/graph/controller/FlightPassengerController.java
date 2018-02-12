package gov.gtas.graph.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gov.gtas.graph.domain.AgencyGraph;
import gov.gtas.graph.domain.PassengerGraph;
import gov.gtas.graph.services.AgencyGraphService;
import gov.gtas.graph.services.FlightGraphService;
import gov.gtas.graph.services.FlightPaxGraphService;
import gov.gtas.graph.services.PassengerGraphService;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController("/gtas-graph")
public class FlightPassengerController {

	@Autowired
	FlightPaxGraphService service;

	@Autowired
	PassengerGraphService passengerGraphService;

	@Autowired
	FlightGraphService flightGraphService;

	
	@Autowired
	AgencyGraphService agencyGraphService;
	


	@RequestMapping("/graph")
	public Map<String, Object> getFlightGraph(@RequestParam(value = "limit",required = false) Integer limit,HttpServletResponse  response) {
		//response.
		return service.graph(limit == null ? 100 : limit);
	}

	@RequestMapping("/searchFlight")
	public Map<String,Object> findByFlightNumber(@Param("flightNumber") String flightNumber){
		return flightGraphService.findByFlightNumber(flightNumber);
	}

	@RequestMapping("/search")
	public Collection<PassengerGraph> searchDocumentByCriteria(@Param("documentNumber") String number){
		number="52263000";
		List<PassengerGraph> passengers=(List<PassengerGraph>) passengerGraphService.findByDocumentNumber(number);
		for(PassengerGraph p:passengers){
			System.out.println("Passenger : "+p.getFirstName());
			System.out.println("Passenger docs : "+p.getDocuments().size());
		}
		return passengers;
	}

	
	@RequestMapping("/Passengers")
	public Collection<PassengerGraph> getPassengers(@RequestParam(value = "limit",required = false) Integer limit){
		limit=300;
		List<PassengerGraph> passengers=(List<PassengerGraph>) passengerGraphService.findPassengers(limit);
		for(PassengerGraph p:passengers){
			System.out.println("Passenger : "+p.getFirstName());
			System.out.println("Passenger docs : "+p.getFlights().size());
		}
		return passengers;
	}
	
	@RequestMapping("/Agencies")
	public Collection<AgencyGraph> getPassengersByAgency(@RequestParam(value = "limit",required = false) Integer limit){
		limit=300;
		List<AgencyGraph> agencies=(List<AgencyGraph>) ( agencyGraphService.findPassengersByAgency(limit));
		
		return agencies;
	}
	

}
