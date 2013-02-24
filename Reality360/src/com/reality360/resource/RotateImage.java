package com.reality360.resource;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class RotateImage {
	public static void rotate(Graphics g, int x, int y, Image image, int rot){
		BufferedImage bf = (BufferedImage)image;
		double rotationRequired = Math.toRadians(rot);
		double locationX = bf.getWidth(null) / 2;
		double locationY = bf.getHeight(null) / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		
		((Graphics2D)g).drawImage(op.filter(bf, null), x, y, null);
	}
}
