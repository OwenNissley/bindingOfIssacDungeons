import java.util.ArrayList;
import java.util.Random;

public class gameMonsters {
    private ArrayList<monster> gameMonsters;


    gameMonsters(){
        gameMonsters = new ArrayList<>();
        monster huant = new monster("B","Huant", 2);
        gameMonsters.add(huant);
        monster delerium = new monster("B","Delerium", 22);
        gameMonsters.add(delerium);
        monster pin = new monster("B","Pin", 1);
        gameMonsters.add(pin);
        monster fallen = new monster("B","The Fallen", 6);
        gameMonsters.add(fallen);
    }

    public monster getMonster(){
        Random random = new Random();
        return gameMonsters.get(random.nextInt(gameMonsters.size()));
    }
}
