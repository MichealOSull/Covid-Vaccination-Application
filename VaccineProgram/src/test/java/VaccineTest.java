import static org.junit.jupiter.api.Assertions.*;
import models.*;
import org.junit.jupiter.api.Test;


class VaccineTest {

	@Test
	public void createName(){
        Name n1=new Name();
        n1.setFirstName("Barry");
        n1.setLastName("Murphy");

        assertEquals("Barry",n1.getFirstName());
        assertEquals("Murphy",n1.getLastName());
 
	}
	
	
	@Test
	public void createClient() {
		Client c1=new Client();
		c1.setId("4");
		c1.setFirstName("David");
		c1.setLastName("Ryan");
		c1.setPhone("0871234567");
		c1.setEmail("david@david.ie");

		assertEquals("4",c1.getId());
		assertEquals("David",c1.getFirstName());
		assertEquals("Ryan",c1.getLastName());
		assertEquals("0871234567",c1.getPhone());
		assertEquals("david@david.ie",c1.getEmail());
		
	}
	
	@Test
	public void createClient1() {
		Client c2=new Client();
		c2.setId("4");
		c2.setFirstName("David");
		c2.setLastName("Ryan");
		c2.setPhone("0871234567");
		c2.setEmail("david@david.ie");

		assertEquals("4",c2.getId());
		assertEquals("David",c2.getFirstName());
		assertEquals("Barry",c2.getLastName());  //fail
		assertEquals("0871234588",c2.getPhone());  //fail
		assertEquals("david@david.ie",c2.getEmail());
	}
	
}


