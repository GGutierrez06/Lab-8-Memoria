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
import lab8memoria.modelo.ListaEnlazada;
import lab8memoria.modelo.Pokemon;

public class GUIAdministrador extends JDialog {

    private GUIPantalla pantalla;

    private JLabel lblTitulo;
    private JLabel lblReferencia;
    private JLabel lblCantidad;

    private JPanel panelLista;
    private JScrollPane scrollLista;

    private JButton btnAgregar;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JButton btnMover;

    private ButtonGroup grupoPokemon;
    private JToggleButton[] botonesPokemon;

    public GUIAdministrador(GUIPantalla pantalla) {
        super(pantalla, "Gestión de equipo", true);

        this.pantalla = pantalla;

        if (pantalla.getJugador() == null) {
            Entrenador demo = new Entrenador("Entrenador de prueba");
            demo.agregarPokemon(new Pokemon("Pikachu", "Electrico", 15, 100));
            demo.agregarPokemon(new Pokemon("Charmander", "Fuego", 18, 120));
            demo.agregarPokemon(new Pokemon("Bulbasaur", "Planta", 14, 90));
            demo.agregarPokemon(new Pokemon("Squirtle", "Agua", 16, 95));
            pantalla.establecerJugador(demo);
        }

        inicializarComponentes();

        setSize(800, 550);
        setLocationRelativeTo(pantalla);
        setVisible(true);
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));

        lblTitulo = new JLabel("GESTIÓN DE POKEMONS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBorder(
                BorderFactory.createEmptyBorder(15, 10, 10, 10)
        );

        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel(new BorderLayout(5, 5));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

        lblReferencia = new JLabel( "Nombre                         Nivel             Vida                 Tipo");

        lblReferencia.setFont( new Font("Monospaced", Font.BOLD, 14)  );

        lblReferencia.setBorder( BorderFactory.createEmptyBorder(5, 5, 5, 5) );

        panelCentral.add(lblReferencia, BorderLayout.NORTH);

     
        panelLista = new JPanel();
        panelLista.setLayout(new BoxLayout(panelLista, BoxLayout.Y_AXIS) );

        panelLista.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5) );

    
        scrollLista = new JScrollPane(panelLista);

        scrollLista.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        scrollLista.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panelCentral.add(scrollLista, BorderLayout.CENTER);

        add(panelCentral, BorderLayout.CENTER);

        
        JPanel panelInferior = new JPanel(new BorderLayout());

        lblCantidad = new JLabel("Pokémon disponibles: 0");
        lblCantidad.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10) );

        panelInferior.add(lblCantidad, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel( new FlowLayout(FlowLayout.CENTER, 10, 8));

        btnAgregar = new JButton("Agregar");
        btnBuscar = new JButton("Buscar");
        btnEliminar = new JButton("Eliminar");
        btnMover = new JButton("Mover");

        btnAgregar.setFocusable(false);
        btnBuscar.setFocusable(false);
        btnEliminar.setFocusable(false);
        btnMover.setFocusable(false);

        
        btnAgregar.addActionListener(e -> {
            JTextField campoNombre = new JTextField();
            JTextField campoTipo = new JTextField();
            JTextField campoNivel = new JTextField();
            JTextField campoHpMaximo = new JTextField();

            JPanel formulario = new JPanel(new GridLayout(4, 2, 5, 5));
            formulario.add(new JLabel("Nombre:"));
            formulario.add(campoNombre);
            formulario.add(new JLabel("Tipo:"));
            formulario.add(campoTipo);
            formulario.add(new JLabel("Nivel:"));
            formulario.add(campoNivel);
            formulario.add(new JLabel("Vida maxima:"));
            formulario.add(campoHpMaximo);

            int opcion = JOptionPane.showConfirmDialog(this, formulario, "Agregar pokemon", JOptionPane.OK_CANCEL_OPTION);
            if (opcion != JOptionPane.OK_OPTION) {
                return;
            }

            String nombre = campoNombre.getText().trim();
            String tipo = campoTipo.getText().trim();
            if (nombre.isEmpty() || tipo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nombre y tipo son obligatorios.");
                return;
            }

            int nivel;
            int hpMaximo;
            try {
                nivel = Integer.parseInt(campoNivel.getText().trim());
                hpMaximo = Integer.parseInt(campoHpMaximo.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Nivel y vida deben ser numeros.");
                return;
            }

            pantalla.getJugador().getEquipo().insertar(new Pokemon(nombre, tipo, nivel, hpMaximo));
            pintarEquipo();
        });


        btnBuscar.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog(this, "Nombre del pokemon:");
            if (nombre == null || nombre.isBlank()) {
                return;
            }
            Pokemon encontrado = pantalla.getJugador().getEquipo().buscar(nombre);
            String mensaje = encontrado == null ? "No existe." : encontrado.toString();
            JOptionPane.showMessageDialog(this, mensaje);
        });


        btnEliminar.addActionListener(e -> {
            JToggleButton seleccionado =
                    obtenerPokemonSeleccionado();

            if (seleccionado == null) {
                return;
            }

            String nombre = seleccionado.getText().trim().split("\\s+")[0];
            pantalla.getJugador().getEquipo().eliminar(nombre);
            pintarEquipo();
        });


        btnMover.addActionListener(e -> {
            JToggleButton seleccionado =
                    obtenerPokemonSeleccionado();

            if (seleccionado == null) {
                return;
            }

            String nombre = seleccionado.getText().trim().split("\\s+")[0];
            pantalla.getJugador().getEquipo().moverAlPrimerLugar(nombre);
            pintarEquipo();
        });

        panelBotones.add(btnAgregar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnMover);

        panelInferior.add(panelBotones, BorderLayout.CENTER);

        add(panelInferior, BorderLayout.SOUTH);

        pintarEquipo();
    }

    private void pintarEquipo() {
        panelLista.removeAll();
        grupoPokemon = new ButtonGroup();

        ListaEnlazada equipo = pantalla.getJugador().getEquipo();
        int cantidad = equipo.contar();
        botonesPokemon = new JToggleButton[cantidad];

        for (int i = 0; i < cantidad; i++) {

            Pokemon p = equipo.obtenerPorIndice(i);
            String informacion = String.format( "%-25s %-15s %-20s %-15s",
                    p.getNombre(),
                    "Nivel " + p.getNivel(),
                    p.getHp() + "/" + p.getHpMaximo(),
                    p.getTipo() );

            JToggleButton botonPokemon =new JToggleButton(informacion, obtenerSprite(p.getNombre()));

            botonPokemon.setFont(new Font("Monospaced", Font.PLAIN, 14) );

            botonPokemon.setHorizontalAlignment(SwingConstants.LEFT );

            botonPokemon.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));

            botonPokemon.setPreferredSize( new Dimension(560, 45) );

            botonPokemon.setAlignmentX(  Component.LEFT_ALIGNMENT );

            botonPokemon.setFocusable(false);

            botonPokemon.setEnabled(!p.estaDerrotado());

            botonPokemon.setOpaque(true);
            botonPokemon.setContentAreaFilled(true);
            botonPokemon.setBackground(p.estaDerrotado() ? Color.LIGHT_GRAY : Color.WHITE);
            botonPokemon.setForeground(Color.BLACK);


            botonPokemon.addChangeListener(e -> {
                if (botonPokemon.isSelected()) {
                    botonPokemon.setBackground(Color.YELLOW);
                } else {
                    botonPokemon.setBackground(p.estaDerrotado() ? Color.LIGHT_GRAY : Color.WHITE);
                }
            });


            grupoPokemon.add(botonPokemon);

            botonesPokemon[i] = botonPokemon;

            panelLista.add(botonPokemon);

            panelLista.add(  Box.createRigidArea(new Dimension(0, 5)) );
        }

        lblCantidad.setText( "Pokémon disponibles: " + equipo.contarDisponibles());

        panelLista.revalidate();
        panelLista.repaint();
    }

    private ImageIcon obtenerSprite(String nombrePokemon) {
        String archivo = nombrePokemon;
        if (archivo.equalsIgnoreCase("Bulbasaur")) {
            archivo = "Bulbasour";
        }

        java.net.URL recurso = getClass().getResource("/SpritesPokemons/" + archivo + "Sprite1.png");
        if (recurso == null) {
            return null;
        }

        ImageIcon icono = new ImageIcon(recurso);
        Image imagen = icono.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        return new ImageIcon(imagen);
    }

    private JToggleButton obtenerPokemonSeleccionado() {
        for (int i = 0; i < botonesPokemon.length; i++) {
            if (botonesPokemon[i].isSelected()) {
                return botonesPokemon[i];
            }
        }

        return null;
    }
}