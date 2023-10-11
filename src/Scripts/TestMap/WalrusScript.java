package Scripts.TestMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;

// script for talking to walrus npc
public class WalrusScript extends Script<NPC> {

    private int sequence = 0;
    private int responseNum = -1;

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();
        String[] selections = {"A", "B", "C"};
        String[] answers = {"This is my response to A", "This is my response to B", "This is my response to C"};

        entity.facePlayer(player);
        // changes what walrus says when talking to him the first time (flag is not set) vs talking to him afterwards (flag is set)
        if (!isFlagSet("hasTalkedToWalrus")) {
            if (sequence == 0) {
                System.out.println("Sequence 0");
                addTextToTextboxQueue( "Hi Cat!!", selections, answers);
                responseNum = getChoice();
                System.out.println("responseNum: " + responseNum);
                // addTextToTextboxQueue( "....oh, you lost your ball?");
                // addTextToTextboxQueue( "Hmmm...my walrus brain remembers seeing Dino with\nit last. Maybe you can check with him?");
            } else if (sequence == 1) {
                System.out.println("Sequence 1");
                if (responseNum == 0) {
                    addTextToTextboxQueue( "This is the branch of A.");
                    addTextToTextboxQueue( "What a wonderful branch.");
                } else if (responseNum == 1) {
                    addTextToTextboxQueue( "This is the branch of B.");
                    addTextToTextboxQueue( "Not too fond of this branch...");
                } else if (responseNum == 2) {
                    addTextToTextboxQueue( "This is the branch of C.");
                    addTextToTextboxQueue( "This branch is alright.");
                }
            }
        } else {
            addTextToTextboxQueue( "I sure love doing walrus things!");
        }
    }
    

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        if (sequence == 0) {
            responseNum = getChoice();
            sequence++;
        } else if (sequence == 1) {
            System.out.println("Sequence 1: Choice is: " + responseNum);
            setFlag("hasTalkedToWalrus");
            sequence++;
        }

        // set flag so that if walrus is talked to again after the first time, what he says changes
    }

    @Override
    public ScriptState execute() {
        // start();
        // if (!isTextboxQueueEmpty()) {
        //     return ScriptState.RUNNING;
        // }
        // end();
        // return ScriptState.COMPLETED;
        if (!isFlagSet("hasTalkedToWalrus")) {
            if (sequence == 0) {
                start();
                if (isTextboxQueueEmpty()) {
                    end();
                }  
            } else if (sequence == 1) {
                start();
                if (isTextboxQueueEmpty()) {
                    end();
                }
            } else if (sequence == 2) {
                start();
                if (isTextboxQueueEmpty()) {
                    end();
                }
            }
            return ScriptState.RUNNING;
        } else if (isFlagSet("hasTalkedToWalrus")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
            return ScriptState.COMPLETED;
        }
        return ScriptState.COMPLETED;
    }   
}
