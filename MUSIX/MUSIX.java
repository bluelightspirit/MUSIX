import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.*;
import java.io.*;
import java.net.URL;
import java.io.IOException;;

/**
 * MUSIX
 *
 * @author Gary Young
 * 
 * Originally finished on 05/13/2022
 * 01/14/2023 bugfix: now properly stops playing music immediately after answering 10th question
 * 01/14/2023 addition 1: added psvm (String[] args) linking to MUSIX() so user doesn't have to create an object on start anymore
 * 01/14/2023 addition 2: added "Ready to play?" to the top of the game when started at top of public MUSIX()
 * 01/14/2023 addition 3: added ready boolean to search for a yes in JOptionPane's "are you really sure" question
 *                        in public void actionPerformed(ActionEvent e)'s question 0
 * 
 * @version (1/14/2023)
 * MUSIX plays a game in JFrame where the user decides
 * what song the song is from a 10-15 second clip. 
 */

// the MUSIX class that sets up the interface in JFrame for the user to play in,
// where the game is run from MUSIX() rather than public static void main (String[] args)
// purpose: to have the user listen to a 10-15 second clip from a song for a question,
// then for the user to guess the answer of what song it is,
// and if the user gets a streak of 6 or more, they get 5 points added
// which is why the title is MUSIX.
// also, if the user gets a streak of 3-5, they get 3 points added.
// the user can also be given a hint, eliminating 2 potential correct answers
// which costs 6 points total.
public class MUSIX extends JFrame implements ActionListener{
    // create five buttons
    private JButton option1,option2,option3,option4,hint;
    // question header at top of JFrame
    private JLabel I1;
    // points, winstreak, lifePoints, & lifePoints2
    // footer at bottom of JFrame
    private JLabel I3;
    // question number
    private int question = 0;
    // all points earned in-game INCLUDING reset games
    // & NOT INCLUDING hint subtraction
    private int lifePoints = 0;
    // all points earned in-game NOT INCLUDING reset games
    // & NOT INCLUDING hint subtraction
    private int lifePoints2 = 0;
    // points earned in-game INCLUDING hint subtraction
    private int points = 0;
    // winstreak
    private int streak = 0;
    // to change title at the top
    private String song = "";
    // to store song's audio
    private Clip clip;
    // song name (in .wav) to play
    private static String filePath;
    
    // buttons, header, & footer without functionality
    public MUSIX() {
        // close JFrame if click on top right X button
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // make not resizable
        setResizable(false);
        // header
        I1 = new JLabel("Ready to play?");
        I1.setHorizontalAlignment(JLabel.CENTER);
        // create button1
        option1 = new JButton();
        // set button background color (blue)
        option1.setBackground(Color.blue);
        // set button text color (white)
        option1.setForeground(Color.white);
        // move text to be left on the button
        option1.setHorizontalAlignment(SwingConstants.LEFT);
        // set font of the button's text
        option1.setFont(new Font("Eras Demi ITC", Font.PLAIN, 11));
        // change text of the actual button
        option1.setText("Yes");
        // make button click do something
        option1.addActionListener(this);
        // on hover change button color and text color
        option1.addMouseListener(new java.awt.event.MouseAdapter() {
            // when hovering over the button
            public void mouseEntered(java.awt.event.MouseEvent e) {
                // tell java to look at the button
                JButton j = (JButton) e.getSource();
                // set button background to white
                option1.setBackground(Color.white);
                // set button text to black
                option1.setForeground(Color.black);
            }
            // after hovering button
            public void mouseExited(java.awt.event.MouseEvent e) {
                // tell java to look at the button
                JButton j = (JButton) e.getSource();
                // set button background to blue
                option1.setBackground(Color.blue);
                // set button text to white
                option1.setForeground(Color.white);
            }
        });
        // button2
        // create button2
        option2 = new JButton();
        // set button background color (hex code (63,167,205))
        option2.setBackground(new java.awt.Color(63,167,205));
        // set button text color (white)
        option2.setForeground(Color.white);
        // move text to be left on the button
        option2.setHorizontalAlignment(SwingConstants.LEFT);
        // set text of the actual button
        option2.setText("Yes");
        // set font of the button's text
        option2.setFont(new Font("Eras Demi ITC", Font.PLAIN, 11));
        // make button click do something
        option2.addActionListener(this);
        // on hover change button color and text color
        option2.addMouseListener(new java.awt.event.MouseAdapter() {
            // when hovering over the button
            public void mouseEntered(java.awt.event.MouseEvent e) {
                // tell java to look at the button
                JButton j = (JButton) e.getSource();
                // set button background to white
                option2.setBackground(Color.white);
                // set button text to black
                option2.setForeground(Color.black);
            }
            // after hovering button
            public void mouseExited(java.awt.event.MouseEvent e) {
                // tell java to look at the button
                JButton j = (JButton) e.getSource();
                // set button background to hex code (63,167,205)
                option2.setBackground(new java.awt.Color(63,167,205));
                // set button text to white
                option2.setForeground(Color.white);
            }
        });
        // button3
        // create button3
        option3 = new JButton();
        // set button background color (hex code (182,13,13)) 
        option3.setBackground(new java.awt.Color(182,13,13));
        // set button text color (white)
        option3.setForeground(Color.white);
        // move text to be left on the button
        option3.setHorizontalAlignment(SwingConstants.LEFT);
        // set text of the actual button
        option3.setText("Yes");
        // set font of the button's text
        option3.setFont(new Font("Eras Demi ITC", Font.PLAIN, 11));
        // make button click do something
        option3.addActionListener(this);
        // on hover change button color and text color
        option3.addMouseListener(new java.awt.event.MouseAdapter() {
            // when hovering over the button
            public void mouseEntered(java.awt.event.MouseEvent e) {
                // tell java to look at the button
                JButton j = (JButton) e.getSource();
                // set button background to white
                option3.setBackground(Color.white);
                // set button text to black
                option3.setForeground(Color.black);
            }
            // after hovering the button
            public void mouseExited(java.awt.event.MouseEvent e) {
                // tell java to look at the button
                JButton j = (JButton) e.getSource();
                // set button background to (182,13,13)
                option3.setBackground(new java.awt.Color(182,13,13));
                // set button text to white
                option3.setForeground(Color.white);
            }
        });
        // button4
        // create button4
        option4 = new JButton();
        // set button background color (hex code (232,86,232)) 
        option4.setBackground(new java.awt.Color(232,86,232));
        // set button text color (white)
        option4.setForeground(Color.white);
        // move text to be left on the button
        option4.setHorizontalAlignment(SwingConstants.LEFT);
        // set text of the actual button
        option4.setText("Yes");
        // set font of the button's text
        option4.setFont(new Font("Eras Demi ITC", Font.PLAIN, 11));
        // make button click do something
        option4.addActionListener(this);
        // on hover change button color and text color
        option4.addMouseListener(new java.awt.event.MouseAdapter() {
            // before hovering button
            public void mouseEntered(java.awt.event.MouseEvent e) {
                // tell java to look at the button
                JButton j = (JButton) e.getSource();
                // set button background to white
                option4.setBackground(Color.white);
                // set button text to black
                option4.setForeground(Color.black);
            }
            // after hovering button
            public void mouseExited(java.awt.event.MouseEvent e) {
                // tell java to look at the button
                JButton j = (JButton) e.getSource();
                // set button background to hex code (232,86,232)
                option4.setBackground(new java.awt.Color(232,86,232));
                // set button text to white
                option4.setForeground(Color.white);
            }
        });
        // hint
        // create hint button
        hint = new JButton();
        // set button background color (blue) 
        hint.setBackground(Color.blue);
        // set button text color (orange)
        hint.setForeground(Color.orange);
        // move text to be right on the button
        hint.setHorizontalAlignment(SwingConstants.RIGHT);
        // set text of the actual button
        hint.setText("NO. EXIT.");
        // set font of the button's text
        hint.setFont(new Font("Eras Demi ITC", Font.PLAIN, 11));
        // make button click do something
        hint.addActionListener(this);
        // on hover change button color and text color
        hint.addMouseListener(new java.awt.event.MouseAdapter() {
            // before hovering button
            public void mouseEntered(java.awt.event.MouseEvent e) {
                // tell java to look at the button
                JButton j = (JButton) e.getSource();
                // set button background to white
                hint.setBackground(Color.white);
                // set button text to black
                hint.setForeground(Color.black);
            }
            // after hovering button
            public void mouseExited(java.awt.event.MouseEvent e) {
                // tell java to look at the button
                JButton j = (JButton) e.getSource();
                // set button background to blue
                hint.setBackground(Color.blue);
                // set button text to orange
                hint.setForeground(Color.orange);
            }
        });
        // create footer
        I3 = new JLabel("Points: " + points + "   " + "Streak: " + streak + "   Lifetime Points (In-Game): " + lifePoints + "   Lifetime Points (All): " + lifePoints2);
        // create the format for the buttons
        // make header not interfere with panels and exist
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(I1, BorderLayout.PAGE_START);
        // the panels for the buttons themselves
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,1));
        panel.add(option1);
        panel.add(option2);
        panel.add(option3);
        panel.add(option4);
        panel.add(hint);
        // make footer not interfere with panels and exist
        c.add(panel,BorderLayout.CENTER);
        c.add(I3,BorderLayout.PAGE_END);
        // set title of application
        setTitle("MUSIX");
        // set initial size of application
        setSize(500,400);
        // make application and things within the application visible
        setVisible(true);
    }
    // set visibility of hint button based on points
    public void hint(){
        // if question is 11 make hint button visibility true no matter what
        if (question==11){
            // make hint button visible
            hint.setVisible(true);
        }
        // if points is greater or equal to 6
        if (points>=6){
            // make hint button visible
            hint.setVisible(true);
        }
        // if points is less than 6 and question is not 11
        else if (points<6 && !(question==11)){
            // make hint button not visible
            hint.setVisible(false);
        }
    }
    // end streak, take away no points, stop audio, move onto next question
    public void lose(){
        // lose with winstreak of 3+ end message
        if (streak>=3){
            // send message wrong to user
            JOptionPane.showMessageDialog(null,"wrong! streak of " + streak + " ended!");
            // set streak to 0
            streak=0;
            // go to question
            question++;
            // update footer
            I3.setText("Points: " + points + "   " + "Streak: " + streak + "   Lifetime Points (In-Game): " + lifePoints + "   Lifetime Points (All): " + lifePoints2);
            // make hint button visible if theres enough points earned by user
            hint();
            // if question is 11, ignore streak and go to gameOver message
            if (question==11){
                // go to gameOver message
                question0();
                // make hint button visible
                hint.setVisible(true);
             }
            // double check everything updates
            revalidate();
        }
        // lose with no streak message
        else if (streak==0){
            // send message wrong to user
            JOptionPane.showMessageDialog(null,"wrong! stay on your 0 streak!", "incorrect :(", JOptionPane.ERROR_MESSAGE);
            // go to next question
            question++;
            // update footer
            I3.setText("Points: " + points + "   " + "Streak: " + streak + "   Lifetime Points (In-Game): " + lifePoints + "   Lifetime Points (All): " + lifePoints2);
            // make hint button visible if theres enough points earned by user
            hint();
            // if question is 11, ignore streak and go to gameOver message
            if (question==11){
                // go to gameOver message
                question0();
                // make hint button visible
                hint.setVisible(true);
            }
            // double check everything updates
            revalidate();
        }
        // lose with streak of 1-2 message
        else if (streak<3){
            // send message wrong to user
            JOptionPane.showMessageDialog(null,"wrong! your 0-point streak is over!\nstreak before: " + streak, "incorrect :(", JOptionPane.ERROR_MESSAGE);
            // go to next question
            question++;
            // set streak to 0
            streak=0;
            // update footer
            I3.setText("Points: " + points + "   " + "Streak: " + streak + "   Lifetime Points (In-Game): " + lifePoints + "   Lifetime Points (All): " + lifePoints2);
            // make hint button visible if theres enough points earned by user
            hint();
            // if question is 11, ignore streak and go to gameOver message
            if (question==11){
                // stop music
                clip.stop();
                // go to gameOver message
                question0();
                // make hint button visible
                hint.setVisible(true);
            }
            // double check everything updates
            revalidate();
        }
    }
    // add to streak, add points, add extra points if on a great enough streak, stop audio, move onto next question
    public void win(){
        // win with streak of 6 or greater message
        if (streak>=6){
            // add 5 points to all types of points
            points=points+5;
            lifePoints=lifePoints+5;
            lifePoints2=lifePoints2+5;
            // add to streak
            streak++;
            // go to next question
            question++;
            // update footer
            I3.setText("Points: " + points + "   " + "Streak: " + streak + "   Lifetime Points (In-Game): " + lifePoints + "   Lifetime Points (All): " + lifePoints2);
            // check if hint appears or not
            hint();
            // send message to user they won
            JOptionPane.showMessageDialog(null,"u win! +5 points! \ncurrent streak: " + streak + "\nkeep going!", "correct :)", JOptionPane.PLAIN_MESSAGE); 
            // if question is 11, ignore streak and go to gameOver message
            if (question==11){
                // go to gameOver message
                question0();
                // make hint button visible if theres enough points earned by user
                hint.setVisible(true);
            }
            // double check everything updates
            revalidate();
        }
        // win with streak of 3 or greater message
        else if (streak>=3){
            // add 3 points to all types of points
            points=points+3;
            lifePoints=lifePoints+3;
            lifePoints2=lifePoints2+3;
            // add to streak
            streak++;
            // go to next question
            question++;
            // update footer
            I3.setText("Points: " + points + "   " + "Streak: " + streak + "   Lifetime Points (In-Game): " + lifePoints + "   Lifetime Points (All): " + lifePoints2);
            // check if hint appears or not
            hint();
            // send message to user they won
            JOptionPane.showMessageDialog(null,"u win! +3 points! \ncurrent streak: " + streak + "\nkeep going!", "correct :)", JOptionPane.PLAIN_MESSAGE); 
            // if question is 11, ignore streak and go to gameOver message
            if (question==11){
                // stop music
                clip.stop();
                // go to gameOver message
                question0();
                // make hint button visible if theres enough points earned by user
                hint.setVisible(true);
            }
            // double check everything updates
            revalidate();
        }
        // win with streak of 0-2 message
        else if (streak<3){
            // add to streak
            streak++;
            // go to next question
            question++;
            // add 1 point to all types of points
            points++;
            lifePoints++;
            lifePoints2++;
            // update footer
            I3.setText("Points: " + points + "   " + "Streak: " + streak + "   Lifetime Points (In-Game): " + lifePoints + "   Lifetime Points (All): " + lifePoints2);
            // check if hint appears or not
            hint();
            // send message to user they won
            JOptionPane.showMessageDialog(null,"u win! +1 point! \ncurrent streak: " + streak, "correct :)", JOptionPane.PLAIN_MESSAGE);
            // if question is 11, ignore streak and go to gameOver message
            if (question==11){
                // go to gameOver message
                question0();
                // make hint button visible if theres enough points earned by user
                hint.setVisible(true);
            }
            // double check everything updates
            revalidate();
        }
    }
    // make all buttons visible again
    // except hint situationally, thus the hint() call
    // most useful after hint is used AND button is pressed
    public void setVisible(){
        // make hint button visible if theres enough points earned by user
        hint();
        // make option1 visible
        option1.setVisible(true);
        // make option2 visible
        option2.setVisible(true);
        // make option3 visible
        option3.setVisible(true);
        // make option4 visible
        option4.setVisible(true);
    }
    // play audio
    public void playMusic(){
        try {
             // get the file (.wav)
             URL url = this.getClass().getClassLoader().getResource(filePath);
             // open an audio input stream
             AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
             // get a sound clip resource
             clip = AudioSystem.getClip();
             // open audio clip and load samples from the audio input stream
             clip.open(audioIn);
             // start the music (audio)
             clip.start();
         // if audio can't be played diagnose the exception
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    // set of questions
    // question before the actual game questions
    // purpose: check if player is ready to play
    public void question0(){
        // reset points
        reset();
        // make option1 visible
        option1.setVisible(true);
        // make option2 visible
        option2.setVisible(true);
        // make option3 visible
        option3.setVisible(true);
        // make option4 visible
        option4.setVisible(true);
        // make hint visible
        hint.setVisible(true);
        // set option1 text to "Yes"
        option1.setText("Yes");
        // set option2 text to "Yes"
        option2.setText("Yes");
        // set option3 text to "Yes"
        option3.setText("Yes");
        // set option4 text to "Yes"
        option4.setText("Yes");
        // set hint text to "NO. EXIT"
        hint.setText("NO. EXIT.");
    }
    // 1st question setup
    public void question1(){
        // option1 is correct
        // hint to make only 1||4 correct
        // set song to Robots and Aliens
        filePath = "song1.wav";
        // play the song
        playMusic();
        // change header
        I1.setText("Question 1: What song is this?");
        // make necessary buttons visible
        setVisible();
        // change text of buttons
        option1.setText("<html>Powerful Beat<br />By penguinmusics</html>");
        option2.setText("<html>Commander Impulse<br />By DivKid</html>");
        option3.setText("<html>Black Mass<br />Brian Bolger</html>");
        option4.setText("<html>Shadoma<br />By Mini Vandals</html>");
        // make hint button show "HINT" (may have changed from question0() call)
        hint.setText("HINT");
        
    }
    // 2nd question setup
    public void question2(){
        // option2 is correct
        // hint to make only 2||3 correct
        // stop previous song
        clip.stop();
        // set song to Believe
        filePath = "song2.wav";
        // play the song
        playMusic();
        // change header
        I1.setText("Question 2: What song is this?");
        // make necessary buttons visible
        setVisible();
        // change text of buttons
        option1.setText("<html>Mountain Path<br />by Magnetic Trailer</html>");
        option2.setText("<html>Believe<br />By NEFFEX</html>");
        option3.setText("<html>Tell Me That I Can't<br />By NEFFEX</html>");
        option4.setText("<html>Natural Light<br />By Chris Haugen</html>");
    }
    // 3rd question setup
    public void question3(){
        // option4 is correct
        // hint to make only 1||4 correct
        // stop previous song
        clip.stop();
        // set song to Stomping Rock (Four Shots)
        filePath = "song3.wav";
        // play the song
        playMusic();
        // change header
        I1.setText("Question 3: What song is this?");
        // make necessary buttons visible
        setVisible();
        // change text of buttons
        option1.setText("<html>Anthem of Victory<br />By ZakharValaha</html>");
        option2.setText("<html>The Spring - I Allegro - Four Seasons<br />By Antonio Vivaldi</html>");
        option3.setText("<html>Egmont, Op. 84 - Overture<br />By Beethoven Ludwig Van</html>");
        option4.setText("<html>Stomping Rock (Four Shots)<br />By AlexGrohl</html>");
    }
    // 4th question setup
    public void question4(){
        // option3 is correct
        // hint to make only 2||3 correct
        // stop previous song
        clip.stop();
        // set song to Spirit Blossom
        filePath = "song4.wav";
        // play the song
        playMusic();
        // change header
        I1.setText("Question 4: What song is this?");
        // make necessary buttons visible
        setVisible();
        // change text of buttons
        option1.setText("<html>Think About You<br />By Kygo</html>");
        option2.setText("<html>Late at Night<br />By Roddy Ricch</html>");
        option3.setText("<html>Spirit Blossom<br />By RomanBelov</html>");
        option4.setText("<html>Happy<br />By Pharrell Williams</html>");
    }
    // 5th question setup
    public void question5(){
        // option4 is correct
        // hint to make only 1||4 correct
        // stop previous song
        clip.stop();
        // set song to Both of Us
        filePath = "song5.wav";
        // play the song
        playMusic();
        // change header
        I1.setText("Question 5: What song is this?");
        // make necessary buttons visible
        setVisible();
        // change text of buttons
        option1.setText("<html>All Roads Lead Home<br />By Zach Bryan</html>");
        option2.setText("<html>Every Breath You Take<br />By The Police</html>");
        option3.setText("<html>All Roads Lead Home<br />By Ohana Bam</html>");
        option4.setText("<html>Both of Us<br />By madIRFAN</html>");
    }
    // 6th questiion setup
    public void question6(){
        // option3 is correct
        // hint to make only 2||3 correct
        // stop previous song
        clip.stop();
        // set song to The Introvert
        filePath = "song6.wav";
        // play the song
        playMusic();
        // change header
        I1.setText("Question 6: What song is this?");
        // make necessary buttons visible
        setVisible();
        // change text of buttons
        option1.setText("<html>Turn On The Lights<br />By Future</html>");
        option2.setText("<html>Wasted Nights<br />By ONE OK ROCK</html>");
        option3.setText("<html>The Introvert<br />By Michael Kobrin</html>");
        option4.setText("<html>Paradise<br />By Coldplay</html>");
    }
    // 7th question setup
    public void question7(){
        // option2 is correct
        // hint to make only 2||3 correct
        // stop previous song
        clip.stop();
        // set song to Battle Of The Dragons
        filePath = "song7.wav";
        // play the song
        playMusic();
        // change header
        I1.setText("Question 7: What song is this?");
        // make necessary buttons visible
        setVisible();
        // change text of buttons
        option1.setText("<html>Into The Night<br />By prazkhanal</html>");
        option2.setText("<html>Battle Of The Dragons<br />By TommyMutiu</html>");
        option3.setText("<html>Payphone ft. Wiz Khalifa<br />By Maroon 5</html>");
        option4.setText("<html>Electronic Rock (King Around Here)<br />By AlexGrohl</html>");
    }
    // 8th question setup
    public void question8(){
        // option4 is correct
        // hint to make only 1||4 correct
        // stop previous song
        clip.stop();
        // set song to Honor and Sword (Main)
        filePath = "song8.wav";
        // play the song
        playMusic();
        // change header
        I1.setText("Question 8: What song is this?");
        // make necessary buttons visible
        setVisible();
        // change text of buttons
        option1.setText("<html>Bad Habits<br />By Ed Sheeran</html>");
        option2.setText("<html>Shivers<br />By Ed Sheeran</html>");
        option3.setText("<html>You & I<br />By One Direction</html>");
        option4.setText("<html>Honor and Sword (Main)<br />By ZakharValaha</html>");
    }
    // 9th question setup
    public void question9(){
        // option3 is correct
        // hint to make only 2||3 correct
        // stop previous song
        clip.stop();
        // set song to Town
        filePath = "song9.wav";
        // play the song
        playMusic();
        // change header
        I1.setText("Question 9: What song is this?");
        // make necessary buttons visible
        setVisible();
        // change text of buttons
        option1.setText("<html>Awakening Instrumental<br />By ItsWatR</html>");
        option2.setText("<html>Caves of Dawn<br />By GuilhermeBernardes</html>");
        option3.setText("<html>Town<br />By BeCorbal</html>");
        option4.setText("<html>Melody of Nature (Main)<br />By ZakharValaha</html>");
    }
    // 10th question setup
    public void question10(){
        // option3 is correct
        // hint to make only 2||3 correct
        // stop previous song
        clip.stop();
        // set song to Fluid
        filePath = "song10.wav";
        // play the song
        playMusic();
        // change header
        I1.setText("Question 10: What song is this?");
        // make necessary buttons visible
        setVisible();
        // change text of buttons
        option1.setText("<html>Moment<br />By SergeQuadrado</html>");
        option2.setText("<html>Piano Moment<br />By ZakharValaha</html>");
        option3.setText("<html>Fluid<br />By ItsWatR</html>");
        option4.setText("<html>Everything Feels New<br />By EvgenyBardyuzha</html>");
    }
    // reset game
    // most useful after clicking "YES" to the game over question
    public void reset(){
        // stop the music
        clip.stop();
        // set user points to 0
        points=0;
        // set user streak to 0
        streak=0;
        // set user question to 0 (ready to play question)
        question=0;
        // set user lifePoints to 0 (in-game)
        lifePoints=0;
        // reset footer text
        I3.setText("Points: " + points + "   " + "Streak: " + streak + "   Lifetime Points (In-Game): " + lifePoints + "   Lifetime Points (All): " + lifePoints2);
    }
    // make clicking a button do something
    public void actionPerformed(ActionEvent e){
        Object src = e.getSource(); // choice/button selected
        // when selecting hint button
        // and question is NOT the game over question (11)
        // selecting hint button overrides all other buttons 
        // when question number is not 11
        if (src==hint && !(question==11)){
            // make options 2&3 wrong
            if (question==1||question==3||question==5||question==8){
                // remove 6 points
                points = points-6;
                // make options 2&3 wrong
                option2.setVisible(false);
                option3.setVisible(false);
                // make hint button disappear
                hint.setVisible(false);
                // update footer (lost points)
                I3.setText("Points: " + points + "   " + "Streak: " + streak + "   Lifetime Points (In-Game): " + lifePoints + "   Lifetime Points (All): " + lifePoints2);
                // send message to user 2 answers are removed
                JOptionPane.showMessageDialog(null,"two answers removed", "hint activated", JOptionPane.INFORMATION_MESSAGE);
            }
            // make options 1&4 wrong
            else if (question==2||question==4||question==6||question==7||question==9||question==10){
                // remove 6 points
                points = points-6;
                // make options 1&4 wrong
                option1.setVisible(false);
                option4.setVisible(false);
                // make hint button disappear
                hint.setVisible(false);
                // update footer (lost points)
                I3.setText("Points: " + points + "   " + "Streak: " + streak + "   Lifetime Points (In-Game): " + lifePoints + "   Lifetime Points (All): " + lifePoints2);
                // send message to user 2 answers are removed
                JOptionPane.showMessageDialog(null,"two answers removed", "hint activated", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        // when question number is 0 (beginning pre-game question)
        if (question==0){
            // close application if option4 is selected
            if (src == hint){    
                // close application
                System.exit(0);
            }
            // move onto next question
            else if ((src==option1||src==option2||src==option3|| src==option4)){
                boolean ready = false;
                String[] responses = {"Yes. I agree 100%", "Maybe. LEMME THINK!!!", "No. (Exit Game)"};
                // double check if user wants to move onto next question
                int response = JOptionPane.showOptionDialog(null,"are you really sure?\nNOTE: CHECK IF YOUR AUDIO IS ON!", "confirm", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, responses, 0);
                switch (response){
                    // 1st button for yes
                    case 0: // also can be JOptionPane.YES_OPTION
                        ready = true;
                        break;
                    // 3rd button for no
                    case 2:
                        System.exit(0);
                        break;
                    // 2nd button and X button work as intended already
                    // by doing nothing but make dialogue disappear on click,
                    // but if needed:
                    // 2nd button = 1
                    // X button = JOptionPane.CLOSED_OPTION
                }
                if (ready) {
                    // setup first question (1)
                    question1();
                    // go to next question
                    question++;
                }
            }
        }
        // when question number is 1
        else if (question==1){
            // make option1 win
            if (src == option1){
                // setup next question (2)
                question2();
                // add points and add to streak
                // and send message to user they won
                win();
            }
            // make all other options loss excluding hint
            else if (src == option2||src==option3||src==option4){
                // setup next question (2)
                question2();
                // end streak if there is one (even though there is none for question 1)
                // and send message to user they lost
                lose();
            }
            }
        // when question number is 2
        else if (question==2){
            // make option2 win
            if (src == option2){
                // setup next question (3)
                question3();
                // add points and add to streak
                // and send message to user they won
                win();
            }
            // make all other options loss excluding hint
            else if (src == option1||src==option3||src==option4){
                // setup next question (3)
                question3();
                // end streak if there is one 
                // and send message to user they lost
                lose();
            }
            }
        // when question number is 3
        else if (question==3){
            // make option4 win
            if (src == option4){
                // setup next question (4)
                question4();
                // add points and add to streak
                // and send message to user they won
                win();
            }
            // make all other options loss excluding hint
            else if (src == option2||src==option3||src==option1){
                // setup next question (4)
                question4();
                // end streak if there is one 
                // and send message to user they lost
                lose();
            }

            }
        // when question number is 4
        else if (question==4){
            // make option3 win
            if (src == option3){
                // setup next question (5)
                question5();
                // add points and add to streak
                // and send message to user they won
                win();
            }
            // make all other options loss excluding hint
            else if (src == option2||src==option1||src==option4){
                // setup next question (5)
                question5();
                // end streak if there is one 
                // and send message to user they lost
                lose();
            }
            }
        // when question number is 5
        else if (question==5){
            // make option4 win
            if (src == option4){
                // setup next question (6)
                question6();
                // add points and add to streak
                // and send message to user they won
                win();
            }
            // make all other options loss excluding hint
            else if (src == option2||src==option1||src==option3){
                // setup next question (6)
                question6();
                // end streak if there is one 
                // and send message to user they lost
                lose();
            }
            }
        // when question number is 6
        else if (question==6){
            // make option3 win
            if (src == option3){
                // setup next question (7)
                question7();
                // add points and add to streak
                // and send message to user they won
                win();
            }
            // make all other options loss excluding hint
            else if (src == option2||src==option1||src==option4){
                // setup next question (7)
                question7();
                // end streak if there is one 
                // and send message to user they lost
                lose();
            }
        }
        // when question number is 7
        else if (question==7){
            // make option2 win
            if (src == option2){
                // setup next question (8)
                question8();
                // add points and add to streak
                // and send message to user they won
                win();
            }
            // make all other options loss excluding hint
            else if (src == option1||src==option3||src==option4){
                // setup next question (8)
                question8();
                // end streak if there is one 
                // and send message to user they lost
                lose();
            }
        }
        // when question number is 8
        else if (question==8){
            // make option4 win
            if (src == option4){
                // setup next question (9)
                question9();
                // add points and add to streak
                // and send message to user they won
                win();

            }
            // make all other options loss excluding hint
            else if (src == option2||src==option3||src==option1){
                // setup next question (9)
                question9();
                // end streak if there is one 
                // and send message to user they lost
                lose();
            }
        }
        // when question number is 9
        else if (question==9){
            // make option3 win
            if (src == option3){
                // setup next question (10)
                question10();
                // add points and add to streak
                // and send message to user they won
                win();
            }
            // make all other options loss excluding hint
            else if (src == option2||src==option1||src==option4){
                // setup next question (10)
                question10();
                // end streak if there is one 
                // and send message to user they lost
                lose();
            }
        }
        // when question number is 10
        else if (question==10){
            // make option3 win
            if (src == option3){
                // stop music
                clip.stop();
                // add points and add to streak
                // and send message to user they won
                win();
                // change header text to game over
                I1.setText("Game Over: Would you like to play again?");
            }
            // make all other options loss excluding hint
            else if (src == option1||src==option2||src==option4){
                // stop music
                clip.stop();
                // end streak if there is one 
                // and send message to user they lost
                lose();
                // change header text to game over
                I1.setText("Game Over: Would you like to play again?");
            }
        }
        // when question number is 11 (when game is over)
        else if (question==11){
            // make clicking hint exit application
            if (src==hint){
                // close applicatiton
                System.exit(0);
            }
            // make all other options reset the game
            else if (src==option1||src==option2||src==option3||src==option4){
                //reset the game
                reset();
            }
        }
    }
    // psvm so an object doesn't have to be created before starting the game
    public static void main (String[] args){
        MUSIX musix = new MUSIX();
    }
}
        
    



