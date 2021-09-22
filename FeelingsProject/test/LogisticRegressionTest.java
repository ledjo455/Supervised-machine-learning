

import feelingsproject.FileModel;
import feelingsproject.FolderModel;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import feelingsproject.Algorithms.CosineSimilarity;

import java.util.List;

import feelingsproject.Algorithms.LogisticRegression.Instance;

public class LogisticRegressionTest {

	@Test
	public final void testinstances() {
		List<Instance> instances=null ;
		int[] x= null ;
		assertEquals(instances,x);
	}
	
	@Test
	public final void classifyTest()
	{
		double logit = .0;
		int[] x = null ;
		assertNotEquals(logit,x);
	}
	
	@Test
	public final void executionTest()
	{
		List<FolderModel> models=null;
		FileModel mysteryFile=null;
		assertEquals(models,mysteryFile);
		
	}
	

}
