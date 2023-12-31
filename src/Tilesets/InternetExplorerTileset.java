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
public class InternetExplorerTileset extends Tileset {

    public InternetExplorerTileset() {
        super(ImageLoader.load("PracticeTilesheet.png"), 16, 16, 3);
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
 
        // wood block
        Frame woodFrame = new FrameBuilder(getSubImage(0, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder woodTile = new MapTileBuilder(woodFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(woodTile);

        
        // left end branch
        Frame leftEndBranchFrame = new FrameBuilder(getSubImage(0, 2))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .build();

        MapTileBuilder leftEndBranchTile = new MapTileBuilder(grassFrame)
                .withTopLayer(leftEndBranchFrame)
                .withTileType(TileType.PASSABLE);
        mapTiles.add(leftEndBranchTile);

        // right end branch
        Frame rightEndBranchFrame = new FrameBuilder(getSubImage(0, 2))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                .build();

        MapTileBuilder rightEndBranchTile = new MapTileBuilder(grassFrame)
                .withTopLayer(rightEndBranchFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(rightEndBranchTile);
        
        // tree leaves
        Frame treeLeavesFrame= new FrameBuilder(getSubImage(0, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder treeLeavesTile = new MapTileBuilder(grassFrame)
                .withTopLayer(treeLeavesFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(treeLeavesTile);

        // tree leaves
        Frame nonPassablestreeLeavesFrame= new FrameBuilder(getSubImage(0, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder nonPassabletreeLeavesTile = new MapTileBuilder(nonPassablestreeLeavesFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(nonPassabletreeLeavesTile);


        // blue 
        Frame blueFrame= new FrameBuilder(getSubImage(0, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder blueTile = new MapTileBuilder(blueFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(blueTile);

        // folder up
        Frame folderUpFrame = new FrameBuilder(getSubImage(0, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder folderUpTile = new MapTileBuilder(folderUpFrame);

        mapTiles.add (folderUpTile);

 
        // pole
        Frame poleFrame = new FrameBuilder(getSubImage(0, 6))
                .withScale(tileScale)
                .withBounds(5, 0, 5, 16)
                .build();

        MapTileBuilder poleTile = new MapTileBuilder(poleFrame)
                // .withTopLayer(poleFrame)
                .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add (poleTile);

        // sign block
        Frame signFrame = new FrameBuilder(getSubImage(1, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder signTile = new MapTileBuilder(grassFrame)
                .withTopLayer(signFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(signTile);

        // righ roof block
        Frame rightRoofFrame = new FrameBuilder(getSubImage(1, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder rightRoofTile = new MapTileBuilder(grassFrame)
                .withTopLayer(rightRoofFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(rightRoofTile);

        // left roof block
        Frame leftRoofFrame = new FrameBuilder(getSubImage(1, 1))
                .withScale(tileScale)
                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                .build();

        MapTileBuilder leftRoofTile = new MapTileBuilder(grassFrame)
                .withTopLayer(leftRoofFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(leftRoofTile);

        // red block
        Frame redFrame = new FrameBuilder(getSubImage(1, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder redTile = new MapTileBuilder(redFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(redTile);

        // white block
        Frame whiteFrame = new FrameBuilder(getSubImage(1, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder whiteTile = new MapTileBuilder(whiteFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(whiteTile);

        // butterfly
        Frame[] butterflyFrames = new Frame[] {
                new FrameBuilder(getSubImage(1, 4), 30)
                    .withScale(tileScale)
                    .build(),
                new FrameBuilder(getSubImage(1, 5), 30)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(1, 6), 30)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(1, 5), 30)
                        .withScale(tileScale)
                        .build()
        };

        MapTileBuilder butterflyTile = new MapTileBuilder(butterflyFrames);

        mapTiles.add(butterflyTile);


        // window frame
        Frame windowFrame = new FrameBuilder(getSubImage(2, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder windowTile = new MapTileBuilder(windowFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(windowTile);

        // window frame
        Frame windowFramePass = new FrameBuilder(getSubImage(2, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder windowTilePass = new MapTileBuilder(windowFramePass);
                

        mapTiles.add(windowTilePass);


        // starFlower frames
        Frame[] starFlowerFrames = new Frame[] {
                new FrameBuilder(getSubImage(2, 1), 20)
                    .withScale(tileScale)
                    .build(),
                new FrameBuilder(getSubImage(2, 2), 20)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(2, 3), 20)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(2,4),20)
                        .withScale(tileScale)
                        .build(),
        };
        MapTileBuilder starFlowerTile = new MapTileBuilder(starFlowerFrames);

        mapTiles.add(starFlowerTile);

        // folder left
        Frame folderLeftFrame = new FrameBuilder(getSubImage(2, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder folderLeftTile = new MapTileBuilder(folderLeftFrame);

        mapTiles.add (folderLeftTile);


        // trash 
        Frame trashFrame = new FrameBuilder(getSubImage(2, 6))
                .withScale(tileScale)
                .withBounds(1, 1, 14, 14)
                .build();

        MapTileBuilder trashTile = new MapTileBuilder(trashFrame)
                // .withTopLayer(trashFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(trashTile);


        // window frame
        Frame brokenWindowFrame = new FrameBuilder(getSubImage(3, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder brokenWindowTile = new MapTileBuilder(brokenWindowFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(brokenWindowTile);


        // library floor 
        Frame libraryFloorFrame = new FrameBuilder(getSubImage(3, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder libraryFloorTile = new MapTileBuilder(libraryFloorFrame);

        mapTiles.add(libraryFloorTile);


        // library roof block
        Frame libraryRoofFrame = new FrameBuilder(getSubImage(3, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder libraryRoofTile = new MapTileBuilder(libraryRoofFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(libraryRoofTile);


        // portal frames
        Frame[] portalFrames = new Frame[] {
                new FrameBuilder(getSubImage(3, 3), 10)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(3, 4), 10)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(3, 5), 10)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(3, 6), 10)
                        .withScale(tileScale)
                        .build()
            };
    
            MapTileBuilder portalTile = new MapTileBuilder(portalFrames);
    
            mapTiles.add(portalTile);

        // passable door
        Frame passableDoorFrame= new FrameBuilder(getSubImage(0, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder passableDoorTile = new MapTileBuilder(passableDoorFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(passableDoorTile);




        return mapTiles;


    }
}

