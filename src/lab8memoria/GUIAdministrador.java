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

public class GUIAdministrador extends JDialog {

    private JFrame pantalla;

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
        
        });

       
        btnBuscar.addActionListener(e -> {
           
        });

    
        btnEliminar.addActionListener(e -> {
            JToggleButton seleccionado =
                    obtenerPokemonSeleccionado();

            if (seleccionado == null) {
                return;
            }

          
        });

     
        btnMover.addActionListener(e -> {
            JToggleButton seleccionado =
                    obtenerPokemonSeleccionado();

            if (seleccionado == null) {
                return;
            }

            
        });

        panelBotones.add(btnAgregar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnMover);

        panelInferior.add(panelBotones, BorderLayout.CENTER);

        add(panelInferior, BorderLayout.SOUTH);

        crearBotonesTemporales();
    }

    private void crearBotonesTemporales() {
        grupoPokemon = new ButtonGroup();

        /*
         * Arreglo temporal.
         *
         * Más adelante se reemplazará por el recorrido
         * de la lista enlazada de Pokémon.
         */
        botonesPokemon = new JToggleButton[5];

        for (int i = 0; i < 5; i++) {

            
            String informacion = String.format( "%-25s %-15s %-20s %-15s", "", "", "",  "" );

            JToggleButton botonPokemon =new JToggleButton(informacion);

            botonPokemon.setFont(new Font("Monospaced", Font.PLAIN, 14) );

            botonPokemon.setHorizontalAlignment(SwingConstants.LEFT );

            botonPokemon.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));

            botonPokemon.setPreferredSize( new Dimension(560, 45) );

            botonPokemon.setAlignmentX(  Component.LEFT_ALIGNMENT );

            botonPokemon.setFocusable(false);

            botonPokemon.setOpaque(true);
            botonPokemon.setContentAreaFilled(true);
            botonPokemon.setBackground(Color.WHITE);
            botonPokemon.setForeground(Color.BLACK);

           
            botonPokemon.addChangeListener(e -> {
                if (botonPokemon.isSelected()) {
                    botonPokemon.setBackground(Color.YELLOW);
                } else {
                    botonPokemon.setBackground(Color.WHITE);
                }
            });

          
            grupoPokemon.add(botonPokemon);

            botonesPokemon[i] = botonPokemon;

            panelLista.add(botonPokemon);

            panelLista.add(  Box.createRigidArea(new Dimension(0, 5)) );
        }

        lblCantidad.setText( "Pokémon disponibles: " + botonesPokemon.length);
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