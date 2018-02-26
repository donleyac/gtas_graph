package gov.gtas.graph.vo;

import java.util.Objects;


public class PhoneGraphVo {

    private Long id;  
    private String number;
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
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
    
	@Override
    public int hashCode() {
       return Objects.hash(this.number);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof PhoneGraphVo))
            return false;
        final PhoneGraphVo other = (PhoneGraphVo)obj;
        return Objects.equals(this.getNumber(), other.getNumber());
    }	
}
