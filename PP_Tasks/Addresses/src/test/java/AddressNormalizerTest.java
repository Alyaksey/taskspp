import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddressNormalizerTest {
    Address address;

    @Before
    public void normalize(){
        address = new Address("Самарская обл.","Ставропольский р-н","ПСК Ягодное","Промышленный р-н","КВ-Л24","Садовая улиц.","здание 30А");
        AddressNormalizer.normalizeAddress(address);
    }
    @Test
    public void testCity() {
        assertEquals("с.Ягодное",address.getCity());
    }
    @Test
    public void testDistrict() {
        assertEquals("Промышленный микрор-н",address.getDistrict());
    }
    @Test
    public void testQuarter() {
        assertEquals("Кв.24", address.getQuarter());
    }
    @Test
    public void testStreet() {
        assertEquals("Ул.Садовая",address.getStreet());
    }
    @Test
    public void testHouse() {
        assertEquals("Д.30А", address.getHouse());
    }
}