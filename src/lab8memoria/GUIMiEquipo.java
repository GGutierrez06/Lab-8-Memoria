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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class  GUIMiEquipo extends JPanel {

    private JPanel panelLista;
    private JScrollPane scrollLista;

    public  GUIMiEquipo(GUIBatalla padre) {
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

        for (int i = 0; i < 5; i++) {
            JLabel labelPokemon = new JLabel("Ejemplo " + i + "        Vida: " + (i + 2) + "        Estado: nulo");

            labelPokemon.setFont(new Font("Monospaced", Font.PLAIN, 16));
            labelPokemon.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            labelPokemon.setOpaque(true);
            labelPokemon.setBackground(Color.WHITE);
            labelPokemon.setPreferredSize(new Dimension(500, 45));
            labelPokemon.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
            labelPokemon.setAlignmentX(LEFT_ALIGNMENT);

            panelLista.add(labelPokemon);
            panelLista.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        panelLista.revalidate();
        panelLista.repaint();
        revalidate();
        repaint();
    }

}
