package lt.vu.usecases;


import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Player;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.PlayersDAO;
import lt.vu.services.GameService;
import lt.vu.services.PasswordGenerator;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.transaction.Transactional;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Map;
@Model
public class Players implements Serializable {

    @Inject
    private PlayersDAO playersDAO;

    @Getter @Setter
    private Player playerToCreate = new Player();

    @Inject
    private PasswordGenerator passwordGenerator;

    @LoggedInvocation
    @Transactional
    public String login(){
        String playerToCreateName = playerToCreate.getName();
        Player player = playersDAO.findByName(playerToCreateName);
        if(player == null){
            playerToCreate.setPassword(passwordGenerator.generatePassword(playerToCreateName));
            playersDAO.persist(playerToCreate);
            player = playerToCreate;
        }
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userId", player.getId());
        return "player.xhtml?faces-redirect=true";
    }
}
