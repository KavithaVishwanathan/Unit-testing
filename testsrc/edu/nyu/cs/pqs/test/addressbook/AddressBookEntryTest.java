package edu.nyu.cs.pqs.test.addressbook;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import edu.nyu.cs.pqs.AddressBook.AddressBookEntry;
/*
 * Every test method in this class tests for three type of entries(normal, null and empty entry)
 */
public class AddressBookEntryTest {

  private AddressBookEntry nullEntry;
  private AddressBookEntry emptyEntry;
  private AddressBookEntry normalEntry;
  
  @Before
  public void setup() {
    nullEntry = new AddressBookEntry();
    
    emptyEntry = new AddressBookEntry();
    emptyEntry.setName("");
    emptyEntry.setPostalAddress("");
    emptyEntry.setPhoneNumber(0);
    emptyEntry.setEmail("");
    emptyEntry.setNote("");
    
    normalEntry = new AddressBookEntry();
    normalEntry.setName("John");
    normalEntry.setPostalAddress("NYC");
    normalEntry.setPhoneNumber(9850051102L);
    normalEntry.setEmail("john@gmail.com");
    normalEntry.setNote("My Friend");
  }
  
  @Test
  public void testSetName() {
    normalEntry.setName("Helen");
    assertEquals("Helen", normalEntry.getName());
  }

  @Test
  public void testSetPostalAddress() {
    normalEntry.setPostalAddress("NYC");
    assertEquals("NYC", normalEntry.getPostalAddress());
  }

  @Test
  public void testSetPhoneNumber() {
    normalEntry.setPhoneNumber(646446666L);
    assertEquals(646446666L, normalEntry.getPhoneNumber());
  }

  @Test
  public void testSetEmail() {
    normalEntry.setEmail("nyu@nyu.edu");
    assertEquals("nyu@nyu.edu", normalEntry.getEmail());
  }

  @Test
  public void testSetNote() {
    normalEntry.setNote("Hello");
    assertEquals("Hello", normalEntry.getNote());
  }

  @Test 
  public void testGetName_nullEntry() {
    assertNull(nullEntry.getName());
  }
  
  @Test 
  public void testGetName_emptyEntry() {
    assertEquals("",emptyEntry.getName());
  }
  
  @Test 
  public void testGetName_normalEntry() {
    assertEquals("John", normalEntry.getName());  
  }

  @Test
  public void testGetPostalAddress_nullEntry() {
    assertNull(nullEntry.getPostalAddress());
  }
  
  @Test
  public void testGetPostalAddress_emptyEntry() {
    assertEquals("",emptyEntry.getPostalAddress());
  }
  
  @Test
  public void testGetPostalAddress_normalEntry() {
    assertEquals("NYC", normalEntry.getPostalAddress());
  }

  @Test
  public void testGetPhoneNumber_nullEntry() {
    assertEquals(0, nullEntry.getPhoneNumber());
  }
  
  @Test
  public void testGetPhoneNumber_emptyEntry() {
    assertEquals(0,emptyEntry.getPhoneNumber());
  }
  
  @Test
  public void testGetPhoneNumber_normalEntry() {
    assertEquals(9850051102L, normalEntry.getPhoneNumber());
  }
  
  @Test
  public void testGetEmail_nullEntry() {
    assertNull(nullEntry.getEmail());
  }
  
  @Test
  public void testGetEmail_emptyEntry() {
    assertEquals("",emptyEntry.getEmail());
  }
  
  @Test
  public void testGetEmail_normalEntry() {
    assertEquals("john@gmail.com", normalEntry.getEmail());
  }

  @Test
  public void testGetNote_nullEntry() {
    assertNull(nullEntry.getNote());
  }
  
  @Test
  public void testGetNote_emptyEntry() {
    assertEquals("",emptyEntry.getNote());
  }
  
  @Test
  public void testGetNote_normalEntry() {
    assertEquals("My Friend", normalEntry.getNote());
  }
  /*
   * For null entry the toString should display empty string instead of "null,null,0,null,null"
   */
  @Test
  public void testToString_nullEntry() {
    assertEquals("", nullEntry.toString());
  }
  
  @Test
  public void testToString_emptyEntry() {
    assertEquals(",,0,,", emptyEntry  .toString());
  }
  
  @Test
  public void testToString_normalEntry() {
    assertEquals("John,NYC,9850051102,john@gmail.com,My Friend", normalEntry.toString());
  }
  
  @Test
  public void testEquals_sameEntry() {
    assertEquals(normalEntry, normalEntry);
  }
  
  /*
   * To avoid the code break in future, equals should follow a contract, one of which 
   * is when the object is compared with null, the method should return false not Exception
   */
  @Test
  public void testEquals_normalEntryWithNull() {
    assertFalse(normalEntry.equals(null));
  }
  
  /*
   * This method should return true, instead it throws exception
   */
  @Test
  public void testEquals_twoNullEntries() {
    assertEquals(nullEntry, nullEntry);
  }

  @Test
  public void testEquals_twoEmptyEntries() {
    assertEquals(emptyEntry, emptyEntry);
  }
  
  /*
   * Inappropriate exception, instead the method should have returned false
   */
  @Test
  public void testEquals_entryWithEmptyString() {
    assertFalse(normalEntry.equals(""));
  }
  
  @Test
  public void testEquals_differentEntriesWithSameProperty() {
    AddressBookEntry anotherEntry = new AddressBookEntry();
    anotherEntry.setName("John");
    anotherEntry.setPostalAddress("NYC");
    anotherEntry.setPhoneNumber(9850051102L);
    anotherEntry.setEmail("john@gmail.com");
    anotherEntry.setNote("My Friend");
    
    assertEquals(normalEntry, anotherEntry);
  }
  
  @Test
  public void testEquals_differentEntriesWithNotSameProperties() {
    AddressBookEntry anotherEntry = new AddressBookEntry();
    anotherEntry.setName("John");
    anotherEntry.setPostalAddress("NYC");
    
    assertFalse(normalEntry.equals(anotherEntry));
  }

}
