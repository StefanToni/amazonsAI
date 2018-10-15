import java.awt.*;

public class BotPlayer extends Player{

	private GameTile[][] chessBoard;
	private int depthLimit = 3;
	private String Color;
	
    BotPlayer(Color color){
        super(color);
        if (color.equals(color.WHITE)) Color = "White";
        if (color.equals(color.BLACK)) Color = "Black";
    }
    
    MiniMax mini = new MiniMax(chessBoard, Color, depthLimit);
    
}