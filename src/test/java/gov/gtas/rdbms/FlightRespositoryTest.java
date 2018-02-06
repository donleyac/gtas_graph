package gov.gtas.rdbms;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import gov.gtas.graph.config.GtasNeoConfiguration;
import gov.gtas.graph.domain.AddressGraph;
import gov.gtas.graph.domain.AgencyGraph;
import gov.gtas.graph.domain.CreditCardGraph;
import gov.gtas.graph.domain.DocumentGraph;
import gov.gtas.graph.domain.EmailGraph;
import gov.gtas.graph.domain.FlightGraph;
import gov.gtas.graph.domain.FlightPaxGraph;
import gov.gtas.graph.domain.PassengerGraph;
import gov.gtas.graph.domain.PhoneGraph;
import gov.gtas.graph.repositories.FlightGraphRepository;
import gov.gtas.graph.repositories.FlightPaxGraphRepository;
import gov.gtas.graph.repositories.PassengerGraphRepository;
import gov.gtas.rdbms.domain.Address;
import gov.gtas.rdbms.domain.Agency;
import gov.gtas.rdbms.domain.CreditCard;
import gov.gtas.rdbms.domain.Document;
import gov.gtas.rdbms.domain.Email;
import gov.gtas.rdbms.domain.Flight;
import gov.gtas.rdbms.domain.Passenger;
import gov.gtas.rdbms.domain.Phone;
import gov.gtas.rdbms.domain.Pnr;
import gov.gtas.rdbms.repositories.FlightRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { GtasNeoConfiguration.class })
@TransactionConfiguration(transactionManager = "rdbmsTransactionManager", defaultRollback = true)
@SpringBootTest
@Transactional
public class FlightRespositoryTest {

	public FlightRespositoryTest(){
		
	}
	@Autowired
	private Session session;
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private FlightGraphRepository instance;

	@Autowired
	private PassengerGraphRepository passengerRepository;

	@Autowired
	private FlightPaxGraphRepository flightPaxGraphRepository;
	
	@Before
	@Transactional
	public void setUp() {

	}
	
	@After
	public void tearDown() {
		//session.purgeDatabase();
	}
	
	@Test
	@Transactional
	public void testCreateGraphObejets() {
		instance.deleteAll();
		passengerRepository.deleteAll();
		flightPaxGraphRepository.deleteAll();
		
		List<Flight> flights=flightRepository.findAll();
		
		if(flights != null && flights.size() >0){
			for(Flight f:flights){
			
			FlightGraph flight = new FlightGraph(f.getFullFlightNumber(),f.getFlightDate().toString(),
					f.getOrigin(),f.getDestination(),f.getOriginCountry(),f.getDestinationCountry());
			//FlightPaxGraph fpg = new FlightPaxGraph();
			//fpg.setFlight(flight);
			instance.save(flight);
			
			if(f.getPassengerCount() != null && f.getPassengerCount() > 0){
				for(Passenger p:f.getPassengers()){
					
					PassengerGraph pg = new PassengerGraph(p.getFirstName(),p.getLastName(),
							p.getGender(),p.getDob().toString());
					//pg.getFlights().add(flight);
					pg.setFlightId(flight.getId());
					//passengerRepository.save(pg);
					flight.getPassengers().add(pg);
					//System.out.println("ADDED :"+p.getFirstName());
					if(p.getDocuments() != null){
						for(Document d:p.getDocuments()){
							DocumentGraph dg = new DocumentGraph();
							dg.setDocumentNumber(d.getDocumentNumber());
							dg.setDocumentType(d.getDocumentType());
							dg.setExpirationDate(d.getExpirationDate() == null?"0":d.getExpirationDate().toString());
							pg.getDocuments().add(dg);
						}
					}
					if(p.getPnrs() != null && p.getPnrs().size() >0){
						for(Pnr pnr:p.getPnrs()){
							updatePassenger(pnr,pg);
						}
					}
					passengerRepository.save(pg);
					//fpg.setPassenger(pg);
				}
				instance.save(flight);

			}
			//flightPaxGraphRepository.save(fpg);
		}
		}
		
		assertNotNull(flights);
	}
	private void updatePassenger(Pnr pnr,PassengerGraph p){
		
		if(pnr.getAddresses() != null && pnr.getAddresses().size() >0){
			for(Address a:pnr.getAddresses()){
				AddressGraph ag=new AddressGraph();
				ag.setCity(a.getCity());
				ag.setCountry(a.getCountry());
				ag.setLine1(a.getLine1());
				ag.setLine2(a.getLine2());
				ag.setPostalCode(a.getPostalCode());
				ag.setState(a.getState());
				p.getAdresses().add(ag);
			}
		}
		if(pnr.getAgencies() != null && pnr.getAgencies().size()>0){
			for(Agency agency:pnr.getAgencies()){
				AgencyGraph ang=new AgencyGraph();
				BeanUtils.copyProperties(agency, ang);
				p.getAgencies().add(ang);
			}
		}
		if(pnr.getCreditCards() != null && pnr.getCreditCards().size()>0){
			for(CreditCard cc:pnr.getCreditCards()){
				CreditCardGraph ccg=new CreditCardGraph();
				//BeanUtils.copyProperties(cc, ccg);
				//ccg.setAccountHolder(cc.getAccountHolder());
				//ccg.setAccountHolderAddress(cc.getAccountHolderAddress());
				//ccg.setAccountHolderPhone(cc.getAccountHolderPhone());
				ccg.setCardType(cc.getCardType());
				if(cc.getExpiration()!= null){
					ccg.setExpiration(cc.getExpiration().toString());
				}
				ccg.setNumber(cc.getNumber());
				p.getCreditCards().add(ccg);
			}
		}
		if(pnr.getPhones() != null && pnr.getPhones().size()>0){
			for(Phone ph : pnr.getPhones()){
				PhoneGraph phg=new PhoneGraph();
				phg.setNumber(ph.getNumber());
				p.getPhones().add(phg);
			}
		}
		
		if(pnr.getEmails() != null && pnr.getEmails().size()>0){
			for(Email e : pnr.getEmails()){
				EmailGraph eg=new EmailGraph();
				eg.setAddress(e.getAddress());
				eg.setDomain(e.getDomain());
				p.getEmails().add(eg);
			}
		}
	}
}
