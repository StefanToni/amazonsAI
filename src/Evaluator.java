import java.awt.Point;
import java.util.ArrayList;

public class Evaluator {
	
	private int type ;
	private ArrayList<Piece> pawns ;
	private String playing ;
		
	public Evaluator(int t, BotPlayer player){
		type = t ;
		pawns = player.pawns ;
		playing = player.color.toString() ;
	}
	
	
	public int evaluate(TreeNode t){
		if(type == 1){
			return ownMobilityEvaluate(t) ;
		}
		else{
			System.out.println("no proper evaluation selceted, this will not work !!!!");
			return 0 ;
		}
		
	}
	
	public int ownMobilityEvaluate(TreeNode node){
		//score is just equal to the number of possible moves for the invoking player at any given boardstate
		int score = 0 ;
		for(int i = 0; i < pawns.size(); i++){
			Piece pieceToMove = pawns.get(i) ;
			pieceToMove.findPaths() ;
			score = score + pieceToMove.movesPool.size() ;
		}
		return score ;
	}
	
	

	
}
