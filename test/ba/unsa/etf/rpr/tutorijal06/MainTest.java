package ba.unsa.etf.rpr.tutorijal06;

import static org.junit.jupiter.api.Assertions.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)


class MainTest {

    @Start
    public void start (Stage stage) throws Exception {
        Parent mainNode = FXMLLoader.load(Main.class.getResource("sample.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }
    @Test
    public void start (FxRobot robot) {
        TextField ime= robot.lookup("#ime").queryAs(TextField.class);
        assertEquals("", ime.getText());
    }
    @Test
    public void nameFieldTest(FxRobot robot) {
        TextField ime = robot.lookup("#ime").queryAs(TextField.class);
        robot.clickOn("#ime").write("Amila");
        assertEquals("Amila", ime.getText());
    }
    @Test
    public void prezimeFieldTest(FxRobot robot) {
        TextField prezime = robot.lookup("#prezime").queryAs(TextField.class);
        robot.clickOn("#prezime").write("Hrustić");
        assertEquals("Hrustić", prezime.getText());
    }
    @Test
    public void indexFieldTest(FxRobot robot) {
        TextField index = robot.lookup("#index").queryAs(TextField.class);
        robot.clickOn("#index").write("18103");
        assertEquals("18103", index.getText());
    }
    @Test
    public void datumFieldTest(FxRobot robot) {
        TextField datum = robot.lookup("#datum").queryAs(TextField.class);
        robot.clickOn("#datum").write("13.10.1998");
        assertEquals("13.10.1998", datum.getText());
    }
    @Test
    public void jmbgFieldTest(FxRobot robot) {
        TextField jmbg = robot.lookup("#jmbg").queryAs(TextField.class);
        robot.clickOn("#jmbg").write("1310998195058");
        assertEquals("1310998195058", jmbg.getText());
    }
    @Test
    public void mjestoRodTest(FxRobot robot) {
        ComboBox mjesto = robot.lookup("#mjesto").queryAs(ComboBox.class);
        robot.clickOn("#mjesto").write("Zenica");
        assertEquals("Zenica", mjesto.getEditor().getText());
    }
    @Test
    public void jmbgValidationTest(FxRobot robot) {
        TextField jmbg = robot.lookup("#jmbg").queryAs(TextField.class);
        robot.clickOn("#jmbg").write("1310998195058");
        assertEquals(true, jmbg.getStyleClass().contains("poljeIspravno"));
    }
    @Test
    public void emailValidation(FxRobot robot) {
        TextField email = robot.lookup("#email").queryAs(TextField.class);
        robot.clickOn("#email").write("ahrustic.com");
        assertEquals(true, email.getStyleClass().contains("poljeNijeIspravno"));
    }
    @Test
    public void borackaPripadnostTest(FxRobot robot) {
        CheckBox pripadnost = robot.lookup("#pripadnost").queryAs(CheckBox.class);
        robot.clickOn("#pripadnost");
        assertEquals(true, pripadnost.isSelected());
    }


}