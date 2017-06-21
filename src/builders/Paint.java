package builders;


import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;
import java.io.InputStream;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Paint {

	public static void DrawQuadTex(Texture tex, float x, float y, float z, float width, float height) {
		glClear(GL_COLOR_BUFFER_BIT);
		glPushMatrix();
		tex.bind();
		glTranslatef(x, y, z);		
		glColor3f(1,1,1);
		glBegin(GL_QUADS);
		{
			glTexCoord2f(0, 0);
			glVertex2f(0, 0);
			glTexCoord2f(0, tex.getHeight());
			glVertex2f(0, height);
			glTexCoord2f(tex.getWidth(), tex.getHeight());
			glVertex2f(width,height);
			glTexCoord2f(tex.getWidth(), 0);
			glVertex2f(width,0);
		}
		glEnd();

		glPopMatrix();
		glDeleteTextures(1);
	}

	public static Texture QuickLoad(String name) {
		Texture tex = null;
		tex = LoadTexture(name, "JPEG");
		return tex;	
	}

	public static Texture LoadTexture(String path, String fileType) {
		Texture tex = null;
		InputStream in = ResourceLoader.getResourceAsStream(path);
		try {
			tex = TextureLoader.getTexture(fileType, in); 
		} catch (IOException e) {

			e.printStackTrace();
		}
		return tex;
	}

}
