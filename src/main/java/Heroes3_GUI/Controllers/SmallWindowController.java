package Heroes3_GUI.Controllers;


import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;


public class SmallWindowController {

    public SmallWindowInterface smallWindowInterface;

    @FXML
    private Button klikButton;

    private ArrayList heroesNumbers = new ArrayList<Integer>();

    @FXML
    public void initialize(){
        klikButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            heroesNumbers.add(getRandom());
            if(heroesNumbers.size() == 2){
                smallWindowInterface.onKlikButton(heroesNumbers);
                Stage stage = (Stage) klikButton.getScene().getWindow();
                stage.close();
            }
        });
    }


    public Integer getRandom() {
        return new SecureRandom().nextInt(101);
    }
}

interface SmallWindowInterface {
    void onKlikButton(ArrayList<Integer> heroesNumbers);
}
