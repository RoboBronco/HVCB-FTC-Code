package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import java.util.Locale;
import android.graphics.Color;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;



@TeleOp(name="Mecanum: OFFICIAL", group="Tele")

public class TeleOp_Mec_Official extends LinearOpMode {
   RoverHardware robot = new RoverHardware();
   private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        
        robot.init(hardwareMap);
        robot.lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
        int superPowerReduction = 1;
        int powerReduction = 1;
        boolean isUp = false;
        
        waitForStart();
        runtime.reset();
        while (opModeIsActive()) {
            ////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////LIGHTS//////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////
            if (runtime.seconds() <= 2){
                robot.Yellow();
                // robot.Purple();
            } else{
                robot.Red();
                // robot.Yellow();
            }
            
            //////////////////////////// DRIVER 1 ------------------ DRIVER 1 //////////////////////

        if(gamepad1.b){
            superPowerReduction = 5;
        }else{
            superPowerReduction = 1;
        }

            if(gamepad1.dpad_up) {
                robot.backward();
            }else if(gamepad1.dpad_down){
                robot.forward();
            }else if(gamepad1.dpad_left){
                robot.right(.5);
            }else if(gamepad1.dpad_right){
                robot.left(.5);
            }else if(gamepad1.left_bumper){
                robot.spinLeft(0.25);
            }else if(gamepad1.right_bumper){
                robot.spinRight(0.25);
            }
            else if(gamepad1.y){
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
      
            if(gamepad2.dpad_up){
                robot.lift.setPower(-0.5/powerReduction);
            }else if(gamepad2.dpad_down){
                robot.lift.setPower(0.5/powerReduction);
            }else{
                robot.lift.setPower(0/powerReduction);
            }
            
          
            if(gamepad2.right_trigger > 0.5){
                robot.collector1.setPosition(1);
                robot.collector2.setPosition(0);
            }else if(gamepad2.left_trigger > 0.5){
                robot.collector1.setPosition(0);
                robot.collector2.setPosition(1);
            }else{
                robot.collector1.setPosition(0.5);
                robot.collector2.setPosition(0.5);
            }
            
            if(gamepad1.y){
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
            
            
                
             if((gamepad2.right_stick_y > 0.1) || (gamepad2.right_stick_y < -0.1)){
                robot.verticalDistribution.setPower(-gamepad2.right_stick_y);
             }else{
                robot.verticalDistribution.setPower(0.075);
             }
            
            
            
            
            if(gamepad1.y){
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
       }
      }
     }
