package MapEditor;

import Level.Map;
import Maps.*;

import java.util.ArrayList;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {
            {
                add("TitleScreen");
                add("WorldZeroMap");
                add("W1GMap");
                add("W1LibraryMap");
                add("W2GMap");
                add("W2AmazonMap");
                add("W2AppleMap");
                add("W2SpotifyMap");
                add("W2StarbucksMap");
                add("W3GMap");
                add("W31Map");
                add("W32Map");
                add("W33Map");
                add("W34Map");
                add("EvanTestMap");
                add("CalvinTestMap");
                add("ShannonTestMap");
                add("JulietTestMap");
                add("AaronTestMap");
                add("HubMap");
            }
        };
    }

    public static Map getMapByName(String mapName) {
        switch (mapName) {
            case "TitleScreen":
                return new TitleScreenMap();
            case "HubMap":
                return new HubMap();
            case "WorldZeroMap":
                return new WorldZeroMap();
            case "W1GMap":
                return new W1GMap();
            case "W1LibraryMap":
                return new W1LibraryMap();
            case "W2GMap":
                return new W2GMap();
            case "W2AmazonMap":
                return new W2AmazonMap();
            case "W2AppleMap":
                return new W2AppleMap();
            case "W2SpotifyMap":
                return new W2SpotifyMap();
            case "W2StarbucksMap":
                return new W2StarbucksMap();
            case "W3GMap":
                return new W3GMap();
            case "W31Map":
                return new W31Map();
            case "W32Map":
                return new W32Map();
            case "W33Map":
                return new W33Map();
            case "W34Map":
                return new W34Map();
            case "EvanTestMap":
                return new EvanTestMap();
            case "CalvinTestMap":
                return new CalvinTestMap();
            case "ShannonTestMap":
                return new ShannonTestMap();
            case "JulietTestMap":
                return new JulietTestMap();
            case "AaronTestMap":
                return new AaronTestMap();
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}