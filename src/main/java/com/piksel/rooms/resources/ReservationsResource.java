package com.piksel.rooms.resources;

import com.piksel.rooms.persistence.ReservationDao;
import com.piksel.rooms.representation.Reservation;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

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
    public List<Reservation> getAll(@QueryParam("startDate") String startDate) {
        List<Reservation> reservations = null;
        if (startDate != null) {
            reservations = reservationDao.findByDay(new DateTime(startDate));
        } else {
            reservations = reservationDao.findAll();
            //reservations = reservationDao.findAfterNow();
        }
        return reservations;
    }

    @POST
    public Reservation addReservation(@Valid Reservation newReservation) {

        List<Reservation> reservations = reservationDao.findAllBetweenDays(newReservation.getReservation_start(), newReservation.getReservation_end());

        if (reservations.size() != 0) {
            for (Reservation reservation : reservations) {
                //provjera da li je soba rezervisana, koju treba rezervisati. dohvata sobu
                if (newReservation.getRoom_id() == reservation.getRoom_id()) {
                    //provjera da li je vrijeme rezervacije nove sobe prije ili nakonr rezervisanog vremena date sobe
                    if ((newReservation.getReservation_start().isBefore(reservation.getReservation_start()) && newReservation.getReservation_end().isBefore(reservation.getReservation_start()))
                            || (newReservation.getReservation_start().isAfter(reservation.getReservation_end()) && newReservation.getReservation_end().isAfter(reservation.getReservation_end()))) {
                        return saveReservations(newReservation);
                    }
                } else {
                    //else sluzi ako je neka nova soba u pitanju
                    return saveReservations(newReservation);
                }
            }
        }
        //izvrsava se samo prvi put ako nema niti jedne rezervacije u bp
        return saveReservations(newReservation);
    }


    @PUT
    @Path("/{id}")
    public Reservation update(@PathParam("id") long id, @QueryParam("isSeries") boolean isSeries, @Valid Reservation newReservation) {
        List<Reservation> reservations = reservationDao.findAll();
        if (reservationDao.findOne(id) == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        if (isSeries == false) {
            Reservation reservation = reservationDao.findOne(id);
            newReservation.setId(reservation.getId());
            return reservationDao.save(newReservation);
        } else {
            List<Reservation> reservationsOccuring = reservationDao.findByUUID(newReservation.getReservation_uuid());

            for (Reservation reservation : reservationsOccuring) {
                newReservation.setId(reservation.getId());
                reservationDao.save(newReservation);
            }
            return newReservation;
        }
    }


    private Reservation saveReservations(@Valid Reservation newReservation) {

        newReservation.setReservation_uuid(UUID.randomUUID());
        //ako se upisuje samo jednom:
        if (newReservation.getNumber_of_occuring() == 0) {
            return reservationDao.save(newReservation);
        }

        //treba rezervaciju upisati onoliko puta koliko se puta ponavlja
        for (int i = 0; i < newReservation.getNumber_of_occuring(); i++) {
            Reservation reservation = new Reservation(newReservation);
            reservation.setReservation_start(reservation.getReservation_start().plusDays(i));
            reservation.setReservation_end(reservation.getReservation_end().plusDays(i));

            reservationDao.save(reservation);
        }
        return newReservation;

    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") long id) {
        Reservation reservation = reservationDao.findOne(id);
        if(reservation == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        reservationDao.delete(reservation);
    }

}