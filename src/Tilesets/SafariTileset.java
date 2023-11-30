package Tilesets;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import Level.TileType;
import Level.Tileset;

import java.util.ArrayList;

public class SafariTileset extends Tileset{

    public SafariTileset() {
        super(ImageLoader.load("SafariTileset.png"), 16, 16, 3);
    }
    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        // sidewalk
        Frame sidewalkFrame = new FrameBuilder(getSubImage(0, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder sidewalkTile = new MapTileBuilder(sidewalkFrame);

        mapTiles.add(sidewalkTile);

        // solid road
        Frame solidRoadFrame = new FrameBuilder(getSubImage(0, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder solidRoadTile = new MapTileBuilder(solidRoadFrame);

        mapTiles.add(solidRoadTile);

        // lane road 
        Frame laneRoadFrame = new FrameBuilder(getSubImage(0, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder laneRoadTile = new MapTileBuilder(laneRoadFrame);

        mapTiles.add(laneRoadTile);

        // end road
        Frame endRoadFrame = new FrameBuilder(getSubImage(0, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder endRoadTile = new MapTileBuilder(endRoadFrame);

        mapTiles.add(endRoadTile);

        //  sewer sidewalk
        Frame sewerSidewalkFrame = new FrameBuilder(getSubImage(0, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder sewerSidewalkTile = new MapTileBuilder(sewerSidewalkFrame);

        mapTiles.add(sewerSidewalkTile);

        // apple wall 1
        Frame appleWallOneFrame = new FrameBuilder(getSubImage(1, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder appleWallOneTile = new MapTileBuilder(appleWallOneFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(appleWallOneTile);

        // apple wall 1 flipped horizontally
        Frame appleWallOneFlippedFrame = new FrameBuilder(getSubImage(1, 0))
                .withScale(tileScale)
                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                .build();

        MapTileBuilder appleWallOneFlippedTile = new MapTileBuilder(appleWallOneFlippedFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(appleWallOneFlippedTile);

        // apple wall 1 flipped upsidedown
        Frame appleWallOneUpsidedownFrame = new FrameBuilder(getSubImage(1, 0))
                .withScale(tileScale)
                .withImageEffect(ImageEffect.FLIP_VERTICAL)
                .build();

        MapTileBuilder appleWallOneUpsidedownTile = new MapTileBuilder(appleWallOneUpsidedownFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(appleWallOneUpsidedownTile);

        // apple wall 1 flipped upsidedown and vertical
        Frame appleWallOneFlipNUpsidedownFrame = new FrameBuilder(getSubImage(1, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder appleWallOneFlipNUpsidedownTile = new MapTileBuilder(appleWallOneFlipNUpsidedownFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(appleWallOneFlipNUpsidedownTile);

        // apple wall 2
        Frame appleWallTwoFrame = new FrameBuilder(getSubImage(1, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder appleWallTwoTile = new MapTileBuilder(appleWallTwoFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(appleWallTwoTile);

        // apple wall 2 flipped horizontally
        Frame appleWallTwoFlippedFrame = new FrameBuilder(getSubImage(1, 1))
                .withScale(tileScale)
                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                .build();

        MapTileBuilder appleWallTwoFlippedTile = new MapTileBuilder(appleWallTwoFlippedFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(appleWallTwoFlippedTile);

        // apple window top
        Frame appleWindowTopFrame = new FrameBuilder(getSubImage(2, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder appleWindowTopTile = new MapTileBuilder(appleWindowTopFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(appleWindowTopTile);

        // apple window bottom
        Frame appleWindowBottomFrame = new FrameBuilder(getSubImage(2, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder appleWindowBottomTile = new MapTileBuilder(appleWindowBottomFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(appleWindowBottomTile);

        // apple logo 1
        Frame appleLogoOneFrame = new FrameBuilder(getSubImage(1, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder appleLogoOneTile = new MapTileBuilder(appleLogoOneFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(appleLogoOneTile);

        // apple logo 2
        Frame appleLogoTwoFrame = new FrameBuilder(getSubImage(1, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder appleLogoTwoTile = new MapTileBuilder(appleLogoTwoFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(appleLogoTwoTile);

        // apple logo 3
        Frame appleLogoThreeFrame = new FrameBuilder(getSubImage(2, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder appleLogoThreeTile = new MapTileBuilder(appleLogoThreeFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(appleLogoThreeTile);

        // apple logo 4
        Frame appleLogoFourFrame = new FrameBuilder(getSubImage(2, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder appleLogoFourTile = new MapTileBuilder(appleLogoFourFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(appleLogoFourTile);

        // starbucks above wall (not shaded)
        Frame starbucksAboveFrame = new FrameBuilder(getSubImage(3, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder starbucksAboveTile = new MapTileBuilder(starbucksAboveFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(starbucksAboveTile);

        // starbucks bottom wall (shaded)
        Frame starbucksBottomFrame = new FrameBuilder(getSubImage(3, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder starbucksBottomTile = new MapTileBuilder(starbucksBottomFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(starbucksBottomTile);

        // starbucks cover wall (the one with the umbrella type thing above it)
        Frame starbucksCoverFrame = new FrameBuilder(getSubImage(4, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder starbucksCoverTile = new MapTileBuilder(starbucksCoverFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(starbucksCoverTile);

        // starbucks cover wall with the pole
        Frame starbucksCoverNPoleFrame = new FrameBuilder(getSubImage(4, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder starbucksCoverNPoleTile = new MapTileBuilder(starbucksCoverNPoleFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(starbucksCoverNPoleTile);

        // starbucks wall with pole
        Frame starbucksPoleFrame = new FrameBuilder(getSubImage(4, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder starbucksPoleTile = new MapTileBuilder(starbucksPoleFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(starbucksPoleTile);

        // starbucks logo 1
        Frame starbucksLogoOneFrame = new FrameBuilder(getSubImage(3, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder starbucksLogoOneTile = new MapTileBuilder(starbucksLogoOneFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(starbucksLogoOneTile);

        // astarbucks logo 2
        Frame starbucksLogoTwoFrame = new FrameBuilder(getSubImage(3, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder starbucksLogoTwoTile = new MapTileBuilder(starbucksLogoTwoFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(starbucksLogoTwoTile);

        // starbucks logo 3
        Frame starbucksLogoThreeFrame = new FrameBuilder(getSubImage(4, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder starbucksLogoThreeTile = new MapTileBuilder(starbucksLogoThreeFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(starbucksLogoThreeTile);

        // starbucks logo 4
        Frame starbucksLogoFourFrame = new FrameBuilder(getSubImage(4, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder starbucksLogoFourTile = new MapTileBuilder(starbucksLogoFourFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(starbucksLogoFourTile);

        // amazon wall
        Frame amazonWallFrame = new FrameBuilder(getSubImage(5, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder amazonWallTile = new MapTileBuilder(amazonWallFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(amazonWallTile);

        // amazon logo wall
        Frame amazonLogoWallFrame = new FrameBuilder(getSubImage(5, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder amazonLogoWallTile = new MapTileBuilder(amazonLogoWallFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(amazonLogoWallTile);

        // door left wall
        Frame doorLeftFrame = new FrameBuilder(getSubImage(5, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder doorLeftTile = new MapTileBuilder(doorLeftFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(doorLeftTile);

        // door right wall
        Frame doorRightFrame = new FrameBuilder(getSubImage(5, 2))
                .withScale(tileScale)
                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                .build();

        MapTileBuilder doorRightTile = new MapTileBuilder(doorRightFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(doorRightTile);

        // spotify window top 
        Frame spotifyWindowTopFrame = new FrameBuilder(getSubImage(6, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder spotifyWindowTopTile = new MapTileBuilder(spotifyWindowTopFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(spotifyWindowTopTile);

        // spotify window bottom 
        Frame spotifyWindowBottomFrame = new FrameBuilder(getSubImage(7, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder spotifyWindowBottomTile = new MapTileBuilder(spotifyWindowBottomFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(spotifyWindowBottomTile);

        // spotify window middle
        Frame spotifyWindowMiddleFrame = new FrameBuilder(getSubImage(6, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder spotifyWindowMiddleTile = new MapTileBuilder(spotifyWindowMiddleFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(spotifyWindowMiddleTile);

        // spotify logo 1
        Frame spotifyLogoOneFrame = new FrameBuilder(getSubImage(6, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder spotifyLogoOneTile = new MapTileBuilder(spotifyLogoOneFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(spotifyLogoOneTile);

        // spotify logo 2
        Frame spotifyLogoTwoFrame = new FrameBuilder(getSubImage(6, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder spotifyLogoTwoTile = new MapTileBuilder(spotifyLogoTwoFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(spotifyLogoTwoTile);

        // spotify logo 3
        Frame spotifyLogoThreeFrame = new FrameBuilder(getSubImage(7, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder spotifyLogoThreeTile = new MapTileBuilder(spotifyLogoThreeFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(spotifyLogoThreeTile);

        // spotify logo 4
        Frame spotifyLogoFourFrame = new FrameBuilder(getSubImage(7, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder spotifyLogoFourTile = new MapTileBuilder(spotifyLogoFourFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(spotifyLogoFourTile);

        // apple wall 3
        Frame appleWallThreeFrame = new FrameBuilder(getSubImage(2, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder appleWallThreeTile = new MapTileBuilder(appleWallThreeFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(appleWallThreeTile);

        // apple wall 3 fliped
        Frame appleWallThreeFlippedFrame = new FrameBuilder(getSubImage(2, 4))
                .withScale(tileScale)
                .withImageEffect(ImageEffect.FLIP_VERTICAL)
                .build();

        MapTileBuilder appleWallThreeFlippedTile = new MapTileBuilder(appleWallThreeFlippedFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(appleWallThreeFlippedTile);

        // door left wall locked
        Frame doorLockedLeftFrame = new FrameBuilder(getSubImage(5, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder doorLockedLeftTile = new MapTileBuilder(doorLockedLeftFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(doorLockedLeftTile);

        // door right wall locked
        Frame doorLockedRightFrame = new FrameBuilder(getSubImage(5, 2))
                .withScale(tileScale)
                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                .build();

        MapTileBuilder doorLockedRightTile = new MapTileBuilder(doorLockedRightFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(doorLockedRightTile);

        // portal tiles
        Frame[] portalFrames = new Frame[] {
                new FrameBuilder(getSubImage(8, 0), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(8, 1), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(8, 2), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(8, 3), 65)
                        .withScale(tileScale)
                        .build(),
    
            };
    
            MapTileBuilder portalTile = new MapTileBuilder(portalFrames)
                    .withTileType(TileType.NOT_PASSABLE);
    
            mapTiles.add(portalTile);


        // table
        Frame tableFrame = new FrameBuilder(getSubImage(6, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder tableTile = new MapTileBuilder(tableFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(tableTile);

        // white square floor
        Frame whiteSquareFloorFrame = new FrameBuilder(getSubImage(1, 0))
                .withScale(tileScale)
                .withImageEffect(ImageEffect.FLIP_VERTICAL)
                .build();

        MapTileBuilder whiteSquareFloorTile = new MapTileBuilder(whiteSquareFloorFrame);

        mapTiles.add(whiteSquareFloorTile);

        // white square floor
        Frame brickFloorFrame = new FrameBuilder(getSubImage(3, 0))
                .withScale(tileScale)
                .withImageEffect(ImageEffect.FLIP_VERTICAL)
                .build();

        MapTileBuilder brickFloorTile = new MapTileBuilder(brickFloorFrame);

        mapTiles.add(brickFloorTile);

        // dark wall
        Frame wallFrame = new FrameBuilder(getSubImage(0, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder wallTile = new MapTileBuilder(wallFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(wallTile);

        // phone on table
        Frame phoneOnTableFrame = new FrameBuilder(getSubImage(7, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder phoneOnTableTile = new MapTileBuilder(phoneOnTableFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(phoneOnTableTile);

       // lost and found
       Frame lostAndFoundFrame = new FrameBuilder(getSubImage(7, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder lostAndFoundTile = new MapTileBuilder(lostAndFoundFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(lostAndFoundTile);
   



        return mapTiles;

    }
    
}
