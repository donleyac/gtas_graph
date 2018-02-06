package gov.gtas.graph.domain;

import java.util.Objects;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity(label="Agency")
public class AgencyGraph {

	public AgencyGraph(){}
 
	@GraphId
	private Long id;
  
    private String name;
    private String location;
    private String identifier;
   
    private String country;

    private String phone;
    
    private String city;
    
    private String type;
    
    
    public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

    /**
    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.location);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final AgencyGraph other = (AgencyGraph) obj;
        return Objects.equals(this.name, other.name)
                && Objects.equals(this.location, other.location);
    }  **/     
}
