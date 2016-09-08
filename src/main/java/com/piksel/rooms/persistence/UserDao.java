package com.piksel.rooms.persistence;

import com.piksel.rooms.representation.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dino on 9/8/16.
 */
public interface UserDao extends JpaRepository<User, Long> {
}
