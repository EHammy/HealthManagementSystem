package health.beans;

import lombok.Data;

@Data
public class VitalsNode {
    private Vitals vital;
    private VitalsNode next;

    public VitalsNode(Vitals vital) {
        this.vital = vital;
        this.next = null;
    }

   
}
