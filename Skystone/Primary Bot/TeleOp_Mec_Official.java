package FTC_2019_2020_Season;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import java.util.Locale;
import android.graphics.Color;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Teleop", group="Tele")

public class TeleOp_Mec_Official extends LinearOpMode {
   RobotHardware robot = new RobotHardware();
   private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        
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
            
            if ((gamepad1.left_trigger > 0.25) && (robot.ssScrew.getCurrentPosition() >= 0)){
                robot.ssScrew.setPower(-(gamepad1.left_trigger/2));
            }if ((gamepad1.right_trigger > 0.25) && (robot.ssScrew.getCurrentPosition() <= 2230)){
                robot.ssScrew.setPower(gamepad1.right_trigger);   
            }else{
                robot.ssScrew.setPower(0);
            }
            
            if(gamepad1.b){
                robot.hook0.setPosition(-0.6);
                robot.hook1.setPosition(0.6);
            }else if(gamepad1.a){
                robot.hook0.setPosition(1);
                robot.hook1.setPosition(-1);
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
            //
            //
            //
            //                                                           
            //////////////////////////// DRIVER 2 ------------------ DRIVER 2 //////////////////////
                                                                          
            if(gamepad2.left_bumper){                                    
                twist = twist + 0.01;                           
            }else if(gamepad2.right_bumper){                            
                twist = twist - 0.01;                        
            }
            
            if (gamepad2.y){
                manual++;
            }
            
            if (manual % 2 == 1){
                if (gamepad2.left_trigger > .5){                              
                    level = level-0.01;
                    robot.ssTilt.setPosition(level);                         
                }else if (gamepad2.right_trigger > .5){                         
                    level = level+0.01;
                    robot.ssTilt.setPosition(level);
                }
            }else{
                robot.ssTilt.setPosition(((robot.ssScrew.getCurrentPosition()/50)*0.0073)+0.80);
                level = 0.75;
            }
            
            if (gamepad2.a){                                
                robot.ssClaw.setPosition(1);                    
            }else if (gamepad2.b){                              
                robot.ssClaw.setPosition(0.55);                 
            }                                                 
                                                       //                             \\
            //robot.ssScrew.setPower(-gamepad2.left_stick_y); //full lift 2250                 \\
            if(gamepad2.right_stick_y < -0.1){
                robot.ssExtend.setPower(gamepad2.right_stick_y / 2);
            }else if(gamepad2.right_stick_y > 0.1){
                robot.ssExtend.setPower(gamepad2.right_stick_y / 2);
            }else{
                robot.ssExtend.setPower(-0.10);
            }
            
            robot.ssTwist.setPosition(twist);
            
            telemetry.addData("Screw Rotation: ", robot.ssScrew.getCurrentPosition());
            telemetry.addData("Extension: ", robot.ssExtend.getCurrentPosition());
            telemetry.update();

            //////////////////////////// DRIVER 1 &&&&&&&&&&&&&&&&& DRIVER 2 //////////////////////

            if(gamepad1.x && gamepad2.x){
                robot.ssClaw.setPosition(-1);    //NUKE
            }
            
            
        
       }
      }
     }
