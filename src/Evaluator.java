import java.util.ArrayList;

public class Evaluator {
	
	private int type ;
	private GameTile[][] moveToMake ;
		
	public Evaluator(int t){
		type = t ;
	}
	
	public void evaluate(TreeNode root){
		
		if(type == 0){
			randomEvaluate(root) ;
		}
		if(type == 1){
			greedyEvaluate(root) ;
		}
		if(type == 2){
			randomScoreAssignEvaluate(root) ;
		}
		if(type == 3){
			minimaxEvaluate(root);
		}
	}
	
	public void randomEvaluate(TreeNode root){
		Game fake = new Game();
		GameTile[][] original = new GameTile[10][10] ; //assigned by contructor
		GameTile[][] copy = new GameTile[10][10] ;
		for(int i = 0; i < 10 ; i++){
			for(int j = 0; j < 10; j++){
				copy[i][j] = new GameTile(original[i][j].getBackground(),i,j, fake);
			}
		}
		for(int i = 0; i < 10 ; i++){
			for(int j = 0; j < 10; j++){
				if(original[i][j].hasPiece){
					//create new copy of piece
					/*copy[i][j].placePiece(new Piece() )*/
				}
			}
		}
		
	}
	
	public void greedyEvaluate(TreeNode root){
		
	}
	
	public void randomScoreAssignEvaluate(TreeNode root){
		
		
	}
	
	public void minimaxEvaluate(TreeNode root){
		
	}
	
	public void addAllPossibleMoves(TreeNode t){
		String nextPlayer ;
		ArrayList<GameTile[][]> moves = new ArrayList<GameTile[][]>() ;
		for(int i = 0; i < moves.size(); i++){
			if(t.getPlayer() == "White"){
				nextPlayer = "Black" ;
			}
			else{
				nextPlayer = "White" ;
			}
			TreeNode newNode = new TreeNode(moves.get(i), nextPlayer, t) ;
			t.addChild(newNode) ;
		}
	}

}
