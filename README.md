# ASL Finger Spelling Practice
A java program that allows users to practice reading finger spelling. Points are added for every successful entry and points are subtracted for every unsuccessful entry inside the textbox
## In Action
The user must start the program by clicking on "New Word", clicking "New Word" at anytime will not deduct any points 
<img src="https://github.com/ImaginaryResources/ASL-Finger-Spelling-Practice/blob/master/gifs/start.gif">

A user can replay a word at no given cost, all points remain
<img src="https://github.com/ImaginaryResources/ASL-Finger-Spelling-Practice/blob/master/gifs/replay.gif">

When a user decides to give up one point is subtracted and the word is displayed
<img src="https://github.com/ImaginaryResources/ASL-Finger-Spelling-Practice/blob/master/gifs/giveup.gif">

When the user does enter the right word then one point is added to the score
<img src="https://github.com/ImaginaryResources/ASL-Finger-Spelling-Practice/blob/master/gifs/pointGain.gif">
## Behind the Code
This program reads many different words from text file "words.txt" inserting them into a string array. 

In this block of code a word is chosen at random and is broken down into separate characters, the image is called by the letter number in order to display the image
```java    
    for (int i = 0; i < words[randInt].length(); i++){
    
    //letter is a number from 0-25 corresponding to the position in the alphabet
    //97 is the beginning of alphabet in ASCII
    int letter = words[randInt].charAt(i) - 97;
    		
    if (time == i) //Displays one image every second
        g.drawImage(alphabet.get(letter), 300, 100, 500, 500, null);
    }
```

This block of code below adds every image from the resource folder into an image ArrayList. By having the name of each png file a letter it makes it easy to add each image into the ArrayList in order, instead of doing "alphabet.add(ImageIO.read(new File("res/a.png")));" for each letter of the alphabet

```java    
    for (int i = 0; i < 26; i++) {
	      char letter = (char) (97 + i);
	      String fileName = letter + ".png";
	      alphabet.add(ImageIO.read(new File("res/" + fileName)));
    }
```

