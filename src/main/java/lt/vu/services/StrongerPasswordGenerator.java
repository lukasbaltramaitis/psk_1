package lt.vu.services;

import javax.enterprise.inject.Specializes;
import java.util.Random;

@Specializes
public class StrongerPasswordGenerator extends PasswordGenerator {
    @Override
    public String generatePassword(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        int randomInt;
        for(char ch : name.toCharArray()){
            randomInt = random.nextInt(10);
            stringBuilder.append(ch + randomInt);
        }
        return super.generatePassword(name) + stringBuilder;
    }
}
