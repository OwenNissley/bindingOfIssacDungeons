import java.util.*;

public abstract class room {
    private tile [][]  tiles;
    private playerTile playerTile;
    private boolean needToMoveRoom;

    private boolean gameOver;





    room(int size, int monsterCount, int itemCount){
        needToMoveRoom = false;
        gameOver = false;
        tiles = new tile [size][size];
        for(int i=0; i<tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                tiles[i][j] = new emptyTile();
            }
        }
        generateRoomItemsAndMonsters(itemCount,monsterCount,size);
        playerTile = new playerTile("P");
        tiles[playerTile.getPosR()][playerTile.getPosC()] = playerTile;
        tiles[tiles.length-1][tiles[0].length-1] = new doorTile();
    }

      public String getBoarder(int size){
        String boarder = "";
        for(int i=0; i<size; i++){
            boarder = boarder + "-";
          }
        return boarder;
      }
    public void printRoom(){
        tile door = new doorTile();
        for(int i=0; i<tiles.length; i++){
            if(i == 0){
                System.out.println(getBoarder(tiles.length+2));
            }
            System.out.print("|");
            for(int j=0; j<tiles[0].length; j++){
                System.out.print(tiles[i][j].getDisplaySymbol());
            }
            System.out.print("|");
                if(!(i == tiles.length-1)){
                System.out.println();
            }else {
                    System.out.println();
                    System.out.println(getBoarder(tiles.length+2));
                }
        }
    }

    /**
     * Make tile empty
     */

    /**
     * Takes postion of player and updates accordingly
     */
    public void movePlayer(String input) throws Exception {
        boolean leftMonsterFight = false;
        if(input.equals("w")){
            if(playerTile.getPosR() == 0){
                throw new Exception("Cant move");
            }else {
                clearPLayerFromCurrentPosition();
                tile checkTile = tiles[playerTile.getPosR()-1][playerTile.getPosC()];
                leftMonsterFight = runMoveInteractionsWithDecsionReturn(checkTile);
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
                throw new Exception("Cant move");
            }else {
                clearPLayerFromCurrentPosition();
                tile checkTile = tiles[playerTile.getPosR()+1][playerTile.getPosC()];
                leftMonsterFight = runMoveInteractionsWithDecsionReturn(checkTile);
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
                throw new Exception("Cant move");
            }else {
                clearPLayerFromCurrentPosition();
                tile checkTile = tiles[playerTile.getPosR()][playerTile.getPosC()-1];
                leftMonsterFight = runMoveInteractionsWithDecsionReturn(checkTile);
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
                throw new Exception("Cant move");
            }else {
                clearPLayerFromCurrentPosition();
                tile checkTile = tiles[playerTile.getPosR()][playerTile.getPosC()+1];
                leftMonsterFight = runMoveInteractionsWithDecsionReturn(checkTile);
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

    public boolean runMoveInteractionsWithDecsionReturn(tile checkTile){
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
   public boolean isThereAInteraction(tile Tile){
        if((Tile.isTileEqualToMonster()) || (Tile.isTileEqualToItem()) || (Tile.isTileEqualToDoor())){
            return true;
        }else {
            return false;
        }
    }
    public boolean isItem(tile Tile){
        if(Tile.isTileEqualToItem()){
            return true;
        }else {
            return false;
        }
    }
    public boolean isMonster(tile Tile){
        if(Tile.isTileEqualToMonster()){
            return true;
        }else {
            return false;
        }
    }
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

    public void generateRoomItemsAndMonsters(int itemCount, int monsterCount, int size) {
        Random rand = new Random();
        gameItems gameItems = new gameItems();
        gameMonsters gameMonsters = new gameMonsters();
        int row = rand.nextInt(size);
        int col = rand.nextInt(size);
        for(int i=0; i<(monsterCount); i++){
                while (!(tiles[row][col].isTileEqualEmpty())) {
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
