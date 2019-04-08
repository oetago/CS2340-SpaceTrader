package neighbors.com.spacetrader;

import org.junit.Before;
import org.junit.Test;

import neighbors.com.spacetrader.ui.configuration.EditConfigurationViewModel;

import static org.junit.Assert.assertEquals;

public class EditConfigurationViewModelTest {
    private EditConfigurationViewModel viewModel;

    @Before
    public void setUp() {
        //viewModel = new EditConfigurationViewModel(); //TODO EDVM does not have a default constructor; make this line work
    }

    @Test
    public void testDefaultValues() {
        assertEquals(viewModel.getMaxPoints(), viewModel.getAvailablePoints());
    }

}
