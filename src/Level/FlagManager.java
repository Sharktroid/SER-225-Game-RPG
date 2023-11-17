package Level;

import java.util.HashMap;
import java.util.Map.Entry;

public class FlagManager {
    protected HashMap<String, Flag> flags = new HashMap<>();



    public void addFlag(String flagName) {
        Flag flag = new Flag(flagName, false, false);
        flags.put(flagName, flag);
    }

    public void addFlag(String flagName, boolean startingValue) {
        Flag flag = new Flag(flagName, startingValue, false);
        flags.put(flagName, flag);
    }

    public void addFlag(String flagName, boolean startingValue, boolean persistence) {
        Flag flag = new Flag(flagName, startingValue, persistence);
        flags.put(flagName, flag);
    }

    public void setFlag(String flagName) {
        if (flags.containsKey(flagName)) {
            flags.get(flagName).setState(true);
        }
    }

    public void unsetFlag(String flagName) {
        if (flags.containsKey(flagName)) {
            flags.get(flagName).setState(false);
        }
    }

    
    public void reset() {
        for (Entry<String, Flag> entry : flags.entrySet()) {
            if (entry.getValue().getPersitence() == false)
                entry.getValue().setState(entry.getValue().getInitialState());
        }
    }


    

    public boolean isFlagSet(String flagName) {
        if (flags.containsKey(flagName)) {
            if (flags.get(flagName).getState() == true)
                return true;
        }
        return false;
    }

    public boolean getFlagState(String flagname) {
        if (isFlagSet(flagname)){
            return true;
        }
        else{
            return false;
        }
        
    } 

    
}
