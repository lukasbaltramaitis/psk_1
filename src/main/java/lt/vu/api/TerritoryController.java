package lt.vu.api;

import lombok.Getter;
import lombok.Setter;
import lt.vu.api.dto.PlayerDTO;
import lt.vu.api.dto.TerritoryDTO;
import lt.vu.entities.Player;
import lt.vu.entities.Territory;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.PlayersDAO;
import lt.vu.persistence.TerritoriesDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@LoggedInvocation
@ApplicationScoped
@Path("/territories")
public class TerritoryController {
    @Inject
    @Setter
    @Getter
    private TerritoriesDAO territoriesDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Territory territory = territoriesDAO.findOne(id);
        if (territory == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        TerritoryDTO territoryDTO = new TerritoryDTO();
        territoryDTO.setId(territory.getId());
        territoryDTO.setName(territory.getName());
        territoryDTO.setCountryId(territory.getCountryId());
        territoryDTO.setPlayerId(territory.getPlayerId());
        territoryDTO.setState(territory.getState());
        return Response.ok(territoryDTO).build();
    }
    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(TerritoryDTO newTerritory) {
        if(newTerritory.getName() == null || newTerritory.getCountryId() == 0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Territory territory = new Territory();
        territory.setId(newTerritory.getId());
        territory.setName(newTerritory.getName());
        territory.setCountryId(newTerritory.getCountryId());
        territory.setPlayerId(newTerritory.getPlayerId());
        territory.setState(newTerritory.getState());
        territoriesDAO.persist(territory);
        return Response.ok(newTerritory).build();
    }

    @Path("/put/{territoryId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(TerritoryDTO territoryToUpdate, @PathParam("territoryId") int id) throws InterruptedException {
        Territory territory = territoriesDAO.findOne(id);
        if (territory == null) {
            throw new IllegalArgumentException("user id "
                    + id + " not found");
        }
        if (territoryToUpdate.getName() != null){
            territory.setName(territoryToUpdate.getName());
        }
        if (territoryToUpdate.getCountryId() > 0){
            territory.setCountryId(territoryToUpdate.getCountryId());
        }
        territory.setState(territoryToUpdate.getState());
        territoryToUpdate.setId(territory.getId());
        if (territoryToUpdate.getState() == 1) {
            Thread.sleep(6000);
        }
        try {
            territoriesDAO.update(territory);
            territoriesDAO.flush();
            return Response.ok(territoryToUpdate).build();
        }
        catch (OptimisticLockException ole){
            return Response.status(Response.Status.CONFLICT)
                    .entity(ole.getEntity()).build();
        }
    }
}
