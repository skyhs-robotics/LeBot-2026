package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.util.ArrayList;

@TeleOp
public class TestToggleButtonServo extends LinearOpMode {
    private DcMotor leftFrontDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightBackDrive = null;
    
    private DcMotor vert_extend = null;
    private DcMotor horz_extend = null;
    
    private Servo clearance_srv = null;
    private CRServo harvester_srv = null;
    private CRServo specimen_srv = null;
    
    private double mod = 0;
    private boolean modtoggle = false;
    
    private ArrayList<Integer> position_changes;
    int target_size = 5;
    
    // private PIDController controller;
    
    // public static double p = 0, i = 0, d = 0;
    // public static double f = 0;
    
    // public static int target = 0;
    
    // private final double ticks_in_degree = 700 / 180.0;

    @Override
    public void runOpMode() {
        leftFrontDrive = hardwareMap.get(DcMotor.class, "front left");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "front right");
        leftBackDrive = hardwareMap.get(DcMotor.class, "back left");
        rightBackDrive = hardwareMap.get(DcMotor.class, "back right");
        
        vert_extend = hardwareMap.get(DcMotor.class, "vertical");
        horz_extend = hardwareMap.get(DcMotor.class, "horizontal");
        
        clearance_srv = hardwareMap.get(Servo.class, "clearance");
        harvester_srv = hardwareMap.get(CRServo.class, "harvester");
        specimen_srv = hardwareMap.get(CRServo.class, "specimen");


        // controller = new PIDController(p, i, d)
        // telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstace().getTelemetry());
        
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
        
        vert_extend.setDirection(DcMotor.Direction.FORWARD);
        
        vert_extend.setTargetPosition(0);
        vert_extend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        vert_extend.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        horz_extend.setDirection(DcMotor.Direction.FORWARD);
        
        horz_extend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        horz_extend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
        waitForStart();
        
        boolean gp_up = true;
        boolean gp_down = true;
        
        boolean gp_out = true;
        boolean gp_in = true;
        
        boolean gp_lt = true;
        
        int last_position = 0;
        
        ElapsedTime stopwatch = new ElapsedTime();
        position_changes = new ArrayList<Integer>();
        
        while (opModeIsActive()) {
            if (modtoggle) {
                mod = 0.5;
            } else if (!modtoggle) { mod = 1; }
            
            if (gamepad1.left_trigger > 0 && gp_lt) {
                modtoggle = !modtoggle;
                gp_lt = false;
            } 
            
            if (gamepad1.left_trigger == 0 && !gp_lt)
            {
                gp_lt = true;
            }
            
            if (gamepad1.right_trigger > 0)
            {
                mod = 0.3;
            }
            
           // Vert extend
            
            if (gamepad1.dpad_up)
            {
                vert_extend.setPower(1);
                vert_extend.setTargetPosition(1000);
            }
            else if (gamepad1.dpad_down)
            {
                vert_extend.setPower(1);
                vert_extend.setTargetPosition(0);
            }
            
            if (stopwatch.time() > 0.005)
            {
                if (position_changes.size() == target_size)
                {
                    position_changes.remove(0);
                }
                
                position_changes.add((vert_extend.getCurrentPosition() - last_position));
                
                int average_position = 0;
                
                for (Integer delta_change : position_changes)
                {
                    average_position += delta_change;
                }
                
                telemetry.addData("average", average_position / position_changes.size());
                last_position = vert_extend.getCurrentPosition();
                telemetry.update();

                stopwatch.reset();
            }
            
            
            // else 
            // {
            //     vert_extend.setPower(0);
            // }
            
            // Horizontal extned
            
            if (gamepad1.dpad_right)
            {
                horz_extend.setPower(0.5);
            }
            else if (gamepad1.dpad_left)
            {
                horz_extend.setPower(-0.5);
            }
            else 
            {
                horz_extend.setPower(0);
            }
            
            if (gamepad1.y)
            {
                specimen_srv.setPower(1);
            }
            else if (gamepad1.x)
            {
                specimen_srv.setPower(-1);
            }
            else
            {
                specimen_srv.setPower(0);
            }
            
            
            
            if (gamepad1.left_bumper)
            {
                harvester_srv.setPower(1);
            }
            else if (gamepad1.right_bumper)
            {
                harvester_srv.setPower(-1);
            }
            else
            {
                harvester_srv.setPower(0);
            }
            
            if (gamepad1.a)
            {
                clearance_srv.setPosition(1);
            }
            else if (gamepad1.b)
            {
                clearance_srv.setPosition(0);
            }
        
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