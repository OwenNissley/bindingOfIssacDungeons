public class enemyTile extends tile{

    monster monster;

    enemyTile(monster monster){
        super(monster.getSymbol());
        this.monster = monster;
    }

   public monster getMonster(){
        return monster;
   }

}
