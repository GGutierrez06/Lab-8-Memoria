/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab8memoria;

/**
 *
 * @author denam
 */

    
import java.awt.*;
import javax.swing.*;

import lab8memoria.modelo.Entrenador;

public class GUIPantalla  extends JFrame {
    private CardLayout cardLayout;
    private JPanel panelCards;
    private GUILogin login;
    private GUICrearCuenta crear;
    private GUIInicio inicio;
    private GUIMenu menu;
    private Entrenador jugador;

  
    public GUIPantalla() {
        super("Pokemon");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        

        InitCardLayout();
        agregarCards();
        
        mostrarCard("menu");
        
        

        

        setVisible(true);
    }

    public void agregarCards() {
        login = new GUILogin( cardLayout, panelCards, this);
        agregarCard(login, "login");

        crear = new GUICrearCuenta ( cardLayout, panelCards, this);
        agregarCard(crear, "crear");
        
        inicio= new GUIInicio ( cardLayout, panelCards, this);
        agregarCard(inicio, "inicio");
        
        menu = new GUIMenu (this);
         agregarCard(menu, "menu");
        

        
    }

    public void InitCardLayout() {
        cardLayout = new CardLayout();
        panelCards = new JPanel(cardLayout);
        panelCards.setOpaque(false);
        getContentPane().add(panelCards, BorderLayout.CENTER);
    }

    private void agregarCard(JPanel panel, String nombre) {
        panelCards.add(panel, nombre);
    }

    public void mostrarCard(String nombreCard) {
        cardLayout.show(panelCards, nombreCard);
        panelCards.revalidate();
        panelCards.repaint();
    }

    public void establecerJugador(Entrenador jugador) {
        this.jugador = jugador;
        menu.actualizar();
    }

    public Entrenador getJugador() {
        return jugador;
    }

}
