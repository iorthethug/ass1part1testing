import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class WorkScheduleTest {



    @Test
    void setRequiredNumber() {
        WorkSchedule ws = new WorkSchedule(5);

        ws.setRequiredNumber(1, 2, 1);
        int zero = ws.readSchedule(0).requiredNumber;
        int one = ws.readSchedule(1).requiredNumber;
        int two = ws.readSchedule(2).requiredNumber;
        int three = ws.readSchedule(3).requiredNumber;
        int four = ws.readSchedule(4).requiredNumber;
        assertEquals(0, zero);
        assertEquals(0, one);
        assertEquals(0, two);
        assertEquals(0, three);
        assertEquals(0, four);



    }

    @Test
    void setRequiredNumberBothEqual () {

        WorkSchedule ws = new WorkSchedule(3);

        ws.setRequiredNumber(2, 0, 2);
        ws.addWorkingPeriod("A", 0, 2);
        ws.addWorkingPeriod("B", 0, 2);
        ws.setRequiredNumber(1, 0, 2);

        //setRequiredNumber removes all employees from the schedule
        assertTrue(ws.readSchedule(0).workingEmployees.length == 1);

    }

    @Test
    void setRequiredNumber3() {
        WorkSchedule ws = new WorkSchedule(2);




    }

    @Test
    void setRequiredNumber4() {
        WorkSchedule ws = new WorkSchedule(5);
        ws.setRequiredNumber(5, 1,2);
        int zero = ws.readSchedule(0).requiredNumber;

        assertEquals(5, zero);
    }

    @Test
    void setRequiredNumber5() {
        WorkSchedule ws = new WorkSchedule(5);
        ws.setRequiredNumber(0,0,0);
    }


    @Test
    void nextIncomplete() {
    }
}