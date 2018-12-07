package aoc2017.day3;

enum Direction {
    UP {
        @Override
        public Direction getNext() {
            return LEFT;
        }
    }, LEFT {
        @Override
        public Direction getNext() {
            return DOWN;
        }
    }, DOWN {
        @Override
        public Direction getNext() {
            return RIGHT;
        }
    }, RIGHT {
        @Override
        public Direction getNext() {
            return UP;
        }
    };

    public abstract Direction getNext();
}
