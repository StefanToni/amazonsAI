import java.util.ArrayList;

public class Evaluator {
	
	private int type ;
		
	public Evaluator(int t){
		type = t ;
	}
	
	
	public int evaluate(TreeNode t){
		if(type == 1){
			return mobilityEvaluate(t) ;
		}
		return 0 ;
	}
	
	public int mobilityEvaluate(TreeNode node){
		int score = 0 ;
		return score ;
	}

	
}
