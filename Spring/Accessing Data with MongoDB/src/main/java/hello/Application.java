package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*



@SpringBootApplication is a convenience annotation that adds all of the following:

 - @Configuration tags the class as a source of bean definitions for the application context.

 - @EnableAutoConfiguration tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.

 - Normally you would add @EnableWebMvc for a Spring MVC app, but Spring Boot adds it automatically when it sees spring-webmvc on the classpath. This flags the application as a web application and activates key behaviors such as setting up a DispatcherServlet.

 - @ComponentScan tells Spring to look for other components, configurations, and services in the the hello package, allowing it to find the controllers.




The main() method uses Spring Boot’s SpringApplication.run() method to launch an application. Did you notice that there wasn’t a single line of XML? No web.xml file either. This web application is 100% pure Java and you didn’t have to deal with configuring any plumbing or infrastructure.

Spring Boot will handle those repositories automatically as long as they are included in the same package (or a sub-package) of your @SpringBootApplication class. For more control over the registration process, you can use the @EnableMongoRepositories annotation.

---
By default, @EnableMongoRepositories will scan the current package for any interfaces that extend one of Spring Data’s repository interfaces. Use it’s basePackageClasses=MyRepository.class to safely tell Spring Data MongoDB to scan a different root package by type if your project layout has multiple projects and its not finding your repositories. 
---

Spring Data MongoDB uses the MongoTemplate to execute the queries behind your find* methods. You can use the template yourself for more complex queries, but this guide doesn’t cover that.


Application includes a main() method that autowires an instance of CustomerRepository: Spring Data MongoDB dynamically creates a proxy and injects it there. We use the CustomerRepository through a few tests. First, it saves a handful of Customer objects, demonstrating the save() method and setting up some data to work with. Next, it calls findAll() to fetch all Customer objects from the database. Then it calls findByFirstName() to fetch a single Customer by her first name. Finally, it calls findByLastName() to find all customers whose last name is "Smith".

---
Spring Boot by default attempts to connect to a locally hosted instance of MongoDB. Read the reference docs (http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-mongodb) for details on pointing your app to an instance of MongoDB hosted elsewhere. 
---
*/

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		repository.deleteAll();

		// save a couple of customers
		repository.save(new Customer("Alice", "Smith"));
		repository.save(new Customer("Bob", "Smith"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByFirstName("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Customer customer : repository.findByLastName("Smith")) {
			System.out.println(customer);
		}

	}

}
