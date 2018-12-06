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
	private BotPlayer player ;
	
	public newMiniMax(BotPlayer p){
		player = p ;
		playing = p.color.toString() ;
		depthLimit = 1 ;
		board = new Board(Game.chessBoard.size, Game.chessBoard.size, Game.chessBoard.parentGame) ;
		board.decode(Game.chessBoard.encode()) ;
		constructTree();
		evaluateTree(root);
	}

	private void constructTree() {
		root = new TreeNode(null, Game.chessBoard.encode(), playing);
		expandTree(root, depthLimit); 
	}
	
	private void evaluateTree(TreeNode node) {

		int invoker;
		if (ogP.equals("White")) {
			invoker = 1;
		} else {
			invoker = 2;
		}
		traverseTree(root, invoker);

	}
	
	private void traverseTree(TreeNode node, int ogP) {
		while (node.hasChildren()) {
			for (int i = 0; i < node.getChildren().size(); i++) {
				traverseTree(node.getChildren().get(i), ogP);
			}
		}
			if (!node.hasChildren()) {
				evaluateNode(node, ogP);
			}
		if (!node.isRoot())	{
			if ((node.getParent().getPlaying().equals("White") && ogP == 1) || (node.getParent().getPlaying().equals("Black") && ogP == 2)) {
				if (node.getParent().getScore() < node.getScore()) {
					node.getParent().setScore(node.getScore());
			}
		}
			else {
				if (node.getParent().getScore() > node.getScore()) {
					node.getParent().setScore(node.getScore());
				}
			}
		}
	}
	
	private void evaluateNode(TreeNode n, int ogP) {
		int score;
		Evaluator eval = new Evaluator(1,/*array with pawns*/);
		score = eval.evaluate(n);
		n.setScore(score);
	}
	
	private void expandTree(TreeNode root, int depthLimit){
		int localLimit = depthLimit;
		if (localLimit == 0) {
			return;
		}
	}
	
	@Override
	public Piece pickPawn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findBestMove() {
		// TODO Auto-generated method stub
		constructTree() ;
		return null;
	}

}
