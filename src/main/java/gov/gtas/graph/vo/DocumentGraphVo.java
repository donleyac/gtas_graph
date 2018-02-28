package gov.gtas.graph.vo;

public class DocumentGraphVo {

    private Long id;  
	private String documentType;
	private String documentNumber;
	private String expirationDate;
	private String issuanceCountry;
	private String include;
	private String includeFlight;
	private String includeAgency;
	private String includeAddress;
	private String includePhone;
	
	public String getIncludeFlight() {
		return includeFlight;
	}
	public void setIncludeFlight(String includeFlight) {
		this.includeFlight = includeFlight;
	}
	public String getIncludeAgency() {
		return includeAgency;
	}
	public void setIncludeAgency(String includeAgency) {
		this.includeAgency = includeAgency;
	}
	public String getIncludeAddress() {
		return includeAddress;
	}
	public void setIncludeAddress(String includeAddress) {
		this.includeAddress = includeAddress;
	}
	public String getIncludePhone() {
		return includePhone;
	}
	public void setIncludePhone(String includePhone) {
		this.includePhone = includePhone;
	}
	public String getInclude() {
		return include;
	}
	public void setInclude(String include) {
		this.include = include;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
