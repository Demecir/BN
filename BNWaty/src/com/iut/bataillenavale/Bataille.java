package com.iut.bataillenavale;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Bataille {

	JFrame frmBatailleNavale;
	static Bataille window = new Bataille();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					window.frmBatailleNavale.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
			
		});
		ChatClient client = new ChatClient();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setVisible(true);
        client.run();
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public Bataille() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBatailleNavale = new JFrame();
		frmBatailleNavale.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Utilisateur\\Desktop\\BN.jpg"));
		frmBatailleNavale.setTitle("Bataille Navale");
		frmBatailleNavale.setBounds(50, 50, 1500, 1000);
		frmBatailleNavale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEADING);
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				window.frmBatailleNavale.setVisible(false);
				
				Fenetre frame = new Fenetre() ;
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
				frame.show() ;	
				
				
			
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Utilisateur\\Desktop\\BN.jpg"));
		btnNewButton.setBackground(UIManager.getColor("TextPane.background"));
		frmBatailleNavale.getContentPane().add(btnNewButton, BorderLayout.CENTER);
	}

}





