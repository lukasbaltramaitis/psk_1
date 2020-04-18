package lt.vu.usecases;

import lombok.Getter;
import lt.vu.entities.Player;
import lt.vu.mybatis.dao.CountryMapper;
import lt.vu.mybatis.dao.TerritoryMapper;
import lt.vu.mybatis.model.Country;
import lt.vu.persistence.PlayersDAO;
import lt.vu.services.Authenticator;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Model
public class CountryForPlayer {
    @Inject
    private CountryMapper countryMapper;
    @Inject
    private TerritoryMapper territoryMapper;
    @Inject
    private PlayersDAO playersDAO;
    @Inject
    private Authenticator authenticator;
    @Getter
    private List<Country> unusedCountries;
    @Getter
    private List<Country> playerCountries;
    @Getter
    private Player player;

    @PostConstruct
    public void init() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        authenticator.authenticate(facesContext);
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int playerId = Integer.parseInt(requestParameters.get("playerId"));
        loadPlayer(playerId);
        loadPlayerCountries(playerId);
        loadAllCountries();
    }

    @Transactional
    public String chooseCountry(int countryId){
        int playerId = player.getId();
        territoryMapper.updateByCountryPlayerId(countryId, playerId);
        loadAllCountries();
        loadPlayer(playerId);
        return "player?faces-redirect=true&playerId=" + playerId + "&password=" + player.getPassword();
    }

    private void loadAllCountries(){
        unusedCountries = countryMapper.selectUnusedCountries();
    }
    private void loadPlayer(int playerId){
        player = playersDAO.findOne(playerId);
    }
    private void loadPlayerCountries(int playerId){
        playerCountries = countryMapper.selectPlayerCountries(playerId);
    }
}
