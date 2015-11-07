import becker.robots.*;

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
 * Created by Iso on 07-Nov-15.
 */
public class Main {
    public static void main(String[] args) {

        City.showFrame(false);

        City pacCity = new City();

        RobotUIComponents components = new RobotUIComponents(pacCity, 0, 0, 10, 10);

        JFrame frame = new JFrame("Assignment03");
        JPanel panel = new JPanel();
        JPanel buttonsPane = new JPanel(new GridBagLayout());
        panel.add(components.getCityView());
        panel.add(buttonsPane);
        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setSize(600, 800);

        JMenuBar menubar = new JMenuBar();


        JMenu actions = new JMenu("Actions");
        JMenu settings = new JMenu("Settings");
        actions.setMnemonic(KeyEvent.VK_F);

        JMenuItem actionMenuItem = new JMenuItem("Start/Pause");
        ButtonGroup settingsGroup = new ButtonGroup();
        JMenuItem easySettingsItem = new JRadioButtonMenuItem("Easy");
        JMenuItem mediumSettingsItem = new JRadioButtonMenuItem("Medium");
        JMenuItem hardSettingsItem = new JRadioButtonMenuItem("Hard");

        settingsGroup.add(easySettingsItem);
        settingsGroup.add(mediumSettingsItem);
        settingsGroup.add(hardSettingsItem);

        JButton up = new JButton("UP");
        buttonsPane.add(up, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        JButton down = new JButton("DOWN");
        buttonsPane.add(down, new GridBagConstraints(1, 2, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        JButton left = new JButton("LEFT");
        buttonsPane.add(left, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        JButton right = new JButton("RIGHT");
        buttonsPane.add(right, new GridBagConstraints(2, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        JButton pick = new JButton("PICK");
        buttonsPane.add(pick, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));


        actionMenuItem.setMnemonic(KeyEvent.VK_E);
        actionMenuItem.setToolTipText("Start/Pause the game");
        actionMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent startstop) {
                System.exit(0);
            }
        });

        easySettingsItem.setToolTipText("Easy Difficulty");
        easySettingsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent changedifficulty) {
            }
        });
        mediumSettingsItem.setToolTipText("Easy Difficulty");
        mediumSettingsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent changedifficulty) {
            }
        });
        hardSettingsItem.setToolTipText("Easy Difficulty");
        hardSettingsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent changedifficulty) {
            }
        });

        actions.add(actionMenuItem);
        menubar.add(actions);
        settings.add(easySettingsItem);
        settings.add(mediumSettingsItem);
        settings.add(hardSettingsItem);
        menubar.add(settings);


        frame.setJMenuBar(menubar);


    }
}
