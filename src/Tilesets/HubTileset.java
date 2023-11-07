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
public class HubTileset extends Tileset {

    public HubTileset() {
        super(ImageLoader.load("HubWorldTileset.png"), 16, 16, 3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        // grass
        Frame grassFrame = new FrameBuilder(getSubImage(0, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder grassTile = new MapTileBuilder(grassFrame);

        mapTiles.add(grassTile);

        // sign
        Frame signFrame = new FrameBuilder(getSubImage(3, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder signTile = new MapTileBuilder(signFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(signTile);

        // first top 
        Frame firstTopFrame = new FrameBuilder(getSubImage(0, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder firstTopTile = new MapTileBuilder(firstTopFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(firstTopTile);

        // middle top 
        Frame middleTopFrame = new FrameBuilder(getSubImage(0, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder middleTopTile = new MapTileBuilder(middleTopFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(middleTopTile);


        // last top 
        Frame lastTopFrame = new FrameBuilder(getSubImage(0, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder lastTopTile = new MapTileBuilder(lastTopFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(lastTopTile);

        // roof top 
        Frame roofFrame = new FrameBuilder(getSubImage(0, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder roofTile = new MapTileBuilder(roofFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(roofTile);

        // rock
        Frame rockFrame = new FrameBuilder(getSubImage(3, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder rockTile = new MapTileBuilder(rockFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(rockTile);

        
 
        // top electric 
        Frame[] topElectricFrames = new Frame[] {
                new FrameBuilder(getSubImage(1, 0), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(2, 0), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(1, 0), 65)
                        .withScale(tileScale)
                        .build(),
            };
    
            MapTileBuilder topElectricTile = new MapTileBuilder(topElectricFrames)
                    .withTileType(TileType.NOT_PASSABLE);
    
            mapTiles.add(topElectricTile);

        // middle electric
        Frame[] middleElectricFrames = new Frame[] {
                new FrameBuilder(getSubImage(1, 1), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(2, 1), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(1, 1), 65)
                        .withScale(tileScale)
                        .build(),
            };
    
            MapTileBuilder middleElectricTile = new MapTileBuilder(middleElectricFrames)
                    .withTileType(TileType.NOT_PASSABLE);
    
            mapTiles.add(middleElectricTile);

        // bottom electric 
        Frame[] bottomElectricFrames = new Frame[] {
                new FrameBuilder(getSubImage(1, 2), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(2, 2), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(1, 2), 65)
                        .withScale(tileScale)
                        .build(),
            };
    
            MapTileBuilder bottomElectricTile = new MapTileBuilder(bottomElectricFrames)
                    .withTileType(TileType.NOT_PASSABLE);
    
            mapTiles.add(bottomElectricTile);





        // n/a 
        Frame greyRockFrame = new FrameBuilder(getSubImage(3, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder greyRockTile = new MapTileBuilder(greyRockFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(greyRockTile);

        // bush
        Frame bushFrame = new FrameBuilder(getSubImage(3, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder bushTile = new MapTileBuilder(bushFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(bushTile);

        // remnant --------- house body
        Frame houseBodyFrame = new FrameBuilder(getSubImage(3, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder houseBodyTile = new MapTileBuilder(houseBodyFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(houseBodyTile);


        // remnant ------------- top water
        Frame[] portalFrames = new Frame[] {
            new FrameBuilder(getSubImage(4, 0), 10)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(4, 1), 10)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(4, 2), 10)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(4, 3), 10)
                    .withScale(tileScale)
                    .build()
        };

        MapTileBuilder portalTile = new MapTileBuilder(portalFrames);

        mapTiles.add(portalTile);




        // remnant ------------- top water
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
