package com.component;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.service.MobileNumberService;
import com.service.MobileNumberServiceImpl;

public class Drawing extends JFrame {
	private JFileChooser fileChooser;
	private JButton button;
	private JTextField textField;
	private JButton queryMobilLocationButton;
	MobileNumberService mobileNumberService;
	/**
	 * 
	 */
	private static final long serialVersionUID = -5245201950952809993L;
	public Drawing(){
		setTitle("��ȡ�ֻ��Ź�����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setBounds(100, 100, 545, 277);
		mobileNumberService = new MobileNumberServiceImpl();
		initComponent();
		addButtonActionListener();
		addQueryMobileLocationButtonActionListener();
		setVisible(true);
		
	}
	
	private void initComponent(){
		fileChooser = new JFileChooser();
		button = new JButton();
		button.setText("���");
		button.setFont(new Font("����", Font.BOLD, 18));
	    button.setBounds(432, 118, 87, 26);
	    textField = new JTextField();
	    textField.setVisible(true);
        textField.setBounds(100, 118, 299, 25);
        getContentPane().add(textField);
        textField.setColumns(10);
        queryMobilLocationButton = new JButton("��ȡ�ֻ����������");
        queryMobilLocationButton.setVisible(true);
        queryMobilLocationButton.setBounds(150, 150, 299, 25);
        getContentPane().add(queryMobilLocationButton);
		getContentPane().add(button);
		JLabel label = new JLabel();
		label.setText("ѡ���ļ�");
		label.setBounds(50, 118, 30, 25);
		label.setVisible(true);
		getContentPane().add(label);
	}
	
	private void addButtonActionListener(){
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = fileChooser.showOpenDialog(null);
				fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
				fileChooser.setMultiSelectionEnabled(false);
				fileChooser.setAcceptAllFileFilterUsed(false);
                if (index == JFileChooser.APPROVE_OPTION) {
                    // �ѻ�ȡ�����ļ��ľ���·����ʾ���ı��༭����
                    textField.setText(fileChooser.getSelectedFile()
                            .getAbsolutePath());
                }
			}
		});
	}
	
	private void addQueryMobileLocationButtonActionListener(){
		queryMobilLocationButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().equals(null)){
					JOptionPane.showMessageDialog(null, "��ѡ��excel�ļ���");
					return ;
				}
				if(!(textField.getText().endsWith(".xls")||textField.getText().endsWith(".xlsx"))){
					JOptionPane.showMessageDialog(null, "��ѡ�ļ�����excel�ĵ���");
					return ;
				}
				mobileNumberService.addMobileNumberLocationToDoc(textField.getText());
			}
		});
	}

	public JFileChooser getFileChooser() {
		return fileChooser;
	}

	public void setFileChooser(JFileChooser fileChooser) {
		this.fileChooser = fileChooser;
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}
	
}
