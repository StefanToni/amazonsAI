import java.awt.Color;

public class MiniMax {
	
    private SearchTree tree;
    private GameTile[][] board;
    private String currentP;
    
    public MiniMax(GameTile[][] b, String p) {
    	this.board = b;
    	this.currentP = p;
    	
    	
    }
 
    public void constructTree(int noOfBones) {
        //tree = new SearchTree(null);
        TreeNode root = new TreeNode(board, currentP, null);
        //tree.setRoot(root);
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
    
    //Player1 is 1, Player2 is 2, Arrow is 3, empty spots are 0
    private int[][] copyBoard(GameTile[][] b) {
    	int board[][] = new int[b.length][b[0].length];
    	for(int x = 0; x < b.length; x++) {
    		for (int y = 0; y < b[0].length; y++) {
    			if (b[x][y].hasPiece) {
    				if(b[x][y].piece.color == Color.WHITE){
    					board[x][y] = 1;
    				}
    				else if (b[x][y].piece.color == Color.BLACK) {
    					board[x][y] = 2;
    				}
    			
    				else (b[x][y].shot) {
    				board[x][y] = 3;
    				}
    			}
    		}
    	}
    	
    }
    
    
    
    
}