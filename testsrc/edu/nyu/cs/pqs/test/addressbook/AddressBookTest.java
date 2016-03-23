package edu.nyu.cs.pqs.test.addressbook;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import junitx.framework.FileAssert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import edu.nyu.cs.pqs.AddressBook.*;

public class AddressBookTest {
  private AddressBook nullAddressBook;
  private AddressBook emptyEntryAddressBook;
  private AddressBook normalEntryAddressBook;
  private AddressBook duplicateEntriesAddressBook;
  private AddressBook wrongEntryAddressBook;
  private AddressBookEntry nullEntry;
  private AddressBookEntry emptyEntry;
  private AddressBookEntry normalEntry;
  private AddressBookEntry entryWithComma;
   
  @Rule
  public TemporaryFolder folder= new TemporaryFolder();
  
  @Before
  public void setUp() {
    nullAddressBook = new AddressBook();
    
    emptyEntryAddressBook = new AddressBook();
    emptyEntryAddressBook.addAnEntry("", "", 0, "", "");
    
    normalEntryAddressBook = new AddressBook();
    normalEntryAddressBook.addAnEntry("John", "NYC", 9850051102L, "john@gmail.com", "My Friend");
    
    wrongEntryAddressBook = new AddressBook();
    wrongEntryAddressBook.addAnEntry("Steve", "NYC", 0, "", "");
    
    duplicateEntriesAddressBook = new AddressBook();
    duplicateEntriesAddressBook.addAnEntry("John","NYC", 9850051102L,"john@gmail.com","My Friend");
    duplicateEntriesAddressBook.addAnEntry("John","NYC", 9850051102L,"john@gmail.com","My Friend");
    
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
    
    entryWithComma = new AddressBookEntry();
    entryWithComma.setName("");
    entryWithComma.setPostalAddress("NYC,NY");
    entryWithComma.setPhoneNumber(0);
    entryWithComma.setEmail("");
    entryWithComma.setNote("");
  }

  @Test
  public void testAddAnEntry_nullAddressBook() {
    assertTrue(nullAddressBook.equals(null));
    assertNotNull(normalEntryAddressBook);     
    assertNotNull(duplicateEntriesAddressBook);
  }

  @Test
  public void testSaveAddressListToFile_nullAddressBook() throws IOException {
    //Expected File
    File expectedfile= folder.newFile("expectedFile.txt"); 
    
    //Actual File
    nullAddressBook.saveAddressListToFile("mycontact.txt");
    File actualFile = new File("mycontact.txt");
    
    //Comparison
    FileAssert.assertEquals(expectedfile, actualFile);
  }
  
  @Test
  public void testSaveAddressListToFile_emptyEntryAddressBook() throws IOException {
    //Expected File
    File expectedfile= folder.newFile("expectedFile.txt");
    FileWriter fw = new FileWriter(expectedfile.getPath());
    BufferedWriter expfile =null;
    expfile = new BufferedWriter(fw);
    expfile.append(",,0,,");
    expfile.close();
    
    //Actual File
    emptyEntryAddressBook.saveAddressListToFile("mycontact.txt");
    File actualFile = new File("mycontact.txt");
    
    //Comparison
    FileAssert.assertEquals(expectedfile, actualFile);
  }
  
  @Test
  public void testSaveAddressListToFile_normalEntryAddressBook() throws IOException {
    //Expected File
    File expectedfile= folder.newFile("expectedFile.txt"); 
    FileWriter fw = new FileWriter(expectedfile.getPath());
    BufferedWriter expfile =null;
    expfile = new BufferedWriter(fw);
    expfile.append("John,NYC,9850051102,john@gmail.com,My Friend");
    expfile.close();
    
    //Actual File
    normalEntryAddressBook.saveAddressListToFile("mycontact.txt");
    File actualFile = new File("mycontact.txt");
    
    //Comparison
    FileAssert.assertEquals(expectedfile, actualFile);
  }
  
  @Test
  public void testSaveAddressListToFile_duplicateEntryAddressBook() throws IOException {
    //Expected File
    File expectedfile= folder.newFile("expectedFile.txt"); 
    FileWriter fw = new FileWriter(expectedfile.getPath());
    BufferedWriter expfile =null;
    expfile = new BufferedWriter(fw);
    expfile.append("John,NYC,9850051102,john@gmail.com,My Friend");
    expfile.newLine();
    expfile.append("John,NYC,9850051102,john@gmail.com,My Friend");
    expfile.close();
    
    //Actual File
    duplicateEntriesAddressBook.saveAddressListToFile("mycontact.txt");
    File actualFile = new File("mycontact.txt");
    
    //Comparison
    FileAssert.assertEquals(expectedfile, actualFile);
  }
  
  @Test
  public void testSaveAddressListToFile_commaValueInEntry() throws IOException {
    normalEntryAddressBook.addAnEntry("", "NYC,NY", 0, "", "");
    
    //Expected File
    File expectedfile= folder.newFile("expectedFile.txt"); 
    FileWriter fw = new FileWriter(expectedfile.getPath());
    BufferedWriter expfile =null;
    expfile = new BufferedWriter(fw);
    expfile.append("John,NYC,9850051102,john@gmail.com,My Friend");
    expfile.newLine();
    expfile.append(",NYC,NY,0,,");
    expfile.close();
    
    //Actual File
    normalEntryAddressBook.saveAddressListToFile("mycontact.txt");
    File actualFile = new File("mycontact.txt");
    
    //Comparison
    FileAssert.assertEquals(expectedfile, actualFile);
  }
  
  @Test
  public void testReadAddressesFromFile_nullAddressBook() throws IOException {
    //Expected ArrayList
    ArrayList<AddressBookEntry> expectedList = new ArrayList<AddressBookEntry>();
    
    //Actual ArrayList
    List<AddressBookEntry> actualList = new ArrayList<AddressBookEntry>(); 
    nullAddressBook.saveAddressListToFile("mycontact.txt");
    actualList = nullAddressBook.readAddressesFromFile("mycontact.txt");
    
    //Comparison
    assertEquals(expectedList, actualList);  
  }
  
  @Test
  public void testReadAddressesFromFile_emptyEntryAddressBook() throws IOException {
    //Expected ArrayList
    ArrayList<AddressBookEntry> expectedList = new ArrayList<AddressBookEntry>();
    
    //Actual ArrayList
    List<AddressBookEntry> actualList = new ArrayList<AddressBookEntry>(); 
    emptyEntryAddressBook.saveAddressListToFile("mycontact.txt");
    actualList = emptyEntryAddressBook.readAddressesFromFile("mycontact.txt");
    
    //Comparison
    assertEquals(expectedList, actualList);  
  }
  
  @Test
  public void testReadAddressesFromFile_normalEntryAddressBook() throws IOException {
    //Expected ArrayList
    ArrayList<AddressBookEntry> expectedList = new ArrayList<AddressBookEntry>();
    expectedList.add(normalEntry);
    
    //Actual ArrayList
    List<AddressBookEntry> actualList = new ArrayList<AddressBookEntry>(); 
    normalEntryAddressBook.saveAddressListToFile("mycontact.txt");
    actualList = normalEntryAddressBook.readAddressesFromFile("mycontact.txt");
    
    //Comparison
    assertEquals(expectedList, actualList);  
  }
  
  @Test
  public void testReadAddressesFromFile_duplicateEntriesAddressBook() throws IOException {
    //Expected ArrayList
    ArrayList<AddressBookEntry> expectedList = new ArrayList<AddressBookEntry>();
    expectedList.add(normalEntry);
    expectedList.add(normalEntry);
    
    //Actual ArrayList
    List<AddressBookEntry> actualList = new ArrayList<AddressBookEntry>(); 
    duplicateEntriesAddressBook.saveAddressListToFile("mycontact.txt");
    actualList = duplicateEntriesAddressBook.readAddressesFromFile("mycontact.txt");
    
    //Comparison
    assertEquals(expectedList, actualList);  
  }
  
  /*
   * This test case fails to read properly when a entry has a string with comma
   */
  @Test
  public void testReadAddressesFromFile_entryWithCommaValue() throws IOException {
    //Expected ArrayList
    ArrayList<AddressBookEntry> expectedList = new ArrayList<AddressBookEntry>();
    expectedList.add(entryWithComma);
    
    //Actual ArrayList
    normalEntryAddressBook.addAnEntry("", "NYC,NY", 0, "", "");
    List<AddressBookEntry> actualList = new ArrayList<AddressBookEntry>(); 
    normalEntryAddressBook.saveAddressListToFile("mycontact.txt");
    actualList = normalEntryAddressBook.readAddressesFromFile("mycontact.txt");
    
    //Comparison
    assertEquals(expectedList, actualList);  
  }
  
  @Test(expected=IOException.class)  //DOUBTTT - Enoughh?
  public void testReadAddressesFromFile_wrongFilename() throws IOException {
    normalEntryAddressBook.saveAddressListToFile("mycontact.txt");
    normalEntryAddressBook.readAddressesFromFile("wrongFile.txt");
  }
  
  /*
   * Expected to handle null exception for filename
   */
  @Test(expected=IOException.class)  //DOUBTTT - Exception right?
  public void testReadAddressesFromFile_nullFilename() throws IOException {
    normalEntryAddressBook.saveAddressListToFile("mycontact.txt");
    normalEntryAddressBook.readAddressesFromFile(null);
  }

  @Test
  public void testRemoveAddressBookEntry_nullAddressBook_nullEntry() throws IOException {
    AddressBookEntry nullEntry = new AddressBookEntry();
    boolean entryFound;
    
    nullAddressBook.saveAddressListToFile("mycontact.txt");
    entryFound = nullAddressBook.removeAddressBookEntry("mycontact.txt", nullEntry);
    assertEquals(false, entryFound); 
  }
  
  @Test
  public void testRemoveAddressBookEntry_nullAddressBook_emptyEntry() throws IOException {
    boolean entryFound; 
    nullAddressBook.saveAddressListToFile("mycontact.txt");
    entryFound = nullAddressBook.removeAddressBookEntry("mycontact.txt", emptyEntry);
    assertEquals(false, entryFound); 
  }
  
  @Test
  public void testRemoveAddressBookEntry_nullAddressBook_normalEntry() throws IOException {
    AddressBookEntry normalEntry = new AddressBookEntry();
    boolean entryFound;
    
    normalEntry.setName("Mary");
    normalEntry.setPostalAddress("LA");
    normalEntry.setPhoneNumber(9850051102L);
    normalEntry.setEmail("mary@gmail.com");
    normalEntry.setNote("Aunt"); 

    nullAddressBook.saveAddressListToFile("mycontact.txt");
    entryFound = nullAddressBook.removeAddressBookEntry("mycontact.txt", normalEntry);
    assertEquals(false, entryFound);
  }
  
  @Test
  public void testRemoveAddressBookEntry_emptyEntryAddressBook_nullEntry() throws IOException {
    boolean entryFound;
    emptyEntryAddressBook.saveAddressListToFile("mycontact.txt");
    entryFound = emptyEntryAddressBook.removeAddressBookEntry("mycontact.txt", nullEntry);
    assertFalse(entryFound); 
  }
  
  /*
   * This test case fails because the remove functionality should remove the empty entry ",,0,,",
   * since the save functionality is able to save the empty entry
   */
  @Test
  public void testRemoveAddressBookEntry_emptyEntryAddressBook_emptyEntry() throws IOException {
    boolean entryFound;
    emptyEntryAddressBook.saveAddressListToFile("emptycontact.txt");
    entryFound = emptyEntryAddressBook.removeAddressBookEntry("emptycontact.txt", emptyEntry);
    assertTrue(entryFound); 
  }
  
  @Test
  public void testRemoveAddressBookEntry_emptyEntryAddressBook_normalEntry() throws IOException {
    boolean entryFound;
    emptyEntryAddressBook.saveAddressListToFile("mycontact.txt");
    entryFound = emptyEntryAddressBook.removeAddressBookEntry("mycontact.txt", normalEntry);
    assertFalse(entryFound);
  }
  
  @Test
  public void testRemoveAddressBookEntry_normalEntryAddressBook_nullEntry() throws IOException {
    boolean entryFound; 
    normalEntryAddressBook.saveAddressListToFile("mycontact.txt");
    entryFound = normalEntryAddressBook.removeAddressBookEntry("mycontact.txt", nullEntry);
    assertFalse(entryFound);
  }
  
  @Test
  public void testRemoveAddressBookEntry_normalEntryAddressBook_emptyEntry() throws IOException {
    boolean entryFound; 
    normalEntryAddressBook.saveAddressListToFile("mycontact.txt");
    entryFound = normalEntryAddressBook.removeAddressBookEntry("mycontact.txt", emptyEntry);
    assertFalse(entryFound);
  }
  
  @Test
  public void testRemoveAddressBookEntry_normalEntryAddressBook_normalEntry() throws IOException {
    ArrayList<AddressBookEntry> expectedList = new ArrayList<AddressBookEntry>();
    List<AddressBookEntry> actualList;
    boolean entryFound;
    
    normalEntryAddressBook.saveAddressListToFile("mycontact.txt");
    entryFound = normalEntryAddressBook.removeAddressBookEntry("mycontact.txt", normalEntry);
    assertTrue(entryFound);
    
    //Also checks if the entry has been removed (apart from the boolean check)
    normalEntryAddressBook.saveAddressListToFile("mycontact.txt");
    actualList = normalEntryAddressBook.searchAddressBook("mycontact.txt", "John",
        EntryAttribute.NAME);
    assertEquals(expectedList,actualList); 
  }
  
  @Test
  public void testRemoveAddressBookEntry_duplicateEntriesAddressBook_nullEntry() 
      throws IOException {
    boolean entryFound; 
    duplicateEntriesAddressBook.saveAddressListToFile("mycontact.txt");
    entryFound = duplicateEntriesAddressBook.removeAddressBookEntry("mycontact.txt", nullEntry);
    assertFalse(entryFound);
  }
  
  @Test
  public void testRemoveAddressBookEntry_duplicateEntriesAddressBook_emptyEntry() 
      throws IOException {
    boolean entryFound; 
    duplicateEntriesAddressBook.saveAddressListToFile("mycontact.txt");
    entryFound = duplicateEntriesAddressBook.removeAddressBookEntry("mycontact.txt", emptyEntry);
    assertFalse(entryFound);
  }
  
  /*
   * The method "removeAddressBookEntry" should remove all the similar entries. Instead it removes
   * only the first instance of the similar entry
   */
  @Test
  public void testRemoveAddressBookEntry_duplicateEntriesAddressBook_normalEntry() 
      throws IOException {
    ArrayList<AddressBookEntry> expectedList = new ArrayList<AddressBookEntry>();
    List<AddressBookEntry> actualList;
    boolean entryFound;
    
    duplicateEntriesAddressBook.saveAddressListToFile("mycontact.txt");
    entryFound = duplicateEntriesAddressBook.removeAddressBookEntry("mycontact.txt", normalEntry);
    assertTrue(entryFound);
    
    //Also checks if the entry has been removed (apart from the boolean check)
    duplicateEntriesAddressBook.saveAddressListToFile("mycontact.txt");
    actualList = duplicateEntriesAddressBook.searchAddressBook("mycontact.txt", "John",
        EntryAttribute.NAME);
    assertEquals(expectedList,actualList); 
  }
  
  @Test
  public void testRemoveAddressBookEntry_wrongCase() throws IOException {
    boolean entryFound;   
    AddressBookEntry wrongEntry = new AddressBookEntry();
    wrongEntry.setName("Helen"); 
    
    normalEntryAddressBook.saveAddressListToFile("mycontact.txt");
    entryFound = normalEntryAddressBook.removeAddressBookEntry("mycontact.txt", wrongEntry);
    assertFalse(entryFound);
     
  }
  
  /*
   * This test failed because of the reason that the "RemoveAddressBookEntry" function is 
   * not case-insensitive
   */
  @Test
  public void testRemoveAddressBookEntry_CaseInSensitive() throws IOException {
    boolean entryFound;
    AddressBookEntry entryWithWrongCase = new AddressBookEntry();
    entryWithWrongCase.setName("john"); 
  
    //Normal addressBook
    normalEntryAddressBook.saveAddressListToFile("mycontact.txt");
    entryFound = normalEntryAddressBook.removeAddressBookEntry("mycontact.txt", entryWithWrongCase);
    assertTrue(entryFound);
  }
  
  /*
   * The method "RemoveAddressbookEntry" is not handling the ioexception (so gives wrong 
   * answer when wrong filename is given)
   */
  @Test
  (expected=IOException.class)
  public void testRemoveAddressBookEntry_FileNotExists() throws IOException {
    normalEntryAddressBook.saveAddressListToFile("mycontact.txt");
    normalEntryAddressBook.removeAddressBookEntry("fileNotThere.txt", normalEntry);
  }
  
  @Test
  (expected=IOException.class)
  public void testRemoveAddressBookEntry_NullFileName() throws IOException {
    normalEntryAddressBook.saveAddressListToFile("mycontact.txt");
    normalEntryAddressBook.removeAddressBookEntry(null, normalEntry);
  }
  
  @Test
  public void testSearchAddressBook_nullAddressBook_nullAsSearchText() throws IOException {
    List<AddressBookEntry> expectedList = new ArrayList<AddressBookEntry>();
    List<AddressBookEntry> actualList;
    nullAddressBook.saveAddressListToFile("mycontact.txt");
    actualList = nullAddressBook.searchAddressBook("mycontact.txt", null, null);
    assertEquals(expectedList, actualList);  
  }
  
  @Test
  public void testSearchAddressBook_nullAddressBook_emptySearchText_nameEnum() throws IOException {
    List<AddressBookEntry> expectedList = new ArrayList<AddressBookEntry>();
    List<AddressBookEntry> actualList;
    nullAddressBook.saveAddressListToFile("mycontact.txt");
    actualList = nullAddressBook.searchAddressBook("mycontact.txt","", EntryAttribute.NAME);
    assertEquals(expectedList, actualList);  
  }
  
  @Test
  public void testSearchAddressBook_nullAddressBook_emptySearchText_addressEnum() throws IOException {
    List<AddressBookEntry> expectedList = new ArrayList<AddressBookEntry>();
    List<AddressBookEntry> actualList;
    nullAddressBook.saveAddressListToFile("mycontact.txt");
    actualList = nullAddressBook.searchAddressBook("mycontact.txt","", EntryAttribute.ADDRESS);
    assertEquals(expectedList, actualList);  
  }
  
  @Test
  public void testSearchAddressBook_nullAddressBook_emptySearchText_phoneEnum() throws IOException {
    List<AddressBookEntry> expectedList = new ArrayList<AddressBookEntry>();
    List<AddressBookEntry> actualList;
    nullAddressBook.saveAddressListToFile("mycontact.txt");
    actualList = nullAddressBook.searchAddressBook("mycontact.txt","", EntryAttribute.PHONE);
    assertEquals(expectedList, actualList);  
  }
  
  @Test
  public void testSearchAddressBook_nullAddressBook_emptySearchText_emailEnum() throws IOException {
    List<AddressBookEntry> expectedList = new ArrayList<AddressBookEntry>();
    List<AddressBookEntry> actualList;
    nullAddressBook.saveAddressListToFile("mycontact.txt");
    actualList = nullAddressBook.searchAddressBook("mycontact.txt","", EntryAttribute.EMAIL);
    assertEquals(expectedList, actualList);  
  }
  
  @Test
  public void testSearchAddressBook_nullAddressBook_emptySearchText_noteEnum() throws IOException {
    List<AddressBookEntry> expectedList = new ArrayList<AddressBookEntry>();
    List<AddressBookEntry> actualList;
    nullAddressBook.saveAddressListToFile("mycontact.txt");
    actualList = nullAddressBook.searchAddressBook("mycontact.txt","", EntryAttribute.NOTE);
    assertEquals(expectedList, actualList);  
  }
  
  @Test
  public void testSearchAddressBook_nullAddressBook_normalSearchText() throws IOException {
    List<AddressBookEntry> expectedList = new ArrayList<AddressBookEntry>();
    List<AddressBookEntry> actualList;
    nullAddressBook.saveAddressListToFile("mycontact.txt");
    actualList = nullAddressBook.searchAddressBook("mycontact.txt","John", EntryAttribute.NAME);
    assertEquals(expectedList, actualList);  
  }
 
  @Test
  public void testSearchAddressBook_emptyEntryAddressBook_nullSearchText() throws IOException {
    List<AddressBookEntry> expectedList = new ArrayList<AddressBookEntry>();
    List<AddressBookEntry> actualList;
    emptyEntryAddressBook.saveAddressListToFile("mycontact.txt");
    actualList = emptyEntryAddressBook.searchAddressBook("mycontact.txt", null, null);
    assertEquals(expectedList, actualList);  
  }
  
  /*
   * This test case fails because the remove functionality should remove the empty entry ",,0,,",
   * since the save functionality is able to save the empty entry
   */
  @Test
  public void testSearchAddressBook_emptyEntryAddressBook_emptySearchText() throws IOException {
    List<AddressBookEntry> expectedList = new ArrayList<AddressBookEntry>();
    expectedList.add(emptyEntry);
    List<AddressBookEntry> actualList;
    emptyEntryAddressBook.saveAddressListToFile("mycontact.txt");
    actualList = emptyEntryAddressBook.searchAddressBook("mycontact.txt","", EntryAttribute.NAME);
    assertEquals(expectedList, actualList);  
  }
   
  @Test
  public void testSearchAddressBook_emptyEntryAddressBook_normalSearchText() throws IOException {
    List<AddressBookEntry> expectedList = new ArrayList<AddressBookEntry>();
    List<AddressBookEntry> actualList;
    nullAddressBook.saveAddressListToFile("mycontact.txt");
    actualList = nullAddressBook.searchAddressBook("mycontact.txt","John", EntryAttribute.NAME);
    assertEquals(expectedList, actualList);  
  }
  
  @Test
  public void testSearchAddressBook_normalEntryAddressBook_nullAsSearchText() throws IOException {
    List<AddressBookEntry> expectedList = new ArrayList<AddressBookEntry>();
    List<AddressBookEntry> actualList;
    normalEntryAddressBook.saveAddressListToFile("mycontact.txt");
    actualList = normalEntryAddressBook.searchAddressBook("mycontact.txt", null, null);
    assertEquals(expectedList, actualList);  
  }
  
  @Test
  public void testSearchAddressBook_normalEntryAddressBook_emptySearchText() throws IOException {
    List<AddressBookEntry> expectedList = new ArrayList<AddressBookEntry>();
    List<AddressBookEntry> actualList;
    normalEntryAddressBook.saveAddressListToFile("mycontact.txt");
    actualList = normalEntryAddressBook.searchAddressBook("mycontact.txt","", EntryAttribute.NAME);
    assertEquals(expectedList, actualList);  
  }
  
  @Test
  public void testSearchAddressBook_normalEntryAddressBook_normalSearchText_nameEnum() 
      throws IOException {
    List<AddressBookEntry> expectedList = new ArrayList<AddressBookEntry>();
    expectedList.add(normalEntry);
    
    List<AddressBookEntry> actualList;
    normalEntryAddressBook.saveAddressListToFile("mycontact.txt");
    actualList = normalEntryAddressBook.searchAddressBook("mycontact.txt","John", 
        EntryAttribute.NAME);
    
    assertEquals(expectedList, actualList);  
  }
  
  @Test
  public void testSearchAddressBook_normalEntryAddressBook_normalSearchText_addressEnum() 
      throws IOException {
    List<AddressBookEntry> expectedList = new ArrayList<AddressBookEntry>();
    expectedList.add(normalEntry);
    
    List<AddressBookEntry> actualList;
    normalEntryAddressBook.saveAddressListToFile("mycontact.txt");
    actualList = normalEntryAddressBook.searchAddressBook("mycontact.txt","NYC", 
        EntryAttribute.ADDRESS);
    
    assertEquals(expectedList, actualList);  
  }
  
  @Test
  public void testSearchAddressBook_normalEntryAddressBook_normalSearchText_phoneEnum() 
      throws IOException {
    List<AddressBookEntry> expectedList = new ArrayList<AddressBookEntry>();
    expectedList.add(normalEntry);
    
    List<AddressBookEntry> actualList;
    normalEntryAddressBook.saveAddressListToFile("mycontact.txt");
    actualList = normalEntryAddressBook.searchAddressBook("mycontact.txt","9850051102", 
        EntryAttribute.PHONE);
    
    assertEquals(expectedList, actualList);  
  }
  
  @Test
  public void testSearchAddressBook_normalEntryAddressBook_normalSearchText_emailEnum() 
      throws IOException {
    List<AddressBookEntry> expectedList = new ArrayList<AddressBookEntry>();
    expectedList.add(normalEntry);
    
    List<AddressBookEntry> actualList;
    normalEntryAddressBook.saveAddressListToFile("mycontact.txt");
    actualList = normalEntryAddressBook.searchAddressBook("mycontact.txt","john@gmail.com", 
        EntryAttribute.EMAIL);
    
    assertEquals(expectedList, actualList);  
  }
  
  @Test
  public void testSearchAddressBook_normalEntryAddressBook_normalSearchText_noteEnum() 
      throws IOException {
    List<AddressBookEntry> expectedList = new ArrayList<AddressBookEntry>();
    expectedList.add(normalEntry);
    
    List<AddressBookEntry> actualList;
    normalEntryAddressBook.saveAddressListToFile("mycontact.txt");
    actualList = normalEntryAddressBook.searchAddressBook("mycontact.txt","My Friend", 
        EntryAttribute.NOTE);
    
    assertEquals(expectedList, actualList);  
  }
  
  @Test
  public void testSearchAddressBook_duplicateEntriesAddressBook_nullAsSearchText() 
      throws IOException {
    List<AddressBookEntry> expectedList = new ArrayList<AddressBookEntry>();
    List<AddressBookEntry> actualList;
    duplicateEntriesAddressBook.saveAddressListToFile("mycontact.txt");
    actualList = duplicateEntriesAddressBook.searchAddressBook("mycontact.txt", null, null);
    assertEquals(expectedList, actualList);  
  }
  
  @Test
  public void testSearchAddressBook_duplicateEntriesAddressBook_emptySearchText() 
      throws IOException {
    List<AddressBookEntry> expectedList = new ArrayList<AddressBookEntry>();
    List<AddressBookEntry> actualList;
    duplicateEntriesAddressBook.saveAddressListToFile("mycontact.txt");
    actualList = duplicateEntriesAddressBook.searchAddressBook("mycontact.txt","", EntryAttribute.NAME);
    assertEquals(expectedList, actualList);  
  }
  
  @Test
  public void testSearchAddressBook_duplicateEntriesAddressBook_normalSearchText() 
      throws IOException {
    List<AddressBookEntry> expectedList = new ArrayList<AddressBookEntry>();
    expectedList.add(normalEntry);
    expectedList.add(normalEntry);
    
    List<AddressBookEntry> actualList;
    duplicateEntriesAddressBook.saveAddressListToFile("mycontact.txt");
    actualList = duplicateEntriesAddressBook.searchAddressBook("mycontact.txt","John", 
        EntryAttribute.NAME);
    
    assertEquals(expectedList, actualList);  
  }
  
  /*
   * Failed because the search functionality is not working for part of the search text. 
   * It only works for exact match. 
   */
  @Test
  public void testSearchAddressBook_partSearchText() throws IOException {
    ArrayList<AddressBookEntry> expectedList = new ArrayList<AddressBookEntry>();
    List<AddressBookEntry> actualList;
    expectedList.add(normalEntry);
    
    normalEntryAddressBook.saveAddressListToFile("mycontact.txt");
    actualList = normalEntryAddressBook.searchAddressBook("mycontact.txt","jo",EntryAttribute.NAME);
    assertEquals(expectedList, actualList);      
  }
  
  /*
   * This test failed because of the reason that the "SearchAddressBook" function is not 
   * case-insensitive
   */
  @Test
  public void testSearchAddressBook_caseInsensitive() throws IOException {
    ArrayList<AddressBookEntry> expectedList = new ArrayList<AddressBookEntry>();
    List<AddressBookEntry> actualList;
    expectedList.add(normalEntry);
    
    normalEntryAddressBook.saveAddressListToFile("mycontact.txt");
    actualList = normalEntryAddressBook.searchAddressBook("mycontact.txt", "john",
        EntryAttribute.NAME);
    assertEquals(expectedList, actualList);     
  }
  
  @Test
  public void testSearchAddressBook_interchangeEnum() throws IOException {
    ArrayList<AddressBookEntry> expectedList = new ArrayList<AddressBookEntry>();
    List<AddressBookEntry> actualList;
    normalEntryAddressBook.saveAddressListToFile("mycontact.txt");
    actualList = normalEntryAddressBook.searchAddressBook("mycontact.txt", "John", 
        EntryAttribute.ADDRESS);
    assertEquals(expectedList, actualList); 
  }
  
  /*
   * The method "SaveAddressbook" is not handling the ioexception (so gives wrong 
   * answer when wrong filename is given)
   */
  @Test(expected=IOException.class)
  public void testSearchAddressBook_wrongFileName() throws IOException {
    normalEntryAddressBook.saveAddressListToFile("mycontact.txt");
    normalEntryAddressBook.searchAddressBook("noFileLikeThis.csv","John", EntryAttribute.NAME);
  }
  
  /*
   *Failed as there is a null displayed({null}) as string instead of {}
   */
  @Test
  public void testToString_nullAddressBook() {
    String expectedString = "{}";
    String actualString = nullAddressBook.toString();
    assertEquals(expectedString, actualString);
  }
  
  /*
   *Failed as there is an additional null displayed at start when converted to String
   */
  @Test
  public void testToString_emptyEntryAddressBook() {
    String expectedString = "{[,,0,,]}";
    String actualString = emptyEntryAddressBook.toString();
    assertEquals(expectedString, actualString);
  }
  
  /*
   *Failed as there is an additional null displayed at start when converted to String
   */
  @Test
  public void testToString_normalEntryAddressBook() {
    String expectedString = "{[John, NYC, 9850051102,john@gmail.com,My Friend]}";
    String actualString = normalEntryAddressBook.toString();
    assertEquals(expectedString, actualString);
  }
  
  /*
   *Failed as there is an additional null displayed at start when converted to String
   */
  @Test
  public void testToString_duplicateAddressBook() {
    String expectedString = "{[John, NYC, 9850051102,john@gmail.com,My Friend]"
        + "[John, NYC, 9850051102,john@gmail.com,My Friend]}";
    String actualString = duplicateEntriesAddressBook.toString();
    assertEquals(expectedString, actualString);
  }
  
  /*
   * The equals method as per contract should return true for same objects
   */
  @Test
  public void testEquals_sameAddressBook() {
    assertEquals(normalEntryAddressBook, normalEntryAddressBook);   
  }
  
  /*
   * The equals method as per contract should return true for two null objects
   */
  @Test
  public void testEquals_nullAddressBook() {
    assertEquals(nullAddressBook, nullAddressBook); 
  }
  
  /*
   * Equals method not implemented properly. The object to be compared and this object should 
   * be of same type
   */
  @Test(expected = IllegalArgumentException.class)
  public void testEquals() {
    assertEquals(normalAddressBook, normalAddressBook);
    assertTrue(emptyAddressBook.equals(null));
    assertFalse(normalAddressBook.equals(""));
    assertFalse(normalAddressBook.equals(duplicateEntriesAddressBook));   
  }

}
