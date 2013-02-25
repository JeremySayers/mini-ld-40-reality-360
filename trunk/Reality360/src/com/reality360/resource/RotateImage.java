package com.reality360.resource;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class RotateImage {
	public static BufferedImage rotate(Graphics g, int x, int y, Image image, int rot){
		BufferedImage bf = (BufferedImage)image;
		double rotationRequired = Math.toRadians(rot);
		double locationX = bf.getWidth(null) / 2;
		double locationY = bf.getHeight(null) / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		BufferedImage rotImg = op.filter(bf, null);
		((Graphics2D)g).drawImage(rotImg, x, y, null);
		return rotImg;
	}
	public static int calculateRotation(int x, int y, int w, int h, int targetX, int targetY){
		int centerX = x+w/2;
		int centerY = y+w/2;
		if (targetX-centerX > 0 && targetY-centerY >0){
			return (int) (Math.toDegrees(Math.atan(1.0*(targetY-centerY)/(targetX-centerX))));
			
		} else if(targetX-centerX < 0 && targetY-centerY >0){
			return (int) ((180.0)-Math.toDegrees(Math.atan(1.0*(targetY-centerY)/(centerX-targetX))));
			
		} else if(targetX-centerX < 0 && targetY-centerY < 0){
			return (int) ((180.0)+Math.toDegrees(Math.atan(1.0*(centerY-targetY)/(centerX-targetX))));
		}
		else if(targetX-centerX > 0 && targetY-centerY < 0){
			return (int) -(Math.toDegrees(Math.atan(1.0*(centerY-targetY)/(targetX-centerX))));
		} else {
			return 0;
		}
	}
}
