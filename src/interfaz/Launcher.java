
package interfaz;

import java.awt.EventQueue;

public class Launcher {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFramePrincipal frame = new JFramePrincipal();
					//JFrameUsuario frame = new JFrameUsuario();
					//JFrameAdmin frame = new JFrameAdmin();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}