package com.iut.bataillenavale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class ChatClient {

    BufferedReader in;
    PrintWriter out;
    JFrame frame = new JFrame("Chatter");
    JTextField textField = new JTextField(40);
    JTextArea messageArea = new JTextArea(8, 40);


    public ChatClient() {

        // Layout GUI
        textField.setEditable(false);
        messageArea.setEditable(false);
        frame.getContentPane().add(textField, "North");
        frame.getContentPane().add(new JScrollPane(messageArea), "Center");
        frame.pack();

        // Add Listeners
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                out.println(textField.getText());
                textField.setText("");
            }
        });
    }

    /**
     * Saisie de l'adresse du sereur
     */
    private String getServerAddress() {
        return JOptionPane.showInputDialog(
            frame,
            "Merci de saisir l'ip du serveur:",
            "Bienvenue sur Chatter",
            JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Selection du Pseudo
     */
    private String getName() {
        return JOptionPane.showInputDialog(
            frame,
            "Votre Pseudo:",
            "Seletion du Pseudo",
            JOptionPane.PLAIN_MESSAGE);
    }

    /**
     *Connexion au serveur et creation de la boucle d'attente des message
     */
    public void run() throws IOException {

        // Creation de la connection avec les informations saisie
        String serverAddress = getServerAddress();
        @SuppressWarnings("resource")
		Socket socket = new Socket(serverAddress, 9001);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        // Récupération de tous les message suivant le "protocole"
        while (true) {
            String line = in.readLine();
            if (line.startsWith("SUBMITNAME")) {
                out.println(getName());
            } else if (line.startsWith("NAMEACCEPTED")) {
                textField.setEditable(true);
            } else if (line.startsWith("MESSAGE")) {
                messageArea.append(line.substring(8) + "\n");
            }
        }
    }

}