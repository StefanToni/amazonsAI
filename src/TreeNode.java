import java.util.ArrayList;



public class TreeNode {
	
	private TreeNode parent ;
	private ArrayList<TreeNode> children ;
	private int score ;
	private Board board ;
	private Game game ;
	
	public TreeNode(TreeNode pa, String b, Game g){
		children = new ArrayList<TreeNode>() ;
		parent = pa;
		score = 0 ;
		game = g ;
		board = new Board(game.getBoard().size, game.getBoard().size, game) ;
		board.decode(b) ;
	}

	public void addChild(TreeNode c){
		children.add(c) ;
	}
	
	public ArrayList<TreeNode> getChildren(){
		return children ;
	}
	
	public TreeNode getParent(){
		return parent ;
	}
	
	public boolean hasChildren(){
		if(children.isEmpty()){
			return false ;
		}
		else{
			return true ;
		}
	}
	
	public boolean hasParent(){
		if(getParent() == null){
			return false;
		}
		else{
			return true ;
		}
	}
	
	public boolean isRoot(){
		if(!hasParent()){
			return true ;
		}
		else{
			return false ;
		}
	}
	
	public void setScore(int s){
		score = s ;
	}
	
	public int getScore(){
		return score ;
	}


}
