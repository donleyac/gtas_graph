package gov.gtas.graph.vo;

import java.util.Objects;

public class EmailGraphVo {
    private Long id;  
    private String address;
    private String domain;
    private String include;
    
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
    
	@Override
    public int hashCode() {
       return Objects.hash(this.getAddress());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof EmailGraphVo))
            return false;
        final EmailGraphVo other = (EmailGraphVo)obj;
        return Objects.equals(this.getAddress(), other.getAddress());
    }	
}
