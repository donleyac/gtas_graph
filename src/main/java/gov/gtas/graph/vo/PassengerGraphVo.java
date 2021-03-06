package gov.gtas.graph.vo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PassengerGraphVo {
	private Long id;
	private String include;
	private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String citizenshipCountry;
    private String residencyCountry;
    private String dob;
    private Long mariaId;
    private  List<FlightGraphVo> flights=new ArrayList<>();
	private  List<DocumentGraphVo> documents=new ArrayList<>();
	private  Set<PhoneGraphVo> phones=new HashSet<>();
	private  Set<EmailGraphVo> emails=new HashSet<>();
	
    public String getInclude() {
		return include;
	}
	public void setInclude(String include) {
		this.include = include;
	}
	public Set<PhoneGraphVo> getPhones() {
		return phones;
	}
	public void setPhones(Set<PhoneGraphVo> phones) {
		this.phones = phones;
	}
	public Set<EmailGraphVo> getEmails() {
		return emails;
	}
	public void setEmails(Set<EmailGraphVo> emails) {
		this.emails = emails;
	}
	public List<DocumentGraphVo> getDocuments() {
		return documents;
	}
	public void setDocuments(List<DocumentGraphVo> documents) {
		this.documents = documents;
	}

    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Long getMariaId() {
		return mariaId;
	}
	public void setMariaId(Long mariaId) {
		this.mariaId = mariaId;
	}
	public List<FlightGraphVo> getFlights() {
		return flights;
	}
	public void setFlights(List<FlightGraphVo> flights) {
		this.flights = flights;
	}
    
}
