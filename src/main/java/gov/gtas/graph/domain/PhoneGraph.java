package gov.gtas.graph.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity(label="Phone")
public class PhoneGraph {
	
	public PhoneGraph(){}
	
	@GraphId
    private Long id;  
    private String number;
    
    public Long getId() {  
        return id;  
    }

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
