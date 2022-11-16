import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class FileProcessTest {


    @Test
    public  void checkVal(){
        Map<String,Double> res1 = new HashMap<String,Double>();
        res1.put("London",320011.3555854);
        Assert.assertEquals((double)res1.get("London"),320011.3555854,0);
    }

    @Test
    public void checkNull(){
        Map<String,Double> res1 = new HashMap<String,Double>();
        res1.put("London",320011.3555854);
        Assert.assertNotNull(res1);
    }
}
