package Scripts.WorldTwoMap;

import Items.Fragment;
import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Level.Textbox.Style;


// script for talking to sloth npc
public class W2DJScript extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();

        setTextboxStyle(Style.WORLDONE);
        setNPCName("Mr Worldwide");
        showTextbox();

        //i cant promise tomorrow, but i promise... anything else you need, Dale
        String[] selections = {"so you really aren't Pitbull?", "So how did he \n start writing music"};
        String[] answers = {"Nope Pitbull really is just a Pitbull dog \n he makes all the music", "He was just a natural \n from the start, it all came easy to him"};

        String[] selections2 = {"uhhh he seemed \n to be annoyed with you","\nHe didn't seem happy "};
        String[] answers2 = {"Oh man he always is","He mustve been in a bad mood because \n I've been forgetting deliveries"};

        String[] selections3 = {"I found all of the Pitbull stans","\nI gave out all the vinlys "};
        String[] answers3 = {"Oh thats great, you really saved me","You saved me, thanks"};


        // changes what Sloth says when talking to him the first time (flag is not set) vs talking to him afterwards (flag is set)
        if (!isFlagSet("hasTalkedToDJ")) {
            entity.facePlayer(player);
            addTextToTextboxQueue( "It's Mr worldwide, \n Mr 305");
            player.items.add(new Fragment(player));
            addTextToTextboxQueue( "Welcome to Pitbull's Official Spotify collab, Dale");
            addTextToTextboxQueue( "I just want to spread Pitbull's music and let people know \n he's been there and done that");
            addTextToTextboxQueue("You really should meet Pitbull he loves to greet new fans");
            addTextToTextboxQueue("He should be around here in the store somewhere");
            addTextToTextboxQueue("Come back to me after and I \n can show you what we have in the store");
             setFlag("hasTalkedToDJ");
        }

         else if ((isFlagSet("hasTalkedToDJ")) && (!isFlagSet("hasTalkedToPitbull"))) {
            entity.facePlayer(player);
            addTextToTextboxQueue("Dale", selections, answers);
        }


        else if ((isFlagSet("hasTalkedToDJ")) && (isFlagSet("hasTalkedToPitbull")) && (!isFlagSet("returnToDJ")) ) {
            entity.facePlayer(player);
            addTextToTextboxQueue( "So was meeting him amazing??", selections2, answers2);
            addTextToTextboxQueue( "OH NO!!! That reminds me \n I forgot to deliver all of Pitbull's new albums");
            addTextToTextboxQueue("Now I'm really screwed if he finds out");
            addTextToTextboxQueue("You're a Pitbull fan right?!? I mean who isn't?? \n Please take these 5 Vinlys and gibe them out");
            addTextToTextboxQueue("I saw the customers in here today \n they all had on Pitbulls iconic sunglasses");
            addTextToTextboxQueue("Please go find them!");
            setFlag("returnToDJ");
            setSamples(5); //also add code to give player record item in inventory
        }
        else if ((isFlagSet("returnToDJ")) && (currentSamples != 0) ){
            addTextToTextboxQueue( "Thanks again for jumping in \nand being so willing to offer help");
            addTextToTextboxQueue("You seem to only have " + currentSamples + " vinlys left, I believe in you!");

        }
        else if ((isFlagSet("returnToDJ")) && (currentSamples == 0) ){
            addTextToTextboxQueue( "Oh it's you again", selections3, answers3);
            addTextToTextboxQueue( "Here, I got you something");
            addTextToTextboxQueue( "It's a chain with a new stone that just flew into our town");
            addTextToTextboxQueue( "take it as a gift for helping me");
            addTextToTextboxQueue( "Dale");

            setFlag("gaveSamples");

        }
        else {
            entity.facePlayer(player);
            addTextToTextboxQueue( "Dale");
        }

    }



    static int currentSamples = -1;

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();



    }

    public static int setSamples(int samples) {
        currentSamples = (samples);
        return currentSamples;
    }

    public static int removeSamples(int samples) {
        currentSamples = (currentSamples - 1);
        return currentSamples;
    }

    @Override
    public ScriptState execute() {
        // setTextboxStyle(Textbox.Style.WORLDTWO);
        start();
        if (!isTextboxQueueEmpty()) {
            return ScriptState.RUNNING;
        }
        end();
        return ScriptState.COMPLETED;
    }
}



