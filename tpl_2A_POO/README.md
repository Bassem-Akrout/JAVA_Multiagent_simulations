# TPL 2A POO - 2023-24

Les ressources distribuées contiennent:

- le sujet
- une librairie d'affichage graphique d'un simulateur (lib/gui.jar) et sa documentation (doc/index.html)
- un fichier de démonstration du simulateur (src/TestInvader.java)

Une mini-introdution à la notation UML des diagrammes de classes est disponibe pour faciliter la lecture du sujet ([Fiche 7](https://programmation-orientee-objet.pages.ensimag.fr/poo/resources/fiches/07-UML/)).


## Compilation & exécution
### Avec un makefile? C'est plus facile non ? 
On vous donne la chance de choisir ***les paramètres*** pour les automates cellulaires , le jeu de Conway et le modèle de Schelling ! 

On vous a fourni un MakeFile à la racine ! 
L'éxecution est assez facile .Voici comment la magie se passe: 
***make*** dans le terminal: compile tous les jeux et démos(avec EventManager et sans aussi ! Intéréssant tout ça ) ! 

***Maintenant pour exécuter un jeu précis*** : Disons que je veux exécuter Boids mais avec l'EventManager. Dans le MakeFile fourni vous trouvez la commande make à exécuter : ***runTestBoidsEvent*** il suffit de taper dans le terminal et à la racine du projet ***make runTestBoidsEvent***  ! 
Cette simple démo s'applique à tous les autres jeux du projet ! 

Profitez et n'oubliez pas de jeter un coup d'oeil sur le ***Rapport*** pour savoir plus à propos de notre structuration .


### IDE Idea Intellij
- créer un nouveau projet:
    - menu *File/New Project*
    - si le répertoire distribué est dans "~/Ensimag/2A/POO/TPL_2A_POO", alors paramétrer les champs *Name* avec "TPL_2A_POO" et *Location* avec "~/Ensimag/2A/POO/"
- configurer l'utilisation de la librairie
    - menu *File/Project Structure* puis *Projet setting/Modules*
    - clicker sur(*Add* puis "JARs & Directories" et sélectionner ~/Ensimag/2A/POO/TPL_2A_POO/lib
    - voir ici pour plus d'aide: https://stackoverflow.com/questions/1051640/correct-way-to-add-external-jars-lib-jar-to-an-intellij-idea-project
- vous pouvez bien sûr utiliser git via l'interface d'idea Intellij

### IDE VS Code
- dans "~/Ensimag/2A/POO/TPL_2A_POO", lancer *code ."
- si vous avez installé les bonnes extensions java (exécution, debogage...) il est possible que tout fonctionne sans rien faire de spécial.
- s'il ne trouve pas la librairie, vous devez alors créer un vrai "projet" et configurer l'import du .jar.
- pas vraiment d'aide pour ça, vous trouverez
- vous pouvez bien sûr utiliser git via l'interface de VS code
