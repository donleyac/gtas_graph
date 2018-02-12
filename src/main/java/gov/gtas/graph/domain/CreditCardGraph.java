package gov.gtas.graph.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity(label="CreditCard")
public class CreditCardGraph {
	
    public CreditCardGraph() { }
    
	@GraphId
    private Long id;  
    private String cardType;
    private String number;
    private String expiration;
    private String accountHolder;
    private Long gtasId;
    
    @Relationship(type = "PAID_WITH",direction = Relationship.INCOMING)
    public List<PassengerGraph> passengers=new ArrayList<>();
    
    public List<PassengerGraph> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<PassengerGraph> passengers) {
		this.passengers = passengers;
	}

	public Long getId() {  
        return id;  
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Long getGtasId() {
		return gtasId;
	}

	public void setGtasId(Long gtasId) {
		this.gtasId = gtasId;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
/**
	public String getAccountHolderAddress() {
		return accountHolderAddress;
	}

	public void setAccountHolderAddress(String accountHolderAddress) {
		this.accountHolderAddress = accountHolderAddress;
	}

	public String getAccountHolderPhone() {
		return accountHolderPhone;
	}

	public void setAccountHolderPhone(String accountHolderPhone) {
		this.accountHolderPhone = accountHolderPhone;
	}
	

    @Override
    public int hashCode() {
        return Objects.hash(this.cardType, this.number, this.expiration);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final CreditCardGraph other = (CreditCardGraph) obj;
        return Objects.equals(this.cardType, other.cardType)
                && Objects.equals(this.number, other.number) 
                && Objects.equals(this.expiration, other.expiration);
    }  **/       
}
