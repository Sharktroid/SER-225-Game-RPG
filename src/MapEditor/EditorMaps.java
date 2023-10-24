package MapEditor;

import Level.Map;
import Maps.TitleScreenMap;
import Maps.HubMap;
import Maps.WorldZeroMap;
import Maps.WorldOneMap;
import Maps.WorldTwoMap;
import Maps.WorldThreeFloors;

import java.util.ArrayList;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {{
            add("TitleScreen");
            add("WorldZeroMap");
            add("WorldOneMap");
            add("WorldTwoMap");
            add("WorldThreeMap");
            add("WorldThreeZeroMap");
            add("WorldThreeOneMap");
            add("WorldThreeTwoMap");
            add("WorldThreeThreeMap");
            add("WorldThreeFourMap");
            add("HubMap");
        }};
    }

    public static Map getMapByName(String mapName) {
        switch(mapName) {
            case "TitleScreen":
                return new TitleScreenMap();
            case "HubMap":
                return new HubMap();
            case "WorldZeroMap":
                return new WorldZeroMap();
            case "WorldOneMap":
                return new WorldOneMap();
            case "WorldTwoMap":
                return new WorldTwoMap();
            case "WorldThreeZeroMap":
                return new WorldThreeFloors(0);
            case "WorldThreeOneMap":
                return new WorldThreeFloors(1);
            case "WorldThreeTwoMap":
                return new WorldThreeFloors(2);
            case "WorldThreeThreeMap":
                return new WorldThreeFloors(3);
            case "WorldThreeFourMap":
                return new WorldThreeFloors(4);
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
