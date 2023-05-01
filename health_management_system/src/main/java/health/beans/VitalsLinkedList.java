package health.beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class VitalsLinkedList {
    private VitalsNode head;
    private int size;

    public VitalsLinkedList() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return size;
    }

    public void addVital(Vitals vital) {
        VitalsNode newNode = new VitalsNode(vital);
        if (head == null) {
            newNode.getVital().setId(1);
            head = newNode;
        } else {
            VitalsNode currentNode = head;
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }
            newNode.getVital().setId(currentNode.getVital().getId() + 1);
            currentNode.setNext(newNode);
        }
        size++;
    }


    public Vitals getVital(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        VitalsNode currentNode = head;

        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }

        return currentNode.getVital();
    }

    public void removeVital(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == 0) {
            head = head.getNext();
        } else {
            VitalsNode currentNode = head;

            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNext();
            }

            currentNode.setNext(currentNode.getNext().getNext());
        }

        size--;
    }

    public List<Vitals> getAllVitals() {
        List<Vitals> vitalsList = new ArrayList<>();

        VitalsNode currentNode = head;
        while (currentNode != null) {
            vitalsList.add(currentNode.getVital());
            currentNode = currentNode.getNext();
        }

        return vitalsList;
    }

    public void updateVitals(Vitals updatedVitals) {
        VitalsNode currentNode = head;
        while (currentNode != null) {
            if (currentNode.getVital().getId() == updatedVitals.getId()) {
                currentNode.setVital(updatedVitals);
                return;
            }
            currentNode = currentNode.getNext();
        }
    }

    public void deleteVitalsById(long id) {
        if (head == null) {
            return;
        }

        if (head.getVital().getId() == id) {
            head = head.getNext();
            size--;
            return;
        }

        VitalsNode currentNode = head;
        while (currentNode.getNext() != null) {
            if (currentNode.getNext().getVital().getId() == id) {
                currentNode.setNext(currentNode.getNext().getNext());
                size--;
                return;
            }
            currentNode = currentNode.getNext();
        }
    }

    public Vitals getVitalsById(long id) {
        VitalsNode currentNode = head;

        while (currentNode != null) {
            if (currentNode.getVital().getId() == id) {
                return currentNode.getVital();
            }

            currentNode = currentNode.getNext();
        }

        return null; // no Vitals found with the given ID
    }
    
}
