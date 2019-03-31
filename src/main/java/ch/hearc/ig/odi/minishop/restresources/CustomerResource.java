/*
 * Copyright (c) 2018. Cours Outils de développement intégré, HEG Arc.
 */

package ch.hearc.ig.odi.minishop.restresources;

import ch.hearc.ig.odi.minishop.business.Customer;
import ch.hearc.ig.odi.minishop.exception.CustomerException;
import ch.hearc.ig.odi.minishop.services.PersistenceService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
}

