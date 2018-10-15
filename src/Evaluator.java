import java.util.ArrayList;

public class Evaluator {
	
	private int type ;
		
	public Evaluator(int t){
		type = t ;
	}
	
	public void evaluate(TreeNode node){
		
		if(type == 0){
			randomEvaluate(node) ;
		}
		if(type == 1){
			greedyEvaluate(node) ;
		}
		if(type == 2){
			randomScoreAssignEvaluate(node) ;
		}
		if(type == 3){
			minimaxEvaluate(node);
		}
	}
	
	public int randomEvaluate(TreeNode node){
		
	}
	
	public int greedyEvaluate(TreeNode node){
		
	}
	
	public int randomScoreAssignEvaluate(TreeNode node){
		
		
	}
	
	public int minimaxEvaluate(TreeNode node){
		
	}
	
}
