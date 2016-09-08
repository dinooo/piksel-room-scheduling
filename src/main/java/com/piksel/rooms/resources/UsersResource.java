package com.piksel.rooms.resources;

import com.piksel.rooms.persistence.UserDao;
import com.piksel.rooms.representation.User;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
@Component
public class UsersResource {

    private UserDao userDao;
    @Inject
    public UsersResource(UserDao userDao){
        this.userDao = userDao;
    }

    @GET
    public List<User> getAll(){
        List<User> users = this.userDao.findAll();
        return users;
    }

    @GET
    @Path("{id}")
    public User getOne(@PathParam("id") long id){
        User user = userDao.findOne(id);
        if(user == null)
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        return user;
    }

    @POST
    public User save(@Valid User user) {
        return userDao.save(user);
    }

    @PUT
    @Path("{id}")
    public User update(@PathParam("id")long id, @Valid User user) {
        if(userDao.findOne(id) == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }else {
            user.setId(id);
            return userDao.save(user);
        }
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id")long id) {
        User user = userDao.findOne(id);
        if(user == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }else {
            userDao.delete(user);
        }
    }

}
