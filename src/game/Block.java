package game;

import game.resource.ResourceLibrary;
import java.io.IOException;
import java.util.ArrayList;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class Block {
    
    public static final byte DIRT = 0;
    public static final byte STONE = 1;
    public static final byte BLANK = 2;
    
    public Texture texture;
    public boolean isTransparent;
    private Chunk chunk;
    
    public Block(byte blockName) throws IOException {
        boolean transparent;
        String textureName;
        switch (blockName) {
            case DIRT:
                transparent = false;
                textureName = "dirt";
                break;
            case STONE:
                transparent = false;
                textureName = "stone";
                break;
            case BLANK:
                transparent = true;
                textureName = "blank";
                break;
            default:
                throw new IOException("Unhandled case @ Block constructor.");
        }
        this.texture = ResourceLibrary.getTextureByName(textureName);
        this.isTransparent = transparent;
    } 
    
    public void setMyChunk(Chunk chunk) {
        this.chunk = chunk;
    }
    
    private void addFloats(float a, float b, float c, ArrayList<Float> vertices) {
        vertices.add(Float.valueOf(a));
        vertices.add(Float.valueOf(b));
        vertices.add(Float.valueOf(c));
    }
    
    public void render(int x, int y, int z, ArrayList<Float> vertices) {
        if (isTransparent)
            return;
        
        texture.bind();
        // Side.
        if (!chunk.isBlock(x,y+1,z)) {
            addFloats(-0.5f+x,-0.5f+z,0.5f+y,vertices);
            addFloats(0.5f+x,-0.5f+z,0.5f+y,vertices);
            addFloats(0.5f+x,0.5f+z,0.5f+y,vertices);
            addFloats(-0.5f+x,0.5f+z,0.5f+y,vertices);
        }
        
        // Opposite side to above.
        if (!chunk.isBlock(x,y-1,z)) {
            addFloats(-0.5f+x,-0.5f+z,-0.5f+y,vertices);
            addFloats(-0.5f+x,0.5f+z,-0.5f+y,vertices);
            addFloats(0.5f+x,0.5f+z,-0.5f+y,vertices);
            addFloats(0.5f+x,-0.5f+z,-0.5f+y,vertices);
        }
        
        // Top.
        if (!chunk.isBlock(x,y,z+1)) {
            addFloats(-0.5f+x,0.5f+z,-0.5f+y,vertices);
            addFloats(-0.5f+x,0.5f+z,0.5f+y,vertices);
            addFloats(0.5f+x,0.5f+z,0.5f+y,vertices);
            addFloats(0.5f+x,0.5f+z,-0.5f+y,vertices);
            GL11.glEnd();
        }
        
        // Bottom.
        if (!chunk.isBlock(x,y,z-1)) {
            addFloats(-0.5f+x,-0.5f+z,-0.5f+y,vertices);
            addFloats(0.5f+x,-0.5f+z,-0.5f+y,vertices);
            addFloats(0.5f+x,-0.5f+z,0.5f+y,vertices);
            addFloats(-0.5f+x,-0.5f+z,0.5f+y,vertices);
        }
        
        // Side.
        if (!chunk.isBlock(x+1,y,z)) {
            addFloats(0.5f+x,-0.5f+z,-0.5f+y,vertices);
            addFloats(0.5f+x,0.5f+z,-0.5f+y,vertices);
            addFloats(0.5f+x,0.5f+z,0.5f+y,vertices);
            addFloats(0.5f+x,-0.5f+z,0.5f+y,vertices);
            GL11.glEnd();
        }
        
        // Opposite side to above.
        if (!chunk.isBlock(x-1,y,z)) {
            addFloats(-0.5f+x,-0.5f+z,-0.5f+y,vertices);
            addFloats(-0.5f+x,-0.5f+z,0.5f+y,vertices);
            addFloats(-0.5f+x,0.5f+z,0.5f+y,vertices);
            addFloats(-0.5f+x,0.5f+z,-0.5f+y,vertices);
        }
    }
}
