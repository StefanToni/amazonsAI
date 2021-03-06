import java.util.ArrayList;



public class TreeNode {
	
	private TreeNode parent = null ;
	private ArrayList<TreeNode> children ;
	private int score ;
	private static Board board ;
	public String codedBoard ;
	public String playing ;
	

	public TreeNode(TreeNode pa, String b, String p){
		children = new ArrayList<TreeNode>() ;
		parent = pa;
		score = 0 ;
		codedBoard = b ;
		board = new Board(Game.chessBoard.size, Game.chessBoard.size, Game.chessBoard.parentGame) ;
		board.decode(b) ;
		playing = p ;
	}

	public void addChild(TreeNode c){
		children.add(c) ;
		c.setParent(this);
	}
	
	private void setParent(TreeNode treeNode) {
		this.parent = treeNode;
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

	public String getPlaying() {
		return playing;
	}

	public void setPlaying(String playing) {
		this.playing = playing;
	}

}
