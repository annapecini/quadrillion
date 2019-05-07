package gazillion;

import utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author Unsal Ozturk
 * @Version 20190501
 * Menu for profile selection, creation, and deletion
 */
public class QProfileMenu extends QPanel {
    private final int MAX_PROFILE_COUNT = 6;
    private QProfileManager profManager;
    private QProfileDisplay[] displays;
    private QMainMenu menu;

    public QProfileMenu(QPanel parent, QFrame frame) {
        super(parent, frame);
        QProfileManager.init();
        menu = new QMainMenu(this, frame, null);
        setLayout(new GridLayout(3,2,20,20)); // WARNING: CHANGE THIS IF MAX COUNT CHANGES
        displays = new QProfileDisplay[MAX_PROFILE_COUNT];
        for(int i = 0; i < MAX_PROFILE_COUNT; i++) {
            displays[i] = new QProfileDisplay(null, frame, QProfileManager.getProfile(i),i);
            add(displays[i]);
        }
    }

    private class QProfileDisplay extends QPanel implements Observer {
        private QProfile profile;
        private int ID;
        private JLabel[] labels;
        private JButton actionButton;
        private JButton deleteButton;
        public QProfileDisplay(QPanel parent, QFrame frame, QProfile profile, int ID) {
            super(parent, frame);
            this.profile = profile;
            this.ID = ID;
            this.setLayout(new GridLayout(8, 1, 20, 10));
            this.setBorder(BorderFactory.createLineBorder(Color.black));
            if(profile != null) {
                profile.getPlayerInstance().addObserver(this);
                labels = new JLabel[6];
                for(int i = 0; i < labels.length; i++) {
                    labels[i] = new JLabel();
                }
                labels[0].setText("Name: " + profile.getPlayerInstance().getName());
                labels[1].setText("Hints: " + profile.getPlayerInstance().getNoHints());
                labels[2].setText("Health: " + profile.getPlayerInstance().getNoHealth());
                labels[3].setText("Health Power Ups: " + profile.getPlayerInstance().getNoHealthPowerUp());
                labels[4].setText("Coins: " + profile.getPlayerInstance().getNoCoins());
                labels[5].setText("Time Power Ups: " + profile.getPlayerInstance().getNoTimeUp());

                actionButton = new JButton("Select Profile");
                actionButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        startGame();
                    }
                });

                deleteButton = new JButton("Delete Profile");
                deleteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = JOptionPane.showInputDialog(frame,"Do delete the profile, enter profile name. Deleted profiles may not be recovered.", "Are you sure?", JOptionPane.PLAIN_MESSAGE);
                        if(name != null && name.equals(profile.getPlayerInstance().getName())) {
                            deleteProfile();
                            JOptionPane.showMessageDialog(frame,"Profile deleted.", "So long, " + name + "!",JOptionPane.PLAIN_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(frame,"Incorrect name entered.", "Deletion failed", JOptionPane.ERROR_MESSAGE);
                        }
                        refresh();
                        repaint();
                        revalidate();
                    }
                });
                for (int i = 0; i < labels.length; i++) {
                    this.add(labels[i]);
                }
                add(actionButton);
                add(deleteButton);
            } else {
                labels = new JLabel[1];
                labels[0] = new JLabel("Empty Profile Slot");
                actionButton = new JButton ("Create Profile");
                actionButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = JOptionPane.showInputDialog(frame,"Enter Profile Name:", "Player Name", JOptionPane.PLAIN_MESSAGE);
                        if(name != null) {
                            createProfile(name);
                            refresh();
                            repaint();
                            revalidate();
                        }
                    }
                });
                add(labels[0]);
                add(actionButton);
            }
        }

        public void createProfile(String name) {
            this.profile = new QProfile(ID, name);
            QProfileManager.put(ID, profile);
        }

        public void startGame() {
            menu.setPlayer(profile.getPlayerInstance());
            frame.setActivePanel(menu);
        }

        public boolean deleteProfile() {
            QProfileManager.put(profile.getID(), null);
            boolean b = profile.delete();
            this.profile = null;
            return b;
        }

        public void update(Message msg) {
            if(msg.isValid()) {
                labels[0].setText("Name: " + profile.getPlayerInstance().getName());
                labels[1].setText("Hints: " + profile.getPlayerInstance().getNoHints());
                labels[2].setText("Health: " + profile.getPlayerInstance().getNoHealth());
                labels[3].setText("Health Power Ups: " + profile.getPlayerInstance().getNoHealthPowerUp());
                labels[4].setText("Coins: " + profile.getPlayerInstance().getNoCoins());
                labels[5].setText("Time Power Ups: " + profile.getPlayerInstance().getNoTimeUp());
                repaint();
                revalidate();
            }
        }

        public void refresh() {
            this.removeAll();
            if(profile != null) {
                profile.getPlayerInstance().addObserver(this);
                labels = new JLabel[6];
                for(int i = 0; i < labels.length; i++) {
                    labels[i] = new JLabel();
                }
                labels[0].setText("Name: " + profile.getPlayerInstance().getName());
                labels[1].setText("Hints: " + profile.getPlayerInstance().getNoHints());
                labels[2].setText("Health: " + profile.getPlayerInstance().getNoHealth());
                labels[3].setText("Health Power Ups: " + profile.getPlayerInstance().getNoHealthPowerUp());
                labels[4].setText("Coins: " + profile.getPlayerInstance().getNoCoins());
                labels[5].setText("Time Power Ups: " + profile.getPlayerInstance().getNoTimeUp());

                actionButton = new JButton("Select Profile");
                actionButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        startGame();
                    }
                });

                deleteButton = new JButton("Delete Profile");
                deleteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = JOptionPane.showInputDialog(frame,"Do delete the profile, enter profile name. Deleted profiles may not be recovered.", "Are you sure?", JOptionPane.PLAIN_MESSAGE);
                        if(name != null && name.equals(profile.getPlayerInstance().getName())) {
                            deleteProfile();
                            JOptionPane.showMessageDialog(frame,"Profile deleted.", "So long, " + name + "!",JOptionPane.PLAIN_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(frame,"Incorrect name entered.", "Deletion failed", JOptionPane.ERROR_MESSAGE);
                        }
                        refresh();
                        repaint();
                        revalidate();
                    }
                });
                for (int i = 0; i < labels.length; i++) {
                    this.add(labels[i]);
                }
                add(actionButton);
                add(deleteButton);
            } else {
                labels = new JLabel[1];
                labels[0] = new JLabel("Empty Profile Slot");
                actionButton = new JButton ("Create Profile");
                actionButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = JOptionPane.showInputDialog(frame,"Enter Profile Name:", "Player Name", JOptionPane.PLAIN_MESSAGE);
                        if(name != null) {
                            createProfile(name);
                            refresh();
                            repaint();
                            revalidate();
                        }
                    }
                });
                add(labels[0]);
                add(actionButton);
            }
        }
    }
}
