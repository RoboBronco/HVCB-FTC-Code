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
    
       // robot.initNoInitBecauseISFunAndNoMove(hardwareMap);
         robot.init(hardwareMap);
        
        
        int superPowerReduction = 1;
        int powerReduction = 1;
        double level = -1; /// was 0.66
        double twist = 0.47;
        double manual = 0;
        boolean isUp = false;
         
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
                // robot.hook0.setPosition(-0.45);
                // robot.hook1.setPosition(0.6);
                robot.hook0.setPosition(-0.3);
                robot.hook1.setPosition(0.45);
            }else if(gamepad1.a){
                // robot.hook0.setPosition(0.5); // Was 1 
                // robot.hook1.setPosition(-1);
                robot.hook0.setPosition(0.6);//Was 0.6 
                robot.hook1.setPosition(-0.1);// Was -0.8
            }else if(gamepad1.dpad_up) {
                robot.forward(.25);
            }else if(gamepad1.dpad_down){
                robot.backward(.25);
            }else if(gamepad1.dpad_left){
                robot.left(.25);
            }else if(gamepad1.dpad_right){
                robot.right(.25);
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
            
            telemetry.addData("X:", robot.fr.getCurrentPosition());
            telemetry.addData("leftY", robot.getLeftYEncoder());
            telemetry.addData("rightY", robot.getRightYEncoder());
            telemetry.update();
            
            
            //////////////////////////// DRIVER 2 ------------------ DRIVER 2 //////////////////////
                
            //This is newstuff
            if (gamepad.left_stick_y > 0)
            
            //this is end of new stuff
                                                                              
            if (Math.abs(gamepad2.left_stick_y) > 0.05){
                  robot.Lelevator.setPower(gamepad2.left_stick_y);
                  robot.Relevator.setPower(-gamepad2.left_stick_y);
            }
            else if (Math.abs(gamepad2.right_stick_y) > 0.05){
                  robot.headExtend.setPower(gamepad2.right_stick_y);
              }
            
              if (gamepad2.right_bumper){
                  robot.Lintake.setPower(-0.6);
                  robot.Rintake.setPower(0.6);
                  
                  //robot.frontGate.setPosition();/// open 
              }
              if (gamepad2.left_bumper){
                  robot.Lintake.setPower(0);
                  robot.Rintake.setPower(0);
                  
                 // robot.frontGate.setPosition();/// close 
              }
              
              if (gamepad2.y){
                  while (robot.Lelevator.getCurrentPosition() < 1000 && robot.Relevator.getCurrentPosition() < 1000 ){
                        robot.Lelevator.setPower(0.25); //// up
                        robot.Relevator.setPower(-0.25); //// up
                  }
                 // robot.frontGate.setPosition();/// Kick
                 // robot.rearRamp.setPosition();/// Dump
              }
              
             if (gamepad2.a){
                 // robot.frontGate.setPosition();/// Open
            }
              if (gamepad2.x){
                 //robot.releaseCap.setPosition();///// Release
            }
            
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
          
            
             if (gamepad2.dpad_up){
                robot.headExtend.setPower(0.55);
            } else if (gamepad2.dpad_down){
                robot.headExtend.setPower(0.45);
            }
            
    
    
                //////////////////////////// DRIVER 1 &&&&&&&&&&&&&&&&& DRIVER 2 //////////////////////
    
                if(gamepad1.x && gamepad2.x){
                    robot.releaseCap.setPosition(-1);    //NUKE
                }
                
            
        
       }
      }
     }
