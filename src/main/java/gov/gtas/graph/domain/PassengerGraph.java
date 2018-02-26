package gov.gtas.graph.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity(label="Passenger")
public class PassengerGraph {

	public PassengerGraph(){
		
	}
	
	public PassengerGraph(String fName,String lName,String gender,String dob){
	    this.firstName=fName;
	    this.lastName=lName;
	    this.gender=gender;
	    this.dob=dob;
	}
	
	@GraphId
	private Long id;
	private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String citizenshipCountry;
    private String residencyCountry;
    private String dob;
    //private Long flightId;
    private Long mariaId;
    
	@Relationship(type = "FLEW_ON", direction = Relationship.INCOMING)
	private List<FlightGraph> flights = new ArrayList<>();
			
	@Relationship(type = "HAS_A", direction = Relationship.INCOMING)
	private List<DocumentGraph> documents = new ArrayList<>();
	
	@Relationship(type = "LIVED_AT", direction = Relationship.OUTGOING)
	private List<AddressGraph> adresses = new ArrayList<>();

	@Relationship(type = "BOOKED_AT",direction = Relationship.OUTGOING)
	private List<AgencyGraph> agencies = new ArrayList<>();
	/**
	@Relationship(type = "BOOKED_WITH",direction = Relationship.OUTGOING)
	private List<CreditCardGraph> creditCards = new ArrayList<>();
	**/
	@Relationship(type = "PHONE",direction = Relationship.INCOMING)
	private List<PhoneGraph> phones = new ArrayList<>();
	
	@Relationship(type = "EMAIL",direction = Relationship.INCOMING)
	private List<EmailGraph> emails = new ArrayList<>();


	public Long getMariaId() {
		return mariaId;
	}

	public void setMariaId(Long mariaId) {
		this.mariaId = mariaId;
	}

	public List<PhoneGraph> getPhones() {
		return phones;
	}

	public void setPhones(List<PhoneGraph> phones) {
		this.phones = phones;
	}

	public List<EmailGraph> getEmails() {
		return emails;
	}

	public void setEmails(List<EmailGraph> emails) {
		this.emails = emails;
	}

	public List<AgencyGraph> getAgencies() {
		return agencies;
	}

	public void setAgencies(List<AgencyGraph> agencies) {
		this.agencies = agencies;
	}
	/**
	public List<CreditCardGraph> getCreditCards() {
		return creditCards;
	}

	public void setCreditCards(List<CreditCardGraph> creditCards) {
		this.creditCards = creditCards;
	}
  **/
//	@Relationship(type = "CO_TRAVELLER", direction = Relationship.UNDIRECTED)
//	public Set<PassengerGraph> coTravellers;
//
//	public void TravelsWith(PassengerGraph person) {
//		if (coTravellers == null) {
//			coTravellers = new HashSet<>();
//		}
//		coTravellers.add(person);
//	}

	public List<AddressGraph> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<AddressGraph> adresses) {
		this.adresses = adresses;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<FlightGraph> getFlights() {
		return flights;
	}

	public void setFlights(List<FlightGraph> flights) {
		this.flights = flights;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCitizenshipCountry() {
		return citizenshipCountry;
	}

	public void setCitizenshipCountry(String citizenshipCountry) {
		this.citizenshipCountry = citizenshipCountry;
	}

	public String getResidencyCountry() {
		return residencyCountry;
	}

	public void setResidencyCountry(String residencyCountry) {
		this.residencyCountry = residencyCountry;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public List<DocumentGraph> getDocuments() {
		return documents;
	}

	public void setDocuments(List<DocumentGraph> documents) {
		this.documents = documents;
	}
	/**
	public Set<PassengerGraph> getCoTravellers() {
		return coTravellers;
	}

	public void setCoTravellers(Set<PassengerGraph> coTravellers) {
		this.coTravellers = coTravellers;
	}
**/
	@Override
    public int hashCode() {
       return Objects.hash(this.firstName, this.lastName, this.gender, this.dob);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof PassengerGraph))
            return false;
        final PassengerGraph other = (PassengerGraph)obj;
        return Objects.equals(this.firstName, other.firstName)
                && Objects.equals(this.lastName, other.lastName)
                && Objects.equals(this.gender, other.gender)
                && Objects.equals(this.dob, other.dob);
    }	

}
