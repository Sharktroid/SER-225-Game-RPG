package Level;

public class Flag {
    private String flagname;
    private boolean flagstate;
    private boolean flagpersistence;
    private boolean initialState;

    /**
     * This method creates a flag object to be used in the FlagSaves class.
     * 
     * @param flagName      Name of the flag as a string
     * 
     * @param startingValue Starting flag state, false for unset and true for set.
     * 
     * @param persistence   Determins if the flag state saves between maps. True for
     *                      persistent false for temporary.
     * 
     *                      If startingValue or persitence are not set manually,
     *                      they will default to false
     * 
     */
    public Flag(String name, boolean state, boolean persistent) {
        flagname = name;
        flagstate = state;
        initialState = state;
        flagpersistence = persistent;
    }

    public void setName(String name) {
        flagname = name;
    }

    public void setState(boolean state) {
        flagstate = state;
    }

    public void setPersistence(boolean persistence) {
        flagpersistence = persistence;
    }

    public String getName() {
        return flagname;
    }

    public boolean getState() {
        return flagstate;
    }

    public boolean getPersitence() {
        return flagpersistence;
    }

    public boolean getInitialState() {
        return initialState;
    }
}
