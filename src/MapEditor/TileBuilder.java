package MapEditor;

import Engine.GraphicsHandler;
import Level.*;
import Utils.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class TileBuilder extends JPanel {
    private Map map;
    private MapTile hoveredMapTile;
    private SelectedTileIndexHolder controlPanelHolder;
    private GraphicsHandler graphicsHandler = new GraphicsHandler();
    private JLabel hoveredTileIndexLabel;
    private boolean showNPCs = true;
    private boolean showEnhancedMapTiles = true;
    private boolean showTriggers;
    private MapEntity selectedEntity;

    public TileBuilder(SelectedTileIndexHolder controlPanelHolder, JLabel hoveredTileIndexLabel) {
        setBackground(Colors.MAGENTA);
        setLocation(0, 0);
        setPreferredSize(new Dimension(585, 562));
        this.controlPanelHolder = controlPanelHolder;
        this.hoveredTileIndexLabel = hoveredTileIndexLabel;
        addMouseListener(new MouseListener() {
            @Override
            public void mouseExited(MouseEvent e) {
                hoveredMapTile = null;
                hoveredTileIndexLabel.setText("");
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (selectedEntity == null) {
                    tileSelected(e.getPoint());
                }
                else {
                    selectedEntity = null;
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) { }

            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) { }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                tileHovered(e.getPoint());
                if (selectedEntity != null) {
                    selectedEntity.setX(e.getX() - e.getX() % map.getTileset().getScaledSpriteWidth()
                            + selectedEntity.getX() % map.getTileset().getScaledSpriteWidth());
                    selectedEntity.setY(e.getY() - e.getY() % map.getTileset().getScaledSpriteHeight()
                            + selectedEntity.getY() % map.getTileset().getScaledSpriteHeight());
                    repaint();
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                tileHovered(e.getPoint());
                tileSelected(e.getPoint());
            }
        });
    }

    public void setMap(Map map) {
        this.map = map;
        setPreferredSize(new Dimension(map.getWidthPixels(), map.getHeightPixels()));
        repaint();
    }

    public void draw() {
        for (MapTile tile : map.getMapTiles()) {
            tile.draw(graphicsHandler);
        }

        if (showEnhancedMapTiles) {
            for (EnhancedMapTile enhancedMapTile : map.getEnhancedMapTiles()) {
                enhancedMapTile.draw(graphicsHandler);
            }
        }

        if (showNPCs) {
            for (NPC npc : map.getNPCs()) {
                npc.draw(graphicsHandler);
            }
        }

        if (showTriggers) {
            for (Trigger trigger : map.getTriggers()) {
                trigger.draw(graphicsHandler, new Color(255, 0, 255, 100));
            }
        }

        if (hoveredMapTile != null) {
            graphicsHandler.drawRectangle(
                    Math.round(hoveredMapTile.getX()) + 2,
                    Math.round(hoveredMapTile.getY()) + 2,
                    hoveredMapTile.getWidth() - 5,
                    hoveredMapTile.getHeight() - 5,
                    Color.YELLOW,
                    5
            );
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphicsHandler.setGraphics((Graphics2D) g);
        draw();
    }

    public void tileSelected(Point selectedPoint) {
        NPC currentNPC = (NPC) getMapEntity(selectedPoint, new ArrayList<MapEntity>(map.getNPCs()));
        if(currentNPC != null) {
            selectedEntity = currentNPC;
        }
        else {
            EnhancedMapTile currentEnhancedMapTile = (EnhancedMapTile) getMapEntity(selectedPoint, new ArrayList<MapEntity>(map.getEnhancedMapTiles()));
            if (currentEnhancedMapTile == null) {
                int selectedTileIndex = getSelectedTileIndex(selectedPoint);
                if (selectedTileIndex != -1) {
                    MapTile oldMapTile = map.getMapTiles()[selectedTileIndex];
                    MapTile newMapTile =  map.getTileset().getTile(controlPanelHolder.getSelectedTileIndex()).build(oldMapTile.getX(), oldMapTile.getY());
                    newMapTile.setMap(map);
                    map.getMapTiles()[selectedTileIndex] = newMapTile;
                }
            }
            else {
                selectedEntity = currentEnhancedMapTile;
            }
        }
        repaint();
    }

    private MapEntity getMapEntity(Point point, ArrayList<MapEntity> iterator) {
        point = new Point((int) (point.getX() - point.getX() % map.getTileset().getScaledSpriteWidth()),
                (int) (point.getY() - point.getY() % map.getTileset().getScaledSpriteHeight()));
        if (showNPCs) {
            for (int index = 0; index < iterator.size(); index++) {
                MapEntity currentNPC = (MapEntity) iterator.get(index);
                if ((point.getX() > currentNPC.getX() - map.getTileset().getScaledSpriteWidth())
                        && (point.getX() < currentNPC.getX() + currentNPC.getWidth()) &&
                        (point.getY() > currentNPC.getY() - map.getTileset().getScaledSpriteHeight())
                        && (point.getY() < currentNPC.getY() + currentNPC.getHeight())) {
                    return currentNPC;
                }
            }
        }
        return null;
    }

    public void tileHovered(Point hoveredPoint) {
        this.hoveredMapTile = getHoveredTile(hoveredPoint);
        if (this.hoveredMapTile != null) {
            int hoveredIndexX = Math.round(this.hoveredMapTile.getX()) / map.getTileset().getScaledSpriteWidth();
            int hoveredIndexY = Math.round(this.hoveredMapTile.getY()) / map.getTileset().getScaledSpriteHeight();
            hoveredTileIndexLabel.setText("X: " + hoveredIndexX + ", Y: " + hoveredIndexY);
            repaint();
        }
    }

    protected MapTile getHoveredTile(Point mousePoint) {
        for (MapTile mapTile : map.getMapTiles()) {
            if (isPointInTile(mousePoint, mapTile)) {
                return mapTile;
            }
        }
        return null;
    }

    protected int getSelectedTileIndex(Point mousePoint) {
        MapTile[] mapTiles = map.getMapTiles();
        for (int i = 0; i < mapTiles.length; i++) {
            if (isPointInTile(mousePoint, mapTiles[i])) {
                return i;
            }
        }
        return -1;
    }

    protected boolean isPointInTile(Point point, MapTile tile) {
        return (point.x >= tile.getX() && point.x <= tile.getX() + tile.getWidth() &&
                point.y >= tile.getY() && point.y <= tile.getY() + tile.getHeight());
    }

    public boolean getShowNPCs() {
        return showNPCs;
    }

    public void setShowNPCs(boolean showNPCs) {
        this.showNPCs = showNPCs;
        repaint();
    }

    public boolean getShowEnhancedMapTiles() {
        return showEnhancedMapTiles;
    }

    public void setShowEnhancedMapTiles(boolean showEnhancedMapTiles) {
        this.showEnhancedMapTiles = showEnhancedMapTiles;
        repaint();
    }

    public boolean getShowTriggers() {
        return showTriggers;
    }

    public void setShowTriggers(boolean showTriggers) {
        this.showTriggers = showTriggers;
        repaint();
    }
}
