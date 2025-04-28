Le programme BellmanFord peut se lancer à l'aide du fichier Test qui lira dans chaque fichier de test donné (JeuxDeTestEtOracle.txt, InstructionsQuestion2.txt, JeuxDeTestPartitionsEtCategories.txt, et JeuxDeTestLimites.txt) les différents jeux de tests mis à disposition. Chaque jeux de test se présentent sous la forme suivante : 
>Nombre de sommet Nombre d'arcs Sommet de départ Graphe(composé du nombre d'arcs et séparé par _ entre les arcs et par des virgules entre les nombres ; ainsi l'arc qui va du sommet 0 au sommet 1 de poids 4 s'écris 0,1,4) = Oracle(composé du nombre de sommet, aussi séparé par _)

Par exemple le graphe donné à la question 2 ressemble à ceci:
>6 9 0 0,1,6_0,2,4_0,3,5_1,4,-1_2,1,-2_2,4,3_3,2,-2_3,5,-1_4,5,3 = 0_1_3_5_0_3

Pour les tests de mutations, il suffit de remplacer la ligne 56 du programme Test, que j'ai marqué d'un commentaire, par BellmanFord_mutants graph = new BellmanFord_mutants(v, e);. Il est inscrit dans ce fichier BellmanFord_mutants par des commentaires toutes les lignes où les critères de mutations ont étés appliqués, afin de garder une bonne visibilité dans le programme BellmanFord.
