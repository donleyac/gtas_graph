package gov.gtas.graph.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@RelationshipEntity(type = "FLEW_ON")
public class FlightPaxGraph {

	@GraphId
	private Long id;
	
	@EndNode
	private FlightGraph flight;
	
	@StartNode
	private PassengerGraph passenger;
	

	public FlightPaxGraph(FlightGraph f,PassengerGraph p){
		this.flight=f;
		this.passenger=p;
	}
	public FlightPaxGraph(){
		
	}

	public Long getId() {
		return id;
	}
	public FlightGraph getFlight() {
		return flight;
	}
	public PassengerGraph getPassenger() {
		return passenger;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setFlight(FlightGraph flight) {
		this.flight = flight;
	}
	public void setPassenger(PassengerGraph passenger) {
		this.passenger = passenger;
	}
	/**
	@Override
    public int hashCode() {
       return Objects.hash(this.flight, this.passenger);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof FlightPaxGraph))
            return false;
        final FlightPaxGraph other = (FlightPaxGraph)obj;
        return Objects.equals(this.flight, other.flight)
                && Objects.equals(this.passenger, other.passenger);
    }		
**/
}
