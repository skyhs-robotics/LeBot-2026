package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp

public class TankDrive extends LinearOpMode {
    private DcMotor leftFrontDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightBackDrive = null;
    
    private DcMotor lcr = null;
    private DcMotor rcr = null;
    private DcMotor rcrt = null;
    
    private DcMotor slide = null;
    
    private Servo Lift = null;
    
    private Servo spin = null;
    
    private double mod = 1;
    private boolean modtoggle = false;
    
    private double slide_pos = 0;
    private double lrot_pos = 0;
    private double rrot_pos = 0;
    private int rot_set = 10;
    
    private double a_pow = 0.5;
    
    private int holdPos = 10;

    @Override
    public void runOpMode() {
        leftFrontDrive = hardwareMap.get(DcMotor.class, "front left");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "front right");
        leftBackDrive = hardwareMap.get(DcMotor.class, "back left");
        rightBackDrive = hardwareMap.get(DcMotor.class, "back right");


        // controller = new PIDController(p, i, d);
        // telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstace().getTelemetry());
        
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
       
        
        waitForStart();
        
        while (opModeIsActive()) {
            double lsx = gamepad1.left_stick_x;
            double lsy = gamepad1.left_stick_y;
            
            double rsx = gamepad1.right_stick_x;
            double rsy = gamepad1.right_stick_y;
            
            leftFrontDrive.setPower((lsy - lsx - rsx));
            leftBackDrive.setPower((lsy - lsx + rsx));
            rightFrontDrive.setPower((lsy + lsx + rsx));
            rightBackDrive.setPower((-lsy - lsx + rsx));
            
            telemetry.addData("lsx", lsx);
            
            telemetry.update();
        }
    }
}