package com.piksel.rooms.persistence;

import com.piksel.rooms.representation.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberDao extends JpaRepository<Member, Long> {


}
