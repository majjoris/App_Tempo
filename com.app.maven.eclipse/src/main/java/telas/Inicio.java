package telas;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class Inicio extends JFrame {

	private JPanel contentPane;
	private JTextField cidade;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Inicio() throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Inicio.class.getResource("/img/weather-news.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		// img
		String path = "C:\\Users\\eulle\\OneDrive\\Documentos\\GitHub\\com.app.maven.eclipse\\src\\main\\java\\img\\weather-news.png";
		File file = new File(path);
		BufferedImage image = ImageIO.read(file);
		ImageIcon icon = new ImageIcon(
				"C:\\Users\\eulle\\OneDrive\\Documentos\\GitHub\\com.app.maven.eclipse\\src\\main\\java\\img\\weather-news.png");

		JLabel label = new JLabel(icon);

		contentPane.add(label);

		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(contentPane);
		// setContentPane(label);

		cidade = new JTextField();
		cidade.setText("Nome da cidade,Estado,Pais");
		cidade.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		cidade.setColumns(10);

		JButton procurar = new JButton("Pocurar");
		procurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				LocalDate date = LocalDate.now();
				SolicitarUrl urll = new SolicitarUrl();

				urll.setLocalizacao(cidade.getText());
				urll.setChave_api("76TNBX56HWG6LMD274ED3RXF7");
				urll.setData_inicio(date.toString());
				// urll.setData_fim("2023-09-15");
				try {
					urll.previsao_completa();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("erro");
					e1.printStackTrace();
				}

				Result frame = new Result();
				frame.setVisible(true);

			}
		});

		JLabel lblNewLabel = new JLabel("Previsão do Tempo");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(
						gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup().addContainerGap(138, Short.MAX_VALUE)
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 167,
												GroupLayout.PREFERRED_SIZE)
										.addGap(129))
								.addGroup(gl_contentPane.createSequentialGroup().addContainerGap(96, Short.MAX_VALUE)
										.addComponent(cidade, GroupLayout.PREFERRED_SIZE, 242,
												GroupLayout.PREFERRED_SIZE)
										.addGap(96))
								.addGroup(gl_contentPane.createSequentialGroup().addGap(175).addComponent(procurar)
										.addContainerGap(190, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(cidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(18).addComponent(procurar).addContainerGap(153, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}
}
