package gazillion;

import javafx.scene.layout.Border;
import utils.Message;
import utils.Observer;
import utils.QProfile;
import utils.QProfileManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        displays = new QProfileDisplay[MAX_PROFILE_COUNT];
        for(int i = 0; i < MAX_PROFILE_COUNT; i++) {
            displays[i] = new QProfileDisplay(null, frame, QProfileManager.getProfile(i),i);
            add(displays[i]);
        }

        setBackground( new Color(200, 191, 231));
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

            setBackground( new Color(200, 191, 231));
            //this.setBorder(BorderFactory.createLineBorder(Color.black));
            refresh();
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

            if( profile != null) {

                this.setLayout(new GridBagLayout());
                profile.getPlayerInstance().addObserver(this);

                JPanel topPanel = new JPanel( new GridLayout(0, 2));
                JPanel bottomPanel = new JPanel( new FlowLayout());

                JPanel avatarPanel = new JPanel( new GridBagLayout());
                avatarPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

                //add image
                try{
                    String name = profile.getPlayerInstance().getName();
                    name = name.substring( name.length() - 1);
                    BufferedImage img;
                    if( name.equals( "a")||  name.equals( "A"))
                        img = ImageIO.read(getClass().getResource("avatar4.png"));
                    else
                        img = ImageIO.read(getClass().getResource("avatar3.png"));

                    avatarPanel.add( new JLabel(new ImageIcon( img)));
                }catch( Exception e){
                    e.printStackTrace();
                }

                //nameLabel.setVerticalAlignment(SwingConstants.CENTER);
                //nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

                this.add( avatarPanel);

                JPanel infoPanel1 = new JPanel((new GridLayout(3, 1, 20, 10)));
                JPanel infoPanel2 = new JPanel((new GridLayout(3, 1, 20, 10)));

                labels = new JLabel[6];
                for(int i = 0; i < labels.length; i++) {
                    labels[i] = new JLabel();
                }

                labels[0].setText("Name: " + profile.getPlayerInstance().getName());
                labels[0].setVerticalAlignment( SwingConstants.BOTTOM);
                labels[1].setText("Hints: " + profile.getPlayerInstance().getNoHints());
                labels[2].setText("Health: " + profile.getPlayerInstance().getNoHealth());
                labels[2].setVerticalAlignment( SwingConstants.TOP);
                labels[3].setText("Health Power Ups: " + profile.getPlayerInstance().getNoHealthPowerUp());
                labels[3].setVerticalAlignment( SwingConstants.BOTTOM);
                labels[4].setText("Coins: " + profile.getPlayerInstance().getNoCoins());
                labels[5].setText("Time Power Ups: " + profile.getPlayerInstance().getNoTimeUp());
                labels[5].setVerticalAlignment( SwingConstants.TOP);

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
                for (int i = 0; i < labels.length/2; i++) {
                    infoPanel1.add(labels[i]);
                    labels[i].setHorizontalAlignment( SwingConstants.CENTER);
                }
                for (int i = labels.length/2; i < labels.length; i++) {
                    infoPanel2.add(labels[i]);
                }

                topPanel.add( infoPanel1);
                topPanel.add( infoPanel2);

                bottomPanel.add(actionButton);
                bottomPanel.add(deleteButton);

                JPanel info = new JPanel(new BorderLayout());
                info.add( topPanel, BorderLayout.NORTH);
                info.add( bottomPanel, BorderLayout.SOUTH);
                this.add(info);

                Color color = new Color(255, 90, 35) ;

                infoPanel1.setBackground( color);

                avatarPanel.setBorder(BorderFactory.createLineBorder(Color.black));
                //topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
                info.setBorder(BorderFactory.createLineBorder(Color.black));
                topPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
                bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
                //bottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));
                infoPanel2.setBackground( color);
                avatarPanel.setBackground(color);
                topPanel.setBackground(color);
                bottomPanel.setBackground(color);

            } else {
                this.setLayout(new BorderLayout());

                JPanel centerPanel = new JPanel( new GridBagLayout());
                JLabel empty = new JLabel( "Empty Profile Slot");
                centerPanel.add( empty);

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

                centerPanel.add(actionButton);
                this.add( centerPanel);
            }

        }
    }
}
