/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab8memoria;

/**
 *
 * @author denam
 */

import javax.swing. *;
import java.awt. *;
import javax.swing.JPanel;
public class GUIInicio extends JPanel {
    private JButton boton1;
    private JButton boton2;
    private JButton boton3;
    private JButton boton4;
    private JPanel panel;
    private CardLayout cardLayout;
    private JPanel cards;

  
    
    
    public GUIInicio(CardLayout cardLayout, JPanel cards, GUIPantalla padre){
        
     this.cardLayout = cardLayout;
     this.cards = cards;
    
    setLayout(new BorderLayout(10, 10));
    
   
   
    
    
    Inicializarbotones(padre);

     setVisible(true);
     
    
    }
    
    
    
    
    private void Inicializarbotones(GUIPantalla padre){
        JPanel Panelenvuelto =new JPanel(new GridBagLayout());
        Panelenvuelto.setOpaque(false);
        boton1 = new JButton("Login");

        boton1.setFont(new Font("Arial", Font.BOLD, 14));
        boton1.setPreferredSize(new Dimension(600, 150));
        boton1.setMaximumSize(new Dimension(600, 150));

        boton1.setForeground(Color.WHITE);
        boton1.setBackground(Color.red);

        boton1.setFocusPainted(false);
        boton1.setBorderPainted(false);
        boton1.setContentAreaFilled(false);
        boton1.setOpaque(true);

        boton1.setHorizontalAlignment(SwingConstants.CENTER);
        boton1.setAlignmentX(Component.CENTER_ALIGNMENT);

        boton1.addActionListener(e -> {
            padre.mostrarCard("login");
        });
        
        
        
        
        boton2 = new JButton("Crear Cuenta");

        boton2.setFont(new Font("Arial", Font.BOLD, 14));
        boton2.setPreferredSize(new Dimension(600, 150));
        boton2.setMaximumSize(new Dimension(600, 150));

        boton2.setForeground(Color.WHITE);
        boton2.setBackground(Color.red);

        boton2.setFocusPainted(false);
        boton2.setBorderPainted(false);
        boton2.setContentAreaFilled(false);
        boton2.setOpaque(true);

        boton2.setHorizontalAlignment(SwingConstants.CENTER);
        boton2.setAlignmentX(Component.CENTER_ALIGNMENT);

        boton2.addActionListener(e -> {
                padre.mostrarCard("crear");
        });
        
        
        boton3 = new JButton("Usuario random");

        boton3.setFont(new Font("Arial", Font.BOLD, 14));
        boton3.setPreferredSize(new Dimension(600, 150));
        boton3.setMaximumSize(new Dimension(600, 150));

        boton3.setForeground(Color.WHITE);
        boton3.setBackground(Color.red);

        boton3.setFocusPainted(false);
        boton3.setBorderPainted(false);
        boton3.setContentAreaFilled(false);
        boton3.setOpaque(true);

        boton3.setHorizontalAlignment(SwingConstants.CENTER);
        boton3.setAlignmentX(Component.CENTER_ALIGNMENT);

        boton3.addActionListener(e -> {
            ///////////////////////////////////////////////////////////////////////////sin logica aun de usuario random
        });
        
        boton4 = new JButton("Salir");

        boton4.setFont(new Font("Arial", Font.BOLD, 14));
        boton4.setPreferredSize(new Dimension(600, 150));
        boton4.setMaximumSize(new Dimension(600, 150));

        boton4.setForeground(Color.WHITE);
        boton4.setBackground(Color.red);

        boton4.setFocusPainted(false);
        boton4.setBorderPainted(false);
        boton4.setContentAreaFilled(false);
        boton4.setOpaque(true);

        boton4.setHorizontalAlignment(SwingConstants.CENTER);
        boton4.setAlignmentX(Component.CENTER_ALIGNMENT);

        boton4.addActionListener(e -> {
            System.exit(0);
        });
        
        panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(400, 230));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        
        panel.add(boton1);
        panel.add(Box.createVerticalStrut(20));
        panel.add(boton2);
        panel.add(Box.createVerticalStrut(20));
        panel.add(boton3);
        panel.add(Box.createVerticalStrut(20));
        panel.add(boton4);
        Panelenvuelto.add(panel);
        add (Panelenvuelto, BorderLayout.CENTER);
        
    }
            

    
}
