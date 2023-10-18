package seedu.address.ui;

import java.util.concurrent.TimeoutException;

import org.junit.After;
import org.junit.Before;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import seedu.address.MainApp;

public class MainAppTest extends ApplicationTest {

    private MainApp mainApp;

    @Before
    public void setUp() throws Exception {
        ApplicationTest.launch(MainApp.class);
    }

    @Override
    public void start(Stage stage) {
        mainApp = new MainApp();
        mainApp.start(stage);
        stage.show();
    }

    @After
    public void afterEachTest() throws TimeoutException {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }
}
