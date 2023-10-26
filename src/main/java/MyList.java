import java.util.Iterator;
import java.util.function.Function;
import java.lang.StringBuilder;

public class MyList <T extends Number> implements Iterable<T>{
  private static final int INITIAL_CAPACITY = 10;
  private  Object[] array = new Object[INITIAL_CAPACITY];
  private int size = 0;
  public MyList() {
  }
  public MyList (T[] array) {
    this.array = array;
    size = array.length;
  }
  public void add(T o) {
    if (size + 1 > array.length) {
      this.resize();
    }
    array[size] = o;
    size++;
  }

  public T get(int index) {
    if (index >= size || index < 0) {
      throw new IndexOutOfBoundsException();
    }
    return (T) array[index];
  }

  private void resize() {
    Object[] tempArray = array;
    array = new Object[(int)(tempArray.length * 1.5)];
    System.arraycopy(tempArray, 0, array, 0, tempArray.length);
  }

  public Object[] remove(int index) {
    if (index >= size || index < 0) {
      throw new IndexOutOfBoundsException();
    }
    Object[] tempArray = new Object[array.length];
    System.arraycopy(array, 0, tempArray, 0, index);
    System.arraycopy(array, index + 1, tempArray, index, array.length-1-index);
    array = tempArray;
    size--;
    return array;
  }

  public <R extends Number> MyList<R> map(Function<T, R> f) {
    MyList<R> newMyList = new MyList<>();
    this.forEach(x -> newMyList.add(f.apply(x)));
    return newMyList;
  }

  public int size() {
    return size;
  }
  @Override
  public boolean equals (Object o){
    if (o == this)
      return true;
    if (o == null)
      return false;
    if (o.getClass() != this.getClass())
      return false;
    MyList<T> comparedList = (MyList<T>) o;
    if (this.hashCode() != comparedList.hashCode())
      return false;
    if (this.size() != comparedList.size())
      return false;
    for (int i = 0; i < this.size(); i++) {
      if (!(this.get(i).equals(comparedList.get(i))))
        return false;
    }
    return true;
  }
  @Override
  public int hashCode () {
    int hash = 0;
    for (T element : this) {
      hash = 31 * hash + (element != null ? element.hashCode() : 0);
    }
    return hash;
  }
  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.get(0));
    for (int i = 1; i < this.size; i++) {
      stringBuilder.append(", ");
      stringBuilder.append(this.get(i));
    }
    return String.valueOf(stringBuilder);
  }

  @Override
  public Iterator<T> iterator() {
    return new MyIterator();
  }

  /**
   * Основным отличием статического inner class от обычного класса является
   * возможность для статического внутреннего класса обращаться к private static
   * переменным и методам внешнего класса. Также для статического внутреннего класса
   * существуют особенности обращения к нему, его переменным и методам за пределами
   * внешнего класса: при обращении к статическому внутреннему классу сначала указывается
   * внешний класс по схеме: OuterClass.StaticInnerClass (обращение к классу),
   * OuterClass.StaticInnerClass.method() (обращение к методу),
   * OuterClass.StaticInnerClass.variable (обращение к переменным). В остальном статический
   * inner class работает так же, как обычный класс.
   */
  private class MyIterator implements Iterator<T>{
    private int index = 0;
    @Override
    public boolean hasNext() {
      return index < size;
    }

    @Override
    public T next() {
      if (!hasNext()) {
        throw new java.util.NoSuchElementException();
      }
      return (T)array[index++];
    }
  }
}
