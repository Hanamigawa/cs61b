
import org.junit.Test;

import static org.junit.Assert.*;


public class FlikTest {

    @Test
    public void testIsSameNumber(){
        for(int i=0, j=0; i< 500; i++, j++){
            if(i==128){
                continue;
            }
            assertTrue("Broke at "+i, Flik.isSameNumber(i,j));
        }
    }

}
