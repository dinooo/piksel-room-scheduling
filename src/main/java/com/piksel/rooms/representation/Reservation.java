package com.piksel.rooms.representation;


import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "reservation_start")
    private DateTime reservation_start;
    @NotNull
    @Column(name = "reservation_end")
    private DateTime reservation_end;

    private String description;

    @NotNull
    @Column(name = "is_occuring")
    private boolean is_occuring;

    @NotNull
    private int number_of_occuring;

    @JoinColumn(name = "room_id")
    private Long room_id;
    @JoinColumn(name = "member_id")
    private Long member_id;

    public Reservation() {
    }

    public Reservation(DateTime reservation_start, DateTime reservation_end, String description, boolean is_occuring, int number_of_occuring, Long room_id, Long member_id) {
        this.reservation_start = reservation_start;
        this.reservation_end = reservation_end;
        this.description = description;
        this.is_occuring = is_occuring;
        this.number_of_occuring = number_of_occuring;
        this.room_id = room_id;
        this.member_id = member_id;
    }

    public Reservation(Reservation reservation){
        this.reservation_start = reservation.getReservation_start();
        this.reservation_end = reservation.getReservation_end();
        this.description = reservation.getDescription();
        this.is_occuring = reservation.is_occuring();
        this.number_of_occuring = reservation.getNumber_of_occuring();
        this.room_id = reservation.getRoom_id();
        this.member_id = reservation.getMember_id();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getReservation_start() {
        return reservation_start;
    }

    public void setReservation_start(DateTime reservation_start) {
        this.reservation_start = reservation_start;
    }

    public DateTime getReservation_end() {
        return reservation_end;
    }

    public void setReservation_end(DateTime reservation_end) {
        this.reservation_end = reservation_end;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean is_occuring() {
        return is_occuring;
    }

    public void setIs_occuring(boolean is_occuring) {
        this.is_occuring = is_occuring;
    }

    public int getNumber_of_occuring() {
        return number_of_occuring;
    }

    public void setNumber_of_occuring(int number_of_occuring) {
        this.number_of_occuring = number_of_occuring;
    }

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }

    public Long getMember_id() {
        return member_id;
    }

    public void setMember_id(Long member_id) {
        this.member_id = member_id;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", reservation_start=" + reservation_start +
                ", reservation_end=" + reservation_end +
                ", description='" + description + '\'' +
                ", is_occuring=" + is_occuring +
                ", number_of_occuring=" + number_of_occuring +
                ", room_id=" + room_id +
                ", member_id=" + member_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        if (is_occuring != that.is_occuring) return false;
        if (number_of_occuring != that.number_of_occuring) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (reservation_start != null ? !reservation_start.equals(that.reservation_start) : that.reservation_start != null)
            return false;
        if (reservation_end != null ? !reservation_end.equals(that.reservation_end) : that.reservation_end != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (room_id != null ? !room_id.equals(that.room_id) : that.room_id != null) return false;
        return member_id != null ? member_id.equals(that.member_id) : that.member_id == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (reservation_start != null ? reservation_start.hashCode() : 0);
        result = 31 * result + (reservation_end != null ? reservation_end.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (is_occuring ? 1 : 0);
        result = 31 * result + number_of_occuring;
        result = 31 * result + (room_id != null ? room_id.hashCode() : 0);
        result = 31 * result + (member_id != null ? member_id.hashCode() : 0);
        return result;
    }
}