package gov.gtas.graph.vo;

public class PassengerFilterVo {
	private boolean address=true;
	private boolean agency=true;
	private boolean flight=true;
	private boolean document=true;
	private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String citizenshipCountry;
    private String residencyCountry;
    private String dob;
    private Long mariaId;
    
	public boolean isAddress() {
		return address;
	}
	public void setAddress(boolean address) {
		this.address = address;
	}
	public boolean isAgency() {
		return agency;
	}
	public void setAgency(boolean agency) {
		this.agency = agency;
	}
	public boolean isFlight() {
		return flight;
	}
	public void setFlight(boolean flight) {
		this.flight = flight;
	}
	public boolean isDocument() {
		return document;
	}
	public void setDocument(boolean document) {
		this.document = document;
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
    
    
}
