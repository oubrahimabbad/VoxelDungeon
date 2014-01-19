package game.resource;

import java.io.IOException;
import org.newdawn.slick.opengl.Texture;

public class ResourceLibrary {
    
    private static Texture dirt = null;
    private static Texture stone = null;
    private static Texture Grass_side = null;
    private static Texture grass_top = null;
    
    public static Texture getDirt() { return dirt; }
    public static Texture getStone() { return stone; }
    public static Texture getGrass_side() { return Grass_side; }
    public static Texture getGrass_top() { return grass_top; }
    
    public static void init() throws IOException {
        dirt = Loader.createTexture("dirt");
        stone = Loader.createTexture("stone");
        Grass_side = Loader.createTexture("Grass_side");
        grass_top = Loader.createTexture("grass_top");
    }

    public static Texture getTextureByName(String name) throws IOException {
        switch (name) {
            case "dirt":
                return dirt;
            case "stone":
                return stone;
            case "Grass_side":
                return Grass_side;
            case "grass_top":
                return grass_top;
            case "blank":
                return null;
            default:
                throw new IOException("Unhandled case @ getTextureByName().");
        }
    }
}