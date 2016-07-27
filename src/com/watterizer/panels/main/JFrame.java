/* **********   JFrame.java   **********
 *
 * This piece of garbage was brought to you by nothing less than the almighty lord
 * of programming, the Java God and ruler of all the non living things, McBeengs, 
 * A.K.A. myself. I don't mind anyone steal or using my codes at their own business,
 * but at least, and I meant VERY least, give me the proper credit for it. I really
 * don't know what the code below does at this point in time while I write this stuff, 
 * but if you took all this time to sit, rip the .java files and read all this 
 * unnecessary bullshit, you know for what you came, doesn't ?
 * 
 * Copyright(c) {YEAR!!!} Mc's brilliant mind. All Rights (kinda) Reserved.
 */

 /*
 * {Insert class description here}
 */
package com.watterizer.panels.main;

import aurelienribon.slidinglayout.SLAnimator;
import aurelienribon.slidinglayout.SLConfig;
import aurelienribon.slidinglayout.SLKeyframe;
import aurelienribon.slidinglayout.SLPanel;
import aurelienribon.slidinglayout.SLSide;
import com.watterizer.panels.options.OptionsJFrame;
import com.watterizer.util.UsefulMethods;
import com.watterizer.xml.XmlManager;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class JFrame extends javax.swing.JFrame {

    // Component variables
    private SLPanel backgroundPanel;
    private SLConfig slideUser;
    private SLConfig slideRanking;
    private SLConfig slideMeasurer;
    private SideButtonPanel userButton;
    private SideButtonPanel measurerButton;
    private SideButtonPanel rankingButton;
    private final Connection conn;
    private JMenu jMenu1;
    private JMenu jMenu2;
    private JMenuBar jMenuBar1;
    private UserPanel userPanel;
    private SLPanel measurerPanel;
    private SLPanel rankingPanel;
    private JMenuItem optionsItem;
    // Other variables
    private XmlManager language;
    private int currentPanel = 1;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public JFrame() {
        //language = UsefulMethods.getManagerInstance(UsefulMethods.LANGUAGE);
        initComponents();
        setListeners();
        conn = UsefulMethods.getDBInstance();

        if (SystemTray.isSupported()) {
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowIconified(WindowEvent e) {
                    SystemTray tray = SystemTray.getSystemTray();
                    PopupMenu popup = new PopupMenu();
                    MenuItem defaultItem = new MenuItem("Abrir");
                    setVisible(false);
                    defaultItem.addActionListener((ActionEvent e1) -> {
                        setVisible(true);
                        setState(Frame.NORMAL);
                    });
                    popup.add(defaultItem);
                    TrayIcon trayIcon = new TrayIcon(new ImageIcon(getClass().getResource("/com/watterizer/style/icons/ic_logo_16.png")).getImage(), "Watterizer", popup);
                    trayIcon.addActionListener((ActionEvent e1) -> {
                        setVisible(true);
                        setState(Frame.NORMAL);
                    });

                    try {
                        tray.add(trayIcon);
                    } catch (AWTException ex) {

                    }
                }

                @Override
                public void windowDeiconified(WindowEvent e) {
                    if (SystemTray.isSupported()) {
                        SystemTray tray = SystemTray.getSystemTray();

                        for (TrayIcon icon : tray.getTrayIcons()) {
                            tray.remove(icon);
                        }

                    }
                }
            });
        }
    }

    private void initComponents() {
        backgroundPanel = new SLPanel();
        userPanel = new UserPanel();
        measurerPanel = new MeasurerPanel();
        rankingPanel = new RankingPanel();
        userButton = new SideButtonPanel(new javax.swing.ImageIcon(getClass()
                .getResource("/com/watterizer/style/icons/user.png")), "Usuário", Color.red);
        measurerButton = new SideButtonPanel(new javax.swing.ImageIcon(getClass()
                .getResource("/com/watterizer/style/icons/user.png")), "Medidor", Color.green);
        rankingButton = new SideButtonPanel(new javax.swing.ImageIcon(getClass()
                .getResource("/com/watterizer/style/icons/user.png")), "Ranking", Color.blue);
        jMenuBar1 = new JMenuBar();
        jMenu1 = new JMenu();
        jMenu2 = new JMenu();
        optionsItem = new JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 600));

        backgroundPanel.setTweenManager(SLAnimator.createTweenManager());
        SLAnimator.start();

        userPanel.setOpaque(true);
        measurerPanel.setOpaque(true);
        rankingPanel.setOpaque(true);

        slideMeasurer = new SLConfig(backgroundPanel)
                .row(1f).col(60).col(1f).col(60)
                .beginGrid(0, 0)
                .row(1f).col(1f)
                .place(0, 0, userButton)
                .endGrid()
                .beginGrid(0, 1)
                .row(1f).col(1f)
                .place(0, 0, measurerPanel)
                .endGrid()
                .beginGrid(0, 2)
                .row(1f).col(1f)
                .place(0, 0, rankingButton)
                .endGrid();

        slideUser = new SLConfig(backgroundPanel)
                .row(1f).col(1f).col(60)
                .beginGrid(0, 0)
                .row(1f).col(1f)
                .place(0, 0, userPanel)
                .endGrid()
                .beginGrid(0, 1)
                .row(1f).col(1f)
                .place(0, 0, measurerButton)
                .endGrid();

        slideRanking = new SLConfig(backgroundPanel)
                .row(1f).col(60).col(1f)
                .beginGrid(0, 0)
                .row(1f).col(1f)
                .place(0, 0, measurerButton)
                .endGrid()
                .beginGrid(0, 1)
                .row(1f).col(1f)
                .place(0, 0, rankingPanel)
                .endGrid();

        backgroundPanel.initialize(slideMeasurer);
        add(backgroundPanel);

        jMenu1.setText("Arquivo");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Configurações");

        optionsItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/watterizer/style/icons/settings.png"))); // NOI18N
        optionsItem.setText("Opções");
        optionsItem.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                ResultSet rs = conn.createStatement().executeQuery("SELECT id FROM perfil WHERE perfil = 'Administrador'");
                rs.next();
                int perfil = rs.getInt("id");
                rs.close();
                if (UsefulMethods.getCurrentUserModel().getIdPerfil() == perfil) {
                    new OptionsJFrame().setVisible(true);
                } else if (JOptionPane.showConfirmDialog(rootPane, "Você não está registrado com uma conta "
                        + "administrativa.\nPara alterar as configurações, entre em contato com um administrador.", 
                        "Info", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.OK_OPTION) {
                    @SuppressWarnings("UseOfObsoleteCollectionType")
                    Hashtable<String, String> info = loginJOptionPane(this);
                    PreparedStatement statement = conn.prepareStatement("SELECT * FROM usuario WHERE username = ? AND senha = ? AND id_perfil = ?");
                    statement.setString(1, info.get("user"));
                    statement.setString(2, info.get("pass"));
                    statement.setInt(3, perfil);
                    
                    if (statement.execute()) {
                        new OptionsJFrame().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Login incorreto", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (SQLException | HeadlessException ex) {
                Logger.getLogger(JFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        jMenu2.add(optionsItem);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }

    private void setListeners() {
        userButton.setListeners(getHoverAdapter(0));
        measurerButton.setListeners(getHoverAdapter(1));
        rankingButton.setListeners(getHoverAdapter(2));

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 39) {
                    if (currentPanel == 0) {
                        changeMainPanel(1);
                    } else if (currentPanel == 1) {
                        changeMainPanel(2);
                    }
                } else if (e.getKeyCode() == 37) {
                    if (currentPanel == 2) {
                        changeMainPanel(1);
                    } else if (currentPanel == 1) {
                        changeMainPanel(0);
                    }
                }
            }
        });
    }

    private MouseAdapter getHoverAdapter(int click) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                if (click == 0) {
                    if (currentPanel == 2) {
                        changeMainPanel(1);
                    } else if (currentPanel == 1) {
                        changeMainPanel(0);
                    }
                } else if (click == 1) {
                    if (currentPanel == 0 || currentPanel == 2) {
                        changeMainPanel(1);
                    }
                } else if (click == 2) {
                    if (currentPanel == 0) {
                        changeMainPanel(1);
                    } else if (currentPanel == 1) {
                        changeMainPanel(2);
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        };
    }

    @SuppressWarnings("UseOfObsoleteCollectionType")
    private Hashtable<String, String> loginJOptionPane(JFrame frame) {
        Hashtable<String, String> logininformation = new Hashtable<>();

        JPanel panel = new JPanel(new BorderLayout(5, 5));

        JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
        label.add(new JLabel("Username:", SwingConstants.RIGHT));
        label.add(new JLabel("Password:", SwingConstants.RIGHT));
        panel.add(label, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        JTextField username = new JTextField();
        controls.add(username);
        JPasswordField password = new JPasswordField();
        controls.add(password);
        panel.add(controls, BorderLayout.CENTER);

        JOptionPane.showConfirmDialog(frame, panel, "Login", JOptionPane.OK_CANCEL_OPTION);

        logininformation.put("user", username.getText());
        logininformation.put("pass", new String(password.getPassword()));
        return logininformation;
    }

    private void changeMainPanel(int position) {
        if (position == currentPanel || position < 0 || position > 2) {
            return;
        }

        switch (position) {
            case 0: // User panel
                backgroundPanel.createTransition().push(new SLKeyframe(slideUser, 2f).setStartSideForNewCmps(SLSide.LEFT).setEndSideForOldCmps(SLSide.RIGHT)).play();
                setTitle("Usuário | Watterizer");
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            sleep(1000);
                            userPanel.beginAnimations();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(JFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }.start();
            case 1: // Measurer panel
                if (currentPanel == 0) {
                    backgroundPanel.createTransition().push(new SLKeyframe(slideMeasurer, 2f).setEndSideForOldCmps(SLSide.LEFT).setStartSideForNewCmps(SLSide.RIGHT)).play();
                } else if (currentPanel == 2) {
                    backgroundPanel.createTransition().push(new SLKeyframe(slideMeasurer, 2f).setEndSideForOldCmps(SLSide.RIGHT).setStartSideForNewCmps(SLSide.LEFT)).play();
                }
                setTitle("Medidor | Watterizer");
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            sleep(1);
                            userPanel.resetAnimations();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(JFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }.start();
            case 2: // Ranking panel
                backgroundPanel.createTransition().push(new SLKeyframe(slideRanking, 2f).setEndSideForOldCmps(SLSide.LEFT).setStartSideForNewCmps(SLSide.RIGHT)).play();
                setTitle("Ranking | Watterizer");
        }
        currentPanel = position;
    }
}
