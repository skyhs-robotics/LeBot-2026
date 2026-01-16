package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp

public class TestMotor extends LinearOpMode {
    private DcMotor testMotor = null;
    // todo: write your code here
    
    public void runOpMode() {
        
        testMotor = hardwareMap.get(DcMotor.class, "testMotor");
        testMotor.setDirection(DcMotor.Direction.FORWARD);
        
        testMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        testMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        waitForStart();
        
        while (opModeIsActive()) {
            testMotor.setPower(1);
        }
        
       
    }
}