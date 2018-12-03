package ru.unn.agile.BookStore.ViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    private void setInputData() {
        viewModel.books1Property().set("1");
        viewModel.books2Property().set("2");
        viewModel.books3Property().set("3");
        viewModel.books4Property().set("4");
        viewModel.books5Property().set("5");
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.books1Property().get());
        assertEquals("", viewModel.books2Property().get());
        assertEquals("", viewModel.books3Property().get());
        assertEquals("", viewModel.books4Property().get());
        assertEquals("", viewModel.books5Property().get());
        assertEquals("", viewModel.resultProperty().get());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithNonSetFields() {
        viewModel.calculate();

        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        setInputData();

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.books1Property().set("a");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingWhenNotEnoughFieldsAreFill() {
        viewModel.books1Property().set("1");

        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledInitially() {
        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setInputData();
        viewModel.books1Property().set("trash");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        viewModel.books1Property().set("1");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        setInputData();

        assertFalse(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void canSetSuccessMessage() {
        setInputData();

        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void calculationHasCorrectResult() {
        viewModel.books1Property().set("1");
        viewModel.books2Property().set("3");
        viewModel.books3Property().set("2");
        viewModel.books4Property().set("1");
        viewModel.books5Property().set("5");

        viewModel.calculate();

        assertEquals("82.8", viewModel.resultProperty().get());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.books5Property().set("#selfie");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }
}
