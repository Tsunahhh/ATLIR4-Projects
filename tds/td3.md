# TD03 - JavaFX
## Questions 1
### 1. Dans la classe HelloWorld modifiez le code d’instanciation de la scène ci-dessous en modifiant la valeur des paramètres numériques.
```java
Scene scene = new Scene(root, 250, 100);
```
#### Quel est l’effet de ce changement de paramètres ?
> Cela permet la modification de la taille de la fenêtre width - height.

### 2. Juste après avoir modifié le titre de la primaryStage ajoutez le code ci-dessous :
```java
primaryStage.initStyle(StageStyle.TRANSPARENT);
```
#### Quel est l’effet de cette méthode initStyle() sur la fenêtre ? 
> Elle permet de modifier le style de la bordure de la fenêtre.

#### Après consultation de la javadoc 5 de la classe Stage donnez les valeurs possibles de l’énumération StageStyle.
> DECORATED, TRANSPARENT, UNDECORATED, UTILITY, UNIFIED


### 3. Afin de placer le composant Text au centre de l’écran, la méthode setCenter() de la classe BorderPane est appelée

```java
BorderPane root = new BorderPane();
root.setCenter(helloText);
```

#### Que ce passe-t-il si on choisit d’appeler une des méthodes ci-dessous ?
- ▷ setTop()
> Message placé en haut à gauche
- ▷ setBottom()
> Le message placé en bas à gauche
- ▷ setLeft()
> Message placé en haut à gauche
- ▷ setRight()
> Message placé en haut à droite

## Questions 2

### 1. Quel est la différence de comportement entre les trois CheckBox de la classe HelloWorldCheckBox ?
> Le premier n'a que deux états (coché et non coché) et il est set sur coché par défaut.

> Le second est par défaut sur indéterminé mais il ne peut être que coché ou décoché par après.

> Le 3rd est non coché, mais il accepte 3 états : (coché, non coché et indéterminé).

### 2. Quel est l’effet sur l’affichage des CheckBox si on supprime les lignes de code ci-dessous ?

```java
BorderPane.setAlignment(checkBox1, Pos.CENTER);
BorderPane.setAlignment(checkBox3, Pos.CENTER);
```

> Si on supprimer le setAlignment, ils se retrouvents au top à gauche pour le premier et à droite pour le second.\
Le setAlignement permet donc de les placer verticalement.\
Pas besoin pour le setCenter car il place directement le checkbox au centre de le fenêtre.

## Questions 3
### 1. Modifiez la classe HelloWorldtextField et transformez le composant TextField en un PasswordField. Quelles sont les conséquences de cette modification ?

```java
PasswordField tfdUserName = new PasswordField();
```

> La zonne de text est devenue une zone ou les caractères sont cachés par des points.

### 2. Lors de l’exécution de HelloWorldTextFieldAction, que se passe-t-il après avoir pressé la touche enter ?

> On ne peut plus modifier le texte de la zone, une fois le bouton pressé.

## Question 4

### Dans le code de présentation ci-dessous, nous avons ajouté l’utilisation du composant Button qui possède également la propriété onAction. Essayez de comprendre ce qui se produit lors d’une pression sur le bouton de cet écran et expliquez-le brièvement.

> Le bouton print est à l'écoute d'un évennement, lorsque l'on clique dessus, il affiche dans la console le text get du TextArea

## Question 5

### Vérifiez via la documentation ce que retourne la méthode getChildren(). Quel peut être l’intérêt d’un tel type de retour ?

> Il retourne une liste de noeuds observable représentant les enfants.

> Il permet de voir qu'est ce que possède le Layout et permet juste d'ajouter un enfant.

## Question 6

### 1. Le layout VBox 17 place les composants sur une ligne verticale de haut en bas les uns en dessous des autres.     Transformez la classe HBoxSample en une classe VBoxSample afin de disposer d’un écran comme sur la figure 14 page précédente;
```java
HBox root = new HBox(10);
```
> On changle juste le HBox en VBox et on importe VBox
```java
VBox root = new VBox(10);
```


### 2. Pour ajouter les composants au layout vous avez utilisé la méthode addChildren(). Pouvez-vous modifier votre code en utilisant la méthode addAll() ?

```java
root.getChildren().addAll(checkBox1, checkBox2, checkBox3);
```
> Les rajouter en une fois donne le même résultat en écrivant moins de ligne de code.

### 3. Dans la documentation du Layout VBox vous trouverez la remarque suivante : VBox does not clip its content by default, so it is possible that childrens’ bounds may extend outside its own bounds if a child’s min size prevents it from being fit within the vbox.. Quel impact sur le développement de nos interfaces graphiques peut avoir cette notion de clip ? Quelles méthodes sont à notre disposition pour gérer cette notion ?

> Il peut dépasser de sa zone attribué si sa taille minimal ne correspond pas à la taille de la VBox. Il peut être également coupé

> Le setVgrow(), setFillWidth() 

## Question 7

### 1. Au sein d’un GridPane placez plusieurs composants dans une même cellule. 

#### Comment sont-ils répartis au sein de cette cellule ?

```java
Label lab1 = new Label("t1");
Label lab2 = new Label("t2");
Label lab3 = new Label("t3");
root.add(lab1,0, 3);
root.add(lab2, 0, 3);
root.add(lab3, 0, 3);
```
> J'ai mis 3 labels dans la même cellule, ils se placent l'un dans l'autre, au même endroit.


### 2. Remplacez les paramètres de la méthode statique `GridPane.setHalignment(lblPassword, HPos.RIGHT)` par `GridPane.setHalignment(lblPassword, HPos.CENTER)`. 

#### Quel est l’impact sur l’affichage de l’écran ?
> Le label password se trouve au milieu de sa cellule

### 3. Remplacez les paramètres de la méthode statique GridPane.setFillWidth( tfdPassword, false) par GridPane.setFillWidth(tfdPassword, true). 

#### Quel(s) changements(s) pouvez-vous noter ?

> La zone de text remplit toute la taille de la cellule

