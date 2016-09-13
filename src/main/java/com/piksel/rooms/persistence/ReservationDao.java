package com.piksel.rooms.persistence;

import com.piksel.rooms.representation.OreservRequest;
import com.piksel.rooms.representation.Reservation;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationDao extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.reservation_start >= :day")
    List<Reservation> findReservationsByDay(@Param("day") DateTime day);

    /*
    @Query("SELECT r FROM Reservation r WHERE r.start BETWEEN :dateStart AND :dateEnd")
    List<Reservation> findReservationsByDateRange(@Param("dateStart") DateTime dateStart, @Param("dateEnd") DateTime dateEnd);
    */
}
