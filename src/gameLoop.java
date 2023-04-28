import java.util.Scanner;

public class gameLoop {
    public static void main(String[] args) throws Exception {
        map gameMap = new map("easy", 5);
        room currentRoom = null;
        boolean completion = true;
        boolean firstBreak = false;
        for (int i = 0; i < 5; i++) {
            currentRoom = gameMap.getNextRoom();
            while (!(currentRoom.isNeedToMoveRoom())) {
                currentRoom.printRoom();
                try {
                    String move = currentRoom.getMove();
                    currentRoom.movePlayer(move);
                    if(currentRoom.isGameOver()){
                        completion = false;
                        firstBreak = true;
                        break;
                    }
                }catch (Exception e){
                    System.out.print(e.getMessage() + " Try again:");
                }
            }
            if(firstBreak){
                break;
            }
        }
        if(completion){
            System.out.println("You have won with a score of: " + scoreCalculator(currentRoom,5));
        }else {
            System.out.println("Wow Your garb, you died!! HAHAHAHAH");
        }
    }

    public static int scoreCalculator(room Room, int size){
        int score = 0;
        int pHealth = Room.getPlayerHealth();
        int monsterCount = Room.getAmountOfMonstersPlayerKilled();
        switch (monsterCount) {
            case 1 -> score += 100;
            case 2 -> score += 200;
            case 3 -> score += 300;
            case 4 -> score += 400;
            case 5 -> score += 500;
        }
        switch (pHealth) {
            case 1 -> score += 100;
            case 2 -> score += 200;
            case 3 -> score += 300;
            case 4 -> score += 400;
            case 5 -> score += 500;
            case 6 -> score += 600;
            case 7 -> score += 700;
            case 8 -> score += 800;
            case 9 -> score += 900;
            case 10 -> score += 1000;
        }
        return score;
            
        }
    }

