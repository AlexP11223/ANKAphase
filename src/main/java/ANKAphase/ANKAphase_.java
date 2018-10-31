package ANKAphase;

import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.plugin.BrowserLauncher;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.URL;
import java.util.Arrays;
import java.util.NoSuchElementException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import ReadEDF.ImportDialog;
import ReadEDF.ReadHeader;

public class ANKAphase_ extends JFrame implements PlugIn {

	private static final long serialVersionUID = 1L;
	private JFrame thisFrame = this;
	private JPanel jContentPane = null;
	private JMenuBar mainMenuBar = null;
	private JMenu fileMenu = null;
	private JMenuItem exitMenuItem = null;
	private JMenu helpMenu = null;
	private JMenuItem aboutMenuItem = null;
	private JMenuItem helpMenuItem = null;
	private JLabel generalLabel = null;
	private JLabel projDirLabel = null;
	private JTextField projDirText = null;
	private JButton browseProjButton = null;
	private JCheckBox darkCheckBox = null;
	private JTextField darkDirText = null;
	private JButton browseDarkButton = null;
	private JCheckBox flat1CheckBox = null;
	private JTextField flat1DirText = null;
	private JButton flat1BrowseButton = null;
	private JCheckBox flat2DirCheckBox = null;
	private JTextField flat2DirText = null;
	private JButton flat2BrowseButton = null;
	private JPanel linePanel1 = null;
	private JPanel linePanel4 = null;
	private JLabel flatSetLabel = null;
	private JLabel ReferenzLabel = null;
	private JCheckBox saveFlatCheckBox = null;
	private JLabel saveFlatPathLabel = null;
	private JTextField saveFlatPathText = null;
	private JButton saveFlatBrowseButton = null;
	private JLabel saveFlatNameLabel = null;
	private JTextField saveNameFlatText = null;
	private JLabel formatFlatLabel = null;
	private JComboBox formatFlatCombo = null;
	private JLabel scalingFlatLabel = null;
	private JComboBox scalingFlatCombo = null;
	private JLabel scalingFlatFromLabel = null;
	private JTextField scalingFlatFromText = null;
	private JLabel scalingFlatToLabel = null;
	private JTextField scalingFlatToText = null;
	private JPanel linePanel2 = null;
	private JLabel phaseretrievalSetLabel = null;
	private JCheckBox savePhaseCheckBox = null;
	private JCheckBox DeltaBetaCheckBox = null;
	private JCheckBox ImageRestoreCheckBox = null;
	private JLabel experimentParaLabel = null;
	private JLabel phaseretrievalParaLabel = null;
	private JLabel betaLabel = null;
	private JTextField betaText = null;
	private JLabel betaUnitLabel = null;
	private JLabel deltaLabel = null;
	private JTextField deltaText = null;
	private JLabel deltaUntitLabel = null;
	private JLabel ImageRestoreGaussLabel = null;
	private JLabel ImageRestoreGaussUnit = null;
	private JLabel ImageRestoreOffsetLabel = null;
	private JTextField DeltaBetaValue = null;
	private JTextField ImageRestoreGaussValue = null;
	private JTextField ImageRestoreOffsetValue = null;
	private JLabel distanceLabel = null;
	private JTextField distanceText = null;
	private JLabel distanceUnitLabel = null;
	private JLabel energyLabel = null;
	private JTextField energyText = null;
	private JLabel energyUnitLabel = null;
	private JLabel pixSizeLabel = null;
	private JTextField pixSizeText = null;
	private JLabel pixSizeUnitLabel = null;
	private JLabel savePhasePathLabel = null;
	private JTextField savePhasePathText = null;
	private JButton savePhaseBrowseButton = null;
	private JLabel savePhaseNameLabel = null;
	private JTextField saveNamePhaseText = null;
	private JLabel formatPhaseLabel = null;
	private JComboBox formatPhaseCombo = null;
	private JPanel linePanel3 = null;
	private JCheckBox calculateImagesFromCheckBox = null;
	private JTextField calculateImagesFromText = null;
	private JLabel calculateImagesToLabel = null;
	private JTextField calculateImagesToText = null;
	private JCheckBox showImagesCheckBox = null;
	private JLabel CPUsLabel = null;
	private JTextField CPUsText = null;
	private JButton stopButton = null;
	private JButton runButton = null;  //  @jve:decl-index=0:
	private JLabel statusLabel = null;
	private JProgressBar ProgressBar = null;
	private JLabel scalingPhaseLabel = null;
	private JComboBox scalingPhaseCombo = null;
	private JLabel scalingPhaseFromLabel = null;
	private JTextField scalingPhaseFromText = null;
	private JLabel scalingPhaseToLabel = null;
	private JTextField scalingPhaseToText = null;
	
	private String[] formatsArr = {"TIFF 8 bit", "TIFF 16 bit","TIFF float", "JPEG 8 bit","PNG 8 bit","BMP 8 bit", "EDF float"};
	private String[] scaleOptArr = {"Scale each image to its min/max", "Scale to 3*(max-min) of first image", "Scale to user-specified value range"};
	
	private String actDirectory;
	private String pathnameProj;  //  @jve:decl-index=0:
	private String pathnameDark;  //  @jve:decl-index=0:
	private String pathnameFlat1;
	private String pathnameFlat2;
	private boolean checkDark;
	private boolean checkFlat1;
	private boolean checkFlat2;
	
	private boolean	checkSaveFlatCorrectedImages;
	private String pathnameFlatOut;
	private String saveNameFlat;
	private String scalingFlatFrom;
	private int formatFlat;
	private int scaleFlat;
	private String scalingFlatTo;
	
	private String betaString;
	private String deltaString;
	private String DeltaBetaString;
	private String ImageRestoreGaussString;
	private String ImageRestoreOffsetString;
	private String distanceString;
	private String energyString;
	private String pixSizeString;
	private float beta;
	private float delta;
	private float deltabeta;
	private float gauss;
	private float offset;
	private float distance;
	private float energy;
	private float pixSize;
	private boolean	checkSavePhaseCorrectedImages;
	private boolean	checkDeltaBetaSet;
	private boolean checkImageRestoreSet;
	private String pathnamePhaseOut;
	private String saveNamePhase;
	private String scalingPhaseFrom;
	private int formatPhase;
	private int scalePhase;
	private String scalingPhaseTo;
	
	private boolean checkCalImFromTo;
	private String calculateImagesFrom;
	private String calculateImagesTo;	
	private boolean checkShowImages;
	private String CPUs;
	private String countStart;
	
	private String ColorDarkPath;
	private String ColorFlat1Path;
	private String ColorFlat2Path;
	private String ColorFlatPathOut;
	private String ColorSaveNameFlat;
	private String ColorFormatFlat;
	private String ColorScalingFlat;
	private String ColorScalingFlatFrom;
	private String ColorScalingFlatTo;
	private String ColorPhasePathOut;
	private String ColorBeta;
	private String ColorDelta;
	private String ColorDeltaBeta;
	private String ColorGauss;
	private String ColorOffset;
	private String ColorDistance;
	private String ColorEnergy;
	private String ColorPixSize;
	private String ColorSaveNamePhase;
	private String ColorFormatPhase;
	private String ColorScalingPhase;
	private String ColorScalingPhaseFrom;
	private String ColorScalingPhaseTo;
	private String ColorCalculateImagesFrom;
	private String ColorCalculateImagesTo;  //  @jve:decl-index=0:
	
	private String[] listProj;
	private String[] listDark;
	private String[] listFlat1;
	private String[] listFlat2;
	
	private boolean checkDarkDirectory;
	private boolean checkFlat1Directory;
	private boolean checkFlat2Directory;
	private boolean checkEverythingOK;
	
	private ImageProcessor ip1;
	private ImageProcessor dark;  //  @jve:decl-index=0:
	private ImageProcessor flat1;  //  @jve:decl-index=0:
	private ImageProcessor flat2;  //  @jve:decl-index=0:
	private int width;
	private int height;
	
	private int imCounter;
	private HandlerThread ht;;
	
	private String stringSavePhaseCheckBox;  //  @jve:decl-index=0:
	private String stringSaveFlatCheckBox;  //  @jve:decl-index=0:
	private String stringDeltaBetaCheckBox;       //  @jve:decl-index=0:
	private String stringImageRestoreCheckBox;
	private String stringDarkCheckBox;
	private String stringFlat1CheckBox;
	private String stringFlat2CheckBox;
	private String stringShowImagesCheckBox;
	private String stringCalImFromToCheckBox;
	private String stringInterpolateCheckBox;  //  @jve:decl-index=0:
	private String stringEdgeExtensionCheckBox;  //  @jve:decl-index=0:
	
	private int calFrom;
	private int calTo;
	private int prozZahl;
	private int countStartAt;
	private JLabel jLabelLogo = null;
	private JMenuItem loadParMenuItem = null;
	private JMenuItem saveParMenuItem = null;
	
	private String pathnameLoadPar;
	private String pathnameSavePar;
	private JLabel countStartLabel = null;
	private JTextField countStartTextField = null;
	private JCheckBox interpolateFlatCheckBox = null;
	private boolean checkInterpolateFlat;
	private boolean edgeExtension;
	
	private static boolean checkPlugin;
	private JCheckBox checkBoxEdgeExtension = null;
	/**
	 * This method initializes mainMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getMainMenuBar() {
		if (mainMenuBar == null) {
			mainMenuBar = new JMenuBar();
			mainMenuBar.add(getFileMenu());
			mainMenuBar.add(getHelpMenu());
		}
		return mainMenuBar;
	}

	/**
	 * This method initializes fileMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getFileMenu() {
		if (fileMenu == null) {
			fileMenu = new JMenu();
			fileMenu.setText("File");
			fileMenu.add(getLoadParMenuItem());
			fileMenu.add(getSaveParMenuItem());
			fileMenu.add(getExitMenuItem());
		}
		return fileMenu;
	}

	/**
	 * This method initializes exitMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getExitMenuItem() {
		if (exitMenuItem == null) {
			exitMenuItem = new JMenuItem();
			exitMenuItem.setText("Exit");
			exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					thisFrame.dispose();
					if(checkPlugin==false){
						PIDHandler pd = new PIDHandler();
						try{
							String pid = pd.getPID();
							if(ManagementFactory.getOperatingSystemMXBean().getName().equals("Linux")){
								String cmd = "kill -9 " + pid;
								Runtime.getRuntime().exec(cmd);
							}
						}
						catch(IOException ioe){}
					}
				}
			});
		}
		return exitMenuItem;
	}

	/**
	 * This method initializes helpMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getHelpMenu() {
		if (helpMenu == null) {
			helpMenu = new JMenu();
			helpMenu.setText("Help");
			helpMenu.add(getHelpMenuItem());
			helpMenu.add(getAboutMenuItem());
		}
		return helpMenu;
	}

	/**
	 * This method initializes aboutMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getAboutMenuItem() {
		if (aboutMenuItem == null) {
			aboutMenuItem = new JMenuItem();
			aboutMenuItem.setText("About");
			aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					About ab = new About();
					ab.setVisible(true);
				}
			});
		}
		return aboutMenuItem;
	}

	/**
	 * This method initializes helpMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getHelpMenuItem() {
		if (helpMenuItem == null) {
			helpMenuItem = new JMenuItem();
			helpMenuItem.setText("Help");
			helpMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					File help = new File("ankaphase-userguide.html");
					String pathHelp = help.getAbsolutePath();
					int subLength = pathHelp.length()-24;
					pathHelp = pathHelp.substring(0, subLength);
					File helpFile = new File(pathHelp);
					String[] helpList = helpFile.list();
					for(int i = 0; i < helpList.length; i++){
						if(helpList[i].equals("ankaphase-userguide.html")){
							try{
								BrowserLauncher bl = new BrowserLauncher();
								bl.openURL("file://" + pathHelp + helpList[i]);
							}
							catch(IOException ioe){
							}
							break;
						}
						StringBuffer sb = new StringBuffer(helpList[i]);
						int there = sb.indexOf(".");
						if(there < 0){
							File help2 = new File(pathHelp + helpList[i]);
							String[] helpList2 = help2.list();
							for(int i2 = 0; i2 < helpList2.length; i2++){
								if(helpList2[i2].equals("ankaphase-userguide.html")){
									try{
										BrowserLauncher bl = new BrowserLauncher();
										bl.openURL("file://" + help2.getAbsolutePath()+ "/" + helpList2[i2]);
									}
									catch(IOException ioe){
									}
									break;
								}
								else{
									if((i2+1) == helpList2.length && (i+1) == helpList.length){
										GenericDialog gd = new GenericDialog("Failure");
										gd.addMessage("There is no ankaphase-userguide.html");
										gd.showDialog();
									}
								}
							}
						}
						
						
					}
				}
			});
		}
		return helpMenuItem;
	}

	/**
	 * This method initializes projDirText	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getProjDirText() {
		if (projDirText == null) {
			projDirText = new JTextField();
			projDirText.setBounds(new Rectangle(300, 60, 500, 20));
			projDirText.setText(pathnameProj);
			projDirText.selectAll();
		}
		return projDirText;
	}

	/**
	 * This method initializes browseProjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBrowseProjButton() {
		if (browseProjButton == null) {
			browseProjButton = new JButton();
			browseProjButton.setBounds(new Rectangle(820, 60, 80, 20));
			browseProjButton.setText("Browse");
			browseProjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFileChooser jfc = new JFileChooser(actDirectory);
					jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					jfc.setDialogTitle("Open projections ...");
					jfc.showOpenDialog(thisFrame);
					try{
						pathnameProj = jfc.getSelectedFile().getAbsolutePath();
					}
					catch(NullPointerException npe){
					}
					projDirText.setText(pathnameProj);
					actDirectory = pathnameProj;
				}
			});
		}
		return browseProjButton;
	}

	/**
	 * This method initializes darkCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getDarkCheckBox() {
		if (darkCheckBox == null) {
			darkCheckBox = new JCheckBox();
			darkCheckBox.setBounds(new Rectangle(40, 95, 233, 20));
			darkCheckBox.setText("dark-field images directory:");
			darkCheckBox.setSelected(checkDark);
			darkCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(darkCheckBox.isSelected() == false){
						darkDirText.setBackground(Color.lightGray);
						checkDark = false;
					}
					else{
						darkDirText.setBackground(Color.white);
						checkDark = true;
					}
				}
			});
		}
		return darkCheckBox;
	}

	/**
	 * This method initializes darkDirText	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getDarkDirText() {
		if (darkDirText == null) {
			darkDirText = new JTextField();
			darkDirText.setBounds(new Rectangle(300, 95, 500, 20));
			darkDirText.setText(pathnameDark);
			//System.out.println("test=" + ColorDarkPath);
			if(ColorDarkPath.equals("java.awt.Color[r=192,g=192,b=192]"))
				darkDirText.setBackground(Color.lightGray);
			else
				darkDirText.setBackground(Color.white);
			darkDirText.selectAll();
		}
		return darkDirText;
	}

	/**
	 * This method initializes browseDarkButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBrowseDarkButton() {
		if (browseDarkButton == null) {
			browseDarkButton = new JButton();
			browseDarkButton.setBounds(new Rectangle(820, 95, 80, 20));
			browseDarkButton.setText("Browse");
			browseDarkButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFileChooser jfc = new JFileChooser(actDirectory);
					jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					jfc.setDialogTitle("Open dark-field images ...");
					jfc.showOpenDialog(thisFrame);
					try{
						pathnameDark = jfc.getSelectedFile().getAbsolutePath();
					}
					catch(NullPointerException npe){
					}
					darkDirText.setText(pathnameDark);
					actDirectory = pathnameDark;
				}
			});
		}
		return browseDarkButton;
	}

	/**
	 * This method initializes flat1CheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getFlat1CheckBox() {
		if (flat1CheckBox == null) {
			flat1CheckBox = new JCheckBox();
			flat1CheckBox.setBounds(new Rectangle(40, 130, 234, 20));
			flat1CheckBox.setText("flat-field 1 images directory:");
			flat1CheckBox.setSelected(checkFlat1);
			flat1CheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(flat1CheckBox.isSelected() == true){
						flat1DirText.setBackground(Color.white);
						checkFlat1 = true;
					}
					else{
						if(interpolateFlatCheckBox.isSelected() == true){
							interpolateFlatCheckBox.setSelected(false);
							checkInterpolateFlat=false;
						}
//						if(checkFlat2 == true){
//							flat1DirText.setBackground(Color.white);
//							flat1CheckBox.setSelected(true);
//							checkFlat1 = true;
//						}
//						else{
							flat1DirText.setBackground(Color.lightGray);
							checkFlat1 = false;
//						}
					}
				}
			});
		}
		return flat1CheckBox;
	}

	/**
	 * This method initializes flat1DirText	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getFlat1DirText() {
		if (flat1DirText == null) {
			flat1DirText = new JTextField();
			flat1DirText.setBounds(new Rectangle(300, 130, 500, 20));
			flat1DirText.setText(pathnameFlat1);
			if(ColorFlat1Path.equals("java.awt.Color[r=192,g=192,b=192]"))
				flat1DirText.setBackground(Color.lightGray);
			else
				flat1DirText.setBackground(Color.white);
			flat1DirText.selectAll();
		}
		return flat1DirText;
	}

	/**
	 * This method initializes flat1BrowseButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getFlat1BrowseButton() {
		if (flat1BrowseButton == null) {
			flat1BrowseButton = new JButton();
			flat1BrowseButton.setBounds(new Rectangle(820, 130, 80, 20));
			flat1BrowseButton.setText("Browse");
			flat1BrowseButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFileChooser jfc = new JFileChooser(actDirectory);
					jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					jfc.setDialogTitle("flat-field images 1 ...");
					jfc.showOpenDialog(thisFrame);
					try{
						pathnameFlat1 = jfc.getSelectedFile().getAbsolutePath();
					}
					catch(NullPointerException npe){
					}
					flat1DirText.setText(pathnameFlat1);
					actDirectory = pathnameFlat1;
				}
			});
		}
		return flat1BrowseButton;
	}

	/**
	 * This method initializes flat2DirCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getFlat2DirCheckBox() {
		if (flat2DirCheckBox == null) {
			flat2DirCheckBox = new JCheckBox();
			flat2DirCheckBox.setBounds(new Rectangle(40, 165, 227, 20));
			flat2DirCheckBox.setText("flat-field 2 images directory:");
			flat2DirCheckBox.setSelected(checkFlat2);
			flat2DirCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(flat2DirCheckBox.isSelected() == true){
						flat2DirText.setBackground(Color.white);
//						flat1DirText.setBackground(Color.white);
//						flat1CheckBox.setSelected(true);
						checkFlat2 = true;
					}
					else{
						if(interpolateFlatCheckBox.isSelected() == true){
							interpolateFlatCheckBox.setSelected(false);
							checkInterpolateFlat=false;
						}
						flat2DirText.setBackground(Color.lightGray);
						checkFlat2 = false;
//						interpolateFlatCheckBox.setSelected(false);
//						checkInterpolateFlat = false;
					}
				}
			});
		}
		return flat2DirCheckBox;
	}

	/**
	 * This method initializes flat2DirText	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getFlat2DirText() {
		if (flat2DirText == null) {
			flat2DirText = new JTextField();
			flat2DirText.setBounds(new Rectangle(300, 165, 500, 20));
			flat2DirText.setText(pathnameFlat2);
			if(ColorFlat2Path.equals("java.awt.Color[r=192,g=192,b=192]"))
				flat2DirText.setBackground(Color.lightGray);
			else
				flat2DirText.setBackground(Color.white);
			flat2DirText.selectAll();
		}
		return flat2DirText;
	}

	/**
	 * This method initializes flat2BrowseButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getFlat2BrowseButton() {
		if (flat2BrowseButton == null) {
			flat2BrowseButton = new JButton();
			flat2BrowseButton.setBounds(new Rectangle(820, 165, 80, 20));
			flat2BrowseButton.setText("Browse");
			flat2BrowseButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFileChooser jfc = new JFileChooser(actDirectory);
					jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					jfc.setDialogTitle("flat-field images 2 ...");
					jfc.showOpenDialog(thisFrame);
					try{
						pathnameFlat2 = jfc.getSelectedFile().getAbsolutePath();
					}
					catch(NullPointerException npe){
					}
					flat2DirText.setText(pathnameFlat2);
					actDirectory = pathnameFlat2;
				}
			});
		}
		return flat2BrowseButton;
	}

	/**
	 * This method initializes linePanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getLinePanel1() {
		if (linePanel1 == null) {
			linePanel1 = new JPanel();
			linePanel1.setLayout(new GridBagLayout());
			linePanel1.setBounds(new Rectangle(20, 200, 974, 2));
			linePanel1.setBackground(Color.black);
		}
		return linePanel1;
	}

	/**
	 * This method initializes saveFlatCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getSaveFlatCheckBox() {
		if (saveFlatCheckBox == null) {
			saveFlatCheckBox = new JCheckBox();
			saveFlatCheckBox.setBounds(new Rectangle(40, 250, 231, 21));
			saveFlatCheckBox.setFont(new Font("Dialog", Font.BOLD, 14));
			saveFlatCheckBox.setText("save flat corrected images");
			saveFlatCheckBox.setSelected(checkSaveFlatCorrectedImages);
			saveFlatCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(saveFlatCheckBox.isSelected() == false){
						saveFlatPathText.setBackground(Color.lightGray);
						saveNameFlatText.setBackground(Color.lightGray);
						formatFlatCombo.setBackground(Color.lightGray);
						scalingFlatCombo.setBackground(Color.lightGray);
						scalingFlatFromText.setBackground(Color.lightGray);
						scalingFlatToText.setBackground(Color.lightGray);
					}
					else{
						saveFlatPathText.setBackground(Color.white);
						saveNameFlatText.setBackground(Color.white);
						formatFlatCombo.setBackground(Color.white);
						scalingFlatCombo.setBackground(Color.white);
						if(scalingFlatCombo.getSelectedIndex() == 2){
							scalingFlatFromText.setBackground(Color.white);
							scalingFlatToText.setBackground(Color.white);
						}
					}
				}
			});
		}
		return saveFlatCheckBox;
	}

	/**
	 * This method initializes saveFlatPathText	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getSaveFlatPathText() {
		if (saveFlatPathText == null) {
			saveFlatPathText = new JTextField();
			saveFlatPathText.setBounds(new Rectangle(170, 285, 250, 20));
			saveFlatPathText.setText(pathnameFlatOut);
			if(ColorFlatPathOut.equals("java.awt.Color[r=192,g=192,b=192]"))
				saveFlatPathText.setBackground(Color.lightGray);
			else
				saveFlatPathText.setBackground(Color.white);
			saveFlatPathText.selectAll();
		}
		return saveFlatPathText;
	}

	/**
	 * This method initializes saveFlatBrowseButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSaveFlatBrowseButton() {
		if (saveFlatBrowseButton == null) {
			saveFlatBrowseButton = new JButton();
			saveFlatBrowseButton.setBounds(new Rectangle(430, 285, 80, 20));
			saveFlatBrowseButton.setText("Browse");
			saveFlatBrowseButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFileChooser jfc = new JFileChooser(actDirectory);
					jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					jfc.setDialogTitle("save flat images ...");
					jfc.showOpenDialog(thisFrame);
					try{
						pathnameFlatOut = jfc.getSelectedFile().getAbsolutePath();
					}
					catch(NullPointerException npe){
					}
					saveFlatPathText.setText(pathnameFlatOut);
					actDirectory = pathnameFlatOut;
				}
			});
		}
		return saveFlatBrowseButton;
	}

	/**
	 * This method initializes saveNameFlatText	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getSaveNameFlatText() {
		if (saveNameFlatText == null) {
			saveNameFlatText = new JTextField();
			saveNameFlatText.setBounds(new Rectangle(610, 285, 100, 20));
			saveNameFlatText.setText(saveNameFlat);
			if(ColorSaveNameFlat.equals("java.awt.Color[r=192,g=192,b=192]"))
				saveNameFlatText.setBackground(Color.lightGray);
			else
				saveNameFlatText.setBackground(Color.white);
			saveNameFlatText.selectAll();
		}
		return saveNameFlatText;
	}

	/**
	 * This method initializes formatFlatCombo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getFormatFlatCombo() {
		if (formatFlatCombo == null) {
			formatFlatCombo = new JComboBox();
			formatFlatCombo.setBounds(new Rectangle(800, 285, 160, 20));
			formatFlatCombo.addItem(formatsArr[0]);
			formatFlatCombo.addItem(formatsArr[1]);
			formatFlatCombo.addItem(formatsArr[2]);
			formatFlatCombo.addItem(formatsArr[3]);
			formatFlatCombo.addItem(formatsArr[4]);
			formatFlatCombo.addItem(formatsArr[5]);
			formatFlatCombo.addItem(formatsArr[6]);
			formatFlatCombo.setSelectedIndex(formatFlat);
			if(ColorFormatFlat.equals("java.awt.Color[r=192,g=192,b=192]"))
				formatFlatCombo.setBackground(Color.lightGray);
			else
				formatFlatCombo.setBackground(Color.white);
			formatFlatCombo.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(formatFlatCombo.getSelectedIndex() == 2 || formatFlatCombo.getSelectedIndex() == 6){
						scalingFlatCombo.setBackground(Color.lightGray);
					}
					else{
						scalingFlatCombo.setBackground(Color.white);
					}
				}
			});
		}
		return formatFlatCombo;
	}

	/**
	 * This method initializes scalingFlatCombo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getScalingFlatCombo() {
		if (scalingFlatCombo == null) {
			scalingFlatCombo = new JComboBox();
			scalingFlatCombo.setBounds(new Rectangle(180, 320, 300, 20));
			scalingFlatCombo.addItem(scaleOptArr[0]);
			scalingFlatCombo.addItem(scaleOptArr[1]);
			scalingFlatCombo.addItem(scaleOptArr[2]);
			scalingFlatCombo.setSelectedIndex(scaleFlat);
			if(ColorScalingFlat.equals("java.awt.Color[r=192,g=192,b=192]"))
				scalingFlatCombo.setBackground(Color.lightGray);
			else
				scalingFlatCombo.setBackground(Color.white);
			scalingFlatCombo.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(scalingFlatCombo.getSelectedIndex() == 2){
						scalingFlatFromText.setBackground(Color.white);
						scalingFlatToText.setBackground(Color.white);
					}
					else{
						scalingFlatFromText.setBackground(Color.lightGray);
						scalingFlatToText.setBackground(Color.lightGray);
					}
				}
			});
		}
		return scalingFlatCombo;
	}

	/**
	 * This method initializes scalingFlatFromText	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getScalingFlatFromText() {
		if (scalingFlatFromText == null) {
			scalingFlatFromText = new JTextField();
			scalingFlatFromText.setBounds(new Rectangle(550, 320, 40, 20));
			scalingFlatFromText.setText(scalingFlatFrom);
			if(ColorScalingFlatFrom.equals("java.awt.Color[r=192,g=192,b=192]"))
				scalingFlatFromText.setBackground(Color.lightGray);
			else
				scalingFlatFromText.setBackground(Color.white);
			scalingFlatFromText.selectAll();
		}
		return scalingFlatFromText;
	}

	/**
	 * This method initializes scalingFlatToText	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getScalingFlatToText() {
		if (scalingFlatToText == null) {
			scalingFlatToText = new JTextField();
			scalingFlatToText.setBounds(new Rectangle(650, 320, 40, 20));
			scalingFlatToText.setText(scalingFlatTo);
			if(ColorScalingFlatTo.equals("java.awt.Color[r=192,g=192,b=192]"))
				scalingFlatToText.setBackground(Color.lightGray);
			else
				scalingFlatToText.setBackground(Color.white);
			scalingFlatToText.selectAll();
		}
		return scalingFlatToText;
	}

	/**
	 * This method initializes linePanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getLinePanel2() {
		if (linePanel2 == null) {
			linePanel2 = new JPanel();
			linePanel2.setLayout(new GridBagLayout());
			linePanel2.setBounds(new Rectangle(20, 355, 974, 2));
			linePanel2.setBackground(Color.black);
		}
		return linePanel2;
	}

	/**
	 * This method initializes savePhaseCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getSavePhaseCheckBox() {
		if (savePhaseCheckBox == null) {
			savePhaseCheckBox = new JCheckBox();
			savePhaseCheckBox.setBounds(new Rectangle(40, 405, 251, 21));
			savePhaseCheckBox.setFont(new Font("Dialog", Font.BOLD, 14));
			savePhaseCheckBox.setText("save phase-retrieval images");
			savePhaseCheckBox.setSelected(checkSavePhaseCorrectedImages);
			savePhaseCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(savePhaseCheckBox.isSelected() == false){
						savePhasePathText.setBackground(Color.lightGray);
						saveNamePhaseText.setBackground(Color.lightGray);
						formatPhaseCombo.setBackground(Color.lightGray);
						scalingPhaseCombo.setBackground(Color.lightGray);
						scalingPhaseFromText.setBackground(Color.lightGray);
						scalingPhaseToText.setBackground(Color.lightGray);
						deltaText.setBackground(Color.lightGray);
						betaText.setBackground(Color.lightGray);
						DeltaBetaValue.setBackground(Color.lightGray);
						ImageRestoreGaussValue.setBackground(Color.lightGray);
						ImageRestoreOffsetValue.setBackground(Color.lightGray);
						distanceText.setBackground(Color.lightGray);
						energyText.setBackground(Color.lightGray);
						pixSizeText.setBackground(Color.lightGray);
					}
					else{
						savePhasePathText.setBackground(Color.white);
						saveNamePhaseText.setBackground(Color.white);
						formatPhaseCombo.setBackground(Color.white);
						scalingPhaseCombo.setBackground(Color.white);
						if(DeltaBetaCheckBox.isSelected() == false){
						deltaText.setBackground(Color.white);
						betaText.setBackground(Color.white);
						DeltaBetaValue.setBackground(Color.lightGray);}
						else {
							deltaText.setBackground(Color.lightGray);
							betaText.setBackground(Color.lightGray);
							DeltaBetaValue.setBackground(Color.white);	
						}
						if(ImageRestoreCheckBox.isSelected() == true){
							ImageRestoreGaussValue.setBackground(Color.white);
							ImageRestoreOffsetValue.setBackground(Color.white);
						}
						else{
							ImageRestoreGaussValue.setBackground(Color.lightGray);
							ImageRestoreOffsetValue.setBackground(Color.lightGray);
						}
						distanceText.setBackground(Color.white);
						energyText.setBackground(Color.white);
						pixSizeText.setBackground(Color.white);
						if(scalingPhaseCombo.getSelectedIndex() == 2){
							scalingPhaseFromText.setBackground(Color.white);
							scalingPhaseToText.setBackground(Color.white);
						}
					}
				}
			});
		}
		return savePhaseCheckBox;
	}

	/**
	 * This method initializes DeltaBetaCheckBox
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getDeltaBetaCheckBox() {
		if (DeltaBetaCheckBox == null) {
			DeltaBetaCheckBox = new JCheckBox();
			DeltaBetaCheckBox.setBounds(new Rectangle(620, 435, 160, 20));
			DeltaBetaCheckBox.setText("use delta/beta ratio:");
			DeltaBetaCheckBox.setSelected(checkDeltaBetaSet);
			DeltaBetaCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {	
					if(savePhaseCheckBox.isSelected() == true) {
						if(DeltaBetaCheckBox.isSelected() == false){
							deltaText.setBackground(Color.white);
							betaText.setBackground(Color.white);
							DeltaBetaValue.setBackground(Color.lightGray);
						}
						else{
							deltaText.setBackground(Color.lightGray);
							betaText.setBackground(Color.lightGray);
							DeltaBetaValue.setBackground(Color.white);
						}
					}
				}
			});
		}
		return DeltaBetaCheckBox;
	}

	/**
	 * This method initializes ImageRestoreCheckBox
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getImageRestoreCheckBox() {
		if (ImageRestoreCheckBox == null) {
			ImageRestoreCheckBox = new JCheckBox();
			ImageRestoreCheckBox.setBounds(new Rectangle(320, 405, 200, 20));
			ImageRestoreCheckBox.setFont(new Font("Dialog", Font.BOLD, 14));
			ImageRestoreCheckBox.setText("apply image restoration");
			ImageRestoreCheckBox.setSelected(checkImageRestoreSet);
			ImageRestoreCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {	
					if(savePhaseCheckBox.isSelected() == true) {
						if(ImageRestoreCheckBox.isSelected() == true){
							ImageRestoreGaussValue.setBackground(Color.white);
							ImageRestoreOffsetValue.setBackground(Color.white);
						}
						else{
							ImageRestoreGaussValue.setBackground(Color.lightGray);
							ImageRestoreOffsetValue.setBackground(Color.lightGray);
						}
					}
				}
			});
		}
		return ImageRestoreCheckBox;
	}

	/**
	 * This method initializes betaText	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getBetaText() {
		if (betaText == null) {
			betaText = new JTextField();
			betaText.setBounds(new Rectangle(275, 435, 50, 20));
			betaText.setText(betaString);
			if(ColorBeta.equals("java.awt.Color[r=192,g=192,b=192]"))
				betaText.setBackground(Color.lightGray);
			else
				betaText.setBackground(Color.white);
			betaText.selectAll();
		}
		return betaText;
	}

	/**
	 * This method initializes deltaText	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getDeltaText() {
		if (deltaText == null) {
			deltaText = new JTextField();
			deltaText.setBounds(new Rectangle(445, 435, 50, 20));
			deltaText.setText(deltaString);
			if(ColorDelta.equals("java.awt.Color[r=192,g=192,b=192]"))
				deltaText.setBackground(Color.lightGray);
			else
				deltaText.setBackground(Color.white);
			deltaText.selectAll();
		}
		return deltaText;
	}

	/**
	 * This method initializes DeltaBetaValue	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getDeltaBetaValue() {
		if (DeltaBetaValue == null) {
			DeltaBetaValue = new JTextField();
			DeltaBetaValue.setBounds(new Rectangle(790, 435, 50, 20));
			DeltaBetaValue.setText(DeltaBetaString);
			if(ColorDeltaBeta.equals("java.awt.Color[r=192,g=192,b=192]"))
				DeltaBetaValue.setBackground(Color.lightGray);
			else
				DeltaBetaValue.setBackground(Color.white);
			DeltaBetaValue.selectAll();
		}
		return DeltaBetaValue;
	}

	/**
	 * This method initializes ImageRestoreGaussValue	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getImageRestoreGaussValue() {
		if (ImageRestoreGaussValue == null) {
			ImageRestoreGaussValue = new JTextField();
			ImageRestoreGaussValue.setBounds(new Rectangle(630, 405, 30, 20));
			ImageRestoreGaussValue.setText(ImageRestoreGaussString);
			if(ColorGauss.equals("java.awt.Color[r=192,g=192,b=192]"))
				ImageRestoreGaussValue.setBackground(Color.lightGray);
			else
				ImageRestoreGaussValue.setBackground(Color.white);
			ImageRestoreGaussValue.selectAll();
		}
		return ImageRestoreGaussValue;
	}
	
	/**
	 * This method initializes ImageRestoreOffsetValue	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getImageRestoreOffsetValue() {
		if (ImageRestoreOffsetValue == null) {
			ImageRestoreOffsetValue = new JTextField();
			ImageRestoreOffsetValue.setBounds(new Rectangle(808, 405, 40, 20));
			ImageRestoreOffsetValue.setText(ImageRestoreOffsetString);
			if(ColorOffset.equals("java.awt.Color[r=192,g=192,b=192]"))
				ImageRestoreOffsetValue.setBackground(Color.lightGray);
			else
				ImageRestoreOffsetValue.setBackground(Color.white);
			ImageRestoreOffsetValue.selectAll();
		}
		return ImageRestoreOffsetValue;
	}
	
	/**
	 * This method initializes distanceText	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getDistanceText() {
		if (distanceText == null) {
			distanceText = new JTextField();
			distanceText.setBounds(new Rectangle(305, 465, 50, 20));
			distanceText.setText(distanceString);
			if(ColorDistance.equals("java.awt.Color[r=192,g=192,b=192]"))
				distanceText.setBackground(Color.lightGray);
			else
				distanceText.setBackground(Color.white);
			distanceText.selectAll();
		}
		return distanceText;
	}

	/**
	 * This method initializes energyText	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getEnergyText() {
		if (energyText == null) {
			energyText = new JTextField();
			energyText.setBounds(new Rectangle(480, 465, 50, 20));
			energyText.setText(energyString);
			if(ColorEnergy.equals("java.awt.Color[r=192,g=192,b=192]"))
				energyText.setBackground(Color.lightGray);
			else
				energyText.setBackground(Color.white);
			energyText.selectAll();
		}
		return energyText;
	}

	/**
	 * This method initializes pixSizeText	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getPixSizeText() {
		if (pixSizeText == null) {
			pixSizeText = new JTextField();
			pixSizeText.setBounds(new Rectangle(675, 465, 50, 20));
			pixSizeText.setText(pixSizeString);
			if(ColorPixSize.equals("java.awt.Color[r=192,g=192,b=192]"))
				pixSizeText.setBackground(Color.lightGray);
			else
				pixSizeText.setBackground(Color.white);
			pixSizeText.selectAll();
		}
		return pixSizeText;
	}

	/**
	 * This method initializes savePhasePathText	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getSavePhasePathText() {
		if (savePhasePathText == null) {
			savePhasePathText = new JTextField();
			savePhasePathText.setBounds(new Rectangle(170, 500, 250, 20));
			savePhasePathText.setText(pathnamePhaseOut);
			if(ColorPhasePathOut.equals("java.awt.Color[r=192,g=192,b=192]"))
				savePhasePathText.setBackground(Color.lightGray);
			else
				savePhasePathText.setBackground(Color.white);
			savePhasePathText.selectAll();
		}
		return savePhasePathText;
	}

	/**
	 * This method initializes savePhaseBrowseButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSavePhaseBrowseButton() {
		if (savePhaseBrowseButton == null) {
			savePhaseBrowseButton = new JButton();
			savePhaseBrowseButton.setBounds(new Rectangle(430, 500, 80, 20));
			savePhaseBrowseButton.setText("Browse");
			savePhaseBrowseButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFileChooser jfc = new JFileChooser(actDirectory);
					jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					jfc.setDialogTitle("save phase-retrieval images ...");
					jfc.showOpenDialog(thisFrame);
					try{
						pathnamePhaseOut = jfc.getSelectedFile().getAbsolutePath();
					}
					catch(NullPointerException npe){
					}
					savePhasePathText.setText(pathnamePhaseOut);
					actDirectory = pathnamePhaseOut;
				}
			});
		}
		return savePhaseBrowseButton;
	}

	/**
	 * This method initializes saveNamePhaseText	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getSaveNamePhaseText() {
		if (saveNamePhaseText == null) {
			saveNamePhaseText = new JTextField();
			saveNamePhaseText.setBounds(new Rectangle(610, 500, 100, 20));
			saveNamePhaseText.setText(saveNamePhase);
			if(ColorSaveNamePhase.equals("java.awt.Color[r=192,g=192,b=192]"))
				saveNamePhaseText.setBackground(Color.lightGray);
			else
				saveNamePhaseText.setBackground(Color.white);
			saveNamePhaseText.selectAll();
		}
		return saveNamePhaseText;
	}

	/**
	 * This method initializes formatPhaseCombo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getFormatPhaseCombo() {
		if (formatPhaseCombo == null) {
			formatPhaseCombo = new JComboBox();
			formatPhaseCombo.setBounds(new Rectangle(800, 500, 160, 20));
			formatPhaseCombo.addItem(formatsArr[0]);
			formatPhaseCombo.addItem(formatsArr[1]);
			formatPhaseCombo.addItem(formatsArr[2]);
			formatPhaseCombo.addItem(formatsArr[3]);
			formatPhaseCombo.addItem(formatsArr[4]);
			formatPhaseCombo.addItem(formatsArr[5]);
			formatPhaseCombo.addItem(formatsArr[6]);
			formatPhaseCombo.setSelectedIndex(formatPhase);
			if(ColorFormatPhase.equals("java.awt.Color[r=192,g=192,b=192]"))
				formatPhaseCombo.setBackground(Color.lightGray);
			else
				formatPhaseCombo.setBackground(Color.white);
			formatPhaseCombo.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(formatPhaseCombo.getSelectedIndex() == 2 || formatPhaseCombo.getSelectedIndex() == 6){
						scalingPhaseCombo.setBackground(Color.lightGray);
					}
					else{
						scalingPhaseCombo.setBackground(Color.white);
					}
				}
			});
		}
		return formatPhaseCombo;
	}

	/**
	 * This method initializes linePanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getLinePanel3() {
		if (linePanel3 == null) {
			linePanel3 = new JPanel();
			linePanel3.setLayout(new GridBagLayout());
			linePanel3.setBounds(new Rectangle(20, 570, 974, 2));
			linePanel3.setBackground(Color.black);
		}
		return linePanel3;
	}

	/**
	 * This method initializes linePanel4	
	 * 	
	 * @return javax.swing.JPanel	
	 */
 	private JPanel getLinePanel4() {
		if (linePanel4 == null) {
			linePanel4 = new JPanel();
			linePanel4.setLayout(new GridBagLayout());
			linePanel4.setBounds(new Rectangle(20, 713, 974, 1));
			linePanel4.setBackground(Color.black);
		}
		return linePanel4;
 	}
	/**
	 * This method initializes calculateImagesFromCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getCalculateImagesFromCheckBox() {
		if (calculateImagesFromCheckBox == null) {
			calculateImagesFromCheckBox = new JCheckBox();
			calculateImagesFromCheckBox.setBounds(new Rectangle(40, 590, 176, 21));
			calculateImagesFromCheckBox.setText("calculate images from:");
			calculateImagesFromCheckBox.setSelected(checkCalImFromTo);
			calculateImagesFromCheckBox
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							if(calculateImagesFromCheckBox.isSelected() == false){
								calculateImagesFromText.setBackground(Color.lightGray);
								calculateImagesToText.setBackground(Color.lightGray);
							}
							else{
								calculateImagesFromText.setBackground(Color.white);
								calculateImagesToText.setBackground(Color.white);
							}
						}
					});
		}
		return calculateImagesFromCheckBox;
	}

	/**
	 * This method initializes calculateImagesFromText	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getCalculateImagesFromText() {
		if (calculateImagesFromText == null) {
			calculateImagesFromText = new JTextField();
			calculateImagesFromText.setBounds(new Rectangle(220, 590, 40, 20));
			calculateImagesFromText.setText(calculateImagesFrom);
			if(ColorCalculateImagesFrom.equals("java.awt.Color[r=192,g=192,b=192]"))
				calculateImagesFromText.setBackground(Color.lightGray);
			else
				calculateImagesFromText.setBackground(Color.white);
			calculateImagesFromText.selectAll();
		}
		return calculateImagesFromText;
	}

	/**
	 * This method initializes calculateImagesToText	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getCalculateImagesToText() {
		if (calculateImagesToText == null) {
			calculateImagesToText = new JTextField();
			calculateImagesToText.setBounds(new Rectangle(300, 590, 40, 20));
			calculateImagesToText.setText(calculateImagesTo);
			if(ColorCalculateImagesTo.equals("java.awt.Color[r=192,g=192,b=192]"))
				calculateImagesToText.setBackground(Color.lightGray);
			else
				calculateImagesToText.setBackground(Color.white);
			calculateImagesToText.selectAll();
		}
		return calculateImagesToText;
	}

	/**
	 * This method initializes showImagesCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getShowImagesCheckBox() {
		if (showImagesCheckBox == null) {
			showImagesCheckBox = new JCheckBox();
			showImagesCheckBox.setBounds(new Rectangle(410, 590, 127, 20));
			showImagesCheckBox.setText("show images");
			showImagesCheckBox.setSelected(checkShowImages);
		}
		return showImagesCheckBox;
	}

	/**
	 * This method initializes CPUsText	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getCPUsText() {
		if (CPUsText == null) {
			CPUsText = new JTextField();
			CPUsText.setBounds(new Rectangle(615, 590, 40, 20));
			CPUsText.setText(CPUs);
			CPUsText.selectAll();
		}
		return CPUsText;
	}

	/**
	 * This method initializes stopButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getStopButton() {
		if (stopButton == null) {
			stopButton = new JButton();
			stopButton.setBounds(new Rectangle(710, 590, 80, 20));
			stopButton.setText("stop");
			stopButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ht.stop();
					if(saveFlatCheckBox.isSelected() == true && savePhaseCheckBox.isSelected() == true)
						imCounter = ProgressBar.getValue()*2;
					else
						imCounter = ProgressBar.getValue();
					GenericDialog gd = new GenericDialog("Failure");
					gd.addMessage( "process was canceled!\n" + imCounter + "  images calculated!");
					gd.showDialog();
					ProgressBar.setValue(0);
					statusLabel.setText("status...");
					imCounter = 0;
					checkEverythingOK = true;
				}
			});
		}
		return stopButton;
	}

	/**
	 * This method initializes runButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRunButton() {
		if (runButton == null) {
			runButton = new JButton();
			runButton.setBounds(new Rectangle(850, 590, 80, 20));
			runButton.setText("run");
			runButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					boolean checkRun;
					checkRun = checkEverythingIsOK();
					if (checkRun == true){
						writeOutPar();
				
						ht = new HandlerThread(pathnameProj, pathnameFlatOut, pathnamePhaseOut, listProj,
											beta, delta, deltabeta, gauss, offset, distance, energy, pixSize, imCounter,
											ip1,dark,flat1,flat2,
											scalingFlatCombo, scalingFlatFromText, scalingFlatToText,
											scalingPhaseCombo, scalingPhaseFromText, scalingPhaseToText,
											darkCheckBox, flat1CheckBox, flat2DirCheckBox,
											saveFlatCheckBox, savePhaseCheckBox, DeltaBetaCheckBox, ImageRestoreCheckBox,
											saveNameFlatText, saveNamePhaseText,
											formatFlatCombo, formatPhaseCombo,
											showImagesCheckBox, ProgressBar, statusLabel,
											calFrom, calTo, calculateImagesFromCheckBox.isSelected(), prozZahl, 
											countStartAt, checkInterpolateFlat, checkBoxEdgeExtension.isSelected());
						
						ht.start();
					}
					else{
						GenericDialog gd = new GenericDialog("Failure");
						gd.addMessage( "process was canceled!\n" + imCounter + "  images calculated!");
						gd.showDialog();
						ProgressBar.setValue(0);
						statusLabel.setText("status...");
						imCounter = 0;
						checkEverythingOK = true;
					}
					checkEverythingOK = true;
				}
			});
		}
		return runButton;
	}

	/**
	 * This method initializes ProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	private JProgressBar getProgressBar() {
		if (ProgressBar == null) {
			ProgressBar = new JProgressBar();
			ProgressBar.setBounds(new Rectangle(40, 680, 716, 15));
		}
		return ProgressBar;
	}

	/**
	 * This method initializes scalingPhaseCombo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getScalingPhaseCombo() {
		if (scalingPhaseCombo == null) {
			scalingPhaseCombo = new JComboBox();
			scalingPhaseCombo.setBounds(new Rectangle(180, 535, 300, 20));
			scalingPhaseCombo.addItem(scaleOptArr[0]);
			scalingPhaseCombo.addItem(scaleOptArr[1]);
			scalingPhaseCombo.addItem(scaleOptArr[2]);
			scalingPhaseCombo.setSelectedIndex(scalePhase);
			if(ColorScalingPhase.equals("java.awt.Color[r=192,g=192,b=192]"))
				scalingPhaseCombo.setBackground(Color.lightGray);
			else
				scalingPhaseCombo.setBackground(Color.white);
			scalingPhaseCombo.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(scalingPhaseCombo.getSelectedIndex() == 2){
						scalingPhaseFromText.setBackground(Color.white);
						scalingPhaseToText.setBackground(Color.white);
					}
					else{
						scalingPhaseFromText.setBackground(Color.lightGray);
						scalingPhaseToText.setBackground(Color.lightGray);
					}
				}
			});
		}
		return scalingPhaseCombo;
	}

	/**
	 * This method initializes scalingPhaseFromText	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getScalingPhaseFromText() {
		if (scalingPhaseFromText == null) {
			scalingPhaseFromText = new JTextField();
			scalingPhaseFromText.setBounds(new Rectangle(550, 535, 40, 20));
			scalingPhaseFromText.setText(scalingPhaseFrom);
			if(ColorScalingPhaseFrom.equals("java.awt.Color[r=192,g=192,b=192]"))
				scalingPhaseFromText.setBackground(Color.lightGray);
			else
				scalingPhaseFromText.setBackground(Color.white);
			scalingPhaseFromText.selectAll();
		}
		return scalingPhaseFromText;
	}

	/**
	 * This method initializes scalingPhaseToText	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getScalingPhaseToText() {
		if (scalingPhaseToText == null) {
			scalingPhaseToText = new JTextField();
			scalingPhaseToText.setBounds(new Rectangle(650, 535, 40, 20));
			scalingPhaseToText.setText(scalingPhaseTo);
			if(ColorScalingPhaseTo.equals("java.awt.Color[r=192,g=192,b=192]"))
				scalingPhaseToText.setBackground(Color.lightGray);
			else
				scalingPhaseToText.setBackground(Color.white);
			scalingPhaseToText.selectAll();
		}
		return scalingPhaseToText;
	}


	/**
	 * This method initializes loadParMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getLoadParMenuItem() {
		if (loadParMenuItem == null) {
			loadParMenuItem = new JMenuItem();
			loadParMenuItem.setText("Load parameter");
			loadParMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Act: "+actDirectory);
					System.out.println("Load: "+pathnameLoadPar);
					JFileChooser jfc = null;
					if(pathnameLoadPar==null)
						jfc = new JFileChooser(actDirectory);
					else
						jfc = new JFileChooser(pathnameLoadPar);
					jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
					jfc.setDialogTitle("Load parameter ...");
					jfc.showOpenDialog(thisFrame);
					try{
						pathnameLoadPar = jfc.getSelectedFile().getAbsolutePath();
					}
					catch(NullPointerException npe){
					}
					initializeParFile(true);
					setPar();
				}
			});
		}
		return loadParMenuItem;
	}

	/**
	 * This method initializes saveParMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getSaveParMenuItem() {
		if (saveParMenuItem == null) {
			saveParMenuItem = new JMenuItem();
			saveParMenuItem.setText("Save parameter");
			saveParMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFileChooser jfc = new JFileChooser(actDirectory);
					jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
					jfc.setDialogTitle("save parameter-file");
					jfc.showSaveDialog(thisFrame);
					try{
						pathnameSavePar = jfc.getSelectedFile().getAbsolutePath();
					}
					catch(NullPointerException npe){
					}
					writePar wp2 = new writePar(pathnameSavePar);
					wp2.runWritePar(pathnameProj, pathnameDark, pathnameFlat1, pathnameFlat2,
							stringDarkCheckBox, stringFlat1CheckBox, stringFlat2CheckBox,
							stringSaveFlatCheckBox, pathnameFlatOut, saveNameFlatText.getText(), formatsArr[formatFlatCombo.getSelectedIndex()],
							scaleOptArr[scalingFlatCombo.getSelectedIndex()], scalingFlatFromText.getText(), scalingFlatToText.getText(),
							stringSavePhaseCheckBox, stringDeltaBetaCheckBox, stringImageRestoreCheckBox, betaText.getText(), deltaText.getText(), DeltaBetaValue.getText(), ImageRestoreGaussValue.getText(), ImageRestoreOffsetValue.getText(), distanceText.getText(), energyText.getText(), pixSizeText.getText(),
							pathnamePhaseOut, saveNamePhaseText.getText(), formatsArr[formatPhaseCombo.getSelectedIndex()],
							scaleOptArr[scalingPhaseCombo.getSelectedIndex()], scalingPhaseFromText.getText(), scalingPhaseToText.getText(), stringCalImFromToCheckBox,
							calculateImagesFromText.getText(), calculateImagesToText.getText(), stringShowImagesCheckBox, CPUsText.getText(), countStartTextField.getText(),
							darkDirText.getBackground().toString(), flat1DirText.getBackground().toString(), flat2DirText.getBackground().toString(), saveFlatPathText.getBackground().toString(),
							saveNameFlatText.getBackground().toString(), formatFlatCombo.getBackground().toString(), scalingFlatCombo.getBackground().toString(),
							scalingFlatFromText.getBackground().toString(), scalingFlatToText.getBackground().toString(),
							savePhasePathText.getBackground().toString(), betaText.getBackground().toString(), deltaText.getBackground().toString(), DeltaBetaValue.getBackground().toString(), ImageRestoreGaussValue.getBackground().toString(), ImageRestoreOffsetValue.getBackground().toString(), distanceText.getBackground().toString(), energyText.getBackground().toString(), pixSizeText.getBackground().toString(),
							saveNamePhaseText.getBackground().toString(), formatPhaseCombo.getBackground().toString(),
							scalingPhaseCombo.getBackground().toString(), scalingPhaseFromText.getBackground().toString(), scalingPhaseToText.getBackground().toString(),
							calculateImagesFromText.getBackground().toString(), calculateImagesToText.getBackground().toString(), CPUsText.getBackground().toString(), stringInterpolateCheckBox,stringEdgeExtensionCheckBox);
				}
			});
		}
		return saveParMenuItem;
	}

	/**
	 * This method initializes countStartTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getCountStartTextField() {
		if (countStartTextField == null) {
			countStartTextField = new JTextField();
			countStartTextField.setLocation(new Point(220, 627));
			countStartTextField.setPreferredSize(new Dimension(50, 20));
			countStartTextField.setSize(new Dimension(50, 20));
			countStartTextField.setText(countStart);
			countStartTextField.selectAll();
		}
		return countStartTextField;
	}

	/**
	 * This method initializes interpolateFlatCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getInterpolateFlatCheckBox() {
		if (interpolateFlatCheckBox == null) {
			interpolateFlatCheckBox = new JCheckBox();
			interpolateFlatCheckBox.setBounds(new Rectangle(730, 320, 231, 22));
			interpolateFlatCheckBox.setText("interpolate flat-field samples");
			interpolateFlatCheckBox.setSelected(checkInterpolateFlat);
			interpolateFlatCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(flat2DirCheckBox.isSelected() == true && flat1CheckBox.isSelected() == true){
						if(checkInterpolateFlat==false){
							interpolateFlatCheckBox.setSelected(true);
							checkInterpolateFlat = true;
						}
						else{
							interpolateFlatCheckBox.setSelected(false);
							checkInterpolateFlat = false;
						}
					}
					else{
						interpolateFlatCheckBox.setSelected(false);
						checkInterpolateFlat = false;
					}
				}
			});
		}
		return interpolateFlatCheckBox;
	}

	/**
	 * This method initializes checkBoxEdgeExtension	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getCheckBoxEdgeExtension() {
		if (checkBoxEdgeExtension == null) {
			checkBoxEdgeExtension = new JCheckBox();
			checkBoxEdgeExtension.setBounds(new Rectangle(730, 535, 231, 22));
			checkBoxEdgeExtension.setText("no automatic edge extension");
			checkBoxEdgeExtension.setSelected(edgeExtension);
		}
		return checkBoxEdgeExtension;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		checkPlugin = false;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ANKAphase_ thisClass = new ANKAphase_();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}
	
	public void run(String args) {
		// TODO Auto-generated method stub
		checkPlugin = true;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ANKAphase_ thisClass = new ANKAphase_();
				thisClass.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public ANKAphase_() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		initializeParFile(false);
		this.setContentPane(getJContentPane());
		this.setJMenuBar(getMainMenuBar());
		this.setTitle("ANKAphase 2.1");
//		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(1024, 818));
		this.setSize(new Dimension(1024, 818));
		this.setLocation(new Point(0, 0));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			thisFrame.setPreferredSize(new Dimension(1024, 818));
//			thisFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			countStartLabel = new JLabel();
			countStartLabel.setBounds(new Rectangle(64, 627, 144, 16));
			countStartLabel.setText("count starting at:");
			URL bildURL = getClass().getResource("/ankalogoX.gif"); //ankaLogoKlein.jpg
			//System.out.println("jLabelHello=" + bildURL);
			jLabelLogo = new JLabel(new ImageIcon(bildURL));
			jLabelLogo.setText("JLabel");
			jLabelLogo.setBounds(new Rectangle(790, 612, 178, 100));
			scalingPhaseToLabel = new JLabel();
			scalingPhaseToLabel.setBounds(new Rectangle(610, 535, 38, 20));
			scalingPhaseToLabel.setText("to:");
			scalingPhaseFromLabel = new JLabel();
			scalingPhaseFromLabel.setBounds(new Rectangle(500, 535, 38, 20));
			scalingPhaseFromLabel.setText("from:");
			scalingPhaseLabel = new JLabel();
			scalingPhaseLabel.setBounds(new Rectangle(60, 535, 94, 20));
			scalingPhaseLabel.setText("scaling option:");
			statusLabel = new JLabel();
			statusLabel.setBounds(new Rectangle(40, 660, 174, 16));
			statusLabel.setText("status...");
			CPUsLabel = new JLabel();
			CPUsLabel.setBounds(new Rectangle(570, 590, 38, 20));
			CPUsLabel.setText("CPUs:");
			calculateImagesToLabel = new JLabel();
			calculateImagesToLabel.setBounds(new Rectangle(270, 590, 24, 20));
			calculateImagesToLabel.setText("to:");
			formatPhaseLabel = new JLabel();
			formatPhaseLabel.setBounds(new Rectangle(738, 500, 59, 20));
			formatPhaseLabel.setText("format:");
			savePhaseNameLabel = new JLabel();
			savePhaseNameLabel.setBounds(new Rectangle(530, 500, 74, 20));
			savePhaseNameLabel.setText("save name:");
			savePhasePathLabel = new JLabel();
			savePhasePathLabel.setBounds(new Rectangle(60, 500, 95, 20));
			savePhasePathLabel.setText("save directory:");
			pixSizeUnitLabel = new JLabel();
			pixSizeUnitLabel.setBounds(new Rectangle(735, 465, 90, 20));
			pixSizeUnitLabel.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
			pixSizeUnitLabel.setText("microns");
			pixSizeLabel = new JLabel();
			pixSizeLabel.setBounds(new Rectangle(605, 465, 69, 20));
			pixSizeLabel.setText("pixel size:");
			energyUnitLabel = new JLabel();
			energyUnitLabel.setBounds(new Rectangle(540, 465, 47, 20));
			energyUnitLabel.setText("keV");
			energyLabel = new JLabel();
			energyLabel.setBounds(new Rectangle(425, 465, 48, 20));
			energyLabel.setText("energy:");
			distanceUnitLabel = new JLabel();
			distanceUnitLabel.setBounds(new Rectangle(365, 465, 50, 20));
			distanceUnitLabel.setText("mm");
			distanceLabel = new JLabel();
			distanceLabel.setBounds(new Rectangle(235, 465, 60, 20));
			distanceLabel.setText("distance:");
			deltaUntitLabel = new JLabel();
			deltaUntitLabel.setBounds(new Rectangle(505, 435, 64, 20));
			deltaUntitLabel.setText("*10^-6");
			deltaLabel = new JLabel();
			deltaLabel.setBounds(new Rectangle(405, 435, 38, 20));
			deltaLabel.setText("delta:");
			ImageRestoreGaussLabel = new JLabel();
			ImageRestoreGaussLabel.setBounds(new Rectangle(540, 405, 90, 20));
			ImageRestoreGaussLabel.setText("Gauss width:");
			ImageRestoreGaussUnit = new JLabel();
			ImageRestoreGaussUnit.setBounds(new Rectangle(665, 405, 60, 20));
			ImageRestoreGaussUnit.setText("*pixel size");
			ImageRestoreOffsetLabel = new JLabel();
			ImageRestoreOffsetLabel.setBounds(new Rectangle(745, 405, 70, 20));
			ImageRestoreOffsetLabel.setText("stabiliser:");
			betaUnitLabel = new JLabel();
			betaUnitLabel.setBounds(new Rectangle(335, 435, 62, 20));
			betaUnitLabel.setText("*10^-9");
			betaLabel = new JLabel();
			betaLabel.setBounds(new Rectangle(235, 435, 38, 20));
			betaLabel.setText("beta:");
			experimentParaLabel = new JLabel();
			experimentParaLabel.setBounds(new Rectangle(60, 465, 153, 16));
			experimentParaLabel.setText("experiment parameters:");
			phaseretrievalParaLabel = new JLabel();
			phaseretrievalParaLabel.setBounds(new Rectangle(60, 435, 173, 20));
			phaseretrievalParaLabel.setText("phase-retrieval parameters:");
			phaseretrievalSetLabel = new JLabel();
			phaseretrievalSetLabel.setBounds(new Rectangle(400, 370, 352, 24));
			phaseretrievalSetLabel.setFont(new Font("Dialog", Font.BOLD, 18));
			phaseretrievalSetLabel.setText("phase-retrieval settings");
			scalingFlatToLabel = new JLabel();
			scalingFlatToLabel.setBounds(new Rectangle(610, 320, 38, 20));
			scalingFlatToLabel.setText("to:");
			scalingFlatFromLabel = new JLabel();
			scalingFlatFromLabel.setBounds(new Rectangle(500, 320, 38, 20));
			scalingFlatFromLabel.setText("from:");
			scalingFlatLabel = new JLabel();
			scalingFlatLabel.setBounds(new Rectangle(60, 320, 94, 20));
			scalingFlatLabel.setText("scaling option:");
			formatFlatLabel = new JLabel();
			formatFlatLabel.setBounds(new Rectangle(738, 285, 59, 20));
			formatFlatLabel.setText("format:");
			saveFlatNameLabel = new JLabel();
			saveFlatNameLabel.setBounds(new Rectangle(530, 285, 74, 20));
			saveFlatNameLabel.setText("save name:");
			saveFlatPathLabel = new JLabel();
			saveFlatPathLabel.setBounds(new Rectangle(60, 285, 95, 20));
			saveFlatPathLabel.setText("save directory:");
			flatSetLabel = new JLabel();
			flatSetLabel.setBounds(new Rectangle(430, 215, 268, 27));
			flatSetLabel.setFont(new Font("Dialog", Font.BOLD, 18));
			flatSetLabel.setText("flat-field settings");
			projDirLabel = new JLabel();
			projDirLabel.setBounds(new Rectangle(60, 60, 213, 20));
			projDirLabel.setText("projection images directory:");
			generalLabel = new JLabel();
			generalLabel.setBounds(new Rectangle(434, 11, 301, 27));
			generalLabel.setFont(new Font("Dialog", Font.BOLD, 18));
			generalLabel.setText("general settings");
			ReferenzLabel = new JLabel();
			ReferenzLabel.setBounds(new Rectangle(120, 725, 943, 21));
			ReferenzLabel.setFont(new Font("Dialog", Font.BOLD, 16));
			ReferenzLabel.setText("T. Weitkamp, D. Haas, D. Wegrzynek, A. Rack, Journal of Synchrotron Radiation 18 (4), 617-629 (2011)");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(generalLabel, null);
			jContentPane.add(projDirLabel, null);
			jContentPane.add(ReferenzLabel, null);
			jContentPane.add(getProjDirText(), null);
			jContentPane.add(getBrowseProjButton(), null);
			jContentPane.add(getDarkCheckBox(), null);
			jContentPane.add(getDarkDirText(), null);
			jContentPane.add(getBrowseDarkButton(), null);
			jContentPane.add(getFlat1CheckBox(), null);
			jContentPane.add(getFlat1DirText(), null);
			jContentPane.add(getFlat1BrowseButton(), null);
			jContentPane.add(getFlat2DirCheckBox(), null);
			jContentPane.add(getFlat2DirText(), null);
			jContentPane.add(getFlat2BrowseButton(), null);
			jContentPane.add(getLinePanel1(), null);
			jContentPane.add(flatSetLabel, null);
			jContentPane.add(getSaveFlatCheckBox(), null);
			jContentPane.add(saveFlatPathLabel, null);
			jContentPane.add(getSaveFlatPathText(), null);
			jContentPane.add(getSaveFlatBrowseButton(), null);
			jContentPane.add(saveFlatNameLabel, null);
			jContentPane.add(getSaveNameFlatText(), null);
			jContentPane.add(formatFlatLabel, null);
			jContentPane.add(getFormatFlatCombo(), null);
			jContentPane.add(scalingFlatLabel, null);
			jContentPane.add(getScalingFlatCombo(), null);
			jContentPane.add(scalingFlatFromLabel, null);
			jContentPane.add(getScalingFlatFromText(), null);
			jContentPane.add(scalingFlatToLabel, null);
			jContentPane.add(getScalingFlatToText(), null);
			jContentPane.add(getLinePanel2(), null);
			jContentPane.add(phaseretrievalSetLabel, null);
			jContentPane.add(getSavePhaseCheckBox(), null);
			jContentPane.add(getDeltaBetaCheckBox(), null);
			jContentPane.add(getImageRestoreCheckBox(), null);
			jContentPane.add(experimentParaLabel, null);
			jContentPane.add(phaseretrievalParaLabel, null);
			jContentPane.add(betaLabel, null);
			jContentPane.add(getBetaText(), null);
			jContentPane.add(betaUnitLabel, null);
			jContentPane.add(deltaLabel, null);
			jContentPane.add(getDeltaText(), null);
			jContentPane.add(deltaUntitLabel, null);
			jContentPane.add(getDeltaBetaValue(), null);
			jContentPane.add(getImageRestoreGaussValue(), null);
			jContentPane.add(getImageRestoreOffsetValue(), null);
			jContentPane.add(ImageRestoreGaussLabel, null);
			jContentPane.add(ImageRestoreGaussUnit, null);
			jContentPane.add(ImageRestoreOffsetLabel, null);
			jContentPane.add(distanceLabel, null);
			jContentPane.add(getDistanceText(), null);
			jContentPane.add(distanceUnitLabel, null);
			jContentPane.add(energyLabel, null);
			jContentPane.add(getEnergyText(), null);
			jContentPane.add(energyUnitLabel, null);
			jContentPane.add(pixSizeLabel, null);
			jContentPane.add(getPixSizeText(), null);
			jContentPane.add(pixSizeUnitLabel, null);
			jContentPane.add(savePhasePathLabel, null);
			jContentPane.add(getSavePhasePathText(), null);
			jContentPane.add(getSavePhaseBrowseButton(), null);
			jContentPane.add(savePhaseNameLabel, null);
			jContentPane.add(getSaveNamePhaseText(), null);
			jContentPane.add(formatPhaseLabel, null);
			jContentPane.add(getFormatPhaseCombo(), null);
			jContentPane.add(scalingPhaseLabel, null);
			jContentPane.add(getScalingPhaseCombo(), null);
			jContentPane.add(scalingPhaseFromLabel, null);
			jContentPane.add(getScalingPhaseFromText(), null);
			jContentPane.add(scalingPhaseToLabel, null);
			jContentPane.add(getScalingPhaseToText(), null);
			jContentPane.add(getLinePanel3(), null);
			jContentPane.add(getCalculateImagesFromCheckBox(), null);
			jContentPane.add(getCalculateImagesFromText(), null);
			jContentPane.add(calculateImagesToLabel, null);
			jContentPane.add(getCalculateImagesToText(), null);
			jContentPane.add(getShowImagesCheckBox(), null);
			jContentPane.add(CPUsLabel, null);
			jContentPane.add(getCPUsText(), null);
			jContentPane.add(getStopButton(), null);
			jContentPane.add(getRunButton(), null);
			jContentPane.add(statusLabel, null);
			jContentPane.add(getProgressBar(), null);
			jContentPane.add(jLabelLogo, null);
			jContentPane.add(countStartLabel, null);
			jContentPane.add(getCountStartTextField(), null);
			jContentPane.add(getInterpolateFlatCheckBox(), null);
			jContentPane.add(getCheckBoxEdgeExtension(), null);
			jContentPane.add(getLinePanel4(), null);
		}
		return jContentPane;
	}
	
	private void initializeParFile(boolean pathIn){
		readPar rp = null;
		if(pathIn == false)
			rp = new readPar();
		if(pathIn == true)
			rp =new readPar(pathnameLoadPar);
		try{
			rp.runReadPar();
		//-----------------------------------------
		actDirectory = rp.pathnameProj;
		pathnameProj = rp.pathnameProj;
		pathnameDark = rp.pathnameDark;
		pathnameFlat1 = rp.pathnameFlat1;
		pathnameFlat2 = rp.pathnameFlat2;
		if(rp.DarkCheckBox.equals("false"))
			checkDark = false;
		else
			checkDark = true;
		if(rp.Flat1CheckBox.equals("false"))
			checkFlat1 = false;
		else
			checkFlat1 = true;
		if(rp.Flat2CheckBox.equals("false"))
			checkFlat2 = false;
		else
			checkFlat2 = true;
		//-----------------------------------------
		if(rp.SaveFlatCheckBox.equals("false"))	
			checkSaveFlatCorrectedImages = false;
		else
			checkSaveFlatCorrectedImages = true;
		pathnameFlatOut = rp.pathnameFlatOut;
		saveNameFlat = rp.SaveNameFlat;
		readFormatCombo(rp.FormatFlat, "flat");
		readScaleCombo(rp.ScalingFlat, "flat");
		scalingFlatFrom = rp.ScalingFlatFromText;
		scalingFlatTo = rp.ScalingFlatToText;
		if(rp.interpolate.equals("false"))	
			checkInterpolateFlat = false;
		else
			checkInterpolateFlat = true;
		//-----------------------------------------
		betaString = rp.beta;
		deltaString = rp.delta;
		DeltaBetaString = rp.deltabeta;
		ImageRestoreGaussString = rp.gauss;
		ImageRestoreOffsetString = rp.offset;
		distanceString = rp.distance;
		energyString = rp.energy;
		pixSizeString = rp.pixSize;
		if(rp.SavePhaseCheckBox.equals("false"))	
			checkSavePhaseCorrectedImages = false;
		else
			checkSavePhaseCorrectedImages = true;
		if(rp.DeltaBetaCheckBox.equals("false"))	
			checkDeltaBetaSet = false;
		else
			checkDeltaBetaSet = true;
		if(rp.ImageRestoreCheckBox.equals("false"))	
			checkImageRestoreSet = false;
		else
			checkImageRestoreSet = true;
		pathnamePhaseOut = rp.pathnamePhaseOut;
		saveNamePhase = rp.SaveNamePhase;
		readFormatCombo(rp.FormatPhase, "phase");
		readScaleCombo(rp.ScalingPhase, "phase");
		scalingPhaseFrom = rp.ScalingPhaseFromText;
		scalingPhaseTo = rp.ScalingPhaseToText;
		if(rp.EdgeExtension.equals("false"))	
			edgeExtension = false;
		else
			edgeExtension = true;
		//--------------------------------------------
		if(rp.checkCalImFromTo.equals("false"))	
			checkCalImFromTo = false;
		else
			checkCalImFromTo = true;
		calculateImagesFrom = rp.calculateImFrom;
		calculateImagesTo = rp.calculateImTo;
		if(rp.showImages.equals("false"))	
			checkShowImages = false;
		else
			checkShowImages = true;
		CPUs = rp.CPUs;
		countStart = rp.countStart;
		//--------------------------------------------
		ColorDarkPath = rp.ColorDarkPath;
		ColorFlat1Path = rp.ColorFlat1Path;
		ColorFlat2Path = rp.ColorFlat2Path;
		ColorFlatPathOut = rp.ColorFlatPathOut;
		ColorSaveNameFlat = rp.ColorSaveNameFlat;
		ColorFormatFlat = rp.ColorFormatFlat;
		ColorScalingFlat = rp.ColorScalingFlat;
		ColorScalingFlatFrom = rp.ColorScalingFlatFrom;
		ColorScalingFlatTo = rp.ColorScalingFlatTo;
		ColorPhasePathOut = rp.ColorPhasePathOut;
		ColorBeta = rp.ColorBeta;
		ColorDelta = rp.ColorDelta;
		ColorDeltaBeta = rp.ColorDeltaBeta;
		ColorGauss = rp.ColorGauss;
		ColorOffset = rp.ColorOffset;
		ColorDistance = rp.ColorDistance;
		ColorEnergy = rp.ColorEnergy;
		ColorPixSize = rp.ColorPixSize;
		ColorSaveNamePhase = rp.ColorSaveNamePhase;
		ColorFormatPhase = rp.ColorFormatPhase;
		ColorScalingPhase = rp.ColorScalingPhase;
		ColorScalingPhaseFrom = rp.ColorScalingPhaseFrom;
		ColorScalingPhaseTo = rp.ColorScalingPhaseTo;
		ColorCalculateImagesFrom = rp.ColorCalculateImagesFrom;
		ColorCalculateImagesTo = rp.ColorCalculateImagesTo;
		}
		catch(NoSuchElementException nsee){
			GenericDialog gd5 = new GenericDialog("Failure");
			gd5.addMessage("Can't load parameter-file");
			gd5.showDialog();
		}
	}
	
	private void readFormatCombo(String format, String combo){
		if(combo.equals("flat")){
			if(format.equals("TIFF 8 bit"))
				formatFlat = 0;
			if(format.equals("TIFF 16 bit"))
				formatFlat = 1;
			if(format.equals("TIFF float"))
				formatFlat = 2;
			if(format.equals("JPEG 8 bit"))
				formatFlat = 3;
			if(format.equals("PNG 8 bit"))
				formatFlat = 4;
			if(format.equals("BMP 8 bit"))
				formatFlat = 5;
			if(format.equals("EDF float"))
				formatFlat = 6;
		}
		if(combo.equals("phase")){
			if(format.equals("TIFF 8 bit"))
				formatPhase = 0;
			if(format.equals("TIFF 16 bit"))
				formatPhase = 1;
			if(format.equals("TIFF float"))
				formatPhase = 2;
			if(format.equals("JPEG 8 bit"))
				formatPhase = 3;
			if(format.equals("PNG 8 bit"))
				formatPhase = 4;
			if(format.equals("BMP 8 bit"))
				formatPhase = 5;
			if(format.equals("EDF float"))
				formatPhase = 6;
		}
	}
	
	private void readScaleCombo(String scale, String combo){
		if(combo.equals("flat")){
			if(scale.equals("Scale each image to its min/max"))
				scaleFlat = 0;
			if(scale.equals("Scale to 3*(max-min) of first image"))
				scaleFlat = 1;
			if(scale.equals("Scale to user-specified value range"))
				scaleFlat = 2;
		}
		if(combo.equals("phase")){
			if(scale.equals("Scale each image to its min/max"))
				scalePhase = 0;
			if(scale.equals("Scale to 3*(max-min) of first image"))
				scalePhase = 1;
			if(scale.equals("Scale to user-specified value range"))
				scalePhase = 2;
		}
	}

	private boolean checkEverythingIsOK(){
		
		checkEverythingOK = true;
		
		//einige Variablen berechnen_______________________________________________________________________________
		
		//Pfade checken und erstellen______________________________________________________________________________
		
		//Windows----------------------------------
		String OpSystem = ManagementFactory.getOperatingSystemMXBean().getName();
		if(OpSystem.startsWith("W")){
			pathnameProj = projDirText.getText();
			if (pathnameProj.endsWith("\\")){}
			else{
				pathnameProj = pathnameProj + "\\";}
			pathnameDark = darkDirText.getText();
			if (pathnameDark.endsWith("\\")){}
			else{
				pathnameDark = pathnameDark + "\\";}
			pathnameFlat1 = flat1DirText.getText();
			if (pathnameFlat1.endsWith("\\")){}
			else{
				pathnameFlat1 = pathnameFlat1 + "\\";}
			pathnameFlat2 = flat2DirText.getText();
			if (pathnameFlat2.endsWith("\\")){}
			else{
				pathnameFlat2 = pathnameFlat2 + "\\";}
			pathnameFlatOut = saveFlatPathText.getText();
			if (pathnameFlatOut.endsWith("\\")){}
			else{
				pathnameFlatOut = pathnameFlatOut + "\\";}
			pathnamePhaseOut = savePhasePathText.getText();
			if (pathnamePhaseOut.endsWith("\\")){}
			else{
				pathnamePhaseOut = pathnamePhaseOut + "\\";}
		}
		if(OpSystem.startsWith("L") || OpSystem.startsWith("M")){
			pathnameProj = projDirText.getText();
			if (pathnameProj.endsWith("/")){}
			else{
				pathnameProj = pathnameProj + "/";}
			pathnameDark = darkDirText.getText();
			if (pathnameDark.endsWith("/")){}
			else{
				pathnameDark = pathnameDark + "/";}
			pathnameFlat1 = flat1DirText.getText();
			if (pathnameFlat1.endsWith("/")){}
			else{
				pathnameFlat1 = pathnameFlat1 + "/";}
			pathnameFlat2 = flat2DirText.getText();
			if (pathnameFlat2.endsWith("/")){}
			else{
				pathnameFlat2 = pathnameFlat2 + "/";}
			pathnameFlatOut = saveFlatPathText.getText();
			if (pathnameFlatOut.endsWith("/")){}
			else{
				pathnameFlatOut = pathnameFlatOut + "/";}
			pathnamePhaseOut = savePhasePathText.getText();
			if (pathnamePhaseOut.endsWith("/")){}
			else{
				pathnamePhaseOut = pathnamePhaseOut + "/";}
		}
		
		if (saveFlatCheckBox.isSelected() == true){
			File fls = new File(pathnameFlatOut);
			if (fls.exists() == false){
				GenericDialog gd5 = new GenericDialog("Failure");
				gd5.addMessage("The Output directory\n" + pathnameFlatOut + "\ndoesn't exist!\nCreate?");
				gd5.showDialog();
				boolean checkCreate = gd5.wasCanceled();
				if (checkCreate == false){
					fls.mkdir();
				}
				else{
					checkEverythingOK = false;
				}
			}
		}
		if (checkEverythingOK == true){
		if (savePhaseCheckBox.isSelected() == true){
			File fls2 = new File(pathnamePhaseOut);
			if (fls2.exists() == false){
				GenericDialog gd5 = new GenericDialog("Failure");
				gd5.addMessage("The Output directory\n" + pathnamePhaseOut + "\ndoesn't exist!\nCreate?");
				gd5.showDialog();
				boolean checkCreate = gd5.wasCanceled();
				if (checkCreate == false){
					fls2.mkdir();
				}
				else{
					checkEverythingOK = false;
				}
			}
		}
		}
		if (checkEverythingOK == true){
		if (saveNameFlatText.getText().equals("")){
			GenericDialog gd5 = new GenericDialog("Failure");
			gd5.addMessage("The name of the flat-field files is not given!");
			gd5.showDialog();
			checkEverythingOK = false;
		}
		}
		if (checkEverythingOK == true){
		if (saveNamePhaseText.getText().equals("")){
			GenericDialog gd5 = new GenericDialog("Failure");
			gd5.addMessage("The name of the phase-retrieval files is not given!");
			gd5.showDialog();
			checkEverythingOK = false;
		}
		}
		
		
		if(savePhaseCheckBox.isSelected() == false)
			stringSavePhaseCheckBox = "false";
		else
			stringSavePhaseCheckBox = "true";
		if(DeltaBetaCheckBox.isSelected() == false)
			stringDeltaBetaCheckBox = "false";
		else
			stringDeltaBetaCheckBox = "true";
		if(ImageRestoreCheckBox.isSelected() == false)
			stringImageRestoreCheckBox = "false";
		else
			stringImageRestoreCheckBox = "true";
		if(saveFlatCheckBox.isSelected() == false)
			stringSaveFlatCheckBox = "false";
		else
			stringSaveFlatCheckBox = "true";
		if(darkCheckBox.isSelected() == false)
			stringDarkCheckBox = "false";
		else
			stringDarkCheckBox = "true";
		if(flat1CheckBox.isSelected() == false)
			stringFlat1CheckBox = "false";
		else
			stringFlat1CheckBox = "true";
		if(flat2DirCheckBox.isSelected() == false)
			stringFlat2CheckBox = "false";
		else
			stringFlat2CheckBox = "true";
		if(calculateImagesFromCheckBox.isSelected() == false)
			stringCalImFromToCheckBox = "false";
		else
			stringCalImFromToCheckBox = "true";
		if(showImagesCheckBox.isSelected() == false)
			stringShowImagesCheckBox = "false";
		else
			stringShowImagesCheckBox = "true";
		if(interpolateFlatCheckBox.isSelected() == false)
			stringInterpolateCheckBox = "false";
		else
			stringInterpolateCheckBox = "true";
		if(checkBoxEdgeExtension.isSelected() == false)
			stringEdgeExtensionCheckBox = "false";
		else
			stringEdgeExtensionCheckBox = "true";
		
	
		
		//Einlesen der Parameter__________________________________________________________________________________
		if (checkEverythingOK == true){
		try{
			beta = Float.valueOf(betaText.getText()).floatValue() / 1000000000;
			delta = Float.valueOf(deltaText.getText()).floatValue() / 1000000;
			deltabeta = Float.valueOf(DeltaBetaValue.getText()).floatValue();
			gauss = Float.valueOf(ImageRestoreGaussValue.getText()).floatValue();
			offset = Float.valueOf(ImageRestoreOffsetValue.getText()).floatValue();
			distance = Float.valueOf(distanceText.getText()).floatValue();
			energy = Float.valueOf(energyText.getText()).floatValue();
			pixSize = Float.valueOf(pixSizeText.getText()).floatValue() / 1000;
		}
		catch(NumberFormatException nfe){
			checkEverythingOK = false;
			GenericDialog gd = new GenericDialog("Failure");
			gd.addMessage("Something is wrong with the phase-retrieval parameters !");
			gd.showDialog();
		}
		}
	
		//Projektions-Bildern ueberpruefen_________________________________________________________________
		
		FilenameFilter filter = new Dateifilter();
		
		if (checkEverythingOK == true){
		try{
		File dataInProj = new File(pathnameProj);
		listProj = dataInProj.list(filter);
		Arrays.sort(listProj);
			for(int i =0; i < listProj.length; i++){
				statusLabel.setText("Testing Projections-directory 2...");
				statusLabel.paintImmediately(statusLabel.getVisibleRect());
				ProgressBar.setMaximum(listProj.length);
				if(listProj[i].toLowerCase().endsWith(".tif") || listProj[i].toLowerCase().endsWith(".tiff") || listProj[i].toLowerCase().endsWith(".jpeg") ||
						listProj[i].toLowerCase().endsWith(".jpg") || listProj[i].toLowerCase().endsWith(".bmp") || listProj[i].toLowerCase().endsWith(".png")
						|| listProj[i].toLowerCase().endsWith(".edf")){
					ProgressBar.setValue(i+1);
					ProgressBar.paintImmediately(ProgressBar.getVisibleRect());
				}
				else{
					GenericDialog gd = new GenericDialog("Failure");
					gd.addMessage("There is a wrong file in the projektion directory !");
					gd.showDialog();
					checkEverythingOK = false;
					break;
				}
			}
			if(checkEverythingOK == true){
				if(listProj[0].toLowerCase().endsWith(".edf")){
					ReadHeader rh = new ReadHeader(listProj[0], pathnameProj);
					rh.runReadEDF();
					width = Integer.parseInt(rh.width);
					height = Integer.parseInt(rh.height);
				}
				else{
					ImagePlus imp1 = new ImagePlus(pathnameProj + listProj[0]);
					ip1 = imp1.getProcessor();							//Eingabebild
					width = ip1.getWidth();							//Breite des Eingabebildes
					height = ip1.getHeight();
				}
			}
		}
		catch(NullPointerException npe){
			checkEverythingOK = false;
			GenericDialog gd = new GenericDialog("Failure");
			gd.addMessage("The projection directory doesn't exist!");
			gd.showDialog();
		}
		catch(ArrayIndexOutOfBoundsException aioobe){
			checkEverythingOK = false;
			GenericDialog gd = new GenericDialog("Failure");
			gd.addMessage("No Files in the projection directory!");
			gd.showDialog();
		}
		}
			
		//Median von Dark-Field-Bildern erstellen_________________________________________________________________
		if (checkEverythingOK == true){
		try{
		if (darkCheckBox.isSelected() == true){
		File dataInDark = new File(pathnameDark);
		listDark = dataInDark.list(filter);
		Arrays.sort(listDark);
			for(int i =0; i < listDark.length; i++){
				statusLabel.setText("Testing dark-field-directory...");
				statusLabel.paintImmediately(statusLabel.getVisibleRect());
				ProgressBar.setMaximum(listDark.length);
				if(listDark[i].toLowerCase().endsWith(".tif") || listDark[i].toLowerCase().endsWith(".tiff") || listDark[i].toLowerCase().endsWith(".jpeg") ||
						listDark[i].toLowerCase().endsWith(".jpg") || listDark[i].toLowerCase().endsWith(".bmp") || listDark[i].toLowerCase().endsWith(".png")
						|| listDark[i].toLowerCase().endsWith(".edf")){
					ProgressBar.setValue(i+1);
					ProgressBar.paintImmediately(ProgressBar.getVisibleRect());
				}
				else{
					checkDarkDirectory = true;
					GenericDialog gd = new GenericDialog("Failure");
					gd.addMessage("There is a wrong file in the dark-field directory !");
					gd.showDialog();
					checkEverythingOK = false;
					break;
				}
			}
			if (checkDarkDirectory == false){
				FlatKorrektur pcDark = new FlatKorrektur();
				pcDark.createImageStack(pathnameDark, listDark);
				statusLabel.setText("Calculating median...");
				ImagePlus impInD = pcDark.doMedianProjection();
				dark = impInD.getProcessor();
				dark = dark.convertToFloat();
			}
		}
		else{
			if(listProj[0].toLowerCase().endsWith(".edf")){
				ReadHeader rh = new ReadHeader(listProj[0], pathnameProj);
				rh.runReadEDF();
				width = Integer.parseInt(rh.width);
				height = Integer.parseInt(rh.height);
				boolean byteOrder;
				if(rh.byteOrder.equals("HighByteFirst"))
					byteOrder = false;
				else
					byteOrder = true;
				try{
					ImportDialog d = new ImportDialog(listProj[0], pathnameProj, width, height, byteOrder,rh.typeIm, rh.headSize);
					d.openImage();
					dark = d.getIp();
				}
				catch(OutOfMemoryError oome){
					GenericDialog gd = new GenericDialog("Failure");
					gd.addMessage( "Heap space out of memory!\n"  + "0  images calculated!");
					gd.showDialog();
					ProgressBar.setValue(0);
					statusLabel.setText("status...");
					imCounter = 0;
					checkEverythingOK = false;
				}
			}
			else{
				ImagePlus imp1 = new ImagePlus(pathnameProj + listProj[0]);
				dark = imp1.getProcessor();							//Eingabebild
				width = dark.getWidth();							//Breite des Eingabebildes
				height = dark.getHeight();
			}
			for (int i = 0; i < width; i++){
				for (int j = 0; j < height; j++){
					dark.putPixelValue(i, j, 0.0);
				}
			}
			dark = dark.convertToFloat();
		}
		if (dark.getWidth() != width || dark.getHeight() != height){
			GenericDialog gd = new GenericDialog("Failure");
			gd.addMessage("The size of the dark field images are wrong!");
			gd.showDialog();
			checkEverythingOK = false;
		}
		}
		catch(NullPointerException npe){
			checkEverythingOK = false;
			GenericDialog gd = new GenericDialog("Failure");
			gd.addMessage("The darkfield directory doesn't exist!");
			gd.showDialog();
		}
		catch(ArrayIndexOutOfBoundsException aioobe){
			checkEverythingOK = false;
			GenericDialog gd = new GenericDialog("Failure");
			gd.addMessage("No Files in the darkfield directory!");
			gd.showDialog();
		}
		}
		
		//Median von Flat1-Field-Bildern erstellen_________________________________________________________________
		if (checkEverythingOK == true){
		try{
		if (flat1CheckBox.isSelected() == true){
		File dataInFlat1 = new File(pathnameFlat1);
		listFlat1 = dataInFlat1.list(filter);
		Arrays.sort(listFlat1);
			for(int i =0; i < listFlat1.length; i++){
				statusLabel.setText("Testing flat-field-directory 1...");
				statusLabel.paintImmediately(statusLabel.getVisibleRect());
				ProgressBar.setMaximum(listFlat1.length);
				if(listFlat1[i].toLowerCase().endsWith(".tif") || listFlat1[i].toLowerCase().endsWith(".tiff") || listFlat1[i].toLowerCase().endsWith(".jpeg") ||
						listFlat1[i].toLowerCase().endsWith(".jpg") || listFlat1[i].toLowerCase().endsWith(".bmp") || listFlat1[i].toLowerCase().endsWith(".png")
						|| listFlat1[i].toLowerCase().endsWith(".edf")){
					ProgressBar.setValue(i+1);
					ProgressBar.paintImmediately(ProgressBar.getVisibleRect());
				}
				else{
					checkFlat1Directory = true;
					GenericDialog gd = new GenericDialog("Failure");
					gd.addMessage("There is a wrong file in the flat-field directory 1 !");
					gd.showDialog();
					checkEverythingOK = false;
					break;
				}
			}
			if (checkFlat1Directory == false){
				FlatKorrektur pcFlat1 = new FlatKorrektur();
				pcFlat1.createImageStack(pathnameFlat1, listFlat1);
				statusLabel.setText("Calculating median...");
				ImagePlus impInF1 = pcFlat1.doMedianProjection();
				flat1 = impInF1.getProcessor();
				flat1 = flat1.convertToFloat();
			}
			if (flat1.getWidth() != width || flat1.getHeight() != height){
				GenericDialog gd = new GenericDialog("Failure");
				gd.addMessage("The size of the flat field 1 images are wrong!");
				gd.showDialog();
				checkEverythingOK = false;
			}
		}
		}
		catch(NullPointerException npe){
			checkEverythingOK = false;
			GenericDialog gd = new GenericDialog("Failure");
			gd.addMessage("The flat-field 1 directory doesn't exist!");
			gd.showDialog();
		}
		catch(ArrayIndexOutOfBoundsException aioobe){
			checkEverythingOK = false;
			GenericDialog gd = new GenericDialog("Failure");
			gd.addMessage("No Files in the flat-field 1 directory!");
			gd.showDialog();
		}
		}
		
		
		//Median von Flat2-Field-Bildern erstellen_________________________________________________________________
		if (checkEverythingOK == true){
		try{
		if (flat2DirCheckBox.isSelected() == true){
		File dataInFlat2 = new File(pathnameFlat2);
		listFlat2 = dataInFlat2.list(filter);
		Arrays.sort(listFlat2);
			for(int i =0; i < listFlat2.length; i++){
				statusLabel.setText("Testing flat-field-directory 2...");
				statusLabel.paintImmediately(statusLabel.getVisibleRect());
				ProgressBar.setMaximum(listFlat2.length);
				if(listFlat2[i].toLowerCase().endsWith(".tif") || listFlat2[i].toLowerCase().endsWith(".tiff") || listFlat2[i].toLowerCase().endsWith(".jpeg") ||
						listFlat2[i].toLowerCase().endsWith(".jpg") || listFlat2[i].toLowerCase().endsWith(".bmp") || listFlat2[i].toLowerCase().endsWith(".png")
						|| listFlat2[i].toLowerCase().endsWith(".png") || listFlat2[i].toLowerCase().endsWith(".edf")){
					ProgressBar.setValue(i+1);
					ProgressBar.paintImmediately(ProgressBar.getVisibleRect());
				}
				else{
					checkFlat2Directory = true;
					GenericDialog gd = new GenericDialog("Failure");
					gd.addMessage("There is a wrong file in the flat-field directory 2 !");
					gd.showDialog();
					checkEverythingOK = false;
					break;
				}
			}
			if (checkFlat2Directory == false){
				FlatKorrektur pcFlat2 = new FlatKorrektur();
				pcFlat2.createImageStack(pathnameFlat2, listFlat2);
				statusLabel.setText("Calculating median...");
				ImagePlus impInF2 = pcFlat2.doMedianProjection();
				flat2 = impInF2.getProcessor();
				flat2 = flat2.convertToFloat();
			}
			if (flat2.getWidth() != width || flat2.getHeight() != height){
				GenericDialog gd = new GenericDialog("Failure");
				gd.addMessage("The size of the flat field 2 images are wrong!");
				gd.showDialog();
				checkEverythingOK = false;
			}
		}
		}
		catch(NullPointerException npe){
			checkEverythingOK = false;
			GenericDialog gd = new GenericDialog("Failure");
			gd.addMessage("The flat-field 2 directory doesn't exist!");
			gd.showDialog();
		}
		catch(ArrayIndexOutOfBoundsException aioobe){
			checkEverythingOK = false;
			GenericDialog gd = new GenericDialog("Failure");
			gd.addMessage("No Files in the flat-field 2 directory!");
			gd.showDialog();
		}
		}
		
		
		//Checken calculate from to________________________________________________________________________________
		if (checkEverythingOK == true){
		if(calculateImagesFromCheckBox.isSelected() == true){
			try{
				calFrom = Integer.parseInt(calculateImagesFromText.getText()) - 1;
				calTo = Integer.parseInt(calculateImagesToText.getText()) - 1;
			}
			catch(NumberFormatException nfe){
				checkEverythingOK = false;
				GenericDialog gd = new GenericDialog("Failure");
				gd.addMessage("Images to be processed can not be found !");
				gd.showDialog();
			}
		}
		}
		if (checkEverythingOK == true){
		if(calculateImagesFromCheckBox.isSelected() == true && calFrom < 0){
			checkEverythingOK = false;
			GenericDialog gd = new GenericDialog("Failure");
			gd.addMessage("calculate from images < 1!");
			gd.showDialog();
		}
		}
		if (checkEverythingOK == true){
		if(calculateImagesFromCheckBox.isSelected() == true && calTo >= listProj.length){
			checkEverythingOK = false;
			GenericDialog gd = new GenericDialog("Failure");
			gd.addMessage("calculate to images > files in the directory!");
			gd.showDialog();
		}
		}
		
		//Prozessorzahl �berpr�fen________________________________________________________________
		if (checkEverythingOK == true){
		try{
			prozZahl = Integer.parseInt(CPUsText.getText());
		}
		catch(NumberFormatException nfe){
			checkEverythingOK = false;
			GenericDialog gd = new GenericDialog("Failure");
			gd.addMessage("There is something wrong in the CPUs field !");
			gd.showDialog();
		}
		}
		if (checkEverythingOK == true){
		if(prozZahl > Runtime.getRuntime().availableProcessors()){
			checkEverythingOK = false;
			GenericDialog gd = new GenericDialog("Failure");
			gd.addMessage("There are not enough CPUs !");
			gd.showDialog();
		}
		}
		//count starting �berpr�fen________________________________________________________________
		if (checkEverythingOK == true){
		try{
			countStartAt = Integer.parseInt(countStartTextField.getText());
		}
		catch(NumberFormatException nfe){
			checkEverythingOK = false;
			GenericDialog gd = new GenericDialog("Failure");
			gd.addMessage("There is something wrong in the 'count start at' field !");
			gd.showDialog();
		}
		}
		if (checkEverythingOK == true){
			if(countStartAt < 0){
				checkEverythingOK = false;
				GenericDialog gd = new GenericDialog("Failure");
				gd.addMessage("The count number is < 0");
				gd.showDialog();
			}
		}
		
		
		return checkEverythingOK;
	}

	
	private void writeOutPar(){
		writePar wp = new writePar();
		wp.runWritePar(pathnameProj, pathnameDark, pathnameFlat1, pathnameFlat2,
				stringDarkCheckBox, stringFlat1CheckBox, stringFlat2CheckBox,
				stringSaveFlatCheckBox, pathnameFlatOut, saveNameFlatText.getText(), formatsArr[formatFlatCombo.getSelectedIndex()],
				scaleOptArr[scalingFlatCombo.getSelectedIndex()], scalingFlatFromText.getText(), scalingFlatToText.getText(),
				stringSavePhaseCheckBox, stringDeltaBetaCheckBox, stringImageRestoreCheckBox, betaText.getText(), deltaText.getText(), DeltaBetaValue.getText(), ImageRestoreGaussValue.getText(), ImageRestoreOffsetValue.getText(), distanceText.getText(), energyText.getText(), pixSizeText.getText(),
				pathnamePhaseOut, saveNamePhaseText.getText(), formatsArr[formatPhaseCombo.getSelectedIndex()],
				scaleOptArr[scalingPhaseCombo.getSelectedIndex()], scalingPhaseFromText.getText(), scalingPhaseToText.getText(), stringCalImFromToCheckBox,
				calculateImagesFromText.getText(), calculateImagesToText.getText(), stringShowImagesCheckBox, CPUsText.getText(), countStartTextField.getText(),
				darkDirText.getBackground().toString(), flat1DirText.getBackground().toString(), flat2DirText.getBackground().toString(), saveFlatPathText.getBackground().toString(),
				saveNameFlatText.getBackground().toString(), formatFlatCombo.getBackground().toString(), scalingFlatCombo.getBackground().toString(),
				scalingFlatFromText.getBackground().toString(), scalingFlatToText.getBackground().toString(),
				savePhasePathText.getBackground().toString(), betaText.getBackground().toString(), deltaText.getBackground().toString(), DeltaBetaValue.getBackground().toString(), ImageRestoreGaussValue.getBackground().toString(), ImageRestoreOffsetValue.getBackground().toString(), distanceText.getBackground().toString(), energyText.getBackground().toString(), pixSizeText.getBackground().toString(),
				saveNamePhaseText.getBackground().toString(), formatPhaseCombo.getBackground().toString(),
				scalingPhaseCombo.getBackground().toString(), scalingPhaseFromText.getBackground().toString(), scalingPhaseToText.getBackground().toString(),
				calculateImagesFromText.getBackground().toString(), calculateImagesToText.getBackground().toString(), CPUsText.getBackground().toString(),stringInterpolateCheckBox,stringEdgeExtensionCheckBox);
		
		if (savePhaseCheckBox.isSelected() == true){ 
		writePar wp2 = new writePar(pathnamePhaseOut + "parameter21.txt");
		wp2.runWritePar(pathnameProj, pathnameDark, pathnameFlat1, pathnameFlat2,
				stringDarkCheckBox, stringFlat1CheckBox, stringFlat2CheckBox,
				stringSaveFlatCheckBox, pathnameFlatOut, saveNameFlatText.getText(), formatsArr[formatFlatCombo.getSelectedIndex()],
				scaleOptArr[scalingFlatCombo.getSelectedIndex()], scalingFlatFromText.getText(), scalingFlatToText.getText(),
				stringSavePhaseCheckBox, stringDeltaBetaCheckBox, stringImageRestoreCheckBox, betaText.getText(), deltaText.getText(), DeltaBetaValue.getText(), ImageRestoreGaussValue.getText(), ImageRestoreOffsetValue.getText(), distanceText.getText(), energyText.getText(), pixSizeText.getText(),
				pathnamePhaseOut, saveNamePhaseText.getText(), formatsArr[formatPhaseCombo.getSelectedIndex()],
				scaleOptArr[scalingPhaseCombo.getSelectedIndex()], scalingPhaseFromText.getText(), scalingPhaseToText.getText(), stringCalImFromToCheckBox,
				calculateImagesFromText.getText(), calculateImagesToText.getText(), stringShowImagesCheckBox, CPUsText.getText(), countStartTextField.getText(),
				darkDirText.getBackground().toString(), flat1DirText.getBackground().toString(), flat2DirText.getBackground().toString(), saveFlatPathText.getBackground().toString(),
				saveNameFlatText.getBackground().toString(), formatFlatCombo.getBackground().toString(), scalingFlatCombo.getBackground().toString(),
				scalingFlatFromText.getBackground().toString(), scalingFlatToText.getBackground().toString(),
				savePhasePathText.getBackground().toString(), betaText.getBackground().toString(), deltaText.getBackground().toString(), DeltaBetaValue.getBackground().toString(),  ImageRestoreGaussValue.getBackground().toString(), ImageRestoreOffsetValue.getBackground().toString(), distanceText.getBackground().toString(), energyText.getBackground().toString(), pixSizeText.getBackground().toString(),
				saveNamePhaseText.getBackground().toString(), formatPhaseCombo.getBackground().toString(),
				scalingPhaseCombo.getBackground().toString(), scalingPhaseFromText.getBackground().toString(), scalingPhaseToText.getBackground().toString(),
				calculateImagesFromText.getBackground().toString(), calculateImagesToText.getBackground().toString(), CPUsText.getBackground().toString(), stringInterpolateCheckBox,stringEdgeExtensionCheckBox);
		}
		
		if (saveFlatCheckBox.isSelected() == true){ 
			writePar wp2 = new writePar(pathnameFlatOut + "parameter21.txt");
			wp2.runWritePar(pathnameProj, pathnameDark, pathnameFlat1, pathnameFlat2,
					stringDarkCheckBox, stringFlat1CheckBox, stringFlat2CheckBox,
					stringSaveFlatCheckBox, pathnameFlatOut, saveNameFlatText.getText(), formatsArr[formatFlatCombo.getSelectedIndex()],
					scaleOptArr[scalingFlatCombo.getSelectedIndex()], scalingFlatFromText.getText(), scalingFlatToText.getText(),
					stringSavePhaseCheckBox, stringDeltaBetaCheckBox, stringImageRestoreCheckBox, betaText.getText(), deltaText.getText(), DeltaBetaValue.getText(), ImageRestoreGaussValue.getText(), ImageRestoreOffsetValue.getText(), distanceText.getText(), energyText.getText(), pixSizeText.getText(),
					pathnamePhaseOut, saveNamePhaseText.getText(), formatsArr[formatPhaseCombo.getSelectedIndex()],
					scaleOptArr[scalingPhaseCombo.getSelectedIndex()], scalingPhaseFromText.getText(), scalingPhaseToText.getText(), stringCalImFromToCheckBox,
					calculateImagesFromText.getText(), calculateImagesToText.getText(), stringShowImagesCheckBox, CPUsText.getText(), countStartTextField.getText(),
					darkDirText.getBackground().toString(), flat1DirText.getBackground().toString(), flat2DirText.getBackground().toString(), saveFlatPathText.getBackground().toString(),
					saveNameFlatText.getBackground().toString(), formatFlatCombo.getBackground().toString(), scalingFlatCombo.getBackground().toString(),
					scalingFlatFromText.getBackground().toString(), scalingFlatToText.getBackground().toString(),
					savePhasePathText.getBackground().toString(), betaText.getBackground().toString(), deltaText.getBackground().toString(), DeltaBetaValue.getBackground().toString(), ImageRestoreGaussValue.getBackground().toString(), ImageRestoreOffsetValue.getBackground().toString(), distanceText.getBackground().toString(), energyText.getBackground().toString(), pixSizeText.getBackground().toString(),
					saveNamePhaseText.getBackground().toString(), formatPhaseCombo.getBackground().toString(),
					scalingPhaseCombo.getBackground().toString(), scalingPhaseFromText.getBackground().toString(), scalingPhaseToText.getBackground().toString(),
					calculateImagesFromText.getBackground().toString(), calculateImagesToText.getBackground().toString(), CPUsText.getBackground().toString(), stringInterpolateCheckBox,stringEdgeExtensionCheckBox);
		}
	}
	
	private void setPar(){
		projDirText.setText(pathnameProj);
		darkCheckBox.setSelected(checkDark);
		darkDirText.setText(pathnameDark);
		if(ColorDarkPath.equals("java.awt.Color[r=192,g=192,b=192]"))
			darkDirText.setBackground(Color.lightGray);
		else
			darkDirText.setBackground(Color.white);
		flat1CheckBox.setSelected(checkFlat1);
		flat1DirText.setText(pathnameFlat1);
		if(ColorFlat1Path.equals("java.awt.Color[r=192,g=192,b=192]"))
			flat1DirText.setBackground(Color.lightGray);
		else
			flat1DirText.setBackground(Color.white);
		flat2DirCheckBox.setSelected(checkFlat2);
		flat2DirText.setText(pathnameFlat2);
		if(ColorFlat2Path.equals("java.awt.Color[r=192,g=192,b=192]"))
			flat2DirText.setBackground(Color.lightGray);
		else
			flat2DirText.setBackground(Color.white);
		saveFlatCheckBox.setSelected(checkSaveFlatCorrectedImages);
		saveFlatPathText.setText(pathnameFlatOut);
		if(ColorFlatPathOut.equals("java.awt.Color[r=192,g=192,b=192]"))
			saveFlatPathText.setBackground(Color.lightGray);
		else
			saveFlatPathText.setBackground(Color.white);
		saveNameFlatText.setText(saveNameFlat);
		if(ColorSaveNameFlat.equals("java.awt.Color[r=192,g=192,b=192]"))
			saveNameFlatText.setBackground(Color.lightGray);
		else
			saveNameFlatText.setBackground(Color.white);
		formatFlatCombo.setSelectedIndex(formatFlat);
		if(ColorFormatFlat.equals("java.awt.Color[r=192,g=192,b=192]"))
			formatFlatCombo.setBackground(Color.lightGray);
		else
			formatFlatCombo.setBackground(Color.white);
		scalingFlatCombo.setSelectedIndex(scaleFlat);
		if(ColorScalingFlat.equals("java.awt.Color[r=192,g=192,b=192]"))
			scalingFlatCombo.setBackground(Color.lightGray);
		else
			scalingFlatCombo.setBackground(Color.white);
		scalingFlatFromText.setText(scalingFlatFrom);
		if(ColorScalingFlatFrom.equals("java.awt.Color[r=192,g=192,b=192]"))
			scalingFlatFromText.setBackground(Color.lightGray);
		else
			scalingFlatFromText.setBackground(Color.white);
		scalingFlatToText.setText(scalingFlatTo);
		if(ColorScalingFlatTo.equals("java.awt.Color[r=192,g=192,b=192]"))
			scalingFlatToText.setBackground(Color.lightGray);
		else
			scalingFlatToText.setBackground(Color.white);
		savePhaseCheckBox.setSelected(checkSavePhaseCorrectedImages);
		DeltaBetaCheckBox.setSelected(checkDeltaBetaSet);
		ImageRestoreCheckBox.setSelected(checkImageRestoreSet);
		betaText.setText(betaString);
		if(ColorBeta.equals("java.awt.Color[r=192,g=192,b=192]"))
			betaText.setBackground(Color.lightGray);
		else
			betaText.setBackground(Color.white);
		deltaText.setText(deltaString);
		if(ColorDelta.equals("java.awt.Color[r=192,g=192,b=192]"))
			deltaText.setBackground(Color.lightGray);
		else
			deltaText.setBackground(Color.white);
		DeltaBetaValue.setText(DeltaBetaString);	
		ImageRestoreGaussValue.setText(ImageRestoreGaussString);
		ImageRestoreOffsetValue.setText(ImageRestoreOffsetString);
		if(ColorDeltaBeta.equals("java.awt.Color[r=192,g=192,b=192]"))
			DeltaBetaValue.setBackground(Color.lightGray);
		else
			DeltaBetaValue.setBackground(Color.white);
		if(ColorGauss.equals("java.awt.Color[r=192,g=192,b=192]"))
			ImageRestoreGaussValue.setBackground(Color.lightGray);
		else
			ImageRestoreGaussValue.setBackground(Color.white);
		if(ColorOffset.equals("java.awt.Color[r=192,g=192,b=192]"))
			ImageRestoreOffsetValue.setBackground(Color.lightGray);
		else
			ImageRestoreOffsetValue.setBackground(Color.white);
		distanceText.setText(distanceString);
		if(ColorDistance.equals("java.awt.Color[r=192,g=192,b=192]"))
			distanceText.setBackground(Color.lightGray);
		else
			distanceText.setBackground(Color.white);
		energyText.setText(energyString);
		if(ColorEnergy.equals("java.awt.Color[r=192,g=192,b=192]"))
			energyText.setBackground(Color.lightGray);
		else
			energyText.setBackground(Color.white);
		pixSizeText.setText(pixSizeString);
		if(ColorPixSize.equals("java.awt.Color[r=192,g=192,b=192]"))
			pixSizeText.setBackground(Color.lightGray);
		else
			pixSizeText.setBackground(Color.white);
		savePhasePathText.setText(pathnamePhaseOut);
		if(ColorPhasePathOut.equals("java.awt.Color[r=192,g=192,b=192]"))
			savePhasePathText.setBackground(Color.lightGray);
		else
			savePhasePathText.setBackground(Color.white);
		saveNamePhaseText.setText(saveNamePhase);
		if(ColorSaveNamePhase.equals("java.awt.Color[r=192,g=192,b=192]"))
			saveNamePhaseText.setBackground(Color.lightGray);
		else
			saveNamePhaseText.setBackground(Color.white);
		formatPhaseCombo.setSelectedIndex(formatPhase);
		if(ColorFormatPhase.equals("java.awt.Color[r=192,g=192,b=192]"))
			formatPhaseCombo.setBackground(Color.lightGray);
		else
			formatPhaseCombo.setBackground(Color.white);
		scalingPhaseCombo.setSelectedIndex(scalePhase);
		if(ColorScalingPhase.equals("java.awt.Color[r=192,g=192,b=192]"))
			scalingPhaseCombo.setBackground(Color.lightGray);
		else
			scalingPhaseCombo.setBackground(Color.white);
		scalingPhaseFromText.setText(scalingPhaseFrom);
		if(ColorScalingPhaseFrom.equals("java.awt.Color[r=192,g=192,b=192]"))
			scalingPhaseFromText.setBackground(Color.lightGray);
		else
			scalingPhaseFromText.setBackground(Color.white);
		scalingPhaseToText.setText(scalingPhaseTo);
		if(ColorScalingPhaseTo.equals("java.awt.Color[r=192,g=192,b=192]"))
			scalingPhaseToText.setBackground(Color.lightGray);
		else
			scalingPhaseToText.setBackground(Color.white);
		calculateImagesFromCheckBox.setSelected(checkCalImFromTo);
		calculateImagesFromText.setText(calculateImagesFrom);
		if(ColorCalculateImagesFrom.equals("java.awt.Color[r=192,g=192,b=192]"))
			calculateImagesFromText.setBackground(Color.lightGray);
		else
			calculateImagesFromText.setBackground(Color.white);
		calculateImagesToText.setText(calculateImagesTo);
		if(ColorCalculateImagesTo.equals("java.awt.Color[r=192,g=192,b=192]"))
			calculateImagesToText.setBackground(Color.lightGray);
		else
			calculateImagesToText.setBackground(Color.white);
		showImagesCheckBox.setSelected(checkShowImages);
		CPUsText.setText(CPUs);
		countStartTextField.setText(countStart);
		interpolateFlatCheckBox.setSelected(checkInterpolateFlat);
		checkBoxEdgeExtension.setSelected(edgeExtension);
	}

	
}  //  @jve:decl-index=0:visual-constraint="10,10"
