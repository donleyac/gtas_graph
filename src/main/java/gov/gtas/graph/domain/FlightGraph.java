package gov.gtas.graph.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.StartNode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import gov.gtas.rdbms.domain.Flight;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity(label="Flight")
public class FlightGraph {

	@GraphId
	private Long id;
    private String flightNumber;
    private String flightDate;
    private String embarkation;
    private String debarkation;
    private String embarkCountry;
    private String debarkCountry;
    private Long mariaId;
   
    public FlightGraph(String fn,String fd,String emb,String deb,String emC,String dbC){
    	this.flightNumber=fn;
    	this.flightDate=fd;
    	this.embarkation=emb;
    	this.debarkation=deb;
    	this.embarkCountry=emC;
    	this.debarkCountry=dbC;
    }
    public FlightGraph(){
    	
    }
    
	//@Relationship(type = "FLEW_ON", direction = Relationship.INCOMING)
	//private Set<PassengerGraph> passengers = new HashSet<>();


	public Long getId() {
		return id;
	}

	public Long getMariaId() {
		return mariaId;
	}
	public void setMariaId(Long mariaId) {
		this.mariaId = mariaId;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getEmbarkation() {
		return embarkation;
	}

	public void setEmbarkation(String embarkation) {
		this.embarkation = embarkation;
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

/**
	public List<FlightPax> getPaxRecords() {
		return paxRecords;
	}

	public void setPaxRecords(List<FlightPax> paxRecords) {
		this.paxRecords = paxRecords;
	}
	public void addFlightPax(FlightPax pax) {
		this.addFlightPax(pax);
	}
	public Set<PassengerGraph> getPassengers() {
		return passengers;
	}
	public void setPassengers(Set<PassengerGraph> passengers) {
		this.passengers = passengers;
	}  

	@Override
    public int hashCode() {
       return Objects.hash(this.flightNumber, this.flightDate, this.embarkation, this.debarkation);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof FlightGraph))
            return false;
        final FlightGraph other = (FlightGraph)obj;
        return Objects.equals(this.flightNumber, other.flightNumber)
                && Objects.equals(this.flightDate, other.flightDate)
                && Objects.equals(this.embarkation, other.embarkation)
                && Objects.equals(this.debarkation, other.debarkation);
    }
**/
}
