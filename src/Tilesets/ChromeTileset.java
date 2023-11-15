package Tilesets;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import Level.TileType;
import Level.Tileset;

import java.util.ArrayList;

// This class represents a "common" tileset of standard tiles defined in the CommonTileset.png file
public class ChromeTileset extends Tileset {

    public ChromeTileset() {
        super(ImageLoader.load("ChromeTileset.png"), 16, 16, 3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        // floor
        Frame floorFrame = new FrameBuilder(getSubImage(0, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder floorTile = new MapTileBuilder(floorFrame);

        mapTiles.add(floorTile);

        // bottom wall
        Frame bottomWallFrame = new FrameBuilder(getSubImage(0, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder bottomWallTile = new MapTileBuilder(bottomWallFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(bottomWallTile);

        // top wall
        Frame topWallFrame = new FrameBuilder(getSubImage(3, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder topWallTile = new MapTileBuilder(topWallFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(topWallTile);


        // down sign
        Frame downSignFrame = new FrameBuilder(getSubImage(3, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder downSignTile = new MapTileBuilder(downSignFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(downSignTile);

        // down sign
        Frame upSignFrame = new FrameBuilder(getSubImage(3, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder upSignTile = new MapTileBuilder(upSignFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(upSignTile);

       // roof sign
        Frame roofFrame = new FrameBuilder(getSubImage(4, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder roofTile = new MapTileBuilder(roofFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(roofTile);

        // stair
        Frame stairFrame = new FrameBuilder(getSubImage(1, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder stairTile = new MapTileBuilder(stairFrame);

        mapTiles.add(stairTile);




        // brick
        Frame brickFrame = new FrameBuilder(getSubImage(1, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder brickTile = new MapTileBuilder(brickFrame);

        mapTiles.add(brickTile);

        // exterior wall 
        Frame exteriorWallFrame = new FrameBuilder(getSubImage(0, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder exteriorWallTile = new MapTileBuilder(exteriorWallFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(exteriorWallTile);

        // top window wall 
        Frame topWindowFrame = new FrameBuilder(getSubImage(0, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder topWindowTile = new MapTileBuilder(topWindowFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(topWindowTile);

        // bottom window wall 
        Frame bottomWindowFrame = new FrameBuilder(getSubImage(0, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder bottomWindowTile = new MapTileBuilder(bottomWindowFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(bottomWindowTile);

        // tulip flower
        Frame tulipFlowerFrame = new FrameBuilder(getSubImage(2, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder tulipFlowerTile = new MapTileBuilder(tulipFlowerFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(tulipFlowerTile);


        // chrome flower
        Frame[] chromeFlowerFrames = new Frame[] {
                new FrameBuilder(getSubImage(2, 0), 65)
                    .withScale(tileScale)
                    .build(),
                new FrameBuilder(getSubImage(1, 2), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(2, 1), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(1, 2), 65)
                        .withScale(tileScale)
                        .build()
        };

        MapTileBuilder chromeFlowerTile = new MapTileBuilder(chromeFlowerFrames)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(chromeFlowerTile);


 

        // top water
        Frame[] topWaterFrames = new Frame[] {
            new FrameBuilder(getSubImage(5, 0), 65)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(5, 1), 65)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(5, 2), 65)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(5, 1), 65)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(5, 0), 65)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(5, 3), 65)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(5, 4), 65)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(5, 3), 65)
                    .withScale(tileScale)
                    .build()
        };

        MapTileBuilder topWaterTile = new MapTileBuilder(topWaterFrames)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(topWaterTile);


        return mapTiles;
    }
}