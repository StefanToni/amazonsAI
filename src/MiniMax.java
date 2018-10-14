import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

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
    
    private void expandTree(TreeNode node, int limit){
    	
    	if(node.getPlayer().equals("White")){
    		for(int i =0; i < boardCopy.length; i++){
    			for(int j= 0; j < boardCopy[0].length; j++){
    				if(boardCopy[i][j] == 1){
    					ArrayList<Point> possibleM = checkForLegalMoves(i,j, boardCopy);
    				}
    			}
    		}
    	}
    	else{
    		for(int i =0; i < boardCopy.length; i++){
    			for(int j= 0; j < boardCopy[0].length; j++){
    				if(boardCopy[i][j] == 2){
    					ArrayList<Point> possibleM = checkForLegalMoves(i,j, boardCopy);
    				}
    			}
    		}
    	}
    }  
    
    private ArrayList<Point> checkForLegalMoves(int i, int j, int[][] board){
    	ArrayList<Point> moves = new ArrayList<>();
    	//horizontal moves
    	for(int xRight = j; xRight < board[0].length; xRight++){
    		if(board[i][xRight] == 1 || board[i][xRight] == 2 || board[i][xRight] == 3){
    			break ;
    		}
    		moves.add(new Point(xRight, i));
    	}
    	for(int xLeft = j; xLeft >= 0; xLeft--){
    		if(board[i][xLeft] == 1 || board[i][xLeft] == 2 || board[i][xLeft] == 3){
    			break ;
    		}
    		moves.add(new Point(xLeft, i));
    	}
    	//vertical moves
    	for(int yDown = i; yDown < board.length; yDown++){
    		if(board[yDown][j] == 1 || board[yDown][j] == 2 || board[yDown][j] == 3){
    			break ;
    		}
    		moves.add(new Point(j, yDown));
    	}
    	for(int yUp = i; yUp >= 0; yUp--){
    		if(board[yUp][j] == 1 || board[yUp][j] == 2 || board[yUp][j] == 3){
    			break ;
    		}
    		moves.add(new Point(yUp, j)) ;
    	}
    	//diagonal moves
    	//top right
    	for(int yUp = i; yUp >= 0; yUp--){
    		for(int xRight = j; xRight < board[0].length; xRight++){
    			if(board[yUp][xRight] == 1 || board[yUp][xRight] == 2 || board[yUp][xRight] == 3){
        			break ;
        		}
    			moves.add(new Point(yUp, xRight)) ;
    		}
    	}
    	//bottom right
    	for(int yDown = i; yDown <  board.length; yDown++){
    		for(int xRight = j; xRight < board[0].length; xRight++){
    			if(board[yDown][xRight] == 1 || board[yDown][xRight] == 2 || board[yDown][xRight] == 3){
        			break ;
        		}
    			moves.add(new Point(yDown, xRight));
    		}
    	}
    	//bottom left
    	for(int yDown = i; yDown < board.length; yDown++){
    		for(int xLeft = j; xLeft >= 0; xLeft--){
    			if(board[yDown][xLeft] == 1 || board[yDown][xLeft] == 2 || board[yDown][xLeft] == 3){
    				break;
    			}
    			moves.add(new Point(yDown, xLeft)) ;
    		}
    	}
    	//top left
    	for(int yUp = i; yUp >= 0; yUp--){
    		for(int xLeft = j; xLeft >= 0; xLeft--){
    			if(board[yUp][xLeft] == 1 || board[yUp][xLeft] == 2 || board[yUp][xLeft] == 3){
    				break  ;
    			}
    			moves.add(new Point(yUp, xLeft)) ;
    		}
    	}
    	return moves;
    }
    
    //Player1 is 1, Player2 is 2, Arrow is 3, empty spots are 0
    private int[][] copyBoard(GameTile[][] b) {
    	int board[][] = new int[b.length][b[0].length];
    	for(int x = 0; x < b.length; x++) {
    		for (int y = 0; y < b[0].length; y++) {
    			if (b[y][x].hasPiece) {
    				if(b[y][x].piece.color == Color.WHITE){
    					board[y][x] = 1;
    				}
    				else if (b[y][x].piece.color == Color.BLACK) {
    					board[y][x] = 2;
    				}
    				else if (b[y][x].shot) {
    				board[y][x] = 3;
    				}
    				else { 
    					board[y][x] = 0;
    				}
    			}
    		}
    	}
    	
    	return board;
    	
    }
    
    
    
    
}