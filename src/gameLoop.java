import java.util.Scanner;

public class gameLoop {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Username");
        String userName = input.nextLine();
        System.out.println("Enter size of game, (How many rooms you want to play throughe) you want to play?");
        int size = getValidInt();
        map gameMap = new map("easy", size);
        room currentRoom = null;
        boolean completion = true;
        boolean firstBreak = false;
        for (int i = 0; i <size; i++) {
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
                    System.out.println();
                }
            }
            if(firstBreak){
                break;
            }
        }
        if(completion){
            System.out.println("You have won with a score of: " + scoreCalculator(currentRoom,size) + " Thats garb, get higher");
        }else {
            System.out.println("Wow Your garb, you died!! MWAHAHAHAHAH\n" +
                    "DEAR DIARY,\n" +
                    "TODAY I DIED.I WAS KILLED BY THIS THING →, \n" +
                    "too tired to code symbol, I LEAVE ALL THAT I OWN TO MY CAT GUPPY, \n" +
                    "GOODBYE CRUEL WORLD\n +" +
                    "XOXO " + userName);

        }
    }

    public static double scoreCalculator(room Room, int size){
        double score = 0;
        int pHealth = Room.getPlayerHealth();
        int monsterCount = Room.getAmountOfMonstersPlayerKilled();
        score = score + (((pHealth + monsterCount)*100)/size);
        return score;
        }

    public static int getValidInt(){
        Scanner console = new Scanner(System.in);
        while (!console.hasNextInt()) {
            System.out.println("Not a valid input, try again.");
            console.nextLine();
        }
        int location = console.nextInt();
        return location;

    }

}

