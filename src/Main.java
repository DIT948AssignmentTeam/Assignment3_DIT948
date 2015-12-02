import becker.robots.*;
import becker.robots.icons.Icon;

import dit948.*;
import dit948.Random;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.imageio.*;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javax.swing.*;

import java.util.*;


/**
 * @author Shafiq Saloum
 * @author Isakovski Filip
 */
public class Main {
    static double robotspeed = 1;
    public static void main(String[] args) {
        StartGame();
//        Thread thread = new Thread (new Runnable(){
//            @Override
//            public void run() {
//                StartGame();
//                while(true){
//                    if (StartGame.userRobot.getIntersection()==StartGame.enemyRobot.getIntersection()) {
//                        winLoseWindow(false, StartGame.frame, StartGame.userRobot);
//                        StartGame.components.getMenuBar().getMenu(1).getItem(0).doClick();
//                    }
//                }
//            }
//        });
//        thread.start();
    }


    /**
     *  Here is where the magic happens:
     *  Worlds are created, robots are created and the game is prepared for starting
     */
    public static boolean StartGame(){

        City.showFrame(false);

        City pacCity = new City(10, 10); //creating a new city

        //Rectangle rectangle = new Rectangle();

        putWalls(pacCity); //showing the walls

        RobotUIComponents components = new RobotUIComponents(pacCity, 0, 0, 11, 11);


        JMenuBar menubar = new JMenuBar();

        JFrame frame = new JFrame("Assignment03");
        JPanel panel = new JPanel();
        JPanel buttonsPane = new JPanel(new GridBagLayout());
        JPanel cityView = components.getCityView();
        //components.getCityView().setSimSize(44);

        JMenu actions = new JMenu("Actions");
        JMenu settings = new JMenu("Settings");
        actions.setMnemonic(KeyEvent.VK_F);

        JMenuItem actionMenuItem = new JMenuItem("Start/Pause");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        ButtonGroup settingsGroup = new ButtonGroup();
        JMenuItem easySettingsItem = new JRadioButtonMenuItem("Easy");
        JMenuItem mediumSettingsItem = new JRadioButtonMenuItem("Medium");
        JMenuItem hardSettingsItem = new JRadioButtonMenuItem("Hard");

        settingsGroup.add(easySettingsItem);
        settingsGroup.add(mediumSettingsItem);
        settingsGroup.add(hardSettingsItem);

        /**
         * Creating random variables for the robots (and making sure they don't clash on start)
         */
        int firstRandomInt = Random.randomInt(10), secondRandomInt = Random.randomInt(10), thirdRandomInt = Random.randomInt(10);
        if(secondRandomInt == firstRandomInt) {
            while(secondRandomInt == Random.randomInt(10)){
                secondRandomInt = Random.randomInt(10);
            }
        }
        if(thirdRandomInt == secondRandomInt) {
            while(thirdRandomInt == Random.randomInt(10)){
                thirdRandomInt = Random.randomInt(10);
            }
        }

        /**
         * Creating the robots
         */

;
//        MachineRobot enemyRobot = new MachineRobot(pacCity, secondRandomInt, Random.randomInt(10),
//                Direction.EAST, frame, components);
        CustomRobot userRobot = new CustomRobot(pacCity, firstRandomInt, Random.randomInt(10), Direction.NORTH, frame,
                components);
        MachineRobot enemyRobot = new MachineRobot(pacCity, secondRandomInt, Random.randomInt(10),
                Direction.EAST, userRobot, frame, components);
        enemyRobot.setColor(Color.BLUE);
        userRobot.setEnemyRobot(enemyRobot);

        //enemyRobot.setSpeed(3);
        System.out.println("Speed: "+enemyRobot.getSpeed());
        userRobot.setColor(Color.RED);
        userRobot.setSpeed(4);
        Thread enemyThread = new Thread(enemyRobot);
        enemyThread.start();
        Thread userThread = new Thread(userRobot);

        /**
         * Creating the Prize
         */
        //Image image = new ImageIcon(frame.getClass().getResource("customPrize.png")).getImage();
        //CustomIcon prizeIcon = new CustomIcon("/home/iso/assignments/Assignment3_DIT948/src/customPrize.png");
        CustomIcon prizeIcon = new CustomIcon();

        //Icon icon = prizeIcon;
        Thing prize = new Thing(pacCity, thirdRandomInt, Random.randomInt(10));
        prize.setIcon(prizeIcon);
        //Thing random = new Thing(pacCity, thirdRandomInt, Random.randomInt(10));
        //prize.setIcon();

        /**
         * Creating and setting functions to the controller buttons
         */
        JButton up = new JButton("UP");
        up.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent up){
                userRobot.moveNorth(1, enemyRobot);
                //if(enemyRobot.intersects(userRobot)) {
                    //winLoseWindow(false, frame, userRobot, components);
                //}
            }
        });
        buttonsPane.add(up, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        JButton down = new JButton("DOWN");
        down.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent down) {
                userRobot.moveSouth(1, enemyRobot);
                //if(enemyRobot.intersects(userRobot)) {
                    //winLoseWindow(false, frame, userRobot, components);
                //}
            }
        });

        buttonsPane.add(down, new GridBagConstraints(1, 2, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        JButton left = new JButton("LEFT");
        left.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent left) {
                userRobot.moveWest(1, enemyRobot);
                //if(enemyRobot.intersects(userRobot)) {
                    //winLoseWindow(false, frame, userRobot, components);
                //}
            }
        });

        buttonsPane.add(left, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        JButton right = new JButton("RIGHT");
        right.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent right){
                userRobot.moveEast(1, enemyRobot);
                //if(enemyRobot.intersects(userRobot)) {
                    //winLoseWindow(false, frame, userRobot, components);
                //}
            }
        });

        buttonsPane.add(right, new GridBagConstraints(2, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        JButton pick = new JButton("PICK");
        pick.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent pick) {
                userRobot.pickThing();
                if (userRobot.checkPrize()) {
                    winLoseWindow(true, frame, enemyRobot, components);
                }
            }
        });
        buttonsPane.add(pick, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        /**
         * Assigning functions to the Menu buttons
         */
        actionMenuItem.setMnemonic(KeyEvent.VK_E);
        actionMenuItem.setToolTipText("Start/Pause the game");
        actionMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent startstop) {
                components.getStartStopButton().doClick();
            }
        });

        exitMenuItem.setToolTipText("Exit the game");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent exit) {
                System.exit(0);
            }
        });

        easySettingsItem.setToolTipText("Easy Difficulty");
        easySettingsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent changedifficulty) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        enemyRobot.setSpeed(2);
                        System.out.println("Speed: "+enemyRobot.getSpeed());
                    }
                });
                thread.start();
            }
        });
        mediumSettingsItem.setToolTipText("Medium Difficulty");
        mediumSettingsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent changedifficulty) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        enemyRobot.setSpeed(4);
                        System.out.println("Speed: "+enemyRobot.getSpeed());
                    }
                });
                thread.start();
            }
        });
        hardSettingsItem.setToolTipText("Hard Difficulty");
        hardSettingsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent changedifficulty) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        enemyRobot.setSpeed(6);
                        System.out.println("Speed: "+enemyRobot.getSpeed());
                    }
                });
                thread.start();
            }
        });

        /**
         * Adding the components to the UI
         */
        actions.add(actionMenuItem);
        actions.add(exitMenuItem);
        menubar.add(actions);
        settings.add(easySettingsItem);
        settings.add(mediumSettingsItem);
        settings.add(hardSettingsItem);
        easySettingsItem.setSelected(true);
        menubar.add(settings);

        panel.add(cityView);
        frame.setJMenuBar(menubar);
        frame.setContentPane(panel);
        frame.add(buttonsPane);
        frame.setSize(600, 720);


        frame.setVisible(true);

        components.getMenuBar().getMenu(1).getItem(0).doClick();

        if(enemyRobot.intersects(userRobot)) {
            userRobot.crashedBot = true;
            System.out.println("AAA");
        }
        return true;
    }

    /**
     * Surrounds the city with walls
     *
     */
    static void putWalls(City city) {  //adding walls

        for (int i = 0; i <= 10; i++) {
            new Wall(city, 0, i, Direction.NORTH);
            new Wall(city, 10, i, Direction.SOUTH);
            new Wall(city, i, 0, Direction.WEST);
            new Wall(city, i, 10, Direction.EAST);
        }
    }

    /**
     * Shows a different dialog depending on whether you win or lose
     * Allows you to restart or exit the game
     */
    static void winLoseWindow(boolean win, Frame frame, CustomRobot robot, RobotUIComponents components){

        if(win){
            //JPanel panel = new JPanel();
            robot.breakBot = true;
            int i = JOptionPane.showConfirmDialog(
                    null,
                    "You win!\nRestart Game?",
                    "Game Over",
                    JOptionPane.YES_NO_OPTION);
            components.getMenuBar().getMenu(1).getItem(0).doClick();


            if(i == JOptionPane.YES_OPTION){
                frame.dispose();
                StartGame();
            }else if(i == JOptionPane.NO_OPTION){
                System.exit(1);
            }
        }else{
            int i = 0;
            robot.breakBot=true;
            Thread thread = new Thread (new Runnable(){
                @Override
                public void run() {
                    components.getMenuBar().getMenu(1).getItem(0).doClick();
                    robot.breakRobot("lklkjl");
                }
            });
            thread.start();
            i = JOptionPane.showConfirmDialog(null, "You lose!\nRestart Game?", "Game Over", JOptionPane.YES_NO_OPTION);
            components.getMenuBar().getMenu(1).getItem(0).doClick();

            if(i == JOptionPane.YES_OPTION){
                frame.dispose();
                StartGame();
            }else if(i == JOptionPane.NO_OPTION){
                System.exit(1);
            }
        }
    }

}




