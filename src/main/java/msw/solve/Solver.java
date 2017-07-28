package msw.solve;

public interface Solver
{
  /** 
   * There are six possible movement operations
   * though all might not be valid for a given 
   * state of the three stacks,
   */ 
  enum Move 
  {
    LeftToMiddle,
    LeftToRight,
    MiddleToLeft,
    MiddleToRight,
    RightToLeft,
    RightToMiddle
  }
  
  Move move(Stacks s);

}
