/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.SignUp;

import Application.Application;
import javafx.stage.Stage;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 *
 * @author danid
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpControllerCloseTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        new Application().start(stage);
        
    }

    @Override
    public void stop() {
    }

    //Test method to check the behavior of the cancel button.
    @Test
    public void Test_A_cancelButtonTest() {
        clickOn("#hlSignUp");
        clickOn("#btnCancel");
        verifyThat("¿Are you sure you want to exit?", isVisible());
        clickOn("Aceptar");

    }
    

    //Test method to check the behavior of the main window exit button.
    @Test
    public void Test_B_ExitTest() {
    clickOn("#hlSignUp");
    closeCurrentWindow();
    verifyThat("¿Are you sure you want to exit?", isVisible());
    clickOn("Aceptar");  
    }
}
