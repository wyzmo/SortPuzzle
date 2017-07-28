package msw.sort;

import msw.solve.Stacks;

/**
 *
 * @author Mark
 */
public class Layout implements Stacks
{
  public static final char DELIMITER = '|';
  
  private static final char NUL = '\0';
  private static final int LEFT = 0;
  private static final int MIDDLE = 1;
  private static final int RIGHT = 2;

  private final String permutation;
  //private final int one;
  //private final int two;
  
  private final String[] stacks = new String[3];
  private final char[] tops = new char[3]; 
  private final String display;
  private final int value;

  public Layout(String permutation)
  {
    this.permutation = permutation;
    int one = permutation.indexOf(DELIMITER);
    int two = permutation.lastIndexOf(DELIMITER);
    //System.out.println("One: " + one + " Two: " + two + " Check: " + permutation.indexOf(DELIMITER, one));
    if (!((one < two) && (permutation.indexOf(DELIMITER,one+1) == two)))
      throw new IllegalArgumentException("String needs exactly two delimiter characters");
    this.stacks[0] = permutation.substring(0,one);
    this.stacks[1] = permutation.substring(one+1,two);
    this.stacks[2] = permutation.substring(two+1);
    char[] firstChars = new char[]{'-','-','-',};
    for(int i = 0; i < 3; i++) 
    {
      if (this.stacks[i].isEmpty()) continue;
      this.tops[i] = this.stacks[i].charAt(0);
      firstChars[i] = tops[i];
    }
    this.display = new String(firstChars);
    this.value = (this.stacks[0].isEmpty() ? 0 : 4) + (this.stacks[1].isEmpty() ? 0 : 2) + (this.stacks[2].isEmpty() ? 0 : 1);
  }

  public String display() { return this.display; }
  public String permutation() { return this.permutation; }
  public int value() { return this.value; }
  
  public char left() { return tops[LEFT]; }
  public char middle() { return tops[MIDDLE]; }
  public char right() { return tops[RIGHT]; }
  
  public boolean emptyLeft() { return tops[LEFT] == NUL; }
  public boolean emptyMiddle() { return tops[MIDDLE] == NUL; }
  public boolean emptyRight() { return tops[RIGHT] == NUL; }
  
  public boolean occupiedLeft() { return !emptyLeft(); }
  public boolean occupiedMiddle() { return !emptyMiddle(); }
  public boolean occupiedRight() { return !emptyRight(); }

  public String toString()
  {
	return permutation + ' ' + display + ' ' + value;
  }

  Layout moveLM()
  {
	if (stacks[0].isEmpty()) throw new IllegalStateException("Left stack is empty");
	if (!stacks[1].isEmpty()) throw new IllegalStateException("Middle stack is occupied");
	String left = stacks[0].substring(1);
	String middle = stacks[0].substring(0,1);
	String right = stacks[2];
	return new Layout(left + DELIMITER + middle + DELIMITER + right);
  }

  Layout moveLR()
  {
  	if (stacks[0].isEmpty()) throw new IllegalStateException("Left stack is empty");
  	String left = stacks[0].substring(1);
  	String middle = stacks[1];
  	String right = stacks[0].substring(0,1) + stacks[2];
  	return new Layout(left + DELIMITER + middle + DELIMITER + right);
  }

  Layout moveML()
  {
  	if (stacks[1].isEmpty()) throw new IllegalStateException("Middle stack is empty");
  	String left = stacks[1].substring(0,1) + stacks[0];
  	String middle = stacks[1].substring(1);
  	String right = stacks[2];
  	return new Layout(left + DELIMITER + middle + DELIMITER + right);
  }

  Layout moveMR()
  {
    if (stacks[1].isEmpty()) throw new IllegalStateException("Middle stack is empty");
    String left = stacks[0];
    String middle = stacks[1].substring(1);
    String right = stacks[1].substring(0,1) + stacks[2];
    return new Layout(left + DELIMITER + middle + DELIMITER + right);
  }

  Layout moveRL()
  {
    if (stacks[2].isEmpty()) throw new IllegalStateException("Right stack is empty");
    String left = stacks[2].substring(0,1) + stacks[0];
    String middle = stacks[1];
    String right = stacks[2].substring(1);
    return new Layout(left + DELIMITER + middle + DELIMITER + right);
  }

  Layout moveRM()
  {
	if (stacks[2].isEmpty()) throw new IllegalStateException("Right stack is empty");
	if (!stacks[1].isEmpty()) throw new IllegalStateException("Middle stack is occupied");
	String left = stacks[0];
	String middle = stacks[2].substring(0,1);
	String right = stacks[2].substring(1);
	return new Layout(left + DELIMITER + middle + DELIMITER + right);
  }

}
