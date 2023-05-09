import java.util.*;

public abstract class room {
    private tile [][]  tiles;
    private playerTile playerTile;
    private boolean needToMoveRoom;

    private boolean gameOver; // maybe store this variable with the player instead??


    /**
     * creates a room and populates it with an array of tiles.
     * @param size long the square room is, in tiles
     * @param monsterCount how many monsters to put in the room
     * @param itemCount how many items to put in the room
     */
    room(int size, int monsterCount, int itemCount){
        needToMoveRoom = false;
        gameOver = false;
        tiles = new tile [size][size];
        for(int i=0; i<tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                tiles[i][j] = new emptyTile();
            }
        }
        playerTile = new playerTile("ጰ"); //possible characters: ጰ♀⛄⚇⚴
        tiles[playerTile.getPosR()][playerTile.getPosC()] = playerTile;
        populateRoom(itemCount,monsterCount,size);

    }

    /**
     * generates the top border of a room for printing
     * @param size the size of the room
     * @return returns a string of the top of the room
     */
      public String getTopBorder(int size){ // generates the top border of a room
        String border = "╔";
        for(int i=0; i<size; i++) {
            border = border + "═";
          }
        border = border + "╗";
        return border;
      }

    public String getBottomBorder(int size){ // generates the top border of a room
        String border = "╚";
        for(int i=0; i<size; i++) {
            border = border + "═";
        }
        border = border + "╝";
        return border;
    }

    public void printRoom(){
        //tile door = new doorTile();
        System.out.println(getTopBorder(tiles.length)); // print the top of the room
        for(int i=0; i<tiles.length; i++){
            System.out.print("║"); // print one wall
            for(int j=0; j<tiles[0].length; j++){
                System.out.print(tiles[i][j].getDisplaySymbol()); // print the row's tiles
            }
            if(i==tiles.length-1) {
                System.out.print("⛫"); // print the door out∏∩⛫
            } else {
                System.out.print("║"); // print the other wall
            }
            System.out.println();
        }
        System.out.println(getBottomBorder(tiles.length)); // print the bottom of the room
    }


    /**
     * handles player movement within the room, updating it accordingly
     * @param input 'w' 'a' 's' or 'd'
     * @throws Exception for if you hit a wall
     */
    public void movePlayer(String input) throws Exception {
        boolean leftMonsterFight = false;
        if(input.equals("w")){
            if(playerTile.getPosR() == 0){
                throw new Exception("Can't move");
            }else {
                clearPLayerFromCurrentPosition();
                tile checkTile = tiles[playerTile.getPosR()-1][playerTile.getPosC()];
                leftMonsterFight = runActions(checkTile);
                if(!(leftMonsterFight)){
                    playerTile.updatePosR(playerTile.getPosR()-1);
                }else {
                    try {
                        tiles[playerTile.getPosR()-1][playerTile.getPosC()].setDisplaySymbol(tiles[playerTile.getPosR()-1][playerTile.getPosC()].getMonster().getSymbol());
                    }catch (Exception ignored){}
                }
            }
        } else if (input.equals("s")) {
            if(playerTile.getPosR() == tiles.length-1){
                throw new Exception("Can't move");
            }else {
                clearPLayerFromCurrentPosition();
                tile checkTile = tiles[playerTile.getPosR()+1][playerTile.getPosC()];
                leftMonsterFight = runActions(checkTile);
                if(!(leftMonsterFight)){
                    playerTile.updatePosR(playerTile.getPosR()+1);
                }else {
                    try {
                        tiles[playerTile.getPosR()+1][playerTile.getPosC()].setDisplaySymbol(tiles[playerTile.getPosR()+1][playerTile.getPosC()].getMonster().getSymbol());
                    }catch (Exception ignored){}
                }
            }
        }else if (input.equals("a")) {
            if(playerTile.getPosC() == 0){
                throw new Exception("Can't move");
            }else {
                clearPLayerFromCurrentPosition();
                tile checkTile = tiles[playerTile.getPosR()][playerTile.getPosC()-1];
                leftMonsterFight = runActions(checkTile);
                if(!(leftMonsterFight)) {
                    playerTile.updatePosC(playerTile.getPosC() - 1);
                }else {
                    try {
                        tiles[playerTile.getPosR()][playerTile.getPosC()-1].setDisplaySymbol(tiles[playerTile.getPosR()][playerTile.getPosC()-1].getMonster().getSymbol());
                    }catch (Exception ignored){}
                }
            }
        }else if (input.equals("d")) {
            if(playerTile.getPosC() == tiles[0].length-1){
                if (playerTile.getPosR() == tiles.length-1) {
                    needToMoveRoom = true;
                } else {
                    throw new Exception("Can't move");
                }
            }else {
                clearPLayerFromCurrentPosition();
                tile checkTile = tiles[playerTile.getPosR()][playerTile.getPosC()+1];
                leftMonsterFight = runActions(checkTile);
                if(!(leftMonsterFight)) {
                    playerTile.updatePosC(playerTile.getPosC() + 1);
                }else {
                    try {
                        tiles[playerTile.getPosR()][playerTile.getPosC()+1].setDisplaySymbol(tiles[playerTile.getPosR()][playerTile.getPosC()+1].getMonster().getSymbol());
                    }catch (Exception ignored){}
                }
            }
        }
                updatePLayer();
    }

    /**
     * Does the appropriate actions for when a player steps onto a special tile.
     * @param checkTile the tile stepped on
     * @return returns whether the room has been left, true or false
     */
    public boolean runActions(tile checkTile){
        boolean leftRoom = false;
        if(this.isThereAInteraction(checkTile)){
            if (isDoor(checkTile)){
                needToMoveRoom = true;
            }else if(isItem(checkTile)){
                item itemToUse = checkTile.getItem();
                playerTile.getPlayer().useItem(itemToUse);
                if(playerTile.getPlayer().getHealth()<=0){
                    gameOver = true;
                }
            } else if (isMonster(checkTile)) {
                leftRoom = playerTile.getPlayer().monsterInteraction(checkTile.getMonster());
                if(playerTile.getPlayer().getHealth()<=0){
                    gameOver = true;
                }
            }
        }
        return leftRoom;
    }

    /** TODO: (possibly) merge this method into the above method?
     * detects whether a tile needs to run an action
     * @param Tile
     * @return true if stepping on the tile needs to run an action
     */
   public boolean isThereAInteraction(tile Tile){
        if((Tile.isTileEqualToMonster()) || (Tile.isTileEqualToItem()) || (Tile.isTileEqualToDoor())){
            return true;
        }else {
            return false;
        }
    }

    /** TODO: just use the conditional statement instead?
     * is the tile an item tile?
     * @param Tile
     * @return true this tile is an item tile
     */
    public boolean isItem(tile Tile){
        if(Tile.isTileEqualToItem()){
            return true;
        }else {
            return false;
        }
    }
    /** TODO: just use the conditional statement instead?
     * is the tile an item tile?
     * @param Tile
     * @return true this tile is a monster tile
     */
    public boolean isMonster(tile Tile){
        if(Tile.isTileEqualToMonster()){
            return true;
        }else {
            return false;
        }
    }
    /** TODO: just use the conditional statement instead?
     * is the tile an item tile?
     * @param Tile
     * @return true this tile is a door tile
     */
    public boolean isDoor(tile Tile){
        if(Tile.isTileEqualToDoor()){
            return true;
        }else {
            return false;
        }
    }



    /**
     * display room with player
     */

    public String getMove(){
        Scanner input = new Scanner(System.in);
        System.out.println("Next Move:");
        String move = "";
        move = input.nextLine();
        while(!((move.equals("w") || (move.equals("s")) || (move.equals("a")) || (move.equals("d"))))){
            System.out.println("Next Move:");
            move = input.nextLine();
        }
        return move;
    }
    /**
     * Puts player to next room if walked through door
     */

    public void updatePLayer(){
        tiles[playerTile.getPosR()][playerTile.getPosC()] = playerTile;
    }
    public void clearPLayerFromCurrentPosition(){
        tiles[playerTile.getPosR()][playerTile.getPosC()] = new emptyTile();
    }

    public void populateRoom(int itemCount, int monsterCount, int size) {
        // This works, i fixed problem, in constructor forgot to do after put player, so player overtook an item,
        Random rand = new Random();
        gameItems gameItems = new gameItems();
        gameMonsters gameMonsters = new gameMonsters();
        int row = rand.nextInt(size);
        int col = rand.nextInt(size);
        for(int i=0; i<(monsterCount); i++){
                while ((!(tiles[row][col].isTileEqualEmpty()))) {
                    row = rand.nextInt(size);
                    col = rand.nextInt(size);
                }
                tiles[row][col] = new enemyTile(gameMonsters.getMonster());
            }
        for(int j=0; j<(itemCount); j++){
            while (!(tiles[row][col].isTileEqualEmpty())) {
                row = rand.nextInt(size);
                col = rand.nextInt(size);
            }
            tiles[row][col] = new itemTile(gameItems.getItem());
        }
    }



    public boolean isNeedToMoveRoom() {
        return needToMoveRoom;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getAmountOfMonstersPlayerKilled(){
        return playerTile.getPlayer().getAmountOfMonstersKilled();
    }
    public int getPlayerHealth(){
        return playerTile.getPlayer().getHealth();
    }


}
