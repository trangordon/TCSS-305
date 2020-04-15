/* Gordon Tran
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import model.Item;
import org.junit.Before;
import org.junit.Test;

/**
 * The Class ItemTest.
 *
 * @author Gordon Tran
 * @version October 2018
 */
public class ItemTest {
    
    /** The item. */
    private Item myItem; 
    
    /** The name. */
    private String myName = "Pencils";    
    
    /** The price. */
    private BigDecimal myPrice = BigDecimal.valueOf(5.00);

    /** The bulk quantity. */
    private int myBulkQ = 3;

    /** The bulk price. */
    private BigDecimal myBulkP = BigDecimal.valueOf(10.00);
    
    
    
    /**
     * Setup.
     */
    @Before
    public void setup() {
        myItem = new Item(myName, myPrice, myBulkQ, myBulkP);
    }

    /**
     * Test method for {@link model.Item#hashCode()}.
     */
    @Test
    public void testHashCode() {
        int hash = 1;
        hash = hash * 4 + myName.hashCode();
        hash = hash * 3 + myPrice.hashCode();
        hash = hash * 11 + myBulkQ;
        hash = hash * 11 + myBulkP.hashCode();
        assertEquals(hash, myItem.hashCode());
    }

    /**
     * Test method for {@link model.Item#Item(java.lang.String, java.math.BigDecimal)}.
     */
    @Test
    public void testItemStringBigDecimal() {
        assertEquals("Pencils", myItem.getName());
        assertEquals(BigDecimal.valueOf(5.00), myItem.getPrice());
    }

    /**
     * Test method for {@link model.Item#Item(java.lang.String, 
     * java.math.BigDecimal, int, java.math.BigDecimal)}.
     */
    @Test
    public void testItemStringBigDecimalIntBigDecimal() {
        assertEquals("Pencils", myItem.getName());
        assertEquals(BigDecimal.valueOf(5.00), myItem.getPrice());
        assertEquals(3, myItem.getBulkQuantity());
        assertEquals(BigDecimal.valueOf(10.00), myItem.getBulkPrice());
    }

    /**
     * Test method for {@link model.Item#getName()}.
     */
    @Test
    public void testGetName() {
        assertEquals("Pencils", myItem.getName());
    }

    /**
     * Test method for {@link model.Item#getPrice()}.
     */
    @Test
    public void testGetPrice() {
        assertEquals(BigDecimal.valueOf(5.00), myItem.getPrice());
    }

    /**
     * Test method for {@link model.Item#getBulkQuantity()}.
     */
    @Test
    public void testGetBulkQuantity() {
        assertEquals(3, myItem.getBulkQuantity());
    }

    /**
     * Test method for {@link model.Item#getBulkPrice()}.
     */
    @Test
    public void testGetBulkPrice() {
        assertEquals(BigDecimal.valueOf(10.00), myItem.getBulkPrice());
    }

    /**
     * Test method for {@link model.Item#isBulk()}.
     */
    @Test
    public void testIsBulk() {
        assertTrue(myItem.isBulk());
    }
    
    /**
     * Test method for {@link model.Item#isBulk()}.
     */
    @Test
    public void testIsBulkFail() {
        final Item subject = new Item("Pencils", BigDecimal.valueOf(5.00));
        assertFalse(subject.isBulk());
    }

    /**
     * Test method for {@link model.Item#toString()}.
     */
    @Test
    public void testToString() {
        final String buffer = myName + ": $" + myPrice 
                        + " (" + myBulkQ + " for $" + myBulkP + ")";
        assertEquals(buffer, myItem.toString());    
    }
    
    /**
     * Test method for {@link model.Item#toString()}.
     */
    @Test
    public void testToStringNoBulk() {
        final String buffer = myName + ": $" + myPrice;
        final Item subject = new Item("Pencils", BigDecimal.valueOf(5.00));
        assertEquals(buffer, subject.toString());    
    }

    /**
     * Test method for {@link model.Item#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObject() {
        final Item subject = new Item("Pencils", BigDecimal.valueOf(5.00),
                                      3, BigDecimal.valueOf(10.00));
        assertTrue(myItem.equals(subject));
    }
    /**
     * Test method for {@link model.Item#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectName() {
        final Item subject = new Item("Pencil", BigDecimal.valueOf(5.00),
                                      3, BigDecimal.valueOf(10.00));
        assertFalse(myItem.equals(subject));
    }
    /**
     * Test method for {@link model.Item#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectPrice() {
        final Item subject = new Item("Pencils", BigDecimal.valueOf(5.01),
                                      3, BigDecimal.valueOf(10.00));
        assertFalse(myItem.equals(subject));
    }
    /**
     * Test method for {@link model.Item#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectBQuantity() {
        final Item subject = new Item("Pencils", BigDecimal.valueOf(5.00),
                                      30, BigDecimal.valueOf(10.00));
        assertFalse(myItem.equals(subject));
    }
    /**
     * Test method for {@link model.Item#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectBPrice() {
        final Item subject = new Item("Pencils", BigDecimal.valueOf(5.00),
                                      3, BigDecimal.valueOf(10.10));
        assertFalse(myItem.equals(subject));
    }

    /**
     * Test method for {@link model.Item#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectSame() {
        assertTrue(myItem.equals(myItem));
    }
    /**
     * Test method for {@link model.Item#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectNull() {
        assertFalse(myItem.equals(null));
    }
}
