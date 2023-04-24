import com.sun.source.tree.UsesTree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class player{
    private int health;
    private int damage;

    private ArrayList<item> inventory;

    /**
     * Create items and add to inventory and set health and damage
     */
    player(){
        health =3;
        damage = 0;
        inventory = new ArrayList<>();
    }

    /**
     * Add Item to item inventory
     * @param Item
     */
    public void addItem(item Item){
        inventory.add(Item);
    }



    /**
     * provides input for room to use
     *
     */

    /**
     * Updates players stats accordingly
     */
    public void useItem(item item){
        health = health + item.getHealth();
        if(item.getName().equals("Double Shot")){
            damage = damage*2;
        }else {
            damage = damage + item.getStrength();
        }
        System.out.printf("%s\n%s",item.getName(),item.getEffect());
    }

    public boolean attackMonster(monster monster){
        System.out.printf("You have entered combat with %s\n Be Prepared!",monster.getName());
        return damage > monster.getAttack();
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public String toPrint(){
        return ("P");
    }


    }



