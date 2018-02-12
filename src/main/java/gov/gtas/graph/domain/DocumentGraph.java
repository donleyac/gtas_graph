package gov.gtas.graph.domain;


import java.util.Objects;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity(label="Document")
public class DocumentGraph {

	public DocumentGraph(){	}
	
	@GraphId
    private Long id;  
    
	private String documentType;
	private String documentNumber;
	private String expirationDate;
	private String issuanceCountry;
	private Long gtasDocId;
	
    public Long getGtasDocId() {
		return gtasDocId;
	}

	public void setGtasDocId(Long gtasId) {
		this.gtasDocId = gtasId;
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
/**
	@Override
	public int hashCode() {
		return Objects.hash( this.documentNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof DocumentGraph))
			return false;
		final DocumentGraph other = (DocumentGraph) obj;
		return Objects.equals(this.documentNumber, other.documentNumber);
	}	
**/
}
