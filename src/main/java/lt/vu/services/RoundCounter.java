package lt.vu.services;

import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Model;
import java.io.Serializable;
@ApplicationScoped
@Model
public class RoundCounter implements Serializable {
    @Getter
    @Setter
    private int roundNr = 1;

}
