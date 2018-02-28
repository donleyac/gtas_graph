package gov.gtas.graph.vo;

public class NoFilterVo {
//{Address: true, Agency: true, Document: true, Flight: true, Passenger: true}
	private boolean flight;
	private boolean address;
	private boolean passenger;
	private boolean document;
	private boolean agency;
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
	public boolean isAgency() {
		return agency;
	}
	public void setAgency(boolean agency) {
		this.agency = agency;
	}
	
}
