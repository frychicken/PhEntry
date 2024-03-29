import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class SuperGoodUI extends EssentialFunctions implements ActionListener{
	private static final long serialVersionUID = 1L;
	public SuperGoodUI() {
		frame = new JFrame("Nice register UI");  
		try {
			frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));
		} catch (Exception e) {
			e.printStackTrace();
			wl.writeLog(e.toString());
		}
		frame.setResizable(false);
		frame.setSize(800, 800); 
		frame.setFocusable(true); 
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE); 
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent evt) {
				wl.writeLog("Terminated by user");
				System.out.println("Terminated by user");
				long endTime = System.currentTimeMillis();
				long timenee =  ((endTime- OverEngi.startTime) /1000);
				System.out.println("Time executed: "+ timenee + " seconds");
				wl.writeLog("Time executed: " + timenee + " seconds");
				System.exit(0);
			}
		});
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				periodicPop();
			}
		};
		Timer timer = new Timer();
		long delay = 1;
		long intevalPeriod = 1 * 30000; 
		timer.scheduleAtFixedRate(task, delay, intevalPeriod);
		privacyP.setSelected(true);
		privacyP.addActionListener(this);
		frame.add(new ControlPanel(), BorderLayout.NORTH);
		frame.add(privacyP, BorderLayout.SOUTH);
		frame.setVisible(true); 

	}
	private void periodicPop() {
		if(!HidePopUpOPtion.hideRP)
			if(Math.random() < 0.5) {
				if( Boolean.parseBoolean(getFistLine("/lang.txt")))
					popUp("Recuerda calificar esta aplicación con 5 estrellas en la tienda!!!","importante");
				else
					popUp("Remember to rate this app 5 stars on the store!!!","Improtant");
			}
			else {
				if( Boolean.parseBoolean(getFistLine("/lang.txt")))
					popUp("Nosotros no usamos COOKIES, así que tienes que aceptar seleccionando OK","importante");
				else
					popUp("We don't use cookies, so you need to accept by clicking ok!!","Important");

			}

	}
	@Override
	public void actionPerformed(ActionEvent e) {	
		if(privacyP.isSelected()) {
			if(!hideRP)
				popUp("You gotta accept the terms and conditions to continue", "About");
			Accepted = false;
		} else {
			if(!hideRP)
				popUp("Make sure you read the terms and conditions\n are you sure you accept it?", "About");
			Accepted = true;

		}		
	}



}
