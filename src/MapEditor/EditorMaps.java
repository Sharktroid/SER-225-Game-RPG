package MapEditor;

import Level.Map;
import Maps.TitleScreenMap;
import Maps.HubMap;
import Maps.WorldZeroMap;
import Maps.WorldOneMap;
import Maps.WorldTwoMap;
import Maps.WorldThreeMap;


import java.util.ArrayList;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {{
            add("TestMap");
            add("TitleScreen");
            add("WorldZeroMap");
            add("WorldOneMap");
            add("WorldTwoMap");
            add("WorldThreeMap");
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
            case "WorldThreeMap":
                return new WorldThreeMap();
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
