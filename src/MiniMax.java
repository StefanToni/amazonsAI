
public class MiniMax {
	
    private SearchTree tree;
    private GameTile[][] board;
    private String currentP;
    
    public MiniMax(GameTile[][] b, String p) {
    	this.board = b;
    	this.currentP = p;
    	
    	
    }
 
    public void constructTree(int noOfBones) {
        tree = new SearchTree(null);
        TreeNode root = new TreeNode(board, currentP, null);
        tree.setRoot(root);
        constructTree(root);
    }
 
    private void constructTree(Node parentNode) {
        List<Integer> listofPossibleHeaps 
          = GameOfBones.getPossibleStates(parentNode.getNoOfBones());
        boolean isChildMaxPlayer = !parentNode.isMaxPlayer();
        listofPossibleHeaps.forEach(n -> {
            Node newNode = new Node(n, isChildMaxPlayer);
            parentNode.addChild(newNode);
            if (newNode.getNoOfBones() > 0) {
                constructTree(newNode);
            }
        });
    }
    
    
    private int[][] copyBoard(GameTile[][] b) {
    	
    }
    
    
    
    
}