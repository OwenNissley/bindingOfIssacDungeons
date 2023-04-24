import java.util.Set;

public abstract class tile {

    //The thing it is going to display
    private String symbol;

    /**
     * @param symbol
     * Supplies symbol
     */
    tile(String symbol){
        this.symbol = symbol;

    }



    public boolean isTileEqualToItem(){
       gameItems gameItems = new gameItems();
       Set<String> itemSymbols = gameItems.getItemSymbols();
        if(itemSymbols.contains(symbol)){
            return true;
        }else {
            return false;
        }
    }

    public boolean isTileEqualToMonster(){

    }


    public String getSymbol(){
         return symbol;
    }

    public void tileReset(){
        symbol = " ";
    }

    public boolean isEqualToPlayer(){
        return this.getSymbol().equals("P");
    }



    public tile setToPlayer(String symbol){

        return null;
    }
}
