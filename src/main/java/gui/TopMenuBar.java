package gui;

import controller.ButtonTarget;
import controller.EventController;
import controller.FramesController;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class TopMenuBar extends JMenuBar implements EventController {

    private FramesController controller;
    private ButtonTarget type;

    private JMenuBar menuBar;
    private JMenu files;
    private JMenuItem exit;
    private JMenuItem reconnect;
    private JMenu helpMenu;
    private JMenuItem help;
    private JMenu admin;
    private JMenuItem adminLogin;
    private JMenuItem normalLogin;

    public TopMenuBar(FramesController controller) {
        this.controller = controller;
        createMenu();
    }
    private void setupMenuItems(JMenu menu, List<JMenuItem> submenus) {
        menuItem(menu);
        submenus.forEach(this::menuItem);
        submenus.forEach(menu::add);
        menuBar.add(menu);
    }

    private void createMenu() {
        menuBar = new JMenuBar();
        menuBar.setBorderPainted(false);
        menuBar.setBackground(Color.RED.darker());

        files = new JMenu("Files");
        exit = new JMenuItem("Exit");
        reconnect = new JMenuItem("Reconnect");

        helpMenu = new JMenu("Help");
        help = new JMenuItem("help");

        admin = new JMenu("admin");
        adminLogin = new JMenuItem("Admin Login");
        normalLogin = new JMenuItem("Normal Login");

        setupMenuItems(files, Arrays.asList(reconnect,exit));

        setupMenuItems(helpMenu,Arrays.asList(help));

        setupMenuItems(admin,Arrays.asList(adminLogin, normalLogin));

        buttonFunctionality();

    }
    private void menuItem(JMenuItem jItem) {
        jItem.setFont(new Font(Font.MONOSPACED,Font.PLAIN,17));
        jItem.setForeground(Color.WHITE);
        jItem.setBackground(Color.RED.darker());
    }

    public void buttonFunctionality() {
        exit.addActionListener(e -> controller.closeApp());
        adminLogin.addActionListener(e -> {
            type = ButtonTarget.ADMIN;
            changeLoginType(controller);
        });
        normalLogin.addActionListener(e -> {
            type = ButtonTarget.LOGIN;
            changeLoginType(controller);
        });
    }

    @Override
    public void changeLoginType(FramesController controller) {
        controller.switchFrameTo(type);


    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }
}
