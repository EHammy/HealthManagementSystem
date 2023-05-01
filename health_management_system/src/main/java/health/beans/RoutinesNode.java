package health.beans;

import lombok.Data;

@Data
public class RoutinesNode {
    private Routines routine;
    private RoutinesNode next;

    public RoutinesNode(Routines routine) {
        this.routine = routine;
        this.next = null;
    }
}
