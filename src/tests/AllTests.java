package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ClientTest.class, DirectedEdgeTest.class, EdgeWeightedDigraphTest.class, GenTest.class,
		GeoCodingTest.class, LinearSearchTest.class, LocationTest.class, Test_HeapSortCategory.class })
public class AllTests {

}
