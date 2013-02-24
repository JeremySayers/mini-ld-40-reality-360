package com.redsoxfan.libs.pixtact;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * Pixtact (PIXel conTACT) - Pixel-level collision detection library using an Image. 
 * @author RedSoxFan
 * @since 23 Feb 13
 * @version 2.0 (Mini Ludum Dare #40 Edition)
 */
public class Pixtact {
	// Versioning Info
	public static final int VERSION_MAJOR = 1;
	public static final int VERSION_MINOR = 0;
	public static final String VERSION = String.format("%s.%s", VERSION_MAJOR, VERSION_MINOR);
	// Variables
	private BufferedImage image;
	private ArrayList<Point> nonAlpha = new ArrayList<Point>();
	private ArrayList<Point> absolute = new ArrayList<Point>();
	private Point point = new Point(0, 0);
	private static boolean DEBUG = false;
	private static Color DEBUG_COLOR = Color.RED;
	/**
	 * A Pixel-level collision detection library.
	 * @param img - Image (with an alpha channel) to use for collision detection
	 */
	public Pixtact(BufferedImage img) {
		image=img;
		for (int y=0; y<getHeight(); y++)
			for (int x=0; x<getWidth(); x++)
				if (getPixelColor(x, y).getAlpha()>0)
					nonAlpha.add(new Point(x, y));
		absolute.addAll(nonAlpha);
	}
	public Pixtact(BufferedImage img, int width, int height) {
		image = img;
		resize(width, height);
	}
	public void resize(int width, int height) {
		image=createFromImage(image.getScaledInstance(width, height, BufferedImage.SCALE_REPLICATE), width, height).getImage();
		for (int y=0; y<getHeight(); y++)
			for (int x=0; x<getWidth(); x++)
				if (getPixelColor(x, y).getAlpha()>0)
					nonAlpha.add(new Point(x, y));
		absolute.addAll(nonAlpha);
	}
	/**
	 * Create a Pixtact instance from a java.awt.Image instance. If you are using
	 * a java.awt.BufferedImage, use the Constructor instead of this method.
	 * @param read - Image to read
	 * @return Pixtact instance created from Image read
	 */
	public static Pixtact createFromImage(Image read) {
		BufferedImage buff = new BufferedImage(read.getWidth(null), read.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		buff.getGraphics().drawImage(read, 0, 0, null);
		return new Pixtact(buff);
	}
	public static Pixtact createFromImage(Image read, int width, int height) {
		BufferedImage buff = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		buff.getGraphics().drawImage(read, 0, 0, null);
		return new Pixtact(buff);
	}
	/**
	 * Create a Pixtact instance from a java.io.File instance.
	 * @param file - File to read
	 * @return Pixtact instance create from File file or null (if read failed)
	 */
	public static Pixtact read(File file) {
		try {
			return new Pixtact(ImageIO.read(file));
		} catch (Exception e){}
		return null;
	}
	/**
	 * Create a Pixtact instance from a java.io.InputStream instance.
	 * @param in - InputStream to read
	 * @return Pixtact instance create from InputStream in or null (if read failed)
	 */
	public static Pixtact read(InputStream in) {
		try {
			return new Pixtact(ImageIO.read(in));
		} catch (Exception e){}
		return null;
	}
	/**
	 * Create a Pixtact instance from a java.net.URL instance.
	 * @param url - URL to read
	 * @return Pixtact instance create from URL url or null (if read failed)
	 */
	public static Pixtact read(URL url) {
		try {
			return new Pixtact(ImageIO.read(url));
		} catch (Exception e){}
		return null;
	}
	private void calculateAbsoluteNonAlphaPoints() {
		absolute.clear();
		for (Point p:nonAlpha)
			absolute.add(new Point(p.x+point.x, p.y+point.y));
	}
	/**
	 * Draw Bounding Box on Graphics g using Color c
	 * @param g - Graphics to draw on
	 * @param c - Color to draw bounding box with
	 */
	public void drawBoundingBox(Graphics g, Color c) {
		Color oc = g.getColor();
		g.setColor(c);
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(oc);
	}
	/**
	 * Draw the Image on Graphics g using the instance's X, Y, Width and Height
	 * @param g - Graphics to draw on
	 */
	public void drawImage(Graphics g) {
		drawImage(g, getX(), getY(), getWidth(), getHeight());
		if (DEBUG) {
			drawBoundingBox(g, DEBUG_COLOR);
		}
	}
	/**
	 * Draw the Image on Graphics g at Coordinate (x, y) using the instance's Width and Height
	 * @param g - Graphics to draw on
	 * @param x - X Location
	 * @param y - Y Location
	 */
	public void drawImage(Graphics g, int x, int y) {
		drawImage(g, x, y, getWidth(), getHeight());
	}
	/**
	 * Draw the Image on Graphics g at Coordinate (x, y) with size w x h
	 * @param g - Graphics to draw on
	 * @param x - X Location
	 * @param y - Y Location
	 * @param w - Width
	 * @param h - Height
	 */
	public void drawImage(Graphics g, int x, int y, int w, int h) {
		g.drawImage(getImage(), x, y, w, h, null);
	}
	/**
	 * Draw the Image on Graphics g at Point p using the instance's Width and Height
	 * @param g - Graphics to draw on
	 * @param p - Point to draw at
	 */
	public void drawImage(Graphics g, Point p) {
		drawImage(g, p.x, p.y);
	}
	/**
	 * Draw the Image on Graphics g using the Rectangle's X, Y, Width and Height
	 * @param g - Graphics to draw on
	 * @param r - Rectangle to use for location and size
	 */
	public void drawImage(Graphics g, Rectangle r) {
		drawImage(g, r.x, r.y, r.width, r.height);
	}
	/**
	 * Gets the absolute location of collision over all given Pixtact instances
	 * @param others - Other Pixtact Instances
	 * @return ArrayList of Point instances that holds the collision points
	 */
	public ArrayList<Point> getAbsoluteCollisionPoints(Pixtact... others) {
		ArrayList<Point> absolute = getAbsoluteNonAlphaPoints();
		for (Pixtact other:others) {
			absolute.retainAll(other.getAbsoluteNonAlphaPoints());
			absolute.trimToSize();
		}
		return absolute;
	}
	/**
	 * Gets the absolute location of all pixels that are not fully transparent
	 * @return ArrayList of Point instances that holds absolute pixels locations
	 */
	public ArrayList<Point> getAbsoluteNonAlphaPoints() {
		return absolute;
	}
	/**
	 * Gets the rectangular bounding box
	 * @return Rectangle instance that holds the bounding box
	 */
	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}
	/**
	 * Gets the height of the Image
	 * @return Height of the Image
	 */
	public int getHeight() {
		return image.getHeight();
	}
	/**
	 * Gets the Image associated with this instance
	 * @return The BufferedImage associated with this instance
	 */
	public BufferedImage getImage() {
		return image;
	}
	public static boolean isDrawingBounds() {
		return DEBUG;
	}
	public static void turnOnDrawBounding(Color c) {
		DEBUG = true;
		DEBUG_COLOR = c;
	}
	public static void turnOffDrawBounding() {
		DEBUG = false;
	}
	/**
	 * Absolute location of this instance
	 * @return Location of instance
	 */
	public Point getLocation() {
		return point;
	}
	/**
	 * Get the integer value at Coordinate (x, y) of the Image
	 * @param x - X Location
	 * @param y - Y Location
	 * @return ARGB integer color of pixel
	 */
	public int getPixel(int x, int y) {
		return getImage().getRGB(x, y);
	}
	/**
	 * Get the integer value at Point p of the Image
	 * @param p - Pixel Location
	 * @return ARGB integer color of pixel
	 */
	public int getPixel(Point p) {
		return getImage().getRGB(p.x, p.y);
	}
	/**
	 * Get the Color at Coordinate (x, y) of the Image
	 * @param x - X Location
	 * @param y - Y Location
	 * @return Color of pixel
	 */
	public Color getPixelColor(int x, int y) {
		return new Color(getPixel(x, y), true);
	}
	/**
	 * Get the Color at Point p of the Image
	 * @param p - Pixel Location
	 * @return Color of pixel
	 */
	public Color getPixelColor(Point p) {
		return new Color(getPixel(p), true);
	}
	/**
	 * Gets the width of the Image
	 * @return Width of Image
	 */
	public int getWidth() {
		return image.getWidth();
	}
	/**
	 * Gets the X Location
	 * @return X Location
	 */
	public int getX() {
		return point.x;
	}
	/**
	 * Gets the Y Location
	 * @return Y Location
	 */
	public int getY() {
		return point.y;
	}
	/**
	 * Move the instance to Coordinate (x, y) then test to see if
	 * the Pixtact instances are colliding
	 * @param x - X Location
	 * @param y - Y Location
	 * @param others - Other Pixtact Instances
	 * @return Whether of not the instances are colliding
	 */
	public boolean isColliding(int x, int y, Pixtact... others) {
		move(x, y);
		return isColliding(others);
	}
	/**
	 * Test to see if the Pixtact instances are colliding
	 * @param others - Other Pixtact Instances
	 * @return Whether of not the instances are colliding
	 */
	public boolean isColliding(Pixtact... others) {
		return !getAbsoluteCollisionPoints(others).isEmpty();
	}
	/**
	 * Tests to see if Point p is colliding with the Pixtact instance (useful for mouse)
	 * @param p - Point to check at
	 * @return Whether of not the instances are colliding
	 */
	public boolean isColliding(Point p) {
		return getAbsoluteNonAlphaPoints().contains(p);
	}
	/**
	 * Move the instance to Point newPoint then test to see if
	 * the Pixtact instances are colliding
	 * @param newPoint - Point to move to
	 * @param others - Other Pixtact Instances
	 * @return Whether of not the instances are colliding
	 */
	public boolean isColliding(Point newPoint, Pixtact... others) {
		move(newPoint);
		return isColliding(others);
	}
	/**
	 * Move instance to Coordinate (x, y)
	 * @param x - X Location to move to
	 * @param y - Y Location to move to
	 */
	public void move(int x, int y) {
		point.x = x;
		point.y = y;
		calculateAbsoluteNonAlphaPoints();
	}
	/**
	 * Move instance to Point p
	 * @param newPoint - Point to move to
	 */
	public void move(Point newPoint) {
		move(newPoint.x, newPoint.y);
	}
	/**
	 * Move instance to Coordinate (x, y)
	 * @param x - X Location to move to
	 * @param y - Y Location to move to
	 */
	public void setLocation(int x, int y) {
		move(x, y);
	}
	/**
	 * Move instance to Point p
	 * @param newPoint - Point to move to
	 */
	public void setLocation(Point newPoint) {
		move(newPoint);
	}
	/**
	 * Set X Location
	 * @param x - X Location to move to
	 */
	public void setX(int x) {
		move(x, getY());
	}
	/**
	 * Set Y Location
	 * @param y - Y Location to move to
	 */
	public void setY(int y) {
		move(getX(), y);
	}
}
