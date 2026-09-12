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

public class GUILogin extends JPanel {

    private JTextField txtUsuario;
    private JPasswordField txtContra;
    private JLabel labelMensaje;

    private Timer tempo;
    private CardLayout cardLayout;
    private JPanel cards;

    public GUILogin(CardLayout cardLayout, JPanel cards, GUIPantalla padre) {
        this.cardLayout = cardLayout;
        this.cards = cards;

        inicializarComponentes(padre);
        inicializarTimer();
    }
    public void inicializarTimer() {
        tempo = new Timer(2100, ev -> {
            labelMensaje.setVisible(false);
            repaint();
            tempo.stop();
        });
    }

    private void inicializarComponentes(GUIPantalla padre) {
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 45));

       
        JPanel barraSuperior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        barraSuperior.setBackground(new Color(20, 20, 35));

        JLabel mensaje = new JLabel("¿No tienes cuenta?");
        mensaje.setForeground(Color.WHITE);

        JButton btnCrearCuenta = new JButton("Crear cuenta");
        btnCrearCuenta.setFocusable(false);

        btnCrearCuenta.addActionListener(e -> {
            limpiarCampos();
            padre.mostrarCard("crear");
        });

        barraSuperior.add(mensaje);
        barraSuperior.add(btnCrearCuenta);

        add(barraSuperior, BorderLayout.NORTH);

        
        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 8, 8, 8);

        JLabel titulo = new JLabel("INICIAR SESIÓN");
        titulo.setFont(new Font("Arial", Font.BOLD, 25));
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridy = 0;
        formulario.add(titulo, gbc);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setForeground(Color.WHITE);

        gbc.gridy = 1;
        formulario.add(lblUsuario, gbc);

        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 15));

        gbc.gridy = 2;
        formulario.add(txtUsuario, gbc);

        JLabel lblContra = new JLabel("Contraseña");
        lblContra.setForeground(Color.WHITE);

        gbc.gridy = 3;
        formulario.add(lblContra, gbc);

        txtContra = new JPasswordField();
        txtContra.setFont(new Font("Arial", Font.PLAIN, 15));

        gbc.gridy = 4;
        formulario.add(txtContra, gbc);

        labelMensaje = new JLabel("");
        labelMensaje.setForeground(Color.RED);
        labelMensaje.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridy = 5;
        formulario.add(labelMensaje, gbc);

        JButton btnLogin = new JButton("Log in");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 15));
        btnLogin.setFocusable(false);

        btnLogin.addActionListener(e -> iniciarSesion());

        gbc.gridy = 6;
        gbc.insets = new Insets(20, 8, 8, 8);
        formulario.add(btnLogin, gbc);

        add(formulario, BorderLayout.CENTER);
    }

    private void iniciarSesion() {
        String usuario = txtUsuario.getText().trim();
        char[] contra = txtContra.getPassword();

        if (usuario.isEmpty() || contra.length == 0) {
            labelMensaje.setVisible(true);
            labelMensaje.setText("Ingrese usuario y contraseña");
            tempo.start();
            return;
        }

        ////////////////////////////////////////////////////////////////////////////comprobar si existe

        
    }

    public void limpiarCampos() {
        txtUsuario.setText("");
        txtContra.setText("");
        labelMensaje.setText("");
    }

}
