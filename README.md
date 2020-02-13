# Lyght
Lyght is a Java 2D game engine, that supports Windows, MacOS, Linux, and Solaris.
Please submit bugs, errors, and glitches at the issues tab.

Enjoy using Lyght!

# Getting started
I expect you to have a solid unserstanding of [Java](https://www.java.com/en/download/ "Download Java") and use [IntelliJ IDEA](https://www.jetbrains.com/idea/ "Download IntelliJ IDEA").

Go to the releases page and download a version jar. Create a new project (Java 1.8 or above) and go to the File tab at the top and click Project Structure. Go to Libraries and add the jar file you previously downloaded. Now make a new class.

You want your class to extend `net.lyght.main.Game`, like:

```java
public class Example extends Game {
```

You would like your project to start, so do:

```java
public static void main(String[] args){
  Lyght.lyght.main(new Example()); //Makes Lyght start the project
}
```

You want to make a constructor which will make an Entity, and make it move with the W, A, S, D keys.

```java
public Example(){
  Entity entity = new Entity(10, 10, 100, 50, Color.cyan); //Makes a new Entity at coordinates 10, 10, it's width is 100, height is 50, it's color is cyan
  entity.addScript(new Movement(display, true)); //Adds the Movement script to it, and enables moving with keys.
  container.add(entity); //Adds the Entity to the game
}
```

The done game:
```java
import net.lyght.entity.Entity;
import net.lyght.main.Game;
import net.lyght.main.Lyght;
import net.lyght.script.physics.Movement;
import net.lyght.util.Color;

public class Example extends Game {

    public Example(){
        Entity entity = new Entity(10, 10, 100, 50, Color.cyan); //Makes a new Entity at coordinates 10, 10, it's width is 100, height is 50, it's color is cyan
        entity.addScript(new Movement(display, true)); //Adds the Movement script to it, and enables moving with keys.
        container.add(entity); //Adds the Entity to the game
    }

    public static void main(String[] args){
        Lyght.lyght.main(new Example()); //Makes Lyght start the project
    }

}
```

Hope it helped. If you want to know more about Lyght, you can always check the code.
