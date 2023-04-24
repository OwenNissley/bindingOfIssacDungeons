import java.util.ArrayList;

public class item {
    private String name;
    private String effect;
    private int health;
    private int strength;

    private String symbol;

    /**
     *
     * @param Name
     * @param effect
     * @param health
     * @param strength
     * Update local variables accordingly
     */
    item( String Name, String effect, int health, int strength, String symbol){
        this.name = Name;
        this.effect = effect;
        this.health = health;
        this.strength = strength;
        this.symbol = symbol;
    }


    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }

    public String getEffect() {
        return effect;
    }

    public String getSymbol() {
        return symbol;
    }


}
