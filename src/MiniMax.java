import java.awt.Color;

public class MiniMax {
	
    //private SearchTree tree;
    private GameTile[][] board;
    private String currentP;
    private TreeNode root ;
    int[][] boardCopy;
    int depthLimit ;
    
    
    public MiniMax(GameTile[][] b, String p, int dL) {
    	this.board = b;
    	this.currentP = p;
    	boardCopy = new int[board.length][board[0].length];
    	depthLimit = dL ;
    	constructTree() ;
    }
 
    private void constructTree() {
        //tree = new SearchTree(null);
    	boardCopy = copyBoard(board);
        root = new TreeNode(boardCopy, currentP, null);
        //tree.setRoot(root);
        expandTree(root, depthLimit);
    }
    
    private void expandTree(TreeNode root, int limit){
    	if(root.getPlayer().equals("White")){
    		for(int i =0; i < boardCopy.length; i++){
    			for(int j= 0; j < boardCopy[0].length; j++){
    				if(boardCopy[i][j] == 1){
    					
    				}
    			}
    		}
    	}
    	else{
    		for(int i =0; i < boardCopy.length; i++){
    			for(int j= 0; j < boardCopy[0].length; j++){
    				if(boardCopy[i][j] == 2){
    					
    				}
    			}
    		}
    	}
    }  
    
    private void checkForLegalMove(int i, int j, int[][] board){
    	//horizontal moves
    	//vertical moves
    	//diagonal moves
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
    				else if (b[x][y].shot) {
    				board[x][y] = 3;
    				}
    				else { 
    					board[x][y] = 0;
    				}
    			}
    		}
    	}
    	
    	return board;
    	
    }
    
    
    
    
}