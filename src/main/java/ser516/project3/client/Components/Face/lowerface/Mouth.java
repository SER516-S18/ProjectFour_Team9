package ser516.project3.client.Components.Face.lowerface;

import ser516.project3.interfaces.FaceElementsInterface;

import java.awt.geom.Arc2D;

/**
 * This class returns an arc based on the input values and state. Mouth can have
 * states like Smile,clench,smirk and laugh. Input value determines the extent
 * of each state
 *
 * @author Vishakha Singal, Manish Tandon
 */
public class Mouth extends Arc2D.Double implements FaceElementsInterface {

    public enum MouthExpression {
        smile, clench, smirkLeft, smirkRight, laugh
    }

    private MouthExpression mouthExpression = MouthExpression.smile;
    // Constants
    private static final double X_POSITION = 275;
    private static final double Y_POSITION = 225;
    private static final double WIDTH = 100;
    private static final double HEIGHT = 70;
    private static final double ANGLE_START = 298;
    private static final double ANGLE_EXTENT = -50;
    private static final double WIDTH_CLENCH = 50;
    private static final double X_POSITION_CLENCH = 300;
    private static final double START_ANGLE_SMILE = 240;
    private static final double EXTENT_ANGLE_SMILE = 65;
    private static final String SMILE = "smile";
    private static final String CLENCH = "clench";
    private static final String LEFT_SMIRK = "leftSmirk";
    private static final String RIGHT_SMIRK = "rightSmirk";
    private static final String LAUGH = "laugh";

    /**
     * Initialize the mouth part of face
     */
    public Mouth() {
        super();
        setArc(X_POSITION, Y_POSITION, WIDTH, HEIGHT, ANGLE_START, ANGLE_EXTENT, OPEN);
    }

    /**
     * Getter for mouth expression
     *
     * @return
     */
    public MouthExpression getMouthExpression() {
        return mouthExpression;
    }

    /**
     * Setter for mouth expression
     *
     * @param mouthExpression
     */
    public void setMouthExpression(MouthExpression mouthExpression) {
        this.mouthExpression = mouthExpression;
    }

    /**
     * Resets the position of Mouth coordinates to default.
     */
    @Override
    public void resetPositionToDefault() {
        setArc(X_POSITION, Y_POSITION, WIDTH, HEIGHT, ANGLE_START, ANGLE_EXTENT, OPEN);
    }

    /**
     * Moves the mouth to different positions based on the mouth expression flag
     */
    @Override
    public void moveToDifferentPosition() {
        double angleStart = 180;
        double angleEnd = 180;
        switch (mouthExpression) {
            case smile:
                setArc(X_POSITION, Y_POSITION, WIDTH, HEIGHT, angleStart, angleEnd, OPEN);
                break;
            case laugh:
                setArc(X_POSITION, Y_POSITION, WIDTH, HEIGHT, angleStart, angleEnd, CHORD);
                break;
            case clench:
                angleStart = 200;
                angleEnd = 150;
                setArc(X_POSITION, Y_POSITION, WIDTH, HEIGHT, angleStart, angleEnd, CHORD);
                break;
            case smirkLeft:
                angleEnd = 140;
                setArc(X_POSITION, Y_POSITION, WIDTH, HEIGHT, angleStart, angleEnd, OPEN);
                break;
            case smirkRight:
                angleStart = 230;
                angleEnd = 130;
                setArc(X_POSITION, Y_POSITION, WIDTH, HEIGHT, angleStart, angleEnd, OPEN);
                break;
        }

    }

    /**
     * Update the arc based on instruction and change value. Extent of each
     * instruction state depends on the value passed.
     */
    @Override
    public void moveElement(String instruction, double changeValue) {
        if (instruction.equals(LEFT_SMIRK)) {
            double angleExentNew = ANGLE_EXTENT - (changeValue * 50);
            setArc(X_POSITION, Y_POSITION, WIDTH, HEIGHT, ANGLE_START, angleExentNew, OPEN);
        }

        if (instruction.equals(RIGHT_SMIRK)) {
            double angleExentNew = ANGLE_EXTENT - (changeValue * 50);
            double angleStartNew = ANGLE_START + (changeValue * 50);
            setArc(X_POSITION, Y_POSITION, WIDTH, HEIGHT, angleStartNew, angleExentNew, OPEN);
        }
        if (instruction.equals(CLENCH)) {

            double yPositionNew = 270;
            double newWidthClench = WIDTH_CLENCH + (changeValue * 15);
            double xNewPostionClench = X_POSITION_CLENCH - (changeValue * 5.5);
            setArc(xNewPostionClench, yPositionNew, newWidthClench, 30, 0, 360, CHORD);
            if (changeValue == 0) {
                resetPositionToDefault();
            }
        }

        if (instruction.equals(SMILE)) {
            configureFaceForSmile(true, changeValue);
        }
        if (instruction.equals(LAUGH)) {
            configureFaceForSmile(false, changeValue);
        }

    }

    /**
     * Moves the mouth based on the boolean value set on the server.
     */
    @Override
    public void moveElement(String instruction, boolean changeValue) {
        // intentionally blank, no use currently for this, since mouth has double
        // attributes on server.
    }

    /**
     * Configures the face for smile and clench.
     * If is smile is on then shows smile else clench
     *
     * @param isSmile boolean varaible to depict to configure for smile or clench
     * @param changeValue the value with with the change is to be displayed
     */
    public void configureFaceForSmile(boolean isSmile, double changeValue) {

        double angleStartNew = START_ANGLE_SMILE - (changeValue * 30);
        double angleExentNew = EXTENT_ANGLE_SMILE + (changeValue * 55);
        int closure = isSmile ? OPEN : CHORD;
        setArc(X_POSITION, Y_POSITION, WIDTH, HEIGHT, angleStartNew, angleExentNew, closure);
        if (changeValue == 0) {
            resetPositionToDefault();
        }

    }
}
