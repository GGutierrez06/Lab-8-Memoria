package lab8memoria;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import lab8memoria.logica.Batalla;

public class VentanaHistorial extends JPanel {

    private Batalla batalla;
    private JTextArea areaHistorial;
    private JTextArea areaEstadisticas;

    public VentanaHistorial(Batalla batalla) {
        this.batalla = batalla;
        inicializarComponentes();
        actualizar();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout());

        areaHistorial = new JTextArea();
        areaHistorial.setEditable(false);
        areaHistorial.setLineWrap(true);
        areaHistorial.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(areaHistorial);
        add(scroll, BorderLayout.CENTER);

        areaEstadisticas = new JTextArea();
        areaEstadisticas.setEditable(false);
        add(areaEstadisticas, BorderLayout.SOUTH);

        JButton botonActualizar = new JButton("Actualizar historial");
        botonActualizar.setFocusable(false);
        botonActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                actualizar();
            }
        });
        add(botonActualizar, BorderLayout.NORTH);
    }

    public void actualizar() {
        areaHistorial.setText(batalla.getHistorial().recorrer());
        areaEstadisticas.setText(batalla.estadisticas());
    }
}
