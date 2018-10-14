import java.util.ArrayList;

public class SearchTree {
	
	private TreeNode root ;
	private int size ;
	private ArrayList<TreeNode> nodes ;
	
	public SearchTree(TreeNode root){
		this.root  = root ;
		size = 0 ;
		nodes = new ArrayList<>() ;
	}
	
	public TreeNode getRoot(){
		return root ;
	}
	
	public int getSize(){
		return size ;
	}
	
	public boolean isLeaf(TreeNode n){
		if(n.hasChildren() == true){
			return false ;
		}
		else{
			return true ;
		}
	}
	
	public TreeNode getParent(TreeNode n){
		return n.getParent() ;
	}
	
	public ArrayList<TreeNode> getChildren(TreeNode n){
		return n.getChildren() ;
	}
	
	public boolean isRoot(TreeNode t){
		if(t.getParent() == null){
			return true;
		}
		else{
			return false ;
		}
	}
	public void addNode(TreeNode n){
		nodes.add(n) ;
		size++ ;
	}
}
