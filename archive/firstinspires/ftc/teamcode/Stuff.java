package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp
public class Stuff extends LinearOpMode {
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
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // servo_move = hardwareMap.servo.get("servoClaw");
        // servo_claw = hardwareMap.servo.get("servoLeft");
        
        waitForStart(); 
        
        while (opModeIsActive())
        {
            double y = gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x; 
        
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx);
            double backLeftPower = (y - x + rx);
            double frontRightPower = (y - x - rx);
            double backRightPower = -(y + x - rx);
            boolean up = gamepad1.y;
            boolean down = gamepad1.a;
        
            // if (up == true){ 
            //     onLeft.setPower(1);
            //     onRight.setPower(-1);
            // }else if (down == true){
            //     onLeft.setPower(-1);
            //     onRight.setPower(1);
            // }else{
            //     onLeft.setPower(0.5);
            //     onRight.setPower(0.5);
            //   }
              
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
        
            frontLeft.setPower(1);
            backLeft.setPower(1);
            frontRight.setPower(1);
            backRight.setPower(1); 
            
            telemetry.addData("RF", frontRightPower);
            telemetry.addData("LF", frontLeftPower);
            telemetry.addData("RB", backRightPower);
            telemetry.addData("LB", backLeftPower);

            telemetry.update();
            
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