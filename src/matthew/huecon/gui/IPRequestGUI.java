package matthew.huecon.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class IPRequestGUI extends HueGUI{

	
	
	/**
	 *  Pre-Generated serialUID
	 */
	private static final long serialVersionUID = 8298625284927397996L;
	
	public IPRequestGUI(){
		super(150, 250, "IP Config");
	}
	
	@Override
	public void addObjects(){
		JPanel pane = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		this.add(pane);
		
		IPTextBox tb = new IPTextBox();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridwidth = 3;
		c.gridy = 0;
		c.insets = new Insets(10,10,10,10);
		
		pane.add(tb, c);
		
		ButtonSubmit b = new ButtonSubmit();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		
		pane.add(b,c);
		
	}
	
	class ButtonSubmit extends JButton{
		/**
		 * Pre-Generated serialUID
		 */
		private static final long serialVersionUID = 6394067113169935961L;

		ButtonSubmit(){
			super("Submit");
			this.setToolTipText("Submit the typed IP above");
		}
	}
	
	class IPTextBox extends JTextField{

		/**
		 * Pre-Generated serialUID
		 */
		private static final long serialVersionUID = 7247479137724829211L;
		
		IPTextBox(){
			super("IP");
			this.setToolTipText("IP's should be in the format of xxx.yyy.zzz.www");
			this.setPreferredSize(new Dimension(100, 20));
		}
		
	}

}
