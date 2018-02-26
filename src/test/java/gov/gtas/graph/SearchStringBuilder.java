package gov.gtas.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import gov.gtas.graph.util.GraphUtil;
import gov.gtas.graph.vo.AddressGraphVo;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SearchStringBuilder {

	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
		String searchString=readJson();
		GraphUtil.convertJsonToObjectMap(searchString);
	/**
		if(StringUtils.isNotBlank(searchString) ){
			
			searchString=searchString.replaceAll("\\s","");
			String[] tokens=searchString.split("},");
			for(String s:tokens){
				s=s.replaceAll("}}", "");
				System.out.println(" token "+s.concat("}"));
			}
			Map<String,Object> searchObjects=new HashMap<>();
			if(searchString.indexOf("Passenger") != -1){
				String key="Passenger";
				String value=searchString.substring(searchString.indexOf(":{")+1, searchString.indexOf("},")+1);
				searchString=searchString.substring(searchString.indexOf("},")+1,searchString.length() );
				//System.out.println(" value "+value);
				//System.out.println(" searchString "+searchString);
				//searchObjects.put(Passenger, searchObjects.su)
				
			}
			if(searchString.indexOf("Address") != -1){
				System.out.println(" Address in begin - "+searchString);
				String key="Address";
				String value=searchString.substring(searchString.indexOf(":{"), searchString.indexOf("},")+1);
				searchString=searchString.substring(searchString.indexOf("},")+1,searchString.length() );
				try {
					System.out.println(" value - "+value);
					if(value.indexOf(":") > -1){
						value=value.substring(1);
						System.out.println(" value 22222222222222- "+value);
					}
					AddressGraphVo avo=mapper.readValue(value, AddressGraphVo.class);
					System.out.println(" ADDRESS"+avo.isInclude());
					//System.out.println(" ADDRESS"+avo.getLine1());
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println(" value "+value);
				//System.out.println(" searchString "+searchString);
				//searchObjects.put(Passenger, searchObjects.su)
				
			}
			if(searchString.indexOf("Agency") != -1){
				String key="Agency";
				String value=searchString.substring(searchString.indexOf(":{")+1, searchString.indexOf("},")+1);
				searchString=searchString.substring(searchString.indexOf("},")+1,searchString.length() );
				//System.out.println(" value "+value);
				//System.out.println(" searchString "+searchString);
				//searchObjects.put(Passenger, searchObjects.su)
				
			}
			if(searchString.indexOf("Flight") != -1){
				String key="Flight";
				String value=searchString.substring(searchString.indexOf(":{")+1, searchString.indexOf("},")+1);
				searchString=searchString.substring(searchString.indexOf("},")+1,searchString.length() );
				//System.out.println(" value "+value);
				//.out.println(" searchString "+searchString);
				//searchObjects.put(Passenger, searchObjects.su)
				
			}
			if(searchString.indexOf("Document") != -1){
				String key="Document";
				String value=searchString.substring(searchString.indexOf(":{")+1, searchString.indexOf("}}")+1);
				searchString=searchString.substring(searchString.indexOf("},")+1,searchString.length()-1);
				//System.out.println(" value "+value);
				//System.out.println(" searchString "+searchString);
				//searchObjects.put(Passenger, searchObjects.su)
				
			}
		}**/
		
	}
	@SuppressWarnings("finally")
	private static String readJson(){
		BufferedReader br = null;
		FileReader fr = null;
		StringBuilder sb = new StringBuilder();
		try {

			//br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader("C:\\NEO4J\\JsonFile1.txt");
			br = new BufferedReader(fr);

			String sCurrentLine;
			
			while ((sCurrentLine = br.readLine()) != null) {
				//System.out.println(sCurrentLine);
				sb.append(sCurrentLine);
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
			return sb.toString();
		}


	}
	//
}
