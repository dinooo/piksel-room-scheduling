package com.piksel.rooms.persistence;

import com.piksel.rooms.representation.OreservRequest;
import com.piksel.rooms.representation.Reservation;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ReservationDao extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.reservation_start = :day")
    List<Reservation> findByDay(@Param("day") DateTime day);

    @Query("SELECT r FROM Reservation r WHERE now() >= r.reservation_end ")
    List<Reservation> findAfterNow();

    @Query("SELECT r FROM Reservation r WHERE r.member_id = :member_id")
    List<Reservation> findByMember(@Param("member_id") long member_id);

    @Query("SELECT r FROM Reservation r WHERE r.reservation_start >= :dayStart AND r.reservation_end <= :dayEnd")
    List<Reservation> findAllBetweenDays(@Param("dayStart") DateTime dayStart, @Param("dayEnd") DateTime dayEnd);

    @Query("SELECT r FROM Reservation r WHERE r.reservation_uuid = :uuid")
    List<Reservation> findByUUID(@Param("uuid") UUID uuid);


    //resevation uid nova kolona, ako se

    /*
    @Query("SELECT r FROM Reservation r WHERE r.start BETWEEN :dateStart AND :dateEnd")
    List<Reservation> findReservationsByDateRange(@Param("dateStart") DateTime dateStart, @Param("dateEnd") DateTime dateEnd);
    */
}
