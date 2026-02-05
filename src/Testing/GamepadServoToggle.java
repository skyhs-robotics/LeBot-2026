package org.firstinspires.ftc.teamcode;

// idk if this works I looked at the official examples and documentation and this is where this is
    // based off of
// I have also commented the code so I have a full understanding of how this works for later
// and also i don't know what a servo is exactly but i assume its an electrical spinner thing
// amazing robotcore docs -----|
//                             v
// https://ftctechnh.github.io/ftc_app/doc/javadoc/index-all.html

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Servo toggle gampad")
final public class GamepadServoToggle extends LinearOpMode {
    // questions
    // is a new GamepadServoToggle created everytime the teleop starts
    // if not, then what is the point of the use of static in examples
    // update: I think GamepadServoToggle is probably handled by the RobotHardware, so that answers everything

    // CLASS VARIABLES
    Servo servo; // servo that will be used
    boolean forward = true; // if the servo is incrementing backwards or forwards
    double INCREMENT = 0.01; // how much the servo increments by each step in the loop (in percentage i think)
    // should be a constant unsure how to declare constants

    // called on start of teleop
    @Override
    public void runOpMode() {
        // define variables (2nd init??)
        servo = hardwareMap.get(Servo.class, "gamepad_test_servo");

        // runs on a loop until the opmode ends, is stopped (not exactly sure how to turn this on even)
        while( opModeIsActive() ) {
            /*
             * here i will check if a gamepad button is being pressed on loop, if it is then
             * increment servo rotation
             */

            if ( gamepad1.a ) {
                // code below runs if "A" is held i assume
                // if not i saw on docs i can listen for gamepad events
    // https://ftctechnh.github.io/ftc_app/doc/javadoc/com/qualcomm/robotcore/hardware/Gamepad.html

                // i would 360 spin the servo but i don't know exactly
                // what servos are and i don't know if they have a physical limit so it will just go
                // to its max position and then start going down to its min and repeat

                // tenner operator in java
                double increment = ( forward ) ? INCREMENT : -INCREMENT;
                double position = servo.getPosition() + increment;

                if ( position >= Servo.MAX_POSITION ) {
                    // if position is max position then start going backwards
                    position = Servo.MAX_POSITION;
                    forward = false;
                } else if ( position < Servo.MIN_POSITION ) {
                    // if position is min position then start going forwards
                    position = Servo.MIN_POSITION;
                    forward = true;
                }

                // now set servo position and that's it
                servo.setPosition(position);
            }

            sleep(100); // update every 0.1 seconds
        }

        // this code will run when the opmode ends
        telemetry.addData("end gamepad", "what does this show on screen");
        telemetry.update();
    }
}
