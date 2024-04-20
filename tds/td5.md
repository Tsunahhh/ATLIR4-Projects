# TD05 - Generics
## Question 1
```java
box.setElement(new Double(43));
```
> 'Double(double)' is deprecated since version 9 and marked for removal 

## Question 2
```java
Box<Integer> box = new Box<Integer>(42);
```
> Explicit type argument Integer can be replaced with <> 

## Question 3

```java
Box<Object> box = new Box<Integer>(Integer.valueOf(10));
```
> Non, on ne peut pas écrire, il faut que les types soient exactement les mêmes.

## Question 4
```java
Box<Integer> box = new Box<>(new Integer(10));
```
> java: incompatible types: esi.atl.generics.Box<java.lang.Integer> cannot be converted to esi.atl.generics.Box<java.lang.Object>

## Question 5
```java

```
> 'Integer(int)' is deprecated since version 9 and marked for removal

## Question 6
```java

```
> java: incompatible types: java.lang.Integer cannot be converted to capture#1 of ?

## Question 7
```java
public class Pair<T> {
```
> java: cannot find symbol\
  symbol:   method compareTo(T)\
  location: variable first of type T

## Question 8
```java
Pair<Number> pair = new Pair<>(new Integer(32), new Integer(42));
```
> java: type argument java.lang.Number is not within bounds of type-variable T

## Question 9
```java
static <Number> void copy(List<Object super Number> d, List<Integer extends Number> s)

List<Object> o = new ArrayList<>();
List<Integer> i = new ArrayList<>();
// add
copy(o, i);
```

## Question 10
```java
List<Double> d = new ArrayList<Double>();

sort(List<T> list, Comparator<? super T> c);
```
> Il doit utiliser un type qui hérite -> Object et pas un type primitif à cause de Comparator<? super T> --> T doit hériter.