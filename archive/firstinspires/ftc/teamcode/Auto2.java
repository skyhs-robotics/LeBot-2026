// package org.firstinspires.ftc.teamcode;

// import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
// import com.qualcomm.robotcore.hardware.DcMotor;
// import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

// @Autonomous

// public class Auto2 extends LinearOpMode {
//     private DcMotor leftFrontDrive = null;
//     private DcMotor rightFrontDrive = null;
//     private DcMotor leftBackDrive = null;
//     private DcMotor rightBackDrive = null;
    
//     private DcMotor slide = null;
    
//     private double mod = 0;
//     private boolean modtoggle = false;
    
//     @Override
//     public void runOpMode() {
//         leftFrontDrive = hardwareMap.get(DcMotor.class, "front left");
//         rightFrontDrive = hardwareMap.get(DcMotor.class, "front right");
//         leftBackDrive = hardwareMap.get(DcMotor.class, "back left");
//         rightBackDrive = hardwareMap.get(DcMotor.class, "back right");
        
//         slide = hardwareMap.get(DcMotor.class, "slide");
        
//         leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
//         leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
//         rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
//         rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
        
//         slide.setDirection(DcMotor.Direction.FORWARD);
        
//         slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//         slide.setMode(DcMotor.RunMode.RUN_USING_ENCORDER);
        
//         waitForStart();
//     }
// }