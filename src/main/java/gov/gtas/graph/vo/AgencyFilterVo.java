package gov.gtas.graph.vo;

public class AgencyFilterVo {
	private boolean flight;
	private boolean address;
	private boolean passenger;
	private boolean document;
    private String name;
    private String location;
    private String identifier;
    private String country;
    private String phone;
    private String city;
    private String type;
    private Long mariaId;
    
	public boolean isFlight() {
		return flight;
	}
	public void setFlight(boolean flight) {
		this.flight = flight;
	}
	public boolean isAddress() {
		return address;
	}
	public void setAddress(boolean address) {
		this.address = address;
	}
	public boolean isPassenger() {
		return passenger;
	}
	public void setPassenger(boolean passenger) {
		this.passenger = passenger;
	}
	public boolean isDocument() {
		return document;
	}
	public void setDocument(boolean document) {
		this.document = document;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getMariaId() {
		return mariaId;
	}
	public void setMariaId(Long mariaId) {
		this.mariaId = mariaId;
	}
    
}
