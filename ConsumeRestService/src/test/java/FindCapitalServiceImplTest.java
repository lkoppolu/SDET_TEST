import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FindCapitalServiceImplTest {

    FindCapitalServiceImpl capitalService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        capitalService = new FindCapitalServiceImpl();
    }

    @Test
    public void testFindCapitalByName_IfEmptyCountryName() {
        Assert.assertEquals(capitalService.findCapitalByName("").size(), 0);
    }

    @Test
    public void testFindCapitalByName_IfBlankCountryName() {
        Assert.assertEquals(capitalService.findCapitalByName(" ").size(), 0);
    }

    @Test
    public void testFindCapitalByName_IfNullCountryName() {
        Assert.assertEquals(capitalService.findCapitalByName( null).size(), 0);
    }

    @Test
    public void testFindCapitalByName_IfInvalidCountryName() {
        Assert.assertEquals(capitalService.findCapitalByName( "123").size(), 0);
    }

    @Test
    public void testFindCapitalByCode_IfEmptyCode() {
        Assert.assertEquals(capitalService.findCapitalByCode("").size(), 0);
    }

    @Test
    public void testFindCapitalByCode_IfBlankCode() {
        Assert.assertEquals(capitalService.findCapitalByCode("").size(), 0);
    }

    @Test
    public void testFindCapitalByCode_IfNullCode() {
        Assert.assertEquals(capitalService.findCapitalByCode(null).size(), 0);
    }

    @Test
    public void testFindCapitalByCode_IfInvalidCode() {
        Assert.assertEquals(capitalService.findCapitalByCode("xyz").size(), 0);
    }

    @Test
    public void testFindCapitalByName() {
        Assert.assertEquals(capitalService.findCapitalByName("india").size(), 1);
        Assert.assertEquals(capitalService.findCapitalByName("india").get(0), "New Delhi");
    }

    @Test
    public void testFindCapitalByCode() {
        Assert.assertEquals(capitalService.findCapitalByCode("pak").size(), 1);
        Assert.assertEquals(capitalService.findCapitalByCode("pak").get(0), "Islamabad");
    }

}
