# TD2 - Réponses aux questions
## Questions 1
### 1. Qu’affiche le programme TestPoints ?
> (3.0, 6.0) - FF0000FF \
x: 3.0 \
color : FF0000FF

### 2.
```java
Point p = new ColoredPoint(2, 4, 0xFF0000FF);
```
#### Quelle ligne pose problème et pourquoi ?
> java: cannot find symbol \
  symbol:   method getColor()\
  location: variable p of type g60552 OOheritagepoly.Point

> ligne 9: Il ne reconnait plus la méthode getcolor() car p n'est plus un ColoredPoint mais un Point, même si il est instancié via ColoredPoint, il est casté en Point et est donc un Point

#### A-t-on toujours une erreur si on supprime (ou commente) cette ligne problématique ?
> Non, si on commente la ligne, cela ne pose plus aucun problèmes.

### 3. Peut-on ajouter la ligne suivante au main ?
```java
ColoredPoint p2 = new Point(2, 4);
```
>java: incompatible types: g60552.OOheritagepoly. \
Point cannot be converted to g60552.OOheritagepoly.ColoredPoint

> En bref on essaye d'instance ColoredPoint à partir de la class dont elle hérite, ce qui est impossible 

### 4. Peut on remplacer le contenu de la méthode toString() de ColoredPoint via
```java
return this.x + " - " this.y + " - " + this.color;
```
> Oui on peut remplacer le toString, avec @override pour permet de réécrire la méthode. Cela va permettre de remplacer la méthode par défaut (celle de la classe parente) par celle de l'enfant

### 5. Quelle erreur obtient on si l'on modifie la déclaration de la classe Point comme suit:
```java
public class Point extends ColoredPoint {
    ...
}
```
> java: cyclic inheritance involving g60552.OOheritagepoly.Point

> Héritage cyclique, le fait de faire un héritage infini comme ça provoque une erreur

### 6. Quelle erreur obtient on si on déclare final la classe Point.
> java: cannot inherit from final g60552.OOheritagepoly.Point

> Elle ne peut pas avoir d'enfants avec final, mais elle peut avoir un parent si par exemple c'était ColoredPoint qui était final.


## Question 2
### 1. Peut-on ajouter la ligne suivante au main ?
```java
Object p3 = new Point(2, 4);
```
> Oui on peut
#### Pourquoi ?
> Parce que Point est la classe enfant de Object (toutes les classes héritent de Objet).

### 2. Peut-on ajouter la ligne suivante au main ?
```java
Object p4 = new ColoredPoint(2, 4, 0xFF0000FF);
```
> Oui on peut
#### Pourquoi ?
> Car elle hérite de Point qui lui même hérite de Objet.

### 3. Peut-on ajouter la ligne suivante au main ?
```java
p.hashCode();
```
> Oui
#### Où est définie cette méthode ?
> Dans Object, toute les classes possède du coup un hashcode()
#### Pourquoi peut-on l’appeler sur un objet de type ColoredPoint ?
> Car elle hérite de la classe Point qui lui même hérite de cette méthode via Object.

## Questions 3
### 1. Quelle erreur de compilation obtenez-vous si vous ajoutez
```java
System.out.println("test");
```
#### Comme première ligne du constructeur de la classe ColoredPoint ?
>java: call to super must be first statement in constructor

> En bref le constructeur de la classe parente doit être la première instructions lors de l'instanciation du fils.

### 2. Quelle erreur de compilation obtenez-vous si vous supprimez la ligne
```java
super(x,y);
```
> java: constructor Point in class g60552.OOheritagepoly.Point cannot be applied to given types; \
  required: double,double \
  found:    no arguments \
  reason: actual and formal argument lists differ in length

> On sans le super, on fait appel au constructeur de Point() par défaut, sans argument, mais comme on a un constructeur a deux arguments dans Point, le constructeur par défaut a été supprimé, ce qui provoque une erreur

#### Dans le constructeur de la classe ColoredPoint ? A quoi sert cette ligne ?
> Elle sert à appeler le constructeur de Point avec deux arguments.

### 3. Après avoir supprimé la ligne au point précédent, ajoutez dans la classe Point le
```java
public Point() {
    this(0,0);
}
```
#### A-t-on toujours la même erreur qu’au point précédent ?
> Non car ici on a précisé le constructeur sans argument, qui est appel implicitement sans le super.

## Questions 4
### 1. Qu’affiche ce programme ?
> constructor of A \
constructor of B \
constructor of C

### 2. Supprimez le constructeur de la classe C, qu’affiche maintenant le programme ?
> constructor of A \
constructor of B

### 3. Après avoir remis le constructeur de la classe C, ajoutez dans chaque constructeur un appel explicite au constructeur de la super-classe (super();). Vérifiez que l’effet est bien identique.
> constructor of A \
constructor of B \
constructor of C

> L'effet est bien le même !

### 4. A votre avis, quelles sont les constructeurs de la classe Object ? Vérifiez en consultant la javadoc.

```java
Object() {};
```
> Elle permet d'être appelé implicitement par n'importe quelle classe sans mettre le super();


## Questions 5

### 1. Qu’affiche ce programme ?
> (0.0, 0.0) - not pinned \
(1.0, 1.0) - pinned


### 2. Selon vous, quelle méthode move est exécutée :
> Celle de PinnablePoint()

### 3. Ajoutez une exception à la méthode move.

```java
@Override
public Point move(double dx, double dy) throws Exception {
    if(!pinned)
        super.move(dx, dy);
    else throw new Exception("Point is pinned, cannot move anymore");
    return this;
}
```

#### Quelle erreur obtenez-vous ?
> java: move(double,double) in g60552.OOheritagepoly.PinnablePoint cannot override move(double,double) in g60552.OOheritagepoly.Point
  overridden method does not throw java.lang.Exception

### 4. Remplacez Exception par une IllegalStateException. Avez-vous toujours une erreur ? Pourquoi ?
> Car la signature de la méthode n'est pas changée, elle est juste indiquée alors que Exception oui.
### 5. Retirez la clause throws (mais gardez le throw), avez–vous une erreur ? 2
> Non, le throws IllegalStateException dans l'en-tête n'est pas obligatoire.
### 6. Remplacez le type de retour par PinnablePoint, avez-vous une erreur ?
> Non, cela fonctionne
### 7. Remplacez le type de retour par Object, obtenez-vous une erreur ? pourquoi ?
> java: move(double,double) in g60552.OOheritagepoly.PinnablePoint cannot override move(double,double) in g60552.OOheritagepoly.Point \
  return type java.lang.Object is not compatible with g60552.OOheritagepoly.Point
### 8. Remplacez le modificateur d’accès public par protected, quelle erreur obtient-on ?
> java: move(double,double) in g60552.OOheritagepoly.PinnablePoint cannot override move(double,double) in g60552.OOheritagepoly.Point \
  attempting to assign weaker access privileges; was public
### 9. Dans la méthode move, que fait l’appel "super.move(...);" ?
> Il fait appel à la méthode move() de la super class (classe mère)


## Question 6
### 1. Qu'affiche ce programme
> (1.0, 1.0) \
(3.0, 5.0) - FF0000FF \
(2.0, 2.0) - not pinned

## Questions 7
### 1. Ajoutez le mot clef protected devant la méthode move. Que constatez-vous ?
> Il n'est pas authorisé pour une interface !
### 2. Remplacez protected par public. Que constatez-vous ?
> Il peut être public
### 3. Modifiez la déclaration de la classe Point afin qu’elle implémente l’interface Movable :
```java
public class Point implements Movable {
...
}
```
### 4. Remplacez Point par Movable dans les déclarations de TestPolymorphisme. Qu’af- fiche le programme ?
> (1.0, 1.0) \
(3.0, 5.0) - FF0000FF \
(2.0, 2.0) - not pinned 