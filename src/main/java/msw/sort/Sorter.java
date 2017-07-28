package msw.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import msw.solve.Solver;

/**
 *
 * @author Mark
 */
public class Sorter
{
  //public static final char DELIMITER = '|';

  //private final String initial;   //supplied by constructor
  private final Layout initial;     //supplied by constructor
  private final Layout goal;
  private final Solver solver;
  
  public Sorter(String initial)
  {
    this(initial, new msw.WyzmoSolution());
  }

  public Sorter(String initial, Solver solver)
  {
    this.initial = new Layout(initial);
    this.goal = new Layout(new Permutation(initial).first());
    this.solver = solver;
  }

  public List<Layout> sort()
  {
	HashMap<String, Layout> tried = new HashMap<>();
	ArrayList<Layout> result = new ArrayList<>();
	result.add(initial);
	//boolean success = true;
	//System.out.println(result.get(result.size()-1).permutation() + " <==> " + goal.permutation());
    while (!result.get(result.size()-1).permutation().equals(goal.permutation()))
    {
	  Layout current = result.get(result.size()-1);
	  String key = current.permutation();
	  if (tried.containsKey(key))
	  {
		System.out.println("Failed: Cycle detected: " + key);
		//success = false;
		break;
	  }
	  tried.put(key, current);
	  result.add(runRules(current));
	}
	//if (success) System.out.println("Success: " + (result.size() - 1));
	////else System.out.println("Failed: ");
    return result;
  }

  private Layout runRules(Layout l)
  {
    switch (this.solver.move(l))
    {
      case LeftToMiddle : return l.moveLM();
      case LeftToRight : return l.moveLR();
      case MiddleToLeft : return l.moveML();
      case MiddleToRight : return l.moveMR(); 
      case RightToLeft : return l.moveRL();
      case RightToMiddle : return l.moveRM();
      default : return l; //unreachable  
    }
  }

}
