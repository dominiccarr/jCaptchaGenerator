import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JComponent;

public class Generator extends JComponent {

	private static final long serialVersionUID = 1L;
	private Random random = new Random();
	private BufferedImage buffered = new BufferedImage(190, 95,
			BufferedImage.TYPE_INT_BGR);
	private Graphics2D g2;
	private Graphics2D g3;
	private String code = "";
	private Color[] colours = { Color.red, Color.blue, Color.cyan,
			Color.DARK_GRAY, Color.MAGENTA, Color.ORANGE };
	private String[] fonts = { "courier", "times", "serif", "arial",
			"american typewriter" };

	public Generator() {
		g3 = buffered.createGraphics();

		g3.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g3.setColor(Color.white);
		g3.fillRect(0, 0, 195, 95);

		g3.drawArc(22, 34, 200, 200, 100, 12);

		g3.setColor(Color.red);
		int starX = 20;
		int starY = 60;
		g3.setColor(Color.yellow);
		drawCircles(random.nextInt(3));
		drawGrid();
		for (int a = 0; a < 5; a++) {

			String f = fonts[random.nextInt(5)];
			Font font = new Font(f, Font.PLAIN, 43);
			g3.setFont(font);

			String toAppend = generateRandom();
			code += toAppend;
			g3.setColor(Color.BLACK);
			g3.setStroke(new BasicStroke(3));
			int offset = random.nextInt(20);
	        AffineTransform orig = g3.getTransform();

	        double angle = Math.toRadians(random.nextDouble()*10.0);
	        g3.rotate(-angle, -10, 80);
			g3.drawString(toAppend, starX + 4, starY + offset + 2);
			font = new Font(f, Font.PLAIN, 40);
			g3.setFont(font);
			g3.setColor(colours[random.nextInt(6)]);
			g3.setStroke(new BasicStroke(2));
			g3.drawString(toAppend, starX, starY + offset);
	        g3.setTransform(orig);
			starX += 30;
		}
		g3.setStroke(new BasicStroke(3));
		int prevx = random.nextInt(190);
		int prevy = random.nextInt(95);
		for (int a = 0; a < 6; a++) {
			g3.setColor(colours[random.nextInt(6)]);
			int endx = random.nextInt(190);
			int endy = random.nextInt(95);
			g3.drawLine(prevx, prevy, endx, endy);
			prevx = endx;
			prevy = endy;

		}

		g3.dispose();
	}

	public void paint(Graphics g) {
		g2 = (Graphics2D) g;
		g2.drawImage(buffered, 0, 0, null);
	}

	private void drawGrid() {
		int upper = 0;
		for (int a = 0; a < 10; a++) {
			g3.setColor(Color.BLACK);
			g3.setStroke(new BasicStroke(2));

			g3.drawLine(0, 0 - upper, 190, 95 - upper);
			g3.drawLine(0, upper, 190, 95 + upper);

			g3.drawLine(195, 0 - upper, 0, 95 - upper);
			g3.drawLine(195, 0 + upper, 0, 95 + upper);

			g3.setColor(colours[random.nextInt(4)]);
			g3.setStroke(new BasicStroke(2));

			g3.drawLine(0, 0 - upper, 190, 95 - upper);
			g3.drawLine(0, upper, 190, 95 + upper);

			g3.drawLine(195, 0 - upper, 0, 95 - upper);
			g3.drawLine(195, 0 + upper, 0, 95 + upper);

			upper += 5+ random.nextInt(15);
		}
	}

	private void drawGrid2() {
		int upper = 0;
		for (int a = 0; a < 20; a++) {
			g3.setColor(Color.BLACK);
			g3.setStroke(new BasicStroke(3));
			g3.drawLine(upper, 0, upper, 200);
			g3.drawLine(0, upper, 200, upper);

			g3.setColor(colours[random.nextInt(4)]);
			g3.setStroke(new BasicStroke(2));
			g3.drawLine(upper, 0, upper, 200);
			g3.drawLine(0, upper, 200, upper);

			upper += 10;
		}
	}

	private void drawCircles(int s) {
		int upper = 0;
		int x = 0;
		int y = 0;
		for (int a = 0; a < 10; a++) {

			g3.setColor(colours[random.nextInt(4)]);
			g3.setStroke(new BasicStroke(2));
			if (s == 0) {
				g3.drawOval(70 - x, 30 - y, 40 + upper, 40 + upper);
			} else if (s == 1) {
				g3.drawRect(70 - x, 30 - y, 40 + upper, 40 + upper);
			} else if (s == 2) {
				Polygon po = new Polygon(); //
				po.addPoint(45, 45);
				po.addPoint(90, 90);
				po.addPoint(0, 90);
				g3.drawPolygon(po);
			}
			upper += random.nextInt(10);
			x += random.nextInt(10);
			y += random.nextInt(10);
		}
	}

	public BufferedImage getImage() {
		return buffered;
	}

	public String getCode() {
		return code;
	}

	private String generateRandom() {
		int ascii = 65 + random.nextInt(90-65);		
		return Character.toString((char) ascii);
	}
}