// Write REST controller(s) to process the following HTTP requests
//  Get a list of all customers
// GET /api/customers
// Accept: application/json
// This HTTP endpoint supports the following parameters
//  offset - return the first result from n records from the first; n is the 
// number given by offset parameter
//  limit - return the number of records specified by limit
// The default value for offset is 0 and limit is 5
//  Get the details of a customer with the customer’s id
// GET /api/customer/<customer_id>
// Accept: application/json
// Return a 404 and an appropriate error object if the customer does not exist
//  Get all orders for a customer
// GET /api/customer/<customer_id>/orders
// Accept: application/json
// This endpoint returns an array of orders in JSON. If the customer does not 
// have any orders, the endpoint should return an empty array
// Return a 404 and an appropriate error object if the customer does not exist.


package sg.nus.iss.workshop21.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import sg.nus.iss.workshop21.models.Customer;
import sg.nus.iss.workshop21.models.Order;
import sg.nus.iss.workshop21.repositories.CustomerRepository;

@RestController
@RequestMapping(path = "/api/customer", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerRESTController {

    @Autowired
    private CustomerRepository custRepo;

    @GetMapping()
    public ResponseEntity<String> getAllCustomer(@RequestParam(required = false) String limit,
            @RequestParam(required = false) String offset) {
        // Query the database for the books
        List<Customer> customers = custRepo.getAllCustomers(
                Integer.parseInt(offset), Integer.parseInt(limit));

        // Build the result
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (Customer c : customers)
            arrBuilder.add(c.toJson());
        JsonArray result = arrBuilder.build();
        System.out.println("" + result.toString());
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());

    }

    @GetMapping(path = "{customerId}")
    public ResponseEntity<String> getCustomerById(@PathVariable Integer customerId) {
        System.out.println("get customer by id");
        JsonObject result = null;
        try {
            // Query the database for the books
            Customer customer = custRepo.findCustomerById(customerId);

            // Build the result
            JsonObjectBuilder objBuilder = Json.createObjectBuilder();
            objBuilder.add("customer", customer.toJson());
            result = objBuilder.build();
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error_mesg\": \"record not found\"}");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());

    }

    @GetMapping(path = "{customerId}/orders")
    public ResponseEntity<String> getCustomersOrder(@PathVariable Integer customerId) {
        System.out.println("Get customer's order");
        JsonArray result = null;
        try {
            // Query the database for the books
            List<Order> orders = custRepo.getCustomersOrder(customerId);

            // Build the result
            JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
            for (Order o : orders)
                arrBuilder.add(o.toJson());
            result = arrBuilder.build();
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error_mesg\": \"record not found\"}");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());

    }

}

