package gov.gtas.graph.util;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gtas.graph.vo.AddressGraphVo;
import gov.gtas.graph.vo.AgencyGraphVo;
import gov.gtas.graph.vo.PassengerGraphVo;
import gov.gtas.graph.vo.FlightGraphVo;
import gov.gtas.graph.vo.DocumentGraphVo;

public class GraphUtil {

	public static Map<String,Object> convertJsonToObjectMap(String jsonString){
		Map<String,Object> result=new HashMap<>();
		
		if(StringUtils.isNotBlank(jsonString) ){
			jsonString=jsonString.replaceAll("\\s","");
			String[] tokens=jsonString.split("},");
			for(String s:tokens){
				s=s.replaceAll("}}", "");
				s=s.concat("}");
				try {
					Object o=getObjectFromJsonString(s);
					if(o != null ){
						result.put(o.getClass().getSimpleName(), o);
						System.out.println(" ADDED "+o.getClass().getSimpleName());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		return result;
	}
	private static Object getObjectFromJsonString(String json) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Object obj=null;
		if(json.indexOf("Address") != -1){
			String value=json.substring(json.indexOf(":{"), json.indexOf("}")+1);
			json=json.substring(json.indexOf("},")+1,json.length() );
			if(value.indexOf(":") > -1){
				value=value.substring(1);
			}
			AddressGraphVo avo=mapper.readValue(value, AddressGraphVo.class);
			obj=avo;
		}
		if(json.indexOf("Passenger") != -1){
			String value=json.substring(json.indexOf(":{"), json.indexOf("}")+1);
			json=json.substring(json.indexOf("},")+1,json.length() );
			if(value.indexOf(":") > -1){
				value=value.substring(1);
			}
			PassengerGraphVo avo=mapper.readValue(value, PassengerGraphVo.class);
			obj=avo;
		}
		if(json.indexOf("Agency") != -1){
			String value=json.substring(json.indexOf(":{"), json.indexOf("}")+1);
			json=json.substring(json.indexOf("},")+1,json.length() );
			if(value.indexOf(":") > -1){
				value=value.substring(1);
			}
			AgencyGraphVo avo=mapper.readValue(value, AgencyGraphVo.class);
			obj=avo;
		}
		if(json.indexOf("Flight") != -1){
			String value=json.substring(json.indexOf(":{"), json.indexOf("}")+1);
			json=json.substring(json.indexOf("},")+1,json.length() );
			if(value.indexOf(":") > -1){
				value=value.substring(1);
			}
			FlightGraphVo avo=mapper.readValue(value, FlightGraphVo.class);
			obj=avo;
		}
		if(json.indexOf("Document") != -1){
			String value=json.substring(json.indexOf(":{"), json.indexOf("}")+1);
			json=json.substring(json.indexOf("},")+1,json.length() );
			if(value.indexOf(":") > -1){
				value=value.substring(1);
			}
			DocumentGraphVo avo=mapper.readValue(value, DocumentGraphVo.class);
			obj=avo;
		}
		return obj;
	}
}
