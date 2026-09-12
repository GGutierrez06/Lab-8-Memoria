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

public class GUICambiar extends JPanel {

    private GUIBatalla padre;

    private JPanel panelLista;
    private JScrollPane scrollLista;
    private JButton btnCerrar;

    private JButton[] botonesObjetos;

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

        crearBotonesTemporales();

        panelLista.revalidate();
        panelLista.repaint();

        revalidate();
        repaint();
    }

    private void crearBotonesTemporales() {
        botonesObjetos = new JButton[5];

        for (int i = 0; i < 5; i++) {
            JButton botonObjeto = new JButton("");

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

            botonObjeto.addActionListener(
                    new ActionListener() {
                @Override
                public void actionPerformed(
                        ActionEvent e
                ) {
                    usarObjeto(botonObjeto);
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

    private void usarObjeto(JButton botonObjeto) {
        recargarLista();
        mostrarHistorial();
    }

    public void mostrarHistorial() {
        padre.mostrarChat();
    }

}
