/*
 * Copyright (c) 2018. Cours Outils de développement intégré, HEG Arc.
 */

package ch.hearc.ig.odi.minishop.restresources;

import ch.hearc.ig.odi.minishop.business.Customer;
import ch.hearc.ig.odi.minishop.exception.CustomerException;
import ch.hearc.ig.odi.minishop.services.PersistenceService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("customer")
public class CustomerResource {
    @Inject
    private PersistenceService persistenceService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Customer> findAllCustomer() {
        return persistenceService.getAllCustomers();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomerByID(@PathParam("id") Long id) throws CustomerException {
        try{
            return persistenceService.getCustomerByID(id);
        }catch (CustomerException ex){
            ex.printStackTrace();
            throw new CustomerException("Customer with id " + id + " not found");
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public void createCustomer(String username, String firstname, String lastname,
                               String email,
                               String phone) {
        persistenceService.createAndPersistCustomer(username, firstname, lastname, email, phone);
    }
}

