package com.alm.service;


import com.alm.model.AutomaticLawnMower;
import com.alm.model.Lawn;
import com.alm.model.Orientation;
import com.alm.model.Position;

public class AutomaticLawnMowerServiceImpl implements AutomaticLawnMowerService {

    @Override
    public AutomaticLawnMower execute(AutomaticLawnMower automaticLawnMower, String instructionsStr) {
        Orientation newOrientation;
        char[] charArray = instructionsStr.toCharArray();
        for (char c : charArray) {
            switch (c) {
                case 'A':
                    move(automaticLawnMower);
                    break;
                case 'D':
                    newOrientation = pivotRight(automaticLawnMower.getOrientation());
                    automaticLawnMower.setOrientation(newOrientation);
                    break;
                case 'G':
                    newOrientation = pivotLeft(automaticLawnMower.getOrientation());
                    automaticLawnMower.setOrientation(newOrientation);
                    break;
                default:
                    throw new IllegalArgumentException("Instruction should use only A or D or G");
            }
        }
        return automaticLawnMower;
    }

    private void move(AutomaticLawnMower automaticLawnMower) {
        int x = automaticLawnMower.getPosition().getX();
        int y = automaticLawnMower.getPosition().getY();
        switch (automaticLawnMower.getOrientation()) {
            case E: {
                x++;
                break;
            }
            case N: {
                y++;
                break;
            }
            case S: {
                y--;
                break;
            }
            case W: {
                x--;
                break;
            }
            default:
                break;

        }
        if (isPositionInsideLawn(automaticLawnMower.getLawn(), x, y)) {
            automaticLawnMower.setPosition(new Position(x, y));
        }
    }

    private Orientation pivotRight(Orientation orientation) {
        return switch (orientation) {
            case N -> Orientation.E;
            case E -> Orientation.S;
            case S -> Orientation.W;
            case W -> Orientation.N;
        };
    }


    private Orientation pivotLeft(Orientation orientation) {
        return switch (orientation) {
            case N -> Orientation.W;
            case E -> Orientation.N;
            case S -> Orientation.E;
            case W -> Orientation.S;
        };
    }

    private boolean isPositionInsideLawn(Lawn lawn, int x, int y) {
        return x >= 0 && x <= lawn.width() && y >= 0 && y <= lawn.height();
    }
}