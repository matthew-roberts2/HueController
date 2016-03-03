package matthew.huecon.gui;

import javax.swing.JButton;
import javax.swing.JTextField;

public class IPRequestGUI extends HueGUI{

	
	
	/**
	 *  Pre-Generated serialUID
	 */
	private static final long serialVersionUID = 8298625284927397996L;
	
	public IPRequestGUI(){
		super(100, 50, "IP Config");
	}
	
	@Override
	public void addObjects(){
		
	}
	
	class JButtonSubmit extends JButton{
		/**
		 * Pre-Generated serialUID
		 */
		private static final long serialVersionUID = 6394067113169935961L;

		JButtonSubmit(){
			
		}
	}
	
	class JIPTextBox extends JTextField{

		/**
		 * Pre-Generated serialUID
		 */
		private static final long serialVersionUID = 7247479137724829211L;
		
	}

}
