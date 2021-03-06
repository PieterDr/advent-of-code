package util;

public enum Direction {
    UP {
        @Override
        public Direction opposite() {
            return DOWN;
        }

        @Override
        public Direction turnLeft() {
            return LEFT;
        }

        @Override
        public Direction turnRight() {
            return RIGHT;
        }
    }, DOWN {
        @Override
        public Direction opposite() {
            return UP;
        }

        @Override
        public Direction turnLeft() {
            return RIGHT;
        }

        @Override
        public Direction turnRight() {
            return LEFT;
        }
    }, LEFT {
        @Override
        public Direction opposite() {
            return RIGHT;
        }

        @Override
        public Direction turnLeft() {
            return DOWN;
        }

        @Override
        public Direction turnRight() {
            return UP;
        }
    }, RIGHT {
        @Override
        public Direction opposite() {
            return LEFT;
        }

        @Override
        public Direction turnLeft() {
            return UP;
        }

        @Override
        public Direction turnRight() {
            return DOWN;
        }
    };

    public static Direction of(char c) {
        switch (c) {
            case 'U': return UP;
            case 'D': return DOWN;
            case 'L': return LEFT;
            case 'R': return RIGHT;
            default: throw new IllegalArgumentException("Invalid direction: " + c);
        }
    }

    public abstract Direction opposite();

    public abstract Direction turnLeft();

    public abstract Direction turnRight();

}
