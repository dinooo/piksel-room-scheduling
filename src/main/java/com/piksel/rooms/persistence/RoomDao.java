package com.piksel.rooms.persistence;

import com.piksel.rooms.representation.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomDao extends JpaRepository<Room, Long> {
}
