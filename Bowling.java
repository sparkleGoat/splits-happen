package com.bowling;

/**
 * class to take a series of 10 pin bowling frames
 * and return a total score for game.
 */
public class Bowling {

    private static String gameFrames = null;

    //constructor
    Bowling(String frame){
        this.gameFrames = frame;
    }

    public static void main(String[] args){
       run();
    }

    public static int playGame(String frames) {
        return parseInput(frames);
    }

    public static int run(){
       return playGame(gameFrames);
    }

    // this is a huge function and should be
    //broken down into the cases
    public static int parseInput(String frames){
         String[] parsedFrame = frames.trim().split("(?!^)");
        int totalScore = 0;
        int numberOfGames = 10;
        int nextPosition = 0;
        int currentPosition = 0;
        int numberOfTurns = parsedFrame.length;

        do{
            // Case 1 if first roll of current frame is a strike
            // Must check numberOfTurns so we don't hit an ArrayOutOfBoundsException
            if(parsedFrame[currentPosition].equals("X") && numberOfTurns-1 >= currentPosition+2){
                //looking up next roll to add to score
                if(parsedFrame[currentPosition+1].matches("[0-9]+")){
                    totalScore += Integer.valueOf(parsedFrame[currentPosition+1].trim());
                  // adding 10 for an additional strike
                } else if (parsedFrame[currentPosition+1].equals("X")){
                    totalScore += 10;
                } else { //finishing out the if-if else - else logic
                    totalScore += 0;
                }
                 //checking the last roll of game , if / means spare
                if(parsedFrame[currentPosition+2].equals("/")){
                    totalScore += (10 - Integer.valueOf(parsedFrame[currentPosition+1]));
                    // strike so add 10 to score
                } else if (parsedFrame[currentPosition+2].equals("X")){
                    totalScore += 10;
                } else { // [0-9] roll
                    totalScore += Integer.valueOf(parsedFrame[currentPosition+2]);
                }
                //setting the next position.
                // If you get a strike first the games point are
                // added to next game and you don't want to skip over them.
                nextPosition += 1;
                //This is the 10 points for initial strike
                totalScore += 10;

             // Case 2 if first roll of frame is not a strike/miss/spare
            } else if (parsedFrame[currentPosition].matches("[0-9]+")){
                //since you didn't get a strike, points are not carried over
                //so you can skip over them, they are counted for this turn
                nextPosition += 2;
                //adding your current roll to total score
                totalScore += Integer.valueOf(parsedFrame[currentPosition].trim());
                //must check so we don't get an ArrayOutOfBounds because we will
                //be checking the previous roll for a strike or not
                if(currentPosition >= 1){
                    //this catches the occurrence of a strike in the last round
                    if (parsedFrame[currentPosition-1].equals("X")){
                        //logic for if you had a strike followed by a spare
                        if(parsedFrame[currentPosition+1].equals("/")){
                            totalScore += (10-Integer.valueOf(parsedFrame[currentPosition].trim()));
                        }
                        totalScore += Integer.valueOf(parsedFrame[currentPosition+2].trim());
                        //if you did not have a strike previous game
                    } else if (parsedFrame[currentPosition-1].matches("[0-9]+")) { //-1 is a number
                        //we have the current roll globally added to this case,
                        //we want to subtract it here because you didn't have a strike so it stands alone
                        totalScore -= Integer.valueOf(parsedFrame[currentPosition].trim());
                        // adding 10 for a spare
                        if(parsedFrame[currentPosition+1].equals("/")){
                            totalScore += 10;
                        }
                    }else{
                        //standard roll with a regular spare after the first game
                        if(parsedFrame[currentPosition+1].equals("/")){
                            totalScore += 10;
                        }
                    }
                    //currentPosition < 1, initial position
                    //standard roll with a regular spare as second roll the first game
                } else if(parsedFrame[currentPosition+1].equals("/")){
                    totalScore += 10;
                }
                // Case 3 if first roll of current frame is a miss :(
            } else if (parsedFrame[currentPosition].equals("-")){
                nextPosition += 2;
                //a miss and a strike, total of 10 points for this frame
                if(parsedFrame[currentPosition+1].equals("X")){
                    totalScore += 10;
                    // a miss and less than strike points added.
                } else {  //+1 is a number
                    totalScore += Integer.valueOf(parsedFrame[currentPosition+1].trim());
                }
            }
            //dependant on your role your next position will change to get correct points.
            currentPosition = nextPosition;
            //one game is finished , decrement counter
            numberOfGames--;
        } while (numberOfGames != 0);

        //returning total score
         return totalScore;
    }
}
