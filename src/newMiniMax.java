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
		ArrayList<Position[]> movesForAllFour = new ArrayList() ;
		for(int i = 0; i < player.pawns.size(); i++){
			Piece selectedPawn = player.pawns.get(i) ;
			ArrayList<Position[]> possibleMoves = new ArrayList() ;
			ArrayList<Position > possibleMoveDestinations = new ArrayList() ;
			selectedPawn.findPaths() ;
			possibleMoveDestinations = selectedPawn.movesPool ;
			for(int m = 0; m < possibleMoveDestinations.size(); m++){
				Position[] move = new Position[3] ;
				move[0] = selectedPawn.position ;
				move[1] = possibleMoveDestinations.get(m) ;
			}
			for(int j = 0; j < possibleMoves.size(); j++){
				Position origin = new Position(selectedPawn.position.width, selectedPawn.position.height) ;
				move(possibleMoves.get(j));
				ArrayList<Position> possibleShots = new ArrayList() ;
				selectedPawn.findPaths();
				possibleShots = selectedPawn.movesPool ;
				for(int k = 0; k < possibleShots.size(); k++){
					Position[] finalMove = new Position[3] ;
					finalMove[0] = origin ;
					finalMove[1] = selectedPawn.position ;
					finalMove[2] = possibleShots.get(k) ;
					movesForAllFour.add(finalMove) ;
					//GameTile tile = board.tiles[possibleShots.get(k)[2].width][possibleShots.get(k)[2].height];
					//tile.wasShot = false ;
				}
				returnMove(possibleMoves.get(j)) ;
			}
		}
		ArrayList<String> futureBoards = new ArrayList() ;
		for(int i = 0; i < movesForAllFour.size(); i++){
			move(movesForAllFour.get(i)) ;
			shoot(movesForAllFour.get(i)) ;
			futureBoards.add(board.encode()) ;
			returnMove(movesForAllFour.get(i)) ;
			GameTile tile = board.tiles[movesForAllFour.get(i)[2].width][movesForAllFour.get(i)[2].height];
			tile.wasShot = false ;
		}
		for(int i = 0; i < futureBoards.size(); i++){
			TreeNode newNode = new TreeNode(root, futureBoards.get(i), nextPlayer(root)) ;
			root.addChild(newNode);
		}
		for(int i = 0; i < root.getChildren().size(); i++){
			expandTree(root.getChildren().get(i), localLimit - 1) ;
		}
	}
	
	private String nextPlayer(TreeNode node){
		if(node.playing.equals("WHITE")){
			return "BLACK" ;
		}
		else{
			return "WHITE" ;
		}
	}
	
	private void shoot(Position[] shot) {
        GameTile tile = board.tiles[shot[2].width][shot[2].height];
        tile.shoot();
    }
	
	private void returnMove(Position[] move) {
        Piece selectedPawn = board.tiles[move[1].width][move[1].height].removePiece();
        GameTile tile = board.tiles[move[0].width][move[0].height];
        tile.setPiece(selectedPawn);
    }
	
	private void move(Position[] move) {
        Piece selectedPawn = board.tiles[move[0].width][move[0].height].removePiece();
        GameTile tile = board.tiles[move[1].width][move[1].height];
        tile.setPiece(selectedPawn);
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
