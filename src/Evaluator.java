import java.util.ArrayList;

public class Evaluator {
	
	private int type ;
		
	public Evaluator(int t){
		type = t ;
	}
	
	public void evaluate(TreeNode root){
		
		if(type == 0){
			randomEvaluate(root) ;
		}
		if(type == 1){
			greedyEvaluate(root) ;
		}
		if(type == 2){
			randomScoreAssignEvaluate(root) ;
		}
		if(type == 3){
			minimaxEvaluate(root);
		}
	}
	
	public int randomEvaluate(TreeNode root){
		
	}
	
	public int greedyEvaluate(TreeNode root){
		
	}
	
	public int randomScoreAssignEvaluate(TreeNode root){
		
		
	}
	
	public int minimaxEvaluate(TreeNode root){
		
	}
	
}
