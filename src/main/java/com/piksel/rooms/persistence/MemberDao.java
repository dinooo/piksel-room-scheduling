package com.piksel.rooms.persistence;

import com.piksel.rooms.representation.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dino on 9/8/16.
 */
public interface MemberDao extends JpaRepository<Member, Long> {
}
