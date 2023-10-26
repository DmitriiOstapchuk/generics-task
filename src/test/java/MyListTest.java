import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class MyListTest {
  //List with size equal to initial capacity. There is no additional test for resize() method
  MyList<Integer> integerList = new MyList<>(new Integer[] {18, 93, 7, -256, -534523, 5223, 6828, 7, 134, 2345});
  @Test
  // Adding random element is checked
  public void should_add_random_element() {
    Integer randomElement =(int) (Math.random() * Integer.MAX_VALUE);
    integerList.add(randomElement);
    MyList<Integer> expectedList = new MyList<>(new Integer[] {18, 93, 7, -256, -534523, 5223, 6828, 7, 134, 2345, randomElement});
    Assertions.assertEquals(expectedList, integerList, "Result list is not equal to expected");
  }
  @Test
  public void should_not_be_changed_after_adding_null() {
    MyList<Integer> copiedList = integerList;
    Integer nullElement = null;
    integerList.add(nullElement);
    Assertions.assertEquals(copiedList, integerList, "Result list is not equal to initial after adding null");
  }

  Byte[] byteArray = new Byte[] {23, 34, -52, 22, 19, 18, -17, 127, 100, -6};
  MyList<Byte> byteList = new MyList<>(byteArray);
  @Test
  public void should_get_element_with_random_correct_index() {
    int randomIndex = (int) (Math.random() * (byteArray.length - 1));
    Byte targetElement = byteArray[randomIndex];
    Byte element = byteList.get(randomIndex);
    Assertions.assertEquals(targetElement, element, "Right element wasn't got");
  }
  @Test
  // Cases with two nearest out of bound indexes, one random negative index, one random incorrect positive index are checked
  public void should_not_get_element_with_incorrect_indexes() {
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> byteList.get(-1), "There is no exception with incorrect index(nearest negative value)");
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> byteList.get(byteArray.length), "There is no exception with incorrect index(nearest out of bounds positive value)");
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> byteList.get((int) (Math.random() * -100)), "There is no exception with incorrect index(random negative value)");
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> byteList.get((int) ((byteArray.length - 1) + Math.random() * 100)), "There is no exception with incorrect index(random out of bounds positive value)");
  }

  Float[] floatArray = new Float[] {74.12f, -54.8234f, 7.929f, -95.2f, -30.924f, -14255.9243f, 94.4234f, 56.97234f, -342.4256f};
  MyList<Float> floatList = new MyList<>(floatArray);
  @Test
  //Test of random index element remove
  public void should_remove_element() {
    int randomIndex = (int) (Math.random() * (floatArray.length - 1));
    floatArray[randomIndex] = null;
    Float[] expectedArray = new Float[floatArray.length-1];
    int newIndex = 0;
    for (Float item : floatArray) {
      if (item != null) {
        expectedArray[newIndex] = item;
        newIndex++;
      }
    }
    MyList<Float> expectedList = new MyList<>(expectedArray);
    floatList.remove(randomIndex);
    Assertions.assertEquals(expectedList, floatList, "List after remove isn't equal to expected List");
  }
  @Test
  // Cases with two nearest out of bound indexes, one random negative index, one random incorrect positive index are checked
  public void should_not_remove_element_with_incorrect_indexes() {
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> floatList.remove(-1), "There is no exception with incorrect index(nearest negative value)");
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> floatList.remove(byteArray.length), "There is no exception with incorrect index(nearest out of bounds positive value)");
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> floatList.remove((int) (Math.random() * -100)), "There is no exception with incorrect index(random negative value)");
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> floatList.remove((int) ((byteArray.length - 1) + Math.random() * 100)), "There is no exception with incorrect index(random out of bounds positive value)");
  }

  MyList<Double> firstDoubleList = new MyList<>(new Double[]{52.134, -254.23, 44.452, 852.92});
  @Test
  public void should_map_list_1 () {
    MyList<Integer> expectedList = new MyList<>(new Integer[]{52, 254, 44, 853});
    MyList<Integer> actualList = firstDoubleList.map(x -> (int) Math.abs(Math.round(x)));
    Assertions.assertEquals(expectedList, actualList, "map() method returns unexpected list");
  }

  @Test
  public void should_map_list_2 () {
    MyList<Integer> expectedList = new MyList<>(new Integer[]{2, 1, 2, 2});
    MyList<Integer> actualList = firstDoubleList.map(x -> (x > 0) ? 2 : 1);
    Assertions.assertEquals(expectedList, actualList, "map() method returns unexpected list");
  }

  @Test
  // An array with random length is created. Its length is compared with method size() invocation for List, which constructor contains that array with random length
  public void should_return_right_size() {
    int randomLength = (int) (Math.random() * 100);
    Integer[] array = new Integer[randomLength];
    for(int i = 0; i < array.length; i++) {
      array[i] = 0;
    }
    MyList<Integer> testList = new MyList<>(array);
    int size = testList.size();
    Assertions.assertEquals(randomLength, size, "Result size isn't equal to expected value");
  }


  MyList<Double> secondDoubleList = new MyList<>(new Double[] {23.234, -234255.24, 72.2345, 15.569, -12.22});
  @Test
  public void should_be_equal_same_reference() {
    MyList<Double> sameReferenceList = secondDoubleList;
    Assertions.assertTrue(secondDoubleList.equals(sameReferenceList), "List isn't equal to the list with the same reference");
  }

  @Test
  //all elements have another class, but the same value
  public void should_not_be_equal_another_class() {
    MyList<Float> floatList = new MyList<>(new Float[]{23.234f, -234255.24f, 72.2345f, 15.569f, -12.22f});
    Assertions.assertFalse(secondDoubleList.equals(floatList), "The list isn't equal to the list with the same reference");
  }

  @Test
  // two lists with the same elements. Symmetry is  also tested.
  public void should_be_equal_same_content() {
    MyList<Double> sameList = new MyList<>(new Double[] {23.234, -234255.24, 72.2345, 15.569, -12.22});
    Assertions.assertTrue(secondDoubleList.equals(sameList), "The list is not equal to the list with same content");
    Assertions.assertTrue(sameList.equals(secondDoubleList), "Symmetry for equals() method doesn't work");
  }

  @Test
  // last element was changed
  public void should_not_be_equal_different_element() {
    MyList<Double> changedList = new MyList<>(new Double[] {23.234, -234255.24, 72.2345, 15.569, -12.22001});
    Assertions.assertFalse(secondDoubleList.equals(changedList), "The list is equal to the list with different content");
  }

  @Test
  // order of two last elements was changed
  public void should_not_be_equal_different_order() {
    MyList<Double> changedOrderList = new MyList<>(new Double[] {23.234, -234255.24, 72.2345, -12.22, 15.569});
    Assertions.assertFalse(secondDoubleList.equals(changedOrderList), "The list is equal to the list with different order of elements");
  }

  @Test
  // The List is a sublist of another. Tested with symmetry
  public void should_not_be_equal_sublist() {
    MyList<Double> extraElementList = new MyList<>(new Double[] {23.234, -234255.24, 72.2345, 15.569, -12.22, -4.7});
    Assertions.assertFalse(secondDoubleList.equals(extraElementList), "The list is equal to its sublist");
    Assertions.assertFalse(extraElementList.equals(secondDoubleList), "The list is equal to its sublist");
  }

  @Test
  // compare empty List and null List
  public void should_not_be_equal_null () {
    MyList<Double> emptyList = new MyList<>();
    MyList<Double> nullList = null;
    Assertions.assertFalse(emptyList.equals(nullList), "The list is equal to null");
  }

  MyList<Short> shortList = new MyList<>(new Short[]{-23, 435, 17, 43, 1, 12, 4256, 11222});
  @Test
  public void should_be_consistent_hashcode() {
    int listHashCode = shortList.hashCode();
    Assertions.assertEquals(listHashCode, shortList.hashCode());
  }
  @Test
  public void should_be_same_hashcode() {
    MyList<Short> sameList = new MyList<>(new Short[]{-23, 435, 17, 43, 1, 12, 4256, 11222});
    Assertions.assertEquals(shortList, sameList, "Lists should be equal for this test");
    Assertions.assertEquals(shortList.hashCode(), sameList.hashCode(), "Same lists should have the same hashCode");
  }

  @Test
  //the last element is changed
  public void should_not_have_same_hashCode() {
    MyList<Short> differentElementList = new MyList<>(new Short[]{-23, 435, 17, 43, 1, 12, 4256, 11221});
    Assertions.assertNotEquals(shortList, differentElementList, "For this test lists should be different");
    Assertions.assertNotEquals(shortList.hashCode(), differentElementList.hashCode(), "Lists with the only different element should have different hashCodes");
  }

  @Test
  public void should_be_transformed_to_String_correctly_1 () {
    MyList<Double> doubleList = new MyList<>(new Double[] {-7.4245, 8.99913, 23.4445, -15.5535, 0.0});
    String expectedString = "-7.4245, 8.99913, 23.4445, -15.5535, 0.0";
    String doubleString = doubleList.toString();
    Assertions.assertEquals(expectedString, doubleString, "toString() method doesn't return expected String");
  }

  @Test
  public void should_be_transformed_to_String_correctly_2 () {
    MyList<Double> doubleList = new MyList<>(new Double[] {-245.525, -23.42, 44.234, 188.2});
    String roundedString = "-245.525, -23.42, 44.234, 188.2";
    String doubleString = doubleList.toString();
    Assertions.assertEquals(roundedString, doubleString, "toString() method doesn't return expected String");
  }

  @Test
  public void should_iterate_correctly () {
    MyList<Long> longList = new MyList<>(new Long[] {1221212121221L, 343444434333343L, 5655565666666656555L});
    Iterator<Long> iterator = longList.iterator();
    Assertions.assertTrue(iterator.hasNext(), "hasNext() method works incorrectly");
    Assertions.assertEquals(1221212121221L, iterator.next(), "next() method works incorrectly");
    Assertions.assertTrue(iterator.hasNext(), "hasNext() method works incorrectly");
    Assertions.assertEquals(343444434333343L, iterator.next(), "next() method works incorrectly");
    Assertions.assertTrue(iterator.hasNext(), "hasNext() method works incorrectly");
    Assertions.assertEquals(5655565666666656555L, iterator.next(), "next() method works incorrectly");
    Assertions.assertFalse(iterator.hasNext(), "hasNext() method works incorrectly");
    Assertions.assertThrows(NoSuchElementException.class, () -> iterator.next(), "next() method works incorrectly");
  }

}
