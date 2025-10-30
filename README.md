# TP de L2 ISIL - USTHB

Ce dépôt contient les travaux pratiques (TP) de deuxième année (L2) de la filière Informatique Systèmes d'Information et Logiciels (ISIL) à l'Université des Sciences et de la Technologie Houari Boumediene (USTHB).

## Structure du Dépôt

### Génie Logiciel (Software Engineering) - Java

#### GL2TP1
Travaux pratiques sur les concepts de base de la programmation orientée objet en Java.
- **Contenu**: Classes géométriques (Point, Cercle), calculs de formes
- **Packages**: 
  - `packageForme`: Définitions des formes géométriques
  - `packageCalcul`: Opérations de calcul sur les formes

#### TP1GL2
Projet NetBeans pour les premiers exercices de Génie Logiciel.

#### TP3-GL
Exercices avancés de programmation orientée objet.

#### TP4-GL
Travaux pratiques sur la maintenance et la réparation.
- **Thématiques**: Gestion de maintenance, réparations

#### TP5-GL
Projet complet de gestion d'atelier de réparation de véhicules.
- **Classes principales**:
  - `Vehicule`: Gestion des véhicules
  - `Atelier`: Gestion de l'atelier
  - `Reparation`: Gestion des réparations
  - `Technicien`: Gestion des techniciens
  - `Equipement`: Gestion des équipements
  - `Fournisseur`: Gestion des fournisseurs
  - `Piece`: Gestion des pièces détachées

#### TP6
Travaux pratiques complémentaires de Génie Logiciel.

### Programmation Web (Web Programming)

#### TP1, TP2, TP3
Exercices de développement web avec HTML, CSS et JavaScript.
- Création de pages web statiques
- Mise en forme avec CSS
- Projets: Pages de vacances, formulaires, etc.

### Théorie des Graphes (Graph Theory)

#### Projet Théorie de Graphe
Application Python interactive pour la visualisation et la manipulation de graphes.
- **Langage**: Python
- **Bibliothèques**: 
  - `tkinter`: Interface graphique
  - `matplotlib`: Visualisation
  - `networkx`: Manipulation de graphes
- **Fonctionnalités**:
  - Création de graphes orientés et non-orientés
  - Visualisation interactive
  - Sauvegarde et chargement de graphes

## Prérequis

### Pour les projets Java:
- Java JDK 8 ou supérieur
- NetBeans IDE (recommandé)
- Apache Ant (pour la compilation)

### Pour les projets Web:
- Navigateur web moderne (Chrome, Firefox, Edge)
- Éditeur de code (VS Code, Sublime Text, etc.)

### Pour le projet Python:
- Python 3.x
- Bibliothèques requises:
  ```bash
  pip install matplotlib networkx
  ```

## Compilation et Exécution

### Projets Java (avec NetBeans):
1. Ouvrir le projet dans NetBeans
2. Clic droit sur le projet → "Clean and Build"
3. Clic droit sur le projet → "Run"

### Projets Java (avec Ant):
```bash
cd <nom-du-tp>
ant compile
ant run
```

### Projets Web:
Ouvrir les fichiers `.html` directement dans un navigateur web.

### Projet Python:
```bash
cd "projet theorie de graph"
python myprojet.py
```

## Structure des Projets NetBeans

Chaque projet Java suit la structure standard NetBeans:
- `src/`: Code source
- `build/`: Fichiers compilés (non versionnés)
- `nbproject/`: Configuration NetBeans
- `build.xml`: Script de compilation Ant
- `manifest.mf`: Fichier manifest Java

## Auteur

Ce dépôt est maintenu par un étudiant de L2 ISIL à l'USTHB.

## Licence

Ces travaux pratiques sont destinés à un usage éducatif dans le cadre du cursus L2 ISIL à l'USTHB.

---

# L2 ISIL Practical Works - USTHB

This repository contains the practical works (TP) for the second year (L2) of the Computer Science - Information Systems and Software (ISIL) program at the University of Science and Technology Houari Boumediene (USTHB).

## Repository Structure

### Software Engineering - Java

Contains various Java projects covering object-oriented programming concepts, design patterns, and practical applications including geometric calculations, vehicle workshop management systems, and maintenance operations.

### Web Programming

HTML, CSS, and JavaScript exercises for web development fundamentals.

### Graph Theory

Interactive Python application for graph visualization and manipulation using NetworkX and Matplotlib.

## Requirements

- **Java Projects**: JDK 8+, NetBeans IDE
- **Web Projects**: Modern web browser
- **Python Project**: Python 3.x, matplotlib, networkx

## Usage

Refer to the French section above for detailed compilation and execution instructions.
