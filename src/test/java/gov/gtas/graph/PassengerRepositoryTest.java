package gov.gtas.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import gov.gtas.graph.domain.AddressGraph;
import gov.gtas.graph.domain.FlightGraph;
import gov.gtas.graph.domain.FlightPaxGraph;
import gov.gtas.graph.domain.PassengerGraph;
import gov.gtas.graph.repositories.FlightGraphRepository;
import gov.gtas.graph.repositories.PassengerGraphRepository;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
public class PassengerRepositoryTest {


	@Autowired
	private Session session;

	@Autowired
	private FlightGraphRepository instance;

	@Autowired
	private PassengerGraphRepository passengerRepository;
	
	//@Before
	//@Transactional
	public void setUp() {
		passengerRepository.deleteAll();
		FlightGraph flight = new FlightGraph("2222","1-16-2018","IAD","JFK","USA","USA");

		instance.save(flight);

		PassengerGraph keanu = new PassengerGraph("John","Vernon","M","10-10-1977");
		//keanu.getFlights().add(flight);
		keanu.getAdresses().add(getAddress());
		PassengerGraph teenu = new PassengerGraph("Ertel","David","M","9-9-1977");
		//teenu.getFlights().add(flight);
		//team.stream().forEach(person -> log.info("\t" + person.toString()));
		teenu.getAdresses().add(getAddress());
		passengerRepository.save(keanu);
		passengerRepository.save(keanu);
		//teenu.TravelsWith(keanu);
		passengerRepository.save(keanu);
		//FlightPax fp = new FlightPax(flight, keanu);
		//fp.addRoleName("FLEW_ON");
		//flight.addFlightPax(fp);
		flight.getPassengers().add(teenu);
		flight.getPassengers().add(teenu);
		instance.save(flight);
	}

	@After
	public void tearDown() {
		//session.purgeDatabase();
	}

	/**
	 * Test of findByTitle method, of class MovieRepository.
	 */
	//@Test
	public void testFindByFirstName() {

		String name = "John";
		PassengerGraph result = passengerRepository.findByFirstName(name);
		System.out.println("RESULT :"+result.getLastName());
		assertNotNull(result);
		assertEquals("Vernon", result.getLastName());
	}
	private AddressGraph getAddress(){
		AddressGraph a = new AddressGraph();
		a.setLine1("123 Main Street");
		a.setCity("ALDIE");
		a.setCountry("USA");
		a.setState("VA");
		a.setPostalCode("20108");
		return a;
	}
}
