package msw.solve;

public interface Stacks {
  
  /** returns the top card of the left stack when occupied,
   *  char(0) when empty
   */
  char left(); 
  
  /** returns the top card of the middle stack when occupied,
   *  char(0) when empty
   */
  char middle(); 
  
  /** returns the top card of the right stack when occupied, 
   * char(0) when empty 
   */
  char right();
  
  boolean emptyLeft();
  boolean emptyMiddle();
  boolean emptyRight();
  
  boolean occupiedLeft();
  boolean occupiedMiddle();
  boolean occupiedRight();

}
