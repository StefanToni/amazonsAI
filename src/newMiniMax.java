import java.util.ArrayList;
import java.awt.Color;
import java.awt.Point;


public class newMiniMax implements Algorithm {
	
	private String playing ;
	private TreeNode root ;
	private boolean whiteWon ;
	private boolean blackWon ;
	private int depthLimit ;
	private int evalType = 1 ;
	private static Board board ;
	private Player player ;
	
	public newMiniMax(Player p){
		player = p ;
		playing = p.color.toString() ;
		depthLimit = 1 ;
		board = new Board(Game.chessBoard.size, Game.chessBoard.size, Game.chessBoard.parentGame) ;
		board.decode(Game.chessBoard.encode()) ;
		constructTree();
	}
	
	private void constructTree() {
		root = new TreeNode(null, board);
		expandTree(root, depthlimit); 
	}
	
	@Override
	public Piece pickPawn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findBestMove() {
		// TODO Auto-generated method stub
		return null;
	}

}
