package neighbors.com.spacetrader;

import org.junit.Before;
import org.junit.Test;

import neighbors.com.spacetrader.ui.configuration.EditConfigurationViewModel;

import static org.junit.Assert.assertEquals;

public class EditConfigurationViewModelTest {
    private EditConfigurationViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new EditConfigurationViewModel();
    }

    @Test
    public void testDefaultValues() {
        assertEquals(viewModel.getMaxPoints(), viewModel.getAvailablePoints());
    }

}
