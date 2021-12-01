package yanshuo;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.Date;
/*
 * ClockApplet.java
 *
 * Created on 2007年6月14日, 上午8:54
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author hyl
 */
public class clock extends Applet
    implements Runnable
{

    static final Object colors[][];
    static final Object cityZone[][] = {
        {
            "夏威夷", "-10"
        }, {
            "阿拉斯加", "-9"
        },  {
            "墨西哥", "-6"
        },  {
            "格林威治", "0"
        }, {
            "巴黎", "1"
        },  {
            "莫斯科", "3"
        },  {
            "香港", "8"
        }, {
            "东京", "9"
        }, {
            "悉尼", "10"
        }
    };
    Thread threadClock;
    boolean isAnalog;
    Font fontText;
    Color fontColor;
    Color backColor;
    Color hHandColor;
    Color mHandColor;
    Color sHandColor;
    Color hPointColor;
    Color mPointColor;
    int xPoint[];
    int yPoint[];
    Image backImage;
    MediaTracker tracker;
    Image imageBuffer;
    int fromGMT;
    int currentZone;
    int oldHour;
    int oldMinute;
    int oldSecond;

    public void setCityZone(String s)
    {
        currentZone = fromGMT;
        for(int i = 0; i < cityZone.length; i++)
            if(s.indexOf((String)cityZone[i][0]) != -1)
                currentZone = Integer.parseInt((String)cityZone[i][1]);

    }

    private Color findColor(String s, Color color)
    {
        if(s != null)
        {
            s = s.toUpperCase();
            if(s.charAt(0) == '#')
                return new Color(Integer.parseInt(s.substring(1), 16));
            for(int i = 0; i < colors.length; i++)
                if(s.compareTo((String)colors[i][0]) == 0)
                    return (Color)colors[i][1];

        }
        return color;
    }

    public void init()
    {
        String s = getParameter("typeface");
        if(s == null)
            s = "Helvetica";
        int i;
        try
        {
            i = Integer.parseInt(getParameter("fontsize"), 10);
        }
        catch(NumberFormatException _ex)
        {
            i = 16;
        }
        fontText = new Font(s, 0, i);
        backColor = findColor(getParameter("backcolor"), backColor);
        fontColor = findColor(getParameter("fontcolor"), fontColor);
        hHandColor = findColor(getParameter("hhandcolor"), hHandColor);
        mHandColor = findColor(getParameter("mhandcolor"), mHandColor);
        sHandColor = findColor(getParameter("shandcolor"), sHandColor);
        hPointColor = findColor(getParameter("hpointcolor"), hPointColor);
        mPointColor = findColor(getParameter("mpointcolor"), mPointColor);
        tracker = new MediaTracker(this);
        s = getParameter("backimage");
        if(s != null)
        {
            backImage = getImage(getCodeBase(), s);
            tracker.addImage(backImage, 0);
        } else
        {
            backImage = null;
        }
        s = getParameter("analog");
        if(s != null && s.indexOf("false") > -1)
            isAnalog = false;
        else
            isAnalog = true;
        Date date = new Date();
        fromGMT = -Math.round((float)date.getTimezoneOffset() / 60F);
        currentZone = fromGMT;
    }

    public void start()
    {
        if(imageBuffer == null)
        {
            Dimension dimension = size();
            imageBuffer = createImage(dimension.width, dimension.height);
        }
        if(threadClock == null)
        {
            threadClock = new Thread(this);
            threadClock.start();
        }
    }

    public void stop()
    {
        if(threadClock != null)
        {
            threadClock.stop();
            threadClock = null;
            imageBuffer = null;
        }
    }

    public void run()
    {
        try
        {
            tracker.waitForAll();
        }
        catch(InterruptedException _ex)
        {
            return;
        }
        while(Thread.currentThread() == threadClock) 
        {
            repaint();
            try
            {
                Thread.sleep(50L);
            }
            catch(InterruptedException _ex)
            {
                return;
            }
        }
    }

    public void update(Graphics g)
    {
        Date date = new Date();
        int i = (date.getHours() + currentZone) - fromGMT;
        if(i >= 24)
            i -= 24;
        if(i < 0)
            i += 24;
        int j = date.getMinutes();
        int k = date.getSeconds();
        if(i != oldHour || j != oldMinute || k != oldSecond)
        {
            Graphics g1 = imageBuffer.getGraphics();
            DrawBackground(g1);
            Dimension dimension = size();
            int l = dimension.width >> 1;
            int i1 = dimension.height >> 1;
            if(isAnalog)
            {
                double d = (double)Math.min(l, i1) * 0.75D;
                double d1 = (double)Math.min(l, i1) * 0.040000000000000001D;
                double d2 = 3.1415926535897931D * ((double)j / 30D + (double)k / 1800D);
                xPoint[0] = (int)Math.round((double)l - 2D * d1 * Math.sin(d2)) - 1;
                xPoint[1] = (int)Math.round((double)l - d1 * Math.cos(d2));
                xPoint[2] = (int)Math.round((double)l + d * Math.sin(d2)) + 1;
                xPoint[3] = (int)Math.round((double)l + d1 * Math.cos(d2));
                yPoint[0] = (int)Math.round((double)i1 + 2D * d1 * Math.cos(d2)) - 1;
                yPoint[1] = (int)Math.round((double)i1 - d1 * Math.sin(d2));
                yPoint[2] = (int)Math.round((double)i1 - d * Math.cos(d2)) + 1;
                yPoint[3] = (int)Math.round((double)i1 + d1 * Math.sin(d2));
                g1.setColor(mHandColor);
                g1.fillPolygon(xPoint, yPoint, 4);
                if(j < 30)
                    g1.setColor(Color.white);
                else
                    g1.setColor(Color.black);
                g1.drawLine(xPoint[0], yPoint[0], xPoint[1], yPoint[1]);
                g1.drawLine(xPoint[1], yPoint[1], xPoint[2], yPoint[2]);
                if(j < 30)
                    g1.setColor(Color.black);
                else
                    g1.setColor(Color.white);
                g1.drawLine(xPoint[2], yPoint[2], xPoint[3], yPoint[3]);
                g1.drawLine(xPoint[3], yPoint[3], xPoint[0], yPoint[0]);
                double d3 = 3.1415926535897931D * ((double)i / 6D + (double)j / 360D);
                d = (double)Math.min(l, i1) * 0.5D;
                d1 = (double)Math.min(l, i1) * 0.050000000000000003D;
                xPoint[0] = (int)Math.round((double)l - 2D * d1 * Math.sin(d3)) - 1;
                xPoint[1] = (int)Math.round((double)l - d1 * Math.cos(d3));
                xPoint[2] = (int)Math.round((double)l + d * Math.sin(d3)) + 1;
                xPoint[3] = (int)Math.round((double)l + d1 * Math.cos(d3));
                yPoint[0] = (int)Math.round((double)i1 + 2D * d1 * Math.cos(d3)) - 1;
                yPoint[1] = (int)Math.round((double)i1 - d1 * Math.sin(d3));
                yPoint[2] = (int)Math.round((double)i1 - d * Math.cos(d3)) + 1;
                yPoint[3] = (int)Math.round((double)i1 + d1 * Math.sin(d3));
                g1.setColor(hHandColor);
                g1.fillPolygon(xPoint, yPoint, 4);
                if(i >= 0 && i <= 6 || i >= 12 && i <= 18)
                    g1.setColor(Color.white);
                else
                    g1.setColor(Color.black);
                g1.drawLine(xPoint[0], yPoint[0], xPoint[1], yPoint[1]);
                g1.drawLine(xPoint[1], yPoint[1], xPoint[2], yPoint[2]);
                if(i >= 0 && i <= 6 || i >= 12 && i <= 18)
                    g1.setColor(Color.black);
                else
                    g1.setColor(Color.white);
                g1.drawLine(xPoint[2], yPoint[2], xPoint[3], yPoint[3]);
                g1.drawLine(xPoint[3], yPoint[3], xPoint[0], yPoint[0]);
                d = (double)Math.min(l, i1) * 0.75D;
                g1.setColor(sHandColor);
                double d4 = (3.1415926535897931D * (double)k) / 30D;
                g1.drawLine(l, i1, (int)Math.round((double)l + d * Math.sin(d4)), (int)Math.round((double)i1 - d * Math.cos(d4)));
            } else
            {
                g1.setFont(fontText);
                g1.setColor(fontColor);
                String s = (i >= 10 ? Integer.toString(i) : "0" + i) + ":" + (j >= 10 ? Integer.toString(j) : "0" + j) + ":" + (k >= 10 ? Integer.toString(k) : "0" + k);
                FontMetrics fontmetrics = g1.getFontMetrics();
                int j1 = dimension.height >> 1;
                if(j1 < 0)
                    j1 = 0;
                int k1 = dimension.width - fontmetrics.stringWidth(s) >> 1;
                if(k1 < 0)
                    k1 = 0;
                g1.drawString(s, k1, j1);
            }
            oldHour = i;
            oldMinute = j;
            oldSecond = k;
        }
        g.drawImage(imageBuffer, 0, 0, null);
    }

    public void paint(Graphics g)
    {
        Dimension dimension = size();
        if(tracker.isErrorAny())
        {
            g.setColor(Color.white);
            g.fillRect(0, 0, dimension.width, dimension.height);
            return;
        }
        if(tracker.checkAll(true))
            DrawBackground(g);
    }

    public boolean mouseDown(Event event, int i, int j)
    {
        isAnalog = !isAnalog;
        return true;
    }

    private void DrawBackground(Graphics g)
    {
        Dimension dimension = size();
        g.setColor(backColor);
        g.fillRect(0, 0, dimension.width, dimension.height);
        if(backImage != null)
        {
            int i = backImage.getWidth(this);
            int k = backImage.getHeight(this);
            if(i < 0 || k < 0)
                return;
            g.drawImage(backImage, dimension.width - i >> 1, dimension.height - k >> 1, null);
        }
        if(isAnalog)
        {
            int j = dimension.width >> 1;
            int l = dimension.height >> 1;
            double d = (double)Math.min(j, l) * 0.90000000000000002D;
            for(int i1 = 1; i1 <= 12; i1++)
            {
                double d1 = 3.1415926535897931D * (0.5D - (double)i1 / 6D);
                int k1 = (int)Math.floor((double)j + d * Math.cos(d1));
                int l1 = (int)Math.floor((double)l - d * Math.sin(d1));
                g.setColor(hPointColor);
                g.fill3DRect(k1 - 2, l1 - 2, 4, 4, true);
            }

            for(int j1 = 1; j1 <= 60; j1++)
                if(j1 % 5 != 0)
                {
                    double d2 = (3.1415926535897931D * (double)j1) / 30D;
                    int i2 = (int)Math.floor((double)j + d * Math.cos(d2));
                    int j2 = (int)Math.floor((double)l - d * Math.sin(d2));
                    g.setColor(mPointColor);
                    g.fill3DRect(i2 - 2, j2 - 2, 3, 3, false);
                }

        }
    }

    public clock()
    {
        isAnalog = true;
        fontColor = Color.black;
        backColor = Color.lightGray;
        hHandColor = Color.blue;
        mHandColor = Color.blue;
        sHandColor = Color.black;
        hPointColor = Color.red;
        mPointColor = Color.lightGray;
        xPoint = new int[4];
        yPoint = new int[4];
        oldHour = -1;
        oldMinute = -1;
        oldSecond = -1;
    }

    static 
    {
        colors = (new Object[][] {
            new Object[] {
                "BLACK", Color.black
            }, new Object[] {
                "BLUE", Color.blue
            }, new Object[] {
                "CYAN", Color.cyan
            }, new Object[] {
                "DARKGRAY", Color.darkGray
            }, new Object[] {
                "GRAY", Color.gray
            }, new Object[] {
                "GREEN", Color.green
            }, new Object[] {
                "LIGHTGRAY", Color.lightGray
            }, new Object[] {
                "MAGENTA", Color.magenta
            }, new Object[] {
                "ORANGE", Color.orange
            }, new Object[] {
                "PINK", Color.pink
            }, new Object[] {
                "RED", Color.red
            }, new Object[] {
                "WHITE", Color.white
            }, new Object[] {
                "YELLOW", Color.yellow
            }
        });
    }
}

