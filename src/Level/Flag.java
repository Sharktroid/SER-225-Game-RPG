package Level;

public class Flag {
    private String flagname;
    private boolean flagstate;

    public Flag(String name, boolean state){
        flagname = name;
        flagstate = state;
    }

    public void setName(String name){
        flagname = name;
    }

    public void setState(boolean state){
        flagstate = state;
    }   

    public String getName(){
        return flagname;
    }

    public boolean getState(){
        return flagstate;
    }

    

}
