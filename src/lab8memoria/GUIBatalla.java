/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab8memoria;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import lab8memoria.logica.Batalla;
import lab8memoria.modelo.Entrenador;
import lab8memoria.modelo.Pokemon;

public class GUIBatalla extends JPanel {

    private JPanel panelBatalla;
    private JPanel panelControles;
    private JPanel panelCartas;
    private JPanel panelInformacionRival;
    private JLabel lblNombreRival;
    private JLabel lblVidaRival;
    private JLabel lblTipoRival;
    private JLabel lblNivelRival;
    private JPanel panelInformacionJugador;
    private JLabel lblNombreJugador;
    private JLabel lblVidaJugador;
    private JLabel lblTipoJugador;
    private JLabel lblNivelJugador;
    private JLabel lblImagenRival;
    private JLabel lblImagenJugador;
    private JLabel lblTituloRival;
    private JLabel lblTituloJugador;
    private JButton btnAtacar;
    private JButton btnCambiar;
    private JButton btnObjetos;
    private JButton btnMiEquipo;
    private CardLayout cardLayout;
    private JPanel panelCards;
    private JPanel panelChat;
    private JPanel panelCambiar;
    private JPanel panelObjetos;
    private JPanel panelEquipo;
    private JTextArea areaHistorial;
    private Timer timerDanio;
    private Batalla batalla;
    private Entrenador jugador;
    private Entrenador rival;

    public GUIBatalla(GUIPantalla padre) {
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout());
        setBackground(new Color(235, 235, 235));

        crearPanelBatalla();
        crearPanelControles();
        crearPanelCards();

        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.add(panelControles, BorderLayout.NORTH);
        panelInferior.add(panelCards, BorderLayout.CENTER);

        add(panelBatalla, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void crearPanelBatalla() {
        panelBatalla = new JPanel(new BorderLayout());
        panelBatalla.setBackground(new Color(235, 235, 235));
        panelBatalla.setBorder(
                BorderFactory.createEmptyBorder(15, 20, 10, 20)
        );

        crearInformacionRival();
        crearInformacionJugador();

        JPanel bloqueRival = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 0, 0)
        );

        bloqueRival.setOpaque(false);
        bloqueRival.add(panelInformacionRival);
        bloqueRival.add(lblImagenRival);

        JPanel bloqueJugador = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 0, 0)
        );

        bloqueJugador.setOpaque(false);
        bloqueJugador.add(lblImagenJugador);
        bloqueJugador.add(panelInformacionJugador);

        JPanel panelPokemons = new JPanel();
        panelPokemons.setLayout(
                new BoxLayout(panelPokemons, BoxLayout.Y_AXIS)
        );

        panelPokemons.setOpaque(false);

        bloqueRival.setAlignmentX(Component.CENTER_ALIGNMENT);
        bloqueJugador.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelPokemons.add(bloqueRival);
        panelPokemons.add(
                Box.createRigidArea(
                        new Dimension(0, 70)
                )
        );
        panelPokemons.add(bloqueJugador);

        panelBatalla.add(
                panelPokemons,
                BorderLayout.CENTER
        );
    }

    private void crearInformacionRival() {
        JPanel contenedor = new JPanel(
                new BorderLayout(2, 2)
        );

        contenedor.setOpaque(false);

        lblTituloRival = new JLabel(
                "Entrenador rival"
        );

        lblTituloRival.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        lblTituloRival.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        contenedor.add(
                lblTituloRival,
                BorderLayout.NORTH
        );

        JPanel datosRival = new JPanel(
                new java.awt.GridLayout(4, 1, 5, 5)
        );

        datosRival.setPreferredSize(
                new Dimension(260, 150)
        );

        datosRival.setBackground(Color.WHITE);

        datosRival.setBorder(
                BorderFactory.createLineBorder(
                        Color.BLACK,
                        2
                )
        );

        lblNombreRival = new JLabel("Nombre: ");
        lblNivelRival = new JLabel("Nivel: ");
        lblVidaRival = new JLabel("Vida: ");
        lblTipoRival = new JLabel("Tipo: ");

        datosRival.add(lblNombreRival);
        datosRival.add(lblNivelRival);
        datosRival.add(lblVidaRival);
        datosRival.add(lblTipoRival);

        contenedor.add(
                datosRival,
                BorderLayout.CENTER
        );

        panelInformacionRival = contenedor;

        lblImagenRival = new JLabel(
                "POKÉMON RIVAL"
        );

        lblImagenRival.setOpaque(true);
        lblImagenRival.setBackground(Color.RED);
        lblImagenRival.setForeground(Color.WHITE);

        lblImagenRival.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        lblImagenRival.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        lblImagenRival.setVerticalAlignment(
                SwingConstants.CENTER
        );

        lblImagenRival.setPreferredSize(
                new Dimension(200, 150)
        );

        lblImagenRival.setBorder(
                BorderFactory.createLineBorder(
                        Color.BLACK,
                        2
                )
        );
    }

    private void crearInformacionJugador() {
        JPanel contenedor = new JPanel(
                new BorderLayout(2, 2)
        );

        contenedor.setOpaque(false);

        lblTituloJugador = new JLabel(
                "Tu nombre"
        );

        lblTituloJugador.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        lblTituloJugador.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        contenedor.add(
                lblTituloJugador,
                BorderLayout.NORTH
        );

        JPanel datosJugador = new JPanel(
                new java.awt.GridLayout(4, 1, 5, 5)
        );

        datosJugador.setPreferredSize(
                new Dimension(260, 150)
        );

        datosJugador.setBackground(Color.WHITE);

        datosJugador.setBorder(
                BorderFactory.createLineBorder(
                        Color.BLACK,
                        2
                )
        );

        lblNombreJugador = new JLabel("Nombre: ");
        lblNivelJugador = new JLabel("Nivel: ");
        lblVidaJugador = new JLabel("Vida: ");
        lblTipoJugador = new JLabel("Tipo: ");

        datosJugador.add(lblNombreJugador);
        datosJugador.add(lblNivelJugador);
        datosJugador.add(lblVidaJugador);
        datosJugador.add(lblTipoJugador);

        contenedor.add(
                datosJugador,
                BorderLayout.CENTER
        );

        panelInformacionJugador = contenedor;

        lblImagenJugador = new JLabel(
                "TU POKÉMON"
        );

        lblImagenJugador.setOpaque(true);
        lblImagenJugador.setBackground(Color.BLUE);
        lblImagenJugador.setForeground(Color.WHITE);

        lblImagenJugador.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        lblImagenJugador.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        lblImagenJugador.setVerticalAlignment(
                SwingConstants.CENTER
        );

        lblImagenJugador.setPreferredSize(
                new Dimension(200, 150)
        );

        lblImagenJugador.setBorder(
                BorderFactory.createLineBorder(
                        Color.BLACK,
                        2
                )
        );
    }

    private void crearPanelControles() {
        panelControles = new JPanel(
                new FlowLayout(
                        FlowLayout.CENTER,
                        15,
                        10
                )
        );

        panelControles.setBackground(
                new Color(30, 30, 45)
        );

        btnAtacar = new JButton("Atacar");
        btnCambiar = new JButton("Cambiar");
        btnObjetos = new JButton("Objetos");
        btnMiEquipo = new JButton("Mi equipo");

        btnAtacar.setFocusable(false);
        btnCambiar.setFocusable(false);
        btnObjetos.setFocusable(false);
        btnMiEquipo.setFocusable(false);

        btnAtacar.addActionListener(e ->
                realizarAtaque()
        );

        btnCambiar.addActionListener(e ->
                cardLayout.show(panelCards, "CAMBIAR")
        );

        btnObjetos.addActionListener(e ->
                cardLayout.show(panelCards, "OBJETOS")
        );

        btnMiEquipo.addActionListener(e ->
                cardLayout.show(panelCards, "EQUIPO")
        );

        panelControles.add(btnAtacar);
        panelControles.add(btnCambiar);
        panelControles.add(btnObjetos);
        panelControles.add(btnMiEquipo);
    }

    private void crearPanelCards() {
        cardLayout = new CardLayout();

        panelCards = new JPanel(
                cardLayout
        );

        panelCards.setPreferredSize(
                new Dimension(900, 250)
        );

        panelChat = crearPanelChat();
        panelCambiar = crearPanelCambiar();
        panelObjetos = crearPanelObjetos();
        panelEquipo = crearPanelEquipo();

        panelCards.add(
                panelChat,
                "CHAT"
        );

        panelCards.add(
                panelCambiar,
                "CAMBIAR"
        );

        panelCards.add(
                panelObjetos,
                "OBJETOS"
        );

        panelCards.add(
                panelEquipo,
                "EQUIPO"
        );

        cardLayout.show(
                panelCards,
                "CHAT"
        );
    }

    private JPanel crearPanelChat() {
        JPanel panel = new JPanel(
                new BorderLayout()
        );

        panel.setBorder(
                BorderFactory.createTitledBorder(
                        "Historial de batalla"
                )
        );

        areaHistorial = new JTextArea();
        areaHistorial.setEditable(false);
        areaHistorial.setLineWrap(true);
        areaHistorial.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(
                areaHistorial
        );

        panel.add(
                scroll,
                BorderLayout.CENTER
        );

        return panel;
    }

    private JPanel crearPanelCambiar() {
        JPanel panel = new JPanel(
                new BorderLayout()
        );

        JLabel titulo = new JLabel(
                "Aquí se mostrarán los Pokémon disponibles"
        );

        titulo.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        panel.add(
                titulo,
                BorderLayout.CENTER
        );

        return panel;
    }

    private JPanel crearPanelObjetos() {
        JPanel panel = new JPanel(
                new BorderLayout()
        );

        JLabel titulo = new JLabel(
                "Aquí se mostrarán los objetos disponibles"
        );

        titulo.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        panel.add(
                titulo,
                BorderLayout.CENTER
        );

        return panel;
    }

    private JPanel crearPanelEquipo() {
        JPanel panel = new JPanel(
                new BorderLayout()
        );

        JLabel titulo = new JLabel(
                "Aquí se mostrará el estado del equipo"
        );

        titulo.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        panel.add(
                titulo,
                BorderLayout.CENTER
        );

        return panel;
    }

    public void refrescarInformacion(
            String nombreRival,
            String nivelRival,
            String vidaRival,
            String tipoRival,
            String nombreJugador,
            String nivelJugador,
            String vidaJugador,
            String tipoJugador
    ) {
        lblNombreRival.setText(
                "Nombre: " + nombreRival
        );

        lblNivelRival.setText(
                "Nivel: " + nivelRival
        );

        lblVidaRival.setText(
                "Vida: " + vidaRival
        );

        lblTipoRival.setText(
                "Tipo: " + tipoRival
        );

        lblNombreJugador.setText(
                "Nombre: " + nombreJugador
        );

        lblNivelJugador.setText(
                "Nivel: " + nivelJugador
        );

        lblVidaJugador.setText(
                "Vida: " + vidaJugador
        );

        lblTipoJugador.setText(
                "Tipo: " + tipoJugador
        );

        revalidate();
        repaint();
    }

    public void agregarAlHistorial(String texto) {
        if (areaHistorial.getText().isEmpty()) {
            areaHistorial.setText(texto);
        } else {
            areaHistorial.append(
                    "\n" + texto
            );
        }
    }

    public void mostrarChat() {
        cardLayout.show(
                panelCards,
                "CHAT"
        );
    }

    public void mostrarCambiarPokemon() {
        cardLayout.show(
                panelCards,
                "CAMBIAR"
        );
    }

    public void mostrarObjetos() {
        cardLayout.show(
                panelCards,
                "OBJETOS"
        );
    }

    public void mostrarEquipo() {
        cardLayout.show(
                panelCards,
                "EQUIPO"
        );
    }

    public void establecerNombreRival(String nombre) {
        lblTituloRival.setText(nombre);
    }

    public void establecerNombreJugador(String nombre) {
        lblTituloJugador.setText(nombre);
    }

    public void parpadearDanio(boolean esRival) {
        JLabel imagen;

        if (esRival) {
            imagen = lblImagenRival;
        } else {
            imagen = lblImagenJugador;
        }

        if (timerDanio != null
                && timerDanio.isRunning()) {
            timerDanio.stop();
        }

        final int[] contador = {0};

        timerDanio = new Timer(
                100,
                new ActionListener() {
            @Override
            public void actionPerformed(
                    ActionEvent e
            ) {
                imagen.setVisible(
                        !imagen.isVisible()
                );

                contador[0]++;

                if (contador[0] >= 6) {
                    imagen.setVisible(true);
                    timerDanio.stop();
                }
            }
        });

        timerDanio.start();
    }

    public void establecerImagenRival(
            ImageIcon imagen
    ) {
        lblImagenRival.setText("");
        lblImagenRival.setIcon(imagen);
    }

    public void establecerImagenJugador(
            ImageIcon imagen) {
        lblImagenJugador.setText("");
        lblImagenJugador.setIcon(imagen);
    }

    public void iniciarBatalla(Entrenador jugador, Entrenador rival) {
        this.jugador = jugador;
        this.rival = rival;
        this.batalla = new Batalla(jugador, rival);

        areaHistorial.setText("");
        establecerNombreJugador(jugador.getNombre());
        establecerNombreRival(rival.getNombre());
        actualizarInformacion();
        mostrarChat();
    }

    private void realizarAtaque() {
        if (batalla == null) {
            return;
        }

        mostrarChat();

        String textoAtaque = batalla.atacar(0);
        agregarAlHistorial(textoAtaque);
        parpadearDanio(true);

        if (resolverEstadoBatalla()) {
            return;
        }

        String textoRival = batalla.turnoRival();
        if (!textoRival.isEmpty()) {
            agregarAlHistorial(textoRival);
            parpadearDanio(false);
        }

        resolverEstadoBatalla();
        actualizarInformacion();
    }

    private boolean resolverEstadoBatalla() {
        if (batalla.jugadorGano()) {
            actualizarInformacion();
            JOptionPane.showMessageDialog(this, "¡Ganaste la batalla!");
            return true;
        }

        if (batalla.jugadorPerdio()) {
            actualizarInformacion();
            JOptionPane.showMessageDialog(this, "Perdiste la batalla...");
            return true;
        }

        if (batalla.hayDerrotado()) {
            String textoContinuar = batalla.continuarConSiguiente();
            if (!textoContinuar.isEmpty()) {
                agregarAlHistorial(textoContinuar);
            }
        }

        return false;
    }

    private void actualizarInformacion() {
        Pokemon activoJugador = jugador.getEquipo().obtenerActivo();
        Pokemon activoRival = rival.getEquipo().obtenerActivo();

        String nombreJugador = activoJugador != null ? activoJugador.getNombre() : "-";
        String nivelJugador = activoJugador != null ? String.valueOf(activoJugador.getNivel()) : "-";
        String vidaJugador = activoJugador != null ? (activoJugador.getHp() + "/" + activoJugador.getHpMaximo()) : "-";
        String tipoJugador = activoJugador != null ? activoJugador.getTipo() : "-";

        String nombreRival = activoRival != null ? activoRival.getNombre() : "-";
        String nivelRival = activoRival != null ? String.valueOf(activoRival.getNivel()) : "-";
        String vidaRival = activoRival != null ? (activoRival.getHp() + "/" + activoRival.getHpMaximo()) : "-";
        String tipoRival = activoRival != null ? activoRival.getTipo() : "-";

        refrescarInformacion(nombreRival, nivelRival, vidaRival, tipoRival,nombreJugador, nivelJugador, vidaJugador, tipoJugador);
    }
}