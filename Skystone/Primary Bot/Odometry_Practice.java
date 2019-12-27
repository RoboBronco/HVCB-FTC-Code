package FTC_2019_2020_Season;
  
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.List;


import android.graphics.Color;

@Autonomous(name = "Odometry Practice", group = "Auto")
public class Odometry_Practice extends LinearOpMode {


    /* Declare OpMode members. */
    RobotHardware robot = new RobotHardware();
    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);
        telemetry.addData("Mode", "calibrating...");
        telemetry.update();

        telemetry.addData("Mode", "calibrating2...");
        telemetry.update();

        while (!isStopRequested() && !robot.imu.isGyroCalibrated()) {
            sleep(50);
            idle();
        }


        // Wait for the game to start (driver presses PLAY)
        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start tracking");
        telemetry.addData("imu calib status", robot.imu.getCalibrationStatus().toString());
        telemetry.update();
        waitForStart();

        robot.resetAngle();


        if (opModeIsActive()) {
            robot.forwardByEncoder(0.5, 8000);
            robot.stop();
            sleep(100);
        // while(opModeIsActive()){
        //     telemetry.addData("Inch Count: ", robot.getLeftYEncoder()/307.699557);
        //     telemetry.update();
        // }
        
        
        
         robot.rightByEncoder(0.5, 5000);
            robot.stop();
            sleep(100);
            
        robot.backwardByEncoder(0.5, 0);
            robot.stop();
            sleep(100);
            
        robot.leftByEncoder(0.5, 0);
            robot.stop();
            sleep(100);
        
        
        }
    }
}
