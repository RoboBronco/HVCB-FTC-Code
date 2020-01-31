package FTC_2019_2020_Season;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import java.util.Locale;
import android.graphics.Color;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Teleop New Robot", group="Tele")

public class TeleOp_Mec_Official_NewRobot extends LinearOpMode {
   RobotHardware robot = new RobotHardware();
   private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
    
         robot.init(hardwareMap);
        sleep(500);
        
        int superPowerReduction = 1;
        int powerReduction = 1;
        double level = -1; /// was 0.66
        double twist = 0.47;
        double manual = 0;
        boolean isUp = false;
        
        boolean spinIsTrueForward = true;
        boolean spinIsTrueBackward = true;
        boolean clampIsTrue = true;
        
        waitForStart();
        runtime.reset();
        while (opModeIsActive()) {
            

            robot.sensorColor0.enableLed(false);
            robot.sensorColor1.enableLed(false);

            //////////////////////////// DRIVER 1 ------------------ DRIVER 1 //////////////////////
            
            if(gamepad1.b){
                superPowerReduction = 5;
            }else{
                superPowerReduction = 1;
            }
            
            if(gamepad1.b){
                // robot.hook0.setPosition(0.45);
                // robot.hook1.setPosition(0.45);
                robot.hook0.setPosition(0.85);
                robot.hook1.setPosition(0.25); //Was 1

            }else if(gamepad1.a){
                robot.hook0.setPosition(0);//Was 0.6 
                robot.hook1.setPosition(0.9);// Was -0.8
            }else if(gamepad1.dpad_up) {
                robot.forward(.25);
            }else if(gamepad1.dpad_down){
                robot.backward(.25);
            }else if(gamepad1.dpad_left){
                robot.left(.25);
            }else if(gamepad1.dpad_right){
                robot.right(.25);
            }else if(gamepad1.left_bumper){
                robot.spinRight(0.25);
            }else if(gamepad1.right_bumper){
                robot.spinLeft(0.25);
            }
            else if(gamepad1.y){
                robot.fl.setPower(-gamepad1.left_stick_x/2 + gamepad1.left_stick_y/2 - gamepad1.right_stick_x/2);
                robot.fr.setPower(gamepad1.left_stick_x/2 + gamepad1.left_stick_y/2 + gamepad1.right_stick_x/2);
                robot.bl.setPower(gamepad1.left_stick_x/2 + gamepad1.left_stick_y/2 - gamepad1.right_stick_x/2);
                robot.br.setPower(-gamepad1.left_stick_x/2 + gamepad1.left_stick_y/2 + gamepad1.right_stick_x/2);
            }else{
                robot.fl.setPower(-gamepad1.left_stick_x/1.33333 + gamepad1.left_stick_y/1.33333 - gamepad1.right_stick_x/1.33333);
                robot.fr.setPower(gamepad1.left_stick_x/1.33333 + gamepad1.left_stick_y/1.33333 + gamepad1.right_stick_x/1.33333);
                robot.bl.setPower(gamepad1.left_stick_x/1.33333 + gamepad1.left_stick_y/1.33333 - gamepad1.right_stick_x/1.33333);
                robot.br.setPower(-gamepad1.left_stick_x/1.33333 + gamepad1.left_stick_y/1.33333 + gamepad1.right_stick_x/1.33333);
            }
            
            telemetry.addData("X:", robot.fr.getCurrentPosition());
            telemetry.addData("leftY", robot.getLeftYEncoder());
            telemetry.addData("rightY", robot.getRightYEncoder());
            telemetry.update();
            
            
            //////////////////////////// DRIVER 2 ------------------ DRIVER 2 //////////////////////
                
            // rearRamp.setPosition(0.42); /////////  0.375 level
             if (gamepad2.left_trigger > 0.01){
                  robot.rearRamp.setPosition(0.55);
            }else {
                robot.rearRamp.setPosition(0.375); ////// was 42 sleigt down
            }
            
            /////////////////////////////////////////////////////////////////////////////////////////////
            //////////////SAMMIE UPDATED - Lift will completely follow stick, -100% to 100%//////////////
            ///////////////////////If it is too fast just divide each by a constant//////////////////////
            //////////////(i.e. gamepad2.right_stick_y/2 for 50%) - Deadzone not needed//////////////////
            /////////////////////////////////////////////////////////////////////////////////////////////
            robot.Lelevator.setPower(-gamepad2.right_stick_y);
            robot.Relevator.setPower(gamepad2.right_stick_y);
            
            // if (Math.abs(gamepad2.right_stick_y) > 0.5){ // was 0.05
            //     robot.Lelevator.setPower(-gamepad2.right_stick_y);
            //     robot.Relevator.setPower(gamepad2.right_stick_y);
            // }else{
            //     robot.Lelevator.setPower(0);
            //     robot.Relevator.setPower(0);
            // }
            
            
             if (gamepad2.a){
                 if (clampIsTrue){
                    robot.frontGate.setPosition(0.8); /////// open 0.3
                    clampIsTrue = false;
                 } else {
                    robot.frontGate.setPosition(0.3); ///// close 0.8
                    clampIsTrue = true;
                 }
                 sleep(200);
             }
            
            
            //Intake
             if (gamepad2.right_bumper){
                 if (spinIsTrueForward){
                    robot.Rintake.setPower(0.4);
                    robot.Lintake.setPower(-0.4);
                    robot.secondaryRintake.setPower(-0.4);
                    robot.secondaryLintake.setPower(0.4);
                    spinIsTrueForward = false;
                 } else {
                    robot.Rintake.setPower(0);
                    robot.Lintake.setPower(0);
                    robot.secondaryRintake.setPower(0);
                    robot.secondaryLintake.setPower(0);
                    spinIsTrueForward = true;
                 }
                 sleep(200);
             }
             
             
            ////Left bumper ejects blocks out the back
               if (gamepad2.left_bumper){
                 if (spinIsTrueBackward){
                    robot.Rintake.setPower(-0.2);
                    robot.Lintake.setPower(0.2);
                    robot.secondaryRintake.setPower(0.2);
                    robot.secondaryLintake.setPower(-0.2);
                    robot.frontGate.setPosition(0.8); //kick
                    spinIsTrueBackward = false;
                 } else {
                    robot.Rintake.setPower(0);
                    robot.Lintake.setPower(0);
                    robot.secondaryRintake.setPower(0);
                    robot.secondaryLintake.setPower(0);
                    robot.frontGate.setPosition(0.3); //open
                    spinIsTrueBackward = true;
                 }
                 sleep(200);
             }
            
            // if (gamepad2.left_trigger > 0.01){
            //     robot.Rintake.setPower(-1);
            //     robot.Lintake.setPower(1);
            // }
            
            // else if (Math.abs(gamepad2.right_stick_y) > 0.05){
            //       robot.headExtend.setPower(gamepad2.right_stick_y);
            //   }
            
            //   if (gamepad2.right_bumper){
            //       robot.Lintake.setPower(-0.6);
            //       robot.Rintake.setPower(0.6);
                  
            //       robot.frontGate.setPosition(0.3);/// open 
            //   }
            //   if (gamepad2.left_bumper){
            //       robot.Lintake.setPower(0);
            //       robot.Rintake.setPower(0);
                  
            //      robot.frontGate.setPosition(0.8);/// close 
            //   }
              
            //   if (gamepad2.y){
            //       while (robot.Lelevator.getCurrentPosition() < 1000 && robot.Relevator.getCurrentPosition() < 1000 ){
            //             robot.Lelevator.setPower(0.25); //// up
            //             robot.Relevator.setPower(-0.25); //// up
            //       }
                  
            //       robot.rearRamp.setPosition(0.55);/// Dump
            //       robot.frontGate.setPosition(1);/// Kick
                  
            //   }
              
            //  if (gamepad2.a){
            //       robot.frontGate.setPosition(0.3);/// Open
            // }
            //   if (gamepad2.x){
            //      robot.releaseCap.setPosition(1);///// Release
            // }
            
            //  if (gamepad2.b){
            //      while (robot.Lelevator.getCurrentPosition() < 20 && robot.Relevator.getCurrentPosition() < 20 ){
            //             robot.Lelevator.setPower(-0.25);  /// down
            //             robot.Relevator.setPower(0.25);  //// down
            //      }
            //      robot.Lelevator.setPower(0);  /// off
            //      robot.Relevator.setPower(0); //// off
            //      // robot.frontGate.setPosition();/// Open
            //       // robot.rearRamp.setPosition();/// level
                 
            //     /// robot.headExtend = in     /// not sure how

            // }
          
            
            //  if (gamepad2.dpad_up){
            //     robot.headExtend.setPower(0.5);
            // } else if (gamepad2.dpad_down){
            //     robot.headExtend.setPower(-0.5);
            // }else {
            //     robot.headExtend.setPower(0);
            // }
            
            
             if (Math.abs(gamepad2.left_stick_y) > 0.05){
                  robot.headExtend.setPower(gamepad2.left_stick_y);
            }else {
                robot.headExtend.setPower(0);
            }
            
            // if (gamepad2.right_trigger > 0.01){
            //     if (robot.Lelevator.getCurrentPosition() > 70){
            //       while (robot.Lelevator.getCurrentPosition() < 70){
            //       robot.Lelevator.setPower(-1);
            //       robot.Relevator.setPower(1);
            //       }
            //     }
            //     else {
            //         while (robot.Lelevator.getCurrentPosition() < 70){
            //         robot.Lelevator.setPower(1);
            //         robot.Relevator.setPower(-1);                        
            //         }
            //     }
            // }
            
            
            telemetry.addData("Left elavator encoder position:", robot.Lelevator.getCurrentPosition()); 
            telemetry.update();
    
                //////////////////////////// DRIVER 1 &&&&&&&&&&&&&&&&& DRIVER 2 //////////////////////
    
                if(gamepad1.x && gamepad2.x){
                    robot.releaseCap.setPosition(-1);    //NUKE
                }
                
            
        
       }
      }
     }
