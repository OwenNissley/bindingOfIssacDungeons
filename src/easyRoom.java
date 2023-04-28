import java.util.ArrayList;
import java.util.Random;

public class easyRoom extends room {
    private ArrayList<tile> easyTiles;

    easyRoom() {
        super(5, 2, 3);
        /*easyTiles = new ArrayList<>();
        gameItems items = new gameItems();
        gameMonsters monsters = new gameMonsters();
        for(int i=0 i<2)
        itemTile iT2 = new itemTile(items.getItem());
        itemTile iT3 = new itemTile(items.getItem());
        itemTile iT4 = new itemTile(items.getItem());
        enemyTile mT = new enemyTile(monsters.getMonster());



         */



    }


   /* public void addTile(tile Tile, int row, int col) throws ArrayIndexOutOfBoundsException {
        if (row > super.getTiles().length - 1) {
            throw new ArrayIndexOutOfBoundsException("Invalid row index");
        } else if (col > super.getTiles()[0].length - 1) {
            throw new ArrayIndexOutOfBoundsException("Invalid col index");
        } else {
            super.getTiles()[row][col] = Tile;
        }
    }

    */
}
