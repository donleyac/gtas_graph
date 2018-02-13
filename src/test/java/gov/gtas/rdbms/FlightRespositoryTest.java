package gov.gtas.rdbms;

import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import gov.gtas.graph.repositories.AddressGraphRepository;
import gov.gtas.graph.repositories.AgencyGraphRepository;
import gov.gtas.graph.repositories.CreditCardGraphRepository;
import gov.gtas.graph.repositories.DocumentGraphRepository;
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
	
	@Autowired
	private DocumentGraphRepository documentGraphRepository;
	
	@Autowired
	private AddressGraphRepository addressGraphRepository;
	
	@Autowired
	private AgencyGraphRepository agencyGraphRepository;	
	
	@Autowired
	private CreditCardGraphRepository ccGraphRepository;	
	
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
		documentGraphRepository.deleteAll();
		addressGraphRepository.deleteAll();
		agencyGraphRepository.deleteAll();
		ccGraphRepository.deleteAll();
		
		List<Flight> flights=flightRepository.findAll();
		
		if(flights != null && flights.size() >0){
			for(Flight f:flights){
			
			FlightGraph flight = new FlightGraph(f.getFullFlightNumber(),f.getFlightDate().toString(),
					f.getOrigin(),f.getDestination(),f.getOriginCountry(),f.getDestinationCountry());
			//FlightPaxGraph fpg = new FlightPaxGraph();
			flight.setMariaId(f.getId());
			FlightGraph existflight=instance.findByMariaId(f.getId());
			if(existflight != null && existflight.getId() != null){
				flight=existflight;
			}
			instance.save(flight);
			//fpg.setFlight(flight);
			if(f.getPassengerCount() != null && f.getPassengerCount() > 0){
				for(Passenger p:f.getPassengers()){
					
					PassengerGraph pg = new PassengerGraph(p.getFirstName(),p.getLastName(),
							p.getGender(),p.getDob().toString());
					
			
					pg.setMariaId(p.getId());
					PassengerGraph existing=passengerRepository.findByMariaId(pg.getMariaId());
					if(existing != null && existing.getId() != null){
						pg=existing;
					}
					passengerRepository.save(pg);
					pg.getFlights().add(flight);

					if(p.getDocuments() != null){
						for(Document d:p.getDocuments()){
							DocumentGraph dg = new DocumentGraph();
							DocumentGraph cdg=documentGraphRepository.findByMariaId(d.getId());
							if(cdg != null && cdg.getId()!= null){
								System.out.println("Document Exists");
								dg=cdg;
							}else{
								dg.setMariaId(d.getId());
								dg.setDocumentNumber(d.getDocumentNumber());
								dg.setDocumentType(d.getDocumentType());
								dg.setExpirationDate(d.getExpirationDate() == null?"0":d.getExpirationDate().toString());
							}
							documentGraphRepository.save(dg);
							//pg.getDocuments().add(dg);
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
				AddressGraph cag=addressGraphRepository.findByMariaId(a.getId());
				if(cag != null && cag.getId() != null){
					ag=cag;
				}else{
					ag.setMariaId(a.getId());
					ag.setCity(a.getCity());
					ag.setCountry(a.getCountry());
					ag.setLine1(a.getLine1());
					ag.setLine2(a.getLine2());
					ag.setPostalCode(a.getPostalCode());
					ag.setState(a.getState());					
				}
				addressGraphRepository.save(ag);
				ag.getPassengers().add(p);
			}
		}
		if(pnr.getAgencies() != null && pnr.getAgencies().size()>0){
			for(Agency agency:pnr.getAgencies()){
				AgencyGraph ang=new AgencyGraph();
				AgencyGraph cang=agencyGraphRepository.findByMariaId(agency.getId());
				if(cang != null && cang.getId() != null){
					ang=cang;
				}else{
					ang.setCity(agency.getCity());
					ang.setCountry(agency.getCountry());
					ang.setIdentifier(agency.getIdentifier());
					ang.setLocation(agency.getLocation());
					ang.setName(agency.getName());
					ang.setPhone(agency.getPhone());
					ang.setType(agency.getType());
					//BeanUtils.copyProperties(agency, ang);
					ang.setMariaId(agency.getId());
				}
				agencyGraphRepository.save(ang);
				ang.getPassengers().add(p);
			}
		}
		if(pnr.getCreditCards() != null && pnr.getCreditCards().size()>0){
			for(CreditCard cc:pnr.getCreditCards()){
				CreditCardGraph ccg=new CreditCardGraph();
				CreditCardGraph chcc=ccGraphRepository.findByMariaId(cc.getId());
				if(chcc != null && chcc.getId() != null){
					ccg=chcc;
				}else{
					ccg.setCardType(cc.getCardType());
					if(cc.getExpiration()!= null){
						ccg.setExpiration(cc.getExpiration().toString());
					}
					ccg.setNumber(cc.getNumber());					
				}
				ccGraphRepository.save(ccg);
				//p.getCreditCards().add(ccg);
				ccg.getPassengers().add(p);
			}
		}
		/**
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
		}**/
	}
}
