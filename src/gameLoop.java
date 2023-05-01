import java.util.Scanner;

public class gameLoop {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Username");
        String userName = input.nextLine();
        System.out.println("Enter size of game, (How many rooms you want to play through)?");
        int size = getValidInt();
        map gameMap = new map("easy", size);
        // initialize variables
        room currentRoom = null;
        boolean completion = true;
        boolean firstBreak = false; // what is firstbreak?
        for (int i = 0; i < size; i++) { // start looping through the list of rooms
            currentRoom = gameMap.getNextRoom();
            while (!(currentRoom.isNeedToMoveRoom())) { // while staying in this room
                currentRoom.printRoom(); // print the room with the player
                try {
                    String move = currentRoom.getMove(); // register player's move
                    currentRoom.movePlayer(move); // move the player
                    if(currentRoom.isGameOver()){ // break out of loop for game overs
                        completion = false;
                        firstBreak = true;
                        break;
                    }
                }catch (Exception e){ // catch illegal actions
                    System.out.print(e.getMessage() + " Try again:");
                    System.out.println();
                }
            }
            if(firstBreak){ // continue to break out of the loop? (is this even needed?)
                break;
            }
        }
        if(completion){
            System.out.println("You, "+ userName +", have won with a score of: " + scoreCalculator(currentRoom,size) + " That's garb, get higher");
        }else {
            System.out.println("Wow You're garb, you died!! MWAHAHAHAHAH\n" +
                    "DEAR DIARY,\n" +
                    "TODAY I DIED. I WAS KILLED BY THIS THING →, \n" + // I think we only need to grab the location from the room and grab that location's symbol
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

    public static int getValidInt(){ // rename to "inp" instead of "int"?
        Scanner console = new Scanner(System.in);
        while (!console.hasNextInt()) {
            System.out.println("Not a valid input, try again.");
            console.nextLine();
        }
        int location = console.nextInt();
        return location;

    }

}

