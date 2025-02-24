# Compte Rendu : Injection des Dépendances

## Ressources Vidéo


## Objectif

Ce projet illustre le principe d'**Injection des Dépendances** en Java en utilisant un **couplage faible**. Nous mettons en place une architecture modulaire en définissant des interfaces et leurs implémentations.

## Étapes de Réalisation

### Partie 1 : Création des Interfaces et Implémentations

#### 1. Création de l'interface `IDao`

Cette interface définit une méthode `getData()` qui retourne une donnée.

```java
public interface IDao {
    double getData();
}
```

#### 2. Implémentation de `IDao`

Nous créons une classe `DaoImpl` qui implémente l'interface `IDao`.

```java
public class DaoImpl implements IDao {
    @Override
    public double getData() {
        return Math.random() * 100; // Simulation de l'accès aux données
    }
}
```

#### 3. Création de l'interface `IMetier`

Cette interface définit une méthode `calcul()` qui effectue un traitement sur les données récupérées depuis `IDao`.

```java
public interface IMetier {
    double calcul();
}
```

#### 4. Implémentation de `IMetier` avec un couplage faible

Nous créons une classe `MetierImpl` qui dépend de `IDao` sans l'instancier directement.

```java
public class MetierImpl implements IMetier {
    private IDao dao;
    
    public void setDao(IDao dao) { // Injection de dépendance
        this.dao = dao;
    }
    
    @Override
    public double calcul() {
        return dao.getData() * 2; // Exemple de traitement
    }
}
```

## Partie 2 : Injection des Dépendances

### a. Par Instanciation Statique

Dans ce cas, nous créons manuellement les instances et nous injectons la dépendance dans le `main`.

```java
public class Presentation {
    public static void main(String[] args) {
        DaoImpl dao = new DaoImpl();
        MetierImpl metier = new MetierImpl();
        metier.setDao(dao); // Injection de la dépendance
        
        System.out.println("Résultat : " + metier.calcul());
    }
}
```

### b. Par Instanciation Dynamique

Nous utilisons la **réflexion** pour charger dynamiquement les classes et injecter les dépendances.

```java
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PresentationDynamic {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("config.txt"));
        String daoClassName = scanner.nextLine();
        scanner.close();
        
        Class<?> cDao = Class.forName(daoClassName);
        IDao dao = (IDao) cDao.getDeclaredConstructor().newInstance();
        
        MetierImpl metier = new MetierImpl();
        metier.setDao(dao);
        
        System.out.println("Résultat : " + metier.calcul());
    }
}
```

Fichier `config.txt` :

```
DaoImpl
```

## Conclusion

Nous avons implémenté **l'injection des dépendances** de deux manières :

1. **Statique** (manuelle dans le `main`).
2. **Dynamique** (chargement par réflexion depuis un fichier de configuration).

Cette approche améliore la flexibilité et la maintenabilité du code en respectant le principe de **couplage faible**.

---

🎯 **Avantages de l'Injection des Dépendances :**

- Améliore la **modularité**.
- Facilite les **tests unitaires**.
- Permet de changer facilement l'implémentation sans modifier le code métier.

🚀 **Technologies utilisées :** Java SE, POO, Réflexion.


