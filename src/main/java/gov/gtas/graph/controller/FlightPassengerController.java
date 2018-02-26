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

import gov.gtas.graph.domain.PassengerGraph;
import gov.gtas.graph.services.AgencyGraphService;
import gov.gtas.graph.services.FlightGraphService;
import gov.gtas.graph.services.FlightPaxGraphService;
import gov.gtas.graph.services.PassengerGraphService;
import gov.gtas.graph.util.GraphUtil;

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
		return service.graph(limit == null ? 5 : limit);
	}

	@RequestMapping("/searchFlight")
	public Map<String,Object> findByFlightNumber(@Param("flightNumber") String flightNumber){
		return flightGraphService.findByFlightNumber(flightNumber);
	}

	@RequestMapping("/search")
	public Collection<PassengerGraph> searchDocumentByCriteria(@Param("documentNumber") String number){
		//TODO remove
		number="52263000";
		List<PassengerGraph> passengers=(List<PassengerGraph>) passengerGraphService.findByDocumentNumber(number);
		//for(PassengerGraph p:passengers){
		//}
		return passengers;
	}

	
	@RequestMapping("/passengers")
	public Map<String, Object> getPassengers(@RequestParam(value = "limit",required = false) Integer limit){
		//TODO remove
		limit=300;
		Map<String, Object> passengers= passengerGraphService.findFullPassengerGraph(limit);

		return passengers;
	}
	
	@RequestMapping("/agencies")
	public Map<String, Object> getPassengersByAgency(@RequestParam(value = "limit",required = false) Integer limit){
		//TODO remove
		limit=10;
		Map<String, Object> agencies=agencyGraphService.agencyGraph(limit);
		
		return agencies;
	}
	
	@RequestMapping("/agencyGraph")
	public Map<String, Object> getPassengersFlightsByAgency(@RequestParam(value = "limit",required = false) Integer limit){
		//TODO remove
		limit=10;
		Map<String, Object> agencies=agencyGraphService.agencyFullGraph(limit);
		
		return agencies;
	}
	@RequestMapping("/searchByCriteria")
	public Map<String, Object> searchPassengersByCriteria(@RequestParam(value = "query",required = false) String query){
	
		Map<String, Object> searchMap=GraphUtil.convertJsonToObjectMap(query);
		if(searchMap != null && searchMap.containsKey("Passenger")){
			Map<String, Object> passengers= passengerGraphService.findFilteredPassengerGraph(searchMap);
			return passengers;
		}

		return null;
	}

}
