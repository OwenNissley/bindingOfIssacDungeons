import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class gameMonsters {
    private ArrayList<monster> gameMonsters;
    private Set<String> monsterSymbols;

    /**
     * a list storing all the possible monsters and their stats.
     */
    gameMonsters(){
        gameMonsters = new ArrayList<>();
        monsterSymbols = new HashSet<>();
        monster huant = new monster("§","Huant", 2);
        gameMonsters.add(huant);
        this.addToSet(huant);
        monster delerium = new monster("▒","Delerium", 20);
        gameMonsters.add(delerium);
        this.addToSet(delerium);
        monster pin = new monster("¡","Pin", 1);
        gameMonsters.add(pin);
        this.addToSet(pin);
        monster fallen = new monster("¿","The Fallen", 6);
        gameMonsters.add(fallen);
        this.addToSet(fallen);
    }

    public void addToSet(monster monster){
        monsterSymbols.add(monster.getSymbol());
    }
    public Set<String> getMonsterSymbols(){
        return monsterSymbols;
    }
    public monster getMonster(){
        Random random = new Random();
        return gameMonsters.get(random.nextInt(gameMonsters.size()));
    }
}
