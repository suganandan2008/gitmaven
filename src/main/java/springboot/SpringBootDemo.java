package springboot;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/devi")
public class SpringBootDemo {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	DataSource dataSource;

	@Autowired
	CustomerRepository customerRepository;

	@RequestMapping("/vignesh")
	@GetMapping // to fetch record
	@PutMapping // update the existing
	@PostMapping // Insert
	@DeleteMapping // delete
	@Transactional(readOnly = true)
	String home() {

		System.out.println("DATASOURCE = " + dataSource);

		Customer c1 = new Customer();
		c1.setDate(new Date());
		c1.setEmail("vignesh@gmail.com");
		c1.setName("Vignesh");

		// Customer c2=customerRepository.save(c1);
		// System.out.println(">>>>>>>"+c2.toString());

		/*
		 * System.out.println("\n1.findAll()..."); for (Customer customer :
		 * customerRepository.findAll()) { System.out.println(customer.toString()); }
		 */

		System.out.println("\n2.findByEmail(String email)...");
		for (Customer customer : customerRepository.findByEmail("vignesh@gmail.com")) {
			System.out.println(customer);
		}

		/*
		 * System.out.println("\n3.findByDate(Date date)..."); try { for (Customer
		 * customer : customerRepository.findByDate(sdf.parse("2017-02-12"))) {
		 * System.out.println(customer); } } catch (ParseException exception) {
		 * exception.printStackTrace(); }
		 */
		// For Stream, need @Transactional
		/*
		 * System.out.
		 * println("\n4.findByEmailReturnStream(@Param(\"email\") String email)...");
		 * try (Stream<Customer> stream =
		 * customerRepository.findByEmailReturnStream("333@yahoo.com")) {
		 * stream.forEach(x -> System.out.println(x)); }
		 */

		//customerRepository.deleteById(new Long(4));
		return "Hello World!";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemo.class, args);

	}

}
