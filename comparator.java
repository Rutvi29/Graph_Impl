package undirgraph;

import java.util.Comparator;

public class comparator<E extends Comparable<E>> implements Comparator<E>
{
/**
 * Compares two elements.
 * 
 * @param  f1  The first element you want to compare.
 * @param  f2  The second element you want to compare.
 * @return  -1,0,1  Whether or not one is greater than, less than,
 * or equal to one another.
 */
	public int compare(E element1,E element2)
	{	
		return element1.compareTo(element2);
	}


}
