import com.sun.source.tree.UsesTree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class player{

    private int health;
    private int damage;

    private int amountOfMonstersKilled;


    /**
     * Create items and add to inventory and set health and damage
     */
    player(){
        amountOfMonstersKilled = 0;
        health = 3;
        damage = 1;

    }
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
        System.out.printf("%s,%s,\n%s",item.getSymbol(),item.getName(),item.getEffect());
    }

    public boolean monsterInteraction(monster monster){
        Scanner input = new Scanner(System.in);
        System.out.printf("You have entered combat with %s\n Be Prepared!\n",monster.getName());
        System.out.printf("Your stats, Health:%s, Damage:%s\n",health,damage);
        System.out.println("Do you desire to leave? or Fight to the death?");
        String answer = input.nextLine();
        while(!((answer.equals("fight")) || (answer.equals("leave")))){
            System.out.println("Invalid, please enter 'fight' or 'leave'.");
            answer = input.nextLine();
        }
        if(answer.equals("leave")){
            return true;
        }
        if(answer.equals("fight")){
            if(damage > monster.getAttack()){
                System.out.printf("%s, has been defeated.\n",monster.getName());
                System.out.println("+1 strength");
                damage = damage +1;
                amountOfMonstersKilled = amountOfMonstersKilled +1;
            }else {
                System.out.printf("%s, has defeated you.\n",monster.getName());
                System.out.println("ouch");
                health = health -1;
            }
        }
        return false;
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

    public int getAmountOfMonstersKilled() {
        return amountOfMonstersKilled;
    }
}



