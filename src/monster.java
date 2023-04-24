public class monster {
    private String symbol;
    private int attack;
    private String name;

    monster(String symbol, String name, int attack){
        this.symbol = symbol;
        this.name = name;
        this.attack = attack;
    }



    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public String getSymbol() {
        return symbol;
    }
}
