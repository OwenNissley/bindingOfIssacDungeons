import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public abstract class room {
    private tile [][]  tiles;
    private player player;

    private ArrayList<Integer> playerPosition;

    room(int size, int monsterCount, int itemCount){
        tiles = new tile [size][size];
        for(int i=0; i<tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                tiles[i][j] = new emptyTile();
            }
        }
        for(int i=0; i<itemCount; i++){
            Random r = new Random();
            gameItems items = new gameItems();
            int randomnizer = r.nextInt(size-1);
            while(randomnizer == 0){
                 randomnizer = r.nextInt(size-1);
            }
            int row = i+randomnizer;
            tiles[row][r.nextInt(size)] = new itemTile(items.getItem());
        }
        player = new player();
        playerPosition = new ArrayList<>();
        playerPosition.add(0,0);
        playerPosition.add(1,0);
        tiles[playerPosition.get(0)][playerPosition.get(1)] = new playerTile("P");
    }

    public tile[][] getTiles() {
        return tiles;
    }

    public void printRoom(){
        for(int i=0; i<tiles.length; i++){
            for(int j=0; j<tiles[0].length; j++){
                if((tiles[i][j].isEqualToPlayer())){
                    System.out.print(player.toPrint());
                }else {
                    System.out.print(tiles[i][j].getSymbol());
                }
            }
                if(!(i == tiles.length-1)){
                System.out.println();
            }else {
                    System.out.println();
                    System.out.println("------");
                }
        }
    }
/*
    public ArrayList<Integer> getPlayerPosition(){
        ArrayList<Integer> indexes = new ArrayList<>();
        for(int i=0; i<tiles.length; i++){
            for(int j=0; j<tiles[0].length; j++){
                if(tiles[i][j].getSymbol().equals(player.getSymbol())){
                    indexes.add(i);
                    indexes.add(j);
                }
            }
        }
        return indexes;
    }

 */

    /**
     * Make tile empty
     */
    public void updateTile(tile Tile){
        Tile.tileReset();
    }
    /**
     * Takes postion of player and updates accordingly
     */
    public void movePlayer(String input) throws Exception {
        tiles[playerPosition.get(0)][playerPosition.get(1)] = new emptyTile();
        if(input.equals("u")){
            if(playerPosition.get(0) == 0){
                tiles[playerPosition.get(0)][playerPosition.get(1)] = new playerTile("P");
                throw new Exception("Cant move");
            }else {
                playerPosition.set(0,(playerPosition.get(0)-1));
            }
        } else if (input.equals("d")) {
            if(playerPosition.get(0) == tiles.length-1){
                tiles[playerPosition.get(0)][playerPosition.get(1)] = new playerTile("P");
                throw new Exception("Cant move");
            }else {
                playerPosition.set(0,(playerPosition.get(0)+1));
            }
        }else if (input.equals("l")) {
            if(playerPosition.get(1) == 0){
                tiles[playerPosition.get(0)][playerPosition.get(1)] = new playerTile("P");
                throw new Exception("Cant move");
            }else {
                playerPosition.set(1,(playerPosition.get(1)-1));
            }
        }else if (input.equals("r")) {
            if(playerPosition.get(1) == tiles[0].length-1){
                tiles[playerPosition.get(0)][playerPosition.get(1)] = new playerTile("P");
                throw new Exception("Cant move");
            }else {
                playerPosition.set(1,(playerPosition.get(1)+1));
            }
        }
        tiles[playerPosition.get(0)][playerPosition.get(1)] = new playerTile("P");
    }

    public      (   ){
        if(move.eauls(dksjfs)){

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
        while(!((move.equals("u") || (move.equals("d")) || (move.equals("r")) || (move.equals("l"))))){
            System.out.println("Next Move:");
            move = input.nextLine();
        }
        return move;
    }
    /**
     * Puts player to next room if walked through door
     */
    public void toNewRoom(){

    }
}
