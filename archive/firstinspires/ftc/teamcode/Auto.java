package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class Auto extends LinearOpMode {
    DcMotor frontLeft; 
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor onLeft;
    DcMotor onRight;
    Servo servo_move;
    Servo servo_claw;
    
    public void runOpMode() throws InterruptedException
    {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");   
        onLeft = hardwareMap.dcMotor.get("onLeft");
        onRight = hardwareMap.dcMotor.get("onRight");
        // servo_move = hardwareMap.servo.get("servoClaw");
        // servo_claw = hardwareMap.servo.get("servoLeft");
        
        waitForStart(); 
        
        while (opModeIsActive())
        {
        
            double frontLeftPower = 1;
            double backLeftPower = -1;
            double frontRightPower = -1;
            double backRightPower = -1;
            boolean up = gamepad1.y;
            boolean down = gamepad1.a;
        
            if (up == true){ 
                onLeft.setPower(1);
                onRight.setPower(-1);
            }else if (down == true){
                onLeft.setPower(-1);
                onRight.setPower(1);
            }else{
                onLeft.setPower(0.5);
                onRight.setPower(0.5);
              }
              
            //  boolean servo_up = gamepad1.x;
            //  boolean servo_down = gamepad1.b;
             
            //  if (servo_up == true)
            //  {
            //      servo_move.setPosition(servo_move.getPosition() + 0.005);
            //  }
            //  else if (servo_down == true)
            //  {
            //      servo_move.setPosition(servo_move.getPosition() - 0.005);
            //  }
              
            frontLeft.setPower(frontLeftPower);
            backLeft.setPower(backLeftPower);
            frontRight.setPower(frontRightPower);
            backRight.setPower(backRightPower);   
            
            // John here reading this, it may be the worst code ive ever read
            // boolean servo_open = gamepad1.left_trigger > 0;
            // boolean servo_close = gamepad1.right_trigger > 0;
            // if (servo_open == true)
            //  {
            //      servo_claw.setPosition(servo_claw.getPosition() + 0.005);
            //  } 
            //  else if (servo_close == true)
            //  {
            //      servo_claw.setPosition(servo_claw.getPosition() - 0.005);
            //  }
        }
    }

}