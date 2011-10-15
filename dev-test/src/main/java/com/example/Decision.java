package com.example;

/**
 *
 */
public class Decision {
    
    public Decision(
            final ChoiceSender choiceSender)
    {
        this.choiceSender = choiceSender;
    }
    

    /**
     * TODO refactor
     */
    public void decide(
            final String input)
    {
        if (input == "choice1") {
            final Choice choice = new Choice();
            choice.setValue(input);
            
            System.out.println(choice.getValue() + " for roger");
            this.choiceSender.send(choice);
        }
        else if (input == "choice2") {
            final Choice choice = new Choice();
            choice.setValue(input);
            
            System.out.println(choice.getValue() + " for robert");
            this.choiceSender.send(choice);
        }
        else if (input == "choice3") {
            final Choice choice = new Choice();
            choice.setValue(input);
            
            System.out.println(choice.getValue() + " for patrick");
            this.choiceSender.send(choice);
        }
        else if (input == "choice4") {
            final Choice choice = new Choice();
            choice.setValue(input);
            
            System.out.println(choice.getValue() + " for roger");
            this.choiceSender.send(choice);
        }
        else if (input == "choice5") {
            final Choice choice = new Choice();
            choice.setValue(input);
            
            System.out.println(choice.getValue() + " for cléo");
            this.choiceSender.send(choice);
        }
        else {
            final Choice choice = new Choice();
            choice.setValue(input);
            
            System.out.println(choice.getValue() + " for anyone");
            this.choiceSender.send(choice);
        }
        
    }
    

    private final ChoiceSender choiceSender;
    
}
