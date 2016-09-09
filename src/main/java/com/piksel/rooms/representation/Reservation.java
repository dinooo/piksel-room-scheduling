package com.piksel.rooms.representation;


import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "reservation_start")
    private DateTime start;
    @NotNull
    @Column(name = "reservation_end")
    private DateTime end;

    private String description;

    @NotNull
    @Column(name = "is_occuring")
    private boolean isOccuring;

    private int numberOfOccuring;

    @JoinColumn(name = "room_id")
    private Long roomId;
    @JoinColumn(name = "user_id")
    private Long userId;

    public Reservation(){}

    public Reservation(DateTime start, DateTime end, String description, boolean isOccuring, int numberOfOccuring, Long roomId, Long userId) {
        this.start = start;
        this.end = end;
        this.description = description;
        this.isOccuring = isOccuring;
        this.numberOfOccuring = numberOfOccuring;
        this.roomId = roomId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getStart() {
        return start;
    }

    public void setStart(DateTime start) {
        this.start = start;
    }

    public DateTime getEnd() {
        return end;
    }

    public void setEnd(DateTime end) {
        this.end = end;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isOccuring() { return isOccuring; }

    public void setOccuring(boolean occuring) { isOccuring = occuring; }

    public int getNumberOfOccuring() { return numberOfOccuring; }

    public void setNumberOfOccuring(int numberOfOccuring) { this.numberOfOccuring = numberOfOccuring; }
}
