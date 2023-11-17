package Level;

public class FlagSaves {

    /**
     * This method sets up all flags in the game to be stored in the flag manager.
     * 
     * @param flagManager The FlagManager that the flags are stored in
     * 
     */

    public void initializeFlags(FlagManager flagManager) {

        // all world flags
        flagManager.addFlag("teleportToHub", false, false);// temp: used to teleport player to hub world

        // hub world flags

        // unused: could use these to detect whether player has been to each world
        flagManager.addFlag("beenToWorldOne", false, false);
        flagManager.addFlag("beenToWorldTwo", false, false);
        flagManager.addFlag("beenToWorldThree", false, false);

        // persistent: talked to fire fox flags. Used to see if the player has talked to
        // the Firefox
        flagManager.addFlag("hasTalkedToFirefox0", false, true);// before entering world one
        flagManager.addFlag("hasTalkedToFirefox1", false, true);// after world one and before world two
        flagManager.addFlag("hasTalkedToFirefox2", false, true);// after world two and before world three
        flagManager.addFlag("hasTalkedToFirefox3", false, true);// after world three and before game clear

        // temp: flags for teleportation using the portals in hub
        flagManager.addFlag("portalOneActivated", false, false);// world one portal
        flagManager.addFlag("portalTwoActivated", false, false);// world two portal
        flagManager.addFlag("portalThreeActivated", false, false);// world three portal

        // persistent: used to unlock portals in hub
        flagManager.addFlag("unlockedPortal1", false, true);
        flagManager.addFlag("unlockedPortal2", false, true);
        flagManager.addFlag("unlockedPortal2", false, true);

        // persistent: used to tell if each world hasa been completed
        flagManager.addFlag("worldOneComplete", false, true);
        flagManager.addFlag("worldTwoComplete", false, true);
        flagManager.addFlag("worldThreeComplete", false, true);

        // world one flags

        // old man jenkins
        flagManager.addFlag("hasTalkedToOMJ", false, true);
        flagManager.addFlag("hasFoundDentures", false, true);
        flagManager.addFlag("hasFinishedOMJ", false, true);

        // engineer
        flagManager.addFlag("hasTalkedToNSE", false, true);



        // librarian
        flagManager.addFlag("hasTalkedToLibrarian", false, false);
        flagManager.addFlag("hasRanVirusScanLibrarian", false, false);
        flagManager.addFlag("hasFoundLibraryShard", false, false);
        // normal npcs
        flagManager.addFlag("hasRanVirusScanNPC1", false, true);
        flagManager.addFlag("hasRanVirusScanNPC2", false, true);
        
        // infected npcs
        flagManager.addFlag("w1CuredNPC1", false, true);
        flagManager.addFlag("w1Btl1",false, false);
        
        flagManager.addFlag("w1CuredNPC2", false, true);
        flagManager.addFlag("w1Btl2",false, false);

        flagManager.addFlag("w1CuredNPC3", false, true);
        flagManager.addFlag("w1Btl3",false, false);

        flagManager.addFlag("w1CuredNPC4", false, true);
        flagManager.addFlag("w1Btl4",false, false);

        flagManager.addFlag("w1CuredNPC5", false, true);
        flagManager.addFlag("w1Btl5",false, false);

        flagManager.addFlag("w1CuredAllNPCs", false, true);


        // enter library
        flagManager.addFlag("enterLibrary", false);

        // world 11/ library flags
        // temp: exit library
        flagManager.addFlag("exitLibrary", false);

        // world two flags

        // beaver
        flagManager.addFlag("hasTalkedToBeaver", false);

        // world three flags

        // temp: used to go up and down levels
        flagManager.addFlag("wentUpLevel", false, false);
        flagManager.addFlag("wentDownLevel", false, false);

        // persistent: test flags. **remove**
        flagManager.addFlag("hasTalkedToRedPanda", false, true);
        flagManager.addFlag("hasTalkedToDino", false, true);

        // test world flags

        // shannon test world
        flagManager.addFlag("hasTalkedToOMJ", false);
        flagManager.addFlag("hasFoundDentures", false);

        flagManager.addFlag("hasTalkedToNSE", false);

        flagManager.addFlag("curedNPC1", false);
        flagManager.addFlag("curedNPC2", false);
        flagManager.addFlag("curedNPC3", false);
        flagManager.addFlag("curedNPC4", false);
        flagManager.addFlag("curedNPC5", false);
        flagManager.addFlag("hasCuredAllNPCs", false);

    }

    /**
     * This method uses the FlagManager reset() method to set all temp flags back to
     * their initial starting value but keep the new flag states.
     * 
     * @param flagManager FlagManager where the flags are stored
     */
    public void loadFlags(FlagManager flagManager) {
        flagManager.reset();
    }
}
