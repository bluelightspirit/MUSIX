# MUSIX
## A Java game displayed in JFrame where the player tries to guess the song name by being given 4 different options to earn as many points as possible, similar to the game SongTrivia 2 from https://songtrivia2.io/play/online

## How does anyone play?

Anyone should be aiming to see the game that looks like this:\
![musix_ready-to-play](https://user-images.githubusercontent.com/22280271/213863759-61ce9e86-58ae-42f0-811a-905fe70fd7e8.png)

First, the player must download the game and it's files, where [MUSIX.java](https://github.com/bluelightspirit/MUSIX/blob/main/MUSIX/MUSIX.java) and the 10 songs must be in the same folder. To do that, it is suggested to download the ZIP file from [Releases](https://github.com/bluelightspirit/MUSIX/releases) or copy the repo from a console like Git. It is also suggested to double check if the [MUSIX](https://github.com/bluelightspirit/MUSIX/tree/main/MUSIX) folder has 10 songs and that [MUSIX.java](https://github.com/bluelightspirit/MUSIX/blob/main/MUSIX/MUSIX.java) is in the same folder of the 10 songs. Then, [MUSIX.java](https://github.com/bluelightspirit/MUSIX/blob/main/MUSIX/MUSIX.java) should be compiled. After that, [MUSIX.java](https://github.com/bluelightspirit/MUSIX/blob/main/MUSIX/MUSIX.java)'s main method should be ran using any IDE that supports Java such as [IntelliJ](https://www.jetbrains.com/idea/download/) or [BlueJ](https://www.bluej.org/).

Note: If the player wants to try to have fun or they want to make me happier by playing the game as intended by not having an answer sheet, they shouldn't open the credits folder until after they want the link to all of the songs played.

After that, to play this game, the player must click "Yes" then "Yes. I agree 100%" if they want to play at the beginning of the game to start the game, confirming their audio is on and they are really sure they want to play, which should look like this:\
![image](https://user-images.githubusercontent.com/22280271/213866461-7b6edaec-8010-4f5c-9adc-e9c824447f9f.png)\
After that, the program will output ~20-30 second audio clip where the player shall select any of the 4 options as their guess. If they get it right, they will gain a point, or 3 points, or 5 points, depending on their streak they currently have. If they get it wrong, their streak ends. To use a hint, the player must have at least 6 points to have the option to remove two of the incorrect options for the songs, which will take away 6 points from the player's normal game points.

The game should look like this if they pressed "Yes. I agree 100%":\
![question1_musix](https://user-images.githubusercontent.com/22280271/213863808-755ce5b8-448b-4539-a3d1-a4e37f4b6819.png)


## How does it work?

This is based on using `JFrame` & `JOptionPane` from Java. As soon as a question starts, a ~20-30 second audio clip starts playing with a question of what the song is. As soon as the player chooses an answer from the top 4 options, it will add points to the player if they're correct and will stop the audio clip and play a new song's audio clip. If they have a hint available from accumulating 6 or more points, the hint button will appear below the top 4 options. Activating the hint button will take away 6 points from the player. 

After they get through 10 questions, it will ask if they want to play again. If so, the game will record their Lifetime Points for all games they played in the one session the JFrame was on up to infinity. If not, it will close the game.

## How do streaks work?

If the player obtains a streak with 0-2 songs correct in a row, they will gain only 1 point per question correct.
If the player obtains a streak with 3-5 songs correct in a row, they will gain 3 points rather than 1.
If the player obtains a streak with 6+ songs correct in a row, they will gain 5 points rather than 3.
If the player get one song wrong at any time during their streak, their streak ends.

## How does hints work?

As soon as the player acculumates at least 6 points, the hint button will appear as an option to use as the hidden 5th button that the player may have noticed was blank at the first few questions, taking away 6 normal game points from the player.

## Why is the game called MUSIX?

Because as soon as the player obtains a streak with 6 or more correct songs in a row, they will gain the most points in the game for streaking. It is also because they need 6 or more points to use up a hint.

Although an earlier iteration made when I was a senior in high school in early May 2022 had a streak of 6+ songs correct in a row allowing the player to gain 6 points, it was nerfed due to how the player could press "HINT" for every question and would make it 50/50 rather than 25/25/25/25. It was nerfed in mid May 2022.

## What did I learn?

1) How to use JFrame.
2) How to use JOptionPane and give the options functionality.
3) How to use create buttons and use ActionListeners with them.
4) How to hide and show buttons.
5) How to sent text fonts of buttons in Java.
6) How to play and stop audio in Java.
7) How to display a message dialog to the user.
8) How to make a window popup close after any button press.
9) How to properly make a functional game using JFrame by changing the text, questions, audio clip, amount of points, and if hint button should appear per question, using all of those skills I learned above in addition with my previous knowledge on how to add an amount of points.

## What goal(s) did I accomplish?

I created a game where the player tries to guess what song they're listening to with the goal of obtaining as many points as possible!

## Compiling

This program uses solely Java to compile.
