package com.piksel.rooms.representation;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "room_name")
    private String roomName;
    @NotNull
    private boolean reservated;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private List<Reservation> reservations;

    public Room(){}

    public Room(String roomName, boolean reservated, List<Reservation> reservations) {

        this.roomName = roomName;
        this.reservated = reservated;
        this.reservations = reservations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
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
}
