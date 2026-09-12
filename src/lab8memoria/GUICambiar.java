/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab8memoria;

/**
 *
 * @author denam
 */



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import lab8memoria.modelo.Entrenador;
import lab8memoria.modelo.ListaEnlazada;
import lab8memoria.modelo.Pokemon;

public class GUICambiar extends JPanel {

    private GUIBatalla padre;

    private JPanel panelLista;
    private JScrollPane scrollLista;
    private JButton btnCerrar;

    private JButton[] botonesPokemon;

    public GUICambiar(GUIBatalla padre) {
        this.padre = padre;

        inicializarComponentes();
        recargarLista();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(
                BorderFactory.createEmptyBorder(10,10, 10, 10 )
        );

        JLabel titulo = new JLabel("Cambiar Pokemon");
        titulo.setFont(
                new Font("Arial", Font.BOLD, 24)
        );
        titulo.setHorizontalAlignment(
                JLabel.CENTER
        );

        add(titulo, BorderLayout.NORTH);

        panelLista = new JPanel();
        panelLista.setLayout(
                new BoxLayout(
                        panelLista,
                        BoxLayout.Y_AXIS
                )
        );
        panelLista.setBackground(Color.WHITE);

        scrollLista = new JScrollPane(panelLista);
        scrollLista.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );
        scrollLista.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        add(scrollLista, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(
                new FlowLayout(
                        FlowLayout.CENTER
                )
        );

        btnCerrar = new JButton("Cerrar");
        btnCerrar.setFocusable(false);

        btnCerrar.addActionListener(e -> {
            mostrarHistorial();
        });

        panelInferior.add(btnCerrar);

        add(panelInferior, BorderLayout.SOUTH);
    }

    public void recargarLista() {
        panelLista.removeAll();

        pintarPokemones();

        panelLista.revalidate();
        panelLista.repaint();

        revalidate();
        repaint();
    }

    private void pintarPokemones() {
        Entrenador jugador = padre.getJugador();
        if (jugador == null) {
            botonesPokemon = new JButton[0];
            return;
        }

        ListaEnlazada equipo = jugador.getEquipo();
        Pokemon activo = equipo.obtenerActivo();
        int cantidad = equipo.contar();
        botonesPokemon = new JButton[cantidad];

        for (int i = 0; i < cantidad; i++) {
            Pokemon p = equipo.obtenerPorIndice(i);

            String estado = p.estaDerrotado() ? "DERROTADO" : p.getTipo();
            String informacion = String.format(
                    "%-20s %-12s %-15s %-12s",
                    p.getNombre(),
                    "Nivel " + p.getNivel(),
                    p.getHp() + "/" + p.getHpMaximo(),
                    estado
            );

            JButton botonPokemon = new JButton(informacion);

            botonPokemon.setFont(
                    new Font(
                            "Monospaced",
                            Font.PLAIN,
                            14
                    )
            );

            botonPokemon.setHorizontalAlignment(
                    JButton.LEFT
            );

            botonPokemon.setPreferredSize(
                    new Dimension(
                            500,
                            45
                    )
            );

            botonPokemon.setMaximumSize(
                    new Dimension(
                            Integer.MAX_VALUE,
                            45
                    )
            );

            botonPokemon.setAlignmentX(
                    LEFT_ALIGNMENT
            );

            botonPokemon.setFocusable(false);

            botonPokemon.setOpaque(true);
            botonPokemon.setContentAreaFilled(true);

            if (p == activo) {
                botonPokemon.setBackground(Color.YELLOW);
            } else if (p.estaDerrotado()) {
                botonPokemon.setBackground(Color.LIGHT_GRAY);
            } else {
                botonPokemon.setBackground(Color.WHITE);
            }

            botonPokemon.setEnabled(p != activo && !p.estaDerrotado());

            String nombre = p.getNombre();
            botonPokemon.addActionListener(e -> seleccionarPokemon(nombre));

            botonesPokemon[i] = botonPokemon;

            panelLista.add(botonPokemon);

            panelLista.add(
                    Box.createRigidArea(
                            new Dimension(
                                    0,
                                    5
                            )
                    )
            );
        }
    }

    private void seleccionarPokemon(String nombre) {
        padre.cambiarPokemon(nombre);
        recargarLista();
    }

    public void mostrarHistorial() {
        padre.mostrarChat();
    }

}
