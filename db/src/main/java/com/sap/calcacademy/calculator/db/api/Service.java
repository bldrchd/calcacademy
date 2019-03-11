package com.sap.calcacademy.calculator.db.api;


import java.net.URI;
import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.sap.calcacademy.calculator.Calculator;
import com.sap.calcacademy.calculator.db.CalculationQuery;
import com.sap.calcacademy.calculator.db.exceptions.DBFailureException;
import com.sap.calcacademy.calculator.db.model.Calculation;


@Singleton
@Path("/calculate")
public class Service {

    private static final String RESOURCE_TYPE = "expression";
    private static final String SUCCESS = "success";

    @GET
    @Path("/history")
    public Response getAllCalculations() {
        List<Calculation> allCalculations;
        try {
            allCalculations = new CalculationQuery().getAllCalculations();
            GenericEntity<List<Calculation>> entity = new GenericEntity<List<Calculation>>(allCalculations) {
            };
            return Response.ok(entity).build();
        } catch (DBFailureException e) {
            ErrorDTO error = new ErrorDTO(e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }
    }

    @DELETE
    @Path("/cleanup")
    public Response deleteAllCalculations() {
        try {
            new CalculationQuery().deleteAll();
            StatusDTO status = new StatusDTO(SUCCESS);
            return Response.ok(status).build();
        } catch (DBFailureException e) {
            ErrorDTO error = new ErrorDTO(e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }
    }

    @POST
    @Path("/result")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUncalculatedExpressionToDatabase(ExpressionDTO expression, @Context UriInfo uriInfo) throws IllegalArgumentException{
        Calculator calculator = new Calculator();
        if (calculator.calculate(expression.getInput()) != null ){
            ErrorDTO error = new ErrorDTO("Invalid input! Try again.");
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
        try {
            String id = insertCalculationInDB(expression);
            URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();
            return Response.created(uri).entity(new IdDTO(id, uri, RESOURCE_TYPE)).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(new ErrorDTO(e.getMessage())).build();
        }
    }
    

    @GET
    @Path("result/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResultForExpressionById(@PathParam("id") int id) {
        IQuery query = new CalculationQuery();
        Calculation calculation = query.getCalculationById(id);
        if (calculation == null) {
            ErrorDTO error = new ErrorDTO("This id doesn't exist.");
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
        return Response.ok(calculation).build();
    }

    private String insertCalculationInDB(ExpressionDTO requestContent) throws DBFailureException {
        Calculation calculation = new Calculation();
        calculation.setExpression(requestContent.getInput());
        CalculationQuery query = new CalculationQuery();
        query.insertCalculation(calculation);
        int id = calculation.getId();
        return String.valueOf(id);
    }

}
