package info.hofOnesimus.visual;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import info.hofOnesimus.main.Main;

public class Frame extends JFrame {
    private JTextArea inputText;
    private JTextArea actualHtml;
    
    private Main m;
    private String html = "";
    private ArrayList<String> undos = new ArrayList<>();
    private File selectedFile = new File(Main.STD_FILE);
    
    public Frame(Main m) {
    	this();
    	this.m = m;
    }
    
    
    public Frame() throws HeadlessException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle("Html Creator: "+ selectedFile.getName());
        this.setMinimumSize(new Dimension(Main.STD_SIZE,600));
        this.setPreferredSize(new Dimension(Main.STD_SIZE,600));

        this.setLayout(new BorderLayout());

        inputText = new JTextArea();
        actualHtml = new JTextArea();

        JPanel menu = new JPanel();
        menu.setPreferredSize(new Dimension(Main.STD_SIZE,100));
        menu.setLayout(new GridLayout(2,2));

        JButton buttonB = new JButton("Textblock/Absatz");
        buttonB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(!inputText.getText().equals("")) {
            		undos.add(html);
            		String tmp = "<p>";
            		tmp+=inputText.getText();
            		tmp+="</p>\n";
            		html+=tmp;
                	inputText.setText("");
                	inputText.requestFocus();
            	}
            	actualHtml.setText(html);
                repaint();
            }
        });
        menu.add(buttonB);
        
        JButton buttonZ = new JButton("Überschrift");
        buttonZ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(!inputText.getText().equals("")) {
            		undos.add(html);
            		String tmp = "<H2>";
            		tmp+=inputText.getText();
            		tmp+="</H2>\n";
            		html+=tmp;
                	inputText.setText("");
                	inputText.requestFocus();
            	}
            	actualHtml.setText(html);
                repaint();
            }
        });
        menu.add(buttonZ);

        JButton buttonC = new JButton("Wähle HTML-Datei (Standard: Aktuelles.html)");
        buttonC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JFileChooser fileChooser = new JFileChooser();
            	FileNameExtensionFilter filter = new FileNameExtensionFilter("HTML-Dateien (*.html; *.htm)", "html","htm");
            	fileChooser.setFileFilter(filter);
            	fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            	int result = fileChooser.showOpenDialog(menu);
            	if (result == JFileChooser.APPROVE_OPTION) {
            	    selectedFile = fileChooser.getSelectedFile();
            	    setTitle("Html Creator: "+ selectedFile.getName());
            	}
            	inputText.requestFocus();
            	actualHtml.setText(html);
                repaint();
            }
        });
        menu.add(buttonC);
        
        JButton buttonD = new JButton("Rückgängig");
        buttonD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(undos.size()!=0) {
            		html = undos.get(undos.size()-1);
            		undos.remove(html);
            	}
        		actualHtml.setText(html);
                repaint();
                inputText.requestFocus();
            }
        });
        menu.add(buttonD);
        
        JButton buttonE = new JButton("Speichern und Exportieren");
        buttonE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(html.length()>=3) {
            		m.export(html, selectedFile);
            		actualHtml.setText(selectedFile.getName()+" erfolgreich aktualisiert!\n"+"Rufe die bearbeitete Seite in deinem Browser auf und überprüfe sie."
            				+ "\nFalls schon geöffnet, drücke im Browser einfach F5.");
            		inputText.setText("");
            		html = "";
            	}else {
					actualHtml.setText("Erst Text hinzufügen, bevor du exportierst!");
				}
                repaint();
            }
        });
        menu.add(buttonE);
        
        JButton buttonF = new JButton("Bild einfügen");
        buttonF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JFileChooser fileChooser = new JFileChooser();
            	FileNameExtensionFilter filter = new FileNameExtensionFilter("Bilddateien (*.jpg; *.jpeg; *.png; *.gif)", "jpg","jpeg","png","gif");
            	fileChooser.setFileFilter(filter);
            	fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            	int result = fileChooser.showOpenDialog(menu);

            	if (result == JFileChooser.APPROVE_OPTION) {
            	    File selectedFile = fileChooser.getSelectedFile();
            	    String tmp = "<figure class=\"gesamt\">\n"
            				+ "<figure class=\"einzel\">"
            				+ "<img src=\"images/"+selectedFile.getName()+"\">";
            		
            		tmp+="</figure>\n</figure>\n";
            		undos.add(html);
            		html+=tmp;
                	inputText.setText("");
                	inputText.requestFocus();
            	}
            	actualHtml.setText(html);
            	
                repaint();
            }
        });
        menu.add(buttonF);
        
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
        //actualHtml.setPreferredSize(new Dimension(Main.STD_SIZE, 100));
        actualHtml.setText("Hier erscheint der generierte HTML-Code");
        actualHtml.setFont(font);
        actualHtml.setLineWrap(true);
		actualHtml.setWrapStyleWord(true);
		actualHtml.setEditable(false);
		actualHtml.setBackground(Color.lightGray);
        this.add(actualHtml, BorderLayout.CENTER);
        
        //inputText.setPreferredSize(new Dimension(Main.STD_SIZE, 150));
        inputText.setFont(font);
        inputText.setLineWrap(true);
		actualHtml.setWrapStyleWord(true);
		inputText.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				actualHtml.setText(html+inputText.getText());
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		
        this.add(inputText, BorderLayout.SOUTH);

        this.add(menu, BorderLayout.NORTH);

        this.setVisible(true);
    }

}