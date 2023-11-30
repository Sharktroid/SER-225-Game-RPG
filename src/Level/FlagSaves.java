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

        // Dentures quest
        flagManager.addFlag("hasTalkedToOMJ", false, true);
        flagManager.addFlag("hasFoundDentures", false, true);
        flagManager.addFlag("hasFinishedOMJ", false, true);///FALSE

        // Virus quest
        flagManager.addFlag("hasTalkedToNSE", false, true);///FALSE
        flagManager.addFlag("hasFinishedNSE",false, true);
        flagManager.addFlag("hasScanner", false, true);
        flagManager.addFlag("hasRanVirusScanNPC1", false, true);
        flagManager.addFlag("hasRanVirusScanNPC2", false, true);
        flagManager.addFlag("w1CuredNPC1", false, true);
        flagManager.addFlag("w1Btl1",false, false);
        flagManager.addFlag("changeSprite", false, false);
        flagManager.addFlag("w1CuredNPC2", false, true);
        flagManager.addFlag("w1Btl2",false, false);
        flagManager.addFlag("w1CuredNPC3", false, true);
        flagManager.addFlag("w1Btl3",false, false);
        flagManager.addFlag("w1CuredNPC4", false, true);
        flagManager.addFlag("w1Btl4",false, false);
        flagManager.addFlag("w1CuredNPC5", false, true);
        flagManager.addFlag("w1Btl5",false, false);
        flagManager.addFlag("w1CuredAllNPCs", false, true);

        // Library quest
        flagManager.addFlag("hasTalkedToLibrarian", false, true);///FALSE
        flagManager.addFlag("scannedLibrarian", false, true);///FALSE
        flagManager.addFlag("hasFinishedLib",false,true);


        flagManager.addFlag("w1FoundFrag1", false, true);
        flagManager.addFlag("w1FoundFrag2", false, true);
        flagManager.addFlag("w1FoundFrag3", false, true);
        


        // enter library
        flagManager.addFlag("enterLibrary", false, false);
        flagManager.addFlag("exitLibrary", false, false);
        flagManager.addFlag("wentIntoLibrary", false, true);
        flagManager.addFlag("wentOutOfLibrary", false, true);

        // world two flags

        //Fragment Flags
        flagManager.addFlag("w2FoundFrag1", false, true);
        flagManager.addFlag("w2FoundFrag2", false, true);
        //Spotify Quest Frag
        flagManager.addFlag("w2FoundFrag3", false, true);

        // Amazon
        flagManager.addFlag("enterBuilding1", false, false);
        flagManager.addFlag("exitBuilding1", false, false);
        flagManager.addFlag("wentIntoBuilding1", false, true);
        flagManager.addFlag("wentOutOfBuilding1", false, true);

        // Apple
        flagManager.addFlag("enterBuilding2", false, false);
        flagManager.addFlag("exitBuilding2", false, false);
        flagManager.addFlag("wentIntoBuilding2", false, true);
        flagManager.addFlag("wentOutOfBuilding2", false, true);

        //Spotify
        flagManager.addFlag("enterBuilding3", false, false);
        flagManager.addFlag("exitBuilding3", false, false);
        flagManager.addFlag("wentIntoBuilding3", false, true);
        flagManager.addFlag("wentOutOfBuilding3", false, true);

        //Starbucks
        flagManager.addFlag("enterBuilding4", false, false);
        flagManager.addFlag("exitBuilding4", false, false);
        flagManager.addFlag("wentIntoBuilding4", false, true);
        flagManager.addFlag("wentOutOfBuilding4", false, true);

        // test flags. **remove**
        flagManager.addFlag("hasTalkedToBeaver", false);

        // world three flags

        //
        flagManager.addFlag("level1Complete", false, true);
        flagManager.addFlag("level2Complete", false, true);
        flagManager.addFlag("level3Complete", false, true);

        // used to go up and down levels
        flagManager.addFlag("goUpLevel", false, false); 
        flagManager.addFlag("goDownLevel", false, false);
        flagManager.addFlag("wentUpLevel", false, true);
        flagManager.addFlag("wentDownLevel", false, true);

        // used for infected npcs
        flagManager.addFlag("w3f1CuredNPC1", false, true);
        flagManager.addFlag("w3f1Btl1", false, false);
        flagManager.addFlag("w3f1CuredNPC2", false, true);
        flagManager.addFlag("w3f1Btl2", false, false);

        flagManager.addFlag("w3f2CuredNPC1", false, true);
        flagManager.addFlag("w3f2Btl1", false, false);
        flagManager.addFlag("w3f2CuredNPC2", false, true);
        flagManager.addFlag("w3f2Btl2", false, false);
        flagManager.addFlag("w3f2CuredNPC3", false, true);
        flagManager.addFlag("w3f2Btl3", false, false);

        flagManager.addFlag("w3f3CuredNPC1", false, true);
        flagManager.addFlag("w3f3Btl1", false, false);
        flagManager.addFlag("w3f3CuredNPC2", false, true);
        flagManager.addFlag("w3f3Btl2", false, false);
        flagManager.addFlag("w3f3CuredNPC3", false, true);
        flagManager.addFlag("w3f3Btl3", false, false);
        flagManager.addFlag("w3f3CuredNPC4", false, true);
        flagManager.addFlag("w3f3Btl4", false, false);

        flagManager.addFlag("hasTalkedToCEO", false, true);
        flagManager.addFlag("hasTalkedToOutsideGuy", false, true);



        // test world flags

        // shannon test world
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
