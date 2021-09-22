


import feelingsproject.FileModel;
import feelingsproject.FolderModel;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class CosineSimilarityTest {

	
	
	@Test
	public void CosineExecutionTest()
	{
		double mysteryNorm=0.0 ;
		double folderNorm=2.0;
		int dotProduct = 0;
		double vectorDistance = dotProduct /(mysteryNorm * folderNorm);
		assertNotEquals(dotProduct,vectorDistance);
		
	}

}
