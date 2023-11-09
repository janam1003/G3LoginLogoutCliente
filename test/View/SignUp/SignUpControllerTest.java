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
 * This is a test class for the SignUpController. It tests various scenarios
 * related to signing up.
 *
 * @author danid
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpControllerTest extends ApplicationTest {

    private static final String mail = "johndoe" + (Math.random() * 100) + "@gmail.com";

    @Override
    public void start(Stage stage) throws Exception {
        new Application().start(stage);
        
    }

    @Override
    public void stop() {
    }

    // Test case for creating an account with empty  
    @Test
    public void Test_A_createButtonEmptyFieldTest() {
        clickOn("#hlSignUp");
        clickOn("#tfNameSurname").write("John Doe");
        clickOn("#tfAddress").write("123 Main St");
        clickOn("#tfZip").write("12345");
        clickOn("#tfPhone").write("+34666666666");
        clickOn("#pfPasswordSU").write("Password1234*");
        clickOn("#pfConfirmPasswordSU").write("Password1234*");
        clickOn("#btnCreateAccount");
        verifyThat("Complete every field", isVisible());
        clickOn("Aceptar");
    }

    // Test case for creating an account with a too long email it is commented beacause it is too long
    //@Test
    public void Test_B_createButtonLongFieldTest() {
        clickOn("#tfEmail").write("cdcvdsvdsvdsvldsanvñlsavnvlsñkadnvdsñalkvnsadlvnslkdvnlkdsñvndsñvnjas´lvnbdsalnakds´fjdnfls´kdnaclkdsncslkda´nfkljdsfncldsk´nc´ldskafjvckldsa´nvlkds´vandsnvls´kdanvvdskvn´ldsanvndvl´kdsanvldk´snv´saldkvndsvnkldsnv´lkdvnasnvlkdsanvlkdsvn´sadkl´vnvndsvnasd´l");
        verifyThat("One field or more are too long", isVisible());
        clickOn("Aceptar");
        clickOn("#tfEmail");
        eraseText(256);
    }

    // Test case for creating an account with wrong input on every field, it checks that the corresponding label is visible
    @Test
    public void Test_C_createButtonWrongFieldsTest() {

        clickOn("#tfNameSurname");
        eraseText(8);
        write("--------");
        clickOn("#tfAddress");
        eraseText(11);
        write("aaa");
        clickOn("#tfZip");
        eraseText(5);
        write("aaa");
        doubleClickOn("#tfPhone");
        eraseText(2);
        write("aaa");
        clickOn("#tfEmail").write("aaa"); 
        clickOn("#pfPasswordSU");
        eraseText(13);
        write("aaa");
        clickOn("#pfConfirmPasswordSU");
        eraseText(13);
        write("aa");
        clickOn("#btnCreateAccount");
        verifyThat("Revise the values", isVisible());
        clickOn("Aceptar");
        verifyThat("#lblName", isVisible());
        verifyThat("#lblEmail", isVisible());
        verifyThat("#lblZip", isVisible());
        verifyThat("#lblPhone", isVisible());
        verifyThat("#lblAddress", isVisible());
        verifyThat("#lblPassword", isVisible());
        verifyThat("#lblConfirmPassword", isVisible());

    }

    // Test case for testing the password reveal method
    @Test
    public void Test_D_PasswordRevealTest() {
        clickOn("#btnEye1");
        clickOn("#btnEye2");
        verifyThat("#tfPasswordRevealSU", isVisible());
        verifyThat("#tfConfirmPasswordRevealSU", isVisible());
        clickOn("#btnEye1");
        clickOn("#btnEye2");
    }

    // Test case for creating an account with valid input
    @Test
    public void Test_E_createButtonFineTest() {
        
        clickOn("#tfNameSurname");
        eraseText(8);
        write("John Doe");
        clickOn("#tfAddress");
        eraseText(3);
        write("123 Main St");
        clickOn("#tfZip");
        eraseText(3);
        write("12345");
        clickOn("#tfPhone");
        eraseText(3);
        write("+34666666666");
        clickOn("#tfEmail");
        eraseText(3);
        write(mail);
        clickOn("#pfPasswordSU");
        eraseText(3);
        write("Password1234*");
        clickOn("#pfConfirmPasswordSU");
        eraseText(2);
        write("Password1234*");
        clickOn("#btnCreateAccount");
        verifyThat("You can now log in with your new account.", isVisible());
        clickOn("Aceptar");
        verifyThat("#btnEye", isVisible());
    }

    // Test case for creating an account with an already existing email
    @Test
    public void Test_F_createButtonEmailExistTest() {
        // Perform actions to simulate user input
        clickOn("#hlSignUp");
        clickOn("#tfNameSurname").write("John Doe");
        clickOn("#tfAddress").write("123 Main St");
        clickOn("#tfZip").write("12345");
        clickOn("#tfPhone").write("+34666666666");
        clickOn("#tfEmail").write(mail);
        clickOn("#pfPasswordSU").write("Password1234*");
        clickOn("#pfConfirmPasswordSU").write("Password1234*");
        clickOn("#btnCreateAccount");
        verifyThat("#lblExist", isVisible());
       

    }

}
