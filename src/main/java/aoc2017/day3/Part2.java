package aoc2017.day3;


import static aoc2017.day3.Direction.RIGHT;

public class Part2 {

    public static void main(String[] args) {
        int input = 347991;
        Memory memory = new Memory();
        Coordinate lastCoordinate = new Coordinate(0, 0);
        memory.addCoordinateWithValue(lastCoordinate, 1);
        int steps = 1;
        Direction direction = RIGHT;
        while (true) {
            for (int n = 0; n < 2; n++) {
                for (int i = 0; i < steps; i++) {
                    lastCoordinate = memory.generateNext(lastCoordinate, direction);
                    Integer value = Memory.MEMORY.get(lastCoordinate);
                    if(value >= input) {
                        System.out.println(value);
                        throw new RuntimeException();
                    }
                }
                direction = direction.getNext();
            }
            steps++;
        }
    }

}
