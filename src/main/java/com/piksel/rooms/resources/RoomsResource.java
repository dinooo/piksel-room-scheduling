package com.piksel.rooms.resources;


import com.piksel.rooms.persistence.RoomDao;
import com.piksel.rooms.representation.Room;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
@Component
public class RoomsResource {

    private RoomDao roomDao;
    @Inject
    public RoomsResource(RoomDao roomDao){
        this.roomDao = roomDao;
    }

    @GET
    public List<Room> getAll(){
        List<Room> rooms = this.roomDao.findAll();
        return rooms;
    }

    @GET
    @Path("{id}")
    public Room getOne(@PathParam("id") long id){
        Room room = roomDao.findOne(id);
        if(room == null)
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        return room;
    }

    @POST
    public Room save(@Valid Room room) {
        return roomDao.save(room);
    }

    @PUT
    @Path("{id}")
    public Room update(@PathParam("id")long id, @Valid Room room) {
        if(roomDao.findOne(id) == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }else {
            room.setId(id);
            return roomDao.save(room);
        }
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id")long id) {
        Room room = roomDao.findOne(id);
        if(room == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }else {
            roomDao.delete(room);
        }
    }

}
