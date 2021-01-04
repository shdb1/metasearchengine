/**
 * 
 */
package com.metasearchengine.ir60.model;

import java.util.Comparator;

/**
 * @author moshadab
 *
 */
public class NewsResultsScoreComparator implements Comparator<NewsResults> {

	@Override
	public int compare(NewsResults o1, NewsResults o2) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		if (o1.getScore() < o2.getScore())
			return 1;
		else if (o1.getScore() > o2.getScore())
			return -1;
		else
			return 0;
	
	}

}
