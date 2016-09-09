
/*
package com.piksel.rooms.resources;

import com.piksel.rooms.persistence.ReservationDao;
import com.piksel.rooms.representation.OreservRequest;
import com.piksel.rooms.representation.Reservation;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/reservations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
@Component
public class ReservationsResource {

    private ReservationDao reservationDao;

    @Inject
    public ReservationsResource(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    @GET
    public List<Reservation> getAll() {
        return this.reservationDao.findAll();
    }

    @GET
    @Path("/")
    public List<Reservation> getAllByDate(@QueryParam("date") String dayString) {
        if(dayString != ""){
            DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
            DateTime dateTime = formatter.parseDateTime(dayString);
            return reservationDao.findReservationsByDay(dateTime);
        }
        return null;
    }

    @GET
    @Path("/")
    public List<Reservation> getAllByDateRange(@QueryParam("dateStart") String dayStartString, @QueryParam("dateEnd") String dayEndString) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
        DateTime dateStart = formatter.parseDateTime(dayStartString);
        DateTime dateEnd = formatter.parseDateTime(dayEndString);

        return reservationDao.findReservationsByDateRange(dateStart, dateEnd);
    }


    @GET
    @Path("{id}")
    public Reservation getOne(@PathParam("id") long id) {
        Reservation reservation = reservationDao.findOne(id);
        if (reservation == null)
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        return reservation;
    }


    @POST
    public Reservation addReservation(@Valid Reservation newReservation) {

        List<Reservation> reservations = reservationDao.findAll();
        if (reservations.size() == 0)
            return reservationDao.save(newReservation);

        for (Reservation reservation : reservations) {

            //provjera da li je trazena soba rezervisana, i da li je rezervacija ponavljajuca
            if (newReservation.getRoomId() == reservation.getRoomId()) {

                //provjera u kojem vremenu je rezervisana
                if (((newReservation.getStart().isBefore(reservation.getStart()) && newReservation.getEnd().isBefore(reservation.getStart()))
                        || (newReservation.getStart().isAfter(reservation.getEnd()) && newReservation.getEnd().isAfter(reservation.getEnd())))
                        && reservation.isOccuring() == false) {

                    //pita da li se novi termin ponavlja, i ako da, toliko dana ga rezervisi
                    if (newReservation.isOccuring() == true) {
                        Reservation reservationOccuring = new Reservation(newReservation.getStart(), newReservation.getEnd(),
                                newReservation.getDescription(), newReservation.isOccuring(), newReservation.getNumberOfOccuring(),
                                newReservation.getRoomId(), newReservation.getUserId());

                        for (int i = 0; i < newReservation.getNumberOfOccuring(); i++) {

                            reservationDao.save(reservationOccuring);
                            reservationOccuring.getStart().plusDays(1);
                            reservationOccuring.getEnd().plusDays(1);
                            //vraca zadnju dodatu rezervaciju:
                            if (i == (newReservation.getNumberOfOccuring() - 1))
                                return newReservation;
                        }
                    } else
                        return reservationDao.save(newReservation);

                }
            }
        }

        return null;
    }

    @PUT
    @Path("{id}")
    public Reservation update(@PathParam("id") long id, @Valid Reservation reservation) {
        if (reservationDao.findOne(id) == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        } else {
            reservation.setId(id);
            return reservationDao.save(reservation);
        }
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") long id) {
        Reservation reservation = reservationDao.findOne(id);
        if (reservation == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        } else {
            reservationDao.delete(reservation);
        }
    }

}
*/