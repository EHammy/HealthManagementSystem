package health.beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class RoutinesLinkedList {
    private RoutinesNode head;
    private int size;

    public RoutinesLinkedList() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return size;
    }

    public void addRoutine(Routines routine) {
        RoutinesNode newNode = new RoutinesNode(routine);

        if (head == null) {
            head = newNode;
        } else {
            RoutinesNode currentNode = head;

            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }

            currentNode.setNext(newNode);
        }

        size++;
    }

    public Routines getRoutine(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        RoutinesNode currentNode = head;

        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }

        return currentNode.getRoutine();
    }

    public void removeRoutine(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == 0) {
            head = head.getNext();
        } else {
            RoutinesNode currentNode = head;

            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNext();
            }

            currentNode.setNext(currentNode.getNext().getNext());
        }

        size--;
    }

    public List<Routines> getAllRoutines() {
        List<Routines> routinesList = new ArrayList<>();

        RoutinesNode currentNode = head;
        while (currentNode != null) {
            routinesList.add(currentNode.getRoutine());
            currentNode = currentNode.getNext();
        }

        return routinesList;
    }

    public void updateRoutine(Routines updatedRoutine) {
        RoutinesNode currentNode = head;
        while (currentNode != null) {
            if (currentNode.getRoutine().getId() == updatedRoutine.getId()) {
                currentNode.setRoutine(updatedRoutine);
                return;
            }
            currentNode = currentNode.getNext();
        }
    }

    public void deleteRoutinesById(long id) {
        if (head == null) {
            return;
        }

        if (head.getRoutine().getId() == id) {
            head = head.getNext();
            size--;
            return;
        }

        RoutinesNode currentNode = head;
        while (currentNode.getNext() != null) {
            if (currentNode.getNext().getRoutine().getId() == id) {
                currentNode.setNext(currentNode.getNext().getNext());
                size--;
                return;
            }
            currentNode = currentNode.getNext();
        }
    }

    public Routines getRoutinesById(long id) {
        RoutinesNode currentNode = head;

        while (currentNode != null) {
            if (currentNode.getRoutine().getId() == id) {
                return currentNode.getRoutine();
            }

            currentNode = currentNode.getNext();
        }

        return null; // no Vitals found with the given ID
    }
    public List<Routines> searchRoutineByName(String routineName) {
        List<Routines> matchedRoutines = new ArrayList<>();

        RoutinesNode currentNode = head;
        while (currentNode != null) {
            if (currentNode.getRoutine().getRoutineName().equalsIgnoreCase(routineName)) {
                matchedRoutines.add(currentNode.getRoutine());
            }
            currentNode = currentNode.getNext();
        }

        // Sort the matched routines by routine name using bubble sort algorithm
        boolean isSwapped;
        int size = matchedRoutines.size();
        for (int i = 0; i < size - 1; i++) {
            isSwapped = false;
            for (int j = 0; j < size - i - 1; j++) {
                if (matchedRoutines.get(j).getRoutineName().compareToIgnoreCase(matchedRoutines.get(j + 1).getRoutineName()) > 0) {
                    Routines tempRoutine = matchedRoutines.get(j);
                    matchedRoutines.set(j, matchedRoutines.get(j + 1));
                    matchedRoutines.set(j + 1, tempRoutine);
                    isSwapped = true;
                }
            }
            if (!isSwapped) {
                break;
            }
        }

        return matchedRoutines;
    }


}
