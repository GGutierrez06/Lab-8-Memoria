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
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import lab8memoria.modelo.Entrenador;
import lab8memoria.modelo.ListaEnlazada;
import lab8memoria.modelo.Pokemon;

public class GUIMiEquipo extends JPanel {

    private GUIBatalla padre;
    private JPanel panelLista;
    private JScrollPane scrollLista;

    public GUIMiEquipo(GUIBatalla padre) {
        this.padre = padre;
        inicializarComponentes();
        recargarLista();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("MI EQUIPO");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setHorizontalAlignment(JLabel.CENTER);
        add(titulo, BorderLayout.NORTH);

        panelLista = new JPanel();
        panelLista.setLayout(new BoxLayout(panelLista, BoxLayout.Y_AXIS));
        panelLista.setBackground(Color.WHITE);

        scrollLista = new JScrollPane(panelLista);
        scrollLista.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollLista.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        add(scrollLista, BorderLayout.CENTER);
    }

    public void recargarLista() {
        panelLista.removeAll();

        if (padre == null || padre.getJugador() == null) {
            panelLista.revalidate();
            panelLista.repaint();
            return;
        }

        Entrenador jugador = padre.getJugador();
        ListaEnlazada equipo = jugador.getEquipo();
        int cantidad = equipo.contar();

        for (int i = 0; i < cantidad; i++) {
            Pokemon pokemon = equipo.obtenerPorIndice(i);

            String estado;

            if (pokemon.getHp() <= 0) {
                estado = "ELIMINADO";
            } else {
                estado = "DISPONIBLE";
            }

            String informacion = String.format(
                    "%-20s Vida: %-10s Estado: %s",
                    pokemon.getNombre(),
                    pokemon.getHp() + "/" + pokemon.getHpMaximo(),
                    estado
            );

            JLabel labelPokemon = new JLabel(
                    informacion,
                    obtenerSprite(pokemon.getNombre()),
                    JLabel.LEFT
            );

            labelPokemon.setFont(new Font("Monospaced", Font.PLAIN, 16));
            labelPokemon.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            labelPokemon.setOpaque(true);
            labelPokemon.setBackground(
                    pokemon.getHp() <= 0
                            ? Color.LIGHT_GRAY
                            : Color.WHITE
            );

            labelPokemon.setPreferredSize(new Dimension(500, 45));
            labelPokemon.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
            labelPokemon.setAlignmentX(LEFT_ALIGNMENT);
            labelPokemon.setIconTextGap(10);

            panelLista.add(labelPokemon);
            panelLista.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        panelLista.revalidate();
        panelLista.repaint();
        revalidate();
        repaint();
    }

    private ImageIcon obtenerSprite(String nombrePokemon) {
        String archivo = nombrePokemon;

        if (archivo.equalsIgnoreCase("Bulbasaur")) {
            archivo = "Bulbasour";
        }

        java.net.URL recurso = getClass().getResource(
                "/SpritesPokemons/"
                + archivo
                + "Sprite1.png"
        );

        if (recurso == null) {
            return null;
        }

        ImageIcon icono = new ImageIcon(recurso);

        return new ImageIcon(
                icono.getImage().getScaledInstance(
                        40,
                        40,
                        java.awt.Image.SCALE_SMOOTH
                )
        );
    }
}