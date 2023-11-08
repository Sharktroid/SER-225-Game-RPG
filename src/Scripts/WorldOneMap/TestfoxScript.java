



package Scripts.WorldOneMap;
import Level.Textbox.Style;
import Level.*;

// script for talking to ttestfox npc
// the script is segmented -- it has multiple setups, cleanups, and executions based on its current action
public class TestfoxScript extends Script<NPC> {
static int FragmentCount = 0;
    @Override
    protected void setup() {
        lockPlayer();
        setTextboxStyle(Textbox.Style.WORLDONE);
        setNPCName("Testfox");
        showTextbox();
        

        if (FragmentCount >= 1) {
            showTextbox();
            addTextToTextboxQueue( "FragmentCount is " + FragmentCount);
            
            

        }
        else if (FragmentCount == 0) {
            showTextbox();
            addTextToTextboxQueue("FragmentCount is = 0");

        }
        else {
            showTextbox();
            addTextToTextboxQueue("This is the else text");
        }
        entity.facePlayer(player);
    }


    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();



    }

    @Override
    public ScriptState execute() {
        start();
        if (!isTextboxQueueEmpty()) {
            return ScriptState.RUNNING;
        }
        end();
        return ScriptState.COMPLETED;
    }

    public static void addFragmentCount(int fragmentCount) {
        FragmentCount = FragmentCount + fragmentCount ;
        System.out.println("Added Fragment count");
    }

     public int getFragmentCount() {
        return FragmentCount;
    }
}


