package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;

@SuppressWarnings("serial")
public class Result extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Result frame = new Result();
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
	public Result() {
		String endereco = null, datadia = null;
		double tempmax = 0.0, tempmin = 0.0, temp = 0.0, humidity = 0.0;
		Time sunrise=null, sunset=null;
		int uvindex = 0;
		
		LocalDate today = LocalDate.now();
        String day = today.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("pt-BR"));
        
		ConexaoBD conexao = new ConexaoBD();
		conexao.conectar();
		
		try {
			// Inicio consulta
			String consulta = "SELECT  endereco,datadia,tempmax,tempmin,temperatura,humidity,uvindex,sunrise,sunset FROM  public.dados WHERE datadia='"+ today.toString()+"' ;";
			// String consulta="SELECT * FROM public.dados";
			ResultSet dados = conexao.executarConsulta(consulta);
			if (dados.next()) {
				endereco = dados.getString("endereco");
				datadia = dados.getString("datadia");
				tempmax = dados.getDouble("tempmax");
				tempmin = dados.getDouble("tempmin");
				temp = dados.getDouble("temperatura");
				humidity = dados.getDouble("humidity");
				sunrise = dados.getTime("sunrise");
				sunset = dados.getTime("sunset");
				uvindex = dados.getInt("uvindex");
			}
		} catch (SQLException ex) {
			System.out.println("Nao conseguiu consultar os dados.");
		} finally {
			conexao.desconectar();
		}

		// Fim consulta
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 398, 411);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		JLabel cidade = new JLabel(endereco);
		cidade.setForeground(new Color(255, 255, 255));
		cidade.setFont(new Font("Verdana", Font.PLAIN, 16));

		JLabel data = new JLabel(datadia);
		data.setForeground(new Color(255, 255, 255));
		data.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel dia = new JLabel(day.toUpperCase());
		dia.setForeground(Color.WHITE);
		dia.setFont(new Font("Verdana", Font.PLAIN, 16));

		JLabel temperatura = new JLabel(temp + "º");
		temperatura.setForeground(Color.WHITE);
		temperatura.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 75));

		JLabel lblMax = new JLabel("MAX:" + tempmax + "º");
		lblMax.setForeground(Color.WHITE);
		lblMax.setFont(new Font("Verdana", Font.PLAIN, 16));

		JLabel lblMim = new JLabel("MIN:" + tempmin + "º");
		lblMim.setForeground(Color.WHITE);
		lblMim.setFont(new Font("Verdana", Font.PLAIN, 16));

		JLabel NascSol = new JLabel("Nasc. do Sol:" + sunrise);
		NascSol.setForeground(Color.WHITE);
		NascSol.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel PorDoSol = new JLabel("Por do Sol:" + sunset);
		PorDoSol.setForeground(Color.WHITE);
		PorDoSol.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel Humidade = new JLabel("Humidade:" + humidity + "%");
		Humidade.setForeground(Color.WHITE);
		Humidade.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel Uv = new JLabel("Índice ultravioleta:" + uvindex);
		Uv.setForeground(Color.WHITE);
		Uv.setFont(new Font("Tahoma", Font.PLAIN, 16));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(cidade, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(dia, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
								.addComponent(data, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblMax, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
								.addGap(67)
								.addComponent(lblMim, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
						.addComponent(temperatura)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(Humidade, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(PorDoSol, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(NascSol, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 164,
										Short.MAX_VALUE)
								.addComponent(Uv, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 184,
										GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(data, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(dia, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(cidade, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(temperatura, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMax, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMim, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(NascSol, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(PorDoSol, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(Humidade, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(Uv, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		contentPane.setLayout(gl_contentPane);

	}
}
