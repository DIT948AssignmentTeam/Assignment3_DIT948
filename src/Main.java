import becker.robots.*;
import dit948.*;
import dit948.Random;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
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
    public static void main(String[] args) {
        StartGame();
    }

    public static void StartGame(){

        City.showFrame(false);

        City pacCity = new City(); //creating a new city

        Rectangle rectangle = new Rectangle();

        putWalls(pacCity); //showing the walls

        RobotUIComponents components = new RobotUIComponents(pacCity, 0, 0, 10, 10);

        JMenuBar menubar = new JMenuBar();

        JFrame frame = new JFrame("Assignment03");
        JPanel panel = new JPanel();
        JPanel buttonsPane = new JPanel(new GridBagLayout());
        JPanel cityView = components.getCityView();
        components.getCityView().setSimSize(44);

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
        int firstRandomInt = Random.randomInt(10), secondRandomInt = Random.randomInt(10);
        if(secondRandomInt == firstRandomInt) {
            while(secondRandomInt == Random.randomInt(10)){
                secondRandomInt = Random.randomInt(10);
            }
        }

        /**
         * Creating the robots
         */
        CustomRobot userRobot = new CustomRobot(pacCity, firstRandomInt, Random.randomInt(10), Direction.NORTH, true);
        MachineRobot enemyRobot = new MachineRobot(pacCity, secondRandomInt, Random.randomInt(10), Direction.EAST, false);
        enemyRobot.setColor(Color.BLUE);
        enemyRobot.setSpeed(3);
        System.out.println("Speed: "+enemyRobot.getSpeed());
        userRobot.setColor(Color.RED);
        Thread enemyThread = new Thread(enemyRobot);
        enemyThread.start();


        /**
         * Creating and setting functions to the controller buttons
         */
        JButton up = new JButton("UP");
        up.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent up){
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        userRobot.moveNorth(1);
                    }
                });
                thread.start();
            }
        });
        buttonsPane.add(up, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        JButton down = new JButton("DOWN");
        down.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent down) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        userRobot.moveSouth(1);
                    }
                });
                thread.start();
            }
        });
        buttonsPane.add(down, new GridBagConstraints(1, 2, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        JButton left = new JButton("LEFT");
        left.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent left) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        userRobot.moveWest(1);
                    }
                });
                thread.start();
            }
        });
        buttonsPane.add(left, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        JButton right = new JButton("RIGHT");
        right.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent right){
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        userRobot.moveEast(1);
                    }
                });
                thread.start();
            }
        });
        buttonsPane.add(right, new GridBagConstraints(2, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        JButton pick = new JButton("PICK");
        pick.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent pick) {

                int i = JOptionPane.showConfirmDialog(
                        null,
                        "You win!\nRestart Game?",
                        "Game Over",
                        JOptionPane.YES_NO_OPTION);
                if(i == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    StartGame();
                }else if(i == JOptionPane.NO_OPTION){
                    System.exit(1);
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
                        enemyRobot.setSpeed(0.5);
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
                        enemyRobot.setSpeed(3);
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
                        enemyRobot.setSpeed(7);
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
        menubar.add(settings);

        panel.add(components.getCityView());
        frame.setJMenuBar(menubar);
        frame.setContentPane(panel);
        frame.add(buttonsPane);
        frame.setSize(600, 700);


        frame.setVisible(true);

    }

    static void putWalls(City pacCity) {  //adding walls

        for (int i = 0; i <= 10; i++) {
            new Wall(pacCity, 0, i, Direction.NORTH);
            new Wall(pacCity, 10, i, Direction.SOUTH);
            new Wall(pacCity, i, 0, Direction.WEST);
            new Wall(pacCity, i, 10, Direction.EAST);
        }

    }

    static void winLoseWindow(boolean winLose){
        //JFrame frame = new JFrame();

        if(winLose){
            //JPanel panel = new JPanel();
            int i = JOptionPane.showConfirmDialog(
                    null,
                    "You win!\nRestart Game?",
                    "Game Over",
                    JOptionPane.YES_NO_OPTION);
            if(i == JOptionPane.YES_OPTION){
                //frame.dispose();
                StartGame();
            }else if(i == JOptionPane.NO_OPTION){
                System.exit(1);
            }
        }else{
            JOptionPane.showConfirmDialog(null, "You lose!\nRestart Game?", "Game Over", JOptionPane.YES_NO_OPTION);
//            frame = new JFrame("You lose!");
//            frame.pack();
//            frame.setVisible(true);
//            System.exit(1);
        }
    }

}




