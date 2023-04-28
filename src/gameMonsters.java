import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class gameMonsters {
    private ArrayList<monster> gameMonsters;
    private Set<String> monsterSymbols;


    gameMonsters(){
        gameMonsters = new ArrayList<>();
        monsterSymbols = new HashSet<>();
        monster huant = new monster("B","Huant", 2);
        gameMonsters.add(huant);
        this.addToSet(huant);
        monster delerium = new monster("O","Delerium", 22);
        gameMonsters.add(delerium);
        this.addToSet(delerium);
        monster pin = new monster("3","Pin", 1);
        gameMonsters.add(pin);
        this.addToSet(pin);
        monster fallen = new monster("4","The Fallen", 6);
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
