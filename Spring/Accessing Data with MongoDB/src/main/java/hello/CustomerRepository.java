package hello;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

/*

CustomerRepository extends the MongoRepository interface and plugs in the type of values and id it works with: Customer and String. Out-of-the-box, this interface comes with many operations, including standard CRUD operations (create-read-update-delete).

*/

    public Customer findByFirstName(String firstName);
    public List<Customer> findByLastName(String lastName);

}

/*



You can define other queries as needed by simply declaring their method signature. In this case, you add findByFirstName, which essentially seeks documents of type Customer and finds the one that matches on firstName.

You also have findByLastName to find a list of people by last name.

In a typical Java application, you write a class that implements CustomerRepository and craft the queries yourself. What makes Spring Data MongoDB so useful is the fact that you don’t have to create this implementation. Spring Data MongoDB creates it on the fly when you run the application.


*/
