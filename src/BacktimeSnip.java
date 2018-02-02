import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.javasoft.plaf.synthetica.SyntheticaWhiteVisionLookAndFeel;
public class BacktimeSnip extends JFrame implements ActionListener{	
	//HA -> horarioChegadaBarbara
	JTextField HA = new JTextField("00:00:00", 8);
	JTextField DA = new JTextField("00:00:00", 8);
	JTextField HN = new JTextField("00:00:00", 8);
	JButton calcular = new JButton("Calcular :)");
	JButton func = new JButton("Instruções");
	JButton sobre = new JButton("Créditos");
	public BacktimeSnip(){
		
		super("Backtime Snip by streetboy");
		JPanel painel = new JPanel(new GridLayout(4, 1,1,1));
		JPanel inicio = new JPanel(new FlowLayout());
		JLabel label1 = new JLabel("Horário de chegada do SEU ataque:");
		painel.add(label1);
		painel.add(HA);
		JLabel label2 = new JLabel("Duração do SEU ataque:");
		painel.add(label2);
		painel.add(DA);
		JLabel label3 = new JLabel("Horário de chegada dos nobres:");
		painel.add(label3);
		painel.add(HN);
		
		calcular.addActionListener(this);
		func.addActionListener(this);
		sobre.addActionListener(this);
		calcular.setBackground(Color.ORANGE);
		calcular.setForeground(Color.BLACK);
		inicio.add(func);
		inicio.add(sobre);
		painel.setBounds(900, 900, 500, 500);
		
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout(15,15));//Espaçamento horizontal, vertical
		c.add(BorderLayout.NORTH, inicio);
		c.add(BorderLayout.SOUTH, calcular);
		c.add(BorderLayout.CENTER, painel);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(420,210);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(SyntheticaWhiteVisionLookAndFeel.class.getName());

		JOptionPane.showMessageDialog(null, "Antes de prosseguir, esteja ciente que:\n"
				+ "- O ataque que será mandado deve ter segundo ímpar ou par, dependendo do tempo de chegada dos nobres.\n"
				+ "- Após saber o horário de cancelamento, é preciso subtrair o tempo de delay da sua internet.\n"
				+ "- Você deve aplicar este método quando faltar 20 minutos OU MENOS para os nobres chegarem.\n"
				+ "- Você poderá cancelar o ataque e tentar novamente quantas vezes quiser até acertar o ms desejado.");
		new BacktimeSnip();
		

	}


	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == calcular){
			long longHA = 0;
			long longDA = 0;
			long longHE = 0;		
			long longHN = 0;			
			long longD = 0;			
			long longHC = 0;
			
			String horarioCancelamento = "";
			
			String split[] = new String[3];
			split = HA.getText().split(":");
			longHA += (Long.parseLong(split[0])) * 3600;
			longHA += (Long.parseLong(split[1])) * 60;
			longHA += (Long.parseLong(split[2]));
			
			split = DA.getText().split(":");
			longDA += (Long.parseLong(split[0])) * 3600;
			longDA += (Long.parseLong(split[1])) * 60;
			longDA += (Long.parseLong(split[2]));
			
			split = HN.getText().split(":");
			longHN += (Long.parseLong(split[0])) * 3600;
			longHN += (Long.parseLong(split[1])) * 60;
			longHN += (Long.parseLong(split[2]));
			
			longHE = longHA - longDA;
			
			if(longHE < 0) longHE *= -1;
			
			longD = longHN - longHE;
			
			longHC = (longD/2) + longHE;
			

			
			if((longHC / 3600) < 10) horarioCancelamento += "0" + (longHC / 3600) + ":";
			else horarioCancelamento += (longHC / 3600) + ":";
			longHC %= 3600;
			
			if((longHC / 60) < 10) horarioCancelamento += "0" + (longHC / 60) + ":";
			else horarioCancelamento += (longHC / 60) + ":";
			longHC %= 60;
			
			if(longHC < 10) horarioCancelamento += "0" + longHC;
			else horarioCancelamento += longHC;
			
			JOptionPane.showMessageDialog(null, "Horário de Cancelamento do seu ataque:\n" + horarioCancelamento);
			
		}
		
		if(e.getSource() == func){
			JOptionPane.showMessageDialog(null,  "- O ataque que será mandado deve ter segundo ímpar ou par, dependendo do tempo de chegada dos nobres.\n"
					+ "- Após saber o horário de cancelamento, é preciso subtrair o tempo de delay da sua internet.\n"
					+ "- Você deve aplicar este método quando faltar 20 minutos OU MENOS para os nobres chegarem.\n"
					+ "- Você poderá cancelar o ataque e tentar novamente quantas vezes quiser até acertar o ms desejado.");
		}
		
		if(e.getSource() == sobre){
			JOptionPane.showMessageDialog(null, "Esta aplicação foi implementada por streetboy");
			JOptionPane.showMessageDialog(null, new MessageWithLink("Os cálculos que determinam o horário de cancelamento são de autoria de arctic dragon e podem ser encontrados <a href=\"https://forum.tribalwars.com.br/showthread.php?272575-TUTORIAL-Guia-definitivo-de-snip-com-uma-aldeia-por-Arctic-Dragon\">aqui</a>"));
		}
		
	}

}
