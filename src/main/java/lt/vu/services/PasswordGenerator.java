package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;

@ApplicationScoped
public class PasswordGenerator implements Serializable {
    public String generatePassword(String name){
        StringBuilder stringBuilder = new StringBuilder();
        for(char ch : name.toCharArray()){
            stringBuilder.append((int)ch);
        }
        return stringBuilder.toString();
    }
}
