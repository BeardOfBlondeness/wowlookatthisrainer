package gameStart;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glViewport;

import java.awt.Dimension;
import java.awt.Toolkit;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;


public class FrameLoop {
	private int FWIDTH = 900, FHEIGHT = 540;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int WIDTH = (int) screenSize.getWidth();
	private int HEIGHT = (int) screenSize.getHeight();
	private int tempWidth = 960;
	private int trueWidth = WIDTH;
	private int trueHeight = HEIGHT; 
//	private int x = 0;
//	private int y = 0;
	//private Texture background;
	private int mainStartPosx;
	private int mainStartPosy;
	//private boolean[][] gridPositions = new boolean[32][18];
	private GameStance gs;
	public FrameLoop() { 
	
		try {
			
			Display();
			gs = new GameStance("menu");
			Update();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Display() {
		if(WIDTH > (HEIGHT/9)*16) {
			trueWidth = (HEIGHT/9)*16;
		} else {
			trueHeight = (WIDTH/16)*9;
		}
		mainStartPosx = (WIDTH - trueWidth)/2;
		mainStartPosy = (HEIGHT - trueHeight)/2;
		Display.setTitle("Tarrare");
		try {
			Display.setDisplayMode(new DisplayMode(FWIDTH, FHEIGHT));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, FWIDTH, FHEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}

	public void Update() throws LWJGLException {
		
		while(!Display.isCloseRequested())
		{
			glClear(GL_COLOR_BUFFER_BIT);
			gs.CheckStance();
			if(Keyboard.isKeyDown(Keyboard.KEY_F2)== true) {
				glViewport(mainStartPosx, mainStartPosy, trueWidth, trueHeight);
				Display.setDisplayModeAndFullscreen(Display.getDesktopDisplayMode());
				setTempWidth(WIDTH);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)== true) {
				//example keypress 
			}

			Display.update();
			Display.sync(60);
		}
		KillAllGame();
	}
	
	public void KillAllGame() {
		Display.destroy();
	}
	public int getTempWidth() {
		return tempWidth;
	}
	public void setTempWidth(int tempWidth) {
		this.tempWidth = tempWidth;
	}
	
	
}
