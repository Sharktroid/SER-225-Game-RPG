

package Scripts.WorldOneMap;

import Level.*;

// script for talking to Garfunkle npc
// the script is segmented -- it has multiple setups, cleanups, and executions based on its current action
public class GarfunkleScript extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();
        setTextboxStyle(Textbox.Style.WORLDONE);
        setNPCName("Garfunkle");
        showTextbox();
        String[] selections1 = {"Are you Garfunkle?"};
        String[] answers1 = {"Depends whose asking"};
        String[] selections2 = {""};
        String[] answers2 = {""};




        if (!isFlagSet("hasTalkedToGarfunkle")) {
            showTextbox();
            addTextToTextboxQueue( "Meow or whatever");
            addTextToTextboxQueue( "Leave me alone \n your presence is making monday worse than usual");
            setFlag("hasTalkedToGarfunkle");

        }

        else if (isFlagSet("hasTalkedToGarfunkle") && (!isFlagSet("hasTalkedToElder"))) {
            showTextbox();
            addTextToTextboxQueue("Didn't I say to leave me alone?");

        }

         else if (isFlagSet("hasTalkedToElder") && (currentFetchCount >= 1) && !isFlagSet("foundFetch") && (isFlagSet("findFetch")) ) {

            showTextbox();
            addTextToTextboxQueue("Oh wow you actually found some");
            addTextToTextboxQueue("i had no idea there was even any on this island");
            addTextToTextboxQueue("Well I guess I'll go back home soon \n justy going to finish my lasanga first");
            setFlag("foundFetch");
            setFlag("foundCat");
        }



        else if (isFlagSet("hasTalkedToGarfunkle") && (isFlagSet("hasTalkedToElder"))&&(!isFlagSet("findFetch"))) {
            showTextbox();
            addTextToTextboxQueue("Didn't I say to leave me alone?", selections1, answers1);
            addTextToTextboxQueue("The ol' lady sent you to get me for her didnt she?");
            addTextToTextboxQueue("How about this, if you find me the Lasanga I lost \n I'll go back alright");
            setFlag("findFetch");



        }

       else if (isFlagSet("findfetch") && !isFlagSet("foundFetch")) {
            showTextbox();
            addTextToTextboxQueue("I told you already \n you have to get my lasanga");



        }



        else if (isFlagSet("foundFetch")) {
            showTextbox();
            addTextToTextboxQueue("I already said Ill go back soon");



        }

        else {
            showTextbox();
            addTextToTextboxQueue("I told you bring me back my lasanga first \n then I'll go back");
        }




        entity.facePlayer(player);
    }
static int currentFetchCount = 0;
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

    public static void setCurrentFetch(int fetchCount) {
        currentFetchCount = fetchCount + 1;

    }
}

