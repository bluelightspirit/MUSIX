# MUSIX
## A Java game displayed in JFrame where a player tries to guess the song name by being given 4 different options to earn as many points as possible, based on the game SongTrivia 2 from https://songtrivia2.io/play/online

## How does anyone play?

To play this game, a user must click "Yes" to if they want to play at the beginning of the game to start the game, confirming their audio is on and they are really sure they want to play. After that, they have to listen up to a 10-15 second audio clip and select any of the 4 options. If they get it right, they will gain a point, or 3 points, or 5 points, depending on their streak they currently have. If they get it wrong, their streak ends. To use a hint, the user must have at least 6 points to have the option to remove two of the incorrect options for the songs, which will take away 6 points from the user's normal game points.

## How does it work?

This is based on using `JFrame` & `JOptionPane` from Java. As soon as a question starts, a 10-15 second audio clip starts playing with a question of what the song is. As soon as the user chooses an answer from the top 4 options, it will add points to the user if they're correct and will stop the audio clip and play a new song's audio clip. If they have a hint available from accumulating 6 or more points, the hint button will appear below the top 4 options. Activating the hint button will take away 6 points from the user. 

After they get through 10 questions, it will ask if they want to play again. If so, the game will record their Lifetime Points for all games they played in the one session the JFrame was on up to infinity. If not, it will close the game.

## How do streaks work?

If you obtain a streak with 0-2 songs correct in a row, you will gain only 1 point per question correct.
If you obtain a streak with 3-5 songs correct in a row, you will gain 3 points rather than 1.
If you obtain a streak with 6+ songs correct in a row, you will gain 5 points rather than 3.
If you get one song wrong at any time during your streak, your streak ends.

## How does hints work?

As soon as the user acculumates at least 6 points, the hint button will appear as an option to use as the hidden 5th button that the user may have noticed was blank at the first few questions, taking away 6 normal game points from the user.

## Why is the game called MUSIX?

Because as soon as you obtain a streak with 6 or more correct songs in a row, you will gain the most points in the game for streaking. It is also because you need 6 or more points to use up a hint.

Although an earlier iteration made in early May 2022 (when I was a senior at high school) had a streak of 6+ songs correct in a row allowing the user to gain 6 points, it was nerfed due to how the user could press "HINT" for every question and would make it 50/50 rather than 25/25/25/25. It was nerfed in mid May 2022.

## Compiling

This program uses solely Java to compile.
