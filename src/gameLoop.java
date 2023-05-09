import java.util.Scanner;

public class gameLoop {
    public static void main(String[] args) throws Exception {
        // setup
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Username");
        String userName = input.nextLine();
        System.out.println("Enter size of game, (How many rooms you want to play through)?");
        int size = getValidInt();
        System.out.println("Enter difficulty of game you like, (easy,medium,hard,mixed, or (constact creator for secret mode)?");
        String difficulty = getValidDifficlty();
        map gameMap = new map(difficulty, size);
        // initialize variables
        room currentRoom = null;
        boolean completion = true;
        boolean firstBreak = false; // what is firstbreak?
        System.out.println("?: item or monster, ║: wall, ⛫: door, ጰ: player");
        for (int i = 0; i < size; i++) { // start looping through the list of rooms.
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
            if(firstBreak){ // handle breaking out of the loop
                break;
            }
        }
        if(completion){
            //print the username, score, win message, and sarcastic comment
            System.out.println("You, "+ userName +", have won with a score of: " + scoreCalculator(currentRoom,size) + " That's garb, get higher");
        }else {
            //print sarcastic lose message
            System.out.println("Wow You're garb, you died!! MWAHAHAHAHAH\n" +
                    "DEAR DIARY,\n" +
                    "TODAY I DIED. I WAS KILLED BY THIS THING →, \n" + // I think we only need to grab the location from the room and grab that location's symbol
                    "too tired to code symbol, I LEAVE ALL THAT I OWN TO MY CAT GUPPY, \n" +
                    "GOODBYE CRUEL WORLD\n +" +
                    "XOXO " + userName);

        }
    }

    /**
     * calculates the score
     * @param Room the room that houses the player currently
     * @param size the number of rooms the player got through
     * @return returns the player's score after winning, factoring the difficulty, number of monsters defeated, size, and length.
     */
    public static double scoreCalculator(room Room, int size){
        double score = 0;
        int pHealth = Room.getPlayerHealth();
        int monsterCount = Room.getAmountOfMonstersPlayerKilled();
        score = score + (((pHealth + monsterCount)*100));
        return score;
        }

    /**
     * checks input to be an integer
     * @return returns the integer
     */
    public static int getValidInt(){
        Scanner console = new Scanner(System.in);
        while (!console.hasNextInt()) {
            System.out.println("Not a valid input, try again.");
            console.nextLine();
        }
        int location = console.nextInt();
        return location;

    }

    /**
     * listens for a valid game difficulty
     * @return returns a string that is the proper integer
     */
    public static String getValidDifficlty(){ // rename to "inp" instead of "int"?
        Scanner console = new Scanner(System.in);
        String nextLine = console.nextLine();
        while (!( (nextLine.equals("easy")) || (nextLine.equals("medium")) || (nextLine.equals("hard")) || (nextLine.equals("mixed")) || (nextLine.equals("17543")) )){
            System.out.println("Not a valid input, try again.");
             nextLine = console.nextLine();
        }
        return nextLine;
    }

}

