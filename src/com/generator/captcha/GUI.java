package com.generator.captcha;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GUI extends UsefulJFrame implements ChangeListener {

	private static final long serialVersionUID = 1L;
	private JButton generate;
	private JTextField exportDir;
	private JButton export;
	private Generator tl;
	private Generator t2;
	private Generator t3;
	private Generator t4;
	private JSlider slider;
	private String directory = "/Users/dominiccarr/Downloads/";
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";

	public GUI(String t, int w, int h) {

		super(t, w, h);
		createGenerators();
		slider = new JSlider(JSlider.HORIZONTAL, 1, 200, 1);
		slider.setMajorTickSpacing(100);
		slider.setPaintTicks(true);
		slider.addChangeListener(this);
		panel.add(slider);
		setUpUIComponents();

		slider.setBounds(240, 210, 200, 30);

	}

	private void createGenerators() {
		tl = new Generator();
		t2 = new Generator();
		t3 = new Generator();
		t4 = new Generator();
		panel.add(tl);
		panel.add(t2);
		panel.add(t3);
		panel.add(t4);
		tl.setBounds(20, 10, 190, 95);
		t2.setBounds(250, 10, 190, 95);
		t3.setBounds(20, 110, 190, 95);
		t4.setBounds(250, 110, 190, 95);
	}

	private static String currTime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		return dateFormat.format(cal.getTime());
	}

	private void save(String directory, int amount) {
		File dir = new File(directory + "/" + currTime());
		dir.mkdir();
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(dir + "/"
					+ "filename.txt"));
			for (int a = 0; a < amount; a++) {
				Random rng = new Random();
				Generator aw = new Generator();
				String name = Integer.toString(rng.nextInt(1000000));
				File img = new File(dir + "/" + name + ".jpg");
				ImageIO.write(aw.getImage(), "jpg", img);
				writer.write(name + " = " + aw.getCode());
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
		}
	}

	private void setUpUIComponents() {
		generate = new JButton("More examples?");
		exportDir = new JTextField(directory);
		export = new JButton("Export " + slider.getValue());

		panel.add(generate);
		generate.setBounds(10, 210, 145, 30);
		panel.add(exportDir);
		exportDir.setBounds(5, 240, 300, 30);
		panel.add(export);
		export.setBounds(305, 240, 150, 30);

		export.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save(exportDir.getText(), slider.getValue());
			}
		});

		generate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.remove(tl);
				panel.remove(t2);
				panel.remove(t3);
				panel.remove(t4);
				createGenerators();
				panel.updateUI();
			}
		});

	}

	/**
	 * The change listener for the raise slider
	 */
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider) e.getSource();
		if (!source.getValueIsAdjusting()) {
			int bet = (int) ((JSlider) e.getSource()).getValue();
			export.setText("Export " + bet);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new GUI("Captcha Generator", 460, 300);
	}

}