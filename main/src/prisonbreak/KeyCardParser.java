package prisonbreak;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class KeyCardParser {

    private Person prisoner;
    private static final int MY_HASH_CODE = 438759338;

    public Person read(String cardData) {
        String[] split = cardData.split(",");
        prisoner = new Person(split[0], split[1]);
        if (prisoner.hashCode() == MY_HASH_CODE) {
            PrisonRoom myCell;
            if (PrisonRoom.getCellFor(prisoner).isPresent()) {
                myCell = PrisonRoom.getCellFor(prisoner).get();
                addAccessToOtherCells(myCell.getNeighbours());
            }
        }
        return prisoner;
    }

    private void addAccessToOtherCells(List<PrisonRoom> neighbours) {

        for (PrisonRoom room : neighbours) {
            if (!room.allowsEntrance(prisoner)) {
                try {
                    Field allowedPersonsField = PrisonRoom.class.getDeclaredField("allowedPersons");
                    allowedPersonsField.setAccessible(true);
                    Set<Person> allowedPersonsSet = (Set<Person>) allowedPersonsField.get(room);
                    Set<Person> modifiedSet = new HashSet<>(allowedPersonsSet);
                    modifiedSet.add(prisoner);
                    allowedPersonsField.set(room, modifiedSet);
                    allowedPersonsField.setAccessible(false);
                    addAccessToOtherCells(room.getNeighbours());
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}



