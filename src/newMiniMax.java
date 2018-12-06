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
		expandTree(root, depthLimit); 
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
			possibleMoves.addAll(computeAllMoves()) ;
			for(int j = 0; j < possibleMoves.size(); j++){
				move(possibleMoves.get(j));
				ArrayList<Position[]> possibleShots = new ArrayList() ;
				possibleShots.addAll(computeAllShots()) ;
				for(int k = 0; k < possibleShots.size(); k++){
					movesForAllFour.add(possibleShots.get(k)) ;
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
