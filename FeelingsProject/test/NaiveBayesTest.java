

import feelingsproject.FileModel;
import feelingsproject.FolderModel;
import feelingsproject.MyHashTable;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;


public class NaiveBayesTest {

	@Test
	public final void test() {
		
	
		List<FolderModel> models = null;
		FileModel mysteryFile = null;
		double totalTexts = models.stream()
                                          .mapToInt(FolderModel::getAmountSubTexts)
                                          .sum(); // total txts in all folders



        feelingsproject.MyHashTable mysteryHistogram = mysteryFile.getHistogram();
        assertEquals(models,mysteryFile);
        assertNotEquals(totalTexts,mysteryFile);
	}
	
	@Test
	public final void SumofElementsTest()
	{
		FolderModel mysteryFile = null;
		assertNull(mysteryFile);
	}
	
	

}
