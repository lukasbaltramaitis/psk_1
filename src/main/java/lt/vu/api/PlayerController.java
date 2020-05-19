package lt.vu.api;

import lombok.Getter;
import lombok.Setter;
import lt.vu.api.dto.PlayerDTO;
import lt.vu.entities.Player;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.PlayersDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@LoggedInvocation
@ApplicationScoped
@Path("/players")
public class PlayerController {
    @Inject
    @Setter @Getter
    private PlayersDAO playersDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Player player = playersDAO.findOne(id);
        if (player == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        PlayerDTO playerDto = new PlayerDTO();
        playerDto.setId(player.getId());
        playerDto.setName(player.getName());
        playerDto.setMoney(player.getMoney());
        playerDto.setPassword(player.getPassword());
        return Response.ok(playerDto).build();
    }
    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(PlayerDTO newPlayer) {
        if(newPlayer.getName() == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Player player = new Player();
        player.setMoney(newPlayer.getMoney());
        player.setName(newPlayer.getName());
        player.setPassword(newPlayer.getPassword());
        playersDAO.persist(player);
        return Response.ok(player).build();
    }

    @Path("/put/{userId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(PlayerDTO playerToUpdate, @PathParam("userId") int id)
    {
        Player player = playersDAO.findOne(id);
        if (player == null) {
            throw new IllegalArgumentException("user id "
                    + id + " not found");
        }
        if (playerToUpdate.getName() != null){
            player.setName(playerToUpdate.getName());
        }
        if (playerToUpdate.getPassword() != null) {
            player.setPassword(playerToUpdate.getPassword());
        }
        if (playerToUpdate.getMoney() != null) {
            player.setMoney(playerToUpdate.getMoney());
        }
        playersDAO.update(player);
        return Response.ok(playerToUpdate).build();
    }

}
