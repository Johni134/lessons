package ru.geekbrains.se.ui.home;

import ru.geekbrains.se.util.FormHelper;

import javax.swing.*;
import java.awt.event.*;

import static ru.geekbrains.se.util.Constants.MAIN_FORM_TITLE;
import static ru.geekbrains.se.util.Constants.TEXT_CLEAR;

public class MainForm extends JFrame {
    private final JPopupMenu popupMenu = new JPopupMenu();
    private final JMenuItem jMenuItem = new JMenuItem();
    private JTextPane textPaneChat;
    private JTextField textFieldMain;
    private JButton buttonSend;
    private JPanel panelMain;
    private JScrollPane scrollPaneChat;

    /**
     * main form constructor
     */
    public MainForm() {
        initForm();
        initListeners();
    }

    /**
     * show popup menu
     */
    private void showPopupMenu(int x, int y) {
        jMenuItem.setEnabled(!textPaneChat.getText().isEmpty());
        popupMenu.show(textPaneChat, x, y);
    }

    /**
     * init form functions
     */
    private void initForm() {
        setTitle(MAIN_FORM_TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(panelMain);
        setBounds(300, 300, 400, 400);
        getRootPane().setDefaultButton(buttonSend);

        jMenuItem.setText(TEXT_CLEAR);
        popupMenu.add(jMenuItem);
    }

    private void initListeners() {
        // add listener to popup menu item
        jMenuItem.addActionListener(e -> textPaneChat.setText(""));

        // focus to text field after window opened
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
                textFieldMain.requestFocus();
            }
        });

        // send msg, when button clicked
        buttonSend.addActionListener(e -> sendMsg());

        textPaneChat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3)
                    showPopupMenu(e.getX(), e.getY());
            }
        });

        // send msg when get enter key, clear text field when get escape key
        textFieldMain.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        sendMsg();
                        break;
                    case KeyEvent.VK_ESCAPE:
                        clearInputTextField();
                        break;
                }
            }
        });
    }

    /**
     * send message function
     */
    private void sendMsg() {
        if (textFieldMain.getText().equals("")) {
            textFieldMain.requestFocus();
            return;
        }
        textPaneChat.setText(textPaneChat.getText() + "[" + FormHelper.getFormattedDate() + "]: " + textFieldMain.getText() + "\n");
        clearInputTextField();
        textFieldMain.requestFocus();
    }

    /**
     * clear text in text field
     */
    private void clearInputTextField() {
        textFieldMain.setText("");
    }
}
