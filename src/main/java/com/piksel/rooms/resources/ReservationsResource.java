package com.piksel.rooms.resources;

import com.piksel.rooms.persistence.ReservationDao;
import com.piksel.rooms.representation.Reservation;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/reservations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
@Component
public class ReservationsResource {

    private ReservationDao reservationDao;
    @Inject
    public ReservationsResource(ReservationDao reservationDao){
        this.reservationDao = reservationDao;
    }

    @GET
    public List<Reservation> getAll(){
        return this.reservationDao.findAll();
    }

    @GET
    @Path("{id}")
    public Reservation getOne(@PathParam("id") long id){
        Reservation reservation = reservationDao.findOne(id);
        if(reservation == null)
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        return reservation;
    }

    @POST
    public Reservation save(@Valid Reservation reservation) {
        return reservationDao.save(reservation);
    }

    @PUT
    @Path("{id}")
    public Reservation update(@PathParam("id")long id, @Valid Reservation reservation) {
        if(reservationDao.findOne(id) == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }else {
            reservation.setId(id);
            return reservationDao.save(reservation);
        }
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id")long id) {
        Reservation reservation = reservationDao.findOne(id);
        if(reservation == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }else {
            reservationDao.delete(reservation);
        }
    }

}
