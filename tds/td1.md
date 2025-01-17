# TD1 - Réponses aux questions
## Questions 1
### Qu'affiche le programme ?
> (0.0, 0.0) \
> (2.0, 2.0)
### Si on change la visibilité de la class en public, quelle erreur le compilateur rapporte-t-il ?
> java: class TestPoint is public, should be declared in a file named TestPoint.java
### Si vous changez la visibilité de la classe Point en visibilité par défaut, obtenez vous une erreur ? si oui, laquelle ?
> Je n'obtiens pas d'erreurs
### Remettez le code dans son état d'origine !

## Questions 2
### Ajoutez dans la méthode principale de la classe TestPoint
> java: x has private access in g60552.OObases.Point
### Ajoutez la méthode suivante à la classe Point
```java
public void move(int dx, int dy) {
    System.out.println("méthode move(int, int)");
    x += dx;
    y += dy;
}
```
> (0.0, 0.0) \
méthode move(int, int) \
(2.0, 2.0)
### Que se passe-t-il si on tente d'ajouter la méthode (avec type de retour différent mais le nombre et type des parametres sont identiques)
> java: method move(double,double) is already defined in class g60552.OObases.Point

## Questions 3
### 1. Modifiez le constructeur sans paramètre comme suit
```java
public Point() {
    System.out.println("test");
    this(0, 0);
}
```
#### Quelle erreur obtient-on ?
> java: call to this must be first statement in constructor
### 2. Supprimez le constructeur sans paremètre de la classe Point
#### Quelle erreur de compilation obtient-on ?
> java: constructor Point in class g60552.OObases.Point cannot be applied to given types; \
required: double,double \
found:    no arguments \
reason: actual and formal argument lists differ in length
#### Supprimez l'autre constructeur. La classe Point ne définit plus de constructeur
#### Obtient-on toujours une erreur ? Pourquoi ?
> Non, car on fait appel dans ce cas au constructeur sans paramètre.
#### Qu'affiche ce programme ?
> (0.0, 0.0) \
méthode move(int, int) \
(2.0, 2.0)
### 3. Il est possible, mais déconseillé, d'initialiser les attributs avec des valeurs par défaut explicites. Par exemple à partir du code de la questions précédente
```java
public class Point {
    private double x = 10;
    private double y = 10;
    ...
}
```
#### Qu'affiche ce programme maintenant ?
> (10.0, 10.0) \
(12.0, 12.0)


## Questions 4

### 1. Qu'affiche ce programme ?
TestCircle
>Circle : [(0.0, 0.0), 5.0] \
Circle : [(2.0, 5.0), 5.0] \
Circle : [(2.0, 5.0), 10.0]

### 2. Combien d'instances différentes de la classe Point sont créées dans ce programme ?
> Une seule instance de point est crée et réutilisée lors de la création de Circle, dans TestCircle

## Questions 5

### 1. Qu'affiche ce programme ?
### 2. Combien d'instance(s) de la classe Point et Circle sont crées dans ce programme ?
TestDefensiveCopy
> Circle : [(0.0, 0.0), 5.0] \
Circle : [(2.0, 5.0), 5.0] \
Circle : [(0.0, 0.0), 5.0]
#### Quelles sont les instances référencées par la variable p et p2 dans le main ?
> p et p2 sont des variable de type Point qui référencie la même instance de Point
#### Quelle instance référence l'attribut center de l'instance c crée dans le main ?
> L'attribut center de l'instance c référencie l'instance pointée par la variable p de type Point qui a été passé en paramètre lors de la création du Cercle

### 3. Ajoutez une copie défensive à la ligne 14 de la classe Circle
```java
this.center = new Point(center.getX(), center.getY());
```
#### Qu'affiche-t-il maintenant ?
TestDefensiveCopy
> Circle : [(0.0, 0.0), 5.0] \
Circle : [(0.0, 0.0), 5.0] \
Circle : [(-2.0, -5.0), 5.0]

### 4. Remplacez la ligne 26 de la classe Circle par la ligne suivante:
```java
return new Point(center.getX(), center.getY());
```
TestDefensiveCopy
> Circle : [(0.0, 0.0), 5.0] \
Circle : [(0.0, 0.0), 5.0] \
Circle : [(0.0, 0.0), 5.0]

### 5. Après avoir effectué ces modifications au programme :
#### Combien d’instances de la classe Point et Circle sont créées dans ce programme ?
> 3 instances de points sont créés \
center, p et p2 auront des instances différentes
#### Quelles sont les instances référencées par la variable p et p2 dans le main ?
> Ce sont deux instances complètement différentes.
p instancié dans le main et p2 instancié dans la méthode de Circle

#### Quelle instance référence l’attribut center de l’instance c créée dans le main ?
> Une nouvelle instance crée dans le constructeur de Circle à partir des attributs de p donnée par ses getteurs.

## Questions 6 - invariants de class
> Ce sont les conditions de la classes qui doivent être respectées pour que l'objet puisse être créé
### 1. Qu’affiche ce programme ?
> Rectangle : [(0.0, 0.0), (5.0, 3.0)]\
perimeter: 16.0\
Rectangle : [(2.0, 5.0), (7.0, 8.0)]\
perimeter: 16.0
### 2. Donnez le diagramme d’objets (instances) de ce programme en indiquant les instances que les variables bl, ur et r référencent.
![](/tds/td1q6-2.png)

### 3. L’implémentation n’effectue pas de copie défensive des points passés en paramètres au constructeur. Ajoutez la ligne suivante à la ligne 36 de la méthode main :
```java
bl.move(10,10);
```
#### L’invariant est-il toujours respecté ?
> oui, il est respecté
#### Qu’affiche maintenant le programme ?
> Rectangle : [(0.0, 0.0), (5.0, 3.0)] \
perimeter: 16.0 \
Rectangle : [(10.0, 10.0), (15.0, 13.0)] \
perimeter: 16.0
#### En particulier, que vaut le périmètre du rectangle ?
> Il vaut toujours 16
#### Pourquoi cette valeur s’affiche-t-elle ?
> Car les deux points ont décalé de 10 et 10, le périmètre reste alors inchangé
### 4. Modifiez la classe Rectangle en lui ajoutant les copies défensives là où elles sont nécessaires. Qu’affiche maintenant le programme (modifié au point précédent) ?
> rien n'a changé dans l'affichage !

> Rectangle : [(0.0, 0.0), (5.0, 3.0)] \
perimeter: 16.0 \
Rectangle : [(10.0, 10.0), (15.0, 13.0)] \
perimeter: 16.0

## Bonus: Faire la classe ImmutableCircle
```java
package g60552.OObases;

public final class ImmutableCircle {
    private final double radius;
    private final ImmutablePoint center;

    ImmutableCircle(ImmutablePoint center, double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("radius must be positive" +
                    ", received: " + radius);
        }
        this.center = new ImmutablePoint(center.getX(), center.getY());
        this.radius = radius;
    }

    public ImmutableCircle move(int dx, int dy) {
        return new ImmutableCircle(center.move(dx, dy), radius);
    }

    public double area() {
        return Math.PI * radius * radius;
    }

    public ImmutablePoint getCenter() {
        return new ImmutablePoint(center.getX(), center.getY());
    }
}
```