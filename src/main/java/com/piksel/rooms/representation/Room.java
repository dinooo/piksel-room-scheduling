package com.piksel.rooms.representation;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "room_name")
    private String room_name;
    @NotNull
    private boolean reservated;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private List<Reservation> reservations;

    public Room(){}

    public Room(String room_name, boolean reservated, List<Reservation> reservations) {

        this.room_name = room_name;
        this.reservated = reservated;
        this.reservations = reservations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public boolean isReservated() {
        return reservated;
    }

    public void setReservated(boolean reservated) {
        this.reservated = reservated;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", room_name='" + room_name + '\'' +
                ", reservated=" + reservated +
                ", reservations=" + reservations +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (reservated != room.reservated) return false;
        if (id != null ? !id.equals(room.id) : room.id != null) return false;
        if (room_name != null ? !room_name.equals(room.room_name) : room.room_name != null) return false;
        return reservations != null ? reservations.equals(room.reservations) : room.reservations == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (room_name != null ? room_name.hashCode() : 0);
        result = 31 * result + (reservated ? 1 : 0);
        result = 31 * result + (reservations != null ? reservations.hashCode() : 0);
        return result;
    }
}
