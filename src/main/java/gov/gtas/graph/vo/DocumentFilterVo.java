package gov.gtas.graph.vo;

public class DocumentFilterVo {
	private boolean address;
	private boolean agency;
	private boolean flight;
	private boolean passenger;
	private String documentType;
	private String documentNumber;
	private String expirationDate;
	private String issuanceCountry;
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
	public boolean isPassenger() {
		return passenger;
	}
	public void setPassenger(boolean passenger) {
		this.passenger = passenger;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getIssuanceCountry() {
		return issuanceCountry;
	}
	public void setIssuanceCountry(String issuanceCountry) {
		this.issuanceCountry = issuanceCountry;
	}
	
	
}
