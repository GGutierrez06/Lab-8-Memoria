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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import lab8memoria.modelo.Entrenador;
import lab8memoria.modelo.ListaObjetos;
import lab8memoria.modelo.Objeto;

public class GUIObjetos extends JPanel {

    private GUIBatalla padre;

    private JPanel panelLista;
    private JScrollPane scrollLista;
    private JButton btnCerrar;

    private JButton[] botonesObjetos;

    public GUIObjetos(GUIBatalla padre) {
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

        JLabel titulo = new JLabel("OBJETOS");
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

        crearBotonesTemporales();

        panelLista.revalidate();
        panelLista.repaint();

        revalidate();
        repaint();
    }

    private void crearBotonesTemporales() {
        Entrenador jugador = padre.getJugador();

        if (jugador == null) {
            botonesObjetos = new JButton[0];
            JLabel sinBatalla = new JLabel("Aun no hay una batalla en curso.");
            panelLista.add(sinBatalla);
            return;
        }

        ListaObjetos objetos = jugador.getObjetos();
        int total = objetos.contar();

        botonesObjetos = new JButton[total];

        if (total == 0) {
            panelLista.add(new JLabel("No tienes objetos disponibles."));
            return;
        }

        for (int i = 0; i < total; i++) {
            Objeto objeto = objetos.obtenerPorIndice(i);

            JButton botonObjeto = new JButton(textoBoton(objeto));

            botonObjeto.setFont(
                    new Font(
                            "Monospaced",
                            Font.PLAIN,
                            14
                    )
            );

            botonObjeto.setHorizontalAlignment(
                    JButton.LEFT
            );

            botonObjeto.setPreferredSize(
                    new Dimension(
                            500,
                            45
                    )
            );

            botonObjeto.setMaximumSize(
                    new Dimension(
                            Integer.MAX_VALUE,
                            45
                    )
            );

            botonObjeto.setAlignmentX(
                    LEFT_ALIGNMENT
            );

            botonObjeto.setFocusable(false);

            botonObjeto.setEnabled(objeto.getCantidad() > 0);

            final int indice = i;

            botonObjeto.addActionListener(
                    new ActionListener() {
                @Override
                public void actionPerformed(
                        ActionEvent e
                ) {
                    usarObjeto(indice);
                }
            });

            botonesObjetos[i] = botonObjeto;

            panelLista.add(botonObjeto);

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

    private String textoBoton(Objeto objeto) {
        return objeto.getNombre() + " - " + objeto.getDescripcion()
                + " (x" + objeto.getCantidad() + ")";
    }

    private void usarObjeto(int indice) {
        padre.usarObjetoDelJugador(indice);

        recargarLista();
        mostrarHistorial();
    }

    public void mostrarHistorial() {
        padre.mostrarChat();
    }

}
