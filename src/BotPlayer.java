import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.function.Supplier;

public class BotPlayer extends Player{// implements Runnable{

    public Position move;
    public Position shot;
    public Piece pawn;
    private HashMap<String, Supplier<ArrayList<Position>>> algorithms = new HashMap<>();
    public String algorithm;
    private Random rand = new Random();
    private GameTile[][] board ;

    BotPlayer(Color color, GameTile[][] b, String algorithm){
        super(color);
        this.algorithm = algorithm;
        algorithms.put("Random", () -> runRandom());
        algorithms.put("MiniMax", () -> runMiniMax());
        //Add conversion from GameTile array to int array
        board = b ;
    }

    /*
    *input: void
    *   Method to be overriden to make new bots.
    *   Finds a random pawn from the players' collection
    *   and makes a random move
    *return: void
    */
    public void run(){
        ArrayList<Position> move_and_shot = algorithms.get(algorithm).get();
        if(move_and_shot.size() == 2){
            this.move = move_and_shot.get(0);
            this.shot = move_and_shot.get(1);
        } else if(move_and_shot.size() < 2){
            this.move = move_and_shot.get(0);
        } else {
            System.out.println("There's a bug here");
        }
    }

    public void runAgain(){
        ArrayList<Position> move_and_shot = algorithms.get(algorithm).get();
        this.shot = move_and_shot.get(0);
    }

    public ArrayList<Position> runRandom(){
        ArrayList<Position> returnValue = new ArrayList<Position>();
        this.pawn = this.pawns.get(rand.nextInt(4));
        returnValue.add(pawn.movesPool.get(rand.nextInt(pawn.movesPool.size())));
        return returnValue;
    }

    public ArrayList<Position> runMiniMax(){
    	//Position[] move = new Position[3] ;
        MiniMax minimax = new MiniMax(board, (color.equals(Color.BLACK)) ? "Black" : "White", 1);
        
        ArrayList<Position> returnValue = new ArrayList<Position>();
        if(minimax.bestMove[0] == null){
        	System.out.println("origin empty");
        }
        if(minimax.bestMove[1] == null){
        	System.out.println("dest empty");
        }
        if(minimax.bestMove[2] == null){
        	System.out.println("arrow empty");
        }
        this.pawn = board[minimax.bestMove[0].width][minimax.bestMove[0].height].piece;
        returnValue.add(minimax.bestMove[1]) ;
        returnValue.add(minimax.bestMove[2]) ;
        return returnValue ;
        
    }
    
}