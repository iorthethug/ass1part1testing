import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class WorkScheduleTest {


    @Test
        // startTime > endTime
    void setRequiredNumberPartition1() {
        WorkSchedule ws = new WorkSchedule(5);

        ws.setRequiredNumber(1, 2, 1);
        int zero = ws.readSchedule(0).requiredNumber;
        int one = ws.readSchedule(1).requiredNumber;
        int two = ws.readSchedule(2).requiredNumber;
        int three = ws.readSchedule(3).requiredNumber;
        int four = ws.readSchedule(4).requiredNumber;
        WorkSchedule ws2 = ws;
        assertEquals(0, zero);
        assertEquals(0, one);
        assertEquals(0, two);
        assertEquals(0, three);
        assertEquals(0, four);
        assertEquals(ws, ws2);

    }

    @Test
        //Partition #2: endTime >= startTime & nemployee < workingEmployee.length[startTime]
    void setRequiredNumberPartition2() {
        WorkSchedule ws = new WorkSchedule(3);

        ws.setRequiredNumber(2, 0, 2);
        ws.addWorkingPeriod("A", 0, 2);
        ws.addWorkingPeriod("B", 0, 2);
        ws.setRequiredNumber(1, 0, 2);
        int reqNum = ws.readSchedule(0).requiredNumber;

        assertEquals(1, ws.readSchedule(0).workingEmployees.length);
        assertEquals(reqNum, 1);
        // FOUND BUG ONE: setRequiredNumber does remove all employees from the schedule
    }

    @Test
        // endTime >= startTime & workingEmployee.length[startTime] < nemployee
    void setRequiredNumberPartition3() {
        WorkSchedule ws = new WorkSchedule(3);

        ws.setRequiredNumber(2, 0, 2);
        ws.addWorkingPeriod("A", 0, 2);
        ws.addWorkingPeriod("B", 0, 2);
        ws.setRequiredNumber(3, 0, 2);
        int reqNum = ws.readSchedule(0).requiredNumber;

        //setRequiredNumber works when the new number is greater than the old number
        assertEquals(2, ws.readSchedule(0).workingEmployees.length);
        assertEquals(reqNum, 3);


    }

    @Test
    void setRequiredNumber4() {
        WorkSchedule ws = new WorkSchedule(5);
        ws.setRequiredNumber(5, 1, 2);
        int zero = ws.readSchedule(0).requiredNumber;

        assertEquals(0, zero);
    }


    @Test
        // “input: currentTime = 0”, given schedule[0].workingEmployees.length < schedule[0].requiredNumber
    void nextIncompleteTest1() {
        WorkSchedule ws = new WorkSchedule(5);
        ws.setRequiredNumber(2, 0, 2);
        ws.addWorkingPeriod("A", 0, 2);
        int next = ws.nextIncomplete(0);
        assertEquals(0, next);
        //FOUND BUG TWO: nextIncomplete does not return the correct index
        // always does return the last index of the schedule
    }

    @Test
        // “input: currentTime = 0”, given schedule[0].workingEmployees.length == schedule[0].requiredNumber
    void nextIncompleteTest2() {
        WorkSchedule ws = new WorkSchedule(5);
        ws.setRequiredNumber(1, 0, 2);
        ws.addWorkingPeriod("A", 0, 2);
        int next = ws.nextIncomplete(0);
        assertEquals(-1, next);

    }
}