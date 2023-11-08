package Level;

import java.util.ArrayList;
import Level.Flag;
import Level.FlagManager;

public class FlagSaves {
    public ArrayList<Flag> savedFlagData = new ArrayList<>();
    public int n;

    public void initialize(String[] flagnames){
        n = flagnames.length;
        for (int i = 0; i <flagnames.length-1; i++){
            Flag flag = new Flag(flagnames[i], false);
            savedFlagData.add(flag);
        }
        
    }


    public void saveAll(){
        for (int i = 0; i <n-1; i++){
            savedFlagData.get(i).setState(Map.flagManager.);
            
        }
        
    }

    

    private boolean isInitialized(Flag flag){

    }

    private Flag getFlag(String flagnames[]){
       reurn
    }
    
}
