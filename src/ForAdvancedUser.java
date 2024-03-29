import java.awt.Color;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class ForAdvancedUser extends JPanel  implements ActionListener{

	private static final long serialVersionUID = 1L;
	JFrame frame = new JFrame();
	JPanel panel= new JPanel();;
	JButton ok;
	JButton remove2= new JButton("Clean up");
	JButton switchJava= new JButton("Java interface");

	JTextField question1 = new JTextField(EssentialFunctions.getUpDateSource(),21);
	JTextField question0 = new JTextField(EssentialFunctions.getMainScreen(),21);
	JTextField question2 = new JTextField(EssentialFunctions.getSuperGoodUIS(), 21);
	JTextField question4 = new JTextField(EssentialFunctions.getUpDaterSource(), 21);
	JButton changeGame;
	JLabel instruc = new JLabel("Checking update source:");
	JLabel instruc1 = new JLabel("Checking Hash for MainScreen.class source");
	JLabel instruc2 = new JLabel("Checking Hash for TheGame.class source");
	JLabel instruc4 = new JLabel("Downloading Updater Source");
	JLabel instruc5 = new JLabel("Be careful of untrusted sources; the only trusted source (til now) is my github");

	public ForAdvancedUser() {
		frame = new JFrame("Advanced setup"); 
		try {
			frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ok = new JButton("Ok");
		changeGame = new JButton("Change game");

		if ( Boolean.parseBoolean(EssentialFunctions.getFistLine("/lang.txt"))) {
			instruc.setText("Comprobando la fuente de actualización");
			instruc1.setText("Comprobando hash para la fuente MainScreen.class");
			instruc2.setText("Comprobando hash para la fuente TheGame.class");
			instruc4.setText("Descargando la fuente Updater");
			instruc5.setText("Se cuidadoso con las fuentes no confiables; la única fuente confiable (hasta ahora) es mi github");
			changeGame.setText("Cambiar juego");
			remove2.setText("Limpiar");
			switchJava.setText("Interfaz java");
		} 

		instruc5.setForeground(Color.RED);
		panel.add(instruc);
		panel.add(question1);
		panel.add(instruc1);
		panel.add(question0);
		panel.add(instruc2);
		panel.add(question2);
		panel.add(instruc4);
		panel.add(question4);
		panel.add(ok);
		panel.add(instruc5);
		panel.add(changeGame);
		panel.add(remove2);
		panel.add(switchJava);
		
		ok.addActionListener(this);
		remove2.addActionListener(this);
		switchJava.addActionListener(this);

		changeGame.addActionListener(this);

		frame.setResizable(false);
		frame.setSize(600, 300); 
		frame.setFocusable(true); 
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent evt) {
				frame.dispose();
			}
		});
		frame.add(panel);
		frame.setVisible(true); 

	}

	public void newSource(String namesource, String where) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(System.getProperty("user.dir")+where);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		writer.print(namesource);
		writer.close();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand(); 
		if(s.equals("Ok")) {
			newSource(question1.getText(), "/updateSource.txt");
			newSource(question0.getText(), "/MainScreen.txt");
			newSource(question2.getText(), "/TheGame.txt");
			newSource(question4.getText(), "/UpdaterSource.txt");
		}
		if(e.getSource() == switchJava) {
			
			EssentialFunctions.WriteInfo("/javaInter.txt", !Boolean.parseBoolean(EssentialFunctions.getFistLine("/javaInter.txt")));
			EssentialFunctions.popUp("Restart to enact changes","Restart");

			
		}
		if(e.getSource() == remove2) {
			if (EssentialFunctions.confirm("By cleaning this, you delete all data the program created\n you sure you want to continue?") ==0) {
				File file[] = new File[11]; 
				file[0] = new File(System.getProperty("user.dir")+"/nightmode.txt");
				file[1] = new File(System.getProperty("user.dir")+"/usercre.txt");
				file[2] = new File(System.getProperty("user.dir")+"/log.txt");
				file[3] = new File(System.getProperty("user.dir")+"/lang.txt");
				file[4] = new File(System.getProperty("user.dir")+"/keepL.txt");
				file[5] = new File(System.getProperty("user.dir")+"/highscore.txt");
				file[6] = new File(System.getProperty("user.dir")+"/TheGame.txt");
				file[7] = new File(System.getProperty("user.dir")+"/MainScreen.txt");
				file[8] = new File(System.getProperty("user.dir")+"/updateSource.txt");
				file[9] = new File(System.getProperty("user.dir")+"/UpdaterSource.txt");
				file[10] = new File(System.getProperty("user.dir")+"/javaInter.txt");

				for (int i=0; i< file.length; i++) {
					if(file[i].delete()) { 
						System.out.println(file[i] +" deleted successfully"); 
						EssentialFunctions.popUp(file[i] +" deleted successfully","Clean-up");
					} 
					else{ 
						System.out.println("Failed to delete "+ file[i]); 
						EssentialFunctions.popUp("Failed to delete "+ file[i],"Clean-up");

					} 
				}

			}

		}
		if (s.equals("Change game") || s.equals("Cambiar juego")) {
			if (Desktop.isDesktopSupported()) {
				Desktop desktop = Desktop.getDesktop();
				try {
					URI uri = new URI("https://frychicken.github.io/PhEntry/docs/index.html");
					desktop.browse(uri);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}

	}
}
