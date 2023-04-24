import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class gameItems {
    private ArrayList<item> gameItems;
    private Set<String> itemSymbols;

    gameItems(){
        gameItems = new ArrayList<>();
        itemSymbols = new HashSet<>();
        item cricketsHead = new item("Crickets Head","DMG UP!",0,3,"C");
        gameItems.add(cricketsHead);
        this.addToSet(cricketsHead);
        item poop = new item("POO","EWWWW",-1,-1,"B");
        gameItems.add(poop);
        this.addToSet(poop);
        item doubleShot = new item("Double Shot","Seeing Two!",0,0,"D");
        gameItems.add(doubleShot);
        this.addToSet(doubleShot);
        item meat = new item("Meat","HMMMMMM!",1,1,"M");
        gameItems.add(meat);
        this.addToSet(meat);
        item lunch = new item("Lunch","HP UP!",1,0,"L");
        gameItems.add(lunch);
        this.addToSet(lunch);
    }

    public void addToSet(item Item){
        itemSymbols.add(Item.getSymbol());
    }
    public Set<String> getItemSymbols(){
        return itemSymbols;
    }
    public item getItem(){
        Random random = new Random();
        return gameItems.get(random.nextInt(gameItems.size()));
    }

}
