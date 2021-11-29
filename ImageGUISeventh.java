
	package ImageViewer;


	import java.awt.BorderLayout;
	import java.awt.Color;
	import java.awt.Dimension;
	import java.awt.FlowLayout;
	import java.awt.Graphics;
	import java.awt.Graphics2D;
	import java.awt.Image;
	import java.awt.Window;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.image.BufferedImage;
	import java.io.File;
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Scanner;

	import javax.imageio.ImageIO;
	import javax.swing.BoxLayout;
	import javax.swing.ImageIcon;
	import javax.swing.JButton;
	import javax.swing.JComboBox;
	import javax.swing.JDialog;
	import javax.swing.JFileChooser;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JMenu;
	import javax.swing.JMenuBar;
	import javax.swing.JMenuItem;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JScrollPane;
	import javax.swing.JTextField;
	import javax.swing.Timer;

	// Reminder: ADD MENU,ADD Scrollable Pane to ImageComp, ADD image ICON,  Change window sizable

	public class ImageGUISeventh extends JFrame {
		private final int WIDTH = 512;
		private final int HEIGHT = 450;
		// ImageIcon Load Change
		private ImageIcon Thumbnail = new ImageIcon(
				"/Users/royvaknin/Documents/workspaceOG/CSC 4ST/src/ImageViewer/Ocean.jpg");

		// Graphics for Main
		private GraphicPanelMain graphicsPanel;

		// Image Comp Display
		private ImageCompDisplay ImageComp;
		// Histogram Frame
		private HistogramFrame HistogramDisplay;

		private ThumbnailDis Thumb;

		// Control Panel Left
		private ImageControlPanel controlPanel;

		private BufferedImage MainImage;
		private int MainImageW;
		private int MainImageH;

		// private CompDisplayGraphics DisplayGraphics;
		private BufferedImage RedImage;
		private BufferedImage GreenImage;
		private BufferedImage BlueImage;

		private ImageObject ImageOB;

		private ImageObject SecondOB;

		private HistogramClass Histogram;

		private HistogramClass SecondHistogram;

		private int ColorChanger;
		
		
		
		// filter op vars 
		private double [][]kernel;
		
		private int [] origin;
		
		private FilterOps FilterClass;

		public ImageGUISeventh() {
			// Sets MainImage and ColoredImages to Default For Base Appearance
			try {

				MainImage = ImageIO
						.read(new File("/Users/royvaknin/Documents/workspaceOG/CSC 4ST/src/ImageViewer/Ocean.jpg"));
				RedImage = ImageIO
						.read(new File("/Users/royvaknin/Documents/workspaceOG/CSC 4ST/src/ImageViewer/Ocean.jpg"));
				GreenImage = ImageIO
						.read(new File("/Users/royvaknin/Documents/workspaceOG/CSC 4ST/src/ImageViewer/Ocean.jpg"));
				BlueImage = ImageIO
						.read(new File("/Users/royvaknin/Documents/workspaceOG/CSC 4ST/src/ImageViewer/Ocean.jpg"));
				ImageOB = new ImageObject();

				SecondOB = new ImageObject();

				Histogram = new HistogramClass();

				SecondHistogram = new HistogramClass();
				
				FilterClass = new FilterOps();

				ImageOB.loadImage("/Users/royvaknin/Documents/workspaceOG/CSC 4ST/src/ImageViewer/Ocean.jpg");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// -- set the application title
			setTitle("Image Processing Application");

			// -- size of the frame: width, height
			setSize(WIDTH, HEIGHT);

			// -- center the frame on the screen
			setLocationRelativeTo(null);

			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			// -- set the layout manager and add items
			// 5, 5 is the border around the edges of the areas
			setLayout(new BorderLayout(5, 5));

			// -- construct a JPanel for graphics
			graphicsPanel = new GraphicPanelMain();
			this.add(graphicsPanel, BorderLayout.CENTER);

			// -- construct a JPanel for controls
			controlPanel = new ImageControlPanel();
			controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

			this.add(controlPanel, BorderLayout.WEST);

			// adds another Jframe for ComP Display
			ImageComp = new ImageCompDisplay();

			HistogramDisplay = new HistogramFrame();

			// MENU Settings
			JMenuBar MenBar = new JMenuBar();
			JMenu Menu1 = new JMenu("Menu");
			JMenuItem Load = new JMenuItem("Load");
			JMenuItem Save = new JMenuItem("Save");

			// Point Op Settings
			JMenu PointOp = new JMenu("Point OP");
			JMenu PointOpSub = new JMenu("Point Operations...");

			JMenuItem PixelMath = new JMenuItem("PixelMath...");
			JMenuItem ContrastEnhance = new JMenuItem("Contrast Enhance...");
			JMenuItem ImageMath = new JMenuItem("Image Math...");
			JMenuItem GammaCorrect = new JMenuItem("Gamma Correction...");
			JMenuItem AlphaBlending = new JMenuItem("AlphaBlend...");

			// Histogram Operations
			JMenu HistoOps = new JMenu("Histogram Operations...");
			JMenuItem HistogramEqual = new JMenuItem("Histogram Equalization...");
			JMenuItem HistogramMatching = new JMenuItem("Histogram Matching...");

			JMenu ColorOps = new JMenu("Color Operations");
			JMenuItem BinaryThresh = new JMenuItem("Binarization...");
			JMenuItem Luminance = new JMenuItem("Luminance...");
			JMenuItem DeSaturation = new JMenuItem("Desaturation...");
			
			
			//FilterOps
			JMenu Filters = new JMenu("Filters");
			JMenuItem Convolution = new JMenuItem("Convolution...");
			JMenuItem MedianFilter = new JMenuItem("Median Filter...");
			JMenuItem OutlierFilter = new JMenuItem("Outlier Filter...");
			JMenuItem GaussianFilter = new JMenuItem("Gaussian2D Filter...");

			// Menu 1
			Menu1.add(Load);
			Menu1.add(Save);
			MenBar.add(Menu1);

			// Point Op Menu
			PointOpSub.add(PixelMath);
			PointOpSub.add(ContrastEnhance);
			PointOpSub.add(ImageMath);
			PointOpSub.add(GammaCorrect);
			PointOpSub.add(AlphaBlending);
			PointOp.add(PointOpSub);

			// Histo Ops sub menu
			HistoOps.add(HistogramEqual);
			HistoOps.add(HistogramMatching);
			PointOp.add(HistoOps);

			// Color Ops Sub Menu

			ColorOps.add(BinaryThresh);
			ColorOps.add(Luminance);
			ColorOps.add(DeSaturation);
			PointOp.add(ColorOps);

			MenBar.add(PointOp);
			
			//Filters
			Filters.add(Convolution);
			Filters.add(MedianFilter);
			Filters.add(OutlierFilter);
			Filters.add(GaussianFilter);

			MenBar.add(Filters);

			this.add(MenBar, BorderLayout.NORTH);

			// JScrollPane scrollPane = new JScrollPane();
			// scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			// scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			// graphicsPanel.add(scrollPane);

			// ACTIONLISTENERS FOR MENU
			Save.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFileChooser jfc = new JFileChooser();
					if (jfc.showDialog(null, "Save") == JFileChooser.APPROVE_OPTION) {
						File file = jfc.getSelectedFile();
						try {
							// File outputfile = new File("saved.png");
							// ImageIO.write(MainImage, "png" ,outputfile);
							ImageIO.write(MainImage, "png", new File(file.getAbsolutePath()));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					// -- send focus back to the graphicsPanel
					graphicsPanel.requestFocus();
				}
			});

			// Load Function Menu
			Load.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFileChooser jfc = new JFileChooser();
					if (jfc.showDialog(null, "Load") == JFileChooser.APPROVE_OPTION) {
						try {
							MainImage = ImageIO.read(new File(jfc.getSelectedFile().getPath()));
							MainImageW = MainImage.getWidth();
							MainImageH = MainImage.getHeight();
							// Thumbnail.setImage(MainImage);

							// RedImage Load Function
							RedImage = ImageIO.read(new File(jfc.getSelectedFile().getPath()));
							// Green Image Load Function
							GreenImage = ImageIO.read(new File(jfc.getSelectedFile().getPath()));
							// BlueImage Load Function
							BlueImage = ImageIO.read(new File(jfc.getSelectedFile().getPath()));

							ImageOB.loadImage(jfc.getSelectedFile().getPath());

							// computes the histogram of the ImageOB with the image[][][] being the main
							// image
							Histogram.ComputeHistogramRed(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramGreen(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramBlue(ImageOB.FromBI(MainImage));

							System.out.println((float) Histogram.GetSDevRed());

							graphicsPanel.repaint();
							ImageComp.repaint();
							Thumb.repaint();
							HistogramDisplay.repaint();
							graphicsPanel.requestFocus();
							ImageComp.requestFocus();

							// DisplayGraphics.repaint();

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					// -- send focus back to the graphicsPanel
					graphicsPanel.requestFocus();
				}
			});

			PixelMath.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFrame f = new JFrame();
					f.setLayout(new BorderLayout(5, 5));
					// f.setLayout( new FlowLayout(30,30, 30) );
					JPanel M = new JPanel();
					f.setTitle("Pixel Math Operations");

					String MainOp = "";

					String[] op = { "Add", "Subtract", "Multiply", "Divide" };
					// String UserOp = (String)JOptionPane.showInputDialog(d,
					// JOptionPane.PLAIN_MESSAGE,op);

					JTextField RedIn = new JTextField(10);
					JTextField GreenIn = new JTextField(10);
					JTextField BlueIn = new JTextField(10);

					JLabel Red = new JLabel("R:", 10);

					JLabel Green = new JLabel("G:");
					JLabel Blue = new JLabel("B:");

					JComboBox Ops = new JComboBox(op);
					Ops.setSelectedIndex(3);
					Ops.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							JComboBox cb = (JComboBox) e.getSource();
							String Operation = (String) cb.getSelectedItem();

						}
					}

					);
					JButton Begin = new JButton("Conduct");

					M.add(Red, BorderLayout.CENTER);
					M.add(RedIn, BorderLayout.CENTER);
					M.add(Green, BorderLayout.CENTER);
					M.add(GreenIn, BorderLayout.CENTER);
					M.add(Blue, BorderLayout.CENTER);
					M.add(BlueIn, BorderLayout.CENTER);
					M.add(Ops, BorderLayout.CENTER);
					M.add(Begin);

					f.add(M, BorderLayout.CENTER);

					f.setSize(150, 350);
					f.setVisible(true);

					// String text = RedIn.getText();
					// int RedValue = Integer.parseInt(text);

					Begin.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							String Operation = (String) Ops.getSelectedItem();

							System.out.println(Operation);

							String R = RedIn.getText();
							int RedValue = Integer.parseInt(R);

							String G = GreenIn.getText();
							int GreenValue = Integer.parseInt(G);

							String B = BlueIn.getText();
							int BlueValue = Integer.parseInt(B);

							if (Operation.equals("Add")) {
								ImageOB.FromBI(MainImage);
								for (int y = 0; y < ImageOB.getImage()[0].length; ++y) {
									for (int x = 0; x < ImageOB.getImage()[0][0].length; ++x) {
										ImageOB.getImage()[0][y][x] += RedValue;
										ImageOB.getImage()[1][y][x] += GreenValue;
										ImageOB.getImage()[2][y][x] += BlueValue;

										if (ImageOB.getImage()[0][y][x] > 255) {
											ImageOB.getImage()[0][y][x] = 255;
										}
										if (ImageOB.getImage()[1][y][x] > 255) {
											ImageOB.getImage()[1][y][x] = 255;
										}

										if (ImageOB.getImage()[2][y][x] > 255) {
											ImageOB.getImage()[2][y][x] = 255;
										}

									}
								}
								MainImage = ImageOB.toBI();
								Histogram.ComputeHistogramRed(ImageOB.FromBI(MainImage));
								Histogram.ComputeHistogramGreen(ImageOB.FromBI(MainImage));
								Histogram.ComputeHistogramBlue(ImageOB.FromBI(MainImage));

								graphicsPanel.repaint();
								ImageComp.repaint();
								Thumb.repaint();
								HistogramDisplay.repaint();

							}

							else if (Operation.equals("Subtract")) {

								ImageOB.FromBI(MainImage);
								for (int y = 0; y < ImageOB.getImage()[0].length; ++y) {
									for (int x = 0; x < ImageOB.getImage()[0][0].length; ++x) {
										ImageOB.getImage()[0][y][x] -= RedValue;
										ImageOB.getImage()[1][y][x] -= GreenValue;
										ImageOB.getImage()[2][y][x] -= BlueValue;

										if (ImageOB.getImage()[0][y][x] < 0) {
											ImageOB.getImage()[0][y][x] = 0;
										}
										if (ImageOB.getImage()[1][y][x] < 0) {
											ImageOB.getImage()[1][y][x] = 0;
										}

										if (ImageOB.getImage()[2][y][x] < 0) {
											ImageOB.getImage()[2][y][x] = 0;
										}

									}
								}

								MainImage = ImageOB.toBI();
								Histogram.ComputeHistogramRed(ImageOB.FromBI(MainImage));
								Histogram.ComputeHistogramGreen(ImageOB.FromBI(MainImage));
								Histogram.ComputeHistogramBlue(ImageOB.FromBI(MainImage));
								graphicsPanel.repaint();
								ImageComp.repaint();
								Thumb.repaint();
								HistogramDisplay.repaint();

							} else if (Operation.equals("Multiply")) {
								ImageOB.FromBI(MainImage);
								for (int y = 0; y < ImageOB.getImage()[0].length; ++y) {
									for (int x = 0; x < ImageOB.getImage()[0][0].length; ++x) {
										ImageOB.getImage()[0][y][x] *= RedValue;
										ImageOB.getImage()[1][y][x] *= GreenValue;
										ImageOB.getImage()[2][y][x] *= BlueValue;

										if (ImageOB.getImage()[0][y][x] > 255) {
											ImageOB.getImage()[0][y][x] = 255;
										}
										if (ImageOB.getImage()[1][y][x] > 255) {
											ImageOB.getImage()[1][y][x] = 255;
										}

										if (ImageOB.getImage()[2][y][x] > 255) {
											ImageOB.getImage()[2][y][x] = 255;
										}

									}
								}

								MainImage = ImageOB.toBI();
								Histogram.ComputeHistogramRed(ImageOB.FromBI(MainImage));
								Histogram.ComputeHistogramGreen(ImageOB.FromBI(MainImage));
								Histogram.ComputeHistogramBlue(ImageOB.FromBI(MainImage));
								graphicsPanel.repaint();
								ImageComp.repaint();
								Thumb.repaint();
								HistogramDisplay.repaint();

							} else if (Operation.equals("Divide")) {
								ImageOB.FromBI(MainImage);
								for (int y = 0; y < ImageOB.getImage()[0].length; ++y) {
									for (int x = 0; x < ImageOB.getImage()[0][0].length; ++x) {
										ImageOB.getImage()[0][y][x] = (int) (ImageOB.getImage()[0][y][x] / RedValue);
										ImageOB.getImage()[1][y][x] = (int) (ImageOB.getImage()[1][y][x] / GreenValue);
										ImageOB.getImage()[2][y][x] = (int) (ImageOB.getImage()[2][y][x] / BlueValue);

										if (ImageOB.getImage()[0][y][x] < 0) {
											ImageOB.getImage()[0][y][x] = 0;
										}
										if (ImageOB.getImage()[1][y][x] < 0) {
											ImageOB.getImage()[1][y][x] = 0;
										}

										if (ImageOB.getImage()[2][y][x] < 0) {
											ImageOB.getImage()[2][y][x] = 0;
										}

									}
								}

								MainImage = ImageOB.toBI();
								Histogram.ComputeHistogramRed(ImageOB.FromBI(MainImage));
								Histogram.ComputeHistogramGreen(ImageOB.FromBI(MainImage));
								Histogram.ComputeHistogramBlue(ImageOB.FromBI(MainImage));
								graphicsPanel.repaint();
								ImageComp.repaint();
								Thumb.repaint();
								HistogramDisplay.repaint();
							}

						}

					});

				}

			});

			ContrastEnhance.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFrame g = new JFrame();
					g.setLayout(new BorderLayout(5, 5));
					g.setTitle("Contrast Enhance");

					JPanel M = new JPanel();

					JTextField High = new JTextField(10);
					JTextField Low = new JTextField(10);

					JLabel Max = new JLabel("Max");

					JLabel Min = new JLabel("Min");

					JButton Begin = new JButton("Conduct");
					JButton Auto = new JButton("Auto Contrast");

					M.add(Max, BorderLayout.CENTER);
					M.add(High, BorderLayout.CENTER);
					M.add(Min, BorderLayout.CENTER);
					M.add(Low, BorderLayout.CENTER);

					M.add(Begin);
					M.add(Auto);

					g.add(M, BorderLayout.CENTER);

					g.setSize(150, 350);
					g.setVisible(true);

					Auto.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							ImageOB.FromBI(MainImage);
							Histogram.ComputeHistogramRed(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramGreen(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramBlue(ImageOB.FromBI(MainImage));

							int RedHigh = 0;
							int RedLow = 0;
							int GreenHigh = 0;
							int GreenLow = 0;
							int BlueHigh = 0;
							int BlueLow = 0;

							for (int i = 0; i < 256; ++i) {
								if (Histogram.getRedHistogram()[i] >= (int) ((ImageOB.getImage()[0][0].length)
										* (ImageOB.getImage()[0].length) * (0.05))) {
									RedLow = i;
								}

								if (Histogram.getGreenHistogram()[i] >= (int) ((ImageOB.getImage()[0][0].length)
										* (ImageOB.getImage()[0].length) * (0.05))) {
									GreenLow = i;
								}
								if (Histogram.getBlueHistogram()[i] >= (int) ((ImageOB.getImage()[0][0].length)
										* (ImageOB.getImage()[0].length) * (0.05))) {
									BlueLow = i;
								}

							}
							for (int i = 0; i < 256; ++i) {
								if (Histogram.getRedHistogram()[i] <= (int) ((ImageOB.getImage()[0][0].length)
										* (ImageOB.getImage()[0].length) * (1 - 0.01))) {
									RedHigh = i;
								}

								if (Histogram.getGreenHistogram()[i] <= (int) ((ImageOB.getImage()[0][0].length)
										* (ImageOB.getImage()[0].length) * (1 - 0.01))) {
									GreenHigh = i;
								}
								if (Histogram.getBlueHistogram()[i] <= (int) ((ImageOB.getImage()[0][0].length)
										* (ImageOB.getImage()[0].length) * (1 - 0.01))) {
									BlueHigh = i;
								}

							}

							for (int y = 0; y < ImageOB.getImage()[0].length; ++y) {
								for (int x = 0; x < ImageOB.getImage()[0][0].length; ++x) {
									ImageOB.getImage()[0][y][x] = ((255) / (RedHigh - RedLow))
											* ((ImageOB.getImage()[0][y][x]) - RedLow);
									ImageOB.getImage()[1][y][x] = ((255) / (GreenHigh - GreenLow))
											* ((ImageOB.getImage()[1][y][x]) - GreenLow);
									ImageOB.getImage()[2][y][x] = ((255) / (BlueHigh - BlueLow))
											* ((ImageOB.getImage()[2][y][x]) - BlueLow);

									if (ImageOB.getImage()[0][y][x] < 0) {
										ImageOB.getImage()[0][y][x] = 0;
									}
									if (ImageOB.getImage()[1][y][x] < 0) {
										ImageOB.getImage()[1][y][x] = 0;
									}
									if (ImageOB.getImage()[2][y][x] < 0) {
										ImageOB.getImage()[2][y][x] = 0;
									}
									if (ImageOB.getImage()[0][y][x] > 255) {
										ImageOB.getImage()[0][y][x] = 255;
									}
									if (ImageOB.getImage()[1][y][x] > 255) {
										ImageOB.getImage()[1][y][x] = 255;
									}

									if (ImageOB.getImage()[2][y][x] > 255) {
										ImageOB.getImage()[2][y][x] = 255;
									}

								}
							}

							MainImage = ImageOB.toBI();
							Histogram.ComputeHistogramRed(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramGreen(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramBlue(ImageOB.FromBI(MainImage));
							graphicsPanel.repaint();
							ImageComp.repaint();
							Thumb.repaint();
							HistogramDisplay.repaint();

						}

					}

					);

					Begin.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String m = Low.getText();
							int SMin = Integer.parseInt(m);

							String h = High.getText();
							int SMax = Integer.parseInt(h);

							ImageOB.FromBI(MainImage);
							for (int y = 0; y < ImageOB.getImage()[0].length; ++y) {
								for (int x = 0; x < ImageOB.getImage()[0][0].length; ++x) {
									ImageOB.getImage()[0][y][x] = ((255) / (SMax - SMin))
											* ((ImageOB.getImage()[0][y][x]) - SMin);
									ImageOB.getImage()[1][y][x] = ((255) / (SMax - SMin))
											* ((ImageOB.getImage()[1][y][x]) - SMin);
									ImageOB.getImage()[2][y][x] = ((255) / (SMax - SMin))
											* ((ImageOB.getImage()[2][y][x]) - SMin);

									if (ImageOB.getImage()[0][y][x] < 0) {
										ImageOB.getImage()[0][y][x] = 0;
									}
									if (ImageOB.getImage()[1][y][x] < 0) {
										ImageOB.getImage()[1][y][x] = 0;
									}
									if (ImageOB.getImage()[2][y][x] < 0) {
										ImageOB.getImage()[2][y][x] = 0;
									}
									if (ImageOB.getImage()[0][y][x] > 255) {
										ImageOB.getImage()[0][y][x] = 255;
									}
									if (ImageOB.getImage()[1][y][x] > 255) {
										ImageOB.getImage()[1][y][x] = 255;
									}

									if (ImageOB.getImage()[2][y][x] > 255) {
										ImageOB.getImage()[2][y][x] = 255;
									}

								}
							}

							MainImage = ImageOB.toBI();
							Histogram.ComputeHistogramRed(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramGreen(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramBlue(ImageOB.FromBI(MainImage));
							graphicsPanel.repaint();
							ImageComp.repaint();
							Thumb.repaint();
							HistogramDisplay.repaint();

						}

					}

					);
				}
			});

			ImageMath.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFrame f = new JFrame();
					f.setLayout(new BorderLayout(5, 5));
					f.setTitle("Image Math");
					// f.setLayout( new FlowLayout(30,30, 30) );
					JPanel M = new JPanel();

					String MainOp = "";

					String[] op = { "Add", "Subtract" };

					JLabel OP = new JLabel("Please Select Operation", 10);

					JComboBox Ops = new JComboBox(op);
					Ops.setSelectedIndex(1);
					Ops.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							JComboBox cb = (JComboBox) e.getSource();
							String Operation = (String) cb.getSelectedItem();

						}
					}

					);
					JButton LoadB = new JButton("Load");
					JButton Begin = new JButton("Conduct");

					M.add(OP, BorderLayout.CENTER);

					M.add(Ops, BorderLayout.CENTER);
					M.add(LoadB);
					M.add(Begin);

					f.add(M, BorderLayout.CENTER);

					f.setSize(160, 250);
					f.setVisible(true);

					LoadB.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							JFileChooser jfc = new JFileChooser();
							if (jfc.showDialog(null, "Load") == JFileChooser.APPROVE_OPTION) {
								try {
									BufferedImage SecondBuff = ImageIO.read(new File(jfc.getSelectedFile().getPath()));
									SecondOB.FromBI(SecondBuff);

								} catch (IOException ee) {
									// TODO Auto-generated catch block
									ee.printStackTrace();
								}

							}
						}
					});

					Begin.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							String Operation = (String) Ops.getSelectedItem();

							System.out.println(Operation);

							if (Operation.equals("Add")) {
								if ((ImageOB.getImage()[0].length)
										* (ImageOB.getImage()[0][0].length) != (SecondOB.getImage()[0].length)
												* (SecondOB.getImage()[0][0].length)) {
									try {
										throw new Exception("Not Same Size");

									} catch (Exception a) {

									}

								}

								else {
									ImageOB.FromBI(MainImage);
									for (int y = 0; y < ImageOB.getImage()[0].length; ++y) {
										for (int x = 0; x < ImageOB.getImage()[0][0].length; ++x) {
											ImageOB.getImage()[0][y][x] = (ImageOB.getImage()[0][y][x])
													+ (SecondOB.getImage()[0][y][x]);
											ImageOB.getImage()[1][y][x] = (ImageOB.getImage()[1][y][x])
													+ (SecondOB.getImage()[1][y][x]);
											ImageOB.getImage()[2][y][x] = (ImageOB.getImage()[2][y][x])
													+ (SecondOB.getImage()[2][y][x]);

											if (ImageOB.getImage()[0][y][x] > 255) {
												ImageOB.getImage()[0][y][x] = 255;
											}
											if (ImageOB.getImage()[1][y][x] > 255) {
												ImageOB.getImage()[1][y][x] = 255;
											}
											if (ImageOB.getImage()[2][y][x] > 255) {
												ImageOB.getImage()[2][y][x] = 255;
											}
										}
									}
									MainImage = ImageOB.toBI();
									Histogram.ComputeHistogramRed(ImageOB.FromBI(MainImage));
									Histogram.ComputeHistogramGreen(ImageOB.FromBI(MainImage));
									Histogram.ComputeHistogramBlue(ImageOB.FromBI(MainImage));
									graphicsPanel.repaint();
									ImageComp.repaint();
									Thumb.repaint();
									HistogramDisplay.repaint();

								}

							}

							if (Operation.equals("Subtract")) {
								if ((ImageOB.getImage()[0].length)
										* (ImageOB.getImage()[0][0].length) != (SecondOB.getImage()[0].length)
												* (SecondOB.getImage()[0][0].length)) {
									try {
										throw new Exception("Not Same Size");

									} catch (Exception a) {

									}

								}

								else {
									ImageOB.FromBI(MainImage);
									for (int y = 0; y < ImageOB.getImage()[0].length; ++y) {
										for (int x = 0; x < ImageOB.getImage()[0][0].length; ++x) {
											ImageOB.getImage()[0][y][x] = (ImageOB.getImage()[0][y][x])
													- (SecondOB.getImage()[0][y][x]);
											ImageOB.getImage()[1][y][x] = (ImageOB.getImage()[1][y][x])
													- (SecondOB.getImage()[1][y][x]);
											ImageOB.getImage()[2][y][x] = (ImageOB.getImage()[2][y][x])
													- (SecondOB.getImage()[2][y][x]);

											if (ImageOB.getImage()[0][y][x] < 0) {
												ImageOB.getImage()[0][y][x] = 0;
											}
											if (ImageOB.getImage()[1][y][x] < 0) {
												ImageOB.getImage()[1][y][x] = 0;
											}
											if (ImageOB.getImage()[2][y][x] < 0) {
												ImageOB.getImage()[2][y][x] = 0;
											}
										}
									}
									MainImage = ImageOB.toBI();
									Histogram.ComputeHistogramRed(ImageOB.FromBI(MainImage));
									Histogram.ComputeHistogramGreen(ImageOB.FromBI(MainImage));
									Histogram.ComputeHistogramBlue(ImageOB.FromBI(MainImage));
									graphicsPanel.repaint();
									ImageComp.repaint();
									Thumb.repaint();
									HistogramDisplay.repaint();

								}

							}
						}
					});

				}
			}

			);

			GammaCorrect.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFrame g = new JFrame();
					g.setLayout(new BorderLayout(5, 5));
					g.setTitle("Gamma Correction");
					JPanel M = new JPanel();

					JTextField RedIn = new JTextField(10);
					JTextField GreenIn = new JTextField(10);
					JTextField BlueIn = new JTextField(10);

					JLabel Red = new JLabel("R:");

					JLabel Green = new JLabel("G:");
					JLabel Blue = new JLabel("B:");

					JButton Begin = new JButton("Conduct");

					M.add(Red, BorderLayout.CENTER);
					M.add(RedIn, BorderLayout.CENTER);
					M.add(Green, BorderLayout.CENTER);
					M.add(GreenIn, BorderLayout.CENTER);
					M.add(Blue, BorderLayout.CENTER);
					M.add(BlueIn, BorderLayout.CENTER);
					M.add(Begin);

					g.add(M, BorderLayout.CENTER);

					g.setSize(150, 350);
					g.setVisible(true);

					Begin.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							String R = RedIn.getText();
							float RedValue = Float.parseFloat(R);

							String G = GreenIn.getText();
							float GreenValue = Float.parseFloat(G);

							String B = BlueIn.getText();
							float BlueValue = Float.parseFloat(B);

							ImageOB.FromBI(MainImage);

							for (int y = 0; y < ImageOB.getImage()[0].length; ++y) {
								for (int x = 0; x < ImageOB.getImage()[0][0].length; ++x) {
									double Red = ImageOB.getImage()[0][y][x];
									double Green = ImageOB.getImage()[1][y][x];
									double Blue = ImageOB.getImage()[2][y][x];
									Red = Red / 255.0;
									Green = Green / 255.0;
									Blue = Blue / 255.0;

									Red = Math.pow(Red, RedValue);
									Green = Math.pow(Green, GreenValue);
									Blue = Math.pow(Blue, BlueValue);

									Red = Red * 255.0;
									Green = Green * 255.0;
									Blue = Blue * 255.0;

									ImageOB.getImage()[0][y][x] = (int) Red;
									ImageOB.getImage()[1][y][x] = (int) Green;
									ImageOB.getImage()[2][y][x] = (int) Blue;

								}
							}

							MainImage = ImageOB.toBI();
							Histogram.ComputeHistogramRed(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramGreen(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramBlue(ImageOB.FromBI(MainImage));
							graphicsPanel.repaint();
							ImageComp.repaint();
							Thumb.repaint();
							HistogramDisplay.repaint();

						}
					});

				}

			});

			AlphaBlending.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFrame g = new JFrame();
					g.setLayout(new BorderLayout(5, 5));
					g.setTitle("Alpha Blend");

					JPanel M = new JPanel();

					JLabel Alpha = new JLabel("Enter Alpha Value Between 0-1");

					JTextField AlphaIn = new JTextField(10);

					JButton LoadB = new JButton("Load Second Image");
					JButton Begin = new JButton("Conduct");

					M.add(Alpha, BorderLayout.CENTER);
					M.add(AlphaIn, BorderLayout.CENTER);
					M.add(LoadB, BorderLayout.CENTER);
					M.add(Begin);

					g.add(M, BorderLayout.CENTER);

					g.setSize(200, 300);
					g.setVisible(true);

					LoadB.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							JFileChooser jfc = new JFileChooser();
							if (jfc.showDialog(null, "Load") == JFileChooser.APPROVE_OPTION) {
								try {
									BufferedImage SecondBuff = ImageIO.read(new File(jfc.getSelectedFile().getPath()));
									SecondOB.FromBI(SecondBuff);

								} catch (IOException ee) {
									// TODO Auto-generated catch block
									ee.printStackTrace();
								}

							}
						}
					});

					Begin.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String A = AlphaIn.getText();
							float AlphaValue = Float.parseFloat(A);
							ImageOB.FromBI(MainImage);
							if ((ImageOB.getImage()[0].length)
									* (ImageOB.getImage()[0][0].length) != (SecondOB.getImage()[0].length)
											* (SecondOB.getImage()[0][0].length)) {
								try {
									throw new Exception("Not Same Size");

								} catch (Exception a) {

								}

							} else {

								for (int y = 0; y < ImageOB.getImage()[0].length; ++y) {
									for (int x = 0; x < ImageOB.getImage()[0][0].length; ++x) {
										ImageOB.getImage()[0][y][x] = (int) ((AlphaValue * ImageOB.getImage()[0][y][x])
												+ ((1 - AlphaValue) * (SecondOB.getImage()[0][y][x])));
										ImageOB.getImage()[1][y][x] = (int) ((AlphaValue * ImageOB.getImage()[1][y][x])
												+ ((1 - AlphaValue) * (SecondOB.getImage()[1][y][x])));
										ImageOB.getImage()[2][y][x] = (int) ((AlphaValue * ImageOB.getImage()[2][y][x])
												+ ((1 - AlphaValue) * (SecondOB.getImage()[2][y][x])));

									}
								}

								MainImage = ImageOB.toBI();
								Histogram.ComputeHistogramRed(ImageOB.FromBI(MainImage));
								Histogram.ComputeHistogramGreen(ImageOB.FromBI(MainImage));
								Histogram.ComputeHistogramBlue(ImageOB.FromBI(MainImage));
								graphicsPanel.repaint();
								ImageComp.repaint();
								Thumb.repaint();
								HistogramDisplay.repaint();

							}

						}

					}

					);

				}
			}

			);

			// Histogram Equalization
			HistogramEqual.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFrame g = new JFrame();
					g.setLayout(new BorderLayout(5, 5));
					g.setTitle("HistogramEqual");

					JPanel M = new JPanel();

					JButton Start = new JButton("Conduct");

					M.add(Start);

					g.add(M, BorderLayout.CENTER);

					g.setSize(200, 150);
					g.setVisible(true);

					Start.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							ImageOB.FromBI(MainImage);
							Histogram.ComputeHistogramRed(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramGreen(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramBlue(ImageOB.FromBI(MainImage));

							// Cumulative Histograms
							Histogram.ComputeCumulativeHistogramRed();
							Histogram.ComputeCumulativeHistogramGreen();
							Histogram.ComputeCumulativeHistogramBlue();

							for (int y = 0; y < ImageOB.getImage()[0].length; ++y) {
								for (int x = 0; x < ImageOB.getImage()[0][0].length; ++x) {

									int Size = (ImageOB.getImage()[0].length) * (ImageOB.getImage()[0][0].length);

									int red = ImageOB.getImage()[0][y][x];
									int green = ImageOB.getImage()[1][y][x];
									int blue = ImageOB.getImage()[2][y][x];

									int R = (Histogram.getCumulativeHistogramRed()[red] * 255) / Size;
									int G = (Histogram.getCumulativeHistogramGreen()[green] * 255) / Size;
									int B = (Histogram.getCumulativeHistogramBlue()[blue] * 255) / Size;

									ImageOB.getImage()[0][y][x] = R;
									ImageOB.getImage()[1][y][x] = G;
									ImageOB.getImage()[2][y][x] = B;

								}
							}

							MainImage = ImageOB.toBI();
							Histogram.ComputeHistogramRed(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramGreen(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramBlue(ImageOB.FromBI(MainImage));
							graphicsPanel.repaint();
							ImageComp.repaint();
							Thumb.repaint();
							HistogramDisplay.repaint();

						}
					}

					);

				}
			}

			);

			HistogramMatching.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFrame g = new JFrame();
					g.setLayout(new BorderLayout(5, 5));
					g.setTitle("HistogramMatch");

					JPanel M = new JPanel();

					JButton LoadB = new JButton("Load Second Image");
					JButton Begin = new JButton("Conduct");

					M.add(LoadB, BorderLayout.CENTER);
					M.add(Begin);

					g.add(M, BorderLayout.CENTER);

					g.setSize(200, 300);
					g.setVisible(true);

					LoadB.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							JFileChooser jfc = new JFileChooser();
							if (jfc.showDialog(null, "Load") == JFileChooser.APPROVE_OPTION) {
								try {
									BufferedImage SecondBuff = ImageIO.read(new File(jfc.getSelectedFile().getPath()));
									SecondOB.FromBI(SecondBuff);
									SecondHistogram.ComputeHistogramRed(SecondOB.FromBI(MainImage));
									SecondHistogram.ComputeHistogramGreen(SecondOB.FromBI(MainImage));
									SecondHistogram.ComputeHistogramBlue(SecondOB.FromBI(MainImage));

								} catch (IOException ee) {
									// TODO Auto-generated catch block
									ee.printStackTrace();
								}
							}
						}
					});

					Begin.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							ImageOB.FromBI(MainImage);
							Histogram.ComputeHistogramRed(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramGreen(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramBlue(ImageOB.FromBI(MainImage));

							int K = 256;

							int red1 = 0;
							int green1 = 0;
							int blue1 = 0;
							int red2 = 0;
							int green2 = 0;
							int blue2 = 0;// sum all histogram values
							for (int i = 0; i < K; i++) {
								red1 += Histogram.getRedHistogram()[i];
								green1 += Histogram.getGreenHistogram()[i];
								blue1 += Histogram.getBlueHistogram()[i];

								red2 += SecondHistogram.getRedHistogram()[i];
								green2 += SecondHistogram.getGreenHistogram()[i];
								blue2 += SecondHistogram.getBlueHistogram()[i];
							}

							double[] RedT1 = new double[K];
							double[] GreenT1 = new double[K];
							double[] BlueT1 = new double[K];

							double[] RedT2 = new double[K];
							double[] GreenT2 = new double[K];
							double[] BlueT2 = new double[K];

							// create CDF table P ADDD
							int R1 = Histogram.getRedHistogram()[0];
							int G1 = Histogram.getGreenHistogram()[0];
							int B1 = Histogram.getBlueHistogram()[0];

							int R2 = SecondHistogram.getRedHistogram()[0];
							int G2 = SecondHistogram.getGreenHistogram()[0];
							int B2 = SecondHistogram.getBlueHistogram()[0];

							// cumulate histogram values
							RedT1[0] = (double) R1 / red1;
							GreenT1[0] = (double) G1 / green1;
							BlueT1[0] = (double) B1 / blue1;

							RedT2[0] = (double) R2 / red2;
							GreenT2[0] = (double) G2 / green2;
							BlueT2[0] = (double) B2 / blue2;

							for (int i = 1; i < K; i++) {
								R1 += Histogram.getRedHistogram()[i];
								G1 += Histogram.getGreenHistogram()[i];
								B1 += Histogram.getBlueHistogram()[i];

								R2 += SecondHistogram.getRedHistogram()[i];
								G2 += SecondHistogram.getGreenHistogram()[i];
								B2 += SecondHistogram.getBlueHistogram()[i];

								RedT1[i] = (double) R1 / red1;
								GreenT1[i] = (double) G1 / green1;
								BlueT1[i] = (double) B1 / blue1;

								RedT2[i] = (double) R2 / red2;
								GreenT2[i] = (double) G2 / green2;
								BlueT2[i] = (double) B2 / blue2;
							}

							double[] RedIm1 = RedT1;

							double[] RedIm2 = RedT2;

							double[] GreenIm1 = GreenT1;

							double[] GreenIm2 = GreenT2;

							double[] BlueIm1 = BlueT1;

							double[] BlueIm2 = BlueT2;

							int[] redfunction = new int[K];
							int[] greenfunction = new int[K];
							int[] bluefunction = new int[K];

							for (int a = 0; a < K; a++) {
								int f = K - 1;
								do {
									redfunction[a] = f;
									f--;

								} while (f >= 0 && RedIm1[a] <= RedIm2[f]);

							}
							

							for (int y = 0; y < ImageOB.getImage()[0].length; ++y) {
								for (int x = 0; x < ImageOB.getImage()[0][0].length; ++x) {
									

									ImageOB.getImage()[0][y][x] = redfunction[ImageOB.getImage()[0][y][x]];

									if (ImageOB.getImage()[0][y][x] < 0) {
										ImageOB.getImage()[0][y][x] = 0;
									}

									if (ImageOB.getImage()[0][y][x] > 255) {
										ImageOB.getImage()[0][y][x] = 255;
									}
								}

							}
							// }

							for (int a = 0; a < K; a++) {
								int f = K - 1;
								do {
									greenfunction[a] = f;
									f--;

								} while (f >= 0 && GreenIm1[a] <= GreenIm2[f]);
							}

							
							for (int y = 0; y < ImageOB.getImage()[0].length; ++y) {
								for (int x = 0; x < ImageOB.getImage()[0][0].length; ++x) {

									ImageOB.getImage()[1][y][x] = greenfunction[ImageOB.getImage()[1][y][x]];

									if (ImageOB.getImage()[1][y][x] < 0) {
										ImageOB.getImage()[1][y][x] = 0;
									}

									if (ImageOB.getImage()[1][y][x] > 255) {
										ImageOB.getImage()[1][y][x] = 255;
									}
								}

							}

							for (int a = 0; a < K; a++) {
								int f = K - 1;
								do {
									bluefunction[a] = f;
									f--;

								} while (f >= 0 && BlueIm1[a] <= BlueIm2[f]);
							}
							for (int y = 0; y < ImageOB.getImage()[0].length; ++y) {
								for (int x = 0; x < ImageOB.getImage()[0][0].length; ++x) {

									ImageOB.getImage()[2][y][x] = bluefunction[ImageOB.getImage()[2][y][x]];

									if (ImageOB.getImage()[2][y][x] < 0) {
										ImageOB.getImage()[2][y][x] = 0;
									}

									if (ImageOB.getImage()[2][y][x] > 255) {
										ImageOB.getImage()[2][y][x] = 255;
									}
								}

							}

							MainImage = ImageOB.toBI();
							Histogram.ComputeHistogramRed(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramGreen(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramBlue(ImageOB.FromBI(MainImage));
							graphicsPanel.repaint();
							ImageComp.repaint();
							Thumb.repaint();
							HistogramDisplay.repaint();
							

						}

					}

					);

				}
			});

			BinaryThresh.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFrame g = new JFrame();
					g.setLayout(new BorderLayout(5, 5));
					g.setTitle("HistogramEqual");
					JPanel M = new JPanel();

					JButton ThreshButton = new JButton("Conduct");
					JButton Auto = new JButton("Auto/Otsu");
					JLabel TValue = new JLabel("Threshold Value");
					JTextField TVal = new JTextField(10);

					// if img[][][] < Thresh Value, set = to 0, if > , set to 255

					M.add(TValue);
					M.add(TVal);
					M.add(ThreshButton);
					M.add(Auto);

					g.add(M, BorderLayout.CENTER);

					g.setSize(200, 200);
					g.setVisible(true);

					Auto.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							ImageOB.FromBI(MainImage);

							// creates temp image to be used to make the Y image, simply for computation
							int[][][] TempImg = new int[3][ImageOB.getImage()[0].length][ImageOB.getImage()[0][0].length];
							for (int y = 0; y < ImageOB.getImage()[0].length; ++y) {
								for (int x = 0; x < ImageOB.getImage()[0][0].length; ++x) {
									for (int r = 0; r < 3; r++) {
										int Y = (int) ((0.299 * ImageOB.getImage()[0][y][x])
												+ (0.587 * ImageOB.getImage()[1][y][x])
												+ (0.114 * ImageOB.getImage()[2][y][x]));
										TempImg[r][y][x] = Y;
									}
								}
							}

							// computes green histogram of Y Image
							Histogram.ComputeHistogramGreen(TempImg);

							int total = ImageOB.getImage()[0].length * ImageOB.getImage()[0][0].length;

							float sum = 0;
							for (int t = 0; t < 256; t++) {
								sum += t * Histogram.getGreenHistogram()[t];
							}

							float sumB = 0;
							int wB = 0;
							int wF = 0;

							float varMax = 0;
							int threshold = 0;

							for (int t = 0; t < 256; t++) {
								wB += Histogram.getGreenHistogram()[t];
								if (wB == 0)
									continue;

								wF = total - wB;
								if (wF == 0)
									break;

								sumB += (float) (t * Histogram.getGreenHistogram()[t]);

								float mB = sumB / wB;
								float mF = (sum - sumB) / wF;

								// Calculate Between Class Variance
								float varBetween = (float) wB * (float) wF * (mB - mF) * (mB - mF);

								// Check if new maximum found
								if (varBetween > varMax) {
									varMax = varBetween;
									threshold = t;
								}
							}
							for (int y = 0; y < ImageOB.getImage()[0].length; ++y) {
								for (int x = 0; x < ImageOB.getImage()[0][0].length; ++x) {
									if (ImageOB.getImage()[0][y][x] <= threshold) {
										ImageOB.getImage()[0][y][x] = 0;
									}
									if (ImageOB.getImage()[1][y][x] <= threshold) {
										ImageOB.getImage()[1][y][x] = 0;
									}
									if (ImageOB.getImage()[2][y][x] <= threshold) {
										ImageOB.getImage()[2][y][x] = 0;
									}

									if (ImageOB.getImage()[0][y][x] > threshold) {
										ImageOB.getImage()[0][y][x] = 255;
									}
									if (ImageOB.getImage()[1][y][x] > threshold) {
										ImageOB.getImage()[1][y][x] = 255;
									}
									if (ImageOB.getImage()[2][y][x] > threshold) {
										ImageOB.getImage()[2][y][x] = 255;
									}
								}
							}

							MainImage = ImageOB.toBI();
							Histogram.ComputeHistogramRed(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramGreen(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramBlue(ImageOB.FromBI(MainImage));
							graphicsPanel.repaint();
							ImageComp.repaint();
							Thumb.repaint();
							HistogramDisplay.repaint();

						}
					});

					ThreshButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							ImageOB.FromBI(MainImage);
							String V = TVal.getText();
							int Thresh = Integer.parseInt(V);

							for (int y = 0; y < ImageOB.getImage()[0].length; ++y) {
								for (int x = 0; x < ImageOB.getImage()[0][0].length; ++x) {
									if (ImageOB.getImage()[0][y][x] <= Thresh) {
										ImageOB.getImage()[0][y][x] = 0;
									}
									if (ImageOB.getImage()[1][y][x] <= Thresh) {
										ImageOB.getImage()[1][y][x] = 0;
									}
									if (ImageOB.getImage()[2][y][x] <= Thresh) {
										ImageOB.getImage()[2][y][x] = 0;
									}

									if (ImageOB.getImage()[0][y][x] > Thresh) {
										ImageOB.getImage()[0][y][x] = 255;
									}
									if (ImageOB.getImage()[1][y][x] > Thresh) {
										ImageOB.getImage()[1][y][x] = 255;
									}
									if (ImageOB.getImage()[2][y][x] > Thresh) {
										ImageOB.getImage()[2][y][x] = 255;
									}

								}
							}

							MainImage = ImageOB.toBI();
							Histogram.ComputeHistogramRed(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramGreen(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramBlue(ImageOB.FromBI(MainImage));
							graphicsPanel.repaint();
							ImageComp.repaint();
							Thumb.repaint();
							HistogramDisplay.repaint();

						}
					});

				}
			});

			Luminance.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFrame g = new JFrame();
					g.setLayout(new BorderLayout(5, 5));
					g.setTitle("Luminance");

					JPanel M = new JPanel();

					JButton Start = new JButton("Conduct");

					M.add(Start);

					g.add(M, BorderLayout.CENTER);

					g.setSize(200, 150);
					g.setVisible(true);

					Start.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							ImageOB.FromBI(MainImage);

							for (int y = 0; y < ImageOB.getImage()[0].length; ++y) {
								for (int x = 0; x < ImageOB.getImage()[0][0].length; ++x) {
									for (int i = 0; i < 3; i++) {

										int Y = (int) ((0.299 * ImageOB.getImage()[0][y][x])
												+ (0.587 * ImageOB.getImage()[1][y][x])
												+ (0.114 * ImageOB.getImage()[2][y][x]));
										ImageOB.getImage()[i][y][x] = Y;

									}
								}
							}

							MainImage = ImageOB.toBI();
							Histogram.ComputeHistogramRed(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramGreen(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramBlue(ImageOB.FromBI(MainImage));
							graphicsPanel.repaint();
							ImageComp.repaint();
							Thumb.repaint();
							HistogramDisplay.repaint();

						}
					}

					);

				}
			}

			);

			DeSaturation.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFrame g = new JFrame();
					g.setLayout(new BorderLayout(5, 5));
					g.setTitle("HistogramEqual");

					JPanel M = new JPanel();

					JButton Start = new JButton("Conduct");
					JLabel SValue = new JLabel("Enter a S value between 0-1");
					JTextField SVal = new JTextField(10);

					M.add(Start);
					M.add(SValue);
					M.add(SVal);

					g.add(M, BorderLayout.CENTER);

					g.setSize(200, 200);
					g.setVisible(true);

					Start.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							ImageOB.FromBI(MainImage);
							String V = SVal.getText();
							double S = Double.parseDouble(V);

							for (int y = 0; y < ImageOB.getImage()[0].length; ++y) {
								for (int x = 0; x < ImageOB.getImage()[0][0].length; ++x) {
									for (int i = 0; i < 3; i++) {

										double Y = ((0.299 * ImageOB.getImage()[0][y][x])
												+ (0.587 * ImageOB.getImage()[1][y][x])
												+ (0.114 * ImageOB.getImage()[2][y][x]));

										ImageOB.getImage()[0][y][x] = (int) (Y + S * (ImageOB.getImage()[0][y][x] - Y));
										ImageOB.getImage()[1][y][x] = (int) (Y + S * (ImageOB.getImage()[1][y][x] - Y));
										ImageOB.getImage()[2][y][x] = (int) (Y + S * (ImageOB.getImage()[2][y][x] - Y));

									}
								}
							}

							MainImage = ImageOB.toBI();
							Histogram.ComputeHistogramRed(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramGreen(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramBlue(ImageOB.FromBI(MainImage));
							graphicsPanel.repaint();
							ImageComp.repaint();
							Thumb.repaint();
							HistogramDisplay.repaint();
						}
					});

				}

			});
			
			
		Convolution.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					
					JFrame f = new JFrame();
					f.setLayout(new BorderLayout(5, 5));
					// f.setLayout( new FlowLayout(30,30, 30) );
					JPanel M = new JPanel();
					f.setTitle("Convolution");

					String MainOp = "";

					String[] op = { "MIRROR", "COPY", "IGNORE" };
					
					JButton LoadK = new JButton("Load Kernel");
					
					
					
					

					JComboBox Ops = new JComboBox(op);
					Ops.setSelectedIndex(2);
					Ops.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							JComboBox cb = (JComboBox) e.getSource();
							String Operation = (String) cb.getSelectedItem();

						}
					}

					);
					JButton Begin = new JButton("Conduct");

					M.add(LoadK);
					M.add(Ops, BorderLayout.CENTER);
					M.add(Begin);

					f.add(M, BorderLayout.CENTER);

					f.setSize(150, 350);
					f.setVisible(true);

					
					
					LoadK.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							JFileChooser cho = new JFileChooser();
							if (cho.showDialog(null, "Load") == JFileChooser.APPROVE_OPTION) {
								try {
									Scanner F = new Scanner(new File(cho.getSelectedFile().getPath()));
									int Height = F.nextInt();
									int Width = F.nextInt();
									kernel = new double [Height][Width];
									for(int i=0; i<Height; ++i) {
										for(int j=0; j<Width; ++j) {
											//String val = F.next();
											//kernel[i][j] = Double.parseDouble(val);
											kernel[i][j] = F.nextDouble();
											//System.out.print(kernel[i][j]);
										}
										
									}
									origin = new int [2];
									origin[0] = F.nextInt();
									origin[1] = F.nextInt();
									F.close();
									
							
								}
									
								 catch (IOException ee) {
									// TODO Auto-generated catch block
									ee.printStackTrace();
								}
							}
							
						}
					}
					);


					Begin.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							FilterOps.PStyle r = null;
							
							String Operation = (String) Ops.getSelectedItem();
							
							if(Operation.equals("MIRROR")) {
								 r = FilterOps.PStyle.MIRROR;
							}
							
							if(Operation.equals("COPY")) {
								r = FilterOps.PStyle.COPY;
							}
							if(Operation.equals("IGNORE")) {
								r = FilterOps.PStyle.IGNORE;
							}
							
							//ImageOB.FromBI(MainImage);
							
							double kernelSub [][]= {
									{1,1,1},
									{1,1,1},
									{1,1,1}
									
							};
							
							int originsub[] = {1,1};
							
							int Final[][][] = FilterOps.Convolution(ImageOB.getImage(), kernel, origin, r,true);
							//int Final[][][] = FilterOps.Convolution(ImageOB.getImage(), kernelSub, originsub, r,true);
							
							for (int y = 0; y < ImageOB.getImage()[0].length; ++y) {
								for (int x = 0; x < ImageOB.getImage()[0][0].length; ++x) {
									ImageOB.getImage()[0][y][x] = Final[0][y][x];
									ImageOB.getImage()[1][y][x] = Final[1][y][x];	
									ImageOB.getImage()[2][y][x] = Final[2][y][x];	
								}
							}
						
							MainImage = ImageOB.toBI();
							Histogram.ComputeHistogramRed(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramGreen(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramBlue(ImageOB.FromBI(MainImage));
							graphicsPanel.repaint();
							ImageComp.repaint();
							Thumb.repaint();
							HistogramDisplay.repaint();
						

						

						//	System.out.println(Operation);

							
						}
					}
					);
					
					
					
				}
		}
		);
		
		MedianFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					
					
					JFrame f = new JFrame();
					f.setLayout(new BorderLayout(5, 5));
					// f.setLayout( new FlowLayout(30,30, 30) );
					JPanel M = new JPanel();
					f.setTitle("Median Filter ");

					

					
					JTextField Rvalue = new JTextField(10);
					JLabel R = new JLabel("Enter a r value for filter size");
					JLabel Ex = new JLabel("eg. r=1 , 3X3 filter");
					JButton Begin = new JButton("Conduct");
					M.add(R);
					M.add(Ex);
					M.add(Rvalue);
					M.add(Begin);

					f.add(M, BorderLayout.CENTER);

					f.setSize(150, 350);
					f.setVisible(true);

					
					
					Begin.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String b = Rvalue.getText();
							int Rval = Integer.parseInt(b);	
							
							
	        int Final[][][] = FilterOps.MedianFilter(ImageOB.FromBI(MainImage),Rval);
							for (int y = 0; y < ImageOB.getImage()[0].length; ++y) {
								for (int x = 0; x < ImageOB.getImage()[0][0].length; ++x) {
									ImageOB.getImage()[0][y][x] = Final[0][y][x];
									ImageOB.getImage()[1][y][x] = Final[1][y][x];	
									ImageOB.getImage()[2][y][x] = Final[2][y][x];	
								}
							}
						
							MainImage = ImageOB.toBI();
							Histogram.ComputeHistogramRed(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramGreen(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramBlue(ImageOB.FromBI(MainImage));
							graphicsPanel.repaint();
							ImageComp.repaint();
							Thumb.repaint();
							HistogramDisplay.repaint();
						


				
						}
					}
					);
				
			}
	}
	);
		
		
		OutlierFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame g = new JFrame();
				g.setLayout(new BorderLayout(5, 5));
				g.setTitle("Outlier Filter");
				
				
				
				JPanel M = new JPanel();
				
		
				
				JTextField Rvalue = new JTextField(10);
				JLabel R = new JLabel("Enter a r value for filter size");
				JLabel Ex = new JLabel("eg. r=1 , 3X3 filter");
				
				JLabel Thresh = new JLabel("Enter a Threshold Value");
				JTextField Threshold = new JTextField(10);
				JButton Begin = new JButton("Conduct");
				M.add(R);
				M.add(Ex);
				M.add(Rvalue);
				M.add(Thresh);
				M.add(Threshold);
				M.add(Begin);

				g.add(M, BorderLayout.CENTER);

				g.setSize(190, 350);
				g.setVisible(true);

				
				
				Begin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String b = Rvalue.getText();
						int Rval = Integer.parseInt(b);	
						
						String t = Threshold.getText();
						
						int T= Integer.parseInt(t);
						
						  int Final[][][] = FilterOps.OutlierFilterOp(ImageOB.FromBI(MainImage),Rval,T);
							for (int y = 0; y < ImageOB.getImage()[0].length; ++y) {
								for (int x = 0; x < ImageOB.getImage()[0][0].length; ++x) {
									ImageOB.getImage()[0][y][x] = Final[0][y][x];
									ImageOB.getImage()[1][y][x] = Final[1][y][x];	
									ImageOB.getImage()[2][y][x] = Final[2][y][x];	
								}
							}
						
							MainImage = ImageOB.toBI();
							Histogram.ComputeHistogramRed(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramGreen(ImageOB.FromBI(MainImage));
							Histogram.ComputeHistogramBlue(ImageOB.FromBI(MainImage));
							graphicsPanel.repaint();
							ImageComp.repaint();
							Thumb.repaint();
							HistogramDisplay.repaint();
						
					}
				}
				);
			
		
				
				
				
			}
	}
	);
		
		GaussianFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				JFrame f = new JFrame();
				f.setLayout(new BorderLayout(5, 5));
				// f.setLayout( new FlowLayout(30,30, 30) );
				JPanel M = new JPanel();
				f.setTitle("Gaussian Filter");

				String MainOp = "";

				String[] op = { "MIRROR", "COPY", "IGNORE" };
				
				
				
				
				
				

				JComboBox Ops = new JComboBox(op);
				Ops.setSelectedIndex(2);
				Ops.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JComboBox cb = (JComboBox) e.getSource();
						String Operation = (String) cb.getSelectedItem();

					}
				}

				);
				JButton Begin = new JButton("Conduct");
				JLabel WidthL = new JLabel("Enter Width");
				JTextField Width = new JTextField(5);
				
				JLabel HeightL = new JLabel("Enter Height");
				JTextField Height = new JTextField(5);
				
				JLabel SigmaxL = new JLabel("Enter SigmaX");
				JTextField SigmaX = new JTextField(5);
				
				JLabel SigmayL = new JLabel("Enter SigmaY");
				JTextField SigmaY = new JTextField(5);
				
				M.add(WidthL);
				M.add(Width);
				
				M.add(HeightL);
				M.add(Height);
				
				M.add(SigmaxL);
				M.add(SigmaX);
				
				M.add(SigmayL);
				M.add(SigmaY);
				
				M.add(Ops, BorderLayout.CENTER);
				M.add(Begin);

				f.add(M, BorderLayout.CENTER);

				f.setSize(150, 350);
				f.setVisible(true);
				
				
				
				Begin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						FilterOps.PStyle r = null;
						
						String Operation = (String) Ops.getSelectedItem();
						
						if(Operation.equals("MIRROR")) {
							 r = FilterOps.PStyle.MIRROR;
						}
						
						if(Operation.equals("COPY")) {
							r = FilterOps.PStyle.COPY;
						}
						if(Operation.equals("IGNORE")) {
							r = FilterOps.PStyle.IGNORE;
						}
						
						String Wid = Width.getText();
						int w = Integer.parseInt(Wid);
						
						String Len = Height.getText();
						int h = Integer.parseInt(Len);
						
						String Sigx = SigmaX.getText();
						double Sigmax= Double.parseDouble(Sigx);
						
						String Sigy = SigmaY.getText();
						double Sigmayy = Double.parseDouble(Sigy);
						
					
						double Kernel [][] = FilterOps.Gaussian2DKernelOp(w,h,Sigmax,Sigmayy);
								
						
						int originsub[] = {(int)Kernel.length/2,(int)Kernel[0].length/2};
						
						int Final[][][] = FilterOps.Convolution(ImageOB.getImage(), Kernel, originsub, r,true);
						//int Final[][][] = FilterOps.Convolution(ImageOB.getImage(), kernelSub, originsub, r,true);
						
						for (int y = 0; y < ImageOB.getImage()[0].length; ++y) {
							for (int x = 0; x < ImageOB.getImage()[0][0].length; ++x) {
								ImageOB.getImage()[0][y][x] = Final[0][y][x];
								ImageOB.getImage()[1][y][x] = Final[1][y][x];	
								ImageOB.getImage()[2][y][x] = Final[2][y][x];	
							}
						}
					
						MainImage = ImageOB.toBI();
						Histogram.ComputeHistogramRed(ImageOB.FromBI(MainImage));
						Histogram.ComputeHistogramGreen(ImageOB.FromBI(MainImage));
						Histogram.ComputeHistogramBlue(ImageOB.FromBI(MainImage));
						graphicsPanel.repaint();
						ImageComp.repaint();
						Thumb.repaint();
						HistogramDisplay.repaint();
					

					

					//	System.out.println(Operation);

						
					}
				}
				);
				
				
		}
		
			
		}
		);


			// END OF ACTION LISTENERS FOR MENU

			// -- show the frame on the screen
			setVisible(true);

			// -- set keyboard focus to the graphics panel
			graphicsPanel.setFocusable(true);
			graphicsPanel.requestFocus();
			graphicsPanel.requestFocus();
			ImageComp.requestFocus();

		}

		// Graphics Class
		public class GraphicPanelMain extends JPanel {

			public void paint(Graphics g) {
				// -- the base class paintComponent(g) method ensures
				// the drawing area will be cleared properly. Do not
				// modify any attributes of g prior to sending it to
				// the base class
				super.paintComponent(g);

				Graphics2D graphicsContext = (Graphics2D) g;
				//int heightt = 450;
				//int widthh = 512;

				
				graphicsContext.drawImage(MainImage, 0, 0, MainImage.getWidth(), MainImage.getHeight(), null);
				// graphicsContext.drawImage(MainImage,0,0,MainImage.getWidth(),MainImage.getHeight(),null);

			}
		}

		public class ThumbnailDis extends JPanel {
			@Override
			public void paint(Graphics g) {

				super.paintComponent(g);

				Graphics2D graphicsContext = (Graphics2D) g;

				graphicsContext.drawImage(MainImage, 16, 10, 70, 70, null);
				// graphicsContext.drawImage(MainImage,0,0,MainImage.getWidth(),MainImage.getHeight(),null);

			}
		}

		// PASS IN J ICON IMAGE
		public class ImageControlPanel extends JPanel {

			// FUTURE ADDITIONS: MAKE IMAGE THUMBNAIL A SEPERATE CONTAINER AND ADD PAINT
			// FUNCTION
			public ImageControlPanel() {

				Thumb = new ThumbnailDis();
				// Image pic = Thumbnail.getImage();
				// Image newimg = pic.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
				// // scale it the smooth way
				// Thumbnail = new ImageIcon(newimg);

				// JButton Pic1 = new JButton("",Thumbnail);

				// this.add(Pic1,BorderLayout.WEST);
				this.add(Thumb, BorderLayout.WEST);
				// Pic1.repaint();
				setVisible(true);

			}

			// Controls size of ControlPanel
			public Dimension getPreferredSize() {
				return new Dimension(100, 500);
			}

		}

		public class ImageCompDisplay extends JFrame {
			private final int w = 320;
			private final int h = 650;
			CompDisplayGraphics CompDisGraphics;

			public ImageCompDisplay() {

				// -- set the application title
				setTitle("Image Component Display ");

				// -- size of the frame: width, height
				setSize(w, h);

				// -- center the frame on the screen
				setLocationRelativeTo(null);

				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				setLayout(new BorderLayout(5, 5));

				CompDisGraphics = new CompDisplayGraphics();
				this.add(CompDisGraphics, BorderLayout.CENTER);

				

				graphicsPanel.repaint();
				

				setVisible(true);

			}

			@Override
			public void paint(Graphics g) {
				super.paint(g);

				CompDisGraphics.repaint();
			}

		}

		public class CompDisplayGraphics extends JPanel {

			@Override
			public void paint(Graphics g) {
				// super.paintComponent(g);
				super.paint(g);

				Graphics2D graphicsContext = (Graphics2D) g;

				// Gets the Image and displays it

				graphicsContext.drawImage(RedImage, 0, 0, 350, 200, null);
				graphicsContext.drawImage(GreenImage, 0, 200, 350, 200, null);
				graphicsContext.drawImage(BlueImage, 0, 400, 350, 200, null);

				for (int y = 0; y < MainImage.getHeight(); y++) {
					for (int x = 0; x < MainImage.getWidth(); x++) {
						// Retrieving contents of a pixel
						int pixel = MainImage.getRGB(x, y);
						// Creating a Color object from pixel value
						Color colorRed = new Color(pixel, true);
						Color colorGreen = new Color(pixel, true);
						Color colorBlue = new Color(pixel, true);
						// Retrieving the R G B values
						int red = colorRed.getRed();
						int green = colorRed.getGreen();
						int blue = colorRed.getBlue();

						// Creating new Color object
						colorRed = new Color(red, red, red);
						colorGreen = new Color(green, green, green);
						colorBlue = new Color(blue, blue, blue);
						// Setting new Color object to the image
						RedImage.setRGB(x, y, colorRed.getRGB());
						GreenImage.setRGB(x, y, colorGreen.getRGB());
						BlueImage.setRGB(x, y, colorBlue.getRGB());

					}
				}

				// DisplayGraphics.repaint();
			}
		}

		public class HistogramFrame extends JFrame {
			private final int w = 500;
			private final int h = 200;

			// private int ColorMode;

			private HistogramGraphics HistoGraph;
			private HistogramControls HistoControl;

			public HistogramFrame() {

				// -- set the application title
				setTitle("Histogram Statistics ");

				// -- size of the frame: width, height
				setSize(w, h);

				// -- center the frame on the screen
				setLocationRelativeTo(null);

				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				setLayout(new BorderLayout(5, 5));

				HistoGraph = new HistogramGraphics();
				this.add(HistoGraph, BorderLayout.CENTER);

				HistoControl = new HistogramControls();
				HistoControl.setLayout(new BoxLayout(HistoControl, BoxLayout.Y_AXIS));
				this.add(HistoControl, BorderLayout.WEST);

				// graphicsPanel.repaint();
				HistoGraph.repaint();

				setVisible(true);

			}
		}

		public class HistogramGraphics extends JPanel {

			@Override
			public void paint(Graphics g) {
				// super.paintComponent(g);
				super.paint(g);

				Graphics2D graphicsContext = (Graphics2D) g;

				double h = 100; // -- height of the histogram display
				double mred = h / (Histogram.GetMaxValueRed() - Histogram.GetMinValueRed());
				double mgreen = h / (Histogram.GetMaxValueGreen() - Histogram.GetMinValueGreen());
				double mblue = h / (Histogram.GetMaxValueBlue() - Histogram.GetMinValueBlue());
				int scaledHRed[] = new int[256];
				int scaledHGreen[] = new int[256];
				int scaledHBlue[] = new int[256];
				for (int j = 0; j < 256; ++j) {
					scaledHRed[j] = (int) (mred * (Histogram.getRedHistogram()[j] - Histogram.GetMinValueRed()));
					scaledHGreen[j] = (int) (mgreen * (Histogram.getGreenHistogram()[j] - Histogram.GetMinValueGreen()));
					scaledHBlue[j] = (int) (mblue * (Histogram.getBlueHistogram()[j] - Histogram.GetMinValueBlue()));
				}

				int hi = this.getHeight();
				// graphicsContext.fillRect(0,hi, 10, hi+10);
				if (ColorChanger == 1) {
					graphicsContext.setColor(Color.RED);
					for (int i = 0; i < 256; ++i) {
						graphicsContext.drawLine(i, hi - 1, i, hi - 1 - scaledHRed[i]);

					}
					this.repaint();
				}
				if (ColorChanger == 2) {
					graphicsContext.setColor(Color.GREEN);
					for (int i = 0; i < 256; ++i) {
						graphicsContext.drawLine(i, hi - 1, i, hi - 1 - scaledHGreen[i]);
					}
					this.repaint();
				}
				if (ColorChanger == 3) {
					graphicsContext.setColor(Color.BLUE);
					for (int i = 0; i < 256; ++i) {
						graphicsContext.drawLine(i, hi - 1, i, hi - 1 - scaledHBlue[i]);
					}
					this.repaint();
				}

				this.repaint();

			}

		}

		public class HistogramControls extends JPanel {

			// Variable will change based on button color click switch
			private double HistoMean;
			private double SDev;
			private int Minn;
			private int Max;
			private int Mode;

			// private int ColorMode;

			private JButton RGB;
			private JLabel ColorIndicator;

			private JLabel MeanLabel;
			private JLabel StandDevLabel;
			private JLabel MinLabel;
			private JLabel MaxLabel;
			JLabel ModeLabel;

			public HistogramControls() {

				AddActionHandlers();

				ColorIndicator = new JLabel("Active Color");
				MeanLabel = new JLabel("Mean:" + HistoMean);
				StandDevLabel = new JLabel("StandDev:" + SDev);
				MinLabel = new JLabel("Minimum:" + Minn);
				MaxLabel = new JLabel("Maximum:" + Max);
				ModeLabel = new JLabel("Mode:" + Mode);

				this.add(RGB, BorderLayout.WEST);
				this.add(ColorIndicator, BorderLayout.WEST);
				this.add(MeanLabel, BorderLayout.WEST);
				this.add(StandDevLabel, BorderLayout.WEST);
				this.add(MinLabel, BorderLayout.WEST);
				this.add(MaxLabel, BorderLayout.WEST);
				this.add(ModeLabel, BorderLayout.WEST);

				setVisible(true);
			}

			private void AddActionHandlers() {
				RGB = new JButton("RGB");
				RGB.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						ColorChanger++;
						if (ColorChanger == 1) {
							HistoMean = Histogram.GetMeanRed();
							SDev = (float) Histogram.GetSDevRed();
							Minn = Histogram.GetMinValueRed();
							Max = Histogram.GetMaxValueRed();
							Mode = Histogram.GetModeRed();

							ColorIndicator.setText("Red");
							MeanLabel.setText("Mean:" + HistoMean);
							StandDevLabel.setText("StandDev:" + SDev);
							MinLabel.setText("Min:" + Minn);
							MaxLabel.setText("Max:" + Max);
							ModeLabel.setText("Mode:" + Mode);

						}
						if (ColorChanger == 2) {
							HistoMean = Histogram.GetMeanGreen();
							SDev = (float) Histogram.GetSDevGreen();
							Minn = Histogram.GetMinValueGreen();
							Max = Histogram.GetMaxValueGreen();
							Mode = Histogram.GetModeGreen();

							ColorIndicator.setText("Green");
							MeanLabel.setText("Mean:" + HistoMean);
							StandDevLabel.setText("StandDev:" + SDev);
							MinLabel.setText("Min:" + Minn);
							MaxLabel.setText("Max:" + Max);
							ModeLabel.setText("Mode:" + Mode);

						}

						if (ColorChanger == 3) {
							HistoMean = Histogram.GetMeanBlue();
							SDev = (float) Histogram.GetSDevBlue();
							Minn = Histogram.GetMinValueBlue();
							Max = Histogram.GetMaxValueBlue();
							Mode = Histogram.GetModeBlue();

							ColorIndicator.setText("Blue");
							MeanLabel.setText("Mean:" + HistoMean);
							StandDevLabel.setText("StandDev:" + SDev);
							MinLabel.setText("Min:" + Minn);
							MaxLabel.setText("Max:" + Max);
							ModeLabel.setText("Mode:" + Mode);

						}

						if (ColorChanger == 4) {
							ColorChanger = 0;
						}

					}
				}

				);

			}

			// Controls size of ControlPanel
			public Dimension getPreferredSize() {
				return new Dimension(150, 400);
			}

		}

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			new ImageGUISeventh();

		}
	}


