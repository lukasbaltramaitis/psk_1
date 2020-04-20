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
import java.util.ArrayList;
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
    @Getter
    private List<Country> unusedCountries;
    @Getter
    private List<Country> playerCountries;
    @Getter
    private Player player;
    @PostConstruct
    public void init() {
        int playerId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().
                getSessionMap().get("userId").toString());
        loadPlayer(playerId);
        loadPlayerCountries(playerId);
        loadAllCountries();
    }

    @Transactional
    public String chooseCountry(int countryId){
        int playerId = player.getId();
        territoryMapper.updateByCountryPlayerId(countryId, playerId);
        //loadAllCountries();
        //loadPlayerCountries(playerId);
        return "player.xhtml?faces-redirect=true";
    }

    private void loadAllCountries(){
        List<Country> countries = countryMapper.selectUnusedCountries();
        unusedCountries = (countries == null) ? new ArrayList<>() : countries;

    }
    private void loadPlayer(int playerId){
        player = playersDAO.findOne(playerId);
    }
    private void loadPlayerCountries(int playerId){
        List<Country> countries = countryMapper.selectPlayerCountries(playerId);
        playerCountries = (countries == null) ? new ArrayList<>() : countries;
    }
}
