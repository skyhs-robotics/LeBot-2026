package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp
public class FtcDecodeV1Teleop extends LinearOpMode {
    private DcMotor leftFrontDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightBackDrive = null;
    
    private DcMotor intakeMotor = null;
    private DcMotor outtakeMotor = null;
    private Servo kickServo = null;
    private DcMotor parkLeft = null;
    private DcMotor parkRight = null;
    
    // Intake: hold button → motor at INTAKE_SPEED
    private static final double INTAKE_SPEED = 0.8;
    // Outtake: constantly running; button toggles between LOW and HIGH power
    private static final double OUTTAKE_POWER_LOW = 0.7;
    private static final double OUTTAKE_POWER_HIGH = 1.0;
    // Kick servo positions (0–1); return to rest when getPosition() is at target (within tolerance)
    private static final double KICK_POSITION = 1.0;
    private static final double KICK_REST_POSITION = 0.0;
    private static final double KICK_POSITION_TOLERANCE = 0.05;
    // Park motors: encoder positions
    private static final int PARK_POSITION_ZERO = 0;
    private static final int PARK_POSITION_TARGET = 1000;
    
    @Override
    public void runOpMode() {
        leftFrontDrive = hardwareMap.get(DcMotor.class, "frontLeft");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "frontRight");
        leftBackDrive = hardwareMap.get(DcMotor.class, "backLeft");
        rightBackDrive = hardwareMap.get(DcMotor.class, "backRight");
        
        intakeMotor = hardwareMap.get(DcMotor.class, "intake");
        outtakeMotor = hardwareMap.get(DcMotor.class, "outtake");
        kickServo = hardwareMap.get(Servo.class, "kick");
        parkLeft = hardwareMap.get(DcMotor.class, "parkLeft");
        parkRight = hardwareMap.get(DcMotor.class, "parkRight");
        
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);
        outtakeMotor.setDirection(DcMotor.Direction.FORWARD);
        parkLeft.setDirection(DcMotor.Direction.FORWARD);
        parkRight.setDirection(DcMotor.Direction.FORWARD);
        parkLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        parkRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        parkLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        parkRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        parkLeft.setTargetPosition(PARK_POSITION_ZERO);
        parkRight.setTargetPosition(PARK_POSITION_ZERO);
        parkLeft.setPower(1.0);
        parkRight.setPower(1.0);
        
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
        
        waitForStart();
        
        // Outtake: toggle between low and high power (button press)
        boolean outtakeHighPower = false;
        boolean lastOuttakeToggle = false;
        boolean lastKickButton = false;
        // Park: one button toggles extended / retracted
        boolean parkExtended = false;
        boolean lastParkButton = false;
        
        while (opModeIsActive()) {
            // ----- INTAKE: hold button → DC motor at INTAKE_SPEED (gamepad2.right_bumper) -----
            if (gamepad2.right_bumper) {
                intakeMotor.setPower(INTAKE_SPEED);
            } else {
                intakeMotor.setPower(0);
            }
            
            // ----- OUTTAKE: always running; button toggles 0.7 vs 1.0 (gamepad2.left_bumper) -----
            if (gamepad2.left_bumper && !lastOuttakeToggle) {
                outtakeHighPower = !outtakeHighPower;
                lastOuttakeToggle = true;
            }
            if (!gamepad2.left_bumper) lastOuttakeToggle = false;
            double outtakePower = outtakeHighPower ? OUTTAKE_POWER_HIGH : OUTTAKE_POWER_LOW;
            outtakeMotor.setPower(outtakePower);
            
            // ----- KICK: Servo to position; return to rest when at target (gamepad2.a) -----
            if (gamepad2.a && !lastKickButton) {
                kickServo.setPosition(KICK_POSITION);
                lastKickButton = true;
            }
            if (!gamepad2.a) lastKickButton = false;
            if (Math.abs(kickServo.getPosition() - KICK_POSITION) < KICK_POSITION_TOLERANCE) {
                kickServo.setPosition(KICK_REST_POSITION);
            }
            
            // ----- PARK (level 2): one button toggles extend/retract (gamepad2.x) -----
            if (gamepad2.x && !lastParkButton) {
                parkExtended = !parkExtended;
                int target = parkExtended ? PARK_POSITION_TARGET : PARK_POSITION_ZERO;
                parkLeft.setTargetPosition(target);
                parkRight.setTargetPosition(target);
                parkLeft.setPower(1.0);
                parkRight.setPower(1.0);
                lastParkButton = true;
            }
            if (!gamepad2.x) lastParkButton = false;
        
            double lsx = -gamepad1.left_stick_x;
            double lsy = gamepad1.left_stick_y;
            
            double rsx = gamepad1.right_stick_x;
            double rsy = gamepad1.right_stick_y;
            
            leftFrontDrive.setPower((lsy - lsx - rsx)*mod);
            leftBackDrive.setPower((lsy - lsx + rsx)*mod);
            rightFrontDrive.setPower((lsy + lsx + rsx)*mod);
            rightBackDrive.setPower((-lsy - lsx + rsx)*mod);
        }
    }
}