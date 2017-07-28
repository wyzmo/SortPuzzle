package msw;

import java.util.Arrays;
import java.util.List;

import msw.sort.Layout;
import msw.sort.Permutation;
import msw.sort.PermutationInt;
import msw.sort.Sorter;


/**
 * %JAVA_HOME%\bin\java -Xmx4g -classpath target\acmsort-1.0-SNAPSHOT.jar msw.Main "abcd||"
 *
 * @author Mark
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {

      for(String arg : args)
      {
        Main.testPermutation(arg, 239500801); // abcdefghij|| 12! / 2! + 1
      }
      if (true) return;
      Permutation p = new Permutation(args[0]);
      System.out.println();
      System.out.println("Sorting: " + args[0] + " Goal: " + p.first());
      Sorter s = new Sorter(args[0]);
      for(Layout l : s.sort())
      {
		System.out.println(l);
	  }

    }

    static void testPermutation(String s, int stop)
    {
      Permutation p = new Permutation(s);
      int success = 0;
      int failed = 0;
      int max = 0;
      for(int i = 0; i < stop; i++, p.next())
      {
		if ((i > 0) && p.initial().equals(p.current())) break;
        //System.out.println(i + ". \t" + p.current() + " \t" + new Layout(p.current()));
        //System.out.println("Goal: " + p.first() + " Sorting: " + p.current());
        Sorter sorter = new Sorter(p.current());
        List<Layout> layouts = sorter.sort();
        int moves = layouts.size() - 1;
        if (moves > max) max = moves;
        Layout last = layouts.get(moves);
        boolean sorted = last.permutation().equals(p.first());
        System.out.println("Goal: " + p.first() + " Sorting: " + p.current() + " Success: " + sorted + " Moves: " + moves);
		//for(Layout l : sorter.sort())
		//{
		//  last = l;
		//  //System.out.println(l);
	    //}
	    if (sorted) success++; else failed++;
	    //System.out.println("----------------------------------------------------------------------------");
      }
      System.out.println("Success: " + success + " Failed: " + failed + " Max: " + max);
    }


    static void testPermutationInt(int[] ia, int stop)
    {
      PermutationInt p = new PermutationInt(ia);
      for(int i = 0; i < stop; i++, p.next())
      {
        System.out.println(i + ". " + Arrays.toString(p.current()));
      }
    }

}
