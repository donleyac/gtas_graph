package gov.gtas.graph.vo;

import java.util.ArrayList;
import java.util.List;

import gov.gtas.graph.domain.PassengerGraph;

public class FlightGraphVo {
	private Long id;
    private String flightNumber;
    private String flightDate;
    private String embarkation;
    private String debarkation;
    private String embarkCountry;
    private String debarkCountry;
    private Long mariaId;
    private String include;
	private List<PassengerGraphVo> passengers = new ArrayList<>();
	
	public List<PassengerGraphVo> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<PassengerGraphVo> passengers) {
		this.passengers = passengers;
	}
	public String getInclude() {
		return include;
	}
	public void setInclude(String include) {
		this.include = include;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getFlightDate() {
		return flightDate;
	}
	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}
	public String getEmbarkation() {
		return embarkation;
	}
	public void setEmbarkation(String embarkation) {
		this.embarkation = embarkation;
	}
	public String getDebarkation() {
		return debarkation;
	}
	public void setDebarkation(String debarkation) {
		this.debarkation = debarkation;
	}
	public String getEmbarkCountry() {
		return embarkCountry;
	}
	public void setEmbarkCountry(String embarkCountry) {
		this.embarkCountry = embarkCountry;
	}
	public String getDebarkCountry() {
		return debarkCountry;
	}
	public void setDebarkCountry(String debarkCountry) {
		this.debarkCountry = debarkCountry;
	}
	public Long getMariaId() {
		return mariaId;
	}
	public void setMariaId(Long mariaId) {
		this.mariaId = mariaId;
	}
    
    
}
