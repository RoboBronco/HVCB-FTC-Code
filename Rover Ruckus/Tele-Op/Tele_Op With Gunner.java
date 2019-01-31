package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import java.util.Locale;
import android.graphics.Color;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name="Mecanum: Tele", group="Tele")
public class TeleOp_Mec extends LinearOpMode {

    /* Declare OpMode members. */
   RoverHardware robot = new RoverHardware();

    @Override
    public void runOpMode() {

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("State:", "Ready to Rumble!");    
        telemetry.update();

        robot.lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        telemetry.addData("Initial (closed)",  "Starting at %7d",
                          robot.lift.getCurrentPosition());
        telemetry.addData("Initial drive value",  "Starting at %7d",
                         robot.avgencoderval());
        telemetry.update();
        robot.lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
        int superPowerReduction = 1;
        int powerReduction = 1;
        boolean isUp = false;
        
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            //////////////////////////// DRIVER 1 ------------------ DRIVER 1 //////////////////////

        if(gamepad1.b){
            superPowerReduction = 5;
        }else{
            superPowerReduction = 1;
        }

            if(gamepad1.dpad_up) { // Forwards
                robot.backward();
            }else if(gamepad1.dpad_down){ // Backwards
                robot.forward();
            }else if(gamepad1.dpad_left){ // Strafe Left
                robot.right(.25);
            }else if(gamepad1.dpad_right){ // Strafe Right
                robot.left(.25);
            }else if(gamepad1.dpad_left && gamepad1.dpad_up){ // Diagonal Forward-Left
                robot .forwardLeft();
            }else if(gamepad1.dpad_left && gamepad1.dpad_down){ // Diagonal Back-Left
                robot.backLeft();
            }else if(gamepad1.dpad_right && gamepad1.dpad_up){ //Diagonal Forward-Right
                robot.forwardRight();
            }else if(gamepad1.dpad_right && gamepad1.dpad_down) { //Diagonal Back-Right
                robot.backRight();
            }else if(gamepad1.left_bumper){ //Spin left
                robot.spinLeft(0.25);
            }else if(gamepad1.right_bumper){ //Spin Right
                robot.spinRight(0.25);
            }
            else if(gamepad1.y){ // Stop
                robot.fl.setPower(gamepad1.left_stick_x/2 - gamepad1.left_stick_y/2 + gamepad1.right_stick_x/2);
                robot.fr.setPower(-gamepad1.left_stick_x/2 - gamepad1.left_stick_y/2 - gamepad1.right_stick_x/2);
                robot.bl.setPower(-gamepad1.left_stick_x/2 - gamepad1.left_stick_y/2 + gamepad1.right_stick_x/2);
                robot.br.setPower(gamepad1.left_stick_x/2 - gamepad1.left_stick_y/2 - gamepad1.right_stick_x/2);
            }else{
                robot.fl.setPower(gamepad1.left_stick_x/1.33333 - gamepad1.left_stick_y/1.33333 + gamepad1.right_stick_x/1.33333);
                robot.fr.setPower(-gamepad1.left_stick_x/1.33333 - gamepad1.left_stick_y/1.33333 - gamepad1.right_stick_x/1.33333);
                robot.bl.setPower(-gamepad1.left_stick_x/1.33333 - gamepad1.left_stick_y/1.33333 + gamepad1.right_stick_x/1.33333);
                robot.br.setPower(gamepad1.left_stick_x/1.33333 - gamepad1.left_stick_y/1.33333 - gamepad1.right_stick_x/1.33333);
            }
            
                        //Servo-Harvester Controlls
            if(gamepad1.right_trigger > 0.1){
                robot.collectorHarvesterFlip.setPower(gamepad1.right_trigger);
            }else if(gamepad1.left_trigger > 0.1){
                robot.collectorHarvesterFlip.setPower(-gamepad1.left_trigger);
            }else{
                robot.collectorHarvesterFlip.setPower(0);
            }
             
            //////////////////////////// DRIVER 2 ------------------ DRIVER 2 //////////////////////

        if(gamepad2.b){
            powerReduction = 2;
        }else{
            powerReduction = 1;
        }

        telemetry.addData("Current",  "Total lift rotation: %7d, Total lift accurate: %7d",
                          robot.lift.getCurrentPosition()/1440, robot.lift.getCurrentPosition());
        telemetry.addData("Current",  "Total drive rotation: %7d, Total drive accurate: %7d",
                          robot.avgencoderval()/1440, robot.avgencoderval());
        telemetry.addData("Current",  "Total scoring lift rotation: %7d, Total drive accurate: %7d",
                          robot.verticalDistribution.getCurrentPosition()/1440, robot.verticalDistribution.getCurrentPosition());
        telemetry.update();
        
            // Lift Controls //12/4 changes: Power from 1/-1 to 0.5/-0.5, power from gamepad
            if(gamepad2.dpad_up){ // Lift Up
                robot.lift.setPower(-0.5/powerReduction);
            }else if(gamepad2.dpad_down){ // Lift Down
                robot.lift.setPower(0.5/powerReduction);
            }else{ // Lift Stop
                robot.lift.setPower(0/powerReduction);
            }
            
           
            //Servo-Harvester Controlls
            if(gamepad2.right_trigger > 0.5){
                robot.collector1.setPosition(0);
                robot.collector2.setPosition(1);
            }else if(gamepad2.left_trigger > 0.5){
                robot.collector1.setPosition(1);
                robot.collector2.setPosition(0);
            }else{
                robot.collector1.setPosition(0.5);
                robot.collector2.setPosition(0.5);
            }
                

            if(robot.verticalDistribution.getCurrentPosition() >= 3000){
                isUp = true;
            }else{
                isUp = false;
            }
            
            if(gamepad2.back && (gamepad2.right_stick_y < 0.1) && (gamepad2.right_stick_y > -0.1)){
                isUp = false;
                robot.cupArm.setPosition(0.5);
                robot.verticalDistribution.setPower(-0.2);
                boolean done = false;
                while(!done && opModeIsActive() && !isStopRequested()){
                    if (robot.verticalDistribution.getCurrentPosition() <= 100) {  ////////////////////////// change encoder value
                        robot.verticalDistribution.setPower(0);                        
                        done = true;
                    }
                    telemetry.addData("Scoring Encoder Value is (: ", robot.verticalDistribution.getCurrentPosition());
                    telemetry.update();
                    }

                robot.cupArm.setPosition(0);
            }else if(gamepad2.start && (gamepad2.right_stick_y < 0.1) && (gamepad2.right_stick_y > -0.1)){
                robot.cupArm.setPosition(0.5);
                robot.reset();
                 robot.verticalDistribution.setPower(1);
                boolean done = false;
                while(!done && opModeIsActive() && !isStopRequested()){
                    if (robot.verticalDistribution.getCurrentPosition() >= 4600) {  ////////////////////////// change encoder value
                        done = true;
                    }
                    telemetry.addData("Scoring Encoder Value is (: ", robot.verticalDistribution.getCurrentPosition());
                    telemetry.update();
                    }
                    isUp = true;
                robot.cupArm.setPosition(1);
            }else if((gamepad2.right_stick_y > 0.1) || (gamepad2.right_stick_y < -0.1)){
                robot.verticalDistribution.setPower(-gamepad2.right_stick_y);
            }else if(isUp){
                robot.verticalDistribution.setPower(0.075);
            }else{
                robot.verticalDistribution.setPower(0);
            }
            
            if(gamepad2.left_bumper){
                robot.selector.setPosition(1);
            }else if(gamepad2.right_bumper){
                robot.selector.setPosition(0);
            }else{
                robot.selector.setPosition(0.5);
            }
            
            if(gamepad2.y){
                robot.cupArm.setPosition(1);
            }else if(gamepad2.x || gamepad2.b){
                robot.cupArm.setPosition(0.5);
            }else if(gamepad2.a){
                robot.cupArm.setPosition(0);
            }
            
            robot.collectorArmExtend.setPower(gamepad2.left_stick_y);

        // hsvValues is an array that will hold the hue, saturation, and value information.
        float hsvValues[] = {0F, 0F, 0F};

        // values is a reference to the hsvValues array.
        final float values[] = hsvValues;

        // sometimes it helps to multiply the raw RGB values with a scale factor
        // to amplify/attentuate the measured values.
        final double SCALE_FACTOR = 255;
        
        Color.RGBToHSV((int) (robot.sensorColor.red()),
                    (int) (robot.sensorColor.green()),
                    (int) (robot.sensorColor.blue()),
                    hsvValues);
                    
             // send the info back to driver station using telemetry function.
            telemetry.addData("Alpha", robot.sensorColor.alpha());
            telemetry.addData("Red  ", robot.sensorColor.red());
            telemetry.addData("Green", robot.sensorColor.green());
            telemetry.addData("Blue ", robot.sensorColor.blue());
            telemetry.addData("Hue", hsvValues[0]);

       
        }
    }
}
