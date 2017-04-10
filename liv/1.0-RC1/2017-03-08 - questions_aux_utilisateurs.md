
# Questions aux utilisateurs - V1.0-RC1

[P1] question prioritaire
[P2] question non prioritaire
[-] question peu importante

## Généralités
* [P1] Le vocable "produit" convient-il pour "rouleau" ?

  *réponse workshape: oui*

* [P1] Le vocable "stock" (entrer dans le stock, sortir du stock) convient-il ?

  *réponse workshape: oui*

* [P1] Y a-t-il des textes qui sont non compréhensibles ?

  *réponse workshape: oui, remplacer "Temps total hors gel" par "Temps total décongelé"*

* [P1] Manque-t-il des champs pour représenter les rouleaux ?

  *réponse workshape: 'rouleau fini'*

* [P1] Le choix des écrans sans scrolling mais épousant la taille de l'écran est-il le bon ?
* [P1] Champs trop grands ? Champs trop petits ?

## Par écran

### Ecran principal
* [P2] Le bouton "Scanner un produit" est-il bien placé ? Pas trop haut ?

  *réponse workshape: oui*

* [-] Le bouton "Lister les produit" est-il bien placé ? Pas trop petit ?

  *réponse workshape: oui*

* [-] Le bouton "Configurer" est-il bien placé ? Pas trop près des autres ?

  *réponse workshape: oui*

### Ecrans de saisie d'un produit
* [P1] L'enchainement "Sortir/entrer dans le stock", "Historique", "Note", "Détails" est-il le bon ?

  *réponse workshape: Meilleur enchainement avec "Sortir/entrer dans le stock", Détails", Historique", "Note"*

* [P2] Un rouleau peut être fini ou non. Est-ce un champ à ajouter ?
* [P1] Faut-il protéger l'écran de détail de la saisie ? Par exemple ajouter une icone pour modifier (éventuellement protégée par un mot de passe) ?

  *réponse workshape: oui, un bouton de même nature que celui pour ajouter un produit (le petit +)*

* [P1] Faut-il pouvoir supprimer une pièce dans l'écran de détail ? Par exemple ajouter une icone poubelle (éventuellement protégée par un mot de passe) ? Le rouleau ne serait pas supprimé de la base de données, mais simplement non vu par le logiciel sur smartphone.

### Ecrans historique
* [P1] Les éléments montrés à l'écran sont-ils les bons ?

### Ecran "Liste de tous les produits"
* Quel champ faut-il montrer sur cette liste ? (3 max)

  *réponse workshape: Champs visibles : "Nom", "Longueur actuelle"

* Faut-il cacher certains rouleaux ? Sur quels critères ?

  *réponse workshape: Il faudrait cacher les rouleaux finis, déterminé soit par une icône soit lorsque longueur actuelle <= 0 mètre*

* Comment classer la liste des rouleaux ? Aujourd'hui ils sont affichés par ordre chronologique d'ajout dans la base de données.

  *réponse workshape: Il serait plus simple pour l'utilisateur que les rouleaux soit trier par ordre antéchronologique (du plus récent au plus ancien)*

### Ecrans "Sortir du stock / Entrer dans le stock / Détail"
* Manque-t-il des informations sur ces écrans ?
* Y a-t-il des informations en trop sur ces écrans ?

### Futur écran de recherche d'un rouleau
* Les critères pour effectuer une recherche sont-ils fixes ? Par exemple, ferez-vous des recherches toujours sur les mêmes critères ?

### Futur écran de stockage de photo des rouleaux
* Faut-il un tel écran ?
