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
	}
	
	

	private void constructTree() {
		root = new TreeNode(null, Game.chessBoard.encode(), playing);
		//System.out.println("constructed");
		expandTree(root, depthLimit);
		
	}
	
	
	private void traverseTree(TreeNode node) {
		if(node.hasChildren()) {
			for (int i = 0; i < node.getChildren().size(); i++) {
				traverseTree(node.getChildren().get(i));
			}
		}
		if (!node.hasChildren()) {
			evaluateNode(node);
		}
		if (!node.isRoot())	{
			if (node.getParent().getPlaying().equals(playing)) {
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
	
	private void evaluateNode(TreeNode n) {
		int score;
		Evaluator eval = new Evaluator(evalType, player);
		score = eval.evaluate(n);
		System.out.println("score = " + score);
		n.setScore(score);
	}
	
	private void expandTree(TreeNode root, int depthLimit){
		int localLimit = depthLimit;
		board.decode(root.codedBoard) ;
		if (localLimit == 0) {
			return;
		}
		//System.out.println("depthlimit = " + depthLimit);
		ArrayList<Position[]> movesForAllFour = new ArrayList() ;
		for(int i = 0; i < player.pawns.size(); i++){
			//System.out.println("selected the " + i + "th pawn");
			Piece selectedPawn = player.pawns.get(i) ;
			ArrayList<Position[]> possibleMoves = new ArrayList() ;
			ArrayList<Position > possibleMoveDestinations = new ArrayList() ;
			selectedPawn.findPaths() ;
			possibleMoveDestinations = selectedPawn.movesPool ;
			for(int m = 0; m < possibleMoveDestinations.size(); m++){
				Position[] move = new Position[3] ;
				move[0] = selectedPawn.position ;
				move[1] = possibleMoveDestinations.get(m) ;
				possibleMoves.add(move) ;
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
		//System.out.println("expanded");
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
		System.out.println("trying to pick pawn");
		return null;
	}

	@Override
	public String findBestMove() {
		// TODO Auto-generated method stub
		//System.out.println("find best move start");
		constructTree();
		traverseTree(root);
		TreeNode bestMoveNode = null ;
		int maxScore = 0 ;
		for(int i = 0; i < root.getChildren().size(); i++){
			if(root.getChildren().get(i).getScore() > maxScore){
				bestMoveNode = root.getChildren().get(i) ;
			}
		}
		System.out.println(root.getChildren().size());
		//System.out.println("selected best movenode");
		return bestMoveNode.codedBoard;
	}

}
