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
import java.io.IOException;

import lab8memoria.modelo.Entrenador;
import lab8memoria.archivos.GestorArchivos;
import lab8memoria.archivos.ListaUsuarios;
import lab8memoria.archivos.Usuario;

public class GUIPantalla  extends JFrame {
    private CardLayout cardLayout;
    private JPanel panelCards;
    private GUILogin login;
    private GUICrearCuenta crear;
    private GUIInicio inicio;
    private GUIMenu menu;
    private GUIBatalla batalla;

    private Entrenador jugador;
    private Entrenador rival;
    private GestorArchivos gestorArchivos;
    private ListaUsuarios listaUsuarios;
    private Usuario usuarioActual;


    public GUIPantalla() {
        super("Pokemon");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);

        cargarDatos();

        InitCardLayout();
        agregarCards();

        mostrarCard("inicio");





        setVisible(true);
    }

    private void cargarDatos() {
        gestorArchivos = new GestorArchivos();
        try {
            gestorArchivos.crearPrecargados();
            listaUsuarios = gestorArchivos.cargarUsuarios();
        } catch (IOException e) {
            listaUsuarios = new ListaUsuarios();
            JOptionPane.showMessageDialog(this, "Error cargando datos: " + e.getMessage());
        }
    }

    public GestorArchivos getGestorArchivos() {
        return gestorArchivos;
    }

    public ListaUsuarios getListaUsuarios() {
        return listaUsuarios;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void iniciarSesionComo(Usuario usuario) {
        this.usuarioActual = usuario;
        try {
            gestorArchivos.cargarEquipo(usuario);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error cargando equipo: " + e.getMessage());
        }
        if (usuario.getEntrenador() == null) {
            usuario.setEntrenador(new Entrenador(usuario.getNombre()));
        }
        establecerJugador(usuario.getEntrenador());
        mostrarCard("menu");
    }

    public void iniciarBatallaConRivalAleatorio() {
        try {
            Entrenador[] todos = gestorArchivos.cargarRivales();
            if (todos.length == 0) {
                JOptionPane.showMessageDialog(this, "No hay rivales precargados todavia.");
                return;
            }
            int indiceRival = (int) (Math.random() * todos.length);
            Entrenador rivalElegido = todos[indiceRival];

            int indiceJugador = (int) (Math.random() * todos.length);
            while (todos.length > 1 && indiceJugador == indiceRival) {
                indiceJugador = (int) (Math.random() * todos.length);
            }
            Entrenador jugadorDemo = todos[indiceJugador];

            this.usuarioActual = null;
            this.rival = rivalElegido;
            establecerJugador(jugadorDemo);
            batalla.iniciarBatalla(jugadorDemo, rivalElegido);
            mostrarCard("batalla");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error cargando rivales: " + e.getMessage());
        }
    }

    public void iniciarBatalla() {
        if (jugador == null) {
            JOptionPane.showMessageDialog(this, "No hay jugador activo.");
            return;
        }
        if (rival == null) {
            try {
                rival = gestorArchivos.cargarRivalAleatorio();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error cargando rival: " + e.getMessage());
                return;
            }
            if (rival == null) {
                JOptionPane.showMessageDialog(this, "No hay rivales precargados todavia.");
                return;
            }
        }
        batalla.iniciarBatalla(jugador, rival);
        mostrarCard("batalla");
    }

    public Entrenador getRival() {
        return rival;
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
         
         batalla= new GUIBatalla (this);
         agregarCard(batalla, "batalla");
         
         
         
        

        
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
