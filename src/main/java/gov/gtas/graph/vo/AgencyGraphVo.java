package gov.gtas.graph.vo;

import java.util.ArrayList;
import java.util.List;

public class AgencyGraphVo {
	private Long id;
    private String name;
    private String location;
    private String identifier;
    private String country;
    private String phone;
    private String city;
    private String type;
    private Long mariaId;
    private List<PassengerGraphVo> passengers=new ArrayList<>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public List<PassengerGraphVo> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<PassengerGraphVo> passengers) {
		this.passengers = passengers;
	}
	
}
