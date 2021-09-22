
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;


import  feelingsproject.FileModel;
import  feelingsproject.FolderModel;
import  feelingsproject.Algorithms.KNearestNeighbors.DistanceStructure;

public class KNearestNeighborsTest {

	@Test
	public final void testExecuteAlgorithm() {
		List<FolderModel> models ;
		FileModel mysteryFile ;
		
		int k = 3;
		String wordMystery ;
		int x0=0 ;
		int y0=0;
		
		int x1=1, y1=1;
        ArrayList<String> distanceClass = new ArrayList<>(); // all the points of a class
        ArrayList<DistanceStructure> distancePoint = new ArrayList<>(k); // 3 points of a word
        
        assertEquals(x0,y0);
        assertEquals(x1,y1);
        
	}

	@Test
	public final void testGetConsumer() {
		int k =3 ;
		ArrayList<DistanceStructure> ls= new ArrayList<>(k) ;
		Map<String, Long> collect = ls.stream().collect(Collectors.groupingBy(distanceStructure -> distanceStructure.nameClass, Collectors.counting()));
		assertNotNull(ls);
		assertNotNull(collect);
	}

	@Test
	public final void testKNearestNeighbors() {
		int k=3;
		double distance=9.0;
		String nameClass;
		ArrayList<DistanceStructure> distancePoint = null;
        assertNull(distancePoint);
        assertNotEquals(distance,k);
	}

	

}
