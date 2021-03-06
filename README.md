# Reversi

## A propos de ce projet

### Cursus
ENI | La Programmation Orientée Objet (POO) avec Java  
~ [Module 8 - Les énumérations](https://github.com/Dyrits/REVERSI/blob/master/Module%2008%20-%20Enonc%C3%A9%20TP%20-%20Reversi%201.pdf)  
~ [Module 9 - La généricité](https://github.com/Dyrits/REVERSI/blob/master/Module%2009%20-%20Enonc%C3%A9%20TP03%20-%20Reversi%202.pdf)

### Énoncés (par l'ENI)

<details markdown="block">
<summary>Partie 1</summary>  

L’objectif est de créer un jeu de Reversi (également connu sous le nom d’Othello). Voici l’explication et les règles de ce jeu, d’après Wikipédia :

*Il se joue sur un plateau unicolore de 64 cases, 8 sur 8. Les joueurs disposent de 64 pions bicolores, noirs d'un côté et blancs de l'autre. En début de partie, quatre pions sont déjà placés au centre du plateau dans la position suivante :*

       1  2  3  4  5  6  7  8  
    1  ·  ·  ·  ·  ·  ·  ·  ·  
    2  ·  ·  ·  ·  ·  ·  ·  ·  
    3  ·  ·  ·  ·  ·  ·  ·  ·  
    4  ·  ·  ·  o  ●  ·  ·  ·  
    5  ·  ·  ·  ●  o  ·  ·  ·  
    6  ·  ·  ·  ·  ·  ·  ·  ·  
    7  ·  ·  ·  ·  ·  ·  ·  ·   
    8  ·  ·  ·  ·  ·  ·  ·  ·  

*Chaque joueur, noir et blanc, pose l'un après l'autre un pion de sa couleur sur le plateau de jeu selon des règles précises. Le jeu s'arrête quand les deux joueurs ne peuvent plus poser de pion. On compte alors le nombre de pions. Le joueur ayant le plus grand nombre de pions de sa couleur sur le plateau a gagné.*

*Noir commence toujours la partie. Puis les joueurs jouent à tour de rôle, chacun étant tenu de capturer des pions adverses lors de son mouvement. Si un joueur ne peut pas capturer de pion(s) adverse(s), il est forcé de passer son tour. Si aucun des deux joueurs ne peut jouer, ou si le plateau ne comporte plus de case vide, la partie s'arrête.*

*La capture de pions survient lorsqu'un joueur place un de ses pions à l'extrémité d'un alignement de pions adverses contigus et dont l'autre extrémité est déjà occupée par un de ses propres pions. Les alignements considérés peuvent être une colonne, une ligne, ou une diagonale. Si le pion nouvellement placé vient fermer plusieurs alignements, il capture tous les pions adverses des lignes ainsi fermées. La capture se traduit par le retournement des pions capturés. Ces retournements n'entraînent pas d'effet de capture en cascade : seul le pion nouvellement posé est pris en compte.*

Pour vous familiariser avec ce jeu, vous pouvez tester une version pour jouer en ligne telle
que [reversi.fr](http://reversi.fr/).

</details>

<details markdown="block">
<summary>Partie 2</summary>  

Il existe une multitude de jeux se jouant sur un plateau de jeu carré ou rectangulaire :

Reversi, dames, bataille navale, échecs, morpion, Puissance 4, démineur… Il est possible de créer une classe Plateau qui serait utilisée pour tous ces jeux. Ainsi, celle-ci mutualiserait les actions communes à tous ces plateaux de jeu (affichage, modification d’une case et consultation d’une case). Ce qui change entre tous ces plateaux de jeu, ce sont les pions qui sont posés sur les cases. Cela sera donc paramétré par un type générique.

</details>


> Plus de détails sont disponibles dans les fichiers au format PDF correspondant à chaque énoncé.

### Technologie principale
- Java

### Détails | Commentaires
Ce projet a été construit à partir de zéro en suivant des instructions spécifiques.

### Statut
Reversi 1 - Terminé  
Reversi 2 - Terminé  

#### Dernière mise à jour
09/07/2020  
(README | 09/07/2020)