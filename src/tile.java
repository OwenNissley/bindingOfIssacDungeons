import java.util.Set;

public abstract class tile {

    //The thing it is going to display
    private String Symbol;

    private String displaySymbol;


    public item getItem(){
        return null;
    }
    public monster getMonster(){
        return null;
    }






    /**
     * @param symbol
     * Supplies symbol
     */
    tile(String symbol){
        Symbol = symbol;
        displaySymbol = symbol;
    }

    public String getDisplaySymbol() {
        return displaySymbol;
    }

    public void setDisplaySymbol(String displaySymbol) {
        this.displaySymbol = displaySymbol;
    }

    public boolean isTileEqualToItem(){
       gameItems gameItems = new gameItems();
       Set<String> itemSymbols = gameItems.getItemSymbols();
        if(itemSymbols.contains(Symbol)){
            return true;
        }else {
            return false;
        }
    }

    public boolean isTileEqualToMonster(){
        gameMonsters gameMonsters = new gameMonsters();
        Set<String> monsterSymbols = gameMonsters.getMonsterSymbols();
        if(monsterSymbols.contains(Symbol)){
            return true;
        }else {
            return false;
        }
    }

    public boolean isTileEqualToDoor(){
        if(Symbol.equals("]")){
            return true;
        }else {
            return false;
        }
    }

    public boolean isTileEqualEmpty(){
        if(Symbol.equals(" ")){
            return true;
        }else {
            return false;
        }
    }


    public void setSymbol(String Symbol) {
        this.Symbol = Symbol;
    }
}
