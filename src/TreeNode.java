import java.util.ArrayList;

public class TreeNode {
	
	private GameTile[][] board ;
	private String player ;
	private TreeNode parent ;
	private ArrayList<TreeNode> children ;
	private SearchTree tree ;
	
	public TreeNode(GameTile[][] b, String pl, TreeNode pa, SearchTree st){
		children = new ArrayList<TreeNode>() ;
		board = b ;
		parent = pa;
		player = pl ;	
		tree = st ;
		
	}
	
	public void addChild(TreeNode c){
		children.add(c) ;
		tree.addNode(c);
	}
	
	public ArrayList<TreeNode> getChildren(){
		return children ;
	}
	
	public String getPlayer(){
		return player ;
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
	
	public GameTile[][] getBoard(){
		return board ;
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

}
