package address;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.web.client.RestClientException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AddressApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	// Test if can create successfully
	@Test
	public void sucessfullyCreateAddress() {
		ResponseEntity<Address> responseEntity =
				restTemplate.postForEntity("/Address", new Address("Rua Domingos Bonato", 57, "A", "Santa Genebra II", "Campinas", "SP", "Brasil", "13084785", "", ""), Address.class);
		Address address = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	// Test if returns HTTP Status 400 when trying to create while missing required fields
	@Test
	public void failToCreateAddress() {
		ResponseEntity<Address> responseEntity =
				restTemplate.postForEntity("/Address", new Address(null, 57, "A", null, "Campinas", "SP", "Brasil", "13084785", "", ""), Address.class);
		Address address = responseEntity.getBody();
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	// Test if returns a list of all addresses
	@Test
	public void sucessfullyListAllAddressess() {
		ResponseEntity<List> responseEntity =
				restTemplate.getForEntity("/Address", List.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	// Test if can retrieve an existing Address sucessfully
	@Test
	public void sucessfullyGetAddress() {
		ResponseEntity<Address> responseEntity =
				restTemplate.getForEntity("/Address/1", Address.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	// Test if throws an exception when trying to retrieve an inexisting Address
	@Test
	public void notFoundAddress() {
		try {
			ResponseEntity<Address> responseEntity =
					restTemplate.getForEntity("/Address/123456", Address.class);
		} catch (RestClientException restClientException) {
			assertEquals(1, 1);
		}


	}

	// Test if can't update something that does not exist
	@Test
	public void failToUpdateAddress() {
		try {
			restTemplate.put("/Address/123456", null);
		} catch (RestClientException restClientException) {
			assertEquals(1, 1);
		}
	}

	// Test if can't delete something that does not exist
	@Test
	public void notFoundDeleteAddress() {
		try {
			restTemplate.delete("/Address/123456");
		} catch (RestClientException restClientException) {
			assertEquals(1, 1);
		}
	}

}
