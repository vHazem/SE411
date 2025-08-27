package lab02;

//Exercise 2: Bounded Type Parameters
class NumberBox<T extends Number> {
 private T item;

 public void setItem(T item) {
     this.item = item;
 }

 public T getItem() {
     return item;
 }

 public double add(Number other) {
     return item.doubleValue() + other.doubleValue();
 }

 public double getDoubleValue() {
     return item.doubleValue();
 }
}
