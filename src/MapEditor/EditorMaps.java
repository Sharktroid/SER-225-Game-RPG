package MapEditor;

import Level.Map;
import Maps.TestMap;
import Maps.TitleScreenMap;
import Maps.WorldZeroMap;
import Maps.WorldOneMap;

import java.util.ArrayList;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {{
            add("TestMap");
            add("TitleScreen");
            add("WorldZeroMap");
            add("WorldOneMap");
        }};
    }

    public static Map getMapByName(String mapName) {
        switch(mapName) {
            case "TestMap":
                return new TestMap();
            case "TitleScreen":
                return new TitleScreenMap();
            case "WorldZeroMap":
                return new WorldZeroMap();
            case "WorldOneMap":
                return new WorldOneMap();
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
