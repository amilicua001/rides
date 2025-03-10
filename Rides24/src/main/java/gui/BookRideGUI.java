package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.*;

import java.awt.GridLayout;
import java.awt.List;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class BookRideGUI extends JFrame {

	private JPanel ErreserbaPanel;
	private JTextField OharrakField;
	private JTextField OharraTitle;
	private JTextField EserlekuTitle;
	private JComboBox<Integer> EserlekuList;
	private JTextField Irteera;
	private JTextField Helmuga;
	private JTextField Data;

	private JButton jButtonClose;
	private JLabel lblError;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookRideGUI frame = new BookRideGUI(null, null);
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
	public BookRideGUI(Ride ride, User user) {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("BookRideGUI.title")); //$NON-NLS-1$ //$NON-NLS-2$
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 445, 339);
		ErreserbaPanel = new JPanel();
		ErreserbaPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(ErreserbaPanel);
		ErreserbaPanel.setLayout(null);
		
		JButton btnErreserbatu = new JButton();
		btnErreserbatu.setText(ResourceBundle.getBundle("Etiquetas").getString("BookRideGUI.Erreserbatu"));
		btnErreserbatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int eserlekuKop = (int) EserlekuList.getSelectedItem();
				Booking erreserba = new Booking(ride.getDriver(), (Traveler) user, ride, eserlekuKop);
				
				BLFacade facade = MainGUI.getBusinessLogic(); 
				Boolean gordeDa = facade.guardarBooking(erreserba);
				
				if(gordeDa) {
					lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("BookRideGUI.gordeDa"));
				}else {
					lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("BookRideGUI.EzDaGorde"));
				}
			}
		});
		btnErreserbatu.setBounds(75, 202, 126, 51);
		ErreserbaPanel.add(btnErreserbatu);
		
		OharrakField = new JTextField();
		OharrakField.setBounds(33, 131, 378, 63);
		ErreserbaPanel.add(OharrakField);
		OharrakField.setColumns(10);
		
		OharraTitle = new JTextField();
		OharraTitle.setText(ResourceBundle.getBundle("Etiquetas").getString("BookRideGUI.OharraTitle"));
		OharraTitle.setEditable(false);
		OharraTitle.setBounds(33, 102, 96, 19);
		ErreserbaPanel.add(OharraTitle);
		OharraTitle.setColumns(10);
		
		
		
		EserlekuTitle = new JTextField();
		EserlekuTitle.setEnabled(true);
		EserlekuTitle.setEditable(false);
		EserlekuTitle.setText(ResourceBundle.getBundle("Etiquetas").getString("BookRideGUI.EserlekuTitle"));
		EserlekuTitle.setBounds(33, 71, 125, 21);
		ErreserbaPanel.add(EserlekuTitle);
		EserlekuTitle.setColumns(10);
		
		Irteera = new JTextField();
		Irteera.setText(ResourceBundle.getBundle("Etiquetas").getString("BookRideGUI.Irteera") + ": " + ride.getFrom());
		Irteera.setEditable(false);
		Irteera.setBounds(47, 10, 153, 19);
		ErreserbaPanel.add(Irteera);
		Irteera.setColumns(10);
		
		Helmuga = new JTextField();
		Helmuga.setText(ResourceBundle.getBundle("Etiquetas").getString("BookRideGUI.Helmuga") + ": " + ride.getTo());
		Helmuga.setEditable(false);
		Helmuga.setBounds(242, 10, 156, 19);
		ErreserbaPanel.add(Helmuga);
		Helmuga.setColumns(10);
		
		Data = new JTextField();
		Data.setEditable(false);
		Data.setText(ResourceBundle.getBundle("Etiquetas").getString("BookRideGUI.Data") + ": " + ride.getDate());
		Data.setBounds(252, 39, 146, 19);
		ErreserbaPanel.add(Data);
		Data.setColumns(10);
				
		ArrayList<Integer> eserlekuKop = new ArrayList();
		for(int i=1; i<=(int)ride.getnPlaces(); i++) {
			eserlekuKop.add(i);
		}
		DefaultComboBoxModel<Integer> comboBoxModel = new DefaultComboBoxModel(eserlekuKop.toArray());
        EserlekuList = new JComboBox<>(comboBoxModel);
		EserlekuList.setBounds(183, 71, 46, 21);
		ErreserbaPanel.add(EserlekuList);
		
		jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					jButtonClose_actionPerformed(e);
			}
		});
		jButtonClose.setBounds(225, 202, 146, 51);
		ErreserbaPanel.add(jButtonClose);
		
		lblError = new JLabel("");
		lblError.setBounds(36, 263, 335, 29);
		ErreserbaPanel.add(lblError);

	}
	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
