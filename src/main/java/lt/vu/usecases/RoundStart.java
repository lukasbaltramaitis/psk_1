package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Player;
import lt.vu.mybatis.dao.TerritoryMapper;
import lt.vu.mybatis.model.Territory;
import lt.vu.persistence.PlayersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class RoundStart implements Serializable {
    @Inject
    private PlayersDAO playersDAO;
    @Inject
    private TerritoryMapper territoryMapper;
    @Getter
    private List<Territory> playerTerritories;
    @Getter
    private List<Territory> freeTerritories;

    private Player player;

    @Getter @Setter
    private int removeTerritoryId = -1;
    @Getter @Setter
    private int addTerritoryId = -1;

    @PostConstruct
    public void init() {
        int playerId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().
                getSessionMap().get("userId").toString());
        loadPlayer(playerId);
        loadPlayerTerritories(playerId);
        loadFreeTerritories();
    }

    @Transactional
    public String unassignTerritory(){
        if(removeTerritoryId != -1) {
            territoryMapper.unassignPlayerId(removeTerritoryId);
        }
        return "roundStart.xhtml?faces-redirect=true";
    }
    @Transactional
    public String assignTerritory(){
        if(addTerritoryId != -1 && player != null) {
            territoryMapper.assignPlayerId(addTerritoryId, player.getId());
        }
        return "roundStart.xhtml?faces-redirect=true";
    }

    private void loadPlayer(int playerId){
        player = playersDAO.findOne(playerId);
    }
    private void loadPlayerTerritories(int playerId){
        playerTerritories = territoryMapper.selectByPlayer(playerId);
    }
    private void loadFreeTerritories() {
        freeTerritories = territoryMapper.selectFree();
    }
}
