package snapcheck;
import java.util.Collections;
import java.util.Comparator;

import org.bson.Document;
import org.junit.*;

public class SnapCheckTest {
	SnapCheck obj = new SnapCheck();
	/*
	 * Unittest to test first sort function
	 */
	@Test
	public void testSortOne() {
		Document[] sortone = obj.SortOne(5);
		Document[] test = sortone;
		Collections.sort(test, (test1,test2) -> {
			
		});
	}
}
