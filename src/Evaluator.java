import java.awt.Point;
import java.util.ArrayList;

public class Evaluator {
	
	private int type ;
	private ArrayList<Piece> pawns ;
		
	public Evaluator(int t, ArrayList<Piece> p){
		type = t ;
		for(int i = 0;  i < p.size(); i++){
			Piece toAdd = new Piece(p.get(i).color) ;
			toAdd.position.height = p.get(i).position.height ;
			toAdd.position.width = p.get(i).position.width ;
			pawns.add(toAdd) ;
		}
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
