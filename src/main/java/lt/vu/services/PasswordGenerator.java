package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class PasswordGenerator implements Serializable {
    public String generatePassword(String name){
        Random random = new Random();
        return name + random.nextInt();
    }
}
