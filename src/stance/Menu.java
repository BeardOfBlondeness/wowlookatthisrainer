package stance;

import org.newdawn.slick.opengl.Texture;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import builders.Sprite;
import javax.sound.sampled.*;

public class Menu {
	
	private Texture background;
	private Sprite bg;
	private Texture tex;
	public Menu(Texture tex) {
		this.background = tex;
		this.setTex(tex);
		bg = new Sprite(16, 9, 0, 0, 0, 900, 540);
		
		try {
		    File yourFile = new File("res/menu/rain.wav");
		    AudioInputStream stream;
		    AudioFormat format;
		    DataLine.Info info;
		    Clip clip;

		    stream = AudioSystem.getAudioInputStream(yourFile);
		    format = stream.getFormat();
		    info = new DataLine.Info(Clip.class, format);
		    clip = (Clip) AudioSystem.getLine(info);
		    clip.open(stream);
		    clip.start();
		    clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		catch (Exception e) {
		    //whatevers
		}
	}
	
	public void makeTextures() {
	
		
	}
	
	public void draw() {
		bg.Draw(background);
	}

	public Texture getTex() {
		return tex;
	}

	public void setTex(Texture tex) {
		this.tex = tex;
		this.background = tex;
	}

}
