import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddressParserTest {
    Address address;

    public AddressParserTest() {
        address = new Address();
    }

    @Before
    public void parseAddress() {
        String[] splitAddress = {"здание 27", "г.Самара", "Аминева улиц.", "микро район Промышленный", "Ставропольский р-н", "КВ-Л22", "Самарская обл."};
        address = AddressParser.getAddress(splitAddress);
    }

    @Test
    public void testProvince() {
        assertEquals("Самарская обл.", address.getProvince());
    }

    @Test
    public void testRegion() {
        assertEquals("Ставропольский р-н", address.getRegion());
    }

    @Test
    public void testCity() {
        assertEquals("г.Самара", address.getCity());
    }

    @Test
    public void testDistrict() {
        assertEquals("микро район Промышленный", address.getDistrict());
    }

    @Test
    public void testQuarter() {
        assertEquals("КВ-Л22", address.getQuarter());
    }

    @Test
    public void testStreet() {
        assertEquals("Аминева улиц.", address.getStreet());
    }

    @Test
    public void testHouse() {
        assertEquals("здание 27", address.getHouse());
    }
}