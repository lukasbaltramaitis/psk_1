package lt.vu.usecases;


import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Player;
import lt.vu.persistence.PlayersDAO;
import javax.transaction.Transactional;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;

@Model
public class Players implements Serializable {

    @Inject
    private PlayersDAO playersDAO;

    @Getter @Setter
    private Player playerToCreate = new Player();

    @Transactional
    public String login(){
        String playerToCreateName = playerToCreate.getName();
        Player player = playersDAO.findByName(playerToCreateName);
        if(player == null){
            playersDAO.persist(playerToCreate);
            player = playerToCreate;
        }
        return "player?id=" + player.getId() + "&password=" + player.getPassword();
    }
}
