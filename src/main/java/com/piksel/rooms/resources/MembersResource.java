package com.piksel.rooms.resources;

import com.piksel.rooms.persistence.MemberDao;
import com.piksel.rooms.representation.Member;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/members")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
@Component
public class MembersResource {

    private MemberDao memberDao;
    @Inject
    public MembersResource(MemberDao memberDao){
        this.memberDao = memberDao;
    }

    @GET
    public List<Member> getAll(){
        return this.memberDao.findAll();
    }

    @GET
    @Path("{id}")
    public Member getOne(@PathParam("id") long id){
        Member member = memberDao.findOne(id);
        if(member == null)
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        return member;
    }

    @POST
    public Member save(@Valid Member member) {
        return memberDao.save(member);
    }

    @PUT
    @Path("{id}")
    public Member update(@PathParam("id")long id, @Valid Member member) {
        if(memberDao.findOne(id) == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }else {
            member.setId(id);
            return memberDao.save(member);
        }
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id")long id) {
        Member member = memberDao.findOne(id);
        if(member == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }else {
            memberDao.delete(member);
        }
    }

}
