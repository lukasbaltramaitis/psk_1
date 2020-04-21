package lt.vu.services;

import lt.vu.mybatis.dao.PlayerMapper;
import lt.vu.mybatis.model.Player;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

@RequestScoped
public class Authenticator implements Serializable {

    @Inject
    private PlayerMapper playerMapper;

    public void authenticate(FacesContext facesContext){
        Map<String, String> requestParameters =
                facesContext.getExternalContext().getRequestParameterMap();
        Integer playerId = Integer.parseInt(requestParameters.get("playerId"));
        String password = requestParameters.get("password");
        Player player = playerMapper.selectByPrimaryKey(playerId);
        if((player == null) || player.getPassword().equals(password)){
            try {
                facesContext.getExternalContext().redirect("index");
            }
            catch (IOException ignored){}
        }
    }
}
