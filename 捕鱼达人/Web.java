package com.Tfishe.day01;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class Web {
	private int x;//×ø±ê
	private int y;
	private Image image;//Í¼Æ¬
    public void setX(int x)
    {
    	this.x = x;
    }
    public int getX()
    {
    	return this.x;
    }
    public void setY(int y)
    {
    	this.y = y;
    }
    public int getY()
    {
    	return this.y;
    }
    public void setImage(Image image)
    {
    	this.image = image;
    }
    public Image getImage()
    {
    	return this.image;
    }
    public void drawWeb(Graphics g,JPanel jp)
    {
    	g.drawImage(this.image, this.x,this.y,120,120, jp);
    }
}
