/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab8memoria;

/**
 *
 * @author denam
 */

   

import javax.swing.*;
import java.awt.*;

public class GUICrearCuenta extends JPanel {

    private JTextField txtUsuario;
    private JPasswordField txtContra;
    private JPasswordField txtConfirmarContra;
    private JLabel labelMensaje;
    private Timer tempo;
    private CardLayout cardLayout;
    private JPanel cards;

    public GUICrearCuenta(CardLayout cardLayout, JPanel cards, GUIPantalla padre) {
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

        JLabel mensaje = new JLabel("Tienes cuenta?");
        mensaje.setForeground(Color.WHITE);

        JButton btnLogin = new JButton("Log in");
        btnLogin.setFocusable(false);

        btnLogin.addActionListener(e -> {
            limpiarCampos();
            limpiarCampos();
            padre.mostrarCard("login");
        });

        barraSuperior.add(mensaje);
        barraSuperior.add(btnLogin);

        add(barraSuperior, BorderLayout.NORTH);

      
        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 8, 6, 8);

        JLabel titulo = new JLabel("CREAR CUENTA");
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

        JLabel lblConfirmarContra = new JLabel("Confirmar contraseña");
        lblConfirmarContra.setForeground(Color.WHITE);

        gbc.gridy = 5;
        formulario.add(lblConfirmarContra, gbc);

        txtConfirmarContra = new JPasswordField();
        txtConfirmarContra.setFont(new Font("Arial", Font.PLAIN, 15));

        gbc.gridy = 6;
        formulario.add(txtConfirmarContra, gbc);

        labelMensaje = new JLabel("");
        labelMensaje.setForeground(Color.RED);
        labelMensaje.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridy = 7;
        formulario.add(labelMensaje, gbc);

        JButton btnCrear = new JButton("Crear cuenta");
        btnCrear.setFont(new Font("Arial", Font.BOLD, 15));
        btnCrear.setFocusable(false);

        btnCrear.addActionListener(e -> crearCuenta());

        gbc.gridy = 8;
        gbc.insets = new Insets(18, 8, 8, 8);
        formulario.add(btnCrear, gbc);

        add(formulario, BorderLayout.CENTER);
    }

    private void crearCuenta() {
        String usuario = txtUsuario.getText().trim();
        char[] contra = txtContra.getPassword();
        char[] confirmarContra = txtConfirmarContra.getPassword();

        if (usuario.isEmpty()) {
            labelMensaje.setVisible(true);
            labelMensaje.setText("Ingrese un usuario");
            tempo.start();
            return;
        }

        if (contra.length == 0) {
            labelMensaje.setVisible(true);
            labelMensaje.setText("Ingrese una contraseña");
            tempo.start();
            return;
        }

        if (!java.util.Arrays.equals(contra, confirmarContra)) {
            labelMensaje.setVisible(true);
            labelMensaje.setText("Las contraseñas no coinciden");
            tempo.start();
            return;
        }

        /*
         * Aquí debes conectar tu ArchivoUsuarioWin.
         *
         * Ejemplo:
         *
         * ArchivoUsuarioWin archivo = new ArchivoUsuarioWin();
         * UsuarioWin usuarioNuevo = new UsuarioWin(
         *         usuario,
         *         contra,
         *         false
         * );
         *
         * archivo.agregarUsuario(usuarioNuevo);
         */

        

        limpiarCampos();
   
    }

    public void limpiarCampos() {
        txtUsuario.setText("");
        txtContra.setText("");
        txtConfirmarContra.setText("");
        labelMensaje.setText("");
    }
}